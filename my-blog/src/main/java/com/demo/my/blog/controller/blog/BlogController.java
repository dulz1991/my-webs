package com.demo.my.blog.controller.blog;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Blog;
import com.demo.my.base.model.BlogLog;
import com.demo.my.base.model.BlogMenu;
import com.demo.my.base.model.User;
import com.demo.my.base.service.BlogLogService;
import com.demo.my.base.service.BlogMenuService;
import com.demo.my.base.service.BlogService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.util.RegularUtil;
import com.demo.my.blog.controller.common.BaseController;

@Controller
public class BlogController extends BaseController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogLogService blogLogService;
	@Autowired
	private BlogMenuService blogMenuService;
	@Autowired
	private UserService userService;
	
	/**
	 * blog首页
	 * @param blogId
	 * @param blogTitle
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/blog/index", method=RequestMethod.GET)
	public ModelAndView index(Long blogId, String blogTitle) throws UnsupportedEncodingException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("blog/index");
		
		//菜单列表
		List<BlogMenu> menuList = blogMenuService.getBeanListByParm(null);
		
		//菜单对应的blog数量
		Map<Long, Integer> menuCountMap = new HashMap<Long, Integer>();
		//菜单对应的blog列表
		Map<Long, Object> menuBlogMap = new HashMap<Long, Object>();
		
		HashMap<String, Object> parmMap = new HashMap<String, Object>();
		for (BlogMenu menu : menuList) {
			parmMap.put("menuId", menu.getId());
			parmMap.put("status", 0);
			
			//该菜单的blog数量
			/*Integer count = blogService.countByParm(parmMap);
			menuMap.put(menu.getId(), count);*/
			
			//查询该菜单下的blog列表
			Map<String, Object> blogParmMap = new HashMap<String, Object>();
			blogParmMap.put("menuId", menu.getId());
			List<Blog> list = blogService.getBeanListByParm(blogParmMap);
			if(list!=null && !list.isEmpty()){
				menuBlogMap.put(menu.getId(), list);	
			}
			
			//该菜单的blog数量
			menuCountMap.put(menu.getId(), list.size());
		}
		
		//查询blog
		if(blogId!=null){
			Blog blog = blogService.getById(blogId);
			modelAndView.addObject("entity", blog);	
			BlogMenu blogMenu = blogMenuService.getById(blog.getMenuId());
			if(blogMenu!=null){
				modelAndView.addObject("blogMenu", blogMenu);	
			}
		}
		
		modelAndView.addObject("blogTitle", blogTitle);
		modelAndView.addObject("blogId", blogId);
		modelAndView.addObject("menuList", menuList);
		modelAndView.addObject("menuCountMap", menuCountMap);
		modelAndView.addObject("menuBlogMap", menuBlogMap);
		return modelAndView;
	}
	
	/**
	 * 进入编辑页
	 * @param blogId
	 * @return
	 */
	@RequestMapping(value = "/auth/blog/edit", method=RequestMethod.GET)
	public ModelAndView edit(Long blogId, Long menuId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("blog/edit");
		
		if(blogId == null){
			modelAndView.addObject("blog", new Blog());
		} else {
			Blog blog = blogService.getById(blogId);
			modelAndView.addObject("blog", blog);	
		}
		
		modelAndView.addObject("blogMenu", new BlogMenu());
		if(menuId!=null){
			BlogMenu blogMenu = blogMenuService.getById(menuId);
			if(blogMenu!=null){
				modelAndView.addObject("blogMenu", blogMenu);	
			}
		}
		modelAndView.addObject("menuId", menuId);
		
		return modelAndView;
	}
	
	/**
	 * 保存
	 * @param blog
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/auth/blog/save")
	public Map<String, Object> saveBlog(Blog blog) {
		if(StringUtils.isBlank(blog.getTitle())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_TITLE);
		}
		if(StringUtils.isBlank(blog.getContent())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_CONTENT);
		}
		if(blog.getMenuId() == null){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_MENU);
		}
		
		blog.setUserId(this.getCurrentUserId());
		String reg = "(?<=src=(\"|\'))[\\S\\s]+?(?=(\"|\'))"; 
		String img = RegularUtil.cutContent(reg, blog.getContent());
		blog.setImg(img);
		String preContent = RegularUtil.Html2Text(blog.getContent());
		if (preContent.length() > 140) {
			blog.setPreContent(preContent.substring(0, 140));
		} else {
			blog.setPreContent(preContent);
		}

		blogService.save(blog);
		
		//更新blog log
		BlogLog blogLog = new BlogLog();
		blogLog.setBlogId(blog.getId());
		blogLog.setCreateTime(new Date());
		blogLog.setUpdateContent(blog.getContent());
		blogLog.setUserId(this.getCurrentUserId());
		if(blog.getId()!=null){
			blogLog.setRemark("新增文档");
		} else {
			blogLog.setRemark("更新文档");
		}
		blogLogService.insert(blogLog);
		
		Map<String, Object> resMap = responseOK("");
		resMap.put("id", blog.getId());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/auth/blog/queryBlogList", method = RequestMethod.GET)
	public HashMap<String, Object> blogList(Blog blog, PageUtil pageUtil) throws UnsupportedEncodingException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		int pageNum = pageUtil.getPageNum();
		pageUtil = new PageUtil(pageUtil.getPageNum(), pageUtil.getPageSize());
		paramMap.put("status", 0);
		if (blog.getMenuId() != null && blog.getMenuId() > 0) {
			paramMap.put("menuId", blog.getMenuId());
		}
		if(StringUtils.isNotBlank(blog.getTitle())){
			String title = URLDecoder.decode(blog.getTitle(), "utf-8");
			paramMap.put("title", title);	
		}
		User user = getCurrentUser();
		paramMap.put("userId", user.getId());
		
		List<Map<String, Object>> list = blogService.excute("BlogMapper.getMapListByParm", paramMap);
		Integer count = blogService.excute("BlogMapper.countByParm", paramMap);
		
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		pageUtil = new PageUtil(pageUtil.getPageNum(), pageUtil.getPageSize(), count);
		resMap.put("list", list);
		resMap.put("page", new PageUtil(pageNum, pageUtil.getPageSize(), count));
		return resMap;
	}
	
	@RequestMapping(value = "/auth/blog/blogDetail", method=RequestMethod.GET)
	public ModelAndView blog(HttpServletRequest request, HttpServletResponse response, Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("blog/blogDetail");
		
		Blog blog = blogService.getById(id);
		if (blog == null) {
			modelAndView.addObject("blog", new Blog());	
		} else {
			Long clickNum = blog.getClick();
			if(null==clickNum){
				blog.setClick(1L);
			}else{
				blog.setClick(blog.getClick() + 1);	
			}
			modelAndView.addObject("blog", blog);
		}
		User blogUser = userService.getById(blog.getUserId());
		modelAndView.addObject("username", blogUser.getUsername());
		
		User user = getCurrentUser();
		if (user != null) {
			modelAndView.addObject("role", user.getRole());
			modelAndView.addObject("isLogin", true);
		} else {
			modelAndView.addObject("role", -1);
			modelAndView.addObject("isLogin", false);
		}
		
		Blog entity = new Blog();
		entity.setId(id);
		entity.setClick(blog.getClick());
		blogService.update(entity);
		return modelAndView;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/auth/blog/delete")
	public ModelAndView delete(Long id) throws Exception {
		if(id==null){
			throw new Exception("删除失败");
		}
		blogService.delete(id);
		return new ModelAndView("redirect:/blog/index");
	}
	
	
}
