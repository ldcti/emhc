package com.emhc.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.emhc.dto.UserDTO;
import com.emhc.model.EmhcUser;
import com.emhc.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/login/login");
		return modelAndView;
	}

	@RequestMapping(value = { "/userCreation" }, method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		UserDTO user = new UserDTO();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("admin/registration");
		return modelAndView;
	}

	@RequestMapping(value = "/userCreation", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid UserDTO userDTO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		EmhcUser userExists = userService.getByUsername(userDTO.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user",
					"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(userDTO.getUser());
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new UserDTO());
			modelAndView.setViewName("admin/registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		EmhcUser user = userService.getByUsername(auth.getName());
		modelAndView.addObject("userName",
				"Welcome " + user.getUsername() + " " + user.getLastname() + " (" + user.getUsername() + ")");
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role!!!");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

}
