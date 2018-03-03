package com.demo.my.backend.service.file;

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
        //����  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
}
