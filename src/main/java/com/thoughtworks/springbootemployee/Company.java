package com.thoughtworks.springbootemployee;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Company {
    private String companyName;
    private int employeesNumber;
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    @DBRef(lazy = true)
    private List<Employee> employees;

    public Company() {
    }

    public Company(String id, String companyName, List<Employee> employees) {
        this.companyName = companyName;
        this.id = id;
        this.employees = employees;
        this.employeesNumber = employees.size();
    }


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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Company company = (Company) obj;
        return this.companyName.equals(company.getCompanyName()) &&
                this.employees.equals(company.employees)
                && this.id.equals(company.id);
    }
}
