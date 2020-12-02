package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoryEmployeeRepository implements EmployeeRepository {
    public List<Employee> getAll() {
        return null;
    }

    public Employee add(Employee capture) {
        return null;
    }
}
