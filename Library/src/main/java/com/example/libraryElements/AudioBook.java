package com.example.libraryElements;

public class AudioBook extends LibraryObject{


    private String narrator;
    private int duration;

    public AudioBook(){}

    public  AudioBook(String title, String author, String narrator, int duration) {
        super(title, author);
        this.narrator = narrator;
        this.duration = duration;
    }

    public String getNarrator() {
        return narrator;
    }

    public int getDuration() {
        return duration;
    }
    @Override
    public void displayInfo() {

    }
}
