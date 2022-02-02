package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.service.SummaryBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.service.TitleBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.view.SummaryBookingViewModel;
import com.example.onlinehotelbookingsystem.model.view.TitleBookingViewModel;
import com.example.onlinehotelbookingsystem.security.CustomUser;
import com.example.onlinehotelbookingsystem.service.BookingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<TitleBookingViewModel>> getAllBookingsByCurrentUserId(@AuthenticationPrincipal CustomUser user) {
        List<TitleBookingServiceModel> allBookings = this.bookingService.getAllBookingsByCurrentUserId(user.getUserId());

        List<TitleBookingViewModel> map = this.mapper.map(
                allBookings, new TypeToken<List<TitleBookingViewModel>>() {}.getType());
        return ResponseEntity.ok(map);
    }

//    @GetMapping("/last-booking")
//    public ResponseEntity<SummaryBookingViewModel> getLastBookingByCurrentUserId(@AuthenticationPrincipal CustomUser user) {
//
//        SummaryBookingServiceModel lastBooking = this.bookingService.getLastBookingByCurrentUserId(user.getUserId());
//        SummaryBookingViewModel viewModel = this.mapper.map(lastBooking, SummaryBookingViewModel.class);
//
//        return ResponseEntity.ok(viewModel);
//    }

    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {

        this.bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/view-bookings/update/{id}")
//    public ResponseEntity<BookingServiceModel>  update(@PathVariable Long id, @AuthenticationPrincipal CustomUser user) {
//        BookingServiceModel byId = this.bookingService.findById(id, user.getUserId());
//        return ResponseEntity.ok(byId);
//    }


//    @PatchMapping("update/{id}")
//    public ResponseEntity<BookingBindingModel> update (@PathVariable("id") Long bookingId,
//                                                       @RequestBody @Valid BookingBindingModel bindingModel,
//                                                       @AuthenticationPrincipal CustomUser user) {
//        BookingServiceModel serviceModel = this.mapper.map(bindingModel, BookingServiceModel.class);
//        serviceModel.setBookingId(bookingId);
//        Long updatedBookingId = this.bookingService.update(serviceModel);
//        return updatedBookingId != null ?
//                ResponseEntity.noContent().build()
//                : ResponseEntity.notFound().build();
//    }
}
