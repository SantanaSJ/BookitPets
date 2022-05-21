package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.service.*;
import com.example.onlinehotelbookingsystem.repository.*;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.RoomService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import com.example.onlinehotelbookingsystem.web.responseMessages.RoomMessages;
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

import static com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum.PAID;
import static com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum.UNPAID;

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
//    private final BookingHistoryRepository bookingHistoryRepository;
//    private final RoomHistoryRepository roomHistoryRepository;

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
//        this.bookingHistoryRepository = bookingHistoryRepository;
//        this.roomHistoryRepository = roomHistoryRepository;
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

    @Override
    public RoomMessages checkAvailability(AvailabilityServiceModel model) {

        RoomMessages roomMessages = new RoomMessages();
//        TODO:  if 1 single is not available and 1 double is available??
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
    public List<TitleBookingServiceModel> findAllActiveBookingsByCurrentUserId(Long id) {

        List<BookingEntity> entities = this.bookingRepository.findAllActiveBookingsBy(id);

        return mapToTitleServiceModel(entities);
    }

    @Override
    public void update(BookingUpdateServiceModel updateServiceModel) {
        BookingEntity bookingEntity = getActiveBooking(updateServiceModel.getId());

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
    public SummaryBookingServiceModel findActiveBookingById(Long bookingId) {
        BookingEntity bookingEntity = getActiveBooking(bookingId);
        SummaryBookingServiceModel serviceModel = mapToTSummaryBookingServiceModel(bookingEntity);
        return serviceModel;
    }

    @Override
    public BookingUpdateServiceModel findBookingUpdateServiceById(Long bookingId) {
        BookingEntity bookingEntity = getActiveBooking(bookingId);
        BookingUpdateServiceModel serviceModel = this.mapper.map(bookingEntity, BookingUpdateServiceModel.class);
        return serviceModel;
    }

//    @Override
//    public void delete(Long id) {
//        this.bookingRepository.deleteById(id);
//        System.out.println();
//    }

    public boolean isOwner(String currentUserEmail, Long bookingId) {
        BookingEntity bookingEntity = this.bookingRepository
                .findActiveBookingById(bookingId)
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

    @Override
    public void countBookings(Long id) {
        BookingEntity bookingEntity = getActiveBooking(id);
//        bookingEntity.setCountBookings(bookingEntity.incrementBookings());
        int countBookings = bookingEntity.getCountBookings();
        int totalNights = (int) bookingEntity.getTotalNights();

        boolean isDiscountApplicable = isDiscountApplicable(countBookings, totalNights);

        if (isDiscountApplicable) {
            applyDiscount(totalNights, bookingEntity);
//            long totalNights = bookingEntity.getTotalNights();
        }
    }


    @Override
    public Page<SummaryBookingServiceModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {

// sortField paymentStatus
        Sort sort =
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<BookingEntity> page = this.bookingRepository.findAllActive(pageable);
        Page<SummaryBookingServiceModel> map = page.map(entity -> mapToTSummaryBookingServiceModel(entity));
        return map;
    }

    @Override
    public void setBookingAsComplete() {
//        this.bookingRepository.moveCompletedBookingsToHistory();
        List<BookingEntity> bookingEntities = this.bookingRepository.findAllActiveBookingsWithCheckOutToday();

        for (BookingEntity bookingEntity : bookingEntities) {
            bookingEntity.setCompleted(true);
//            if (bookingEntity.getCheckOut().equals(LocalDate.now())) {
//                BookingHistoryEntity bookingHistoryEntity = setBookingHistoryEntity(bookingEntity);
//                bookingHistoryEntity
//                        .setCancelled(false)
//                        .setCompleted(true);
//
//                RoomsHistoryEntity roomsHistoryEntity = new RoomsHistoryEntity();
//                List<RoomsHistoryEntity> roomsHistoryList = new ArrayList<>();
//                setRoomHistoryList(bookingEntity, bookingHistoryEntity, roomsHistoryList, roomsHistoryEntity);
//
//                this.roomHistoryRepository.save(roomsHistoryEntity);
//                this.bookingHistoryRepository.save(bookingHistoryEntity);

//                deleteCompletedBookings();
            this.bookingRepository.save(bookingEntity);

        }
    }

//    @Override
//    public BookingServiceModel findBookingById(Long id) {
//
//        BookingEntity entity = getActiveBooking(id);
//        BookingServiceModel serviceModel = this.mapper.map(entity, BookingServiceModel.class);
//        serviceModel.setPaymentStatus(entity.getPayment().getStatusEnum().name());
//        return serviceModel;
//    }

    @Override
    public void setPaymentStatus(Long bookingId) {
        BookingEntity bookingEntity = getActiveBooking(bookingId);
        PaymentEntity payment = bookingEntity.getPayment();
        payment.setStatusEnum(PAID);
    }

    @Override
    public void setBookingAsCancelled(Long id) {
        BookingEntity bookingEntity = getActiveBooking(id);
        bookingEntity
                .setCancelled(true)
                .setCancelledOn(LocalDateTime.now());
        this.bookingRepository.save(bookingEntity);

//        BookingHistoryEntity bookingHistoryEntity = setBookingHistoryEntity(bookingEntity);
//        bookingHistoryEntity
//                .setCancelled(true)
//                .setCompleted(false);
//
//        List<RoomsHistoryEntity> roomsHistoryList = new ArrayList<>();
//        RoomsHistoryEntity roomsHistoryEntity = new RoomsHistoryEntity();
//
//        setRoomHistoryList(bookingEntity, bookingHistoryEntity, roomsHistoryList, roomsHistoryEntity);
//
//        bookingHistoryEntity.setRoomsHistoryEntity(roomsHistoryList);
//
//        this.bookingHistoryRepository.save(bookingHistoryEntity);
//        this.roomHistoryRepository.save(roomsHistoryEntity);

    }

    @Override
    public boolean isOwnerHistory(String currentUserEmail, Long id) {

        BookingEntity bookingEntity = this.bookingRepository
                .findPassedBookingById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Booking with id " + id + " not found!"));
        String email = bookingEntity.getGuest().getEmail();
        if (email.equals(currentUserEmail)) {
            return true;
        }
        return false;
    }

    @Override
    public Page<SummaryBookingServiceModel> findPaginatedPassedBookings(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort =
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<BookingEntity> page = this.bookingRepository.findAllCompletedAndCancelledBookings(pageable);
        Page<SummaryBookingServiceModel> map = page.map(this::mapToTSummaryBookingServiceModel);
        return map;

    }

    @Override
    public SummaryBookingServiceModel findPassedBookingById(Long id) {
        BookingEntity bookingEntity = this.bookingRepository.findPassedBookingById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Booking with id " + id + " not found!"));
        SummaryBookingServiceModel summaryBookingServiceModel = mapToTSummaryBookingServiceModel(bookingEntity);
        return summaryBookingServiceModel;

    }

    @Override
    public List<TitleBookingServiceModel> findAllPassedBookingsByUserId(Long userId) {
        List<BookingEntity> all = this.bookingRepository.findAllCompleteAndCancelledBookingsByUserId(userId);
        List<TitleBookingServiceModel> serviceModels = mapToTitleServiceModel(all);
        return serviceModels;
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
                .setProperty(acEntity)
                .setFirstName(serviceModel.getFirstName())
                .setLastName(serviceModel.getLastName())
                .setCancelled(false)
                .setCompleted(false)
                .setGuest(userEntity);
//                .setDateNow();

        return bookingEntity;
    }

//    private void setRoomHistoryList(BookingEntity bookingEntity, BookingHistoryEntity bookingHistoryEntity, List<RoomsHistoryEntity> roomsHistoryEntities, RoomsHistoryEntity roomsHistoryEntity) {
//        for (BookedRoomsEntity bookedRoom : bookingEntity.getBookedRooms()) {
//            roomsHistoryEntity
//                    .setPrice(bookedRoom.getPrice())
//                    .setBookingHistory(bookingHistoryEntity)
//                    .setRoom(bookedRoom.getRoom())
//                    .setNumberOfRooms(bookedRoom.getNumberOfRooms());
//            roomsHistoryEntities.add(roomsHistoryEntity);
//        }
//    }

//    private BookingHistoryEntity setBookingHistoryEntity(BookingEntity bookingEntity) {
//        BookingHistoryEntity bookingHistoryEntity = new BookingHistoryEntity();
//        bookingHistoryEntity
//                .setPayment(bookingEntity.getPayment())
//                .setBookingTime(bookingEntity.getBookingTime())
//                .setCheckOut(bookingEntity.getCheckOut())
//                .setCheckIn(bookingEntity.getCheckIn())
//                .setTotalNights(bookingEntity.getTotalNights())
//                .setPetName(bookingEntity.getPetName())
//                .setEmail(bookingEntity.getEmail())
//                .setPetKilograms(bookingEntity.getPetKilograms())
//                .setPhoneNumber(bookingEntity.getPhoneNumber())
//                .setTotalPrice(bookingEntity.getTotalPrice())
//                .setLastName(bookingEntity.getLastName())
//                .setFirstName(bookingEntity.getFirstName())
//                .setProperty(bookingEntity.getProperty())
//                .setGuest(bookingEntity.getGuest())
//                .setCancelledOn(LocalDateTime.now())
//                .setLat(bookingEntity.getProperty().getLat())
//                .setLng(bookingEntity.getProperty().getLng())
//                .setComments(bookingHistoryEntity.getComments())
//                .setUpdated(bookingEntity.getUpdated());
//        return bookingHistoryEntity;
//    }

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

    private BookingEntity getActiveBooking(Long bookingId) {

        BookingEntity bookingEntity = this.bookingRepository
                .findActiveBookingById(bookingId)
                .orElseThrow(() -> new ObjectNotFoundException("Booking with id " + bookingId + " not found!"));
        return bookingEntity;
    }

//    private void deleteCompletedBookings() {
//        this.bookingRepository.deleteAllCompleted();
//    }

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
        serviceModel.setLat(entity.getProperty().getLat());
        serviceModel.setLastUpdated(entity.getUpdated());
        serviceModel.setCreatedAt(entity.getBookingTime());
        serviceModel.setLng(entity.getProperty().getLng());

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
