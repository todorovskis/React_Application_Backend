package com.example.lab.rest;


import com.example.lab.model.Author;
import com.example.lab.service.AuthorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    In the context of React, if a React application needs to make HTTP requests to a RESTful
    web service running on a different domain, it must comply with CORS restrictions.
    This means that the server hosting the RESTful web service must explicitly allow requests
    from the React application's domain. Otherwise, the browser will block the request and
    return an error.
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return this.authorService.findAll();
    }


}
