package com.emhc.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
			Method[] methods = c.getMethods();
			Object object = c.newInstance();

			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().startsWith("getButton")) {
					System.out.println(methods[i].getName());
					Method m = methods[i];
					if (m.invoke(object) == null) {
						errors.rejectValue("button01", "Assessment.select.error");

					} else if (methods[i].invoke(object).equals("Yes")) {

						String name = methods[i].getName();
						name.replace("Button", "Answer");
						System.out.println("----------------------" + name);
						Method mm = c.getMethod(name, null);
						if (mm.invoke(object).equals(null)) {
							LOGGER.debug("email not in right format");
							errors.rejectValue("button01", "Assessment.selected.error");

						}

					}

				}
			}

			/*
			 * if (form.getButton01() == null) { errors.rejectValue("button01",
			 * "Assessment.select.error");
			 * 
			 * } else {
			 * 
			 * System.out.println("-------button is --------------" +
			 * form.getButton01()); if
			 * (form.getButton01().equalsIgnoreCase("Yes")) {
			 * 
			 * System.out.println("-------button is --------------" +
			 * form.getButton01().equalsIgnoreCase("Yes"));
			 * System.out.println("----answer is --------"+ form.getAnswer1());
			 * } if (form.getAnswer1().isEmpty()) {
			 * System.out.println("-------button is here--------------");
			 * LOGGER.debug("email not in right format");
			 * errors.rejectValue("button01", "Assessment.selected.error");
			 * 
			 * } }
			 */ } catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private String getClassName() {

		// Do appropriate stuff here to find out the classname

		return "com.emhc.dto.AnswerDTO";
	}
}
