package com.example.onlinehotelbookingsystem.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityBindingModel {

    private Long hotelId;
    private String hotelName;

    @FutureOrPresent
    //    type mismatch error
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Check in date is required!")
    private LocalDate checkIn;

    @Future
//    type mismatch error
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Check out date is required!")
    private LocalDate checkOut;

    @Valid
    private List<RoomBindingModel> rooms = new ArrayList<>();

    private int category;
    private String city;
    private String address;

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

    public Long getHotelId() {
        return hotelId;
    }

    public AvailabilityBindingModel setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public AvailabilityBindingModel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }


    public int getCategory() {
        return category;
    }

    public AvailabilityBindingModel setCategory(int category) {
        this.category = category;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AvailabilityBindingModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AvailabilityBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }
}
