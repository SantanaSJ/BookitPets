package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.service.*;
import com.example.onlinehotelbookingsystem.web.responseMessages.RoomMessages;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingService {

    Long createBooking(CreateBookingServiceModel serviceModel, Long userId);

    RoomMessages checkAvailability(AvailabilityServiceModel dto);

    List<TitleBookingServiceModel> findAllActiveBookingsByCurrentUserId(Long userId);

    void update(BookingUpdateServiceModel updateServiceModel);

    SummaryBookingServiceModel findActiveBookingById(Long bookingId);

    BookingUpdateServiceModel findBookingUpdateServiceById(Long bookingId);

//    void delete(Long id);

    boolean isOwner(String userEmail, Long bookingId);

    void countBookings(Long id);

    Page<SummaryBookingServiceModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

    void setBookingAsComplete();

//    BookingServiceModel findBookingById(Long id);

    void setPaymentStatus(Long bookingId);

    void setBookingAsCancelled(Long id);


    Page<SummaryBookingServiceModel> findPaginatedPassedBookings(int pageNo, int pageSize, String sortField, String sortDir);

    SummaryBookingServiceModel findPassedBookingById(Long id);

    List<TitleBookingServiceModel> findAllPassedBookingsByUserId(Long userId);

    boolean isOwnerHistory(String currentUserEmail, Long id);

//    SummaryBookingServiceModel findCompletedBookingBy(Long id);
}
