package com.example.library;
import com.example.libraryElements.LibraryObject;

import java.util.ArrayList;
import java.util.List;
import com.example.users.*;
import java.time.LocalDate;

public class Library {

    private List<LibraryObject> objects;
    private List<Reader> readers;
    private List<Borrowing> borrowings;

    public Library() {
        this.objects = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.borrowings = new ArrayList<>();
    }

    public void addObject(LibraryObject object) {
        objects.add(object);
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void borrowBook(Reader reader, LibraryObject object) {
        if (!objects.contains(object)) {
            System.out.println("Book not available in the library.");
            return;
        }

        if (!readers.contains(reader)) {
            System.out.println("Reader not registered in the library.");
            return;
        }

        for (Borrowing borrowing : borrowings) {
            if (borrowing.getObject().equals(object)) {
                System.out.println("Book already borrowed by another reader.");
                return;
            }
        }
        Borrowing borrowing = new Borrowing(reader, object, LocalDate.now());
        borrowings.add(borrowing);

    }
}
