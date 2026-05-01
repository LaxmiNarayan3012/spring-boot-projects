package com.springdata.employeemanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.springdata.employeemanager.entity.Employee;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeeControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	

	@Test
    void shouldCreateEmployee() {
        Employee emp = new Employee();
        emp.setName("Test User");
        emp.setDepartment("QA");
        emp.setSalary(40000.0);

        ResponseEntity<Employee> response =
            restTemplate.postForEntity("/employees", emp, Employee.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getId());
    }
}
