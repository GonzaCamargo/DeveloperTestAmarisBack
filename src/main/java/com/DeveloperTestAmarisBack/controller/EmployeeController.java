package com.DeveloperTestAmarisBack.controller;

import com.DeveloperTestAmarisBack.model.Employee;
import com.DeveloperTestAmarisBack.model.EmployeesGroup;
import com.DeveloperTestAmarisBack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> getAllEmployees() throws Exception {
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/{idEmployee}")
    public Employee getOneEmployee(@PathVariable("idEmployee") Long idEmployee) throws Exception {
        return this.employeeService.getOneEmployee(idEmployee);
    }
}
