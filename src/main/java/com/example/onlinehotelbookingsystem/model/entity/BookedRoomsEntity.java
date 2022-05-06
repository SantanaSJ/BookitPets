package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "booked_rooms")
public class BookedRoomsEntity extends BaseEntity {

//when create booking
//because without this I was getting an error
// object references an unsaved transient instance - save the transient instance before flushing
//cascde type all from parent didnt solve the problem
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BookingEntity booking;

    @ManyToOne
    private RoomEntity room;

    private BigDecimal price;
//    ??
    private Integer numberOfRooms;
//    private Integer numberOfPeople;


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
