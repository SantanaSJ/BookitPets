//package com.example.onlinehotelbookingsystem.model.service;
//
//import com.example.onlinehotelbookingsystem.model.entity.RoomEntity;
//
//import javax.validation.constraints.Future;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Positive;
//import java.time.LocalDate;
//import java.util.List;
////create booking dto
//public class TestDto {
//
//
//    private Long hotelId;
//
//    @NotBlank(message = "First name is required!")
//    private String firstName;
//
//    @NotBlank(message = "Last name is required!")
//    private String lastName;
//
//    @NotBlank(message = "Pet name is required!")
//    private String petName;
//
//    @NotNull(message = "Pet kilograms are required!")
//    @Positive
//    private Integer petKilograms;
//
//    @NotNull(message = "Check in date is required!")
//    @Future
//    private LocalDate checkIn;
//    @Future
//    @NotNull(message = "Check out date is required!")
//    private LocalDate checkOut;
//
//    @NotNull(message = "Number of rooms is required!")
//    private List<RoomEntity> rooms;
//
//    @NotNull(message = "Number of people is required!")
//    private Integer numberOfPeople;
//
//    @NotBlank(message = "Email is required!")
//    private String email;
//    private String comments;
//
//    public Long getHotelId() {
//        return hotelId;
//    }
//
//    public TestDto setHotelId(Long hotelId) {
//        this.hotelId = hotelId;
//        return this;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public TestDto setFirstName(String firstName) {
//        this.firstName = firstName;
//        return this;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public TestDto setLastName(String lastName) {
//        this.lastName = lastName;
//        return this;
//    }
//
//    public String getPetName() {
//        return petName;
//    }
//
//    public TestDto setPetName(String petName) {
//        this.petName = petName;
//        return this;
//    }
//
//    public Integer getPetKilograms() {
//        return petKilograms;
//    }
//
//    public TestDto setPetKilograms(Integer petKilograms) {
//        this.petKilograms = petKilograms;
//        return this;
//    }
//
//    public LocalDate getCheckIn() {
//        return checkIn;
//    }
//
//    public TestDto setCheckIn(LocalDate checkIn) {
//        this.checkIn = checkIn;
//        return this;
//    }
//
//    public LocalDate getCheckOut() {
//        return checkOut;
//    }
//
//    public TestDto setCheckOut(LocalDate checkOut) {
//        this.checkOut = checkOut;
//        return this;
//    }
//
//    public List<RoomEntity> getRooms() {
//        return rooms;
//    }
//
//    public TestDto setRooms(List<RoomEntity> rooms) {
//        this.rooms = rooms;
//        return this;
//    }
//
//    public Integer getNumberOfPeople() {
//        return numberOfPeople;
//    }
//
//    public TestDto setNumberOfPeople(Integer numberOfPeople) {
//        this.numberOfPeople = numberOfPeople;
//        return this;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public TestDto setEmail(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public TestDto setComments(String comments) {
//        this.comments = comments;
//        return this;
//    }
////    public void addRoom(RoomEntity room) {
////        this.rooms.add(room);
////    }
//}
