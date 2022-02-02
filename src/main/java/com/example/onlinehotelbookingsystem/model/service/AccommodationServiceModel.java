package com.example.onlinehotelbookingsystem.model.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AccommodationServiceModel {

    private Long id;
    private String name;
    private String category;
    private String city;
    private String address;
    private String postalCode;
    private String imageUrl;
    private List<RoomServiceModel> rooms = new ArrayList<>();
    private String type;
    private String cancellationPolicy;
    private String paymentPolicy;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;


    public AccommodationServiceModel() {
    }

    public String getName() {
        return name;
    }

    public AccommodationServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public AccommodationServiceModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AccommodationServiceModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AccommodationServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AccommodationServiceModel setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccommodationServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public AccommodationServiceModel setType(String type) {
        this.type = type;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AccommodationServiceModel setId(Long id) {
        this.id = id;
        return this;
    }


    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public AccommodationServiceModel setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
        return this;
    }

    public String getPaymentPolicy() {
        return paymentPolicy;
    }

    public AccommodationServiceModel setPaymentPolicy(String paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
        return this;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public AccommodationServiceModel setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
        return this;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public AccommodationServiceModel setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
        return this;
    }

    public List<RoomServiceModel> getRooms() {
        return rooms;
    }

    public AccommodationServiceModel setRooms(List<RoomServiceModel> rooms) {
        this.rooms = rooms;
        return this;
    }
}
