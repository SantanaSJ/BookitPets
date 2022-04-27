package com.example.onlinehotelbookingsystem.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityServiceModel {

    private Long hotelId;
    private LocalDate checkIn;
    private LocalDate checkOut;

    private List<RoomServiceModel> rooms = new ArrayList<>();

//    private Integer numberOfPeople;

    public Long getHotelId() {
        return hotelId;
    }

    public AvailabilityServiceModel setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public AvailabilityServiceModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public AvailabilityServiceModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public List<RoomServiceModel> getRooms() {
        return rooms;
    }

    public AvailabilityServiceModel setRooms(List<RoomServiceModel> rooms) {
        this.rooms = rooms;
        return this;
    }

//    public Integer getNumberOfPeople() {
//        return numberOfPeople;
//    }
//
//    public AvailabilityServiceModel setNumberOfPeople(Integer numberOfPeople) {
//        this.numberOfPeople = numberOfPeople;
//        return this;
//    }
}
