package com.emhc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.emhc.dto.UserDTO;
import com.emhc.model.EmhcUser;
import com.emhc.service.UserService;
@Component
public class UserDTOValidator implements Validator {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDTOValidator.class);
    private final UserService userService;

    @Autowired
    public UserDTOValidator(UserService userService) {
        this.userService = userService;
    }

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UserDTO userDTO = (UserDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username", "User name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "error.firstname", "First name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "error.lastname", "Last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email", "Email is required.");
		validatePasswords(errors,userDTO);
		validateEmail(errors, userDTO);
	}
	
    private void validatePasswords(Errors errors, UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmpassword())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }
    
    private void validateEmail(Errors errors, UserDTO userDTO) {
    	EmhcUser userExists  = userService.findUserByEmail(userDTO.getEmail());
        if (userExists != null) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }

}
