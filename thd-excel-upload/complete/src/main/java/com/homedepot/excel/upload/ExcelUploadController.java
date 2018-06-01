package com.homedepot.excel.upload;

import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.homedepot.excel.upload.dao.User;
import com.homedepot.excel.upload.dto.ExcelUploadDto;
import com.homedepot.excel.upload.exception.UserNotFoundException;
import com.homedepot.excel.upload.repository.UserRepository;
import com.homedepot.excel.upload.service.ExcelUploadDataProcessService;

import io.swagger.annotations.Api;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/excel") // This means URL's start with /demo (after Application path)
@Api(value = "ThreshholdManagementWS",description = "These APIs are used to manage threshold limits details")
public class ExcelUploadController {	
	
	@Autowired
	ExcelUploadDataProcessService excelUploadDataProcessService;
	
	@Value( "${excel.csv.max.row.size}" )
	private int maxRowsAllowed;
	
	@GetMapping(path="/displayAllUsers")
	public ResponseEntity<T>   getAllUsers() {	
		ExcelUploadDto excelUploadDto = null;
		excelUploadDto = excelUploadDataProcessService.getAllUser();
		if(null == excelUploadDto.getUserList() || excelUploadDto.getUserList().size()==0)
			throw new UserNotFoundException("No user Data available");
		ModelAndView modelAndView = new ModelAndView();		 
		modelAndView.setViewName("userDisplay");
    	modelAndView.addObject("excelUploadDto", excelUploadDto);    	
		return new ResponseEntity (modelAndView,HttpStatus.OK);
	}
	@GetMapping(path="/searchUserById")
	public ResponseEntity<T>   getUserById(@RequestParam("id") String userId) {	
		ExcelUploadDto excelUploadDto = null;
		//excelUploadDto.setUserList((List<User>)userRepository.findUserById(id));
		excelUploadDto = excelUploadDataProcessService.getUserById(userId);
		if(null == excelUploadDto.getUserList() || excelUploadDto.getUserList().size()==0)
			throw new UserNotFoundException("No user Data available for the specified user Id "+userId);		
		ModelAndView modelAndView = new ModelAndView();		 
		modelAndView.setViewName("userDisplay");
    	modelAndView.addObject("excelUploadDto", excelUploadDto);    	
		return new ResponseEntity (modelAndView,HttpStatus.OK);
	}
	@GetMapping(path="/searchUserByName")
	public ResponseEntity<T>   getUserByName(@RequestParam("name") String userName) {	
		ExcelUploadDto excelUploadDto = null;
		//excelUploadDto.setUserList((List<User>)userRepository.findUserByName(name));
		excelUploadDto = excelUploadDataProcessService.getUserByName(userName);
		if(null == excelUploadDto.getUserList() || excelUploadDto.getUserList().size()==0)
			throw new UserNotFoundException("No user Data available for the specified user Name "+userName);
		ModelAndView modelAndView = new ModelAndView();		 
		modelAndView.setViewName("userDisplay");
    	modelAndView.addObject("excelUploadDto", excelUploadDto);    	
		return new ResponseEntity (modelAndView,HttpStatus.OK);
	}
	
	@GetMapping("/uploadHome")
    public String index() {
	
        return "upload";
    }

    @PostMapping("/uploadData") 
	//@RequestMapping(value = "/uploadData", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<T> excelFileUpload(@RequestParam("file") MultipartFile[] uploadingFiles) {		
    	System.out.println("Hi uploadData ");
    	ExcelUploadDto excelUploadDto = null;
    	//for(MultipartFile file : uploadingFiles) {
    		 /*if (file.isEmpty()) {
    	            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
    	            return "redirect:uploadStatus";
    	        }*/
    		excelUploadDto = excelUploadDataProcessService.processFiles(uploadingFiles, maxRowsAllowed);                      
    //	}
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("uploadStatus");
    	modelAndView.addObject("excelUploadDto", excelUploadDto);
    	return new ResponseEntity (modelAndView,HttpStatus.OK);
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
