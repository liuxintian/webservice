package com.omt.cms.core.service.bo.base;

import java.io.Serializable;

public class FileInfoBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fileName;
	private int context;
	private String fileURL;
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getContext() {
		return context;
	}
	
	public void setContext(int context) {
		this.context = context;
	}
	
	public String getFileURL() {
		return fileURL;
	}
	
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	
}
