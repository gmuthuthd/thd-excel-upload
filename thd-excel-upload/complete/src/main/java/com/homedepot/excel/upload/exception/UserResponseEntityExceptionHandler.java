/**
 * 
 */
package com.homedepot.excel.upload.exception;

import java.util.Date;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 358167
 *
 */


@ControllerAdvice
@Controller
public class UserResponseEntityExceptionHandler {
	
	

	@ExceptionHandler(UserNotFoundException.class) 
	  public final ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    ModelAndView modelAndView = new ModelAndView();		 
		modelAndView.setViewName("userDisplay");
    	modelAndView.addObject("errorDetails", errorDetails); 
	    return new ResponseEntity<Object>(modelAndView, HttpStatus.NOT_FOUND);
	  }

}
