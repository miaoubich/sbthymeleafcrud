package com.miaoubich.HibernateTutorial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miaoubich.HibernateTutorial.model.Employee;
import com.miaoubich.HibernateTutorial.repository.EmployeeRepository;

@Service
public class EmployeeServices {

	@Autowired 
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteById(Long id) {
		employeeRepository.deleteById(id);
	}
	
}
