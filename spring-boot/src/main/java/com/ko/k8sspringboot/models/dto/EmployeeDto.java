package com.ko.k8sspringboot.models.dto;

public class EmployeeDto {

    private int age;
    private String firstName;

    private String lastName;

    public int getAge() {
        return age;
    }

    public EmployeeDto setAge(int age) {
        this.age = age;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
