package com.example.onlinehotelbookingsystem.scheduler;

import com.example.onlinehotelbookingsystem.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SetBookingAsComplete {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetBookingAsComplete.class);

    private final BookingService bookingService;

    public SetBookingAsComplete(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //    @Scheduled(cron = "* 0 22 * * 7")
    @Scheduled(cron = "00 35 17 * * *")
    private void moveCompletedBookings() {
        this.bookingService.setBookingAsComplete();
        LOGGER.info("Set successfully at {}", LocalDateTime.now());
    }
}
