package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entities.Company;
import com.thoughtworks.springbootemployee.entities.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    @BeforeEach
    void tearDown() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_all_companies_when_get_all_given_companies() throws Exception {

        //given
        Employee employee = new Employee("bar", 20, "Female", 120);
        employeeRepository.save(employee);
        Company company = new Company(null, "a", Collections.singletonList(employee));
        companyRepository.save(company);
        //when
        //then
        mockMvc.perform(get("/companies")).
                andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].companyName").value("a"))
                .andExpect(jsonPath("$[0].employees[0].id").isString())
                .andExpect(jsonPath("$[0].employees[0].name").value("bar"))
                .andExpect(jsonPath("$[0].employees[0].age").value(20))
                .andExpect(jsonPath("$[0].employees[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].employees[0].salary").value(120));

    }

    @Test
    public void should_return_companies_when_get_specified_company() throws Exception {

        //given
        Employee employee = new Employee("bar", 20, "Female", 120);
        employeeRepository.save(employee);
        Company company = new Company(null, "a", Collections.singletonList(employee));
        String companyId = companyRepository.save(company).getId();
        //when
        //then
        mockMvc.perform(get("/companies/" + companyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("a"))
                .andExpect(jsonPath("$.employeesNumber").value(1))
                .andExpect(jsonPath("$.employees[0].id").isString())
                .andExpect(jsonPath("$.employees[0].name").value("bar"))
                .andExpect(jsonPath("$.employees[0].age").value(20))
                .andExpect(jsonPath("$.employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.employees[0].salary").value(120));
    }

    @Test
    public void should_return_404_when_get_specified_company_with_invalid_id() throws Exception {

        //given
        //when
        //then
        mockMvc.perform(get("/companies/5fc88b568a093725de815b42")).
                andExpect(status().isNotFound());
    }

    @Test
    public void should_return_companies_when_get_all_companies_paged() throws Exception {

        //given
        Employee employee = new Employee("bar", 20, "Female", 120);
        employeeRepository.save(employee);
        List<Company> companyList = Arrays.asList(
                new Company(null, "a", Collections.singletonList(employee)),
                new Company(null, "b", Collections.singletonList(employee)),
                new Company(null, "c", Collections.singletonList(employee))
        );
        companyList.forEach(company -> companyRepository.save(company));
        //when
        //then
        mockMvc.perform(get("/companies/?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").isString())
                .andExpect(jsonPath("$.content[0].companyName").value("a"))
                .andExpect(jsonPath("$.content[0].employeesNumber").value(1))
                .andExpect(jsonPath("$.content[0].employees[0].id").isString())
                .andExpect(jsonPath("$.content[0].employees[0].name").value("bar"))
                .andExpect(jsonPath("$.content[0].employees[0].age").value(20))
                .andExpect(jsonPath("$.content[0].employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.content[0].employees[0].salary").value(120))
                .andExpect(jsonPath("$.content[1].id").isString())
                .andExpect(jsonPath("$.content[1].companyName").value("b"))
                .andExpect(jsonPath("$.content[1].employeesNumber").value(1))
                .andExpect(jsonPath("$.content[1].employees[0].id").isString())
                .andExpect(jsonPath("$.content[1].employees[0].name").value("bar"))
                .andExpect(jsonPath("$.content[1].employees[0].age").value(20))
                .andExpect(jsonPath("$.content[1].employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.content[1].employees[0].salary").value(120))
                .andExpect(jsonPath("$.content", hasSize(2)));
    }


    @Test
    public void should_create_and_return_company_when_create_given_company() throws Exception {
        //given
        Employee employee = new Employee("bar", 20, "Female", 120);
        employee = employeeRepository.save(employee);
        String companyAsJson = String.format(
                "{\n" +
                        "    \"companyName\": \"a\",\n" +
                        "    \"employees\": [\n" +
                        "         \"%s\"" +
                        "    ]\n" +
                        "}",
                employee.getId());
        //when
        //then
        mockMvc
                .perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(companyAsJson)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("a"))
                .andExpect(jsonPath("$.employeesNumber").value(1))
                .andExpect(jsonPath("$.employees[0].id").isString())
                .andExpect(jsonPath("$.employees[0].name").value("bar"))
                .andExpect(jsonPath("$.employees[0].age").value(20))
                .andExpect(jsonPath("$.employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.employees[0].salary").value(120));

    }

    //
    @Test
    public void should_return_updated_company_when_update_company_given_an_company() throws Exception {
        //given
        Company company = companyRepository.save(new Company(null, "a", new ArrayList<>()));
        Employee employee = new Employee("bar", 20, "Female", 120);
        employee = employeeRepository.save(employee);
        String companyAsJson = String.format(
                "{\n" +
                        "    \"companyName\": \"a\",\n" +
                        "    \"employees\": [\n" +
                        "     \"%s\"" +
                        "    ]\n" +
                        "}",
                employee.getId());
        //when

        //then
        mockMvc.perform(put("/companies/" + company.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("a"))
                .andExpect(jsonPath("$.employeesNumber").value(1))
                .andExpect(jsonPath("$.employees[0].id").isString())
                .andExpect(jsonPath("$.employees[0].name").value("bar"))
                .andExpect(jsonPath("$.employees[0].age").value(20))
                .andExpect(jsonPath("$.employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.employees[0].salary").value(120));
    }

    @Test
    public void should_return_404_when_update_company_with_invalid_id() throws Exception {

        //given
        //when
        //then
        mockMvc.perform(put("/companies/5fc88b568a093725de815b42").contentType(MediaType.APPLICATION_JSON).content("{}")).
                andExpect(status().isNotFound());
    }

    @Test
    public void should_delete_company_when_delete_company_given_an_company() throws Exception {
        //given
        Company company = companyRepository.save(new Company(null, "a", new ArrayList<>()));
        //when

        //then
        mockMvc.perform(delete("/companies/" + company.getId()))
                .andExpect(status().isOk());
        assertFalse(companyRepository.findById(company.getId()).isPresent());
    }

    @Test
    public void should_return_404_when_delete_company_with_invalid_id() throws Exception {

        //given
        //when
        //then
        mockMvc.perform(delete("/companies/5fc88b568a093725de815b42")).
                andExpect(status().isNotFound());
    }
}
