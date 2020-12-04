package com.thoughtworks.springbootemployee.dto;

import java.util.List;

public class CompanyResponse {
    private String id;
    private String name;
    private List<EmployeeResponse> employees;
    private int employeeNumber;

    public CompanyResponse(String id, String name, List<EmployeeResponse> employees, int employeeNumber) {
        this.id = id;
        this.name = name;
        this.employees = employees;
        this.employeeNumber = employeeNumber;
    }

    public CompanyResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponse> employees) {
        this.employees = employees;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
