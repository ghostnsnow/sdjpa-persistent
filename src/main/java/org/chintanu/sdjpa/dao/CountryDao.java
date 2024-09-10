package org.chintanu.sdjpa.dao;

import org.chintanu.sdjpa.entity.Country;

public interface CountryDao {

    Country getCountryById(String countryId);
    Country getCountryByName(String countryName);

    boolean saveCountry(Country country);
}
