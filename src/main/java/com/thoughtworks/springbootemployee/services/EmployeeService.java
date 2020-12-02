package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final  EmployeeRepository employeeRepository ;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getAll() {
        return null;
    }
}
