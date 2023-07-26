package com.DeveloperTestAmarisBack.model;

import lombok.Data;

import java.util.List;

@Data
public class EmployeesGroup {

    private List<Employee> data;

    private String status;

    private String message;


}
