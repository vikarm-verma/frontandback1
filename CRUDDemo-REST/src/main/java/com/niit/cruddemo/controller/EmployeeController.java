package com.niit.cruddemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.cruddemo.service.*;

import com.niit.cruddemo.*;
import com.niit.cruddemo.service.EmployeeService;



//create restcontroller cause not to facing issue when at time of sending data from json 
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	//if we want to send data with condition
	/*public ResponseEntity<?> getAllEmployee()
	{
		if(list !=null)
		{
			retunr new ResponseEntity("No employee found",HttpStatus.NoContent);
		}
	}*/
	@GetMapping
	public ResponseEntity<List<Employee>> listAllEmployees() {
		
		
		//cause of this line we will get a json object.
		return new ResponseEntity<List<Employee>>(employeeService.findAllEmployees(), HttpStatus.CREATED);
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("empId") int empId) {
		
		if(employeeService.findEmployeesById(empId)!=null) {
			return new ResponseEntity<Employee>(employeeService.findEmployeesById(empId), HttpStatus.OK);
		}
		else 
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND); 
	}
	
	@DeleteMapping("/{empId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("empId") int empId) {
		
		if(employeeService.findEmployeesById(empId)!=null) {
			employeeService.deleteEmployee(empId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else 
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); 
	}
	
	@PostMapping	
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
		if(employeeService.findEmployeesByName(employee.getName())!=null) {
			
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		else {
			employeeService.addEmployee(employee);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	@PutMapping
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
		
		if(employeeService.findEmployeesById(employee.getEmpId())!=null) {
			employeeService.updateEmployee(employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	
	

}
