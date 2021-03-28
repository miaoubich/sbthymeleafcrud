package com.miaoubich.HibernateTutorial;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
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
public class CreateDesignationTest {

	@Autowired
	private DesignationRepository designationRepository;
	
	private Designation designation;
	
	@Test
	public void addNewJobTitle() {
		designation = new Designation();
		designation.setName("job test");
		assertNotNull(designationRepository.save(designation));
	}
}
