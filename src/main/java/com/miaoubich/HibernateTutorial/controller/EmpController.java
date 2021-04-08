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
import org.springframework.web.bind.annotation.PathVariable;
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
public class EmpController {
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	@Autowired
	private EmployeeServices employeeServices;
	@Autowired
	private DesignationService designationService;

	private Employee employee;

	@GetMapping("/new/employee/form")
	public String addEmployee(Model model) {
		employee = new Employee();
		List<Designation> listDesignation = designationService.getAllDesignations();

		model.addAttribute("employee", employee);
		model.addAttribute("designations", listDesignation);

		return "newEmployee";
	}

	@PostMapping("/addNew")
	public String addNewEmployee(@Valid Employee employee, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("designations", listDesignation);

		if (result.hasErrors()) {
			logger.error("Validation errors while submitting add new employee form!");
			return "newEmployee";
		}
		employeeServices.addEmployee(employee);

		redirectAttributes.addFlashAttribute("addEmpMessage", "INFO! Employee added successfully.");
		redirectAttributes.addFlashAttribute("alertClass", "alert");

		return "redirect:/employees";
	}

	@GetMapping("/displayUpdateForm/{id}")
	public String displateUpdateEmployeeForm(@PathVariable(value = "id") Integer id, Model model) {
		// get employee by id
		Optional<Employee> employee = employeeServices.getEmployeeById(id);
		// get list of designations
		List<Designation> jobsList = designationService.getAllDesignations();
		model.addAttribute("jobs", jobsList);

		// set employee as a model attribute to populate the form
		model.addAttribute("employee", employee);

		return "updateEmployee";
	}

	@GetMapping("/employee/detail/{id}")
	public String showEmployeeDetail(@PathVariable(value = "id") Integer id, Model model) {
		
		Optional<Employee> employee = employeeServices.getEmployeeById(id);
		List<Designation> jobsList = designationService.getAllDesignations();
		
		model.addAttribute("employee", employee);
		model.addAttribute("jobs", jobsList);
		
		
		return "employeeDetails";
	}
	
	@RequestMapping("/employee/delete2/{id}")
	public String deleteEmployee2(@PathVariable(value = "id") Integer id, RedirectAttributes redirectAttributes) {
		employeeServices.deleteById(id);

		redirectAttributes.addFlashAttribute("deleteEmpMessage", "INFO! Employee Deleted successfully.");
		redirectAttributes.addFlashAttribute("alertClass", "alert");

		return "redirect:/employees";
	}

	@GetMapping("/blank/form")
	public String blankForm(Model model) {

		model.addAttribute("Employees", employeeServices.getAllEmployees());
		return "index";
	}
}
