package com.demo.my.pic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.PicBlog;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.PicBlogServiceBean;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.util.RegularUtil;
import com.demo.my.pic.mina.MinaClient;

@Controller
public class PicController extends BaseController{
	
	@Resource(name = "picBlogServiceBean")
	private PicBlogServiceBean picBlogService;
	@Autowired
	private MinaClient minaClient;
	
	/**
	 * 图片列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pic/list", method=RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pic/list");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("needPic", true);
		paramMap.put("isDeleted", 0);
		List<Map<String, Object>> list = picBlogService.getMapListByParm(new PageUtil(1, 8), paramMap, KeyConstant.MAPPER_PIC);
		modelAndView.addObject("picList", list);
		modelAndView.addObject("isLogin", this.isLogin());
		return modelAndView;
	}
	
	/**
	 * 文章详情页面
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/pic/detail", method=RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response, Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pic/detail");
		PicBlog p = picBlogService.getById(id, KeyConstant.MAPPER_PIC);
		modelAndView.addObject("picBlog", p);
		modelAndView.addObject("isLogin", this.isLogin());
		return modelAndView;
	}

	/**
	 * 跳转到创建页面
	 */
	@RequestMapping(value = "/auth/pic/createPic", method=RequestMethod.GET)
	public ModelAndView createPic(HttpServletRequest request,HttpServletResponse response, Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pic/create");
		modelAndView.addObject("isLogin", this.isLogin());
		PicBlog picBlog = picBlogService.getById(id, KeyConstant.MAPPER_PIC);
		if(id!=null){
			modelAndView.addObject("id", id);
		}
		modelAndView.addObject("picBlog", picBlog);
		return modelAndView;
	}
	
	/**
	 * 提交表单
	 */
	@ResponseBody
	@RequestMapping(value = "/pic/doSubmit", method=RequestMethod.POST)
	public HashMap<String, Object> doSubmit(PicBlog picBlog) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		Long userId = getCurrentUserId();
		if (null == userId || userId == 0L) {
			return responseError(ErrorConstant.ERROR_400, ErrorConstant.NOT_LOGIN);
		}
		if(StringUtils.isBlank(picBlog.getTitle())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_TITLE);
		} else if(StringUtils.isBlank(picBlog.getContent())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_CONTENT);
		}
		picBlog.setUserId(userId);
		String reg = "<img\\s*([\\w]+=(\"|\')([^\"\']*)(\"|\')\\s*)*/>"; //截取img
		//String reg = "src=\"?(.*?)(\"|>|\\s+)"; //截取src
		String picPath = RegularUtil.cutContent(reg, picBlog.getContent()); 
		picBlog.setShowPic(picPath);
		String description = RegularUtil.Html2Text(picBlog.getContent());
		if (description.length() > 80) {
			description = description.substring(0,80);	
		}
		picBlog.setDescription(description);
		
		if(picBlog.getId()!=null){
			picBlogService.update(picBlog);
		} else {
			picBlogService.insert(picBlog);	
		}
		
		minaClient.getIoSession().write("hello world");
		
		resMap.put(ErrorConstant.ERROR_NO, ErrorConstant.ERROR_200);
		resMap.put("id", picBlog.getId());
		return resMap;
	}
}
