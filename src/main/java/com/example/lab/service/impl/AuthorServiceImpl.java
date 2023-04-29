package com.example.lab.service.impl;


import com.example.lab.model.Author;
import com.example.lab.model.Country;
import com.example.lab.repository.AuthorRepository;
import com.example.lab.repository.CountryRepository;
import com.example.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).get();
        Author author = new Author(name,surname,country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
