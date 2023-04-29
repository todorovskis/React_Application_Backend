package com.example.lab.model.exceptions;

public class CountryIdNotFoundException extends Exception {

    public CountryIdNotFoundException(Long id) {
        super("Category with requested id =" + id + " could not be found");
    }
}
