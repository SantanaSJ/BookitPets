package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookingRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookedRoomsRepository bookedRoomsRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    private BookingEntity testBookingEntity;
    private AccommodationEntity testAccommodationEntity;
    private UserEntity testUser;
    private List<BookingEntity> bookingEntityList;


    @BeforeEach
    public void setUp() {
        this.testUser = getTestUser();
    }

    @AfterEach
    public void tearDown() {
        this.userRepository.deleteAll();
        this.bookingRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void when_all_bookings_is_accessed_by_authorized_user_return_status_200() throws Exception {
        this.testAccommodationEntity = getAccommodationEntity();
        this.testBookingEntity = getBookingEntity();

        this.bookingEntityList = List.of(this.testBookingEntity);

        Mockito.when(this.bookingRepository.findAllActiveBookingsBy(this.testUser.getId()))
                .thenReturn(this.bookingEntityList);

        this.mockMvc
                .perform(get("/all-bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void when_all_bookings_is_accessed_by_unauthenticated_user_return_login_form() throws Exception {
        this.testBookingEntity = getBookingEntity();

        this.bookingEntityList = List.of(this.testBookingEntity);

        Mockito.when(this.bookingRepository.findAllActiveBookingsBy(this.testBookingEntity.getId()))
                .thenReturn(this.bookingEntityList);

        this.mockMvc
                .perform(get("/all-bookings"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));

    }

    private BookingEntity getBookingEntity() {

        RoomEntity roomEntity = getRoomEntity();

        List<BookedRoomsEntity> bookedRoomsEntities = new ArrayList<>();
        BookedRoomsEntity bookedRooms = new BookedRoomsEntity();
        bookedRooms
                .setNumberOfRooms(1)
                .setBooking(this.testBookingEntity)
                .setPrice(TEST_BOOKED_ROOM_PRICE)
                .setRoom(roomEntity)
                .setNumberOfRooms(TEST_BOOKED_ROOM_NUMBER_OF_ROOMS)
                .setId(TEST_BOOKED_ROOM_ID);
        bookedRoomsEntities.add(bookedRooms);
        this.bookedRoomsRepository.save(bookedRooms);

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setStatusEnum(PaymentStatusEnum.UNPAID);
        this.paymentRepository.save(paymentEntity);


        this.testBookingEntity = new BookingEntity();
        this.testBookingEntity
                .setGuest(this.testUser)
                .setProperty(this.testAccommodationEntity)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setLastName(TEST_USER_LAST_NAME)
                .setTotalPrice(BigDecimal.valueOf(200))
                .setPhoneNumber(TEST_USER_PHONE)
                .setEmail(TEST_USER_EMAIL)
                .setPetKilograms(TEST_USER_PET_KG)
                .setPetName(TEST_USER_PET_NAME)
                .setCheckIn(TEST_VALID_CHECKIN)
                .setCheckOut(TEST_VALID_CHECKOUT)
                .setTotalNights(TEST_BOOKING_NIGHTS)
                .setBookedRooms(bookedRoomsEntities)
                .setBookingTime(LocalDateTime.now())
                .setPayment(paymentEntity)
                .setId(TEST_BOOKING_ID);
        this.bookingRepository.save(this.testBookingEntity);

        return this.testBookingEntity;
    }


//    private PaymentEntity getPaymentEntity() {
//        PaymentEntity paymentEntity = new PaymentEntity();
//        paymentEntity.setStatusEnum(PaymentStatusEnum.UNPAID);
//        this.paymentRepository.save(paymentEntity);
//        return paymentEntity;
//    }

    private RoomEntity getRoomEntity() {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity
                .setRoomType(TEST_ROOM_TYPE)
                .setAccommodationEntity(this.testAccommodationEntity)
                .setCurrentPrice(TEST_ROOM_CURRENT_PRICE)
                .setDescription(TEST_ROOM_DESCRIPTION)
                .setId(TEST_ROOM_ID);
        this.roomRepository.save(roomEntity);
        return roomEntity;
    }

    private AccommodationEntity getAccommodationEntity() {

        AccommodationTypeEntity accommodationTypeEntity = getAccommodationTypeEntity();
        AccommodationEntity accommodationEntity = new AccommodationEntity();
        accommodationEntity
                .setName(TEST_HOTEL_NAME)
                .setType(accommodationTypeEntity)
                .setCity(TEST_HOTEL_CITY)
                .setAddress(TEST_HOTEL_ADDRESS)
                .setPostalCode(TEST_HOTEL_PK)
                .setImageUrl(TEST_HOTEL_IMAGE)
                .setId(TEST_HOTEL_ID);
        this.accommodationRepository.save(accommodationEntity);
        return accommodationEntity;
    }

    private UserEntity getTestUser() {
        UserRoleEntity userRoleEntity = getUserRoleEntity();

        UserEntity userEntity = new UserEntity();
        userEntity
                .setPassword(TEST_USER_PASSWORD)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setLastName(TEST_USER_LAST_NAME)
                .setEmail(TEST_USER_EMAIL)
                .setRoles(Set.of(userRoleEntity))
                .setPhoneNumber(TEST_USER_PHONE)
                .setId(TEST_USER_ID);

        return this.userRepository.save(userEntity);
    }

    private UserRoleEntity getUserRoleEntity() {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRoleEnum.USER);
        this.userRoleRepository.save(userRoleEntity);
        return userRoleEntity;
    }

    private AccommodationTypeEntity getAccommodationTypeEntity() {
        AccommodationTypeEntity accommodationTypeEntity = new AccommodationTypeEntity();
        accommodationTypeEntity.setType(AccommodationTypeEnum.HOTEL);
        this.accommodationTypeRepository.save(accommodationTypeEntity);
        return accommodationTypeEntity;
    }


}