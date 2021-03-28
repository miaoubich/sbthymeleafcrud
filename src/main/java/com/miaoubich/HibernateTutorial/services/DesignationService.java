package com.miaoubich.HibernateTutorial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.miaoubich.HibernateTutorial.model.Designation;
import com.miaoubich.HibernateTutorial.repository.DesignationRepository;

@Service
public class DesignationService {

	@Autowired
	private DesignationRepository designationRepository;
	
	public List<Designation> getAllDesignations() {
		return designationRepository.findAll();
	}

	public void saveDesignation(Designation designation) {
		designationRepository.save(designation);	
	}

	public void deleteDesignation(Integer id) {
		designationRepository.deleteById(id);
	}

	public Optional<Designation> getSingleJobById(Integer id) {
		return designationRepository.findById(id);
	}
	
	public Integer isJobExists(String jobName) {
		return designationRepository.jobExist(jobName);
	}
}
