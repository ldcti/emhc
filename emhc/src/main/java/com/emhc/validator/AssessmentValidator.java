package com.emhc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.emhc.dto.AnswerDTO;

@Component
public class AssessmentValidator implements Validator {

	Class<?> noparams[] = {};
	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AnswerDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOGGER.debug("Assessment Validating {} started", target);
		AnswerDTO form = (AnswerDTO) target;
		validateAnswerDTO(errors, form);

		// input string conatains numeric values only
	}

	private void validateAnswerDTO(Errors errors, AnswerDTO form) {

		try {
				
				Class<?> c = Class.forName(getClassName());

				for(int i = 1; i < 15; i++){
					String mtn = String.format("getButton%1$02d" , i);
					System.out.println(mtn);
					if(c.getDeclaredMethod(mtn, noparams).invoke(form) !=null){
						System.out.println(" name is "+ mtn);
					
					}

				}
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		
	}

	private String getClassName() {

		// Do appropriate stuff here to find out the classname

		return "com.emhc.dto.AnswerDTO";
	}
}