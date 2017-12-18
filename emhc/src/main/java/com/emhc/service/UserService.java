package com.emhc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emhc.dto.UserDTO;
import com.emhc.model.EmhcUser;
import com.emhc.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public EmhcUser findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public EmhcUser getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public EmhcUser saveUser(EmhcUser user) {
		if (user.getUserid() == 0) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		return userRepository.save(user);
	}

	/*
	 * @Override public User findUserByUsernameAndRole(User user, Role role) {
	 * List<Role> roles = new ArrayList<Role>(); roles.add(role); return
	 * userRepository.findUserByUsernameAndRole(user, roles); }
	 */
}
