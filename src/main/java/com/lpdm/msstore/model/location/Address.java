package com.lpdm.msstore.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

    private int id;
    private String streetNumber;
    private String streetName;
    private String complement;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int cityId;
    private City city;

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetNumber='" + streetNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", complement='" + complement + '\'' +
                ", cityId=" + cityId +
                ", city=" + city +
                '}';
    }
}
