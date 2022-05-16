package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
public class RoomEntity extends BaseEntity {

//    tests
//    @JoinColumn(nullable = false)
    @ManyToOne()
    private AccommodationEntity accommodationEntity;

    @Column(nullable = false)
    private String roomType;

    @Column(nullable = false)
    private BigDecimal currentPrice;

    @Column(nullable = false)
    private String description;


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

    public String getDescription() {
        return description;
    }

    public RoomEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
