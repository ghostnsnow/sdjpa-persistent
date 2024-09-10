package org.chintanu.sdjpa.dao;

import org.assertj.core.api.Assertions;
import org.chintanu.sdjpa.entity.Country;
import org.chintanu.sdjpa.entity.enumerations.CountryContinent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "org.chintanu.sdjpa.dao")
class CountryDaoImplTest {

    @Autowired
    CountryDao dao;

    @Test
    void getCountryById() {

        Country country = dao.getCountryById("15b3cc1d-564b-424c-bc2a-1aaf2ccb82e2");
        Assertions.assertThat(country).isNotNull();
        Assertions.assertThat(country.getCountryName()).isEqualToIgnoringCase("India");
        Assertions.assertThat(country.getState().getStateNm()).isEqualToIgnoringCase("Odisha");
    }

    @Test
    void getCountryByName() {

        Country country = dao.getCountryByName("India");
        Assertions.assertThat(country).isNotNull();
        Assertions.assertThat(country.getCountryId()).isEqualToIgnoringCase("15b3cc1d-564b-424c-bc2a-1aaf2ccb82e2");
        Assertions.assertThat(country.getState().getStateNm()).isEqualToIgnoringCase("Odisha");
    }

    @Test
    void saveCountry() {

        Country country = new Country("USA", 2, CountryContinent.NA);

        boolean saveSuccessful = dao.saveCountry(country);

        Country savedCountry = dao.getCountryById(country.getCountryId());
        System.out.println("======>" + savedCountry);

        Assertions.assertThat(saveSuccessful).isTrue();

        Assertions.assertThat(savedCountry).isNotNull();
        Assertions.assertThat(savedCountry.getCountryName()).isEqualToIgnoringCase("USA");
        Assertions.assertThat(savedCountry.getContinent()).isEqualTo(CountryContinent.NA);
    }
}