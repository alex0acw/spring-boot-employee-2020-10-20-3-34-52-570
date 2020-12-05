package com.thoughtworks.springbootemployee.dto;

import java.util.List;

public class CompanyResponse {
    private String id;
    private String companyName;
    private List<EmployeeResponse> employees;
    private int employeesNumber;

    public CompanyResponse(String id, String name, List<EmployeeResponse> employees, int employeesNumber) {
        this.id = id;
        this.companyName = name;
        this.employees = employees;
        this.employeesNumber = employeesNumber;
    }

    public CompanyResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponse> employees) {
        this.employees = employees;
        this.employeesNumber = this.employees.size();
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

}
