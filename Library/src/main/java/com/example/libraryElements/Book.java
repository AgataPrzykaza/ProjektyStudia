package com.example.libraryElements;

public class Book extends LibraryObject {

    private String ISBN;
    private String genre;

    public Book(String title, String author, String ISBN, String genre) {
        super(title, author);
        this.ISBN = ISBN;
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }
    public String getGenre() {
        return genre;
    }

    @Override
    public void displayInfo() {

    }
}
