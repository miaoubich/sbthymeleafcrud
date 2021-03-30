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
public class DesignationController {

	private static final Logger logger = LoggerFactory.getLogger(DesignationController.class);

	@Autowired
	private DesignationService designationService;
	@Autowired
	private EmployeeServices employeeService;

	@GetMapping("/Designation")
	public String printAllDesignation(Model model) {
		model.addAttribute("designations", designationService.getAllDesignations());
		return "designation";
	}

	@PostMapping("/designation/add")
	public String addDesignation(Designation designation, RedirectAttributes redirectAttributes) {

		logger.info("0. designation.getName(): " + designation.getName());

		if (designationService.isJobExists(designation.getName()) > 0) {
			redirectAttributes.addFlashAttribute("addMessageWarning", "WARNING! This Job title already exists.");
			redirectAttributes.addFlashAttribute("alertClass", "alert");
		}
		else{
			designationService.saveDesignation(designation);
			redirectAttributes.addFlashAttribute("addMessage", "INFO! Job title added successfully.");
			redirectAttributes.addFlashAttribute("alertClass", "alert");
		}
		return "redirect:/Designation";
	}

	@RequestMapping("/job/delete/{id}")
	public String deleteDesignation(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		String employeeIdExists = "false";
		List<Employee> employeeList = employeeService.getAllEmployees();

		for (Employee employee : employeeList) {
			if (employee.getDesignationid().equals(id)) {
				employeeIdExists = "true";
				redirectAttributes.addFlashAttribute("errorMessage",
						"ERROR! You can't delete this job title, one or more Employee are assign to it!");
				redirectAttributes.addFlashAttribute("alertClass", "alert");
				break;
			}
		}
		if (employeeIdExists.equals("false")) {
			designationService.deleteDesignation(id);
			redirectAttributes.addFlashAttribute("infoMessage", "INFO! Job title was successfully deleted");
			redirectAttributes.addFlashAttribute("alertClass", "alert");
		}

		return "redirect:/Designation";
	}

	@RequestMapping("/job/findById")
	@ResponseBody
	public Optional<Designation> findJobById(Integer id) {
		return designationService.getSingleJobById(id);
	}

	@RequestMapping(value = "/job/update", method = { RequestMethod.PUT, RequestMethod.GET })
	public String updateJob(Designation designation, RedirectAttributes redirectAttributes) {
		designationService.saveDesignation(designation);
		redirectAttributes.addFlashAttribute("updateJobMessage", "INFO! Job title updated successfully");
		redirectAttributes.addFlashAttribute("alertClass", "alert");

		return "redirect:/Designation";
	}

}
