//package com.example.onlinehotelbookingsystem.web;
//
//import com.example.onlinehotelbookingsystem.model.binding.BookingBindingModel;
//import com.example.onlinehotelbookingsystem.service.BookingService;
//import com.example.onlinehotelbookingsystem.security.CustomUser;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//public class RoomAvailabilityRestController {
//
//    private final ModelMapper mapper;
//    private final BookingService bookingService;
//
//    public RoomAvailabilityRestController(ModelMapper mapper, BookingService bookingService) {
//        this.mapper = mapper;
//        this.bookingService = bookingService;
//    }
//
//
//    @GetMapping("/check-availability")
//    public ResponseEntity<?> checkAvailability(@Valid @RequestBody BookingBindingModel bindingModel,
//                                               @AuthenticationPrincipal CustomUser user) {
//
//        this.bookingService.checkAvailability(bindingModel.getCheckIn(), bindingModel.getCheckOut(), bindingModel.getHotelId());
//
//        return null;
//
//
//
//
//    }
//}
