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
        Company actual = companyRepository.findById(1);
        //given
        assertNull(actual);
    }

//    @Test
//    public void should_return_paged_result_given_added_employees() {
//        //should
//        EmployeeRepository employeeRepository = new MemoryEmployeeRepository();
//        List<Employee> expected = Arrays.asList(
//                new Employee(1, "foo", 18, "Male", 100),
//                new Employee(2, "bar", 20, "Female", 120));
//        for (Employee employee : expected) {
//            employeeRepository.add(employee);
//        }
//        employeeRepository.add(new Employee(3, "bar", 20, "Female", 120));
//        employeeRepository.add(new Employee(4, "bar", 20, "Female", 120));
//        //when
//        List<Employee> actual = employeeRepository.findAllPaged(0, 2);
//        //given
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void should_return_males_result_when_find_by_gender_given_added_employees() {
//        //should
//        EmployeeRepository employeeRepository = new MemoryEmployeeRepository();
//        List<Employee> expected = Arrays.asList(
//                new Employee(1, "foo", 18, "Male", 100),
//                new Employee(2, "bar", 20, "Male", 120));
//        for (Employee employee : expected) {
//            employeeRepository.add(employee);
//        }
//        employeeRepository.add(new Employee(3, "bar", 20, "Female", 120));
//        employeeRepository.add(new Employee(4, "bar", 20, "Female", 120));
//        //when
//        List<Employee> actual = employeeRepository.findByGender("Male");
//        //given
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void should_update_employee_with_same_id_when_add_given_employees_and_modified_employee() {
//        //should
//        EmployeeRepository employeeRepository = new MemoryEmployeeRepository();
//
//        for (Employee employee : Arrays.asList(
//                new Employee(1, "foo", 18, "Male", 100),
//                new Employee(2, "bar", 20, "Male", 120))) {
//            employeeRepository.add(employee);
//        }
//        Employee expected = new Employee(1, "foooo", 22, "Male", 99999);
//        //when
//        employeeRepository.add(expected);
//        Employee actual = employeeRepository.findById(1);
//        //given
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void should_delete_employee_when_delete_given_employees_and_id() {
//        //should
//        EmployeeRepository employeeRepository = new MemoryEmployeeRepository();
//
//        for (Employee employee : Arrays.asList(
//                new Employee(1, "foo", 18, "Male", 100),
//                new Employee(2, "bar", 20, "Male", 120))) {
//            employeeRepository.add(employee);
//        }
//        //when
//        employeeRepository.deleteById(1);
//        Employee actual = employeeRepository.findById(1);
//        //given
//        assertNull(actual);
//    }

}
