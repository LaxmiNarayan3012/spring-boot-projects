package com.springdata.employeemanager.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import com.springdata.employeemanager.entity.Employee;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ActiveProfiles;

import com.springdata.employeemanager.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
    private EmployeeRepository repo;

    @InjectMocks
    private EmployeeService service;
    
    @Test
    public void saveTest() {
    	Employee e = new Employee();
    	e.setName("Unit Test");
    	when(repo.save(any(Employee.class))).thenReturn(e);
    	Employee e1 = service.save(e);
    	assertEquals("Unit Test",e1.getName());
    }
    
}
