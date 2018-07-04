package com.demo.my.base.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.demo.my.base.model.dto.FileDto;

public class FileUtil {
    
   public static List<FileDto> getFilesInFolder(String folder) {
	   if(StringUtils.isBlank(folder)){
			return null;
		}
		
		File file = new File(folder);
	    File[] tempList = file.listFiles();
	    List<FileDto> list = new ArrayList<FileDto>();
	    
	    for (int i = 0; i < tempList.length; i++) {
	    	FileDto dto = new FileDto();
	        if (tempList[i].isFile()) {
	        	dto.setIsFile(true);
	        } else {
	            dto.setIsFile(false);
	        }
	        
	        String fileName = tempList[i].getName();
	        dto.setFileName(fileName);
	        String path = tempList[i].getAbsolutePath().replaceAll("\\\\", "\\\\\\\\");
	        dto.setFilePath(path);
	        dto.setUpdateTime(DateUtil.dateToString(new Date(tempList[i].lastModified()), DateUtil.DATETIME_FORMATE_2));
	        if(dto.getIsFile()){
	        	dto.setFileType(fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase());	
	        } else {
	        	dto.setFileType("文件夹");
			}
	        
	        list.add(dto);
	    }
	    
	    return list;
   }
   
   public static void main(String[] args) {
	   getFilesInFolder("F:\\data\\programming-data\\");
   }
    
}
