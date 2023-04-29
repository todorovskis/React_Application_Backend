package com.example.lab.rest;

import com.example.lab.enumeration.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryController {

    @GetMapping
    public List<Category> getCategories() {
        return List.of(Category.values());
    }
}
