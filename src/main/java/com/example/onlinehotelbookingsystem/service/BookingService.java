package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.errors.RoomMessages;
import com.example.onlinehotelbookingsystem.model.service.*;

import java.util.List;

public interface BookingService {

    SummaryBookingServiceModel createBooking(BookingServiceModel serviceModel, Long userId);

    RoomMessages checkAvailability(AvailabilityServiceModel dto);

    List<TitleBookingServiceModel> getAllBookingsByCurrentUserId(Long userId);

    SummaryBookingServiceModel getLastBookingByCurrentUserId(Long id);

    void update(BookingUpdateServiceModel updateServiceModel);

    SummaryBookingServiceModel findById(Long bookingId, Long userId);

    void delete(Long id);

    boolean isOwner(String userEmail, Long bookingId);

//    boolean isAvailableBetween(LocalDate checkIn, LocalDate checkOut, Long hotelId, List<RoomBindingModel> rooms);

//    void addRoom(BookingBindingModel bindingModel);

//    void setRooms(BookingBindingModel bindingModel);
}
