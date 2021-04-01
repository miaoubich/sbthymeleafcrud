package com.miaoubich.HibernateTutorial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miaoubich.HibernateTutorial.model.Designation;
import com.miaoubich.HibernateTutorial.model.Employee;
import com.miaoubich.HibernateTutorial.services.DesignationService;
import com.miaoubich.HibernateTutorial.services.EmployeeServices;

@Controller
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeServices employeeServices;
	@Autowired
	private DesignationService designationService;

//	@GetMapping("/employees")
//	public String homePage(Model model, Integer jobId) {
//		model.addAttribute("Employees", employeeServices.getAllEmployees());
//		return "index";
//	}

	@GetMapping("/employees")
	public String home(Model model, Integer jobId) {
		List<Designation> listDesignation = designationService.getAllDesignations();

		model.addAttribute("jobs", listDesignation);
		model.addAttribute("Employees", employeeServices.getAllEmployees());

		return "employee";
	}

	@PostMapping("/employee/addNew")
	public String addNewEmployee(@Valid @ModelAttribute(value = "employee") Employee employee, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Validation errors while submitting add new employee form!");
			return "employee";
		}
		if (employeeServices.isEmailUsed(employee.getEmail()) > 0) {
			logger.info("employee.getEmail(): " + employee.getEmail());
			redirectAttributes.addFlashAttribute("emailExistsErrorMsg", "ERROR! This email is already used.");
			redirectAttributes.addFlashAttribute("alectClass", "alert");
		} else {
			employeeServices.addEmployee(employee);
			redirectAttributes.addFlashAttribute("addEmpMessage", "INFO! Employee added successfully.");
			redirectAttributes.addFlashAttribute("alertClass", "alert");
		}
		return "redirect:/employees";
	}

	@GetMapping("/employee/addNew")
	public String showAddNewEmployeeForm(Model model, Integer jobId) {
		List<Designation> listDesignation = designationService.getAllDesignations();

		model.addAttribute("jobs", listDesignation);
		model.addAttribute("Employees", employeeServices.getAllEmployees());

		return "employee";
	}

	@RequestMapping("/employee/findById")
	@ResponseBody
	public Optional<Employee> getEmployeeById(Integer id, Model model) {
		model.addAttribute("jobs", designationService.getAllDesignations());
		return employeeServices.getEmployeeById(id);
	}

	@RequestMapping(value = "/employee/update", method = { RequestMethod.PUT, RequestMethod.GET })
	public String updateEmployee(Employee employee, RedirectAttributes redirectAttributes) {
		employeeServices.addEmployee(employee);
		redirectAttributes.addFlashAttribute("updateEmpMessage", "INFO! Employee updated successfully.");
		redirectAttributes.addFlashAttribute("alertClass", "alert");
		
		return "redirect:/employees";
	}

	@RequestMapping(value = "/employee/delete/", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String deleteEmployee(Integer id, RedirectAttributes redirectAttributes) {
		
		employeeServices.deleteById(id);
		logger.info("Deleted User!");
		
		redirectAttributes.addFlashAttribute("deleteEmpMessage", "INFO! Employee Deleted successfully.");
		redirectAttributes.addFlashAttribute("alertClass", "alert");
		
		return "redirect:/employees";
	}
	
}
