package com.example.onlinehotelbookingsystem.model.service;

import java.time.LocalDate;

public class TitleBookingServiceModel {

    private Long bookingId;
    private String firstName;
    private String lastName;
    private String hotelName;
    private String city;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public String getFirstName() {
        return firstName;
    }

    public TitleBookingServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TitleBookingServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public TitleBookingServiceModel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public TitleBookingServiceModel setCity(String city) {
        this.city = city;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public TitleBookingServiceModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public TitleBookingServiceModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public TitleBookingServiceModel setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }
}
