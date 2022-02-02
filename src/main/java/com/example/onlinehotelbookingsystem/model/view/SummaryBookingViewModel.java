package com.example.onlinehotelbookingsystem.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SummaryBookingViewModel {

    private Long bookingId;

    private String firstName;

    private String lastName;

    private String petName;

    private Integer petKilograms;
    private String email;
    private String comments;

//    DateTimeFormatter.ISO_LOCAL_DATE.format(localDate)
    private LocalDate checkIn;
    private LocalDate checkOut;

    private Map<String, BigDecimal> roomTypesCostPerNight = new HashMap<>();

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

    public SummaryBookingViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SummaryBookingViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public SummaryBookingViewModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public SummaryBookingViewModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SummaryBookingViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public SummaryBookingViewModel setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public SummaryBookingViewModel setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public SummaryBookingViewModel setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }


    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public SummaryBookingViewModel setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SummaryBookingViewModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public SummaryBookingViewModel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getType() {
        return type;
    }

    public SummaryBookingViewModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SummaryBookingViewModel setAddress(String address) {
        this.address = address;
        return this;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public SummaryBookingViewModel setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Map<String, BigDecimal> getRoomTypesCostPerNight() {
        return roomTypesCostPerNight;
    }

    public SummaryBookingViewModel setRoomTypesCostPerNight(Map<String, BigDecimal> roomTypesCostPerNight) {
        this.roomTypesCostPerNight = roomTypesCostPerNight;
        return this;
    }

    public long getTotalNights() {
        return totalNights;
    }

    public SummaryBookingViewModel setTotalNights(long totalNights) {
        this.totalNights = totalNights;
        return this;
    }

    public String getCity() {
        return city;
    }

    public SummaryBookingViewModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public SummaryBookingViewModel setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public SummaryBookingViewModel setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }
}
