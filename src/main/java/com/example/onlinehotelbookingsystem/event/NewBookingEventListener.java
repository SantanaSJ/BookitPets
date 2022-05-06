package com.example.onlinehotelbookingsystem.event;

import com.example.onlinehotelbookingsystem.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewBookingEventListener {

    private final BookingService bookingService;

    private final Logger LOGGER = LoggerFactory.getLogger(NewBookingEventListener.class);

    public NewBookingEventListener(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @EventListener(BookingCreatedEvent.class)
    public void onCreateBooking(BookingCreatedEvent bookingCreatedEvent) {
        LOGGER.info("Event triggered for booking {}", bookingCreatedEvent.getId());
        this.bookingService.countBookings(bookingCreatedEvent.getId());
    }
}
