package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking_history")
public class BookingHistoryEntity extends BaseEntity {

    @OneToMany(mappedBy = "bookingHistory")
    private List<RoomsHistoryEntity> roomsHistoryEntity = new ArrayList<>();

    @ManyToOne
    private UserEntity guest;

    @ManyToOne
    private AccommodationEntity property;

    private String firstName;
    private String lastName;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime bookingTime;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updated;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate checkIn;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate checkOut;
//    ??
    private Long deletedBookingId;

    private BigDecimal totalPrice;
    private long totalNights;
    private String comments;
    private Integer petKilograms;
    private String petName;
    private String email;
    private String phoneNumber;
    private String lat;
    private String lng;
    @Column(columnDefinition="tinyint(1) default 1")
    private boolean isCompleted;
    @Column(columnDefinition="tinyint(1) default 1")
    private boolean isCancelled;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime cancelledOn;

    @OneToOne
    private PaymentEntity payment;

    public UserEntity getGuest() {
        return guest;
    }

    public BookingHistoryEntity setGuest(UserEntity guest) {
        this.guest = guest;
        return this;
    }

    public AccommodationEntity getProperty() {
        return property;
    }

    public BookingHistoryEntity setProperty(AccommodationEntity property) {
        this.property = property;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BookingHistoryEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BookingHistoryEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingHistoryEntity setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
        return this;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public BookingHistoryEntity setUpdated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public BookingHistoryEntity setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BookingHistoryEntity setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BookingHistoryEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public long getTotalNights() {
        return totalNights;
    }

    public BookingHistoryEntity setTotalNights(long totalNights) {
        this.totalNights = totalNights;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public BookingHistoryEntity setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public BookingHistoryEntity setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public BookingHistoryEntity setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookingHistoryEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BookingHistoryEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public BookingHistoryEntity setPayment(PaymentEntity payment) {
        this.payment = payment;
        return this;
    }

    public List<RoomsHistoryEntity> getRoomsHistoryEntity() {
        return roomsHistoryEntity;
    }

    public BookingHistoryEntity setRoomsHistoryEntity(List<RoomsHistoryEntity> roomsHistoryEntity) {
        this.roomsHistoryEntity = roomsHistoryEntity;
        return this;
    }

    public Long getDeletedBookingId() {
        return deletedBookingId;
    }

    public BookingHistoryEntity setDeletedBookingId(Long deletedBookingId) {
        this.deletedBookingId = deletedBookingId;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public BookingHistoryEntity setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public BookingHistoryEntity setCancelled(boolean cancelled) {
        isCancelled = cancelled;
        return this;
    }

    public LocalDateTime getCancelledOn() {
        return cancelledOn;
    }

    public BookingHistoryEntity setCancelledOn(LocalDateTime cancelledOn) {
        this.cancelledOn = cancelledOn;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public BookingHistoryEntity setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public BookingHistoryEntity setLng(String lng) {
        this.lng = lng;
        return this;
    }
}
