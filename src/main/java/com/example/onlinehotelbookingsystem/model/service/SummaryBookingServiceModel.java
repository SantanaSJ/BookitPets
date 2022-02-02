package com.example.onlinehotelbookingsystem.model.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SummaryBookingServiceModel {

    private Long bookingId;
    private String firstName;

    private String lastName;

    private String petName;

    private Integer petKilograms;
    private String email;
    private String comments;

    private LocalDate checkIn;
    private LocalDate checkOut;

    private Map<String,BigDecimal> roomTypesCostPerNight = new HashMap<>();

    private Integer numberOfPeople;
    private String category;
    private String hotelName;
    private String hotelImage;
    private String type;
    private String address;
    private String city;
//    private BigDecimal costPerNight;
    private BigDecimal totalPrice;
    private long totalNights;

    public String getFirstName() {
        return firstName;
    }

    public SummaryBookingServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SummaryBookingServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public SummaryBookingServiceModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public SummaryBookingServiceModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SummaryBookingServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public SummaryBookingServiceModel setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public SummaryBookingServiceModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public SummaryBookingServiceModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public Map<String, BigDecimal> getRoomTypesCostPerNight() {
        return roomTypesCostPerNight;
    }

    public SummaryBookingServiceModel setRoomTypesCostPerNight(Map<String, BigDecimal> roomTypesCostPerNight) {
        this.roomTypesCostPerNight = roomTypesCostPerNight;
        return this;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public SummaryBookingServiceModel setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SummaryBookingServiceModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public SummaryBookingServiceModel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getType() {
        return type;
    }

    public SummaryBookingServiceModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SummaryBookingServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public SummaryBookingServiceModel setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public long getTotalNights() {
        return totalNights;
    }

    public SummaryBookingServiceModel setTotalNights(long totalNights) {
        this.totalNights = totalNights;
        return this;
    }

    public String getCity() {
        return city;
    }

    public SummaryBookingServiceModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public SummaryBookingServiceModel setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public SummaryBookingServiceModel setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }
}
