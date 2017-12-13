package com.emhc.controller.student;

import java.time.LocalDate;
import java.util.Date;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.emhc.dto.StudentScheduleTest;
import com.emhc.error.Message;
import com.emhc.model.EmhcUser;
import com.emhc.model.Registration;
import com.emhc.model.Schedule;
import com.emhc.model.Session;
import com.emhc.security.LoginStudent;
import com.emhc.service.RegistrationService;
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
	private RegistrationService registrationService;
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
			List<Schedule> schedules = scheduleService.getBySession(session);
			form.setSchedules(schedules);

			Registration registration = registrationService.findByEmhcUser(user);
			if(registration!=null){
				System.out.println("registration ID = "+ registration.getRegistrationid());
				form.setRegistrationid(registration.getRegistrationid());
				form.setScheduleid(registration.getSchedule().getScheduleid());
			}
			else{
				form.setRegistrationid(0);
				form.setScheduleid(0);
				
			}
			
			
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

	
	@RequestMapping(value="/schedule", method=RequestMethod.POST)
//	@RequestMapping(value="/schedule/{scheduleRadio}", method=RequestMethod.POST)
	public ModelAndView createschedule(@Valid StudentScheduleTest form, BindingResult bindingResult, HttpSession httpSession) {
//		public ModelAndView createschedule(@Valid StudentScheduleTest form, @RequestParam("scheduleRadio") int scheduleid, BindingResult bindingResult) {
		
		String rtn = "";
		
		int scheduleid = form.getScheduleid() ;
		Schedule schedule = scheduleService.findByScheduleid(scheduleid);
		int sessionid = schedule.getSession().getSessionid();
		rtn = "redirect:/student/schedule/" + sessionid;
		Message message = new Message();
		
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			

			//Form validation
			if (bindingResult.hasErrors()) {
	            // failed validation
				LOGGER.debug("Profile form validation failed!!!!!!!!");
				List<ObjectError> errors = bindingResult.getAllErrors();
				String msg = messageSource.getMessage("StudentProfile.updatePassword.validation", new Object[] {}, LocaleContextHolder.getLocale()) + "<br />";
				for(ObjectError i: errors) {
					if(i instanceof FieldError) {
						FieldError fieldError = (FieldError) i;
						msg += messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()) + "<br />";
					}
				}
				message.setStatus(Message.ERROR);
				message.setMessage(msg);
				
	            modelAndView.setViewName(rtn);
	            return modelAndView;
	            
	        }
			
			
			EmhcUser currentUser = getPrincipal();
				
			if((registrationService.findByEmhcUser(currentUser)==null)||(form.getRegistrationid()!=0)) {
				
				Registration register = new Registration();
				register.setRegistrationid(form.getRegistrationid());
				register.setUser(currentUser);
				scheduleid = form.getScheduleid();
				register.setSchedule(scheduleService.findByScheduleid(scheduleid));
				LocalDate localDate = LocalDate.now();
				Date date = java.sql.Date.valueOf(localDate);
				register.setRegistdate(date);
				
				register = registrationService.saveRegistration(register);
				form.setRegistrationid(register.getRegistrationid());
	
				//emailService.sendMail(emailDTO);
				message.setStatus(Message.SUCCESS);
				message.setMessage(messageSource.getMessage("StudentSchedule.scheduleTest.success", new Object[] {}, LocaleContextHolder.getLocale()));
			}else
			{
				
			}
		} catch(Exception e) {
			LOGGER.debug("Error in /student/profile POST of StudentProfile.  Error: " + e.getMessage());
			message.setStatus(Message.ERROR);
			message.setMessage(messageSource.getMessage("StudentSchedule.scheduleTest.error", new Object[] {}, LocaleContextHolder.getLocale()));
		}
		modelAndView.addObject("studentScheduleTest",form);
		modelAndView.setViewName(rtn);
		
		return modelAndView;
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
