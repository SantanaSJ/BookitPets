package com.example.onlinehotelbookingsystem.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingServiceModel {

    private Long hotelId;
    private Long bookingId;

    private String firstName;

    private String lastName;

    private String petName;

    private Integer petKilograms;
    private String email;
    private String comments;

    private LocalDate checkIn;
    private LocalDate checkOut;

    private List<RoomServiceModel> bookedRooms = new ArrayList<>();

    private Integer numberOfPeople;

    public String getFirstName() {
        return firstName;
    }

    public BookingServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BookingServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public BookingServiceModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public BookingServiceModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookingServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public BookingServiceModel setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public BookingServiceModel setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public BookingServiceModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BookingServiceModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public List<RoomServiceModel> getBookedRooms() {
        return bookedRooms;
    }

    public BookingServiceModel setBookedRooms(List<RoomServiceModel> bookedRooms) {
        this.bookedRooms = bookedRooms;
        return this;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public BookingServiceModel setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public BookingServiceModel setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }
}
