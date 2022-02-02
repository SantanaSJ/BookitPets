//package com.example.onlinehotelbookingsystem.web;
//
//import com.example.onlinehotelbookingsystem.model.service.TestDto;
//import com.example.onlinehotelbookingsystem.model.entity.RoomEntity;
//import com.example.onlinehotelbookingsystem.service.BookingService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class TestController {
//
//    private final BookingService bookingService;
//
//    public TestController(BookingService bookingService) {
//        this.bookingService = bookingService;
//    }
//
//    @GetMapping(value = "/create")
//    public String showCreateForm(Model model) {
//        TestDto dto = new TestDto();
//
////        for (int i = 1; i <= 3; i++) {
////            dto.addRoom(new RoomEntity());
////        }
//
//        model.addAttribute("form", dto);
//
//        return "testHtml";
//    }
//
//    @PostMapping(value = "/save")
//    public String saveBooks(@ModelAttribute TestDto form, Model model) {
//        this.bookingService.saveAll(form.getRooms());
//
//        model.addAttribute("books", this.bookingService.find(form.getHotelId()));
//
//        return "redirect:/books/all";
//    }
//}
