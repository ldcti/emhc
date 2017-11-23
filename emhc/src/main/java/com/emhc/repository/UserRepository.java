package com.emhc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emhc.model.EmhcUser;

public interface UserRepository extends JpaRepository<EmhcUser, Integer> {
	 EmhcUser findByEmail(String email);
	 EmhcUser findByUsername(String username);
}
