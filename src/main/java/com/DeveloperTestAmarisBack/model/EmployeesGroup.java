package com.DeveloperTestAmarisBack.model;

import lombok.Data;

import java.util.List;

@Data
public class EmployeesGroup {

    private List<Employee> data;

    private String status;

    private String message;

//    public EmployeesGroup(){
//        super();
//    }
//
//    public List<Employee> getData() {
//        return data;
//    }
//
//    public void setData(List<Employee> employees) {
//        this.data = employees;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
