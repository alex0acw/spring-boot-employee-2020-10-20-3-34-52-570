package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entities.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    @BeforeEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_all_employees_when_get_all_given_employees() throws Exception {

        //given
        Employee employee = new Employee("bar", 20, "Female", 120, "");
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(get("/employees")).
                andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("bar"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].salary").value(120));
    }

    @Test
    public void should_return_employees_when_get_specified_employee() throws Exception {

        //given
        Employee employee = new Employee("bar", 20, "Female", 120);
        String employeeId = employeeRepository.save(employee).getId();
        //when
        //then
        mockMvc.perform(get("/employees/" + employeeId)).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("bar"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.gender").value("Female"))
                .andExpect(jsonPath("$.salary").value(120));
    }

    @Test
    public void should_return_404_when_get_specified_employee_with_invalid_id() throws Exception {

        //given
        //when
        //then
        mockMvc.perform(get("/employees/5fc88b568a093725de815b42")).
                andExpect(status().isNotFound());
    }

    @Test
    public void should_return_employees_when_get_all_employees_paged() throws Exception {

        //given
        List<Employee> employeeList = Arrays.asList(
                new Employee("foo", 20, "Male", 120),
                new Employee("barr", 20, "Female", 120),
                new Employee("a", 20, "a", 120),
                new Employee("b", 20, "b", 120),
                new Employee("c", 20, "c", 120)
        );
        employeeList.forEach(employee -> employeeRepository.save(employee));
        //when
        //then
        mockMvc.perform(get("/employees/?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").isString())
                .andExpect(jsonPath("$.content[0].name").value("foo"))
                .andExpect(jsonPath("$.content[0].age").value(20))
                .andExpect(jsonPath("$.content[0].gender").value("Male"))
                .andExpect(jsonPath("$.content[0].salary").value(120))
                .andExpect(jsonPath("$.content[1].id").isString())
                .andExpect(jsonPath("$.content[1].name").value("barr"))
                .andExpect(jsonPath("$.content[1].age").value(20))
                .andExpect(jsonPath("$.content[1].gender").value("Female"))
                .andExpect(jsonPath("$.content[1].salary").value(120))
                .andExpect(jsonPath("$.content", hasSize(2)));
    }


    @Test
    public void should_return_one_gender_employees_when_get_all_employees_with_gender() throws Exception {

        //given
        List<Employee> employeeList = Arrays.asList(
                new Employee("foo", 20, "Male", 120),
                new Employee("bar", 20, "Female", 120),
                new Employee("b", 20, "Male", 120),
                new Employee("a", 20, "Female", 120),
                new Employee("c", 20, "Male", 120)
        );
        employeeList.forEach(employee -> employeeRepository.save(employee));
        //when
        //then
        mockMvc.perform(get("/employees/?gender=Female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isString())
                .andExpect(jsonPath("$.[0].name").value("bar"))
                .andExpect(jsonPath("$.[0].age").value(20))
                .andExpect(jsonPath("$.[0].gender").value("Female"))
                .andExpect(jsonPath("$.[0].salary").value(120))
                .andExpect(jsonPath("$.[1].id").isString())
                .andExpect(jsonPath("$.[1].name").value("a"))
                .andExpect(jsonPath("$.[1].age").value(20))
                .andExpect(jsonPath("$.[1].gender").value("Female"))
                .andExpect(jsonPath("$.[1].salary").value(120));
    }

    @Test
    public void should_return_employee_when_create_given_employee() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "        \"name\": \"foobar\",\n" +
                "        \"age\": 3,\n" +
                "        \"gender\": null,\n" +
                "        \"salary\": 4\n" +
                "    }";
        //when
        //then
        mockMvc
                .perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeAsJson)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("foobar"))
                .andExpect(jsonPath("$.age").value(3))
                .andExpect(jsonPath("$.salary").value(4));

    }

    @Test
    public void should_return_updated_employee_when_update_employee_given_an_employee() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee("bar", 20, "Female", 120));
        String employeeToString =
                " {\n" +
                        "\"name\": \"bar\",\n" +
                        "\"age\": 30,\n" +
                        "\"gender\": \"Female\",\n" +
                        "\"salary\": 120\n" +
                        "}";
        //when

        //then
        mockMvc.perform(put("/employees/" + employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeToString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value("bar"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.salary").value(120))
                .andExpect(jsonPath("$.gender").value("Female"));
    }

    @Test
    public void should_return_404_when_update_employee_with_invalid_id() throws Exception {

        //given
        //when
        //then
        mockMvc.perform(put("/employees/5fc88b568a093725de815b42").contentType(MediaType.APPLICATION_JSON).content("{}")).
                andExpect(status().isNotFound());
    }

    @Test
    public void should_delete_employee_when_delete_employee_given_an_employee() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee("bar", 20, "Female", 120));
        //when

        //then
        mockMvc.perform(delete("/employees/" + employee.getId()))
                .andExpect(status().isOk());
        assertFalse(employeeRepository.findById(employee.getId()).isPresent());
    }

    @Test
    public void should_return_404_when_delete_employee_with_invalid_id() throws Exception {

        //given
        //when
        //then
        mockMvc.perform(delete("/employees/5fc88b568a093725de815b42")).
                andExpect(status().isNotFound());
    }
}
