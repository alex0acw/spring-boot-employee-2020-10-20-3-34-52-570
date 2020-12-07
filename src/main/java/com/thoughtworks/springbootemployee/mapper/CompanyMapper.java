package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.entities.Company;
import com.thoughtworks.springbootemployee.entities.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        if (companyRequest.getEmployees() != null)
            company.setEmployees(companyRequest.getEmployees().stream().map(id -> {
                        Employee employee = new Employee();
                        employee.setId(id);
                        return employee;
                    })
                            .collect(Collectors.toList())
            );
        return company;
    }

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        return companyResponse;
    }
}
