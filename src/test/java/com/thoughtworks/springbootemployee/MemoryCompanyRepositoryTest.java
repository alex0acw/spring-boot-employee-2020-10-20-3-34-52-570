package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.repositories.MemoryCompanyRepository;
import com.thoughtworks.springbootemployee.repositories.MemoryEmployeeRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.thoughtworks.springbootemployee.repositories.CompanyRepository.NO_SUCH_COMPANY_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

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
        companyRepository.add(new Company(2, "bar", new ArrayList<>()));
        //when
        Company actual = companyRepository.findById(1);
        //given
        assertEquals(expected, actual);
    }


    @Test
    public void should_return_null_when_get_by_id_given_added_company_and_invalid_id() {
        //should
        CompanyRepository companyRepository = new MemoryCompanyRepository();
        companyRepository.add(new Company(1, "foo", new ArrayList<>()));
        //when
        Company actual = companyRepository.findById(2);
        //given
        assertNull(actual);
    }

    @Test
    public void should_return_paged_result_given_added_companies() {
        //should
        CompanyRepository companyRepository = new MemoryCompanyRepository();
        List<Company> expected = Arrays.asList(
                new Company(1, "foo", new ArrayList<>()),
                new Company(2, "bar", new ArrayList<>()));
        for (Company company : expected) {
            companyRepository.add(company);
        }
        companyRepository.add(new Company(3, "foo", new ArrayList<>()));
        companyRepository.add(new Company(4, "bar", new ArrayList<>()));
        //when
        List<Company> actual = companyRepository.findAllPaged(0, 2);
        //given
        assertEquals(expected, actual);
    }


    @Test
    public void should_update_company_with_same_id_when_add_given_companies_and_modified_company() {
        //should
        CompanyRepository companyRepository = new MemoryCompanyRepository();

        for (Company company : Arrays.asList(
                new Company(1, "foo", new ArrayList<>()),
                new Company(2, "bar", new ArrayList<>()))) {
            companyRepository.add(company);
        }
        Company expected = new Company(1, "foo",
                Arrays.asList(
                        new Employee(1, "foo", 18, "Male", 100),
                        new Employee(2, "bar", 20, "Male", 120))
        );
        //when
        companyRepository.add(expected);
        Company actual = companyRepository.findById(1);
        //given
        assertEquals(expected, actual);
    }

    @Test
    public void should_delete_company_when_delete_given_company_and_id() {
        //should
        CompanyRepository companyRepository = new MemoryCompanyRepository();

        for (Company company : Arrays.asList(
                new Company(1, "foo", new ArrayList<>()),
                new Company(2, "bar", new ArrayList<>()))) {
            companyRepository.add(company);
        }

        //when
        companyRepository.deleteById(1);
        Company actual = companyRepository.findById(1);
        //given
        assertNull(actual);
    }

    @Test
    public void should_throw_exception_when_delete_given_wrong_id() {
        //should
        CompanyRepository companyRepository = new MemoryCompanyRepository();

        for (Company company : Arrays.asList(
                new Company(1, "foo", new ArrayList<>()),
                new Company(2, "bar", new ArrayList<>()))) {
            companyRepository.add(company);
        }

        //when
        //given
        NoSuchElementException actual = assertThrows(NoSuchElementException.class, () -> companyRepository.deleteById(3));
        assertEquals(NO_SUCH_COMPANY_MESSAGE, actual.getMessage());
    }

}
