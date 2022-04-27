package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
import com.example.onlinehotelbookingsystem.service.AccommodationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MapController {
    private AccommodationService accommodationService;

    public MapController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public String map(Model model) {
        List<AccommodationViewModel> all = this.accommodationService.findAll();
        model.addAttribute("all", all);
        return "home";
    }
}
