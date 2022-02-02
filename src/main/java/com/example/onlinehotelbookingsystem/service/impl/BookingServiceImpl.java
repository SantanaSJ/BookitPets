package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.errors.RoomMessages;
import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.model.service.*;
import com.example.onlinehotelbookingsystem.repository.AccommodationRepository;
import com.example.onlinehotelbookingsystem.repository.BookedRoomsRepository;
import com.example.onlinehotelbookingsystem.repository.BookingRepository;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import com.example.onlinehotelbookingsystem.service.AccommodationService;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.RoomService;
import com.example.onlinehotelbookingsystem.service.UserService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private final ModelMapper mapper;
    private final BookingRepository bookingRepository;
    private final BookedRoomsRepository bookedRoomsRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;
    private final RoomService roomService;

    public BookingServiceImpl(ModelMapper mapper, BookingRepository bookingRepository, BookedRoomsRepository bookedRoomsRepository, UserRepository userRepository, AccommodationRepository accommodationRepository, UserService userService, AccommodationService accommodationService,
                              RoomService roomService) {
        this.mapper = mapper;
        this.bookingRepository = bookingRepository;
        this.bookedRoomsRepository = bookedRoomsRepository;
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
        this.roomService = roomService;
    }

    @Override
    public SummaryBookingServiceModel createBooking(BookingServiceModel serviceModel, Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow();
        AccommodationEntity accommodationEntity = this.accommodationRepository.findById(serviceModel.getHotelId()).orElseThrow();
//manual mapping because hotelId is mapped to entity id and overrides entity id
//        BookingEntity bookingEntity = this.mapper.map(serviceModel, BookingEntity.class);
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity
                .setCheckIn(serviceModel.getCheckIn())
                .setCheckOut(serviceModel.getCheckOut())
                .setComments(serviceModel.getComments())
                .setEmail(serviceModel.getEmail())
                .setNumberOfPeople(serviceModel.getNumberOfPeople())
                .setPetKilograms(serviceModel.getPetKilograms())
                .setPetName(serviceModel.getPetName())
                .setBookingTime(LocalDateTime.now())
                .setGuest(userEntity)
                .setProperty(accommodationEntity)
                .setFirstName(serviceModel.getFirstName())
                .setLastName(serviceModel.getLastName());

        List<BookedRoomsEntity> list = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        long nights = 0;

        for (RoomServiceModel bookedRoom : serviceModel.getBookedRooms()) {
            BookedRoomsEntity bookedRoomsEntity = new BookedRoomsEntity();
//            BookedRoomsEntity map = this.mapper.map(bookedRoom, BookedRoomsEntity.class);
            bookedRoomsEntity.setBooking(bookingEntity);
            RoomEntity byId = this.roomService.findById(bookedRoom.getRoomId());
            bookedRoomsEntity.setRoom(byId);
            bookedRoomsEntity.setPrice(bookedRoom.getPrice());
            bookedRoomsEntity.setNumberOfRooms(bookedRoom.getNumberOfRooms());
            this.bookedRoomsRepository.save(bookedRoomsEntity);
            list.add(bookedRoomsEntity);

            BigDecimal pricePerRoom = bookedRoom.getPrice();

            LocalDate checkin = serviceModel.getCheckIn();
            LocalDate checkOut = serviceModel.getCheckOut();
// leap year?
            nights = ChronoUnit.DAYS.between(checkin, checkOut);

            total = pricePerRoom
                    .multiply(BigDecimal.valueOf(nights))
                    .multiply(BigDecimal.valueOf(bookedRoom.getNumberOfRooms()))
                    .add(total);

        }
        bookingEntity.setTotalPrice(total);
        bookingEntity.setTotalNights(nights);
        bookingEntity.setBookedRooms(list);

        BookingEntity savedEntity = this.bookingRepository.save(bookingEntity);
        SummaryBookingServiceModel bookingServiceModel = mapToServiceModel(savedEntity);
//        BookingServiceModel model = this.mapper.map(savedEntity, BookingServiceModel.class);
//        model.setHotelId(savedEntity.getProperty().getId());
//        model.setFirstName(savedEntity.getFirstName());
//        model.setLastName(savedEntity.getLastName());
//        model.setBookingId(savedEntity.getId());
        return bookingServiceModel;
    }

    @Override
    public RoomMessages checkAvailability(AvailabilityServiceModel model) {

        RoomMessages roomMessages = new RoomMessages();
//        convert it to entity before checking?
//        TODO: Correct RoomServiceModel in Dto or check for best practices. To correct - if 1 single is not available and 1 double is available??
        for (RoomServiceModel room : model.getRooms()) {
            Integer n = this.bookingRepository.isRoomAvailableBetween(model.getCheckIn(), model.getCheckOut(),
                    model.getHotelId(), room.getRoomId());
            String type = room.getType();
            if (n > 0) {
                roomMessages.getType().add(type);
                roomMessages.getNoRoomsMessage().add(String.format("No availability for %s room for the selected dates. Please update your search!", type));
            } else {
                roomMessages.getSuccessMessage().add(String.format("Selected %s room is available for the period.", type));
            }
        }
        return roomMessages;
    }

    @Override
    public List<TitleBookingServiceModel> getAllBookingsByCurrentUserId(Long id) {
//        if null???
        List<BookingEntity> all = this.bookingRepository.findAllByUserId(id);

        List<TitleBookingServiceModel> list = new ArrayList<>();
        for (BookingEntity bookingEntity : all) {
            TitleBookingServiceModel serviceModel = new TitleBookingServiceModel();
            serviceModel.setCity(bookingEntity.getProperty().getCity());
            serviceModel.setFirstName(bookingEntity.getFirstName());
            serviceModel.setLastName(bookingEntity.getLastName());
            serviceModel.setHotelName(bookingEntity.getProperty().getName());
            serviceModel.setCheckIn(bookingEntity.getCheckIn());
            serviceModel.setCheckOut(bookingEntity.getCheckOut());
            serviceModel.setBookingId(bookingEntity.getId());
            list.add(serviceModel);
        }
        return list;
    }

    @Override
    public SummaryBookingServiceModel getLastBookingByCurrentUserId(Long id) {
        BookingEntity entity = this.bookingRepository.getLastByUserId(id);
        SummaryBookingServiceModel serviceModel = mapToServiceModel(entity);
        return serviceModel;
    }



    @Override
    public void update(BookingUpdateServiceModel updateServiceModel) {
        BookingEntity bookingEntity = this.bookingRepository.findById(updateServiceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Booking with id " + updateServiceModel.getId() + " not found!"));

        bookingEntity
                .setUpdated(LocalDateTime.now())
                .setFirstName(updateServiceModel.getFirstName())
                .setLastName(updateServiceModel.getLastName())
                .setPetName(updateServiceModel.getPetName())
                .setPetKilograms(updateServiceModel.getPetKilograms())
                .setEmail(updateServiceModel.getEmail())
                .setComments(updateServiceModel.getComments());

        this.bookingRepository.save(bookingEntity);
    }

    @Override
    public SummaryBookingServiceModel findById(Long bookingId, Long userId) {
        BookingEntity bookingEntity = this.bookingRepository.findById(bookingId).orElseThrow();
        SummaryBookingServiceModel serviceModel = mapToServiceModel(bookingEntity);
        return serviceModel;
    }

    @Override
    public void delete(Long id) {
        this.bookingRepository.deleteById(id);
        System.out.println();
    }

    public boolean isOwner(String currentUserEmail, Long bookingId) {
        BookingEntity bookingEntity = this.bookingRepository.
                findById(bookingId).orElse(null);
//        UserEntity userEntity = this.userRepository.
//                findByEmail(currentUserEmail).orElse(null);

        String email = bookingEntity.getGuest().getEmail();
        if (email.equals(currentUserEmail)) {
            return true;
        }
//        if (bookingOpt.isPresent() && caller.isPresent()) return true;
        else return false;
//        else {
//            BookingEntity bookingEntity = bookingOpt.get();
//
//            return isAdmin(caller.get()) ||
//                    offerEntity.getSeller().getUsername().equals(userName);
//        }
    }

//    private boolean isAdmin(UserEntity user) {
//        return user.
//                getRoles().
//                stream().
//                map(UserRoleEntity::getRole).
//                anyMatch(r -> r == UserRoleEnum.ADMIN);
//    }
    private SummaryBookingServiceModel mapToServiceModel(BookingEntity entity) {
        SummaryBookingServiceModel serviceModel = this.mapper.map(entity, SummaryBookingServiceModel.class);
//        or else null/optional/or else throw???

        for (BookedRoomsEntity bookedRoom : entity.getBookedRooms()) {
            serviceModel.getRoomTypesCostPerNight().put(bookedRoom.getRoom().getRoomType(), bookedRoom.getPrice());
        }
        serviceModel.setCategory(entity.getProperty().getCategory());
        serviceModel.setHotelName(entity.getProperty().getName());
        serviceModel.setType(entity.getProperty().getType().getType().name());
        serviceModel.setAddress(entity.getProperty().getAddress());
        serviceModel.setCity(entity.getProperty().getCity());
        serviceModel.setHotelImage(entity.getProperty().getImageUrl());
        serviceModel.setBookingId(entity.getId());
        return serviceModel;
    }

//    @Override
//    public boolean isAvailableBetween(LocalDate checkIn, LocalDate checkOut, Long hotelId, List<RoomBindingModel> rooms) {
//
////        this.roomService.f
////        this.roomService.isRoomAvailaleBetween()
//        return false;
//    }

}
