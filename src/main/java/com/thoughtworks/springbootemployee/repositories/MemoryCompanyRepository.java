package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Company;

import java.util.ArrayList;
import java.util.List;

public class MemoryCompanyRepository implements CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    @Override
    public Company add(Company company) {
        companies.add(company);
        return company;
    }

    @Override
    public List<Company> findAll() {
        return companies;
    }

    @Override
    public Company findById(int i) {
        return null;
    }
}
