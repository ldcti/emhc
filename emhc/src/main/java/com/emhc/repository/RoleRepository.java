package com.emhc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emhc.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String name);

	Role findByRoleid(int id);
}
