package com.thoughtworks.springbootemployee;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Employee {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String name;
    private int age;
    private String gender;
    private int salary;

    public Employee(String name, int age, String gender, int salary, String id) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.id = id;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Employee employee = (Employee) obj;
        return this.age == employee.age && this.gender.equals(employee.gender) && this.name.equals(employee.name)
                && this.salary == employee.salary && this.id.equals(employee.id);
    }
}
