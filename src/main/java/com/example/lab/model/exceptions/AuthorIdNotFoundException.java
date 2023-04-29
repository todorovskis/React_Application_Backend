package com.example.lab.model.exceptions;

public class AuthorIdNotFoundException extends Exception{

    public AuthorIdNotFoundException(Long id) {
        super("Author with requested id =" + id + " could not be found");
    }
}
