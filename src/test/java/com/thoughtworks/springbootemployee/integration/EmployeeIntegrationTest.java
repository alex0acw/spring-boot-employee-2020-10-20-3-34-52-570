package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("foobar"))
                .andExpect(jsonPath("$.age").value(3))
                .andExpect(jsonPath("$.salary").value(4));

    }
}
