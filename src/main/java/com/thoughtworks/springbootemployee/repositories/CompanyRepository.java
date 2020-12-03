package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Company;

import java.util.List;

public interface CompanyRepository {
    String NO_SUCH_COMPANY_MESSAGE = "No such company.";

    Company add(Company company);

    List<Company> findAll();

    Company findById(String i);

    List<Company> findAllPaged(int page, int pageSize);

    void deleteById(String i);
}
