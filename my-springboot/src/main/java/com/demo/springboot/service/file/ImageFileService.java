package com.demo.springboot.service.file;

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
	
	public Map<String, Object> uploadImage(MultipartFile attachFile, String uploadPath) {
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
					fileName = PropertiesUtil.get("upload_path_save") + DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"//" + fileName;
					resMap.put("fileName", fileName);
					return resMap;
				}
			}
		} catch (Exception e) {
			logger.error("upload image exception："+e.getMessage());
			return responseError(ErrorConstant.ERROR_GENERAL, e.getMessage());
		}
		return responseError(ErrorConstant.ERROR_GENERAL, "未知错误");
	}

	public Map<String, Object> uploadImage(MultipartFile attachFile) {
		String uploadPath = PropertiesUtil.get("upload_path"); 
    	uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"//";
		return this.uploadImage(attachFile, uploadPath);
	}
	
}
