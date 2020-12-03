package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repositories.MongoEmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {
    private static final String NO_SUCH_EMPLOYEE_MESSAGE = "No such employee.";
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
        else throw new NoSuchElementException(NO_SUCH_EMPLOYEE_MESSAGE);
    }

    public void delete(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent())
            employeeRepository.delete(employee.get());
        else throw new NoSuchElementException(NO_SUCH_EMPLOYEE_MESSAGE);
    }
}
