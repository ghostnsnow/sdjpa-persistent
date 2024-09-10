package org.chintanu.sdjpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.chintanu.sdjpa.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl implements CountryDao {

    @Autowired
    EntityManager em;

    @Override
    public Country getCountryById(String countryId) {

        Country country = em.find(Country.class, countryId);
        return country;
    }

    @Override
    public Country getCountryByName(String countryName) {

        TypedQuery<Country> query = em.createQuery("select c from Country c where c.countryName = :countryName", Country.class);
        query.setParameter("countryName", countryName);
        return query.getSingleResult();
    }

    @Override
    public boolean saveCountry(Country country) {

        boolean saveSuccessful = true;

        try {
            em.persist(country);
        } catch (Exception e) {
            saveSuccessful = false;
        }

        return saveSuccessful;
    }
}
