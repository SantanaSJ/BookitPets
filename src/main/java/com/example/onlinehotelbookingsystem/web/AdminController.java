package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.binding.BookingUpdateBindingModel;
import com.example.onlinehotelbookingsystem.model.service.AllUsersServiceModel;
import com.example.onlinehotelbookingsystem.model.service.BookingUpdateServiceModel;
import com.example.onlinehotelbookingsystem.model.service.SummaryBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.view.AllUsersViewModel;
import com.example.onlinehotelbookingsystem.model.view.SummaryBookingViewModel;
import com.example.onlinehotelbookingsystem.service.BookingHistoryService;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.StatsService;
import com.example.onlinehotelbookingsystem.service.UserService;
import com.example.onlinehotelbookingsystem.web.responseMessages.Message;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("summaryBookingViewModel")
public class AdminController {
    private final StatsService statsService;
    private final UserService userService;
    private final ModelMapper mapper;
    private final BookingService bookingService;
    private final BookingHistoryService bookingHistoryService;

    public AdminController(StatsService statsService, UserService userService,
                           ModelMapper mapper, BookingService bookingService, BookingHistoryService bookingHistoryService) {
        this.statsService = statsService;
        this.userService = userService;
        this.mapper = mapper;
        this.bookingService = bookingService;
        this.bookingHistoryService = bookingHistoryService;
    }


    @GetMapping("/admin/statistics")
    public ModelAndView statistics() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stats", statsService.getStats());
        modelAndView.setViewName("stats");
        return modelAndView;
    }

    //    PAGINATION MANAGE USERS
    @GetMapping("/admin/manage-users")
    public String showUsers(Model model) {
        String paginated = findPaginatedUsers(1, model, "firstName", "asc");
        return paginated;
    }

    @GetMapping("/admin/manage-users/page/{pageNo}")
    public String findPaginatedUsers(@PathVariable("pageNo") int pageNo, Model model,
                                     @SortDefault @RequestParam(value = "sortField", defaultValue = "firstName") String sortField,
                                     @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {

        int pageSize = 5;

//        pageNo, pageSize, sortField, sortDir
        Page<AllUsersServiceModel> paginated = this.userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        Page<AllUsersViewModel> usersViewModels = paginated.map(this::mapToViewModelUsers);

        List<AllUsersViewModel> allUsers = usersViewModels.getContent();


        if (allUsers.isEmpty()) {

            model.addAttribute("noContent", new Message("There are no users at the moment!"));

        } else {
            model.addAttribute("allUsers", allUsers);
            model
                    .addAttribute("currentPage", pageNo)
                    .addAttribute("totalPages", usersViewModels.getTotalPages());


            model
                    .addAttribute("sortField", sortField)
                    .addAttribute("sortDir", sortDir)
                    .addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        }
        return "admin-manage-users";
    }

    private AllUsersViewModel mapToViewModelUsers(AllUsersServiceModel allUsersServiceModel) {
//        AllUsersViewModel allUsersViewModel = new AllUsersViewModel();
        AllUsersViewModel usersViewModel = this.mapper.map(allUsersServiceModel, AllUsersViewModel.class);
        return usersViewModel;
    }

    //    ADD ADMIN
    @PatchMapping("/admin/manage-users/add-admin")
    public String addAdmin(@RequestParam("email") String email, RedirectAttributes rAtt) {
        boolean exists = this.userService.existsBy(email);
        if (exists) {
            this.userService.makeAdmin(email);
            rAtt.addFlashAttribute("successMsg", new Message("Role admin added successfully to user " + email + "!"));
            System.out.println();
        }

        return "redirect:/admin/manage-users";
    }

    //    REMOVE ADMIN
    @PatchMapping("/admin/manage-users/remove-admin")
    public String removeAdmin(@RequestParam("email") String email, RedirectAttributes rAtt) {
        boolean exists = this.userService.existsBy(email);
        if (exists) {
            this.userService.removeAdmin(email);
            rAtt.addFlashAttribute("successMsgRemove", new Message("Role admin removed successfully from user " + email + "!"));
            System.out.println();
        }

        return "redirect:/admin/manage-users";
    }

    //    REMOVE USER
    @PostMapping("/admin/manage-users/remove-user")
    public String removeUser(@RequestParam("email") String email, RedirectAttributes rAtt) {
        boolean exists = this.userService.existsBy(email);
        if (exists) {
            this.userService.deleteUser(email);
            rAtt.addFlashAttribute("successMsgRemoveUser", new Message("User  email " + email + " removed successfully!"));
            System.out.println();
        }
        return "redirect:/admin/manage-users";
    }

    //    PAGINATION BOOKINGS
    @GetMapping("/admin/manage-all-bookings")
    public String showBookings(Model model) {
        String paginated = findPaginated(1, model, "firstName", "asc");
        return paginated;
    }

    @GetMapping("/admin/bookings/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, Model model,
                                @RequestParam(value = "sortField", defaultValue = "firstName") String sortField,
                                @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
//        bad request

        int pageSize = 5;

//        pageNo, pageSize, sortField, sortDir
        Page<SummaryBookingServiceModel> paginated = this.bookingService.findPaginated(pageNo, pageSize, sortField, sortDir);
        Page<SummaryBookingViewModel> viewModelPage = paginated.map(this::mapToViewModel);
        List<SummaryBookingViewModel> content = viewModelPage.getContent();

        if (content.isEmpty()) {

            model.addAttribute("noContent", new Message("There are no bookings at the moment!"));

        } else {
            model.addAttribute("content", content);
            model
                    .addAttribute("currentPage", pageNo)
                    .addAttribute("totalPages", viewModelPage.getTotalPages());


            model
                    .addAttribute("sortField", sortField)
                    .addAttribute("sortDir", sortDir)
                    .addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        }
        return "admin-all-bookings";
    }


    //    BOOKING DETAILS
    @GetMapping("/admin/details/{id}")
    public String showBookingDetails(@PathVariable("id") Long id, Model model) {
        SummaryBookingServiceModel serviceModel = this.bookingService.findById(id);
//        no view Model, binding model instead
//        SummaryBookingViewModel summaryBookingViewModel = this.mapper.map(serviceModel, SummaryBookingViewModel.class);
        SummaryBookingViewModel summaryBookingViewModel = mapToViewModel(serviceModel);

        model.addAttribute("summaryBookingViewModel", summaryBookingViewModel);
        return "admin-booking-details";
    }

    //    MANAGE BOOKINGS
    @GetMapping("/admin/manage/{id}")
    public String showUpdate(@PathVariable Long id, Model model,
                             @ModelAttribute("summaryBookingViewModel") SummaryBookingViewModel summaryBookingViewModel) {
        BookingUpdateServiceModel bookingUpdateServiceById = this.bookingService.findBookingUpdateServiceById(id);
        BookingUpdateBindingModel bookingUpdateBindingModel = this.mapper.map(bookingUpdateServiceById, BookingUpdateBindingModel.class);

        if (!model.containsAttribute("bookingUpdateBindingModel")) {
            model.addAttribute("bookingUpdateBindingModel", bookingUpdateBindingModel);
        }
        return "admin-update-booking";
    }

    @PatchMapping("/admin/manage/{id}")
    public String manageBooking(@PathVariable Long id, @Valid BookingUpdateBindingModel bookingUpdateBindingModel,
                                BindingResult br, RedirectAttributes rAtt, SessionStatus sessionStatus) {
        if (br.hasErrors()) {
            rAtt
                    .addFlashAttribute("bookingUpdateBindingModel", bookingUpdateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookingUpdateBindingModel", br);
            return "redirect:/admin/manage/" + id;
        }
        BookingUpdateServiceModel updateServiceModel = this.mapper.map(bookingUpdateBindingModel, BookingUpdateServiceModel.class);
        updateServiceModel.setId(id);
        this.bookingService.update(updateServiceModel);
        sessionStatus.setComplete();
        return "redirect:/admin/details/" + id;
    }

    private SummaryBookingViewModel mapToViewModel(SummaryBookingServiceModel serviceModel) {
        SummaryBookingViewModel viewModel = this.mapper.map(serviceModel, SummaryBookingViewModel.class);

        return viewModel;
    }

//BOOKINGS HISTORY

    @GetMapping("/admin/bookings-history")
    public String showHistory(Model model) {
        String paginated = findPaginatedHistory(1, model, "firstName", "asc");
        return paginated;
    }

    @GetMapping("/admin/bookings-history/page/{pageNo}")
    public String findPaginatedHistory(@PathVariable("pageNo") int pageNo, Model model,
                                       @RequestParam(value = "sortField", defaultValue = "firstName") String sortField,
                                       @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
//        bad request

        int pageSize = 5;

//        pageNo, pageSize, sortField, sortDir
        Page<SummaryBookingServiceModel> paginated = this.bookingHistoryService.findPaginated(pageNo, pageSize, sortField, sortDir);
        Page<SummaryBookingViewModel> viewModelPage = paginated.map(this::mapToViewModel);
        List<SummaryBookingViewModel> content = viewModelPage.getContent();

        if (content.isEmpty()) {

            model.addAttribute("noContent", new Message("There are no bookings at the moment!"));

        } else {
            model.addAttribute("content", content);
            model
                    .addAttribute("currentPage", pageNo)
                    .addAttribute("totalPages", viewModelPage.getTotalPages());


            model
                    .addAttribute("sortField", sortField)
                    .addAttribute("sortDir", sortDir)
                    .addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        }
        return "admin-bookings-history";
    }

    //    HISTORY DETAILS
    @GetMapping("/admin/history-details/{id}")
    public String showHistoryDetails(@PathVariable("id") Long id, Model model) {
        SummaryBookingServiceModel serviceModel = this.bookingHistoryService.findById(id);
        SummaryBookingViewModel summaryBookingViewModel = this.mapper.map(serviceModel, SummaryBookingViewModel.class);

        model.addAttribute("viewModel", summaryBookingViewModel);
        return "history-details";
    }
}
