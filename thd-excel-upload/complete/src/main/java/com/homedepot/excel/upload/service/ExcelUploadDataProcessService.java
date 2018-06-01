package com.homedepot.excel.upload.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.homedepot.excel.upload.dao.User;
import com.homedepot.excel.upload.dto.ExcelUploadDto;

@Service
public interface ExcelUploadDataProcessService {
	
	public void  insertExcelData(List<User> userList);
	
	public ExcelUploadDto processFiles(MultipartFile[] uploadingFiles,int maxRowsAllowed);
	
	public ExcelUploadDto getUserById(String userId);
	
	public ExcelUploadDto getUserByName(String userName);
	
	public ExcelUploadDto getAllUser();

}
