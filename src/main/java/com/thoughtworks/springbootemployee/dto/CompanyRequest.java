package com.thoughtworks.springbootemployee.dto;

import java.util.List;

public class CompanyRequest {
    private String companyName;
    private List<String> employees;

    public CompanyRequest(String companyName, List<String> employees) {
        this.companyName = companyName;
        this.employees = employees;
    }

    public CompanyRequest() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }
}
