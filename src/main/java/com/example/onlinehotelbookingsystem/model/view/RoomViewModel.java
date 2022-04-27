package com.example.onlinehotelbookingsystem.model.view;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RoomViewModel {

    private Long id;
    private String type;
    private BigDecimal price;

    private Integer numberOfRooms;

    private String description;

    public RoomViewModel() {
    }

    public Long getId() {
        return id;
    }

    public RoomViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public RoomViewModel setType(String type) {
        this.type = type;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public RoomViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public RoomViewModel setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoomViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
