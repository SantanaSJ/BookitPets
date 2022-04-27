package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms_history")
public class RoomsHistoryEntity extends BaseEntity {

    @ManyToOne
    private BookingHistoryEntity bookingHistory;

//    @ManyToOne
//    private CancelledBookingEntity cancelledBooking;

    @ManyToOne
    private RoomEntity room;

    private BigDecimal price;
    private Integer numberOfRooms;


    public RoomEntity getRoom() {
        return room;
    }

    public RoomsHistoryEntity setRoom(RoomEntity room) {
        this.room = room;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public RoomsHistoryEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public RoomsHistoryEntity setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    public BookingHistoryEntity getBookingHistory() {
        return bookingHistory;
    }

    public RoomsHistoryEntity setBookingHistory(BookingHistoryEntity bookingHistory) {
        this.bookingHistory = bookingHistory;
        return this;
    }
}

