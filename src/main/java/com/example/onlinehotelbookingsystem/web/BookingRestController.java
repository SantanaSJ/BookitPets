package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.binding.AvailabilityBindingModel;
import com.example.onlinehotelbookingsystem.model.binding.RoomBindingModel;
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

//    @GetMapping("/room-availability")
//    public ResponseEntity<?> check(@Valid AvailabilityBindingModel bindingModel, BindingResult br) {
//        if (br.hasErrors() || isRoomListEmpty(bindingModel)) {
//            System.out.println();
//            return ResponseEntity.badRequest().body(new Message("Message!"));
//        }
//        AvailabilityServiceModel serviceModel = this.mapper.map(bindingModel, AvailabilityServiceModel.class);
//        RoomMessages roomMessages = this.bookingService.checkAvailability(serviceModel);
//
//        if (!roomMessages.getNoRoomsMessage().isEmpty()) {
//
//////            rAtt
////                    .addFlashAttribute("messages", roomMessages)
////                    .addFlashAttribute("bindingModel", bindingModel);
////
////            return "redirect:/booking-form/accommodation/" + bindingModel.getHotelId();
//        }
//
//        rAtt
//                .addFlashAttribute("successMessage", roomMessages)
//                .addFlashAttribute("bindingModel", bindingModel);
//        return ResponseEntity.ok(bindingModel);
//    }

    private boolean isRoomListEmpty(AvailabilityBindingModel bindingModel) {
//         will return true if the list contains a 0
//        bindingModel.getRooms().stream().map(roomBindingModel -> roomBindingModel.getNumberOfRooms()).anyMatch(e -> e == 0);

        boolean flag = false;
        for (RoomBindingModel room : bindingModel.getRooms()) {
            if (room.getNumberOfRooms() == 0) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
