package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.BookingHistoryEntity;
import com.example.onlinehotelbookingsystem.model.entity.RoomsHistoryEntity;
import com.example.onlinehotelbookingsystem.model.service.RoomServiceModel;
import com.example.onlinehotelbookingsystem.model.service.SummaryBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.service.TitleBookingServiceModel;
import com.example.onlinehotelbookingsystem.repository.BookingHistoryRepository;
import com.example.onlinehotelbookingsystem.service.BookingHistoryService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {
    private final BookingHistoryRepository bookingHistoryRepository;
    private final ModelMapper mapper;

    public BookingHistoryServiceImpl(BookingHistoryRepository bookingHistoryRepository, ModelMapper mapper) {
        this.bookingHistoryRepository = bookingHistoryRepository;
        this.mapper = mapper;
    }

    //completed by id
    @Override
    public SummaryBookingServiceModel findCompletedBookingBy(Long id) {
        BookingHistoryEntity bookingHistoryEntity = this.bookingHistoryRepository
                .findById(id).orElseThrow(() -> new ObjectNotFoundException("Booking with id " + id + " not found!"));

//     TODO:   repeated to check how to make it more generic
        return mapToServiceModel(bookingHistoryEntity);
    }

    private SummaryBookingServiceModel mapToServiceModel(BookingHistoryEntity entity) {
        SummaryBookingServiceModel serviceModel = this.mapper.map(entity, SummaryBookingServiceModel.class);
        serviceModel.setPaymentStatus(entity.getPayment().getStatusEnum().name());
        List<RoomsHistoryEntity> bookedRooms = entity.getRoomsHistoryEntity();
        List<RoomServiceModel> roomServiceModels = bookedRooms
                .stream()
                .map(b -> {
                    RoomServiceModel roomServiceModel = this.mapper.map(b, RoomServiceModel.class);
                    roomServiceModel.setType(b.getRoom().getRoomType());
                    return roomServiceModel;
                })
                .collect(Collectors.toList());

        serviceModel.setRooms(roomServiceModels);
        serviceModel.setCategory(entity.getProperty().getCategory());
        serviceModel.setHotelName(entity.getProperty().getName());
        serviceModel.setType(entity.getProperty().getType().name());
        serviceModel.setAddress(entity.getProperty().getAddress());
        serviceModel.setCity(entity.getProperty().getCity());
        serviceModel.setHotelImage(entity.getProperty().getImageUrl());
        serviceModel.setBookingId(entity.getId());
        serviceModel.setPaymentStatus(entity.getPayment().getStatusEnum().name());
        return serviceModel;
    }

    //    All Completed by userId
    @Override
    public List<TitleBookingServiceModel> findAllBookingsByUserId(Long userId) {
        List<BookingHistoryEntity> all = this.bookingHistoryRepository.findAllByGuestId(userId);

//        repeated with mapToServiceModel -> check if I can make it more generic
        List<TitleBookingServiceModel> collect = all
                .stream()
                .map(b -> {
                    TitleBookingServiceModel serviceModel = this.mapper.map(b, TitleBookingServiceModel.class);
                    serviceModel
                            .setHotelName(b.getProperty().getName())
                            .setCity(b.getProperty().getCity())
                            .setBookingId(b.getId());
                    return serviceModel;

                })
                .collect(Collectors.toList());
        System.out.println();
        return collect;
    }

    @Override
    public boolean isOwnerHistory(String currentUserEmail, Long id) {

        BookingHistoryEntity bookingHistoryEntity = this.bookingHistoryRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Booking with id " + id + " not found!"));
        String email = bookingHistoryEntity.getGuest().getEmail();
        if (email.equals(currentUserEmail)) {
            return true;
        }
        return false;
    }

    @Override
    public Page<SummaryBookingServiceModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        // sortField paymentStatus
        Sort sort =
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<BookingHistoryEntity> page = this.bookingHistoryRepository.findAll(pageable);
        Page<SummaryBookingServiceModel> map = page.map(this::mapToTSummaryBookingServiceModel);
        return map;
    }

    @Override
    public SummaryBookingServiceModel findById(Long id) {
        BookingHistoryEntity bookingHistoryEntity = this.bookingHistoryRepository
                .findById(id).orElseThrow(() -> new ObjectNotFoundException("Booking with id " + id + " not found!"));
        SummaryBookingServiceModel serviceModel = mapToTSummaryBookingServiceModel(bookingHistoryEntity);
        return serviceModel;
    }


    private SummaryBookingServiceModel mapToTSummaryBookingServiceModel(BookingHistoryEntity entity) {
        SummaryBookingServiceModel serviceModel = this.mapper.map(entity, SummaryBookingServiceModel.class);
        serviceModel.setPaymentStatus(entity.getPayment().getStatusEnum().name());

        List<RoomsHistoryEntity> roomsHistoryEntity = entity.getRoomsHistoryEntity();
        List<RoomServiceModel> roomServiceModels = roomsHistoryEntity
                .stream()
                .map(b -> {
                    RoomServiceModel roomServiceModel = this.mapper.map(b, RoomServiceModel.class);
                    roomServiceModel.setType(b.getRoom().getRoomType());
                    return roomServiceModel;
                })
                .collect(Collectors.toList());

        serviceModel.setRooms(roomServiceModels);
        serviceModel.setCategory(entity.getProperty().getCategory());
        serviceModel.setHotelName(entity.getProperty().getName());
        serviceModel.setType(entity.getProperty().getType().name());
        serviceModel.setAddress(entity.getProperty().getAddress());
        serviceModel.setCity(entity.getProperty().getCity());
        serviceModel.setHotelImage(entity.getProperty().getImageUrl());
        serviceModel.setBookingId(entity.getId());
        serviceModel.setPaymentStatus(entity.getPayment().getStatusEnum().name());
        serviceModel.setLng(entity.getLng());
        serviceModel.setLat(entity.getLat());
        return serviceModel;

    }

}
