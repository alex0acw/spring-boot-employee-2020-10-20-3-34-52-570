package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@ResponseBody
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable String id) {
        return companyService.getById(id);

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
