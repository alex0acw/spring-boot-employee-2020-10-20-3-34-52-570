package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();

    Employee add(Employee employee);

    Employee findAllById(int id);
}
