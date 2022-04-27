package com.example.onlinehotelbookingsystem.event;

import com.example.onlinehotelbookingsystem.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewBookingEvent {

    private final BookingService bookingService;

    private final Logger LOGGER = LoggerFactory.getLogger(NewBookingEvent.class);

    public NewBookingEvent(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @EventListener(BookingCreatedEvent.class)
    public void onBookingCreated(BookingCreatedEvent bookingCreatedEvent) {
        LOGGER.info("Event triggered for booking {}", bookingCreatedEvent.getId());



        this.bookingService.countBookings(bookingCreatedEvent.getId());
    }
}
