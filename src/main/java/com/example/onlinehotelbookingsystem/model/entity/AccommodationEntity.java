package com.example.onlinehotelbookingsystem.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "properties")
public class AccommodationEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String city;
    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    private String address;
    @Column(nullable = false)
    private Integer postalCode;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String imageUrl;

    @OneToMany(mappedBy = "accommodationEntity", cascade = CascadeType.ALL)
    private List<RoomEntity> roomEntity;

    private String cancellationPolicy;
    private String paymentPolicy;

    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private AccommodationTypeEntity type;
//
//    @OneToMany(mappedBy = "property")
//    private List<BookingEntity> bookings = new ArrayList<>();

//    @OneToMany(mappedBy = "property")
//    private List<RoomEntity> rooms = new ArrayList<>();

    public AccommodationEntity() {
    }

//    public boolean isAvailableBetween(LocalDate checkin, LocalDate checkout) {
//        return this.bookings.stream()
//                .noneMatch(b ->
//                        (b.isDateInsideCheckedIn(checkin) || b.isDateInsideCheckedIn(checkout))
//                                || (b.getCheckin().equals(checkin) && b.getCheckOut().equals(checkout)));
//    }

    public String getName() {
        return name;
    }

    public AccommodationEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public AccommodationEntity setCategory(String category) {
        this.category = category;
        return this;
    }


//    public List<RoomEntity> getRooms() {
//        return rooms;
//    }
//
//    public AccommodationEntity setRooms(List<RoomEntity> rooms) {
//        this.rooms = rooms;
//        return this;
//    }

    public String getCity() {
        return city;
    }

    public AccommodationEntity setCity(String city) {
        this.city = city;
        return this;
    }

//    public List<BookingEntity> getBookings() {
//        return bookings;
//    }
//
//    public AccommodationEntity setBookings(List<BookingEntity> bookings) {
//        this.bookings = bookings;
//        return this;
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccommodationEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public AccommodationTypeEntity getType() {
        return type;
    }

    public AccommodationEntity setType(AccommodationTypeEntity type) {
        this.type = type;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AccommodationEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public AccommodationEntity setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public AccommodationEntity setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
        return this;
    }

    public String getPaymentPolicy() {
        return paymentPolicy;
    }

    public AccommodationEntity setPaymentPolicy(String paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
        return this;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public AccommodationEntity setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
        return this;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public AccommodationEntity setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccommodationEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<RoomEntity> getRoomEntity() {
        return roomEntity;
    }

    public AccommodationEntity setRoomEntity(List<RoomEntity> roomEntity) {
        this.roomEntity = roomEntity;
        return this;
    }
}
