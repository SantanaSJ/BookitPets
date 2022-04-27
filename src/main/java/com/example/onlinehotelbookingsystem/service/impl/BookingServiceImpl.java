package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.web.responseMessages.RoomMessages;
import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.service.*;
import com.example.onlinehotelbookingsystem.repository.*;
import com.example.onlinehotelbookingsystem.service.*;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum.*;

@Service
public class BookingServiceImpl implements BookingService {
    private int bookingCounter = 0;
    private final ModelMapper mapper;
    private final BookingRepository bookingRepository;
    private final BookedRoomsRepository bookedRoomsRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final RoomService roomService;
    private final PaymentRepository paymentRepository;

    public BookingServiceImpl(ModelMapper mapper, BookingRepository bookingRepository,
                              BookedRoomsRepository bookedRoomsRepository, UserRepository userRepository,
                              AccommodationRepository accommodationRepository,
                              RoomService roomService, PaymentRepository paymentRepository) {
        this.mapper = mapper;
        this.bookingRepository = bookingRepository;
        this.bookedRoomsRepository = bookedRoomsRepository;
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
        this.roomService = roomService;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Long createBooking(CreateBookingServiceModel serviceModel, Long userId) {
        UserEntity userEntity = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User with id" + userId + " not found!"));
        AccommodationEntity accommodationEntity = this.accommodationRepository
                .findById(serviceModel.getHotelId())
                .orElseThrow(() -> new ObjectNotFoundException("Accommodation with id" + serviceModel.getHotelId() + " not found!"));


//manual mapping because hotelId is mapped to entity id and overrides entity id
//        BookingEntity bookingEntity = this.mapper.map(serviceModel, BookingEntity.class);

        BookingEntity bookingEntity = mapToBookingEntity(serviceModel, userEntity, accommodationEntity);


        List<BookedRoomsEntity> list = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        long nights = getNights(serviceModel);

//        rooms
        for (RoomServiceModel bookedRoom : serviceModel.getBookedRooms()) {
            if (bookedRoom.getNumberOfRooms() != 0) {

                BookedRoomsEntity bookedRoomsEntity = getBookedRoomsEntity(bookingEntity, bookedRoom);
                list.add(bookedRoomsEntity);

//                calculate total price and total nights
                BigDecimal pricePerRoom = bookedRoom.getPrice();

                total = calculateTotal(total, nights, bookedRoom, pricePerRoom);

            }
        }

        //        set payment to unpaid
        PaymentEntity paymentEntity = getPaymentEntity();

        setBookingCounter(bookingEntity);
        bookingEntity.setTotalPrice(total);
        bookingEntity.setTotalNights(nights);
        bookingEntity.setBookedRooms(list);
//        LocalDate cxlDate = calculateCancellationDate(serviceModel.getCheckIn(), accommodationEntity.getCancellationPolicy());
//        bookingEntity.setCancellationDate(cxlDate);


        bookingEntity.setPayment(paymentEntity);

        BookingEntity savedEntity = this.bookingRepository.save(bookingEntity);

        return savedEntity.getId();
    }

    private void setBookingCounter(BookingEntity bookingEntity) {
        bookingCounter++;
        bookingEntity.setCountBookings(bookingCounter);
        bookingCounter = bookingEntity.getCountBookings();
    }

    private BookingEntity mapToBookingEntity(CreateBookingServiceModel serviceModel, UserEntity userEntity, AccommodationEntity acEntity) {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity
                .setCheckIn(serviceModel.getCheckIn())
                .setCheckOut(serviceModel.getCheckOut())
                .setComments(serviceModel.getComments())
                .setEmail(serviceModel.getEmail())
                .setPhoneNumber(serviceModel.getPhoneNumber())
                .setPetKilograms(serviceModel.getPetKilograms())
                .setPetName(serviceModel.getPetName())
                .setBookingTime(LocalDateTime.now())
                .setGuest(userEntity)
                .setProperty(acEntity)
                .setFirstName(serviceModel.getFirstName())
                .setLastName(serviceModel.getLastName());

        return bookingEntity;
    }

    @Override
    public RoomMessages checkAvailability(AvailabilityServiceModel model) {

        RoomMessages roomMessages = new RoomMessages();
//        convert to entity before checking?
//        TODO: To correct - if 1 single is not available and 1 double is available??
        for (RoomServiceModel room : model.getRooms()) {
            if (room.getNumberOfRooms() != 0) {
                Integer n = this.bookingRepository.isRoomAvailableBetween(model.getCheckIn(), model.getCheckOut(),
                        model.getHotelId(), room.getRoomId());
                String type = room.getType();
                if (n > 0) {
                    roomMessages.getType().add(type);
                    roomMessages.getNoRoomsMessage().add(String.format("No availability for %s room for the selected dates. Please update your search!", type));
                }
            }
        }
        if (roomMessages.getNoRoomsMessage().isEmpty()) {
            roomMessages.getSuccessMessage().add("Selected rooms are available!");
        }
        return roomMessages;
    }

    @Override
    public List<TitleBookingServiceModel> getAllActiveBookingsByCurrentUserId(Long id) {

        List<BookingEntity> entities = this.bookingRepository.findAllActiveBookingsBy(id);

        return mapToTitleServiceModel(entities);
    }

    @Override
    public void update(BookingUpdateServiceModel updateServiceModel) {
        BookingEntity bookingEntity = this.bookingRepository.findById(updateServiceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Booking with id " + updateServiceModel.getId() + " not found!"));

        //mapToEntity
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
    public SummaryBookingServiceModel findById(Long bookingId) {
        BookingEntity bookingEntity = getBooking(bookingId);
        SummaryBookingServiceModel serviceModel = mapToTSummaryBookingServiceModel(bookingEntity);
        return serviceModel;
    }

    @Override
    public BookingUpdateServiceModel findBookingUpdateServiceById(Long bookingId) {
        BookingEntity bookingEntity = getBooking(bookingId);
        BookingUpdateServiceModel serviceModel = this.mapper.map(bookingEntity, BookingUpdateServiceModel.class);
        return serviceModel;
    }

    @Override
    public void delete(Long id) {
        this.bookingRepository.moveCancelledBookingToHistory(id);
        deleteCancelledBooking(id);

        System.out.println();
    }

    private void deleteCancelledBooking(Long id) {
        this.bookingRepository.deleteById(id);
        System.out.println();
    }

    public boolean isOwner(String currentUserEmail, Long bookingId) {
        BookingEntity bookingEntity = this.bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new ObjectNotFoundException("Booking with id " + bookingId + " not found!"));

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

    //    to change - count after completed
    @Override
    public void countBookings(Long id) {
        BookingEntity bookingEntity = this.bookingRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Booking with id " + id + " not found!"));

//        bookingEntity.setCountBookings(bookingEntity.incrementBookings());
        int countBookings = bookingEntity.getCountBookings();
        int totalNights = (int) bookingEntity.getTotalNights();

        boolean isDiscountApplicable = isDiscountApplicable(countBookings, totalNights);

        if (isDiscountApplicable) {
            applyDiscount(totalNights, bookingEntity);
//            long totalNights = bookingEntity.getTotalNights();
        }
    }

    private void applyDiscount(int totalNights, BookingEntity bookingEntity) {
        int numberOfRooms = bookingEntity.getBookedRooms().size();
        int count = totalNights * numberOfRooms;
        BigDecimal sum = new BigDecimal(0);
        for (BookedRoomsEntity bookedRoom : bookingEntity.getBookedRooms()) {
            BigDecimal price = bookedRoom.getPrice();
            sum = sum.add(price.multiply(BigDecimal.valueOf(totalNights)));
        }
        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), RoundingMode.FLOOR);
        bookingEntity.setTotalPrice(bookingEntity.getTotalPrice().subtract(avg));
        System.out.println(bookingEntity.getTotalPrice());
        bookingEntity.setCountBookings(0);
        bookingEntity.setHasDiscount(true);
        this.bookingRepository.save(bookingEntity);

    }

//    ???
    private boolean isDiscountApplicable(int countBookings, int totalNights1) {
        return countBookings > 0 && totalNights1 > 1;
    }

    @Override
    public Page<SummaryBookingServiceModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {

// sortField paymentStatus
        Sort sort =
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<BookingEntity> page = this.bookingRepository.findAll(pageable);
        Page<SummaryBookingServiceModel> map = page.map(this::mapToTSummaryBookingServiceModel);
        return map;
    }

    @Override
    public void moveCompletedBookingsToHistory() {
        this.bookingRepository.moveCompletedBookingsToHistory();

        deleteCompletedBookings();
    }

    @Override
    public BookingServiceModel findBookingById(Long id) {

        BookingEntity entity = getBooking(id);
        BookingServiceModel serviceModel = this.mapper.map(entity, BookingServiceModel.class);
        serviceModel.setPaymentStatus(entity.getPayment().getStatusEnum().name());
        return serviceModel;
    }

    @Override
    public void setPaymentStatus(Long bookingId) {
        BookingEntity bookingEntity = this.bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new ObjectNotFoundException("Booking with id " + bookingId + "not found!"));
        PaymentEntity payment = bookingEntity.getPayment();
        payment.setStatusEnum(PAID);
    }

    private BookedRoomsEntity getBookedRoomsEntity(BookingEntity bookingEntity, RoomServiceModel bookedRoom) {
        RoomEntity roomEntity = this.roomService.findById(bookedRoom.getRoomId());
        BookedRoomsEntity bookedRoomsEntity = new BookedRoomsEntity();
        bookedRoomsEntity.setBooking(bookingEntity);
        bookedRoomsEntity.setRoom(roomEntity);
        bookedRoomsEntity.setPrice(bookedRoom.getPrice());
        bookedRoomsEntity.setNumberOfRooms(bookedRoom.getNumberOfRooms());
        this.bookedRoomsRepository.save(bookedRoomsEntity);
        return bookedRoomsEntity;
    }

    private BigDecimal calculateTotal(BigDecimal total, long nights, RoomServiceModel bookedRoom, BigDecimal pricePerRoom) {
        total = pricePerRoom
                .multiply(BigDecimal.valueOf(nights))
                .multiply(BigDecimal.valueOf(bookedRoom.getNumberOfRooms()))
                .add(total);
        return total;
    }

    private long getNights(CreateBookingServiceModel serviceModel) {
        long nights;
        LocalDate checkin = serviceModel.getCheckIn();
        LocalDate checkOut = serviceModel.getCheckOut();
// leap year?
        nights = ChronoUnit.DAYS.between(checkin, checkOut);
        return nights;
    }

    private PaymentEntity getPaymentEntity() {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setStatusEnum(UNPAID);

        this.paymentRepository.save(paymentEntity);
        return paymentEntity;
    }

    private BookingEntity getBooking(Long bookingId) {
        return this.bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new ObjectNotFoundException("Booking with id " + bookingId + " not found!"));
    }

    private void deleteCompletedBookings() {
        this.bookingRepository.deleteAllCompleted();
    }

    private List<TitleBookingServiceModel> mapToTitleServiceModel(List<BookingEntity> entities) {
        List<TitleBookingServiceModel> list = entities
                .stream()
                .map(b -> {
                    TitleBookingServiceModel serviceModel = this.mapper.map(b, TitleBookingServiceModel.class);
                    serviceModel
                            .setHotelName(b.getProperty().getName())
                            .setCity(b.getProperty().getCity());
                    return serviceModel;
                })
                .collect(Collectors.toList());
        return list;
    }

    private SummaryBookingServiceModel mapToTSummaryBookingServiceModel(BookingEntity entity) {
        SummaryBookingServiceModel serviceModel = this.mapper.map(entity, SummaryBookingServiceModel.class);
        serviceModel.setPaymentStatus(entity.getPayment().getStatusEnum().name());

        List<BookedRoomsEntity> bookedRooms = entity.getBookedRooms();
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
        serviceModel.setType(entity.getProperty().getType().getType().name());
        serviceModel.setAddress(entity.getProperty().getAddress());
        serviceModel.setCity(entity.getProperty().getCity());
        serviceModel.setHotelImage(entity.getProperty().getImageUrl());
        serviceModel.setBookingId(entity.getId());
        serviceModel.setPaymentStatus(entity.getPayment().getStatusEnum().name());
        return serviceModel;

    }


    //    private boolean isAdmin(UserEntity user) {
//        return user.
//                getRoles().
//                stream().
//                map(UserRoleEntity::getRole).
//                anyMatch(r -> r == UserRoleEnum.ADMIN);
//    }


}
