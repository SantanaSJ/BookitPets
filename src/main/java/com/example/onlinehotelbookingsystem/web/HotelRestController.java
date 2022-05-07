package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
import com.example.onlinehotelbookingsystem.service.AccommodationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(allowedHeaders = "*")
@RestController
public class HotelRestController {

    private final AccommodationService accommodationService;

    public HotelRestController(AccommodationService accommodationService, ModelMapper mapper) {
        this.accommodationService = accommodationService;
    }

    //called on http://localhost:8080/hotelNamesAutocomplete
    @GetMapping("/hotelNamesAutocomplete")
    public ResponseEntity<List<AccommodationViewModel>> hotelNamesAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "") String searchTerm) {

        List<AccommodationViewModel> properties = this.accommodationService.findHotels(searchTerm);

        return ResponseEntity.ok(properties);
    }

    //called on http://localhost:8080/accommodations

    @GetMapping("/accommodations")
    public ResponseEntity<List<AccommodationViewModel>> accommodations() {
        List<AccommodationViewModel> allHotels = this.accommodationService.findAll();

        return ResponseEntity.
                ok(allHotels);
   }
}
