/**
 * 
 */
package com.homedepot.excel.upload.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 358167
 *
 */
@Component
public class ExcelUploadUtil {
	
	public Workbook getWorkbook(MultipartFile file,InputStream stream) throws IOException
	{
		Workbook workbook = null;
		  if(file.getOriginalFilename().endsWith(".xlsx"))
	         workbook = new XSSFWorkbook(stream);
	        else if(file.getOriginalFilename().endsWith(".xls"))
	         workbook = new HSSFWorkbook(stream);
		  return workbook;
	}
	
	

}
