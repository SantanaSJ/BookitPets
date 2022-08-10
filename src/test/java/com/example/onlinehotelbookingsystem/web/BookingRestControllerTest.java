package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.repository.*;
import com.example.onlinehotelbookingsystem.service.BookingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookedRoomsRepository bookedRoomsRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingService bookingService;

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
    void when_all_bookings_is_accessed_by_unauthenticated_user_return_login_form() throws Exception {
        this.testBookingEntity = getBookingEntity();
//        this.bookingEntityList = List.of(this.testBookingEntity);
//        Mockito.when(this.bookingRepository.findAllActiveBookingsBy(this.testBookingEntity.getId()))
//                .thenReturn(this.bookingEntityList);

        this.mockMvc
                .perform(get("/all-bookings"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void when_all_bookings_is_accessed_by_authorized_user_should_return_status_200() throws Exception {
        this.mockMvc
                .perform(get("/all-bookings"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void all_bookings_should_return_correct_content_type() throws Exception {
        this.mockMvc
                .perform(get("/all-bookings"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void all_bookings_should_return_correct_number_of_bookings() throws Exception {
       this.testAccommodationEntity = getAccommodationEntity();
        this.testBookingEntity = getBookingEntity();

        this.mockMvc
                .perform(get("/all-bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void all_bookings_should_return_correct_entities() throws Exception {
        this.testAccommodationEntity = getAccommodationEntity();
        this.testBookingEntity = getBookingEntity();
        long count = this.bookingRepository.count();
        System.out.println();

        this.mockMvc
                .perform(get("/all-bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookingId", is(this.testBookingEntity.getId().intValue())))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
    void delete_booking() throws Exception {
        this.testBookingEntity = getBookingEntity();
        assertEquals(1, this.bookingRepository.count());

        this.mockMvc
                .perform(delete("/delete/" + this.testBookingEntity.getId())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isNoContent());

        Optional<BookingEntity> passedBookingById = this.bookingRepository.findPassedBookingById(this.testBookingEntity.getId());

        assertTrue(passedBookingById.get().isCancelled());
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
        return this.bookingRepository.save(this.testBookingEntity);

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
        this.testAccommodationEntity = new AccommodationEntity();
        this.testAccommodationEntity
                .setName(TEST_HOTEL_NAME)
                .setType(accommodationTypeEntity)
                .setCity(TEST_HOTEL_CITY)
                .setAddress(TEST_HOTEL_ADDRESS)
                .setPostalCode(TEST_HOTEL_PK)
                .setImageUrl(TEST_HOTEL_IMAGE)
                .setId(TEST_HOTEL_ID);
        AccommodationEntity saved = this.accommodationRepository.save(this.testAccommodationEntity);
        return saved;
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