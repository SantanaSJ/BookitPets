package com.example.onlinehotelbookingsystem.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateBookingServiceModel {

    private Long hotelId;
    private Long bookingId;

    private String firstName;

    private String lastName;

    private String petName;

    private Integer petKilograms;
    private String email;
    private String phoneNumber;
    private String comments;

    private LocalDate checkIn;
    private LocalDate checkOut;

    private List<RoomServiceModel> bookedRooms = new ArrayList<>();

    public Long getHotelId() {
        return hotelId;
    }

    public CreateBookingServiceModel setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public CreateBookingServiceModel setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CreateBookingServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateBookingServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public CreateBookingServiceModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public CreateBookingServiceModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateBookingServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CreateBookingServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public CreateBookingServiceModel setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public CreateBookingServiceModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public CreateBookingServiceModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public List<RoomServiceModel> getBookedRooms() {
        return bookedRooms;
    }

    public CreateBookingServiceModel setBookedRooms(List<RoomServiceModel> bookedRooms) {
        this.bookedRooms = bookedRooms;
        return this;
    }
}
