package com.example.lab.service.impl;


import com.example.lab.model.Country;
import com.example.lab.repository.CountryRepository;
import com.example.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name, continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
