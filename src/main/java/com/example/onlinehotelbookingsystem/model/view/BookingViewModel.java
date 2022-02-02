package com.example.onlinehotelbookingsystem.model.view;

import com.example.onlinehotelbookingsystem.model.service.RoomServiceModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingViewModel {
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

    public Long getHotelId() {
        return hotelId;
    }

    public BookingViewModel setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BookingViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BookingViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public BookingViewModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public BookingViewModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookingViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public BookingViewModel setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public BookingViewModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BookingViewModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public List<RoomServiceModel> getBookedRooms() {
        return bookedRooms;
    }

    public BookingViewModel setBookedRooms(List<RoomServiceModel> bookedRooms) {
        this.bookedRooms = bookedRooms;
        return this;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public BookingViewModel setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public BookingViewModel setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }
}
