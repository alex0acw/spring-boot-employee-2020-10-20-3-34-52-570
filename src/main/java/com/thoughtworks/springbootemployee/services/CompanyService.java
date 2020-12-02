package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void deleteById(int id) {
        companyRepository.deleteById(id);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public List<Company> getAllPaged(int page, int pageSize) {
        return companyRepository.findAllPaged(page, pageSize);
    }

    public Company create(Company company) {
        return companyRepository.add(company);
    }

    public Company update(int id, Company company) {
        company.setId(id);
        return companyRepository.add(company);
    }
}
