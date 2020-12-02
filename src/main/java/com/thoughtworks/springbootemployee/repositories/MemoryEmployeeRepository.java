package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryEmployeeRepository implements EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getAll() {
        return this.employees;
    }

    public Employee add(Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee getById(int id) {
        return null;
    }
}
