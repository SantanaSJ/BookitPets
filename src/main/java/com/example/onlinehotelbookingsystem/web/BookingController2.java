//package com.example.onlinehotelbookingsystem.web;
//
//import com.example.onlinehotelbookingsystem.model.binding.BookingBindingModel;
//import com.example.onlinehotelbookingsystem.model.binding.Row;
//import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
//import com.example.onlinehotelbookingsystem.service.AccommodationService;
//import com.example.onlinehotelbookingsystem.service.BookingService;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.time.LocalDate;
//
//@org.springframework.stereotype.Controller
//public class BookingController {
//
//    private final AccommodationService accService;
//    private final BookingService bookingService;
//
//    public BookingController(AccommodationService accService, BookingService bookingService) {
//        this.accService = accService;
//        this.bookingService = bookingService;
//    }
//
//    @GetMapping("/booking-form")
//    public String show(Model model) {
//        AccommodationViewModel viewModels = this.accService.findById(1L);
//
//        model.addAttribute("viewModels", viewModels);
//        return "select-rooms";
//    }
//
//        @ModelAttribute("bindingModel")
//    public BookingBindingModel bindingModel() {
//       return new BookingBindingModel();
//
//    }
//
//    @PostMapping("save")
//    public String save(BookingBindingModel bindingModel) {
//
//        LocalDate checkIn = bindingModel.getCheckIn();
//        return "details";
//    }
//
////    @PostMapping("/addRoom")
////    public String addContact(BookingBindingModel bindingModel, Model model) {
////        this.bookingService.addRoom(bindingModel);
////        AccommodationViewModel viewModels = this.accService.findById(1L);
////
////        model.addAttribute("viewModels", viewModels);
////        return "select-rooms :: rooms"; // returning the updated section
////    }
//
////    @PostMapping("/addRoom")
////    public String addRow( BookingBindingModel bindingModel) {
////        bindingModel.getRows().add(new Row());
////        return "select-rooms";
////    }
//}
//
//
//
//
//
////package com.example.onlinehotelbookingsystem.web;
////
////import com.example.onlinehotelbookingsystem.model.binding.BookingBindingModel;
////import com.example.onlinehotelbookingsystem.model.binding.RoomBindingModel;
////import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
////import com.example.onlinehotelbookingsystem.service.AccommodationService;
////import com.example.onlinehotelbookingsystem.service.BookingService;
////import com.example.onlinehotelbookingsystem.security.CustomUser;
////import org.springframework.security.core.annotation.AuthenticationPrincipal;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.validation.BindingResult;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////import org.springframework.web.servlet.mvc.support.RedirectAttributes;
////
////import javax.servlet.http.Cookie;
////import javax.servlet.http.HttpServletRequest;
////import javax.validation.Valid;
////import java.util.List;
////
////@Controller
////public class BookingController {
////
////    private final BookingService bookingService;
////    private final AccommodationService accommodationService;
////
////    public BookingController(BookingService bookingService, AccommodationService accommodationService) {
////        this.bookingService = bookingService;
////        this.accommodationService = accommodationService;
////    }
////
////    @GetMapping("/booking-form")
////    public String show(Model model, HttpServletRequest request) {
////        Cookie[] cookies = request.getCookies();
////        long id = 0;
////        for (Cookie cookie : cookies) {
//////            if null
////            if (cookie.getName().equals("id")) {
////                id = Long.parseLong(cookie.getValue());
////                break;
////            }
////        }
////        AccommodationViewModel viewModel = this.accommodationService.findById(id);
////        model.addAttribute("viewModel", viewModel);
////        return "select-rooms";
////    }
////
////    @ModelAttribute("bindingModel")
////    public BookingBindingModel bindingModel(Model model) {
////        BookingBindingModel bookingBindingModel = new BookingBindingModel();
////        List<RoomBindingModel> rooms = bookingBindingModel.getRooms();
////        model.addAttribute("rooms", rooms);
////        return bookingBindingModel;
////
////    }
////
////    @PostMapping("/check-availability")
////    public String createBooking(@Valid BookingBindingModel bindingModel, BindingResult br,
////                                RedirectAttributes rAtt, @AuthenticationPrincipal CustomUser user,
////                                @RequestParam(value = "roomType") List<String> type,
////                                @RequestParam(value = "index", required = false) List<Integer> index) {
////        if (br.hasErrors()) {
//////            System.out.println(br);
////            rAtt
////                    .addFlashAttribute("bindingModel", bindingModel)
////                    .addFlashAttribute("org.springframework.validation.BindingResult.bindingModel", br);
////            return "redirect:booking-form";
////        }
//////        bindingModel.setHotelId(id);
//////        bindingModel.setRooms(type);
////
////
//////        this.bookingService.setRooms(bindingModel);
//////        TODO: map in the controller everywhere or check best practices
//////        this.bookingService.checkDates(bindingModel.getCheckIn(), bindingModel.getCheckOut());
////        this.bookingService.checkAvailability(bindingModel.getCheckIn(), bindingModel.getCheckOut(), bindingModel.getHotelId());
////        this.bookingService.createBooking(bindingModel, user.getUserId());
//////        List<RoomViewModel> viewModels = this.roomService.findByHotelId(bindingModel().getHotelId());
//////        return "redirect:/booking/" + id + "/details";
////        return "redirect:/details";
////
////    }
////
//////    @GetMapping("/booking-form")
//////    public String showRoomForm(Model model) {
//////        AccommodationViewModel viewModel = this.accommodationService.findById(1L);
//////        List<RoomViewModel> rooms = viewModel.getRooms();
//////
////////        ArrayList<String> models = new ArrayList<>();
//////        model.addAttribute("viewModel", viewModel);
//////        return "select-rooms1";
//////    }
////
////
////    @PostMapping("/addRoom")
////    public String addContact(BookingBindingModel bindingModel, Model model) {
////
////        this.bookingService.addRoom(bindingModel);
////        return "select-rooms :: rooms"; // returning the updated section
////    }
////
////
////}
