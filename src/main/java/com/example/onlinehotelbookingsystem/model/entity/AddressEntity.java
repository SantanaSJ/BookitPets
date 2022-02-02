package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//NOT WORKING ONE TO ONE RELATIONSHIP


public class AddressEntity {

    private String city;
    private String street;
    private Integer number;
    private Integer postalCode;

    @OneToOne(optional = false)
    @JoinColumn(name = "address")
    private AccommodationEntity property;


    public String getCity() {
        return city;
    }

    public AddressEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public AddressEntity setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public AddressEntity setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AccommodationEntity getProperty() {
        return property;
    }

    public AddressEntity setProperty(AccommodationEntity property) {
        this.property = property;
        return this;
    }
}
