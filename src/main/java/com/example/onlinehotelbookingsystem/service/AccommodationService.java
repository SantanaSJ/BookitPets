package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.binding.RoomBindingModel;
import com.example.onlinehotelbookingsystem.model.service.AccommodationServiceModel;
import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationService {

    List<AccommodationViewModel> findHotels(String searchTerm);

    List<AccommodationViewModel> findAll();

    AccommodationServiceModel findById(Long id);


}
