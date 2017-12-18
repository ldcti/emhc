package com.emhc.controller.base;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	protected static final String DATE_FORMAT = "yyyy-MM-dd";

	protected static final String ERROR_VIEW = "error/error";

	@ExceptionHandler({ Exception.class })
	public ModelAndView uncaughtExceptionHandling(Exception e, HttpServletResponse httpresponse) {

		logger.info("General Error is caught" + e.getMessage());
		e.printStackTrace();
		// String errorMsg = "";
		String errorDetails = "N/A";

		final ModelAndView model = initModelView(ERROR_VIEW);

		if (e != null) {
			errorDetails = e.getMessage();

			if (e.getCause() != null && e.getCause().getMessage() != null) {
				errorDetails += " - " + e.getCause().getMessage();
			}

		}

		model.addObject("errordetails", errorDetails);

		return model;

	}

	private ModelAndView initModelView(String view) {
		ModelAndView model = new ModelAndView(ERROR_VIEW);
		/*
		 * if(view != null && !view.isEmpty() && !ERROR_VIEW.equals(view)) {
		 * model = new ModelAndView(view); }
		 * 
		 * Boolean isAdmin = userService.isAdmin();
		 * 
		 * model.addObject(USER_INFO, user); model.addObject(IS_ADMIN, isAdmin);
		 * 
		 * if(isAdmin) { List<Type> types = typeService.findAll();
		 * 
		 * model.addObject(ADMIN_STAGE, STAGE_HOME); model.addObject(TYPES,
		 * types); }
		 */

		return model;
	}
}
