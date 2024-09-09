package org.chintanu.sdjpa.bootstrap;

import org.chintanu.sdjpa.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private CountryRepository countryRepository;

    //Constructor Injection
    public DataInitializer(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        countryRepository.findAll().forEach(e -> {

            System.out.println("Country : " + e.toString());
        });
    }
}
