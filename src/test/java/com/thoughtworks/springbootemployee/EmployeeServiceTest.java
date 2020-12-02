package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeRepository employeeRepository;

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

    @Test
    public void should_pass_employee_data_when_create_employee_give_nothing_in_database() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee(1, "bee", 27, "make", 20);

        //when
        employeeService.create(employee);
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).create(employeeArgumentCaptor.capture());

        //then
        Employee actual = employeeArgumentCaptor.getValue();
        assertEquals(employee, actual);
    }
}
