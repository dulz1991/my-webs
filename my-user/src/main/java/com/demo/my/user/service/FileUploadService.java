package com.demo.my.user.service;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadService {

	public void uploadAttachFile(MultipartFile file, String uploadPath) {
		File targetFile = new File(uploadPath, file.getOriginalFilename());
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //±£´æ  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
}
