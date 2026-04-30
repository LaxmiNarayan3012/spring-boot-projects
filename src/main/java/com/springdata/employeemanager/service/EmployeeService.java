package com.springdata.employeemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springdata.employeemanager.entity.Employee;
import com.springdata.employeemanager.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee save(Employee employee) {
		return 	employeeRepository.save(employee);
	}
	
	public List<Employee> getAll(){
		return employeeRepository.findAll();
	} 
	
	public Page<Employee> getPaged(int page,int size){
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.findAll(pageable);
	}
	
	public List<Employee> searchByName(String name){
		return employeeRepository.findByNameContaining(name);
	}
	
	public List<Employee> highSalary(Double salary){
		return employeeRepository.findBySalaryGreaterThan(salary);
	}
	
	public Employee update(Long id, Employee employee) {
	
		 Employee e= employeeRepository.findById(id).get();
		 e.setDepartment(employee.getDepartment());
		 e.setName(employee.getName());
		 e.setSalary(employee.getSalary());
		 return employeeRepository.save(e);
		 
	}
	
	
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}
}
