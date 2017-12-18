package com.emhc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emhc.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
	public Organization findByEmail(String email);

	public Organization findByOrganizationid(long organizationid);

	public List<Organization> findAll();
}
