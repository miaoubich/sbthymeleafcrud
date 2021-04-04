package com.miaoubich.HibernateTutorial;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.miaoubich.HibernateTutorial.model.Employee;
import com.miaoubich.HibernateTutorial.repository.EmployeeRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class CrudEmployeeTest {

	@Autowired 
	private EmployeeRepository employeeRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CrudEmployeeTest.class);
	private static final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
	
	private static String fname = "testName";
	private static String fnameUpdate = "testName Update";
	
	private static Employee employee = new Employee();
	
	@Test
	@Order(1)
	public void createEmployee() {
		
		employee.setFirstname(fname);
		employee.setLastname("testLastname");
		employee.setEmail("testEmail@mail.org");
		employee.setDesignationid(42);
		
		assertNotNull(employeeRepository.save(employee));
	}
	
	@Test
	@Order(2)
	public void isEmployeeExists() {
		assertTrue(employeeRepository.existsById(26));
	}
	
	@Test
	@Order(3)
	public void findEmployeeByFirstname() {
		employee = employeeRepository.findByFirstname("DARIN");
		
		logger.info("employee.getFirstname(): " + employee.getFirstname());
		assertThat(employee.getFirstname()).isEqualTo("DARIN");
	}
	
	@Test
	@Order(4)
	public void readSingleEmployeeData() {
		employee = employeeRepository.findByFirstname(fname);

		logger.info("First name: " + employee.getFirstname());
		logger.info("Last name: " + employee.getLastname());
		logger.info("Email: " + employee.getEmail());
		logger.info("Job Title: " + employee.getDesignation().getName());
		
		assertThat(employee.getFirstname()).isEqualTo(fname);
		assertThat(employee.getLastname()).isEqualTo("testLastname");
		assertThat(employee.getEmail()).isEqualTo("testEmail@bouzar.org");
		assertThat(employee.getDesignation().getName()).isEqualTo("jobTest");
	}
	
	@Test
	@Order(5)
	public void updateEmployee() {
		Integer id = employeeRepository.getEmployeeIdByFirstname(fname);
		logger.info("ID: " + id);
		
		employee = employeeRepository.findEmployeeById(id);
		
		logger.info("Employee Firstname: " + employee.getFirstname());
		
		employee.setFirstname(fnameUpdate);
		employee.setLastname("testLastname Update");
		employee.setEmail("testEmailUpdate@mail.org");
		employee.setDesignationid(3);
		
		assertNotNull(employeeRepository.save(employee));
		
		logger.info("First name: " + employee.getFirstname());
		logger.info("Last name: " + employee.getLastname());
		logger.info("Email: " + employee.getEmail());
		logger.info("Job Title: " + employee.getDesignation().getName());
	}
	
	@Test
	@Order(6)
	public void chechIfEmployeeIsUpdate() {
		Integer id = employeeRepository.getEmployeeIdByFirstname(fnameUpdate);
		logger.info("After Update ID: " + id);
		
		employee = employeeRepository.findEmployeeById(id);
		
		assertThat(employee.getFirstname()).isEqualTo(fnameUpdate);
		assertThat(employee.getLastname()).isEqualTo("testLastname Update");
		assertThat(employee.getEmail()).isEqualTo("testEmailUpdate@mail.org");
		assertThat(employee.getDesignation().getName()).isEqualTo("Developer");
	}
	
	@Test
	@Order(7)
	public void deleteEmployee() throws InterruptedException {
		Integer id = employeeRepository.getEmployeeIdByFirstname(fnameUpdate);
		
		employeeRepository.deleteById(id);
		timeUnit.sleep(10000);
		
		assertFalse(employeeRepository.existsById(26));
		
	}
	
}
