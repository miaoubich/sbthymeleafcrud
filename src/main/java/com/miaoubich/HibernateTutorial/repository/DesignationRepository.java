package com.miaoubich.HibernateTutorial.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miaoubich.HibernateTutorial.model.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Integer> {

	//JPQL
	
	@Query("SELECT COUNT(*) FROM Jobs WHERE name=?1")
	Integer jobExist(String jobName);

//	Whenever you are trying to modify a record in db, you have to mark it @Transactional as well as @Modifying, which instruct Spring that it can modify existing records.
//	The repository method must be void, int or Integer
	@Transactional
	@Modifying
	@Query("DELETE FROM Jobs WHERE name=?1")
	void deleteJobByName(String jobName);
	
	@Query("SELECT id FROM Jobs WHERE name=?1")
	Integer findByName(String jobeName);
	
	@Transactional
	@Modifying
	@Query("UPDATE Jobs SET name=?1 WHERE id=?2")
	void updateJobTitle(String jobeNameUpdate, Integer id);
	
}
