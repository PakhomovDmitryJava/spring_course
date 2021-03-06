package ru.alishev.springcourse.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "The name shouldn't be empty")
    @Size(min = 2, max = 30, message = "The name should be between 2 " +
            "and 30 characters")
    private String name;

    @Min(value = 0, message = "The age must be greater than 0")
    private int age;

    @NotEmpty(message = "The email shouldn't be empty")
    @Email(message = "The email should be valid")
    private String email;

    // County, City, Index (123456)
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",
            message = "Your address should be like this form include cases: County, City, Index (123456)")
    private String address;

    public Person() {
    }

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
