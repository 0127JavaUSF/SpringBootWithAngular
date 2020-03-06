package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1") 
//localhost:portnumber/projectname/api/v1/endpoint
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@GetMapping("/employees")
	//if we receive a request with the verb GET and endpoint of "/employees"
	public List<Employee> getAllEmployees(){
		return employeeRepo.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> 
	getEmployeeById(@PathVariable(value="id") Long employeeId){
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		Employee emp = employee.get();
		return ResponseEntity.ok().body(emp);
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee){
		return employeeRepo.save(employee);
	}
	
	
}
