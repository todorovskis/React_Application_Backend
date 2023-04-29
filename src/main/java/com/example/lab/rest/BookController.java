package com.example.lab.rest;


import com.example.lab.enumeration.Category;
import com.example.lab.model.Author;
import com.example.lab.model.Book;
import com.example.lab.model.dto.BookDto;
import com.example.lab.model.exceptions.AuthorIdNotFoundException;
import com.example.lab.service.AuthorService;
import com.example.lab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/*
    If the Optional object contains a Manufacturer object,
     the method maps it to an ResponseEntity object with
     an HTTP status code of 200 OK using the
     ResponseEntity.ok().body(manufacturer) method,
     where manufacturer is the retrieved Manufacturer object.

    If the Optional object is empty, the method maps it
    to an ResponseEntity object with an HTTP status code
    of 404 Not Found using the ResponseEntity.notFound().build()
    method.
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping({"/api/books", "/"})
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return this.bookService.findAll();
    }

    @GetMapping("/categories")
    public List<Category> getAllBookCategories() {
        return Arrays.stream(Category.values()).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping("/add")
//    public ResponseEntity<Book> save(@RequestParam String name,
//                                     @RequestParam String category,
//                                     @RequestParam Long authorId,
//                                     @RequestParam Integer availableCopies) {
//        return this.bookService.save(name, Category.valueOf(category), authorId, availableCopies)
//                .map(book -> ResponseEntity.ok().body(book))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

//    @PostMapping("/{id}/edit")
//    public ResponseEntity<Book> edit(@PathVariable Long id,
//                                     @RequestParam String name,
//                                     @RequestParam String category,
//                                     @RequestParam Long authorId,
//                                     @RequestParam Integer availableCopies) {
//        return this.bookService.edit(id, name, Category.valueOf(category), authorId, availableCopies)
//                .map(book -> ResponseEntity.ok().body(book))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Book> edit(@PathVariable Long id,
                                     @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}/taken")
    public ResponseEntity markAsTaken(@PathVariable Long id) {
        Book book = this.bookService.findById(id).get();
        this.bookService.edit(id, book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies()-1);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if (this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
