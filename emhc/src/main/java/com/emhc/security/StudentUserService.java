package com.emhc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.emhc.model.EmhcUser;
import com.emhc.service.UserService;

@Component
public class StudentUserService implements UserDetailsService {

	@Autowired
	UserService userService;

	private final Logger LOGGER = LoggerFactory.getLogger(StudentUserService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LOGGER.debug("Student Auth Begins. Locating User in DB {}", username);

		EmhcUser emhcuser = userService.getByUsername(username);

		if (emhcuser == null) {
			LOGGER.debug("Client is not found!!!!!!!!!!!!!!");
			throw new UsernameNotFoundException(String.format("User with stdnum=%s was not found", username));
		}
		// .orElseThrow(() -> new UsernameNotFoundException(String.format("User
		// with stdnum=%s was not found", stdnum))); //1.8
		return new LoginStudent(emhcuser);
	}
}
