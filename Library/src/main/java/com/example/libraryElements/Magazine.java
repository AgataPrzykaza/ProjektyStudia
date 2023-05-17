package com.example.libraryElements;

public class Magazine extends LibraryObject {

    private String issueNumber;
    private String publisher;

    public Magazine(String title,String author, String issueNumber, String publisher) {
        super(title,author);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public void displayInfo() {
    }
}
