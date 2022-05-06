package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.event.BookingCreatedEvent;
import com.example.onlinehotelbookingsystem.model.binding.AvailabilityBindingModel;
import com.example.onlinehotelbookingsystem.model.binding.BookingBindingModel;
import com.example.onlinehotelbookingsystem.model.binding.BookingUpdateBindingModel;
import com.example.onlinehotelbookingsystem.model.binding.RoomBindingModel;
import com.example.onlinehotelbookingsystem.model.service.*;
import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
import com.example.onlinehotelbookingsystem.model.view.SummaryBookingViewModel;
import com.example.onlinehotelbookingsystem.security.CustomUser;
import com.example.onlinehotelbookingsystem.service.AccommodationService;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.UserService;
import com.example.onlinehotelbookingsystem.web.responseMessages.RoomMessages;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@SessionAttributes({"bindingModel", "summaryBookingViewModel"})
public class BookingController {

    private final AccommodationService accommodationService;
    private final ModelMapper mapper;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final BookingService bookingService;

    public BookingController(AccommodationService accommodationService,
                             ModelMapper mapper, UserService userService,
                             ApplicationEventPublisher applicationEventPublisher, BookingService bookingService) {
        this.accommodationService = accommodationService;
        this.mapper = mapper;
        this.userService = userService;
        this.eventPublisher = applicationEventPublisher;
        this.bookingService = bookingService;
    }

//    open booking form
    @GetMapping("/booking-form/accommodation/{id}")
    public String bookingForm(HttpServletRequest request, Model model, HttpSession session, @PathVariable Long id) {
//        Cookie[] cookies = request.getCookies();
//        long id = 0;
//        for (Cookie cookie : cookies) {
////            if null
//            if (cookie.getName().equals("id")) {
//                id = Long.parseLong(cookie.getValue());
//                break;
//            }
//        }
//        session.setAttribute("id", id);
        AccommodationServiceModel serviceModel = this.accommodationService.findById(id);
        AccommodationViewModel accommodationViewModel = this.mapper.map(serviceModel, AccommodationViewModel.class);

        model.addAttribute("accommodationViewModel", accommodationViewModel);

        return "booking-form";
    }

    @ModelAttribute("bindingModel")
    public AvailabilityBindingModel bindingModel() {
        System.out.println();
        return new AvailabilityBindingModel();
    }

    //    TODO: handle requestParam exception when required=true
//    https://stackoverflow.com/questions/37746428/java-spring-how-to-handle-missing-required-request-parameters
    @GetMapping("/room-availability")
    public String checkAvailability(@Valid AvailabilityBindingModel bindingModel, BindingResult br,
                                    RedirectAttributes rAtt, Model model,
                                    @RequestParam(value = "type", required = false) List<String> type,
                                    @RequestParam(value = "price", required = false) List<BigDecimal> prices,
                                    @RequestParam(value = "roomId", required = false) List<Long> ids) {
        if (br.hasErrors() || isRoomListEmpty(bindingModel)) {
//            System.out.println(br);
            rAtt
                    .addFlashAttribute("bindingModel", bindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bindingModel", br);
            Long hotelId = bindingModel.getHotelId();

            return "redirect:/booking-form/accommodation/" + hotelId;
        }

        for (int i = 0; i < type.size(); i++) {
//            if rooms are null - dont add

            String s = type.get(i);
            Long roomId = ids.get(i);
            BigDecimal price = prices.get(i);
            List<RoomBindingModel> rooms = bindingModel.getRooms();
            rooms.get(i).setType(s);
            rooms.get(i).setId(roomId);
            rooms.get(i).setPrice(price);
        }

        AvailabilityServiceModel serviceModel = this.mapper.map(bindingModel, AvailabilityServiceModel.class);
        RoomMessages roomMessages = this.bookingService.checkAvailability(serviceModel);

        if (!roomMessages.getNoRoomsMessage().isEmpty()) {

            rAtt
                    .addFlashAttribute("messages", roomMessages)
                    .addFlashAttribute("bindingModel", bindingModel);

            return "redirect:/booking-form/accommodation/" + bindingModel.getHotelId();
        }

        rAtt
                .addFlashAttribute("successMessage", roomMessages)
                .addFlashAttribute("bindingModel", bindingModel);
        return "redirect:/booking-form/accommodation/" + bindingModel.getHotelId();
    }

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

    @GetMapping("/create-booking")
    public String showCreateBookingForm(Model model, @AuthenticationPrincipal CustomUser user) {

        ProfileServiceModel profileServiceModel = this.userService.findById(user.getUserId());
        BookingBindingModel bookingBindingModel = this.mapper.map(profileServiceModel, BookingBindingModel.class);


        if (!model.containsAttribute("bookingBindingModel")) {
            model.addAttribute("bookingBindingModel", bookingBindingModel);
        }
        System.out.println();
        return "create-booking";
    }

    @PostMapping("/create-booking")
    public String createBooking(@Valid BookingBindingModel bookingBindingModel,
                                BindingResult br, RedirectAttributes rAtt,
                                @ModelAttribute("bindingModel") AvailabilityBindingModel bindingModel,
                                SessionStatus sessionStatus,
                                @AuthenticationPrincipal CustomUser user, Model model) {

        if (br.hasErrors()) {
            rAtt
                    .addFlashAttribute("bookingBindingModel", bookingBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookingBindingModel", br);
            return "redirect:create-booking";
        }

        bookingBindingModel.setBookedRooms(bindingModel.getRooms());
//        bookingBindingModel.setHotelId(bindingModel.getHotelId());

        CreateBookingServiceModel serviceModel = this.mapper.map(bookingBindingModel, CreateBookingServiceModel.class);
        Long bookingId = this.bookingService.createBooking(serviceModel, user.getUserId());
//        publish event
        BookingCreatedEvent event = new BookingCreatedEvent(this, bookingId);
        this.eventPublisher.publishEvent(event);
        sessionStatus.setComplete();
        return "redirect:/bookings/details/" + bookingId;
    }

    //    Booking Summary
    @ModelAttribute("summaryBookingViewModel")
    public SummaryBookingViewModel summaryBookingViewModel() {
        System.out.println();
        return new SummaryBookingViewModel();
    }
    @PreAuthorize("isOwner(#id) or hasRole('ADMIN')")
    @GetMapping("/bookings/details/{id}")
    public String showBookingDetails(@PathVariable Long id, Model model, @AuthenticationPrincipal CustomUser user) {
        SummaryBookingServiceModel bookingServiceModel = this.bookingService.findById(id);

        SummaryBookingViewModel summaryBookingViewModel = this.mapper.map(bookingServiceModel, SummaryBookingViewModel.class);
        model.addAttribute("summaryBookingViewModel", summaryBookingViewModel);
        model.addAttribute("paymentStatus", summaryBookingViewModel.getPaymentStatus());
        return "details";
    }

    @PreAuthorize("isOwner(#id) or hasRole('ADMIN')")
    @GetMapping("/bookings/update/{id}")
    public String showUpdate(@PathVariable Long id, @AuthenticationPrincipal CustomUser user, Model model,
                             SessionStatus sessionStatus) {

        BookingUpdateServiceModel bookingUpdateService = this.bookingService.findBookingUpdateServiceById(id);
        BookingUpdateBindingModel bookingUpdateBindingModel = this.mapper.map(bookingUpdateService, BookingUpdateBindingModel.class);

        if (!model.containsAttribute("bookingUpdateBindingModel")) {
            model.addAttribute("bookingUpdateBindingModel", bookingUpdateBindingModel);
        }
        sessionStatus.setComplete();
        System.out.println();
        return "update";
    }

    @PreAuthorize("isOwner(#id) or hasRole('ADMIN')")
    @PatchMapping("/bookings/update/{id}")
    public String updateBooking(@Valid BookingUpdateBindingModel bookingUpdateBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes rAtt,
                                @PathVariable Long id,
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

//    @PreAuthorize(value = "hasRole('ADMIN') or isBookingsOwner(#principal.username)")
    @GetMapping("/view-all")
    public String viewAllBookings(@AuthenticationPrincipal CustomUser user) {
        System.out.println();
        return "view-all";
    }


}
