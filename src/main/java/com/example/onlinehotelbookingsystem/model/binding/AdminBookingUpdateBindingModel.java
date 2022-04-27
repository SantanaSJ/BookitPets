//package com.example.onlinehotelbookingsystem.model.binding;
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
//public class AdminBookingUpdateBindingModel {
//
//    private Long bookingId;
//    @NotBlank
//    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 characters.")
//    private String firstName;
//    @NotBlank
//    @Size(min = 3, max = 20, message = "Last name length must be between 3 and 20 characters.")
//    private String lastName;
//    @NotBlank
//    private String petName;
//    @NotNull
//    private Integer petKilograms;
//    @NotBlank
//    private String email;
//    private String comments;
//    @NotBlank
//    private String  phoneNumber;
//
//
//    private LocalDate checkIn;
//    private LocalDate checkOut;
//    private LocalDateTime cancelledOn;
//    private Map<String, BigDecimal> roomTypesCostPerNight = new HashMap<>();
//    private int category;
//    private String hotelName;
//    private String hotelImage;
//    private String type;
//    private String address;
//    private String city;
//    private BigDecimal totalPrice;
//    private long totalNights;
//    private LocalDateTime lastUpdated;
//    private String paymentStatus;
//    private boolean isCompleted;
//
//    public Long getBookingId() {
//        return bookingId;
//    }
//
//    public AdminBookingUpdateBindingModel setBookingId(Long bookingId) {
//        this.bookingId = bookingId;
//        return this;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public AdminBookingUpdateBindingModel setFirstName(String firstName) {
//        this.firstName = firstName;
//        return this;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public AdminBookingUpdateBindingModel setLastName(String lastName) {
//        this.lastName = lastName;
//        return this;
//    }
//
//    public String getPetName() {
//        return petName;
//    }
//
//    public AdminBookingUpdateBindingModel setPetName(String petName) {
//        this.petName = petName;
//        return this;
//    }
//
//    public Integer getPetKilograms() {
//        return petKilograms;
//    }
//
//    public AdminBookingUpdateBindingModel setPetKilograms(Integer petKilograms) {
//        this.petKilograms = petKilograms;
//        return this;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public AdminBookingUpdateBindingModel setEmail(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public AdminBookingUpdateBindingModel setComments(String comments) {
//        this.comments = comments;
//        return this;
//    }
//
//    public LocalDate getCheckIn() {
//        return checkIn;
//    }
//
//    public AdminBookingUpdateBindingModel setCheckIn(LocalDate checkIn) {
//        this.checkIn = checkIn;
//        return this;
//    }
//
//    public LocalDate getCheckOut() {
//        return checkOut;
//    }
//
//    public AdminBookingUpdateBindingModel setCheckOut(LocalDate checkOut) {
//        this.checkOut = checkOut;
//        return this;
//    }
//
////    public LocalDate getCancellationDate() {
////        return cancellationDate;
////    }
////
////    public AdminBookingUpdateBindingModel setCancellationDate(LocalDate cancellationDate) {
////        this.cancellationDate = cancellationDate;
////        return this;
////    }
//
//    public LocalDateTime getCancelledOn() {
//        return cancelledOn;
//    }
//
//    public AdminBookingUpdateBindingModel setCancelledOn(LocalDateTime cancelledOn) {
//        this.cancelledOn = cancelledOn;
//        return this;
//    }
//
//    public Map<String, BigDecimal> getRoomTypesCostPerNight() {
//        return roomTypesCostPerNight;
//    }
//
//    public AdminBookingUpdateBindingModel setRoomTypesCostPerNight(Map<String, BigDecimal> roomTypesCostPerNight) {
//        this.roomTypesCostPerNight = roomTypesCostPerNight;
//        return this;
//    }
//
//    public int getCategory() {
//        return category;
//    }
//
//    public AdminBookingUpdateBindingModel setCategory(int category) {
//        this.category = category;
//        return this;
//    }
//
//    public String getHotelName() {
//        return hotelName;
//    }
//
//    public AdminBookingUpdateBindingModel setHotelName(String hotelName) {
//        this.hotelName = hotelName;
//        return this;
//    }
//
//    public String getHotelImage() {
//        return hotelImage;
//    }
//
//    public AdminBookingUpdateBindingModel setHotelImage(String hotelImage) {
//        this.hotelImage = hotelImage;
//        return this;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public AdminBookingUpdateBindingModel setType(String type) {
//        this.type = type;
//        return this;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public AdminBookingUpdateBindingModel setAddress(String address) {
//        this.address = address;
//        return this;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public AdminBookingUpdateBindingModel setCity(String city) {
//        this.city = city;
//        return this;
//    }
//
//    public BigDecimal getTotalPrice() {
//        return totalPrice;
//    }
//
//    public AdminBookingUpdateBindingModel setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//        return this;
//    }
//
//    public long getTotalNights() {
//        return totalNights;
//    }
//
//    public AdminBookingUpdateBindingModel setTotalNights(long totalNights) {
//        this.totalNights = totalNights;
//        return this;
//    }
//
//    public LocalDateTime getLastUpdated() {
//        return lastUpdated;
//    }
//
//    public AdminBookingUpdateBindingModel setLastUpdated(LocalDateTime lastUpdated) {
//        this.lastUpdated = lastUpdated;
//        return this;
//    }
//
//    public String getPaymentStatus() {
//        return paymentStatus;
//    }
//
//    public AdminBookingUpdateBindingModel setPaymentStatus(String paymentStatus) {
//        this.paymentStatus = paymentStatus;
//        return this;
//    }
//
//    public boolean isCompleted() {
//        return isCompleted;
//    }
//
//    public AdminBookingUpdateBindingModel setCompleted(boolean completed) {
//        isCompleted = completed;
//        return this;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public AdminBookingUpdateBindingModel setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//        return this;
//    }
//}
