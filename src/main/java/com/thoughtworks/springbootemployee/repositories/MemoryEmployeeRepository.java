package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class MemoryEmployeeRepository implements EmployeeRepository {
    public static final String NO_SUCH_EMPLOYEE_MESSAGE = "No such employee exist for updating.";
    public static final String DUPLICATE_EMPLOYEE_ID_MESSAGE = "Duplicate employee id";
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> findAll() {
        return this.employees;
    }

    @Override
    public List<Employee> findAllPaged(int page, int pageSize) {
        return employees.stream().skip((long) page * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findByGender(String gender) {
        return this.employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(int i) {
        Employee employee = this.findById(i);
        this.employees.remove(employee);
    }

    public Employee add(Employee employee) {
        Employee oldEmployee = this.findById(employee.getId());
        if (oldEmployee != null) {
            throw new IllegalArgumentException(DUPLICATE_EMPLOYEE_ID_MESSAGE);
        } else
            employees.add(employee);
        return employee;
    }

    public Employee update(Employee employee) {
        Employee oldEmployee = this.findById(employee.getId());
        if (oldEmployee == null) throw new NoSuchElementException(NO_SUCH_EMPLOYEE_MESSAGE);
        oldEmployee.setAge(employee.getAge());
        oldEmployee.setGender(employee.getGender());
        oldEmployee.setName(employee.getName());
        oldEmployee.setSalary(employee.getSalary());
        return oldEmployee;
    }

    @Override
    public Employee findById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), employees -> {
                    if (employees.size() > 1)
                        throw new IllegalStateException("Duplicate employee id in DB");
                    if (employees.size() == 1)
                        return employees.get(0);
                    return null;
                }));
    }
}
