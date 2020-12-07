package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entities.Employee;
import com.thoughtworks.springbootemployee.exceptions.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee update(String employeeID, Employee updatedEmployee) {
        Employee employee = getById(employeeID);
        updatedEmployee.setId(employee.getId());
        return employeeRepository.save(updatedEmployee);
    }

    public Page<Employee> getAllPaged(int page, int pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);
        return employeeRepository.findAll(paging);
    }

    public List<Employee> getAllByGender(String gender) {
        return employeeRepository.getAllByGender(gender);
    }

    public Employee getById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent())
            return employee.get();
        else throw new EmployeeNotFoundException();
    }

    public void delete(String id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
    }
}
