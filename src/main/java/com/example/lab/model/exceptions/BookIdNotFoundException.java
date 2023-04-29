package com.example.lab.model.exceptions;

public class BookIdNotFoundException extends Exception{

    public BookIdNotFoundException(Long id) {
        super("Book with requested id =" + id + " could not be found");
    }
}
