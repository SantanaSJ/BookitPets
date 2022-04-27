package com.example.onlinehotelbookingsystem.model.service;

import com.example.onlinehotelbookingsystem.model.view.RoomViewModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummaryBookingServiceModel {

    private Long bookingId;
    private String firstName;

    private String lastName;

    private String petName;

    private Integer petKilograms;
    private String email;
    private String phoneNumber;
    private String comments;

    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDateTime cancelledOn;
    private List<RoomServiceModel> rooms;

    private int category;
    private String hotelName;
    private String hotelImage;
    private String type;
    private String address;
    private String city;
    private String phone;
    private BigDecimal totalPrice;
    private long totalNights;
    private LocalDateTime lastUpdated;
    private String paymentStatus;
    private boolean isCancelled;
    private boolean hasDiscount;

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

    public int getCategory() {
        return category;
    }

    public SummaryBookingServiceModel setCategory(int category) {
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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public SummaryBookingServiceModel setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

//    public LocalDate getCancellationDate() {
//        return cancellationDate;
//    }
//
//    public SummaryBookingServiceModel setCancellationDate(LocalDate cancellationDate) {
//        this.cancellationDate = cancellationDate;
//        return this;
//    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public SummaryBookingServiceModel setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public LocalDateTime getCancelledOn() {
        return cancelledOn;
    }

    public SummaryBookingServiceModel setCancelledOn(LocalDateTime cancelledOn) {
        this.cancelledOn = cancelledOn;
        return this;
    }

//    public boolean isCompleted() {
//        return isCompleted;
//    }
//
//    public SummaryBookingServiceModel setCompleted(boolean completed) {
//        isCompleted = completed;
//        return this;
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SummaryBookingServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public SummaryBookingServiceModel setCancelled(boolean cancelled) {
        isCancelled = cancelled;
        return this;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public SummaryBookingServiceModel setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
        return this;
    }

    public List<RoomServiceModel> getRooms() {
        return rooms;
    }

    public SummaryBookingServiceModel setRooms(List<RoomServiceModel> rooms) {
        this.rooms = rooms;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public SummaryBookingServiceModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
