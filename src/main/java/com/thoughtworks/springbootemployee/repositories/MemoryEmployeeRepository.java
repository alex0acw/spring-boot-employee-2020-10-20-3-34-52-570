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

    public List<Employee> findAll() {
        return this.employees;
    }

    @Override
    public List<Employee> findAllPaged(int page, int pageSize) {
        return null;
    }

    public Employee add(Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee findById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), employees -> {
                    if (employees.size() > 1)
                        throw new IllegalStateException("Duplicate employee id");
                    if (employees.size() == 1)
                        return employees.get(0);
                    return null;
                }));
    }
}
