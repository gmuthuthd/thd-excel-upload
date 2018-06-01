/**
 * 
 */
package com.homedepot.excel.upload.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.stereotype.Component;
import com.homedepot.excel.upload.dao.User;
import com.homedepot.excel.upload.exception.ErrorDetails;

/**
 * @author 358167
 *
 */
@Component
public class ExcelUploadValidator {
	
	
	public List<ErrorDetails> validateUser(User user)
	{
		Validator validator = null;
		List<ErrorDetails> errorList = null; 
		validator = ExcelUploadValidationFactory.getValidationFactory();
		Set<ConstraintViolation<User>> violationErrorSet = validator.validate(user);
		//errorList = new ArrayList<User>(violationErrorSet);
		for(ConstraintViolation<User> userConstViolation : violationErrorSet)
		{
			if(errorList == null)
				errorList = new ArrayList<ErrorDetails>();			 
			errorList.add(new ErrorDetails(new Date(), "Error Occured on User Id "+user.getId()+" ", userConstViolation.getMessage()));
		}
		return errorList;
		
	}
	

}
