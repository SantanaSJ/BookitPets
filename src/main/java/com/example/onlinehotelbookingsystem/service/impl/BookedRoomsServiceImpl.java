//package com.example.onlinehotelbookingsystem.service.impl;
//
//import com.example.onlinehotelbookingsystem.model.entity.BookedRoomsEntity;
//import com.example.onlinehotelbookingsystem.model.entity.RoomEntity;
//import com.example.onlinehotelbookingsystem.model.service.RoomServiceModel;
//import com.example.onlinehotelbookingsystem.repository.BookedRoomsRepository;
//import com.example.onlinehotelbookingsystem.service.BookedRoomsService;
//import com.example.onlinehotelbookingsystem.service.RoomService;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BookedRoomsServiceImpl implements BookedRoomsService {
//    private final RoomService roomService;
//    private final ModelMapper mapper;
//    private final BookedRoomsRepository bookedRoomsRepository;
//
//    public BookedRoomsServiceImpl(RoomService roomService, ModelMapper mapper, BookedRoomsRepository bookedRoomsRepository) {
//        this.roomService = roomService;
//        this.mapper = mapper;
//        this.bookedRoomsRepository = bookedRoomsRepository;
//    }
//
//
////    @Override
////    public BookedRoomsEntity getBookedRooms(RoomServiceModel serviceModel, Long id) {
////        RoomEntity roomEntity = this.roomService.findById(id);
////
//////        BookedRoomsEntity bookedRoomsEntity = new BookedRoomsEntity();
////        BookedRoomsEntity bookedRoomsEntity = this.mapper.map(serviceModel, BookedRoomsEntity.class);
//////        bookedRoomsEntity.setBooking(bookingEntity);
////
////        bookedRoomsEntity.setRoom(roomEntity);
////        bookedRoomsEntity.setPrice(serviceModel.getPrice());
////        bookedRoomsEntity.setNumberOfRooms(serviceModel.getNumberOfRooms());
//////        bookedRoomsEntity.setNumberOfPeople(serviceModel.getNumberOfPeople());
////        this.bookedRoomsRepository.save(bookedRoomsEntity);
////        return bookedRoomsEntity;
////    }
//}
