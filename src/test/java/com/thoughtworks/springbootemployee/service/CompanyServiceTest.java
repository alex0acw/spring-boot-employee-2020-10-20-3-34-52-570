package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Test
    public void should_return_all_companies_when_get_all_given_companies() {
        //given
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);
        Employee employee = new Employee("bar", 20, "Female", 120);
        List<Company> expected = Arrays.asList(
                new Company(null, "a", Collections.singletonList(employee)),
                new Company(null, "b", Collections.singletonList(employee)),
                new Company(null, "c", Collections.singletonList(employee))
        );
        when(companyRepository.findAll()).thenReturn(expected);

        //when
        List<Company> actualEmployees = companyService.getAll();

        //then
        assertEquals(expected, actualEmployees);
    }

    @Test
    public void should_pass_employee_data_when_create_employee_give_nothing_in_database() {
        //given
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyService employeeService = new CompanyService(companyRepository);
        Employee employee = new Employee("bar", 20, "Female", 120);
        Company expected = new Company("1", "a", Collections.singletonList(employee));
        when(companyRepository.save(expected)).thenReturn(expected);
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(expected));
        final ArgumentCaptor<Company> employeeArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        //when
        employeeService.create(expected);

        //then
        Mockito.verify(companyRepository, times(1)).save(employeeArgumentCaptor.capture());
        assertEquals(expected, employeeArgumentCaptor.getValue());
    }

    @Test
    public void should_return_modified_employee_when_update_given_old_employee() {
        //given
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyService employeeService = new CompanyService(companyRepository);

        Employee employee = new Employee("bar", 20, "Female", 120);
        Company original = new Company("1", "b", Collections.singletonList(employee));
        Company expected = new Company("1", "a", Collections.singletonList(employee));
        when(companyRepository.findById("id")).thenReturn(java.util.Optional.of(original));
        when(companyRepository.save(expected)).thenReturn(expected);
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(expected));

        final ArgumentCaptor<Company> employeeArgumentCaptor = ArgumentCaptor.forClass(Company.class);

        //when
        Company actual = employeeService.update("id", expected);

        //then
        Mockito.verify(companyRepository, times(1)).findById("id");
        Mockito.verify(companyRepository, times(1)).save(employeeArgumentCaptor.capture());
        assertEquals(expected, employeeArgumentCaptor.getValue());
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_modified_employee_when_update_given_invalid_id() {
        //given
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyService employeeService = new CompanyService(companyRepository);

        when(companyRepository.findById("id")).thenReturn(java.util.Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> employeeService.update("id", new Company("1", "b", new ArrayList<>())));

    }

    @Test
    public void should_call_delete_when_delete_given_id() {
        //given
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);
        Employee employee = new Employee("bar", 20, "Female", 120);
        Company expected = new Company("id", "a", Collections.singletonList(employee));
        doNothing().when(companyRepository).delete(expected);
        when(companyRepository.findById("id")).thenReturn(java.util.Optional.of(expected));

        final ArgumentCaptor<Company> employeeArgumentCaptor = ArgumentCaptor.forClass(Company.class);

        //when
        companyService.deleteById("id");

        //then
        Mockito.verify(companyRepository, times(1)).delete(employeeArgumentCaptor.capture());
        assertEquals(expected, employeeArgumentCaptor.getValue());
    }
}
