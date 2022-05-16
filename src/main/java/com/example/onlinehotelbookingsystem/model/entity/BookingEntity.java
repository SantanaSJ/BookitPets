package com.example.onlinehotelbookingsystem.model.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "bookings")
public class BookingEntity extends BaseEntity {

    @ManyToOne
    private UserEntity guest;

    @ManyToOne
    private AccommodationEntity property;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookedRoomsEntity> bookedRooms = new ArrayList<>();

    private String firstName;
    private String lastName;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime bookingTime;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updated;

    @CreationTimestamp
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate checkIn;
    @CreationTimestamp
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate checkOut;

    private BigDecimal totalPrice;
    private long totalNights;
    private String comments;
    private Integer petKilograms;
    private String petName;
    private String email;
    private String phoneNumber;
    private int countBookings;
    private boolean hasDiscount;
    @OneToOne
    private PaymentEntity payment;


    public BookingEntity() {
    }


    public UserEntity getGuest() {
        return guest;
    }

    public BookingEntity setGuest(UserEntity guest) {
        this.guest = guest;
        return this;
    }

    public AccommodationEntity getProperty() {
        return property;
    }

    public BookingEntity setProperty(AccommodationEntity property) {
        this.property = property;
        return this;
    }

    public List<BookedRoomsEntity> getBookedRooms() {
        return bookedRooms;
    }

    public BookingEntity setBookedRooms(List<BookedRoomsEntity> bookedRooms) {
        this.bookedRooms = bookedRooms;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BookingEntity setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public BookingEntity setPayment(PaymentEntity payment) {
        this.payment = payment;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public BookingEntity setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public BookingEntity setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public BookingEntity setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookingEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public BookingEntity setUpdated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingEntity setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BookingEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BookingEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public BookingEntity setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BookingEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public long getTotalNights() {
        return totalNights;
    }

    public BookingEntity setTotalNights(long totalNights) {
        this.totalNights = totalNights;
        return this;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BookingEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public int getCountBookings() {
        return countBookings;
    }

    public BookingEntity setCountBookings(int countBookings) {
        this.countBookings = countBookings;
        return this;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public BookingEntity setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
        return this;
    }


}
