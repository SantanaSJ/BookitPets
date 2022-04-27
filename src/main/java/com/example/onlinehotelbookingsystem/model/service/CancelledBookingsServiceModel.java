package com.example.onlinehotelbookingsystem.model.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CancelledBookingsServiceModel {


    private LocalDate checkIn;
    private LocalDate checkOut;
    private String firstName;
    private String lastName;
    private int petKilograms;
    private String petName;
    private BigDecimal totalPrice;
    private long totalNights;

    private LocalDateTime cancelledOn;


    public LocalDate getCheckIn() {
        return checkIn;
    }

    public CancelledBookingsServiceModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public CancelledBookingsServiceModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CancelledBookingsServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CancelledBookingsServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getPetKilograms() {
        return petKilograms;
    }

    public CancelledBookingsServiceModel setPetKilograms(int petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public CancelledBookingsServiceModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public CancelledBookingsServiceModel setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public long getTotalNights() {
        return totalNights;
    }

    public CancelledBookingsServiceModel setTotalNights(long totalNights) {
        this.totalNights = totalNights;
        return this;
    }

    public LocalDateTime getCancelledOn() {
        return cancelledOn;
    }

    public CancelledBookingsServiceModel setCancelledOn(LocalDateTime cancelledOn) {
        this.cancelledOn = cancelledOn;
        return this;
    }
}
