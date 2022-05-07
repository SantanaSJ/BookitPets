package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.model.service.SummaryBookingServiceModel;
import com.example.onlinehotelbookingsystem.repository.BookingHistoryRepository;
import com.example.onlinehotelbookingsystem.security.CustomUser;
import com.example.onlinehotelbookingsystem.service.BookingHistoryService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingHistoryServiceImplTest {

    @Mock
    BookingHistoryRepository mockBookingHistoryRepository;

    private final CustomUser customUserDetails = new CustomUser
            (TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_ID, TEST_USER_FIRST_NAME,
                    List.of(new SimpleGrantedAuthority("USER")));


    BookingHistoryService bookingHistoryServiceToTest;
    private BookingHistoryEntity testBookingHistoryEntity;
    private UserEntity testUserEntity;
    private List<BookingHistoryEntity> bookingHistoryEntities;

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
                .setType(AccommodationTypeEnum.HOTEL)
                .setCity(TEST_HOTEL_CITY)
                .setAddress(TEST_HOTEL_ADDRESS)
                .setPostalCode(TEST_HOTEL_PK)
                .setImageUrl(TEST_HOTEL_IMAGE)
                .setId(TEST_HOTEL_ID);


        this.testBookingHistoryEntity = new BookingHistoryEntity();
        this.testBookingHistoryEntity
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

        this.bookingHistoryEntities = new ArrayList<>();
        this.bookingHistoryEntities.add(this.testBookingHistoryEntity);

        this.bookingHistoryServiceToTest = new BookingHistoryServiceImpl(this.mockBookingHistoryRepository, new ModelMapper());

    }

    @Test
    void findById_should_return_BookingHistory_Entity_when_entity_exists() {
        when(this.mockBookingHistoryRepository
                .findById(this.testBookingHistoryEntity.getId()))
                .thenReturn(Optional.of(this.testBookingHistoryEntity));

        SummaryBookingServiceModel byId = this.bookingHistoryServiceToTest.findById(TEST_BOOKING_HISTORY_ID);

        System.out.println();
        assertNotNull(byId);
        assertEquals(TEST_BOOKING_HISTORY_ID, byId.getBookingId());
    }

    @Test
    void isOwnerHistory_should_throw_exception_when_findById_returns_null() {
        when(this.mockBookingHistoryRepository
                .findById(TEST_BOOKING_HISTORY_ID_1))
                .thenThrow(new ObjectNotFoundException("Booking with id " + TEST_BOOKING_HISTORY_ID_1 + " not found!"));

        assertThrows(ObjectNotFoundException.class, () -> this.bookingHistoryServiceToTest.findById(TEST_BOOKING_HISTORY_ID_1));

    }

    @Test
    void getAllPassedBookingsByUserId_should_return_list_of_bookingHistoryEntity_when_entities_exist() {
        when(this.mockBookingHistoryRepository.findAllByGuestId(this.testUserEntity.getId())).thenReturn(this.bookingHistoryEntities);

        this.bookingHistoryServiceToTest.findAllBookingsByUserId(this.testUserEntity.getId());

        assertEquals(1, this.bookingHistoryEntities.size());

    }

    @Test
    void findCompletedBookingBy_should_return_entity_when_entity_with_matching_id_exists() {
        when(this.mockBookingHistoryRepository
                .findById(this.testBookingHistoryEntity.getId()))
                .thenReturn(Optional.of(this.testBookingHistoryEntity));

        SummaryBookingServiceModel byId = this.bookingHistoryServiceToTest.findCompletedBookingBy(TEST_BOOKING_HISTORY_ID);

        assertNotNull(byId);
        assertEquals(TEST_BOOKING_HISTORY_ID, byId.getBookingId());
    }

    @Test
    void findCompletedBookingBy_should_throw_exception_when_entity_with_matching_id_does_not_exist() {
        when(this.mockBookingHistoryRepository.findById(TEST_BOOKING_HISTORY_ID_1))
                .thenThrow(new ObjectNotFoundException("id not found!"));

        assertThrows(ObjectNotFoundException.class, () -> this.bookingHistoryServiceToTest.findById(TEST_BOOKING_HISTORY_ID_1));
    }

}