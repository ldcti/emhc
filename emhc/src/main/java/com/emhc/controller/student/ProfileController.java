package com.emhc.controller.student;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emhc.dto.ResetPassword;
import com.emhc.dto.StudentProfileUpdate;
import com.emhc.error.Message;
import com.emhc.model.EmhcUser;
import com.emhc.model.Program;
import com.emhc.security.LoginStudent;
import com.emhc.service.ProgramService;
import com.emhc.service.UserService;
import com.emhc.validator.ResetPassowrdValidator;
import com.emhc.validator.StudentProfileUpdateValidator;

/**
 * 
 * @author dong.liu
 *
 */
@Controller
@RequestMapping("/student")
public class ProfileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private ProgramService programService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private StudentProfileUpdateValidator profileValidator;
	@Autowired
	private ResetPassowrdValidator resetValidator;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@InitBinder("studentProfileUpdate")
	public void initProfileUpdateBinder(WebDataBinder binder) {
		binder.addValidators(profileValidator);
	}

	@InitBinder("resetPassword")
	public void initResetPasswordeBinder(WebDataBinder binder) {
		binder.addValidators(resetValidator);
	}

	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	public String profile(Model model) {

		System.out.println("-------run to profile here-------");
		String rtn = "/student/updateProfile";

		try {

			StudentProfileUpdate form = new StudentProfileUpdate();
			EmhcUser emhcuser = getPrincipal();
			LOGGER.info("$$$$ SP status: " + emhcuser.getUserid());
			form.setUsername(emhcuser.getUsername());
			System.out.println("------userid is " + emhcuser.getUserid());
			form.setUserid(emhcuser.getUserid());
			form.setFirstname(emhcuser.getFirstname());
			form.setLastname(emhcuser.getLastname());
			form.setEmail(emhcuser.getEmail());
			form.setProgramyear(emhcuser.getProgramyear());
			form.setProgram(emhcuser.getProgram());
			List<Program> programs = programService.findAll();
			form.setPrograms(programs);
			model.addAttribute("studentProfileUpdate", form);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profile(@Valid @ModelAttribute("studentProfileUpdate") StudentProfileUpdate form,
			BindingResult bindingResult, Model model) {

		String rtn = "/student/updateProfile";

		EmhcUser emhcuser = getPrincipal();

		Message message = new Message();

		LOGGER.debug("Processing updateProfile form={}, bindingResult={}", form, bindingResult);

		String username = form.getUsername();
		String firstname = form.getFirstname();
		String lastname = form.getLastname();
		String email = form.getEmail();
		Integer programyear = form.getProgramyear();
		Program program = form.getProgram();

		StudentProfileUpdate newForm = new StudentProfileUpdate();
		newForm.setUsername(username);
		newForm.setFirstname(firstname);
		newForm.setLastname(lastname);
		newForm.setEmail(email);
		newForm.setProgramyear(programyear);
		newForm.setProgram(program);
		List<Program> programs = programService.findAll();
		newForm.setPrograms(programs);
		model.addAttribute("studentProfileUpdate", newForm);

		try {
			// Form validation
			if (bindingResult.hasErrors()) {
				// failed validation
				System.out.println("-------run to here --------");
				LOGGER.debug("Profile form validation failed!!!!!!!!");
				List<ObjectError> errors = bindingResult.getAllErrors();
				String msg = messageSource.getMessage("StudentProfile.updateProfile.validation", new Object[] {},
						LocaleContextHolder.getLocale()) + "<br />";
				for (ObjectError i : errors) {
					if (i instanceof FieldError) {
						FieldError fieldError = (FieldError) i;
						msg += messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()) + "<br />";
					}
				}
				message.setStatus(Message.ERROR);
				message.setMessage(msg);
				model.addAttribute("message", message);

				return rtn;
			}

			// Required fields
			System.out.println("------userid is form------" + form.getUserid());
			System.out.println("------password is form------" + emhcuser.getPassword());

			emhcuser.setUsername(username);
			emhcuser.setFirstname(form.getFirstname());
			emhcuser.setLastname(form.getLastname());
			emhcuser.setEmail(form.getEmail());
			emhcuser.setProgramyear(form.getProgramyear());
			emhcuser.setProgram(form.getProgram());

			emhcuser = userService.saveUser(emhcuser);

			message.setStatus(Message.SUCCESS);
			message.setMessage(messageSource.getMessage("StudentProfile.updateProfile.success", new Object[] {},
					LocaleContextHolder.getLocale()));
		} catch (Exception e) {
			LOGGER.debug("Error in /student/profile POST of StudentProfile.  Error: " + e.getMessage());
			message.setStatus(Message.ERROR);
			message.setMessage(messageSource.getMessage("StudentProfile.updateProfile.error", new Object[] {},
					LocaleContextHolder.getLocale()));
		}

		model.addAttribute("message", message);

		return rtn;
	}

	@RequestMapping(value = { "/reset" }, method = RequestMethod.GET)
	public String reset(Model model) {

		System.out.println("-------run to reset here-------");
		String rtn = "/student/resetPassword";

		try {

			ResetPassword form = new ResetPassword();
			EmhcUser emhcuser = getPrincipal();
			LOGGER.info("$$$$ SP status: " + emhcuser.getUserid());
			model.addAttribute("resetPassword", form);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String reset(@Valid @ModelAttribute("resetPassword") ResetPassword form, BindingResult bindingResult,
			Model model) {

		String rtn = "/student/resetPassword";

		EmhcUser emhcuser = getPrincipal();

		Message message = new Message();
		String msg;
		String oldpassword = form.getOldpassword();
		String newpassword = form.getPassword();
		String confirmpassword = form.getConfirmpassword();

		ResetPassword newForm = new ResetPassword();
		newForm.setOldpassword(oldpassword);
		newForm.setPassword(newpassword);
		newForm.setConfirmpassword(confirmpassword);

		model.addAttribute("resetPassword", newForm);

		LOGGER.debug("Processing resetPassword form={}, bindingResult={}", form, bindingResult);

		try {

			// Form validation
			if (bindingResult.hasErrors()) {
				// failed validation
				model.addAttribute("ResetPassword", form);
				LOGGER.debug("Profile form validation failed!!!!!!!!");
				List<ObjectError> errors = bindingResult.getAllErrors();
				msg = messageSource.getMessage("StudentProfile.updatePassword.validation", new Object[] {},
						LocaleContextHolder.getLocale()) + "<br />";
				for (ObjectError i : errors) {
					if (i instanceof FieldError) {
						FieldError fieldError = (FieldError) i;
						msg += messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()) + "<br />";
					}
				}
				message.setStatus(Message.ERROR);
				message.setMessage(msg);
				model.addAttribute("message", message);

				return rtn;
			}

			// Required fields
			System.out.println("------userid is form------" + form.getUserid());
			System.out.println("------password is form------" + emhcuser.getPassword());

			emhcuser.setPassword(bCryptPasswordEncoder.encode(newpassword));
			emhcuser = userService.saveUser(emhcuser);

			msg = messageSource.getMessage("StudentProfile.updatePassword.success", new Object[] {},
					LocaleContextHolder.getLocale());
			System.out.println("------msg is ------" + msg);
			message.setStatus(Message.SUCCESS);
			message.setMessage(msg);

		} catch (Exception e) {
			LOGGER.debug("Error in /student/profile POST of StudentProfile.  Error: " + e.getMessage());
			message.setStatus(Message.ERROR);
			message.setMessage(messageSource.getMessage("StudentProfile.updatePassword.error", new Object[] {},
					LocaleContextHolder.getLocale()));
		}

		model.addAttribute("message", message);

		return rtn;
	}

	private EmhcUser getPrincipal() {
		EmhcUser user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof LoginStudent) {
			user = ((LoginStudent) principal).getClient();
		} else {
			user = userService.getByUsername("");
		}
		return user;
	}

}
