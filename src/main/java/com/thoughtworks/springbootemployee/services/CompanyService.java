package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.repositories.MongoCompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CompanyService {
    private final MongoCompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private static final String NO_SUCH_COMPANY_MESSAGE = "No such company.";


    public CompanyService(MongoCompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public void deleteById(String id) {
        companyRepository.deleteById(id);
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent())
            companyRepository.delete(company.get());
        else throw new NoSuchElementException(NO_SUCH_COMPANY_MESSAGE);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Page<Company> getAllPaged(int page, int pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);
        return companyRepository.findAll(paging);
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company update(String id, Company updqatedCompany) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            updqatedCompany.setId(company.get().getId());
            return companyRepository.save(updqatedCompany);
        } else
            throw new NoSuchElementException();
    }

    public Company getById(String id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        } else
            throw new NoSuchElementException();
    }
}
