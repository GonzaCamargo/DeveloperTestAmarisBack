package com.DeveloperTestAmarisBack.service.serviceImpl;

import com.DeveloperTestAmarisBack.controller.EmployeeController;
import com.DeveloperTestAmarisBack.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(EmployeeController.class)
class EmployeeServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private EmployeeController employeeController;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllEmployees() throws Exception {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setEmployee_name("Gonzalo");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setEmployee_name("Mishell");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{'id':1,'employee_name':'Gonzalo'},{'id':2,'employee_name':'Mishell'}]"));

    }

    @Test
    void getOneEmployee() throws Exception {

        Long employeeId = 1L;

        Employee employee1 = new Employee();
        employee1.setId(employeeId);
        employee1.setEmployee_name("Gonzalo");

        when(employeeService.getOneEmployee(employee1.getId())).thenReturn(employee1);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
                // Verificamos que el estado de la respuesta sea 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Verificamos que la respuesta contiene el empleado esperado en formato JSON
                .andExpect((ResultMatcher) jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.employee_name").value("Gonzalo"));

    }
}