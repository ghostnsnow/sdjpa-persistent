package org.chintanu.sdjpa.entity;

import jakarta.persistence.*;
import org.chintanu.sdjpa.entity.enumerations.CountryContinent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "COUNTRIES")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@GenericGenerator(strategy = GenerationType.UU)
    @Column(name = "COUNTRY_ID")
    private String countryId;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "REGION_ID")
    private int regionId;

    @Column(name = "CONTINENT")
    @Enumerated(EnumType.ORDINAL)
    private CountryContinent continent;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "stateCode", column = @Column(name = "STATECODE")),
                        @AttributeOverride(name = "stateNm", column = @Column(name = "STATENAME"))})
    private State state;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "STATES", joinColumns = @JoinColumn(name = "COUNTRY_ID"))
    private Set<State> states = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PM_ID")
    private PrimeMinister countryPM;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name = "COUNTRY_ID")
    @JoinTable(name = "XREF_COUNTRY_CMS", joinColumns = {@JoinColumn(name = "COUNTRY_ID")}, inverseJoinColumns = {@JoinColumn(name = "CM_ID")})
    private List<ChiefMinister> lstCMs;

    public List<ChiefMinister> getLstCMs() {
        return lstCMs;
    }

    public void setLstCMs(List<ChiefMinister> lstCMs) {
        this.lstCMs = lstCMs;
    }

    public PrimeMinister getCountryPM() {
        return countryPM;
    }

    public void setCountryPM(PrimeMinister countryPM) {
        this.countryPM = countryPM;
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public CountryContinent getContinent() {
        return continent;
    }

    public void setContinent(CountryContinent continent) {
        this.continent = continent;
    }

    public Country() {

    }

    public Country(String countryName, int regionId, CountryContinent continent) {
        this.countryName = countryName;
        this.regionId = regionId;
        this.continent = continent;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId='" + countryId + '\'' +
                ", countryName='" + countryName + '\'' +
                ", regionId=" + regionId +
                ", continent=" + continent +
                ", state=" + state +
                ", states=" + states +
                ", countryPM=" + countryPM +
                ", lstCMs=" + lstCMs +
                '}';
    }
}
