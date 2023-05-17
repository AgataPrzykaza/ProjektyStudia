package com.example.users;

public class Admin extends Person {

    private String role;

    public Admin(String name, int age, String role) {
        super(name, age);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
