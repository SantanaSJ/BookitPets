package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.entity.RoomEntity;
import com.example.onlinehotelbookingsystem.model.service.RoomServiceModel;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    List<RoomServiceModel> findByHotelId(Long id);

   RoomEntity findById(Long roomId);



//    void addRooms(AccommodationViewModel accommodationViewModel);

}
