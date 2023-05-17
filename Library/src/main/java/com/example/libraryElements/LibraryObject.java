package com.example.libraryElements;

public abstract class LibraryObject {

    private String title;
    private String author;
    private boolean available;

    public LibraryObject(){}
    public LibraryObject(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract void displayInfo();
}
