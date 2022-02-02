package com.example.onlinehotelbookingsystem.model.view;

import java.time.LocalDateTime;

public class ProfileViewModel {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String petName;
    private String petImageUrl;
    private String petKilograms;

    private LocalDateTime joined;

    public ProfileViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public ProfileViewModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public String getPetImageUrl() {
        return petImageUrl;
    }

    public ProfileViewModel setPetImageUrl(String petImageUrl) {
        this.petImageUrl = petImageUrl;
        return this;
    }

    public LocalDateTime getJoined() {
        return joined;
    }

    public ProfileViewModel setJoined(LocalDateTime joined) {
        this.joined = joined;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ProfileViewModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getPetKilograms() {
        return petKilograms;
    }

    public ProfileViewModel setPetKilograms(String petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }
}
