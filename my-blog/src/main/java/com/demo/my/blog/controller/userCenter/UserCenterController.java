package com.demo.my.blog.controller.userCenter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Blog;
import com.demo.my.base.model.BlogMenu;
import com.demo.my.base.model.User;
import com.demo.my.base.service.BlogService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.util.RegularUtil;
import com.demo.my.base.converter.MapConverter;
/*import com.demo.my.blog.jms.ProducerService;*/
import com.demo.my.blog.controller.common.BaseController;

@Controller
@RequestMapping("/auth/userCenter")
public class UserCenterController extends BaseController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;
	//队列消息生产者
    /*@Resource(name="producerService")
    private ProducerService producer;*/
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/index");
		
		User user = getCurrentUser();
		modelAndView.addObject("username", user.getUsername());
		modelAndView.addObject("phone", user.getPhone());
		modelAndView.addObject("email", user.getEmail());
		modelAndView.addObject("avatar", user.getAvatar());
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", user.getId());
		Integer myBlogCount = blogService.excute("BlogMapper.countByParm", paramMap);
		modelAndView.addObject("myBlogCount", myBlogCount);
		modelAndView.addObject("followBlogCount", 0);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/pwd", method=RequestMethod.GET)
	public ModelAndView pwd() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/pwd");
		modelAndView.addObject("username", this.getCurrentUser().getUsername());
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/modifyPwd", method=RequestMethod.POST)
	public HashMap<String, Object> modifyPwd(String newpwd, String oldpwd, String cfmpwd) {
		if(StringUtils.isBlank(newpwd)||StringUtils.isBlank(oldpwd)||StringUtils.isBlank(cfmpwd)){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_PWD);
		}
		if(!oldpwd.equals(cfmpwd)){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_NEW_CFM_PWD_NOT_SAME);
		}
		Long userId = this.getCurrentUserId();
		User user = new User();
		user.setPassword(newpwd);
		user.setId(userId);
		userService.update(user);
		return responseOK(ErrorConstant.ERROR_PWD_MODIFY_SUCCESS);
	}
	
	@RequestMapping(value = "/myBlogList", method=RequestMethod.GET)
	public ModelAndView myBlogList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/blog/myBlogList");
		
		List<BlogMenu> menuList = blogService.excute("BlogMapper.getBeanListByParm", null);
		modelAndView.addObject("menuList", menuList);
		User user = this.getCurrentUser();
		modelAndView.addObject("username", user.getUsername());
		modelAndView.addObject("avatar", user.getAvatar());
		
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/getMyBlogList", method=RequestMethod.GET)
	public HashMap<String, Object> list(Blog blog, PageUtil pageUtil) throws UnsupportedEncodingException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		pageUtil = new PageUtil(pageUtil.getPageNum(), pageUtil.getPageSize());
		if (StringUtils.isNotBlank(blog.getTitle())) {
			String title = URLDecoder.decode(blog.getTitle(), "utf-8");
			paramMap.put("title", title);
		}
		User user = this.getCurrentUser();
		if(user!=null){
			paramMap.put("userId", user.getId());	
		}
		if (blog.getMenuId() != null && blog.getMenuId() != -1) {
			paramMap.put("menuId", blog.getMenuId());
		}
		paramMap.put("start", pageUtil.getPageNum());
		paramMap.put("limit", pageUtil.getPageSize());
		List<Map<String, Object>> list = blogService.excute("BlogMapper.getMapListByParm", paramMap);
		list = new MapConverter().map2Map(list, null);
		Integer count = blogService.excute("BLogMapper.countByParm", paramMap);

		HashMap<String, Object> resMap = new HashMap<String, Object>();
		pageUtil = new PageUtil(pageUtil.getCurrentPage(), pageUtil.getPageSize(), count);
		resMap.put("list", list);
		resMap.put("page", pageUtil);

		return resMap;
	}
	
	@RequestMapping(value = "/followBlogList", method=RequestMethod.GET)
	public ModelAndView followBlogList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/blog/followBlogList");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editBlog", method=RequestMethod.GET)
	public ModelAndView editBlog(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/blog/editBlog");
		
		if(id!=null){
			Blog blog = blogService.getById(id);
			modelAndView.addObject("blog", blog);
			modelAndView.addObject("title", "编辑："+blog.getTitle());
		} else {
			modelAndView.addObject("title", "创建新的博客");
		}
		
		List<BlogMenu> menuList = blogService.excute("BlogMenuMapper.getBeanListByParm", null);
		modelAndView.addObject("menuList", menuList);
		modelAndView.addObject("username", this.getCurrentUser().getUsername());
		
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
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
		String reg = "(?<=src=(\"|\'))[\\S\\s]+?(?=(\"|\'))"; // 截取img的src
		String img = RegularUtil.cutContent(reg, blog.getContent());
		blog.setImg(img);
		String preContent = RegularUtil.Html2Text(blog.getContent());
		if (preContent.length() > 140) {
			blog.setPreContent(preContent.substring(0, 140));
		} else {
			blog.setPreContent(preContent);
		}

		blogService.save(blog);
		/*producer.sendMessage("cmd1");*/
		Map<String, Object> resMap = responseOK("");
		resMap.put("id", blog.getId());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteBlog", method = RequestMethod.POST)
	public Map<String, Object> delete(Long id) {
		if(id==null){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_ID);
		}
		blogService.delete(id);
		return responseOK("");
	}
	
}
