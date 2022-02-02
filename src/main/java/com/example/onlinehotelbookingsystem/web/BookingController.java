package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.errors.RoomMessages;
import com.example.onlinehotelbookingsystem.model.binding.BookingBindingModel;
import com.example.onlinehotelbookingsystem.model.binding.AvailabilityBindingModel;
import com.example.onlinehotelbookingsystem.model.binding.BookingUpdateBindingModel;
import com.example.onlinehotelbookingsystem.model.binding.RoomBindingModel;
import com.example.onlinehotelbookingsystem.model.service.*;
import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
import com.example.onlinehotelbookingsystem.model.view.BookingViewModel;
import com.example.onlinehotelbookingsystem.model.view.SummaryBookingViewModel;
import com.example.onlinehotelbookingsystem.service.AccommodationService;
import com.example.onlinehotelbookingsystem.service.AccommodationTypeService;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.RoomService;
import com.example.onlinehotelbookingsystem.security.CustomUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes("bindingModel")
public class BookingController {

    private final AccommodationService accommodationService;
    private final AccommodationTypeService accommodationTypeService;
    private final RoomService roomService;
    private final BookingService bookingService;
    private final ModelMapper mapper;

    public BookingController(AccommodationService accommodationService, AccommodationTypeService accommodationTypeService, RoomService roomService, BookingService bookingService, ModelMapper mapper) {
        this.accommodationService = accommodationService;
        this.accommodationTypeService = accommodationTypeService;
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.mapper = mapper;
    }

    @GetMapping("/booking-form")
    public String bookingForm(HttpServletRequest request, Model model, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        long id = 0;
        for (Cookie cookie : cookies) {
//            if null
            if (cookie.getName().equals("id")) {
                id = Long.parseLong(cookie.getValue());
                break;
            }
        }
        session.setAttribute("id", id);
        AccommodationServiceModel serviceModel = this.accommodationService.findById(id);
        AccommodationViewModel accommodationViewModel = this.mapper.map(serviceModel, AccommodationViewModel.class);

        model.addAttribute("accommodationViewModel", accommodationViewModel);

        return "booking-form";
    }

    @ModelAttribute("bindingModel")
    public AvailabilityBindingModel bindingModel() {
        return new AvailabilityBindingModel();
    }

//    @PostMapping("/addRoom")
//    public String addContact(BookingBindingModel bindingModel, HttpSession session, Model model) {
//        this.bookingService.addRoom(bindingModel);
//        System.out.println();
//        return "booking-form :: rooms"; // returning the updated section
//    }


    //    TODO: handle requestParam exception when required=true
//    https://stackoverflow.com/questions/37746428/java-spring-how-to-handle-missing-required-request-parameters
    @GetMapping("/room-availability")
    public String checkAvailability(@Valid AvailabilityBindingModel bindingModel, BindingResult br,
                                    RedirectAttributes rAtt, Model model,
                                    @ModelAttribute("messages") RoomMessages roomMessages,
                                    @RequestParam(value = "type", required = false) List<String> type,
                                    @RequestParam(value = "price", required = false) List<BigDecimal> prices,
                                    @RequestParam(value = "roomId", required = false) List<Long> ids) {
        if (br.hasErrors()) {
//            System.out.println(br);
            rAtt
                    .addFlashAttribute("bindingModel", bindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bindingModel", br);
            return "redirect:booking-form";
        }

        for (int i = 0; i < type.size(); i++) {

            String s = type.get(i);
            Long roomId = ids.get(i);
            BigDecimal price = prices.get(i);
            List<RoomBindingModel> rooms = bindingModel.getRooms();
            rooms.get(i).setType(s);
            rooms.get(i).setId(roomId);
            rooms.get(i).setPrice(price);
        }

//        TODO: map in the controller everywhere or check best practices
        AvailabilityServiceModel dto = this.mapper.map(bindingModel, AvailabilityServiceModel.class);
        roomMessages = this.bookingService.checkAvailability(dto);

        if (!roomMessages.getNoRoomsMessage().isEmpty()) {

            rAtt
                    .addFlashAttribute("messages", roomMessages)
                    .addFlashAttribute("bindingModel", bindingModel);

            return "redirect:booking-form";
        }

        rAtt
                .addFlashAttribute("successMessage", roomMessages)
                .addFlashAttribute("bindingModel", bindingModel);
        return "redirect:booking-form";
    }

    @ModelAttribute("bookingBindingModel")
    public BookingBindingModel bookingBindingModel() {
        return new BookingBindingModel();
    }

    @GetMapping("/create-booking")
    public String createBooking() {
        return "create-booking";
    }

    @PostMapping("/create-booking")
    public String createBooking(@Valid BookingBindingModel bookingBindingModel,
                                BindingResult br, RedirectAttributes rAtt,
                                @ModelAttribute("bindingModel") AvailabilityBindingModel bindingModel,
                                @AuthenticationPrincipal CustomUser user) {

        if (br.hasErrors()) {
            rAtt
                    .addFlashAttribute("bookingBindingModel", bookingBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookingBindingModel", br);
            return "redirect:create-booking";
        }

        bookingBindingModel.setBookedRooms(bindingModel.getRooms());
        bookingBindingModel.setHotelId(bindingModel.getHotelId());


        BookingServiceModel serviceModel = this.mapper.map(bookingBindingModel, BookingServiceModel.class);
        SummaryBookingServiceModel serviceBooking = this.bookingService.createBooking(serviceModel, user.getUserId());
        Long bookingId = serviceBooking.getBookingId();
//        message for successful booking + link to all bookings

//        serviceModel.setHotelId(id);
        return "redirect:/bookings/details/" + bookingId;
    }


    @PreAuthorize("isOwner(#id)")
    @GetMapping("/bookings/details/{id}")
    public String showBookingDetails(@PathVariable Long id, Model model, @AuthenticationPrincipal CustomUser user) {
        SummaryBookingServiceModel bookingServiceModel = this.bookingService.findById(id, user.getUserId());

        SummaryBookingViewModel bookingViewModel = this.mapper.map(bookingServiceModel, SummaryBookingViewModel.class);
        model.addAttribute("bookingViewModel", bookingViewModel);
        return "details";
    }

    @PreAuthorize("isOwner(#id)")
    @GetMapping("/bookings/update/{id}")
    public String showUpdate(@PathVariable Long id, @AuthenticationPrincipal CustomUser user, Model model) {
        SummaryBookingServiceModel serviceModel = this.bookingService.findById(id, user.getUserId());
        SummaryBookingViewModel updateViewModel = this.mapper.map(serviceModel, SummaryBookingViewModel.class);
//        binding model??
        model.addAttribute("updateViewModel", updateViewModel);
        System.out.println();
        return "update";
    }

//    TODO: to handle 403
    @PreAuthorize("isOwner(#id)")
    @PatchMapping("/bookings/update/{id}")
    public String updateOffer(@PathVariable Long id, @Valid BookingUpdateBindingModel bookingUpdateBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes rAtt,
                              @AuthenticationPrincipal CustomUser user) {
        if (bindingResult.hasErrors()) {
            rAtt
                    .addFlashAttribute("bookingUpdateBindingModel", bookingUpdateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookingUpdateBindingModel", bindingResult);
            return "redirect:/bookings/update/" + id;
        }
        BookingUpdateServiceModel updateServiceModel = this.mapper.map(bookingUpdateBindingModel, BookingUpdateServiceModel.class);
        updateServiceModel.setId(id);
        this.bookingService.update(updateServiceModel);
        return "redirect:/bookings/details/" + id;
    }

    @GetMapping("/view-all")
    public String viewAllBookings(Model model) {
        System.out.println();
//        List<TitleBookingServiceModel> allBookings = this.bookingService.getAllBookings();
//        model.addAttribute("all", allBookings);
        return "view-all";
    }


}
