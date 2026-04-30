package com.springdata.employeemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springdata.employeemanager.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByNameContaining(String name);
	List<Employee> findBySalaryGreaterThan(Double double1);
	
	@Query("select e1 from Employee e1 where e1.department= :dept")
	List<Employee> findByDepartment(@Param("dept") String dept);
}
