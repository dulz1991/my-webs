package com.demo.my.user.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UserImgUploadService {
	
	Logger logger = Logger.getLogger(this.getClass());

	public void imageUpload(HttpServletRequest request,
			HttpServletResponse response, MultipartFile attachFile, String realPath) {
		String title = ""; // ͼƬ����
		String url = ""; // ͼƬ��ַ
		String fileName = "";
		String originalName = "";
		String state = "SUCCESS";
		String ftype = "";
		try {
			// �ж�·���Ƿ���ڣ��������򴴽�
			File dir = new File(realPath);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}
			if(attachFile!=null){
				fileName = attachFile.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					Pattern reg = Pattern.compile("[.]jpg|png|jpeg|gif$");
					Matcher matcher = reg.matcher(fileName);
					if (!matcher.find()) {
						state = "File type are not allowed!";
						return;
					}
					ftype = matcher.group();
					fileName = new Date().getTime() + "." + ftype;
					url = realPath + "/" + fileName;
					BufferedInputStream in = new BufferedInputStream(attachFile.getInputStream());// ����ļ�������
					FileOutputStream a = new FileOutputStream(new File(url));
					BufferedOutputStream output = new BufferedOutputStream(a);
					Streams.copy(in, output, true);// ��ʼ���ļ�д����ָ�����ϴ��ļ���
				}
			}
			
			title = title.replace("&", "&amp;").replace("'", "&qpos;")
					.replace("\"", "&quot;").replace("<", "&lt;")
					.replace(">", "&gt;");
			String outputPat = "{'original':'"
					+ originalName
					+ "','url':'" + realPath.substring(realPath.lastIndexOf("\\") + 1,
							realPath.length()) + "/" + fileName + "','title':'"
					+ title + "','state':'" + state + "'}";
			response.getWriter().print(outputPat);
		} catch (Exception e) {
			logger.error("图片上传错误"+e.getCause());
		}
				
	}
}
