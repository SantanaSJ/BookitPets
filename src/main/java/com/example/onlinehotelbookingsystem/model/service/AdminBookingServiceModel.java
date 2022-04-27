//package com.example.onlinehotelbookingsystem.model.service;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.validation.constraints.Future;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//public class AdminBookingServiceModel {
//    private Long bookingId;
//
//    private String firstName;
//
//    private String lastName;
//
//    private String petName;
//
//    private Integer petKilograms;
//    private String email;
//    private String comments;
//
//    private LocalDate checkIn;
//
//    private LocalDate checkOut;
//    private LocalDate cancellationDate;
//    private LocalDateTime cancelledOn;
//
//    private Map<String, BigDecimal> roomTypesCostPerNight = new HashMap<>();
//
//    private int category;
//    private String hotelName;
//    private String hotelImage;
//    private String type;
//    private String address;
//    private String city;
//    private BigDecimal totalPrice;
//    private long totalNights;
//    private LocalDateTime lastUpdated;
//
//    private String paymentStatus;
//    private boolean isCompleted;
//
//    public Long getBookingId() {
//        return bookingId;
//    }
//
//    public AdminBookingServiceModel setBookingId(Long bookingId) {
//        this.bookingId = bookingId;
//        return this;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public AdminBookingServiceModel setFirstName(String firstName) {
//        this.firstName = firstName;
//        return this;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public AdminBookingServiceModel setLastName(String lastName) {
//        this.lastName = lastName;
//        return this;
//    }
//
//    public String getPetName() {
//        return petName;
//    }
//
//    public AdminBookingServiceModel setPetName(String petName) {
//        this.petName = petName;
//        return this;
//    }
//
//    public Integer getPetKilograms() {
//        return petKilograms;
//    }
//
//    public AdminBookingServiceModel setPetKilograms(Integer petKilograms) {
//        this.petKilograms = petKilograms;
//        return this;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public AdminBookingServiceModel setEmail(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public AdminBookingServiceModel setComments(String comments) {
//        this.comments = comments;
//        return this;
//    }
//
//    public LocalDate getCheckIn() {
//        return checkIn;
//    }
//
//    public AdminBookingServiceModel setCheckIn(LocalDate checkIn) {
//        this.checkIn = checkIn;
//        return this;
//    }
//
//    public LocalDate getCheckOut() {
//        return checkOut;
//    }
//
//    public AdminBookingServiceModel setCheckOut(LocalDate checkOut) {
//        this.checkOut = checkOut;
//        return this;
//    }
//
//    public LocalDate getCancellationDate() {
//        return cancellationDate;
//    }
//
//    public AdminBookingServiceModel setCancellationDate(LocalDate cancellationDate) {
//        this.cancellationDate = cancellationDate;
//        return this;
//    }
//
//    public LocalDateTime getCancelledOn() {
//        return cancelledOn;
//    }
//
//    public AdminBookingServiceModel setCancelledOn(LocalDateTime cancelledOn) {
//        this.cancelledOn = cancelledOn;
//        return this;
//    }
//
//    public Map<String, BigDecimal> getRoomTypesCostPerNight() {
//        return roomTypesCostPerNight;
//    }
//
//    public AdminBookingServiceModel setRoomTypesCostPerNight(Map<String, BigDecimal> roomTypesCostPerNight) {
//        this.roomTypesCostPerNight = roomTypesCostPerNight;
//        return this;
//    }
//
//    public int getCategory() {
//        return category;
//    }
//
//    public AdminBookingServiceModel setCategory(int category) {
//        this.category = category;
//        return this;
//    }
//
//    public String getHotelName() {
//        return hotelName;
//    }
//
//    public AdminBookingServiceModel setHotelName(String hotelName) {
//        this.hotelName = hotelName;
//        return this;
//    }
//
//    public String getHotelImage() {
//        return hotelImage;
//    }
//
//    public AdminBookingServiceModel setHotelImage(String hotelImage) {
//        this.hotelImage = hotelImage;
//        return this;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public AdminBookingServiceModel setType(String type) {
//        this.type = type;
//        return this;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public AdminBookingServiceModel setAddress(String address) {
//        this.address = address;
//        return this;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public AdminBookingServiceModel setCity(String city) {
//        this.city = city;
//        return this;
//    }
//
//    public BigDecimal getTotalPrice() {
//        return totalPrice;
//    }
//
//    public AdminBookingServiceModel setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//        return this;
//    }
//
//    public long getTotalNights() {
//        return totalNights;
//    }
//
//    public AdminBookingServiceModel setTotalNights(long totalNights) {
//        this.totalNights = totalNights;
//        return this;
//    }
//
//    public LocalDateTime getLastUpdated() {
//        return lastUpdated;
//    }
//
//    public AdminBookingServiceModel setLastUpdated(LocalDateTime lastUpdated) {
//        this.lastUpdated = lastUpdated;
//        return this;
//    }
//
//    public String getPaymentStatus() {
//        return paymentStatus;
//    }
//
//    public AdminBookingServiceModel setPaymentStatus(String paymentStatus) {
//        this.paymentStatus = paymentStatus;
//        return this;
//    }
//
//    public boolean isCompleted() {
//        return isCompleted;
//    }
//
//    public AdminBookingServiceModel setCompleted(boolean completed) {
//        isCompleted = completed;
//        return this;
//    }
//}
