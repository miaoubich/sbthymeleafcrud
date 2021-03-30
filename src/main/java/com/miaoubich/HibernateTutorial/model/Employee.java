package com.miaoubich.HibernateTutorial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employees")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message="Mandatory field!")
	private String firstname;
	@NotBlank(message="Mandatory field!")
	private String lastname;
	@NotEmpty(message="Mandatory field!")
	@Email
	private String email;
	
	@ManyToOne
	@JoinColumn(name="designationid", nullable = true, insertable=false, updatable=false)
	private Designation designation;
	
	private Integer designationid;
	
}
