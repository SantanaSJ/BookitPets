package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.RoomEntity;
import com.example.onlinehotelbookingsystem.model.service.RoomServiceModel;
import com.example.onlinehotelbookingsystem.repository.RoomRepository;
import com.example.onlinehotelbookingsystem.service.RoomService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper mapper;

    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper mapper) {
        this.roomRepository = roomRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RoomServiceModel> findByHotelId(Long id) {

        List<RoomEntity> roomEntities = this.roomRepository.findByAccommodationEntity_Id(id);

        return roomEntities
                .stream()
                .map(r -> this.mapper.map(r, RoomServiceModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public RoomEntity findById(Long roomId) {
        return this.roomRepository.findById(roomId).orElseThrow(() -> new ObjectNotFoundException("Room with id " + roomId + "not found!"));
    }

}
