//package com.example.onlinehotelbookingsystem.scheduler;
//
//import com.example.onlinehotelbookingsystem.service.CancelledBookingService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public class CleanUpCancelledBookingsScheduler {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(CleanUpCancelledBookingsScheduler.class);
//
//    private final CancelledBookingService cancelledBookingService;
//
//    public CleanUpCancelledBookingsScheduler(CancelledBookingService cancelledBookingService) {
//        this.cancelledBookingService = cancelledBookingService;
//    }
//
//
////    @Scheduled(cron = "* 0 22 * * 7")
////    public void removeCancelledBookings() {
////        LOGGER.info("Bookings removed at {}", LocalDateTime.now());
////        this.cancelledBookingService.deleteAll();
////    }
//
//}
