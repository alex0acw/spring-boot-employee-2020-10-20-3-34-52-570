package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.repositories.MongoEmployeeRepository;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;
//
//    @Mock
//    private MongoEmployeeRepository mongoEmployeeRepository;

    @Test
    public void should_return_all_employees_when_get_all_given_employees() {
        //given
        MongoEmployeeRepository employeeRepository = mock(MongoEmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        final List<Employee> expected = Arrays.asList(
                new Employee("bar", 20, "Female", 120, ""),
                new Employee("bar", 20, "Female", 120, ""));
        when(employeeRepository.findAll()).thenReturn(expected);

        //when
        List<Employee> actualEmployees = employeeService.getAll();

        //then
        assertEquals(expected, actualEmployees);
    }

    @Test
    public void should_pass_employee_data_when_create_employee_give_nothing_in_database() {
        //given
        MongoEmployeeRepository employeeRepository = mock(MongoEmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        Employee expected = new Employee("test", 1, "", 1, "");
        when(employeeRepository.save(expected)).thenReturn(expected);
        final ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        //when
        employeeService.create(expected);

        //then
        Mockito.verify(employeeRepository, times(1)).save(employeeArgumentCaptor.capture());
        assertEquals(expected, employeeArgumentCaptor.getValue());
    }

    @Test
    public void should_return_modified_employee_when_update_given_old_employee() {
        //given
        MongoEmployeeRepository employeeRepository = mock(MongoEmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        Employee original = new Employee("test", 1, "", 1, "id");
        Employee expected = new Employee("test", 1, "fsdafsadf", 58364589, "id");
        when(employeeRepository.findById("id")).thenReturn(java.util.Optional.of(original));
        when(employeeRepository.save(expected)).thenReturn(expected);

        final ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        //when
        Employee actual = employeeService.update("id", expected);

        //then
        Mockito.verify(employeeRepository, times(1)).findById("id");
        Mockito.verify(employeeRepository, times(1)).save(employeeArgumentCaptor.capture());
        assertEquals(expected, employeeArgumentCaptor.getValue());
        assertEquals(expected, actual);
    }
}
