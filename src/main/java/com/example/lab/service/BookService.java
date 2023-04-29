package com.example.lab.service;


import com.example.lab.enumeration.Category;
import com.example.lab.model.Book;
import com.example.lab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    void markBookAsUnavailable(Long id);
}
