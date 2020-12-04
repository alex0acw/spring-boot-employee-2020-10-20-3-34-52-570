package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    EmployeeController(EmployeeMapper employeeMapper, EmployeeService employeeService) {
        this.employeeMapper = employeeMapper;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAll().stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable String id) {
        return employeeMapper.toResponse(employeeService.getById(id));
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<EmployeeResponse> getAllEmployeesPaged(@RequestParam int page, @RequestParam int pageSize) {
        return employeeService.getAllPaged(page, pageSize).map(employeeMapper::toResponse);
    }

    @GetMapping(params = {"gender"})
    public List<EmployeeResponse> getAllEmployeesByGender(@RequestParam String gender) {
        return employeeService.getAllByGender(gender).stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employee) {
        return employeeMapper.toResponse(employeeService.create(employeeMapper.toEntity(employee)));
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable String id, @RequestBody EmployeeRequest updatedEmployee) {
        return employeeMapper.toResponse(employeeService.update(id, employeeMapper.toEntity(updatedEmployee)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        employeeService.delete(id);
    }

}
