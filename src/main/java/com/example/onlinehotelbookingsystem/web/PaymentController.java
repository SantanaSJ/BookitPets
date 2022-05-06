package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.service.SummaryBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.view.SummaryBookingViewModel;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.StripeService;
import com.example.onlinehotelbookingsystem.web.responseMessages.StripeResponseMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class PaymentController {

    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    private final StripeService stripeService;
    private final BookingService bookingService;
    private final ModelMapper mapper;

    public PaymentController(StripeService stripeService, BookingService bookingService, ModelMapper mapper) {
        this.stripeService = stripeService;
        this.bookingService = bookingService;
        this.mapper = mapper;
    }

    @GetMapping("/payment/{id}")
    public String chargePage(Model model, @PathVariable("id") Long id) {
        SummaryBookingServiceModel serviceModel = this.bookingService.findById(id);
        SummaryBookingViewModel bookingViewModel = this.mapper.map(serviceModel, SummaryBookingViewModel.class);
        long totalNights = bookingViewModel.getTotalNights();

        BigDecimal totalPrice = bookingViewModel.getTotalPrice();
        model.addAttribute("all", bookingViewModel);
        model.addAttribute("total", totalPrice);
//        model.addAttribute("numberOfRooms", numberOfRooms);
        model.addAttribute("nights", totalNights);
//        model.addAttribute("roomTypes", bookingViewModel.getRooms());
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        model.addAttribute("hasDiscount", bookingViewModel.hasDiscount());
        model.addAttribute("bookingId", bookingViewModel.getBookingId());
        return "payment";

    }

    @PostMapping("/create-charge")
    public @ResponseBody
    StripeResponseMessage createCharge(String email, String token, BigDecimal amount, Long bookingId) {
        //validate data
        if (token == null) {
            return new StripeResponseMessage(false, "Stripe payment token is missing. Please, try again later.");
//        throw new RuntimeException("Stripe payment token is missing. Please, try again later.");
        }

        int amountInCents = convertToCents(amount);

        //create charge
        String chargeId = this.stripeService.createCharge(email, token, amountInCents, bookingId); //135 EUR

        if (chargeId == null) {
            return new StripeResponseMessage(false, "An error occurred while trying to create a charge.");
        }

        return new StripeResponseMessage(true, "Success! Your charge id is " + chargeId);
    }

//    Stripe working with cents
    private int convertToCents(BigDecimal amount) {
        return amount.movePointRight(2).intValueExact();
    }

}
