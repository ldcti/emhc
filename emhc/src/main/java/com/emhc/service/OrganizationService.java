package com.emhc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emhc.model.Organization;
import com.emhc.repository.OrganizationRepository;

@Service("organizationService")
public class OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	public Organization getById(long organizationid) {
		return organizationRepository.findByOrganizationid(organizationid);
	}

	public Organization findOrganizationByEmail(String email) {
		return organizationRepository.findByEmail(email);
	}

	public List<Organization> findAll() {
		System.out.println("------run in Service-------");
		return organizationRepository.findAll();
	}

	public Organization saveOrganization(Organization organization) {
		return organizationRepository.save(organization);
	}

}
