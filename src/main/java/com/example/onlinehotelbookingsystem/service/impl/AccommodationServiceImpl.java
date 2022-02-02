package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.AccommodationEntity;
import com.example.onlinehotelbookingsystem.model.service.AccommodationServiceModel;
import com.example.onlinehotelbookingsystem.model.service.RoomServiceModel;
import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
import com.example.onlinehotelbookingsystem.model.view.RoomViewModel;
import com.example.onlinehotelbookingsystem.repository.AccommodationRepository;
import com.example.onlinehotelbookingsystem.service.AccommodationService;
import com.example.onlinehotelbookingsystem.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {
// TODO: check if it is a good idea to return entity->serviceModel->view/bindingModel


    private final AccommodationRepository accommodationRepository;
    private final ModelMapper mapper;
    private final RoomService roomService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, ModelMapper mapper, RoomService roomService) {
        this.accommodationRepository = accommodationRepository;
        this.mapper = mapper;
        this.roomService = roomService;
    }


    @Override
    public List<AccommodationViewModel> findHotels(String searchTerm) {

        List<AccommodationEntity> properties = this.accommodationRepository.findPropertyEntityByNameIsStartingWith(searchTerm);
        return getAccommodationViewModels(properties);
    }

    @Override
    public List<AccommodationViewModel> findAll() {
        List<AccommodationEntity> properties = this.accommodationRepository.findAll();
        return getAccommodationViewModels(properties);
    }

    @Override
    public AccommodationServiceModel findById(Long id) {
//        TODO: to map all to view model, without service layer
        return this.accommodationRepository
                .findById(id)
                .map(a -> {
                    AccommodationServiceModel serviceModel = this.mapper.map(a, AccommodationServiceModel.class);
                    serviceModel.setType(a.getType().getType().name());
//                 to correct it - map it and add it to the accViewModel here.
                    List<RoomServiceModel> roomServiceModels = this.roomService.findByHotelId(id);
                    serviceModel.setRooms(roomServiceModels);
                    return serviceModel;
                })
                .orElse(null);
//      TODO:  to check orElseThrow

    }

//    @Override
//    public boolean isAvailableBetween(LocalDate checkIn, LocalDate checkOut, Long id, List<RoomBindingModel> rooms) {
////        TODO: check when to use orElse(null) and orElseThrow()
//
//        for (RoomBindingModel room : rooms) {
//            String type = room.getType();
//            AccommodationEntity entity = this.accommodationRepository.findById(id).orElseThrow();
////            this.accommodationRepository.isRoomAvailableBetween(checkIn, checkOut, type);
//
//        }



//        boolean availableBetween = entity.isAvailableBetween(checkIn, checkOut);
//        return this.bookings.stream()
//                    .noneMatch(b ->
//                            (b.isDateInsideCheckedIn(checkin) || b.isDateInsideCheckedIn(checkout))
//                                    || (b.getCheckin().equals(checkin) && b.getCheckOut().equals(checkout)));




    private List<AccommodationViewModel> getAccommodationViewModels(List<AccommodationEntity> properties) {
        List<AccommodationServiceModel> serviceModels = properties
                .stream()
                .map(a -> {
                    AccommodationServiceModel map = this.mapper.map(a, AccommodationServiceModel.class);
                    map.setType(a.getType().getType().name());
                    return map;
                })
                .collect(Collectors.toList());
        return serviceModels
                .stream()
                .map(s -> this.mapper.map(s, AccommodationViewModel.class))

                .collect(Collectors.toList());
    }


}
