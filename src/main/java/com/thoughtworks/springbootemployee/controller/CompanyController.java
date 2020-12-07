package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
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
    public List<EmployeeResponse> getCompanyEmployeesById(@PathVariable String id) {
        return getCompanyById(id).getEmployees();
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<CompanyResponse> getAllCompanyPaged(@RequestParam int page, @RequestParam int pageSize) {
        return companyService.getAllPaged(page, pageSize).map(companyMapper::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse addCompany(@RequestBody CompanyRequest company) {
        return companyMapper.toResponse(companyService.create(companyMapper.toEntity(company)));
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(@PathVariable String id, @RequestBody CompanyRequest company) {
        return companyMapper.toResponse(companyService.update(id, companyMapper.toEntity(company)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        companyService.deleteById(id);
    }

}
