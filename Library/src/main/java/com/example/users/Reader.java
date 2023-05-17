package com.example.users;

import com.example.libraryElements.LibraryObject;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person{
    private int readerId;
    private List<LibraryObject> libraryObjects;

    public Reader(String name, int age, int readerId) {
        super(name, age);
        this.readerId = readerId;
        this.libraryObjects =new ArrayList<>();

    }

    public List<LibraryObject> getBooks() {
        return libraryObjects;
    }

    public void addBook(LibraryObject book) {
        libraryObjects.add(book);
    }
    public void removeBook(LibraryObject book) {
        libraryObjects.remove(book);
    }
    public int getReaderId() {
        return readerId;
    }
}
