package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.service.SummaryBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.service.TitleBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.view.SummaryBookingViewModel;
import com.example.onlinehotelbookingsystem.model.view.TitleBookingViewModel;
import com.example.onlinehotelbookingsystem.security.CustomUser;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.web.responseMessages.Message;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingHistoryController {

    private final BookingService bookingService;
    private final ModelMapper mapper;


    public BookingHistoryController(BookingService bookingService, ModelMapper mapper) {
        this.bookingService = bookingService;
        this.mapper = mapper;
    }


    @GetMapping("/history")
    public String showAllHistory(@AuthenticationPrincipal CustomUser user, Model model) {
        List<TitleBookingServiceModel> serviceModels = this.bookingService.findAllPassedBookingsByUserId(user.getUserId());

        List<TitleBookingViewModel> viewModels = serviceModels
                .stream()
                .map(s -> this.mapper.map(s, TitleBookingViewModel.class))
                .collect(Collectors.toList());

        if (viewModels.isEmpty()) {
            model.addAttribute("noContent", new Message("There are no bookings at the moment!"));
        } else {
            model.addAttribute("viewModels", viewModels);
        }
        return "history";
    }

    @PreAuthorize("isOwnerHistory(#id) or hasRole('ADMIN')")
    @GetMapping("/history/details/{id}")
    public String showHistoryDetails(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal CustomUser user) {
        SummaryBookingServiceModel serviceModel = this.bookingService.findPassedBookingById(id);
        SummaryBookingViewModel viewModel = this.mapper.map(serviceModel, SummaryBookingViewModel.class);

        model.addAttribute("viewModel", viewModel);

        return "history-details";
    }
}
