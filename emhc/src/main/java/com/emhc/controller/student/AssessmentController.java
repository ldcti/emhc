package com.emhc.controller.student;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import com.emhc.dto.AnswerDTO;
import com.emhc.error.Message;
import com.emhc.service.AnswerService;
import com.emhc.validator.AssessmentValidator;

@Controller
@RequestMapping("/student")
public class AssessmentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);
	
    @Autowired
    private MessageSource messageSource;

	@Autowired
	private AnswerService answerService;

	@Autowired
    private AssessmentValidator assessentValidator;
	
   @InitBinder("answerDTO")
   public void initAssessmentBinder(WebDataBinder binder) {
       binder.addValidators(assessentValidator);
   }
	
	
	
	@RequestMapping(value={"/assessment"}, method = RequestMethod.GET)
	public String  assessment(Model model){
		
		String rtn = "student/assessment";
		AnswerDTO form = new AnswerDTO();

		model.addAttribute("answerDTO", form);

		return rtn;
	}
	
	
	@RequestMapping(value = "/assessment", method = RequestMethod.POST)
	public String createNewAssessment(@Valid @ModelAttribute("answerDTO") AnswerDTO form, BindingResult bindingResult, Model model) {
		
		String rtn = "student/assessment";

		
		Message message = new Message();

		
		String answer1 = form.getAnswer1();
		
		AnswerDTO newform = new AnswerDTO();
		//newform.setAnswer1(answer1);
		model.addAttribute("answerDTO", newform);
		
		
		if (bindingResult.hasErrors()) {

			LOGGER.debug("Assessment form validation failed!!!!!!!!");
			List<ObjectError> errors = bindingResult.getAllErrors();
			String msg = messageSource.getMessage("Assessment.validation", new Object[] {}, LocaleContextHolder.getLocale()) + "<br />";
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
			
		} else {
			
			try{
				
				answerService.saveAnswer(form.getAnswer());
				System.out.println("------run to assessment saved answer-----");
				message.setStatus(Message.SUCCESS);
				message.setMessage(messageSource.getMessage("Assessment.success", new Object[] {}, LocaleContextHolder.getLocale()));
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
		model.addAttribute("message",message);
		return rtn;
	}

}
