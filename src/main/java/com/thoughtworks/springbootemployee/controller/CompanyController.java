package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.entities.Company;
import com.thoughtworks.springbootemployee.entities.Employee;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
@ResponseBody
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }
    
    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAll().stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable String id) {
        return companyMapper.toResponse(companyService.getById(id));

    }

    @GetMapping("/{id}/employees")
    public List<Employee> getCompanyEmployeesById(@PathVariable String id) {
        return companyService.getById(id).getEmployees();
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Company> getAllCompanyPaged(@RequestParam int page, @RequestParam int pageSize) {
        return companyService.getAllPaged(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        return companyService.create(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable String id, @RequestBody Company company) {
        return companyService.update(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        companyService.deleteById(id);
    }

}
