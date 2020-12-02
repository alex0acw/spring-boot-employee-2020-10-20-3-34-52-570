package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Company;

import java.util.List;

public interface CompanyRepository {
    Company add(Company company);

    List<Company> findAll();

    Company findById(int i);

    List<Company> findAllPaged(int page, int pageSize);
}
