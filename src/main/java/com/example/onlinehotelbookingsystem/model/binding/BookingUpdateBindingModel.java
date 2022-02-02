package com.example.onlinehotelbookingsystem.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookingUpdateBindingModel {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 characters.")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20, message = "Last name length must be between 3 and 20 characters.")
    private String lastName;

    @NotBlank
    private String petName;

    @NotNull
    private Integer petKilograms;
    @NotBlank
    private String email;
    private String comments;

    public String getFirstName() {
        return firstName;
    }

    public BookingUpdateBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BookingUpdateBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public BookingUpdateBindingModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public BookingUpdateBindingModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookingUpdateBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public BookingUpdateBindingModel setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BookingUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }
}
