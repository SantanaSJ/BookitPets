package com.example.onlinehotelbookingsystem.model.binding;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ProfileUpdateBindingModel {

    private Long userId;

    @NotBlank(message = "Please provide first name!")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters.")

    private String firstName;

    @NotBlank(message = "Please provide last name!")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters.")
    private String lastName;

    @NotBlank(message = "Please provide an email!")
    @Pattern(regexp = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$", message = "Please enter a valid email!")
    private String email;

    @NotBlank(message = "Please provide a phone number!")
    @Pattern(regexp = "[0-9 ]{10}", message = "Phone number should be in the format: 0xxxxxxx")
    private String phoneNumber;

    @NotBlank(message = "Please provide your pet name!")
    @Size(min = 3, max = 10, message = "Pet name must be between 3 and 10 characters.")
    private String petName;

    @Valid
    private AddImageBindingModel addImageBindingModel;

    @NotBlank(message = "Please provide pet kilograms.")
    private String petKilograms;

    public Long getUserId() {
        return userId;
    }

    public ProfileUpdateBindingModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileUpdateBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileUpdateBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileUpdateBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public ProfileUpdateBindingModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public String getPetKilograms() {
        return petKilograms;
    }

    public ProfileUpdateBindingModel setPetKilograms(String petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileUpdateBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AddImageBindingModel getAddImageBindingModel() {
        return addImageBindingModel;
    }

    public ProfileUpdateBindingModel setAddImageBindingModel(AddImageBindingModel addImageBindingModel) {
        this.addImageBindingModel = addImageBindingModel;
        return this;
    }
}
