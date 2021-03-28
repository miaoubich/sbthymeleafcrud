package com.miaoubich.HibernateTutorial.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Jobs")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Designation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_id")
	private Integer id;
	
//	@NotNull(message = "Mandatory Field!")
	@Column(name = "job_name", length = 25, nullable = false, unique = true)
	private String name;
	
	@OneToMany(mappedBy = "designation") //, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Employee> employees;
	
}
