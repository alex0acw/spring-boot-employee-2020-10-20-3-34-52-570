package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                andExpect(status().is(404));
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
                .andExpect(jsonPath("$.content[0].employees[0].id").isString())
                .andExpect(jsonPath("$.content[0].employees[0].name").value("bar"))
                .andExpect(jsonPath("$.content[0].employees[0].age").value(20))
                .andExpect(jsonPath("$.content[0].employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.content[0].employees[0].salary").value(120))
                .andExpect(jsonPath("$.content[1].id").isString())
                .andExpect(jsonPath("$.content[1].companyName").value("b"))
                .andExpect(jsonPath("$.content[1].employees[0].id").isString())
                .andExpect(jsonPath("$.content[1].employees[0].name").value("bar"))
                .andExpect(jsonPath("$.content[1].employees[0].age").value(20))
                .andExpect(jsonPath("$.content[1].employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.content[1].employees[0].salary").value(120))
                .andExpect(jsonPath("$.content", hasSize(2)));
    }
//
//
//    @Test
//    public void should_return_one_gender_companies_when_get_all_companies_with_gender() throws Exception {
//
//        //given
//        List<Company> companyList = Arrays.asList(
//                new Company("foo", 20, "Male", 120),
//                new Company("bar", 20, "Female", 120),
//                new Company("b", 20, "Male", 120),
//                new Company("a", 20, "Female", 120),
//                new Company("c", 20, "Male", 120)
//        );
//        companyList.forEach(company -> companyRepository.save(company));
//        //when
//        //then
//        mockMvc.perform(get("/companies/?gender=Female"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.[0].id").isString())
//                .andExpect(jsonPath("$.[0].name").value("bar"))
//                .andExpect(jsonPath("$.[0].age").value(20))
//                .andExpect(jsonPath("$.[0].gender").value("Female"))
//                .andExpect(jsonPath("$.[0].salary").value(120))
//                .andExpect(jsonPath("$.[1].id").isString())
//                .andExpect(jsonPath("$.[1].name").value("a"))
//                .andExpect(jsonPath("$.[1].age").value(20))
//                .andExpect(jsonPath("$.[1].gender").value("Female"))
//                .andExpect(jsonPath("$.[1].salary").value(120));
//    }
//
//    @Test
//    public void should_return_company_when_create_given_company() throws Exception {
//        //given
//        String companyAsJson = "{\n" +
//                "        \"name\": \"foobar\",\n" +
//                "        \"age\": 3,\n" +
//                "        \"gender\": null,\n" +
//                "        \"salary\": 4\n" +
//                "    }";
//        //when
//        //then
//        mockMvc
//                .perform(post("/companies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(companyAsJson)
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").isString())
//                .andExpect(jsonPath("$.name").value("foobar"))
//                .andExpect(jsonPath("$.age").value(3))
//                .andExpect(jsonPath("$.salary").value(4));
//
//    }
//
//    @Test
//    public void should_return_updated_company_when_update_company_given_an_company() throws Exception {
//        //given
//        Company company = companyRepository.save(new Company("bar", 20, "Female", 120));
//        String companyToString =
//                " {\n" +
//                        "\"name\": \"bar\",\n" +
//                        "\"age\": 30,\n" +
//                        "\"gender\": \"Female\",\n" +
//                        "\"salary\": 120\n" +
//                        "}";
//        //when
//
//        //then
//        mockMvc.perform(put("/companies/" + company.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(companyToString))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(company.getId()))
//                .andExpect(jsonPath("$.name").value("bar"))
//                .andExpect(jsonPath("$.age").value(30))
//                .andExpect(jsonPath("$.salary").value(120))
//                .andExpect(jsonPath("$.gender").value("Female"));
//    }
//
//    @Test
//    public void should_return_404_when_update_company_with_invalid_id() throws Exception {
//
//        //given
//        //when
//        //then
//        mockMvc.perform(put("/companies/5fc88b568a093725de815b42").contentType(MediaType.APPLICATION_JSON).content("{}")).
//                andExpect(status().is(404));
//    }
//
//    @Test
//    public void should_delete_company_when_delete_company_given_an_company() throws Exception {
//        //given
//        Company company = companyRepository.save(new Company("bar", 20, "Female", 120));
//        //when
//
//        //then
//        mockMvc.perform(delete("/companies/" + company.getId()))
//                .andExpect(status().isOk());
//        assertFalse(companyRepository.findById(company.getId()).isPresent());
//    }
//
//    @Test
//    public void should_return_404_when_delete_company_with_invalid_id() throws Exception {
//
//        //given
//        //when
//        //then
//        mockMvc.perform(delete("/companies/5fc88b568a093725de815b42")).
//                andExpect(status().is(404));
//    }
}
