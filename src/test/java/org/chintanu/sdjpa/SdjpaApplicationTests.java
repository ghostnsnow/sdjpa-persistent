package org.chintanu.sdjpa;

import org.assertj.core.api.Assertions;
import org.chintanu.sdjpa.entity.Country;
import org.chintanu.sdjpa.entity.enumerations.CountryContinent;
import org.chintanu.sdjpa.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class SdjpaApplicationTests {

    @Autowired
    CountryRepository countryRepository;

    @Test
    void testGetCountry() {

        long before = countryRepository.count();

        Assertions.assertThat(before).isGreaterThan(0);

        Country sl = new Country();
        sl.setContinent(CountryContinent.ASIA);
        sl.setCountryName("SL");
        sl.setRegionId(2);
        countryRepository.save(sl);

        long after = countryRepository.count();

        System.out.println("=====>" + before + " : " + after);
        Assertions.assertThat(after).isGreaterThan(0);
        Assertions.assertThat(after).isGreaterThan(before);
    }
}
