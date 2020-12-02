package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee add(Employee employee);

    Employee findById(int id);
    List<Employee> findAll();
    List<Employee> findAllPaged(int page, int pageSize);

    List<Employee> findByGender(String male);
}
