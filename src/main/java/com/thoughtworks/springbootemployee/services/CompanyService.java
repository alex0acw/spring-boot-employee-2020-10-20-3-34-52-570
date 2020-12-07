package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entities.Company;
import com.thoughtworks.springbootemployee.exceptions.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private static final String NO_SUCH_COMPANY_MESSAGE = "No such company.";


    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void deleteById(String id) {
        Company company = getById(id);
        companyRepository.delete(company);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Page<Company> getAllPaged(int page, int pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);
        return companyRepository.findAll(paging);
    }

    public Company create(Company company) {
        return companyRepository.findById(companyRepository.save(company).getId()).get();
    }

    public Company update(String id, Company updatedCompany) {
        Company company = getById(id);
        updatedCompany.setId(company.getId());
        return companyRepository.findById(companyRepository.save(updatedCompany).getId()).get();

    }

    public Company getById(String id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isPresent()) {
            return company.get();
        } else
            throw new CompanyNotFoundException();
    }
}
