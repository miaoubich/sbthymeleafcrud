package com.miaoubich.HibernateTutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miaoubich.HibernateTutorial.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
