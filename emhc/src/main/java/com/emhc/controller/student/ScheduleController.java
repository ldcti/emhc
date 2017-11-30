package com.emhc.controller.student;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.emhc.dto.StudentScheduleTest;
import com.emhc.error.Message;
import com.emhc.model.EmhcUser;
import com.emhc.model.Schedule;
import com.emhc.model.Session;
import com.emhc.security.LoginStudent;
import com.emhc.service.ScheduleService;
import com.emhc.service.SessionService;
import com.emhc.service.UserService;


/**
 * 
 * @author dong.liu
 *
 */
@Controller
@RequestMapping("/student")
public class ScheduleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

 	 
	@Autowired
	private UserService userService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private SessionService sessionService;
    @Autowired
    private MessageSource messageSource;
 
    @RequestMapping(value={"/schedule"}, method = RequestMethod.GET)
 	public ModelAndView schedule(ModelMap model, HttpSession httpSession){
 		String rtn = "/student/scheduleTest";
		ModelAndView modelAndView = new ModelAndView();

		try{
			StudentScheduleTest form = new StudentScheduleTest();
			EmhcUser user = getPrincipal();
			LOGGER.info("$$$$ SP status: " + user.getUserid());
			
			Session session = sessionService.findById(1);
			List<Session> sessions = sessionService.findByProgram(user.getProgram());
			form.setSession(session);
			form.setSessions(sessions);
			modelAndView.addObject("studentScheduleTest",form);
			modelAndView.setViewName(rtn);
			return modelAndView;

		}
		catch (Exception e){
			e.printStackTrace();
		}
		return modelAndView;
	}

 	@RequestMapping(value={"/schedule/{sesid}"}, method = RequestMethod.GET)
    public ModelAndView schedule(ModelMap model, @PathVariable("sesid") int sesid, HttpSession httpSession){
 		String rtn = "/student/scheduleTest";
		ModelAndView modelAndView = new ModelAndView();

		try{
			StudentScheduleTest form = new StudentScheduleTest();
			EmhcUser user = getPrincipal();
			LOGGER.info("$$$$ SP status: " + user.getUserid());
			
			Session session = sessionService.findById(sesid);
			List<Session> sessions = sessionService.findByProgram(user.getProgram());
			
			if(sesid != 0) {
				List<Schedule> schedules = scheduleService.getBySession(session);
				form.setSchedules(schedules);
			}
			
			form.setSession(session);
			form.setSessions(sessions);
			modelAndView.addObject("studentScheduleTest",form);
//			model.addAttribute("studentScheduleTest", form);
			modelAndView.setViewName(rtn);
			return modelAndView;

		}
		catch (Exception e){
			e.printStackTrace();
		}
		return modelAndView;
	}

	
	@RequestMapping(value="/schedule", method=RequestMethod.POST)
	public String schedule(@Valid @ModelAttribute("studentScheduleTest") StudentScheduleTest form, BindingResult bindingResult, Model model) {
		
		String rtn = "/student/scheduleTest";
		
		
		Message message = new Message();
		
		LOGGER.debug("Processing scheduleTest form={}, bindingResult={}", form, bindingResult);
		
		try {
			

			//Form validation
			if (bindingResult.hasErrors()) {
	            // failed validation
				model.addAttribute("studentScheduleTest", form);

				
				List<ObjectError> errors = bindingResult.getAllErrors();
				String msg = messageSource.getMessage("StudentProfile.updateProfile.validation", new Object[] {}, LocaleContextHolder.getLocale()) + "<br />";
				for(ObjectError i: errors) {
					if(i instanceof FieldError) {
						FieldError fieldError = (FieldError) i;
						msg += messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()) + "<br />";
					}
				}
				message.setStatus(Message.ERROR);
				message.setMessage(msg);
				model.addAttribute("message", message);
				
	            return rtn;
	        }
			
			
			message.setStatus(Message.SUCCESS);
			message.setMessage(messageSource.getMessage("StudentProfile.updateProfile.success", new Object[] {}, LocaleContextHolder.getLocale()));
		} catch(Exception e) {
			LOGGER.debug("Error in /student/profile POST of StudentProfile.  Error: " + e.getMessage());
			message.setStatus(Message.ERROR);
			message.setMessage(messageSource.getMessage("StudentProfile.updateProfile.error", new Object[] {}, LocaleContextHolder.getLocale()));
		}
		
		model.addAttribute("message", message);
		
		return rtn;
	}
	
	
	   private EmhcUser getPrincipal(){
	    	EmhcUser user = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        
	        if (principal instanceof LoginStudent) {
	            user = ((LoginStudent)principal).getClient();
	        } else {
	            user = userService.findUserByEmail("");
	        }
	        return user;
	    }
		
	
}
