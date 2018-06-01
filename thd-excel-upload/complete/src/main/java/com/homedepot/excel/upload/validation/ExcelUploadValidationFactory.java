/**
 * 
 */
package com.homedepot.excel.upload.validation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

/**
 * @author 358167
 *
 */
@Component
public class ExcelUploadValidationFactory {
	
	private static Validator validator = null;
	
	
	/*public void setValidator(Validator validator) {
		this.validator = validator;
	}*/
	public static Validator getValidationFactory() {		 
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		return validator;
	}	 
}
