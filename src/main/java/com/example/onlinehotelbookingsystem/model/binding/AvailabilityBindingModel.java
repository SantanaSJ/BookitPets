package com.example.onlinehotelbookingsystem.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityBindingModel {

    private Long hotelId;

    @NotNull(message = "Check in date is required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate checkIn;
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Check out date is required!")
    private LocalDate checkOut;

    @Valid
    private List<RoomBindingModel> rooms = new ArrayList<>();

    @NotNull(message = "Number of people is required!")
    private Integer numberOfPeople;


    public AvailabilityBindingModel() {
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public AvailabilityBindingModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public AvailabilityBindingModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }


    public List<RoomBindingModel> getRooms() {
        return rooms;
    }

    public AvailabilityBindingModel setRooms(List<RoomBindingModel> rooms) {
        this.rooms = rooms;
        return this;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public AvailabilityBindingModel setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        return this;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public BookingBindingModel setEmail(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public BookingBindingModel setComments(String comments) {
//        this.comments = comments;
//        return this;
//    }

    public Long getHotelId() {
        return hotelId;
    }

    public AvailabilityBindingModel setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }
}
