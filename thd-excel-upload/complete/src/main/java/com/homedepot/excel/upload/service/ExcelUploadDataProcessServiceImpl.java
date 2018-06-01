/**
 * 
 */
package com.homedepot.excel.upload.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import com.homedepot.excel.upload.dao.User;
import com.homedepot.excel.upload.dto.ExcelUploadDto;
import com.homedepot.excel.upload.exception.ErrorDetails;
import com.homedepot.excel.upload.repository.UserRepository;
import com.homedepot.excel.upload.util.ExcelUploadUtil;
import com.homedepot.excel.upload.validation.ExcelUploadValidator;

/**
 * @author Gnanamuthu
 *
 */
@Component
public class ExcelUploadDataProcessServiceImpl implements ExcelUploadDataProcessService {

	@Autowired
	private ExcelUploadUtil excelUploadUtil;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExcelUploadDto excelUploadDto;
	@Autowired
	private ExcelUploadValidator excelUploadValidator;

	@Override
	public void insertExcelData(List<User> userList) {

	}

	public ExcelUploadDto processFiles(MultipartFile[] uploadingFiles, int maxRowsAllowed) {		
		List<User> userList = null;
		List<ErrorDetails> errorList = null;
		List<String> uploadStatus = null;
		for (MultipartFile file : uploadingFiles) {

			if (file.isEmpty()) {
				if(errorList == null)
					errorList = new ArrayList<ErrorDetails>();
				// redirectAttributes.addFlashAttribute("message", "Please select a file to
				// upload");
				// return "redirect:uploadStatus";
				errorList.add(new ErrorDetails(new Date(),"the file " + file.getOriginalFilename()," is empty"));
				excelUploadDto.setExceptionList(errorList);
				continue;
			}

			else if(StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),"csv"))
			{
				if(userList == null)
					userList = new ArrayList<User>();
				excelUploadDto = processCSVFile(file,maxRowsAllowed);
				userList.addAll(userList.size(),excelUploadDto.getUserList());
			}
			else 
			{
				if(userList == null)
					userList = new ArrayList<User>();
				excelUploadDto = processExcelFile(file,maxRowsAllowed);
				userList.addAll(userList.size(), excelUploadDto.getUserList());
			}

			if (uploadStatus == null)
				uploadStatus = new ArrayList<String>();
			uploadStatus
					.add("Excel File has been uploaded successfully with the name  " + file.getOriginalFilename());			
			excelUploadDto.setUploadStatus(uploadStatus);			
		}
		userRepository.saveAll(userList);
		return excelUploadDto;
	}
	private ExcelUploadDto processCSVFile(MultipartFile file, int maxRowsAllowed)
	{
		 List<ErrorDetails> errorList = null;
		 List<User> userList = null;
        try {
           /* reader = new CSVReader(new InputStreamReader(file.getInputStream()));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
                
            }*/
        	CellProcessor[] cellProcessors = new CellProcessor[] {
                    new NotNull(), // ID
                    new NotNull(), // Name
                    new NotNull(), // Email             
            };
        	final String[] header = {"id","name","email"};
        	ICsvBeanReader csvBeanReader = new CsvBeanReader(new InputStreamReader(file.getInputStream()), CsvPreference.STANDARD_PREFERENCE);        	
        	User user = null;
            userList = new ArrayList<>();
            try {

                while ((user = csvBeanReader.read(User.class, header, cellProcessors)) != null) {
                    if(userList.size()<= maxRowsAllowed)
                    {                    	
                    	if(null != excelUploadValidator.validateUser(user))
                    	{
                    		if(errorList == null)                    		
                    			errorList = excelUploadValidator.validateUser(user);  
                    		else 
                    			errorList.addAll(errorList.size(), excelUploadValidator.validateUser(user));
                    	}
                    	else                    		
                    		userList.add(user);
                    }
                    else
                    {
                    	if(errorList == null)
        					errorList = new ArrayList<ErrorDetails>();
                    	errorList.add(new ErrorDetails(new Date()," CVS File "+file.getOriginalFilename() ," Exceeds Maximum Size allowed "+maxRowsAllowed));
                    	break;
                    	
                    }
                    System.out.println("deserialized " + user);
                    
                }
            } finally {
                if (csvBeanReader != null) {
                    csvBeanReader.close();
                }
            }
        	
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(errorList!=null)
        	excelUploadDto.setExceptionList(errorList);
        excelUploadDto.setUserList(userList);
		return excelUploadDto;
		
	}
	
	private ExcelUploadDto processExcelFile(MultipartFile file,int maxRowsAllowed)
	{
		List<ErrorDetails> errorList = null;
		List<User> userList = null;
        try {
        	// Get the file and save it somewhere
			InputStream stream = file.getInputStream();

			Workbook workbook = null;
			workbook = excelUploadUtil.getWorkbook(file, stream);

			// Get the first worksheet
			Sheet sheet = workbook.getSheetAt(0);

			// Sheet sheet = workbook.getSheetAt(0); /// this will read 1st workbook of
			// ExcelSheet
			userList = new ArrayList<User>();
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				Row row = (Row) rows.next();
				if (row.getRowNum() != 0) {
					User user = new User();
					Iterator<?> cells = row.cellIterator();
					int count = 1;
					while (cells.hasNext()) {

						Cell cell = (Cell) cells.next();

						switch (count) {
						case 1:
							user.setId(String.valueOf((int)cell.getNumericCellValue()));
							break;
						case 2:
							user.setName(cell.getStringCellValue());
							break;
						case 3:
							user.setEmail(cell.getStringCellValue());
							break;

						}

						count++;			
					}					
					if(userList.size()<= maxRowsAllowed)
                    {
						if(null != excelUploadValidator.validateUser(user))
                    	{
                    		if(errorList == null)                    		
                    			errorList = excelUploadValidator.validateUser(user);  
                    		else 
                    			errorList.addAll(errorList.size(), excelUploadValidator.validateUser(user));
                    	}
                    	else                    		
                    		userList.add(user);                    	
                    }
                    else
                    {
                    	if(errorList == null)
        					errorList = new ArrayList<ErrorDetails>();
                    	errorList.add(new ErrorDetails(new Date()," The Exel File "+file.getOriginalFilename() ," Exceeds Maximum Size allowed "+maxRowsAllowed));
                    	
                    }
					System.out.println("user " + user.getId());
				} else {
					continue;
				}
			}			        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(errorList!=null)
        	excelUploadDto.setExceptionList(errorList);
        excelUploadDto.setUserList(userList);
		return excelUploadDto;
		
	}

	@Override
	public ExcelUploadDto getUserById(String userId) {

		excelUploadDto.setUserList((List<User>)userRepository.findUserById(userId));
		
		return excelUploadDto;
	}

	@Override
	public ExcelUploadDto getUserByName(String userName) {
		
		excelUploadDto.setUserList((List<User>)userRepository.findUserByName(userName));
		
		return excelUploadDto;
	}

	@Override
	public ExcelUploadDto getAllUser() {
		
		excelUploadDto.setUserList((List<User>)userRepository.findAll());
		
		return excelUploadDto;
	}
}
