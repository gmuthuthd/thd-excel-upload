package com.homedepot.excel.upload.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.homedepot.excel.upload.dao.User;
import com.homedepot.excel.upload.exception.ErrorDetails;

@Component
public class ExcelUploadDto {
	
	List<User> userList;
	List<ErrorDetails> errorList;
	List<String> uploadStatus;
	
	public List<String> getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(List<String> uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public List<ErrorDetails> getExceptionList() {
		return errorList;
	}

	public void setExceptionList(List<ErrorDetails> errorList) {
		this.errorList = errorList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	

}
