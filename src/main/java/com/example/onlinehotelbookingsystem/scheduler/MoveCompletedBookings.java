package com.example.onlinehotelbookingsystem.scheduler;

import com.example.onlinehotelbookingsystem.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MoveCompletedBookings {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoveCompletedBookings.class);

    private final BookingService bookingService;

    public MoveCompletedBookings(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //    @Scheduled(cron = "* 0 22 * * 7")
    @Scheduled(cron = "0 30 21 * * *")
    private void moveCompletedBookings() {
        this.bookingService.moveCompletedBookingsToHistory();
        LOGGER.info("Moved successfully at {}", LocalDateTime.now());
    }
}
