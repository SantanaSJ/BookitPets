package com.example.onlinehotelbookingsystem.model.service;

public class UserRegistrationServiceModel {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;


    public UserRegistrationServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegistrationServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
