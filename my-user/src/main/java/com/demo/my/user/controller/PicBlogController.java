package com.demo.my.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.PicBlog;
import com.demo.my.base.bean.User;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.PicBlogServiceBean;
import com.demo.my.base.servicebean.UserServiceBean;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.converter.MapConverter;

@Controller
@RequestMapping("/auth/picblog")
public class PicBlogController extends BaseController {
	
	@Resource(name = "picBlogServiceBean")
	private PicBlogServiceBean picBlogService;
	@Resource(name = "userServiceBean")
	private UserServiceBean userService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("picblog/pic_blog_list");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getList", method=RequestMethod.GET)
	public Map<String, Object> list(PicBlog picBlog, PageUtil pageUtil) throws UnsupportedEncodingException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		pageUtil = new PageUtil(pageUtil.getPageNum(), pageUtil.getPageSize());
		paramMap.put("needPic", false);
		if(StringUtils.isNotBlank(picBlog.getTitle())){
			String title = URLDecoder.decode(picBlog.getTitle(), "utf-8");
			paramMap.put("title", title);	
		}
		if(StringUtils.isNotBlank(picBlog.getUsername())){
			String username = URLDecoder.decode(picBlog.getUsername(), "utf-8");
			User user = userService.getByUsername(username);
			if (user != null) {
				paramMap.put("userId", user.getId());	
			}
		} else {
	    	 User user = this.getCurrentUser();
	    	 if (user != null && user.getRole() != 1) {
	    		 paramMap.put("userId", user.getId());
	    		 paramMap.put("status", 0);
	    	 }
		}
		
		List<Map<String, Object>> list = picBlogService.getMapListByParm(pageUtil, paramMap, KeyConstant.MAPPER_PIC);
		list = new MapConverter().map2Map(list, null);
		for (Map<String, Object> map : list) {
			String userId = map.get("userId").toString();
			map.put("username", userService.getById(Long.valueOf(userId)).getUsername());
		}
		Integer count = picBlogService.countByParm(paramMap, KeyConstant.MAPPER_PIC);
		
		pageUtil = new PageUtil(pageUtil.getCurrentPage(), pageUtil.getPageSize(), count);
		resMap.put("list", list);
		resMap.put("page", pageUtil);

		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/doDelete", method=RequestMethod.GET)
	public HashMap<String, Object> doDelete(@RequestParam(value="id", defaultValue="0")String id) {
		PicBlog picBlog = picBlogService.getById(Long.parseLong(id), KeyConstant.MAPPER_PIC);
		if (null == picBlog) {
			return responseError(ErrorConstant.ERROR_404, ErrorConstant.ERROR_NO_RECORD);
		}
		picBlog = new PicBlog();
		picBlog.setStatus(1);
		picBlog.setId(Long.parseLong(id));
		picBlogService.update(picBlog);
		return responseOK(ErrorConstant.TIP_DELETE_SUCCESS);
	}

}
