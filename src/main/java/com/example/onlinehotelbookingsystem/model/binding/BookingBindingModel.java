package com.example.onlinehotelbookingsystem.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingBindingModel {


    private Long hotelId;

    @NotBlank(message = "First name is required!")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    private String lastName;

    @NotBlank(message = "Pet name is required!")
    private String petName;

    @NotNull(message = "Pet kilograms are required!")
    @Positive
    private Integer petKilograms;

    @NotBlank(message = "Email is required!")
    private String email;
    private String comments;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;

    private List<RoomBindingModel> bookedRooms = new ArrayList<>();

    private Integer numberOfPeople;

    public String getFirstName() {
        return firstName;
    }

    public BookingBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BookingBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public BookingBindingModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public BookingBindingModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookingBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public BookingBindingModel setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public BookingBindingModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BookingBindingModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public List<RoomBindingModel> getBookedRooms() {
        return bookedRooms;
    }

    public BookingBindingModel setBookedRooms(List<RoomBindingModel> bookedRooms) {
        this.bookedRooms = bookedRooms;
        return this;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public BookingBindingModel setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        return this;
    }

        public Long getHotelId() {
        return hotelId;
    }

    public BookingBindingModel setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }
}
