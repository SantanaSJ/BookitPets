package com.example.onlinehotelbookingsystem.model.service;

import com.example.onlinehotelbookingsystem.model.binding.AddImageBindingModel;

import java.util.ArrayList;
import java.util.List;


public class ProfileUpdateServiceModel {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String petName;
    private Integer petKilograms;
    private AddImageBindingModel addImageBindingModel;

    public String getFirstName() {
        return firstName;
    }

    public ProfileUpdateServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileUpdateServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileUpdateServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public ProfileUpdateServiceModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public ProfileUpdateServiceModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ProfileUpdateServiceModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileUpdateServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AddImageBindingModel getAddImageBindingModel() {
        return addImageBindingModel;
    }

    public ProfileUpdateServiceModel setAddImageBindingModel(AddImageBindingModel addImageBindingModel) {
        this.addImageBindingModel = addImageBindingModel;
        return this;
    }
}
