package com.demo.my.base.service.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.util.DateUtil;
import com.demo.my.base.util.PropertiesUtil;

@Component
public class ImageFileService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(ImageFileService.class);
	
	public Map<String, Object> uploadImage(MultipartFile attachFile, String uploadPath, String savePath) {
		if(attachFile==null){
			return responseError(ErrorConstant.ERROR_GENERAL, "请选择上传文件");
		}
		File dir = new File(uploadPath);
		checkDir(dir);
		
		try {
			if(attachFile!=null){
				String fileName = attachFile.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					
					if(attachFile.getSize()>IMG_MAXI_SIZE){
						return responseError(ErrorConstant.ERROR_GENERAL, "文件太大，请重新选择");
					}
					
					Pattern reg = Pattern.compile("[.]jpg|JPG|png|PNG|jpeg|JPEG|gif|GIF$");
					Matcher matcher = reg.matcher(fileName);
					if (!matcher.find()) {
						return responseError(ErrorConstant.ERROR_GENERAL, "文件格式错误");
					}
					String ftype = matcher.group();
					fileName = new Date().getTime() + ftype;
					uploadPath = uploadPath + fileName;
					BufferedInputStream in = new BufferedInputStream(attachFile.getInputStream());
					FileOutputStream a = new FileOutputStream(new File(uploadPath));
					BufferedOutputStream output = new BufferedOutputStream(a);
					Streams.copy(in, output, true);
					
					Map<String, Object> resMap = responseOK("");
					resMap.put("fileName", fileName);
					resMap.put("type", ftype);
					resMap.put("url", savePath+"fileName");
					return resMap;
				}
			}
		} catch (Exception e) {
			logger.error("upload image exception�?"+e.getMessage());
			return responseError(ErrorConstant.ERROR_GENERAL, e.getMessage());
		}
		return responseError(ErrorConstant.ERROR_GENERAL, "未知错误");
	}

	//code图片上传
	public Map<String, Object> uploadCodeImage(MultipartFile attachFile) {
		 String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
         String uploadPath = PropertiesUtil.get("pic_code_path_upload");
         uploadPath = uploadPath.replace("{{yyyymmdd}}", dateStr);
         String savePath = PropertiesUtil.get("pic_code_path_save");
         savePath = savePath.replace("{{yyyymmdd}}", dateStr);
		return this.uploadImage(attachFile, uploadPath, savePath);
	}
	
	//blog图片上传
	public Map<String, Object> uploadBlogImage(MultipartFile attachFile) {
		 String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
         String uploadPath = PropertiesUtil.get("pic_blog_path_upload");
         uploadPath = uploadPath.replace("{{yyyymmdd}}", dateStr);
         String savePath = PropertiesUtil.get("pic_blog_path_save");
         savePath = savePath.replace("{{yyyymmdd}}", dateStr);
		return this.uploadImage(attachFile, uploadPath, savePath);
	}
	
	//vue图片上传
	public Map<String, Object> uploadVueImage(MultipartFile attachFile) {
		 String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
         String uploadPath = PropertiesUtil.get("pic_vue_path_upload");
         uploadPath = uploadPath.replace("{{yyyymmdd}}", dateStr);
         String savePath = PropertiesUtil.get("pic_vue_path_save");
         savePath = savePath.replace("{{yyyymmdd}}", dateStr);
		return this.uploadImage(attachFile, uploadPath, savePath);
	}

	//user图片上传
	public Map<String, Object> uploadUserImage(MultipartFile attachFile) {
		 String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
         String uploadPath = PropertiesUtil.get("pic_user_path_upload");
         uploadPath = uploadPath.replace("{{yyyymmdd}}", dateStr);
         String savePath = PropertiesUtil.get("pic_user_path_save");
         savePath = savePath.replace("{{yyyymmdd}}", dateStr);
		return this.uploadImage(attachFile, uploadPath, savePath);
	}
	
}
