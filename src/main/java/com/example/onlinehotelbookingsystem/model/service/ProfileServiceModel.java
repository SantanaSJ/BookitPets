package com.example.onlinehotelbookingsystem.model.service;

import java.time.LocalDateTime;

public class ProfileServiceModel {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String petName;
    private String petImageUrl;
    private Integer petKilograms;
    private LocalDateTime joined;
    private String phoneNumber;
    private String profileImageUrl;

    public ProfileServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public ProfileServiceModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public String getPetImageUrl() {
        return petImageUrl;
    }

    public ProfileServiceModel setPetImageUrl(String petImageUrl) {
        this.petImageUrl = petImageUrl;
        return this;
    }

    public LocalDateTime getJoined() {
        return joined;
    }

    public ProfileServiceModel setJoined(LocalDateTime joined) {
        this.joined = joined;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public ProfileServiceModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ProfileServiceModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public ProfileServiceModel setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        return this;
    }
}
