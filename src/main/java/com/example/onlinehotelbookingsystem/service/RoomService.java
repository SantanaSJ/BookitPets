package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.entity.RoomEntity;
import com.example.onlinehotelbookingsystem.model.service.RoomServiceModel;

import java.util.List;

public interface RoomService {

    List<RoomServiceModel> findByHotelId(Long id);

   RoomEntity findById(Long roomId);


}
