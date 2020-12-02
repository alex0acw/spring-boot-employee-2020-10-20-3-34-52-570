package com.thoughtworks.springbootemployee;

public class Employee {
    private int id;
    private String name;
    private int age;
    private String gender;
    private int salary;

    public Employee(int id, String name, int age, String gender, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public int getId() {
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
}
