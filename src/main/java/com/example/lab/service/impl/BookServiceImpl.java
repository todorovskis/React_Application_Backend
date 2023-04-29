package com.example.lab.service.impl;

import com.example.lab.enumeration.Category;
import com.example.lab.model.Author;
import com.example.lab.model.Book;
import com.example.lab.model.dto.BookDto;
import com.example.lab.model.exceptions.AuthorIdNotFoundException;
import com.example.lab.model.exceptions.BookIdNotFoundException;
import com.example.lab.repository.AuthorRepository;
import com.example.lab.repository.BookRepository;
import com.example.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).get();
        Book book = new Book(name, category, author, availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Category category = Category.valueOf(bookDto.getCategory().name());
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElse(null);
        this.bookRepository.deleteBookByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), category, author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }


    // @Override
    //    public Optional<Product> save(ProductDto productDto) {
    //        Category category = this.categoryRepository.findById(productDto.getCategory())
    //                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
    //        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer())
    //                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer()));
    //
    //        this.productRepository.deleteByName(productDto.getName());
    //        Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getQuantity(), category, manufacturer);
    //        this.productRepository.save(product);
    //        //this.refreshMaterializedView();
    //
    //        this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
    //        return Optional.of(product);
    //    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).get();
        Book book = this.bookRepository.findById(id).get();

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElse(null);
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).get();

        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(bookDto.getCategory());

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void markBookAsUnavailable(Long id) {
        Book book = this.bookRepository.findById(id).get();
        int newNumCopies = book.getAvailableCopies() - 1;
        book.setAvailableCopies(newNumCopies);
    }
}
