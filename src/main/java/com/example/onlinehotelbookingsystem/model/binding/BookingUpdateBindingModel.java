package com.example.onlinehotelbookingsystem.model.binding;

import javax.validation.constraints.*;

public class BookingUpdateBindingModel {

    private Long bookingId;

    @NotBlank(message = "Please provide first name!")
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 characters.")
    private String firstName;

    @NotBlank(message = "Please provide last name!")
    @Size(min = 3, max = 20, message = "Last name length must be between 3 and 20 characters.")
    private String lastName;

    @NotBlank(message = "Please provide pet name!")
    @Size(min = 3, max = 10, message = "Pet name must be between 3 and 10 characters.")
    private String petName;

    @NotNull(message = "Please provide pet kilograms!")
    @Positive
    private Integer petKilograms;

    @NotBlank(message = "Please provide an email!")
    @Pattern(regexp = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$", message = "Please enter a valid email!")
    private String email;

    @NotBlank(message = "Please provide a phone number!")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number should be in the format: 0884444333")
    private String phoneNumber;

    private String comments;

    private String paymentStatus;

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
        return bookingId;
    }

    public BookingUpdateBindingModel setId(Long id) {
        this.bookingId = id;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public BookingUpdateBindingModel setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BookingUpdateBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public BookingUpdateBindingModel setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }
}
