package com.miaoubich.HibernateTutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miaoubich.HibernateTutorial.model.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Integer> {

	//JPQL
	
	@Query("SELECT COUNT(*) FROM Jobs WHERE name=?1")
	Integer jobExist(String jobName);

}
