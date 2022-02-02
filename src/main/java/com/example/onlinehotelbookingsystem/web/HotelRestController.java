package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.service.AccommodationServiceModel;
import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
import com.example.onlinehotelbookingsystem.service.AccommodationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelRestController {

    private final AccommodationService accommodationService;
    private final ModelMapper mapper;

    public HotelRestController(AccommodationService accommodationService, ModelMapper mapper) {
        this.accommodationService = accommodationService;
        this.mapper = mapper;
    }

    //called on http://localhost:8080/hotelNamesAutocomplete
    @GetMapping("/hotelNamesAutocomplete")
    public ResponseEntity<List<AccommodationViewModel>> hotelNamesAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "") String searchTerm) {

        List<AccommodationViewModel> properties = this.accommodationService.findHotels(searchTerm);

        return ResponseEntity.ok(properties);

//    hardcoded
//    List<String> suggestions = new ArrayList<>();
//    suggestions.add("Best Western");
//    suggestions.add("Central Hotel");
//    suggestions.add("Ramada");
//    return suggestions;
    }

    //called on http://localhost:8080/accommodations
    @GetMapping("/accommodations")
    public ResponseEntity<List<AccommodationViewModel>> accommodations() {
        List<AccommodationViewModel> allHotels = this.accommodationService.findAll();

        return ResponseEntity.
                ok(allHotels);
   }

//    @GetMapping("/hotels/{id}")
//    public ResponseEntity<AccommodationViewModel> showBookingForm(@PathVariable Long id) {
////        TODO: try search by id in the DB
//        AccommodationServiceModel serviceModel = this.accommodationService.findById(id);
//        AccommodationViewModel viewModel = this.mapper.map(serviceModel, AccommodationViewModel.class);
//
//
////        TODO: If invalid id is passed - error
//
//        return ResponseEntity.ok(viewModel);
//    }
}
