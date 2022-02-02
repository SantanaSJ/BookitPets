package com.example.onlinehotelbookingsystem.model.view;

import java.time.LocalDate;

public class TitleBookingViewModel {

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

    public TitleBookingViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TitleBookingViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public TitleBookingViewModel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public TitleBookingViewModel setCity(String city) {
        this.city = city;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public TitleBookingViewModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public TitleBookingViewModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public TitleBookingViewModel setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }
}
