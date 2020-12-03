package com.thoughtworks.springbootemployee;

import java.util.List;

public class Company {
    private String companyName;
    private int employeesNumber;
    private int id;

    public Company() {
    }

    public Company(int id, String companyName, List<Employee> employees) {
        this.companyName = companyName;
        this.id = id;
        this.employees = employees;
    }

    private List<Employee> employees;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        this.employeesNumber = this.employees.size();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Company company = (Company) obj;
        return this.companyName.equals(company.getCompanyName()) &&
                this.employees.equals(company.employees)
                && this.id == company.id;
    }
}
