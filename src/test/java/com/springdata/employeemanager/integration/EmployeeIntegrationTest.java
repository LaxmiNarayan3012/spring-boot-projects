package com.springdata.employeemanager.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.springdata.employeemanager.entity.Employee;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class EmployeeIntegrationTest {



    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8")
                    .withDatabaseName("testdb")
                    .withUsername("root")
                    .withPassword("root");

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldCreateAndFetchEmployee() {

        // create employee
        Employee emp = new Employee();
        emp.setName("Container Test");
        emp.setDepartment("QA");
        emp.setSalary(50000.0);

        ResponseEntity<Employee> postResponse =
                restTemplate.postForEntity("/employees", emp, Employee.class);

        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody().getId());

        // fetch employees
        ResponseEntity<Employee[]> getResponse =
                restTemplate.getForEntity("/employees", Employee[].class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertTrue(getResponse.getBody().length > 0);
    }

	
}
