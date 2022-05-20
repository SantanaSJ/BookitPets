package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.model.service.SummaryBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.service.TitleBookingServiceModel;
import com.example.onlinehotelbookingsystem.repository.*;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.RoomService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository mockBookingRepository;

    @Mock
    private BookedRoomsRepository mockBookedRoomsRepository;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private AccommodationRepository mockAccommodationRepository;

    @Mock
    private PaymentRepository mockPaymentRepository;

//    @Mock
//    private BookingHistoryRepository mockBookingHistoryRepository;
//
//    @Mock
//    RoomHistoryRepository mockRoomHistoryRepository;

    private RoomService roomService;

    private BookingService bookingServiceToTest;
    private UserEntity testUserEntity;
    private AccommodationEntity testAccommodationEntity;
    private BookingEntity testBookingEntity;
    private BookingEntity testCancelledBookingEntity;
    private BookingEntity testCompletedBookingEntity;
    private List<BookingEntity> bookingEntityList;
    private List<BookingEntity> bookingHistoryEntityList;


    @BeforeEach
    void setUp() {
        //ARRANGE

        this.testUserEntity = getTestUser();
        this.testAccommodationEntity = getTestAccommodation();
        this.testBookingEntity = getBookingEntity();
        this.testCancelledBookingEntity = getCancelledBooking();
        this.testCompletedBookingEntity = getCompletedBooking();

        this.bookingEntityList = new ArrayList<>();
        this.bookingEntityList.add(this.testBookingEntity);
        this.bookingHistoryEntityList = new ArrayList<>();
        this.bookingHistoryEntityList.add(this.testCancelledBookingEntity);
        this.bookingHistoryEntityList.add(this.testCompletedBookingEntity);

        this.bookingServiceToTest = new BookingServiceImpl(new ModelMapper(), this.mockBookingRepository,
                this.mockBookedRoomsRepository, this.mockUserRepository, this.mockAccommodationRepository,
                this.roomService, this.mockPaymentRepository);
    }

    private BookingEntity getCompletedBooking() {
        this.testCompletedBookingEntity = new BookingEntity();
        this.testCompletedBookingEntity
                .setCompleted(true)
                .setEmail(TEST_USER_EMAIL)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setCancelledOn(LocalDateTime.now())
                .setCheckIn(TEST_VALID_CHECKIN)
                .setCheckOut(TEST_VALID_CHECKOUT)
                .setGuest(this.testUserEntity)
                .setLastName(TEST_USER_LAST_NAME)
                .setProperty(this.testAccommodationEntity)
                .setPayment(new PaymentEntity().setStatusEnum(PaymentStatusEnum.UNPAID))
                .setId(TEST_COMPLETED_BOOKING_ID);

        return this.testCompletedBookingEntity;
    }

    private BookingEntity getCancelledBooking() {

        this.testCancelledBookingEntity = new BookingEntity();
        this.testCancelledBookingEntity
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
                .setId(TEST_CANCELLED_BOOKING_ID);
        return this.testCancelledBookingEntity;
    }

    @Test
    void findAllActiveBookingsByCurrentUserId_should_return_a_list_of_entities_when_entities_exist() {
//        arrange
        when(this.mockBookingRepository.findAllActiveBookingsBy(TEST_USER_ID)).thenReturn(this.bookingEntityList);
//        act
        List<TitleBookingServiceModel> allActiveBookingsByCurrentUserId = this.bookingServiceToTest.findAllActiveBookingsByCurrentUserId(this.testUserEntity.getId());
//        assert
        assertEquals(1, allActiveBookingsByCurrentUserId.size());
    }

    @Test
    void findAllActiveBookingsByCurrentUserId_should_return_empty_list_when_no_entities_exist() {
        this.bookingEntityList.clear();
        when(this.mockBookingRepository.findAllActiveBookingsBy(TEST_USER_ID)).thenReturn(this.bookingEntityList);

        List<TitleBookingServiceModel> allActiveBookingsByCurrentUserId = this.bookingServiceToTest.findAllActiveBookingsByCurrentUserId(this.testBookingEntity.getId());
        assertEquals(0, allActiveBookingsByCurrentUserId.size());
    }

    @Test
    void findActiveBookingById_should_return_booking_entity_when_entity_with_given_id_exists() {
        when(this.mockBookingRepository.findActiveBookingById(TEST_BOOKING_ID)).thenReturn(Optional.of(this.testBookingEntity));
        SummaryBookingServiceModel byId = this.bookingServiceToTest.findActiveBookingById(this.testBookingEntity.getId());

        assertEquals(byId.getBookingId(), TEST_BOOKING_ID);
    }

    @Test
    void findActiveBookingById_should_throw_exception_when_booking_with_given_id_does_not_exist() {
        when(this.mockBookingRepository.findActiveBookingById(TEST_INVALID_BOOKING_ID))
                .thenThrow(new ObjectNotFoundException("Booking with id " + TEST_INVALID_BOOKING_ID + " not found!"));
        assertThrows(ObjectNotFoundException.class, () -> this.bookingServiceToTest.findActiveBookingById(TEST_INVALID_BOOKING_ID));
    }

    @Test
    void isOwner_should_throw_exception_when_entity_with_given_id_does_not_exist() {
        when(this.mockBookingRepository
                .findActiveBookingById(TEST_BOOKING_ID))
                .thenThrow(new ObjectNotFoundException("Booking with id " + TEST_BOOKING_ID + " not found!"));

        assertThrows(ObjectNotFoundException.class, () -> this.bookingServiceToTest.isOwner(TEST_USER_EMAIL, TEST_BOOKING_ID));
    }

    @Test
    void isOwner_should_return_true_when_hotel_with_given_id_exists_and_user_with_given_email_exist() {
        when(this.mockBookingRepository
                .findActiveBookingById(TEST_BOOKING_ID))
                .thenReturn(Optional.of(this.testBookingEntity));

        assertTrue(this.bookingServiceToTest.isOwner(TEST_USER_EMAIL, TEST_BOOKING_ID));
    }

    @Test
    void isOwner_should_return_false_when_hotel_with_given_id_exists_and_user_with_given_email_does_not_exist() {
        when(this.mockBookingRepository
                .findActiveBookingById(TEST_BOOKING_ID))
                .thenReturn(Optional.of(this.testBookingEntity));

        assertFalse(this.bookingServiceToTest.isOwner(TEST_USER_NON_EXISTING_EMAIL, TEST_BOOKING_ID));
    }

    @Test
    void isOwnerHistory_should_throw_exception_when_booking_with_given_id_does_not_exist() {
        when(this.mockBookingRepository
                .findPassedBookingById(TEST_INVALID_BOOKING_ID))
                .thenThrow(new ObjectNotFoundException("Booking with id " + TEST_INVALID_BOOKING_ID + " not found!"));

        assertThrows(ObjectNotFoundException.class, () -> this.bookingServiceToTest.isOwnerHistory(TEST_USER_EMAIL, TEST_INVALID_BOOKING_ID));
    }

    @Test
    void isOwnerHistory_should_return_true_when_booking_with_given_id_exists_and_user_with_given_email_exist() {

        when(this.mockBookingRepository
                .findPassedBookingById(TEST_COMPLETED_BOOKING_ID))
                .thenReturn(Optional.of(this.testCompletedBookingEntity));

        assertTrue(this.bookingServiceToTest.isOwnerHistory(TEST_USER_EMAIL, TEST_COMPLETED_BOOKING_ID));
    }

    @Test
    void isOwnerHistory_should_return_false_when_booking_with_given_id_exists_and_user_with_given_email_does_not_exist() {
        when(this.mockBookingRepository
                .findPassedBookingById(TEST_COMPLETED_BOOKING_ID))
                .thenReturn(Optional.of(this.testBookingEntity));

        assertFalse(this.bookingServiceToTest.isOwnerHistory(TEST_USER_NON_EXISTING_EMAIL, TEST_COMPLETED_BOOKING_ID));
    }

    @Test
    void findAllPassedBookingsByUserId_should_return_a_list_of_booking_entities_when_entities_exist() {
        when(this.mockBookingRepository.findAllCompleteAndCancelledBookingsByUserId(this.testUserEntity.getId()))
                .thenReturn(this.bookingHistoryEntityList);

        List<TitleBookingServiceModel> allBookingsByUserId = this.bookingServiceToTest.findAllPassedBookingsByUserId(this.testUserEntity.getId());

        assertEquals(2, allBookingsByUserId.size());

    }

    @Test
    void findPassedBookingById_should_return_entity_when_entity_with_matching_id_exists() {
        when(this.mockBookingRepository
                .findPassedBookingById(this.testCancelledBookingEntity.getId()))
                .thenReturn(Optional.of(this.testCancelledBookingEntity));

        SummaryBookingServiceModel byId = this.bookingServiceToTest.findPassedBookingById(this.testCancelledBookingEntity.getId());

        assertNotNull(byId);
        assertEquals(TEST_CANCELLED_BOOKING_ID, byId.getBookingId());
    }

    @Test
    void findPassedBookingById_should_throw_exception_when_entity_with_matching_id_does_not_exist() {
        when(this.mockBookingRepository.findPassedBookingById(TEST_INVALID_BOOKING_ID))
                .thenThrow(new ObjectNotFoundException("id not found!"));

        assertThrows(ObjectNotFoundException.class, () -> this.bookingServiceToTest.findPassedBookingById(TEST_INVALID_BOOKING_ID));
    }

    private BookingEntity getBookingEntity() {
        this.testBookingEntity = new BookingEntity();
        this.testBookingEntity
                .setBookingTime(LocalDateTime.now())
                .setEmail(TEST_USER_EMAIL)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setCheckIn(TEST_VALID_CHECKIN)
                .setCheckOut(TEST_VALID_CHECKOUT)
                .setGuest(this.testUserEntity)
                .setLastName(TEST_USER_LAST_NAME)
                .setProperty(this.testAccommodationEntity)
                .setPayment(new PaymentEntity().setStatusEnum(PaymentStatusEnum.UNPAID))
                .setId(TEST_BOOKING_ID);
        return this.testBookingEntity;

    }

    private AccommodationEntity getTestAccommodation() {
        this.testAccommodationEntity = new AccommodationEntity();
        this.testAccommodationEntity
                .setName(TEST_HOTEL_NAME)
                .setType(new AccommodationTypeEntity().setType(AccommodationTypeEnum.HOTEL))
                .setCity(TEST_HOTEL_CITY)
                .setAddress(TEST_HOTEL_ADDRESS)
                .setPostalCode(TEST_HOTEL_PK)
                .setImageUrl(TEST_HOTEL_IMAGE)
                .setId(TEST_HOTEL_ID);
        return this.testAccommodationEntity;
    }

    private UserEntity getTestUser() {
        this.testUserEntity = new UserEntity();
        this.testUserEntity
                .setPassword(TEST_USER_PASSWORD)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setLastName(TEST_USER_LAST_NAME)
                .setEmail(TEST_USER_EMAIL)
                .setRoles(Set.of(new UserRoleEntity().setRole(UserRoleEnum.USER)))
                .setPhoneNumber(TEST_USER_PHONE)
                .setId(TEST_USER_ID);
        return this.testUserEntity;
    }

}