package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Resources not found")  // 409
    @ExceptionHandler(NoSuchElementException.class)
    public void notFound() {
    }

    @ResponseStatus(
            value = HttpStatus.UNPROCESSABLE_ENTITY,
            reason = "Invalid input format")  // 409
    @ExceptionHandler(ConversionFailedException.class)
    public void unprocessed() {
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getAllEmployees(@PathVariable String id) {
        return employeeService.getById(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Employee> getAllEmployeesPaged(@RequestParam int page, @RequestParam int pageSize) {
        return employeeService.getAllPaged(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getAllEmployeesByGender(@RequestParam String gender) {
        return employeeService.getAllByGender(gender);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee) {
        return employeeService.update(id, updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        employeeService.delete(id);
    }

}
