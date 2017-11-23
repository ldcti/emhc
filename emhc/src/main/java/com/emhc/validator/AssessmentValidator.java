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

    private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentValidator.class);


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AssessmentValidator.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Assessment Validating {} started", target);
        AnswerDTO form = (AnswerDTO) target;
		validateAnswerDTO(errors, form);
			  
			// input string conatains numeric values only  
    }

    
    private void validateAnswerDTO(Errors errors, AnswerDTO form) {
    
	}

}
