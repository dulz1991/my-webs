package com.demo.my.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.my.base.service.file.ImgUploadService;
import com.demo.my.base.util.PropertiesUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	private ImgUploadService imgUploadService;

	@ResponseBody
	@RequestMapping(value = "/doImgUpload", method = RequestMethod.POST)
	public void doImgUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String imgPath = PropertiesUtil.get(PropertiesUtil.PIC_BLOG_PATH);
		imgUploadService.imageUpload(imgPath, request, response);
	}
	
}
