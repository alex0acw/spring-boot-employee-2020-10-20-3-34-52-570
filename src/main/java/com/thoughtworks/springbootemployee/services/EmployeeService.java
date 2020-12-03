package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.repositories.MemoryEmployeeRepository;
import com.thoughtworks.springbootemployee.repositories.MongoEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {
    private final MongoEmployeeRepository employeeRepository;

    public EmployeeService(MongoEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee update(String employeeID, Employee updatedEmployee) {
        Optional<Employee> employee = employeeRepository.findById(employeeID);
        if (employee.isPresent())
            updatedEmployee.setId(employee.get().getId());
        else
            throw new NoSuchElementException();
        return employeeRepository.save(updatedEmployee);
    }

//    public List<Employee> getAllPaged(int page, int pageSize) {
//        return employeeRepository.findAllPaged(page, pageSize);
//    }

    public List<Employee> getAllByGender(String gender) {
        return employeeRepository.getAllByGender(gender);
    }

    public Employee getById(String id) {
        return employeeRepository.findById(id).get();
    }

//    public void delete(String id) {
//
//        employeeRepository.delete(employeeRepository.findById(id));
//    }
}
