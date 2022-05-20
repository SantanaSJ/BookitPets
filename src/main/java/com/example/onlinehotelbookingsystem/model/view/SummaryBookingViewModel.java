package com.example.onlinehotelbookingsystem.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SummaryBookingViewModel {

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

    private List<RoomViewModel> rooms;

    private int category;
    private String hotelName;
    private String hotelImage;
    private String lat;
    private String lng;
    private String type;
    private String address;
    private String city;
    private String phone;
    private BigDecimal totalPrice;
    private long totalNights;
    private LocalDateTime lastUpdated;
    private boolean isCancelled;
    private boolean isCompleted;
    private boolean hasDiscount;
    private String paymentStatus;



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


    public int getCategory() {
        return category;
    }

    public SummaryBookingViewModel setCategory(int category) {
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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public SummaryBookingViewModel setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public SummaryBookingViewModel setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public LocalDateTime getCancelledOn() {
        return cancelledOn;
    }

    public SummaryBookingViewModel setCancelledOn(LocalDateTime cancelledOn) {
        this.cancelledOn = cancelledOn;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SummaryBookingViewModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public SummaryBookingViewModel setCancelled(boolean cancelled) {
        isCancelled = cancelled;
        return this;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

    public SummaryBookingViewModel setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
        return this;
    }

    public List<RoomViewModel> getRooms() {
        return rooms;
    }

    public SummaryBookingViewModel setRooms(List<RoomViewModel> rooms) {
        this.rooms = rooms;
        return this;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public String getPhone() {
        return phone;
    }

    public SummaryBookingViewModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public SummaryBookingViewModel setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public SummaryBookingViewModel setLng(String lng) {
        this.lng = lng;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public SummaryBookingViewModel setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }
}
