package com.example.onlinehotelbookingsystem.model.service;

import java.math.BigDecimal;

public class RoomServiceModel {

    private Long roomId;

    private Integer numberOfRooms;
    private String type;

    private BigDecimal price;

    public RoomServiceModel() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public RoomServiceModel setRoomId(Long roomId) {
        this.roomId = roomId;
        return this;
    }

    public String getType() {
        return type;
    }

    public RoomServiceModel setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public RoomServiceModel setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public RoomServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
