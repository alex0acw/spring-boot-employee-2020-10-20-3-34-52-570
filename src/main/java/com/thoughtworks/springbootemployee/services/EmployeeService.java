package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.repositories.MemoryEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(MemoryEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.add(employee);
    }

    public Employee update(int employeeID, Employee updatedEmployee) {
        updatedEmployee.setId(employeeID);
        return employeeRepository.update(updatedEmployee);
    }

    public List<Employee> getAllPaged(int page, int pageSize) {
        return employeeRepository.findAllPaged(page, pageSize);
    }

    public List<Employee> getAllByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
