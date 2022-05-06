package com.example.onlinehotelbookingsystem.model.entity;


import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "properties")
public class AccommodationEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int category;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    private String address;

    @Column(nullable = false)
    private Integer postalCode;

    private String lat;
    private String lng;

    //    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String imageUrl;

    private LocalTime checkInTime;


    private LocalTime checkOutTime;

    @OneToMany(mappedBy = "accommodationEntity", cascade = CascadeType.ALL)
    private List<RoomEntity> roomEntity;

    //    @ManyToOne(cascade = CascadeType.ALL)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccommodationTypeEnum type;

    public AccommodationEntity() {
    }

    public String getName() {
        return name;
    }

    public AccommodationEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public AccommodationEntity setCategory(int category) {
        this.category = category;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AccommodationEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccommodationEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public AccommodationTypeEnum getType() {
        return type;
    }

    public AccommodationEntity setType(AccommodationTypeEnum type) {
        this.type = type;
        return this;
    }

    //    public AccommodationTypeEntity getType() {
//        return type;
//    }
//
//    public AccommodationEntity setType(AccommodationTypeEntity type) {
//        this.type = type;
//        return this;
//    }

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

    public String getLat() {
        return lat;
    }

    public AccommodationEntity setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public AccommodationEntity setLng(String lng) {
        this.lng = lng;
        return this;
    }
}
