package com.example.onlinehotelbookingsystem.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingBindingModel {


    private Long hotelId;

    @NotBlank(message = "First name is required!")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters.")
    private String lastName;

    @NotBlank(message = "Pet name is required!")
    @Size(min = 3, max = 10, message = "Pet name must be between 3 and 10 characters.")
    private String petName;

    @NotNull(message = "Pet kilograms are required!")
    @Positive
    private Integer petKilograms;

    @NotBlank(message = "Email is required!")
    @Pattern(regexp = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$", message = "Please enter a valid email!")
    private String email;

    @NotBlank(message = "Phone number is required!")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number should be in the format: 0884444333")
    private String phoneNumber;

    private String comments;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;
    private List<RoomBindingModel> bookedRooms = new ArrayList<>();

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

//    public Integer getNumberOfPeople() {
//        return numberOfPeople;
//    }
//
//    public BookingBindingModel setNumberOfPeople(Integer numberOfPeople) {
//        this.numberOfPeople = numberOfPeople;
//        return this;
//    }

        public Long getHotelId() {
        return hotelId;
    }

    public BookingBindingModel setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BookingBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

//    public AddPetImageBindingModel getAddPetImageBindingModel() {
//        return addPetImageBindingModel;
//    }
//
//    public BookingBindingModel setAddPetImageBindingModel(AddPetImageBindingModel addPetImageBindingModel) {
//        this.addPetImageBindingModel = addPetImageBindingModel;
//        return this;
//    }

//    public String getPetImageUrl() {
//        return petImageUrl;
//    }
//
//    public BookingBindingModel setPetImageUrl(String petImageUrl) {
//        this.petImageUrl = petImageUrl;
//        return this;
//    }
}
