package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.repositories.MemoryCompanyRepository;
import com.thoughtworks.springbootemployee.repositories.MemoryEmployeeRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MemoryCompanyRepositoryTest {
    @Test
    public void should_return_all_employees_when_get_all_given_added_employees() {
        //should
        CompanyRepository companyRepository = new MemoryCompanyRepository();
        List<Company> expected = Arrays.asList(
                new Company(1, "foo", new ArrayList<>()),
                new Company(2, "bar", new ArrayList<>()));
        for (Company company : expected) {
            companyRepository.add(company);
        }
        //when
        List<Company> actual = companyRepository.findAll();
        //given
        assertEquals(expected, actual);
    }


    @Test
    public void should_return_employee_when_get_by_id_given_added_employees() {
        //should
        CompanyRepository companyRepository = new MemoryCompanyRepository();
        Company expected = new Company(1, "foo", new ArrayList<>());
        companyRepository.add(expected);
        companyRepository.add(new Company(1, "bar", new ArrayList<>()));
        //when
        Company actual = companyRepository.findById(1);
        //given
        assertEquals(expected, actual);
    }

}
