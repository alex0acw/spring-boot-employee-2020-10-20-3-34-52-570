package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getAllEmployeesPaged(@RequestParam int page, @RequestParam int pageSize) {
        return employeeService.getAllPaged(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getAllEmployeesByGender(@RequestParam String gender) {
        return employeeService.getAllByGender(gender);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{employeeID}")
    public Employee updateEmployee(@PathVariable int employeeID, @RequestBody Employee updatedEmployee) {
        return employeeService.update(employeeID, updatedEmployee);
    }

    @DeleteMapping("/{employeeID}")
    public void deleteById(@PathVariable int id) {
        employeeService.deleteById(id);
    }

}
