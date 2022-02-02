//package com.example.onlinehotelbookingsystem.web;
//
//
//import com.example.onlinehotelbookingsystem.model.binding.BookingBindingModel;
//import com.example.onlinehotelbookingsystem.model.binding.RoomBindingModel;
//import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
//import com.example.onlinehotelbookingsystem.service.AccommodationService;
//import com.example.onlinehotelbookingsystem.service.BookingService;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.time.LocalDate;
//
//@org.springframework.stereotype.Controller
//public class ControllerNOTWORKING {
//
//    private final AccommodationService accService;
//    private final BookingService bookingService;
//
//    public ControllerNOTWORKING(AccommodationService accService, BookingService bookingService) {
//        this.accService = accService;
//        this.bookingService = bookingService;
//    }
//
//    @GetMapping("/booking-form")
//    public String show(Model model) {
//
//
//
//        if (!model.containsAttribute("roomBindingModel")) {
//            model.addAttribute("roomBindingModel", new RoomBindingModel());
//        }
//        return "booking-formNOT-WORKING-WITH-CONTROLLER";
//    }
//
//
//
//    @PostMapping("save")
//    public String save(BookingBindingModel bindingModel) {
//
//        LocalDate checkIn = bindingModel.getCheckIn();
//        return "details";
//    }
//
//    @ModelAttribute("bindingModel")
//    public BookingBindingModel bindingModel() {
//        return new BookingBindingModel();
//    }
//
//    @PostMapping("/addRoom")
//    public String addContact(BookingBindingModel bindingModel, Model model) {
//
//       this.bookingService.addRoom(bindingModel);
//        AccommodationViewModel viewModels = this.accService.findById(1L);
//
//        model.addAttribute("viewModels", viewModels);
//        return "booking-form :: rooms"; // returning the updated section
//    }
//}
