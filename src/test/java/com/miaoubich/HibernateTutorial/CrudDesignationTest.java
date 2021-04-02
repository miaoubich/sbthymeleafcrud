package com.miaoubich.HibernateTutorial;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.miaoubich.HibernateTutorial.model.Designation;
import com.miaoubich.HibernateTutorial.repository.DesignationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class CrudDesignationTest {

	@Autowired
	private DesignationRepository designationRepository;
	
	private Designation designation;
	private static String jobName= "job test";
	private static String jobUpdate;
	
	@Test
	@Order(1)
	public void createNewJobTitle() {
		designation = new Designation();
		designation.setName(jobName);
		assertNotNull(designationRepository.save(designation));
	}
	
	@Test
	@Order(2)
	public void readJobTitle() {
		assertThat(designationRepository.jobExist(jobName)).isEqualTo(1);
		assertEquals(designationRepository.jobExist(jobName), 1);
		assertSame(designationRepository.jobExist(jobName), 1);
		assertNotSame(designationRepository.jobExist(jobName), "ONE");
	}
	
	@Test
	@Order(3)
	public void updateJobTitle() {
		Integer id = designationRepository.findByName(jobName);
		designation = new Designation();
		jobUpdate = jobName.concat("Update");
		designationRepository.updateJobTitle(jobUpdate, id);
		
		assertSame(designationRepository.jobExist(jobUpdate), 1);
	}
	
	@Test
	@Order(4)
	public void deleteJobTitle() {
		designationRepository.deleteJobByName("job testUpdate");
		assertThat(designationRepository.jobExist(jobName)).isEqualTo(0);
	}
	
//	@Test
	public void deleteJobById() {
		designationRepository.deleteById(41);
		assertThat(designationRepository.jobExist(jobName)).isEqualTo(0);
	}
		
//		assertTrue(variable1<variable2);					
//      assertArrayEquals(airethematicArrary1, airethematicArrary2);	
}
