package com.chainsys.parceltracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chainsys.parceltracker.model.Employee;
import com.chainsys.parceltracker.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employeeform")
	public String showEmployeeForm(Model model) {
	Employee theemp = new Employee();
		model.addAttribute("newemp", theemp);
		return "employee-form";
}
		

	@PostMapping("/newemp")
	public String addNewCus(@ModelAttribute("newemp") Employee theemp) {
      
     employeeService.addEmployeeDetails(theemp); 
	return null;
	}
	
	@GetMapping("/employeelogin")
	public String adminaccessform(Model model) {
		Employee theemp = new Employee();
		model.addAttribute("admin", theemp);
		return "employee-login-form";
	}
	@PostMapping("/check")
	public String checkingaccess(@ModelAttribute("admin") Employee employee) {
	Employee admin  = employeeService.getEmployeeRoleAndEmployeeNameAndEmployeePassword(employee.getEmployeeRole(), employee.getEmployeeName(),employee.getEmployeePassword());
		if(admin!= null) {
		        return "redirect:/courier/courierlist";
		        }
		else return "redirect:/employee/employeelogin";
			
		}
	}
