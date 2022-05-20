package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.repository.*;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;

@ExtendWith(MockitoExtension.class)
class BookingHistoryServiceImplTest {

    @Mock
    private BookingRepository mockBookingRepository;

    @Mock
    private BookedRoomsRepository mockBookedRoomsRepository;

    @Mock
    private AccommodationRepository mockAccommodationRepository;

    @Mock
    private PaymentRepository mockPaymentRepository;


    @Mock
    private UserRepository mockUserRepository;

    private RoomService roomService;

    private BookingService bookingServiceToTest;
    private UserEntity testUserEntity;
    private List<BookingEntity> bookingEntityList;
    private BookingEntity testBookingEntity;

    @BeforeEach
    void setUp() {

        this.testUserEntity = new UserEntity();
        this.testUserEntity
                .setPassword(TEST_USER_PASSWORD)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setLastName(TEST_USER_LAST_NAME)
                .setEmail(TEST_USER_EMAIL)
                .setRoles(Set.of(new UserRoleEntity().setRole(UserRoleEnum.USER)))
                .setPhoneNumber(TEST_USER_PHONE)
                .setId(TEST_USER_ID);

        AccommodationEntity testAccommodationEntity = new AccommodationEntity();
        testAccommodationEntity
                .setName(TEST_HOTEL_NAME)
                .setType(new AccommodationTypeEntity().setType(AccommodationTypeEnum.HOTEL))
                .setCity(TEST_HOTEL_CITY)
                .setAddress(TEST_HOTEL_ADDRESS)
                .setPostalCode(TEST_HOTEL_PK)
                .setImageUrl(TEST_HOTEL_IMAGE)
                .setId(TEST_HOTEL_ID);


        this.testBookingEntity = new BookingEntity();
        this.testBookingEntity
                .setBookingTime(LocalDateTime.now())
                .setCancelled(true)
                .setEmail(TEST_USER_EMAIL)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setCancelledOn(LocalDateTime.now())
                .setCheckIn(TEST_VALID_CHECKIN)
                .setCheckOut(TEST_VALID_CHECKOUT)
                .setGuest(this.testUserEntity)
                .setLastName(TEST_USER_LAST_NAME)
                .setProperty(testAccommodationEntity)
                .setPayment(new PaymentEntity().setStatusEnum(PaymentStatusEnum.UNPAID))
                .setId(TEST_BOOKING_HISTORY_ID);

        this.bookingEntityList = new ArrayList<>();
        this.bookingEntityList.add(this.testBookingEntity);

        this.bookingServiceToTest = new BookingServiceImpl(new ModelMapper(), this.mockBookingRepository,
                this.mockBookedRoomsRepository, this.mockUserRepository, this.mockAccommodationRepository,
                this.roomService, this.mockPaymentRepository);
    }

//    @Test
//    void findById_should_return_BookingHistory_Entity_when_entity_exists() {
//        when(this.mockBookingRepository
//                .findPassedBookingById(this.testBookingEntity.getId()))
//                .thenReturn(Optional.of(this.testBookingEntity));
//
//        SummaryBookingServiceModel byId = this.bookingServiceToTest.findPassedBookingById(TEST_BOOKING_HISTORY_ID);
//
//        System.out.println();
//        assertEquals(TEST_BOOKING_HISTORY_ID, byId.getBookingId());
//    }














}