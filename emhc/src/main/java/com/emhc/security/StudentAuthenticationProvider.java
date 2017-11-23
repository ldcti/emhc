package com.emhc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.emhc.model.EmhcUser;
import com.emhc.repository.UserRepository;


public class StudentAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired
    private UserRepository userRepository;
    
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
    	
    	EmhcUser emhcuser = userRepository.findByUsername(auth.getName());
    	
        if ((emhcuser == null)) {
            throw new BadCredentialsException("Invalid username or password");
        }
        
        final Authentication result = super.authenticate(auth);
        
        //System.out.println("result.getCredentials(): " + result.getCredentials().toString());
        return new UsernamePasswordAuthenticationToken(new LoginStudent(emhcuser), result.getCredentials(), result.getAuthorities());

    }
    
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
