package com.miaoubich.HibernateTutorial.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miaoubich.HibernateTutorial.model.Employee;
import com.miaoubich.HibernateTutorial.services.EmployeeServices;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeServices employeeServices;

	private Employee employee;

	@GetMapping("/employees")
	public String homePage(Model model) {

		model.addAttribute("Employees", employeeServices.getAllEmployees());
		return "index";
	}

	@PostMapping("/emmployee/addNew")
	public String addNewEmployee(Employee employee) {
		employeeServices.addEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/new/employee/form")
	public String addEmployee(Model model) {
		employee = new Employee();
		model.addAttribute("employee", employee);
		return "newEmployee";
	}

	@GetMapping("/blank/form")
	public String blankForm(Model model) {

		model.addAttribute("Employees", employeeServices.getAllEmployees());
		return "index";
	}

	@RequestMapping("/employee/findById")
	@ResponseBody
	public Optional<Employee> getEmployeeById(Long id) {
		return employeeServices.getEmployeeById(id);
	}

	@RequestMapping(value = "/employee/update", method = { RequestMethod.PUT, RequestMethod.GET })
	public String updateEmployee(Employee employee) {
		employeeServices.addEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/displayUpdateForm/{id}")
	public String displateUpdateEmployeeForm(@PathVariable(value = "id") long id, Model model) {
		//get employee by id
		Optional<Employee> employee = employeeServices.getEmployeeById(id);
		
		//set employee as a model attribute to populate the form
		model.addAttribute("employee", employee);
		return "updateEmployee";
	}

	@RequestMapping(value = "/employee/delete", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String deleteEmployee(Long id) {
		employeeServices.deleteById(id);
		return "redirect:/employees";
	}
	
	@GetMapping("/employee/delete/{id}")
	public String deleteEmployee2(@PathVariable(value = "id") long id) {
		employeeServices.deleteById(id);
		return "redirect:/employees";
	}
}
