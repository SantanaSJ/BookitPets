package com.example.onlinehotelbookingsystem.model.binding;


import com.example.onlinehotelbookingsystem.model.binding.validator.UniqueEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UserRegistrationBindingModel {

    @NotBlank(message = "Please fill in first name!")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters.")
    private String firstName;

    @NotBlank(message = "Please fill in last name!")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters.")
    private String lastName;

    @NotBlank(message = "Please enter phone number")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number should be in the format: 0884444333")
    private String phoneNumber;

    @NotBlank(message = "Please enter an email!")
    @UniqueEmail
    @Pattern(regexp = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$", message = "Please enter a valid email!")
    private String email;

    @NotBlank
    @Size(min = 3, max = 10, message = "Pet name must be between 3 and 10 characters.")
    private String petName;

    @Positive
    private String petKilograms;


    @NotBlank(message = "Please provide a password!")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Password must contain minimum 8 characters: At least 1 upper case letter, " +
                    "At least 1 lower case letter, At least 1 digit, " +
                    "At least 1 special character")
    private String password;

    @NotBlank(message = "Please provide a confirmation password!")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Password must contain: At least 1 upper case letter, " +
                    "At least 1 lower case letter, At least 1 digit, " +
                    "At least 1 special character, Minimum length - 8 characters.")
    private String confirmPassword;

    public UserRegistrationBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegistrationBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public UserRegistrationBindingModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public String getPetKilograms() {
        return petKilograms;
    }

    public UserRegistrationBindingModel setPetKilograms(String petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }
}
