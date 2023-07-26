package com.DeveloperTestAmarisBack.service;

import com.DeveloperTestAmarisBack.model.Employee;
import com.DeveloperTestAmarisBack.model.EmployeesGroup;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees() throws Exception;

    Employee getOneEmployee(Long idEmployee) throws Exception;
}
