package com.example.users;

public  class Person {

    private String name;

    private String lastName;

    private String address;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getLastName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age){
        this.age=age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address=address;
    }

}
