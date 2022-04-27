package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
public class RoomEntity extends BaseEntity {

//    tests
    @JoinColumn(nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
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
