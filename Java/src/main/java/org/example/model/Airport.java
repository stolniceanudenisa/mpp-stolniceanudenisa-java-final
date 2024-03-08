package org.example.model;

import java.util.Objects;

public class Airport extends Entity<Long>{

    private Enum<AirportNames> name;
    private String city;

    public Airport(Long id, Enum<AirportNames> name, String city) {
        super(id);
        this.name = name;
        this.city = city;
    }

    public Enum<AirportNames> getName() {
        return name;
    }

    public void setName(Enum<AirportNames> name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Airport: " +
                "name: " + name +
                ", city: " + city + '\'' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport)) return false;
        return Objects.equals(getName(), airport.getName()) && Objects.equals(getCity(), airport.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCity());
    }
}