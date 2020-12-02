package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryCompanyRepository implements CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    @Override
    public Company add(Company company) {
        Company oldCompany = this.findById(company.getId());
        if (oldCompany != null) {
            oldCompany.setCompanyName(company.getCompanyName());
            oldCompany.setEmployees(company.getEmployees());
        } else
            companies.add(company);
        return company;
    }

    @Override
    public List<Company> findAll() {
        return companies;
    }

    @Override
    public Company findById(int id) {
        return companies.stream().filter(company -> company.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), companies -> {
                    if (companies.size() > 1)
                        throw new IllegalStateException("Duplicate company id");
                    if (companies.size() == 1)
                        return companies.get(0);
                    return null;
                }));
    }

    @Override
    public List<Company> findAllPaged(int page, int pageSize) {
        return companies.stream().skip((long) page * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    @Override
    public void deleteById(int i) {

    }
}
