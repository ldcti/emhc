package com.emhc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emhc.model.EmhcUser;
import com.emhc.model.Registration;
import com.emhc.model.Schedule;
import com.emhc.repository.RegistrationRepository;



@Service("registrationService")
public class RegistrationService{

	@Autowired
	private RegistrationRepository registrationRepository;
	public Registration findByEmhcUser(EmhcUser emhcuser){
		
		return registrationRepository.findByUser(emhcuser);
	}
	public Registration saveRegistration(Registration register) {
		return registrationRepository.save(register);
	}

}
