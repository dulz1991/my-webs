package com.demo.my.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.my.base.util.PropertiesUtil;
import com.demo.my.user.service.UserImgUploadService;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	private UserImgUploadService imgUploadService;

	@ResponseBody
	@RequestMapping(value = "/doImgUpload", method = RequestMethod.POST)
	public void doImgUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "upfile", required = false) MultipartFile attachFile) {
		String realPath = PropertiesUtil.get(PropertiesUtil.PIC_CODE_PATH);
		imgUploadService.imageUpload(request, response, attachFile,realPath);
	}
	
}
