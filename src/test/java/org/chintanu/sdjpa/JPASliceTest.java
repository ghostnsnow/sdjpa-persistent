package org.chintanu.sdjpa;

import org.assertj.core.api.Assertions;
import org.chintanu.sdjpa.entity.Country;
import org.chintanu.sdjpa.entity.enumerations.CountryContinent;
import org.chintanu.sdjpa.repository.CountryRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:tcp://localhost/~/test",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.jpa.hibernate.ddl-auto=none"})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)//To disable using he embedded DB & to use the above provided config
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//To define in what order test-cases should be ordered. Here we are using @Order annotation
public class JPASliceTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    @Order(1)
    void testPersistenceOfData() {

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
