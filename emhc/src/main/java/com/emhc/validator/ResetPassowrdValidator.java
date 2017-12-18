package com.emhc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.emhc.dto.ResetPassword;

@Component
public class ResetPassowrdValidator implements Validator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResetPassowrdValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ResetPassword.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOGGER.debug("Validating {}", target);
		ResetPassword form = (ResetPassword) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldpassword", "error.oldpassword",
				"Old PASSWORD field is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password",
				"New PASSWORD field is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmpassword", "error.confirmpassword",
				"Confirmed PASSWORD field is mandatory");

		validatePasswords(errors, form);
	}

	private void validatePasswords(Errors errors, ResetPassword form) {
		if (!form.getPassword().equals(form.getConfirmpassword())) {
			LOGGER.debug("password.no_match :" + form.getPassword() + ", " + form.getConfirmpassword());
			errors.rejectValue("password", "NotMatch.resetPassword.newPasswordConfirm");
		}
	}

}
