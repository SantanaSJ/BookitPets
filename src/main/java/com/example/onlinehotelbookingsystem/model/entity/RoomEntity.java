package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
public class RoomEntity extends BaseEntity {

    @ManyToOne
    private AccommodationEntity accommodationEntity;

    @Column(nullable = false)
    private String roomType;

//    private int free;

    private BigDecimal currentPrice;


    public RoomEntity() {
    }

    public String getRoomType() {
        return roomType;
    }

    public RoomEntity setRoomType(String roomType) {
        this.roomType = roomType;
        return this;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public RoomEntity setCurrentPrice(BigDecimal price) {
        this.currentPrice = price;
        return this;
    }

    public AccommodationEntity getAccommodationEntity() {
        return accommodationEntity;
    }

    public RoomEntity setAccommodationEntity(AccommodationEntity accommodationEntity) {
        this.accommodationEntity = accommodationEntity;
        return this;
    }
}
