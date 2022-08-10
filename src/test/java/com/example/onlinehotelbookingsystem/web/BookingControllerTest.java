package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.model.service.SummaryBookingServiceModel;
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
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {

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

    private UserEntity testUser;
    private AccommodationEntity testAccommodationEntity;
    private BookingEntity testBookingEntity;

    @BeforeEach
    public void setUp() {
        this.testUser = getTestUser();

    }

    @AfterEach
    public void tearDown() {
        this.bookedRoomsRepository.deleteAll();
        this.roomRepository.deleteAll();
        this.bookingRepository.deleteAll();
        this.userRepository.deleteAll();
        this.accommodationRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
    void when_invalid_params_entered_in_booking_form_redirect_with_errors() throws Exception {
//        this.testUser = getTestUser();
//        this.testBookingEntity = getBookingEntity();
        this.testAccommodationEntity = getAccommodationEntity();

        Long id = this.testAccommodationEntity.getId();
        BindingResult bindingResult = (BindingResult) this.mockMvc
                .perform(get("/room-availability")
                        .param("checkIn", String.valueOf(TEST_INVALID_CHECKIN))
                        .param("checkOut", String.valueOf(TEST_INVALID_CHECKOUT))
                        .param("hotelId", String.valueOf(this.testAccommodationEntity.getId())))
                .andExpect(redirectedUrl("/booking-form/accommodation/" + id))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.bindingModel"))
                .andReturn().getFlashMap().get("org.springframework.validation.BindingResult.bindingModel");

        assertTrue(bindingResult.hasErrors());
        assertTrue(bindingResult.hasFieldErrors("checkIn"));
        assertTrue(bindingResult.hasFieldErrors("checkOut"));
    }


    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    @WithMockUser(username = TEST_USER_EMAIL, password = TEST_USER_PASSWORD, roles = "USER")
    void when_create_booking_is_opened_from_authorized_user_expect_status_200() throws Exception {


        this.mockMvc
                .perform(get("/create-booking"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-booking"));
    }

    @Test
    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
    void when_invalid_params_entered_in_create_booking_form_redirect_with_errors() throws Exception {
        BindingResult bindingResult = (BindingResult) this.mockMvc
                .perform(post("/create-booking")
                        .param("firstName", TEST_USER_INVALID_FIRST_NAME)
                        .param("lastName", TEST_USER_INVALID_LAST_NAME)
                        .param("petName", TEST_USER_INVALID_PET_NAME)
                        .param("email", TEST_USER_INVALID_EMAIL)
                        .param("petKilograms", String.valueOf(TEST_USER_INVALID_PET_KG))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("create-booking"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.bookingBindingModel"))
                .andReturn().getFlashMap().get("org.springframework.validation.BindingResult.bookingBindingModel");

        assertTrue(bindingResult.hasErrors());
        assertTrue(bindingResult.hasFieldErrors("firstName"));
        assertTrue(bindingResult.hasFieldErrors("lastName"));
        assertTrue(bindingResult.hasFieldErrors("petName"));
        assertTrue(bindingResult.hasFieldErrors("email"));
        assertTrue(bindingResult.hasFieldErrors("petKilograms"));
    }

    @Test
    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
    void when_booking_details_are_opened_by_authorized_user_expect_status_200() throws Exception {
        this.testAccommodationEntity = getAccommodationEntity();
        this.testBookingEntity = getBookingEntity();
//        SummaryBookingServiceModel activeBookingById = this.bookingService.findActiveBookingById(this.testBookingEntity.getId());


//        Mockito.when(this.bookingRepository.findActiveBookingById(this.testBookingEntity.getId()))
//                .thenReturn(Optional.of(this.testBookingEntity));

        this.mockMvc
                .perform(get("/bookings/details/" + this.testBookingEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("details"));
    }

    @Test
    @WithMockUser(value = TEST_USER_NON_EXISTING_EMAIL)
    void when_booking_details_are_opened_by_unauthorized_user_expect_status_403() throws Exception {
        this.testBookingEntity = getBookingEntity();
//        Mockito.when(this.bookingRepository.findActiveBookingById(this.testBookingEntity.getId()))
//                .thenReturn(Optional.of(this.testBookingEntity));
        this.mockMvc
                .perform(get("/bookings/details/" + this.testBookingEntity.getId()))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void when_update_booking_form_is_opened_by_authorized_user_expect_status_200() throws Exception {
        this.testBookingEntity = getBookingEntity();
//        Mockito.when(this.bookingRepository.findActiveBookingById(this.testBookingEntity.getId()))
//                    .thenReturn(Optional.of(this.testBookingEntity));
        this.mockMvc
                .perform(get("/bookings/update/" + this.testBookingEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("update"));
    }

    @Test
    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
    void when_invalid_params_entered_in_update_booking_form_redirect_with_errors() throws Exception {
        this.testAccommodationEntity = getAccommodationEntity();
        this.testBookingEntity = getBookingEntity();
        SummaryBookingServiceModel activeBookingById = this.bookingService.findActiveBookingById(this.testBookingEntity.getId());

//       Mockito.when(this.bookingRepository.findActiveBookingById(this.testBookingEntity.getId()))
//               .thenReturn(Optional.of(this.testBookingEntity));

        BindingResult bindingResult = (BindingResult) this.mockMvc
                .perform(patch("/bookings/update/" + this.testBookingEntity.getId())
                        .param("firstName", TEST_USER_INVALID_FIRST_NAME)
                        .param("lastName", TEST_USER_INVALID_LAST_NAME)
                        .param("petName", TEST_USER_INVALID_PET_NAME)
                        .param("petKilograms", String.valueOf(TEST_USER_INVALID_PET_KG))
                        .param("email", TEST_USER_INVALID_EMAIL)
                        .param("phoneNumber", TEST_USER_INVALID_PHONE)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/bookings/update/" + activeBookingById.getBookingId()))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.bookingUpdateBindingModel"))
                .andReturn().getFlashMap().get("org.springframework.validation.BindingResult.bookingUpdateBindingModel");

        assertTrue(bindingResult.hasErrors());
        assertTrue(bindingResult.hasFieldErrors("firstName"));
        assertTrue(bindingResult.hasFieldErrors("lastName"));
        assertTrue(bindingResult.hasFieldErrors("petName"));
        assertTrue(bindingResult.hasFieldErrors("email"));
        assertTrue(bindingResult.hasFieldErrors("petKilograms"));
    }

    @Test
    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
    void when_valid_params_entered_in_update_booking_form_update_user_info() throws Exception {
        this.testAccommodationEntity = getAccommodationEntity();
        this.testBookingEntity = getBookingEntity();
//        BookingEntity saved = this.bookingRepository.save(this.testBookingEntity);
        SummaryBookingServiceModel activeBookingById = this.bookingService.findActiveBookingById(this.testBookingEntity.getId());
//        Mockito.when(this.bookingRepository.findActiveBookingById(this.testBookingEntity.getId()))
//                .thenReturn(Optional.of(this.testBookingEntity));
        this.mockMvc
                .perform(patch("/bookings/update/" + this.testBookingEntity.getId())
                        .param("firstName", TEST_USER_FIRST_NAME)
                        .param("lastName", TEST_USER_LAST_NAME)
                        .param("petName", TEST_USER_PET_NAME)
                        .param("petKilograms", String.valueOf(TEST_USER_PET_KG))
                        .param("email", TEST_USER_EMAIL)
                        .param("phoneNumber", TEST_USER_PHONE)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bookings/details/" + activeBookingById.getBookingId()));

        Optional<BookingEntity> newReservation = this.bookingRepository.findActiveBookingById(this.testBookingEntity.getId());
        assertTrue(newReservation.isPresent());
        BookingEntity bookingEntity = newReservation.get();
        assertEquals(TEST_USER_FIRST_NAME, bookingEntity.getFirstName());
        assertEquals(TEST_USER_LAST_NAME, bookingEntity.getLastName());
        assertEquals(TEST_USER_PHONE, bookingEntity.getPhoneNumber());
        assertEquals(TEST_USER_EMAIL, bookingEntity.getEmail());
        assertEquals(TEST_USER_PET_NAME, bookingEntity.getPetName());
        assertEquals(TEST_USER_PET_KG, bookingEntity.getPetKilograms());
    }

//    @Test
//    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void create_new_booking() throws Exception {
//        List<String> values = Arrays.asList("1", "single", "135");
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.addAll("bookedRooms", values);
//
//        this.testAccommodationEntity = getAccommodationEntity();
//        BookingEntity testBookingEntity = getBookingEntity();
//        this.mockMvc
//                .perform(post("/create-booking")
//                        .param("firstName", testUser.getFirstName())
//                        .param("lastName", testUser.getLastName())
//                        .param("email", testUser.getEmail())
//                        .param("phoneNumber", testUser.getPhoneNumber())
//                        .param("petName", TEST_USER_PET_NAME)
//                        .param("petKilograms", String.valueOf(TEST_USER_PET_KG))
//                        .param("hotelId", String.valueOf(testAccommodationEntity.getId()))
//                        .param("checkIn", "2022-12-01")
//                        .param("checkOut", "2022-12-05")
//                        .params(params)
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/bookings/details/" + testBookingEntity.getId()));
//
//        assertEquals(1, this.bookingRepository.count());
//        BookingEntity bookingEntity = this.bookingRepository.findById(testBookingEntity.getId()).orElse(null);
//
//        assertEquals(testBookingEntity.getFirstName(), bookingEntity.getFirstName());
//    }


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

        this.testUser = new UserEntity();
        this.testUser
                .setPassword(TEST_USER_PASSWORD)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setLastName(TEST_USER_LAST_NAME)
                .setEmail(TEST_USER_EMAIL)
                .setRoles(Set.of(userRoleEntity))
                .setPhoneNumber(TEST_USER_PHONE)
                .setId(TEST_USER_ID);

        return this.userRepository.save(this.testUser);
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