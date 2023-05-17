package com.example.library;

import com.example.libraryElements.LibraryObject;
import com.example.users.*;
import java.time.LocalDate;

public class Borrowing {
    private Reader reader;
    private LibraryObject object;
    private LocalDate borrowDate;

    public Borrowing(Reader reader, LibraryObject object, LocalDate borrowDate) {
        this.reader = reader;
        this.object = object;
        this.borrowDate = borrowDate;
    }

    public Reader getReader() {
        return reader;
    }

    public LibraryObject getObject() {
        return object;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }
}
