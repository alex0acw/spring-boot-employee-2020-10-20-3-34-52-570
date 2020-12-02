package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();

    Employee add(Employee employee);
}
