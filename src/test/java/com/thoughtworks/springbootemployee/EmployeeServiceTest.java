package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {


    @Test
    public void should_return_all_employees_when_get_all_given_employees() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        final List<Employee> expected = Arrays.asList(
                new Employee(1, "foo", 18, "Male", 100),
                new Employee(1, "bar", 20, "Female", 120));

        when(employeeRepository.getAll()).thenReturn(expected);

        //when
        List<Employee> actualEmployees = employeeService.getAll();

        //then
        assertEquals(expected, actualEmployees);
    }
}
