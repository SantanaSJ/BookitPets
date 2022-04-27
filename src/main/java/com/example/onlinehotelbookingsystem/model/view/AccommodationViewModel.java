package com.example.onlinehotelbookingsystem.model.view;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AccommodationViewModel {

    private Long id;
    private String name;
    private int category;
    private String city;
    private String address;
    private Integer postalCode;
    private String imageUrl;
    private String description;
    private String lat;
    private String lng;

    private List<RoomViewModel> rooms = new ArrayList<>();
//    to change
    private String type;

//    private String cancellationPolicy;
//    private String paymentPolicy;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

    public AccommodationViewModel() {
    }

    public String getName() {
        return name;
    }

    public AccommodationViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public AccommodationViewModel setCategory(int category) {
        this.category = category;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AccommodationViewModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AccommodationViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public AccommodationViewModel setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccommodationViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public AccommodationViewModel setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "'" + name + '\'' +
                ", category '" + category + '\'' +
                ", city '" + city + '\'' +
                ", address '" + address + '\'' +
                ", type " + type + '\'';
    }

    public Long getId() {
        return id;
    }

    public AccommodationViewModel setId(Long id) {
        this.id = id;
        return this;
    }
//
//    public String getCancellationPolicy() {
//        return cancellationPolicy;
//    }
//
//    public AccommodationViewModel setCancellationPolicy(String cancellationPolicy) {
//        this.cancellationPolicy = cancellationPolicy;
//        return this;
//    }

//    public String getPaymentPolicy() {
//        return paymentPolicy;
//    }
//
//    public AccommodationViewModel setPaymentPolicy(String paymentPolicy) {
//        this.paymentPolicy = paymentPolicy;
//        return this;
//    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public AccommodationViewModel setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
        return this;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public AccommodationViewModel setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
        return this;
    }

    public List<RoomViewModel> getRooms() {
        return rooms;
    }

    public AccommodationViewModel setRooms(List<RoomViewModel> rooms) {
        this.rooms = rooms;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccommodationViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public AccommodationViewModel setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public AccommodationViewModel setLng(String lng) {
        this.lng = lng;
        return this;
    }
}
