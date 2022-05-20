package com.example.onlinehotelbookingsystem.model.binding;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RoomBindingModel {
    private Long id;


    @NotNull
    private Integer numberOfRooms;
    private String type;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public RoomBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public RoomBindingModel setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    public String getType() {
        return type;
    }

    public RoomBindingModel setType(String type) {
        this.type = type;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public RoomBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

}
