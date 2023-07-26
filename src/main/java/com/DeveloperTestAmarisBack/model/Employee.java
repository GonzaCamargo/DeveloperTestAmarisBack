package com.DeveloperTestAmarisBack.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Employee {

    private Long id;
    private String employee_name;
    private BigDecimal employee_salary;
    private BigDecimal employee_age;
    private String profile_image;
    private BigDecimal employee_anual_salary;

    public Employee(Long id, String employee_name, BigDecimal employee_salary, BigDecimal employee_age, String profile_image) {
        super();
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
        this.employee_anual_salary = this.employee_salary.multiply(new BigDecimal(12));
    }

}
