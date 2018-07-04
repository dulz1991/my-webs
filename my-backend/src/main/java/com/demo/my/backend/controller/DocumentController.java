package com.demo.my.backend.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.util.FileUtil;
import com.demo.my.base.util.PropertiesUtil;

@Controller
@RequestMapping("/doc")
public class DocumentController extends BaseBackendController {
	
	/**
	 * 程序资料
	 * @param toUrl
	 * @return
	 */
	@RequestMapping("/programming")
	public ModelAndView index(String toUrl) {
		ModelAndView mw = new ModelAndView("document/programming");
		mw.addObject("loadUrl", "/doc/programming/files");
		return mw;
	}
	@ResponseBody
	@RequestMapping("/programming/files")
	public Map<String, Object> programmingFiles(String folder){
		Map<String, Object> resMap = responseOK();
		
		if(StringUtils.isBlank(folder)){
			folder = PropertiesUtil.get("programming_folder");
		}
		
		resMap.put("list", FileUtil.getFilesInFolder(folder));
		if(StringUtils.isNotBlank(folder)){
			resMap.put("parentFolder", folder.substring(0, folder.lastIndexOf("\\")));
		} else {
			resMap.put("parentFolder", "");	
		}
		
		return resMap;
	}
	
	/**
	 * 前端框架
	 */
	@RequestMapping("/frontFramework")
	public ModelAndView frontFramework(String toUrl) {
		ModelAndView mw = new ModelAndView("document/programming");
		mw.addObject("loadUrl", "/doc/frontFramework/files");
		return mw;
	}
	@ResponseBody
	@RequestMapping("/frontFramework/files")
	public Map<String, Object> frontFrameworkFiles(String folder){
		Map<String, Object> resMap = responseOK();
		
		if(StringUtils.isBlank(folder)){
			folder = PropertiesUtil.get("front_framework");
		}
		
		resMap.put("list", FileUtil.getFilesInFolder(folder));
		if(StringUtils.isNotBlank(folder)){
			resMap.put("parentFolder", folder.substring(0, folder.lastIndexOf("\\")));
		} else {
			resMap.put("parentFolder", "");	
		}
		
		return resMap;
	}
	
}
