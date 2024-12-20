package com.ko.k8sspringboot.models.dto;

public class EmployeeOldestDto {

    private int age;
    private String firstName;

    private String lastName;

    public int getAge() {
        return age;
    }

    public EmployeeOldestDto setAge(int age) {
        this.age = age;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeOldestDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeOldestDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
