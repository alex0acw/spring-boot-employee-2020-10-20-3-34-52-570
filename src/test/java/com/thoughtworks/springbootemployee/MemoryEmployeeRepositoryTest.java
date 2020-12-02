package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.repositories.MemoryEmployeeRepository;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryEmployeeRepositoryTest {
    @Test
    public void should_return_all_employees_when_get_all_given_added_employees() {
        //should
        EmployeeRepository employeeRepository = new MemoryEmployeeRepository();
        List<Employee> expected = Arrays.asList(
                new Employee(1, "foo", 18, "Male", 100),
                new Employee(1, "bar", 20, "Female", 120));
        for (Employee employee : expected) {
            employeeRepository.add(employee);
        }
        //when
        List<Employee> actual = employeeRepository.getAll();
        //given
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_employee_when_get_by_id_given_added_employees() {
        //should
        EmployeeRepository employeeRepository = new MemoryEmployeeRepository();
        Employee expected = new Employee(1, "foo", 18, "Male", 100);
        employeeRepository.add(expected);
        employeeRepository.add(new Employee(2, "bar", 20, "Female", 120));
        //when
        Employee actual = employeeRepository.getById(1);
        //given
        assertEquals(expected, actual);
    }
}
