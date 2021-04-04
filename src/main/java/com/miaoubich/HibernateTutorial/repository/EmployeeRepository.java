package com.miaoubich.HibernateTutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miaoubich.HibernateTutorial.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT COUNT(*) FROM employees WHERE email=?1")
	int isEmailExist(String email);

	public Employee findByFirstname(String fname);

	@Query("SELECT id FROM employees WHERE firstname=?1")
	Integer getEmployeeIdByFirstname(String firstname);

	Employee findEmployeeById(Integer id);

}
