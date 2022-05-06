package com.example.onlinehotelbookingsystem.model.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AccommodationServiceModel {

    private Long id;
    private String name;
    private int category;
    private String city;
    private String address;
    private String postalCode;
    private String imageUrl;
    private String description;

    private String lat;
    private String lng;


    private List<RoomServiceModel> rooms = new ArrayList<>();
    private String type;

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

    public int getCategory() {
        return category;
    }

    public AccommodationServiceModel setCategory(int category) {
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

    public String getDescription() {
        return description;
    }

    public AccommodationServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public AccommodationServiceModel setLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public AccommodationServiceModel setLat(String lat) {
        this.lat = lat;
        return this;
    }
}
