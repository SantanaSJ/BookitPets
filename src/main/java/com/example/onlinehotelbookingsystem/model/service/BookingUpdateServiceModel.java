package com.example.onlinehotelbookingsystem.model.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookingUpdateServiceModel {

    private Long id;

    private String firstName;

    private String lastName;

    private String petName;

    private Integer petKilograms;
    private String email;
    private String comments;

    public String getFirstName() {
        return firstName;
    }

    public BookingUpdateServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BookingUpdateServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public BookingUpdateServiceModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public BookingUpdateServiceModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookingUpdateServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public BookingUpdateServiceModel setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BookingUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }
}
