package com.demo.my.base.model.dto;

public class FileDto {
	
	private String fileName; //文件名
	private String updateTime; //修改时间
	private String fileType; //文件类型
	private boolean isFile; //是文件类型或者文件夹
	private String filePath; //路径目录
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public boolean getIsFile() {
		return isFile;
	}
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}