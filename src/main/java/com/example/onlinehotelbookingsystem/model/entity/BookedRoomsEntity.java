package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "booked_rooms")
public class BookedRoomsEntity extends BaseEntity {

//when create booking
//because without this I was getting an error
// object references an unsaved transient instance - save the transient instance before flushing
//cascde type all from parent didnt solve the problem
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private BookingEntity booking;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RoomEntity room;

    private BigDecimal price;
//    ??
    private Integer numberOfRooms;


    public BookingEntity getBooking() {
        return booking;
    }

    public BookedRoomsEntity setBooking(BookingEntity booking) {
        this.booking = booking;
        return this;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public BookedRoomsEntity setRoom(RoomEntity room) {
        this.room = room;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookedRoomsEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public BookedRoomsEntity setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

}
