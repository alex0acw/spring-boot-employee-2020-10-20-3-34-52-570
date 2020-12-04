package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
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
    private final CompanyRepository companyRepository;
    private static final String NO_SUCH_COMPANY_MESSAGE = "No such company.";


    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void deleteById(String id) {
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
        Optional<Company> createdCompany = companyRepository.findById(companyRepository.save(company).getId());
        if (createdCompany.isPresent())
            return createdCompany.get();
        throw new IllegalStateException("Company created but cannot be fetched");
    }

    public Company update(String id, Company updatedCompany) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            updatedCompany.setId(company.get().getId());
            Optional<Company> savedCompany = companyRepository.findById(companyRepository.save(updatedCompany).getId());
            if(savedCompany.isPresent())
                return savedCompany.get();
            else
                throw  new IllegalStateException("update company failed to fetch updated object.");

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
