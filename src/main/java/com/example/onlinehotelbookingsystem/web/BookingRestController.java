package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.service.TitleBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.view.TitleBookingViewModel;
import com.example.onlinehotelbookingsystem.security.CustomUser;
import com.example.onlinehotelbookingsystem.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookingRestController {
    private final BookingService bookingService;
    private final ModelMapper mapper;

    public BookingRestController(BookingService bookingService, ModelMapper mapper) {
        this.bookingService = bookingService;
        this.mapper = mapper;
    }

    @GetMapping("/all-bookings")
//    user?
    public ResponseEntity<List<TitleBookingViewModel>> getAllBookingsByCurrentUserId(@AuthenticationPrincipal CustomUser user)     {

        List<TitleBookingServiceModel> allBookings = this.bookingService
                .findAllActiveBookingsByCurrentUserId(user.getUserId());

        List<TitleBookingViewModel> collect = allBookings
                .stream()
                .map(b -> this.mapper.map(b, TitleBookingViewModel.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }

    @PreAuthorize("isOwner(#id) or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable("id") Long id) {

        this.bookingService.setBookingAsCancelled(id);

//        this.bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
