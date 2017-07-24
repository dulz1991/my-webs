package com.demo.my.blog.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.my.base.service.file.ImgUploadService;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	private ImgUploadService imgUploadService;

	@ResponseBody
	@RequestMapping(value = "/doImgUpload", method = RequestMethod.POST)
	public void doImgUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*imgUploadService.uploadBlogImage(request, response);*/
		imgUploadService.uploadBlogImage(request);
	}
	
}
