package com.springdata.employeemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springdata.employeemanager.entity.Employee;
import com.springdata.employeemanager.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService  employeeService;
	
	@PostMapping
	public Employee add(@RequestBody Employee e) {
		return employeeService.save(e);
	}
	
	
	//Get All Employee
	@GetMapping
	public List<Employee> getAll() {
		return employeeService.getAll();
	}
	
	// Pagnation
	@GetMapping("/page")
	public Page<Employee> getPage(@RequestParam int page, @RequestParam int size) {
		return employeeService.getPaged(page, size);
	}
	
	// SerachBy Name
	@GetMapping("/search")
	public List<Employee> search(@RequestParam String name)
	{
		return employeeService.searchByName(name);
	}
	
	@GetMapping("/salary")
	public List<Employee> salary(@RequestParam Double salary) {
		return employeeService.highSalary(salary);
	}
	
	@PutMapping("/{id}")
	public Employee update(@PathVariable long id ,@RequestBody Employee e) {
		return employeeService.update(id, e);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Employee employee = employeeService.getAll().stream().filter(e -> e.getId() == id).findFirst().get();
		employeeService.delete(employee);
	}
	
}
