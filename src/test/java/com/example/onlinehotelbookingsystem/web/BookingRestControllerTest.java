//package com.example.onlinehotelbookingsystem.web;
//
//import com.example.onlinehotelbookingsystem.model.entity.*;
//import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
//import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;
//import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
//import com.example.onlinehotelbookingsystem.repository.*;
//import com.example.onlinehotelbookingsystem.security.CustomUser;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.test.context.support.TestExecutionEvent;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static com.example.onlinehotelbookingsystem.constants.Constants.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class BookingRestControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private AccommodationRepository accommodationRepository;
//
//    @Autowired
//    private AccommodationTypeRepository accommodationTypeRepository;
//
//    @Autowired
//    private UserRoleRepository userRoleRepository;
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Autowired
//    private BookedRoomsRepository bookedRoomsRepository;
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//    private final CustomUser customUserDetails = new CustomUser
//            (TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_ID, TEST_USER_FIRST_NAME,
//                    List.of(new SimpleGrantedAuthority("USER")));
//
//    @BeforeEach
//    public void setUp() {
//        UserEntity testUser = getTestUser();
//        UserEntity testUser1 = getTestUser();
//        AccommodationEntity accommodationEntity = getAccommodationEntity();
//        AccommodationEntity accommodationEntity1 = getAccommodationEntity();
//        BookingEntity testBookingEntity = getBookingEntity(accommodationEntity, testUser, TEST_BOOKING_ID);
//        BookingEntity bookingEntity1 = getBookingEntity(accommodationEntity1, testUser1, TEST_BOOKING_ID1);
//        Long id = testBookingEntity.getId();
//        Long id1 = bookingEntity1.getId();
//        long count = this.bookingRepository.count();
//        System.out.println();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        this.userRepository.deleteAll();
//    }
//
//
//
//    @Test
//    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    void when_all_bookings_is_accessed_by_authorized_user_return_status_200() throws Exception {
//        this.mockMvc
//                .perform(get("/all-bookings"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)));
//    }
//
//    @Test
//    void when_all_bookings_is_accessed_by_unauthenticated_user_return_login_form() throws Exception {
//        this.mockMvc
//                .perform(get("/all-bookings"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("http://localhost/users/login"));
//
//    }
//
//
////    mock stored procedure in H2
////    https://stackoverflow.com/questions/46408493/dbunit-jdbcsqlexception-function-not-found
////    https://stackoverflow.com/questions/70740123/calling-h2-function-as-spring-data-jpa-procedure
////    @Test
////    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
////    void when_booking_is_deleted_expect_status_204() throws Exception {
////
////        this.mockMvc
////                .perform(delete("/delete/2")
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk());
////
////    }
//
//    private BookingEntity getBookingEntity(AccommodationEntity accommodationEntity, UserEntity userEntity, Long id) {
//
//        RoomEntity roomEntity = getRoomEntity(accommodationEntity);
//        PaymentEntity paymentEntity = getPaymentEntity();
//
//        BookingEntity bookingEntity = new BookingEntity();
//
//        List<BookedRoomsEntity> bookedRoomsEntities = new ArrayList<>();
//        BookedRoomsEntity bookedRooms = new BookedRoomsEntity();
//
//        bookingEntity
//                .setGuest(userEntity)
//                .setProperty(accommodationEntity)
//                .setFirstName(TEST_USER_FIRST_NAME)
//                .setLastName(TEST_USER_LAST_NAME)
//                .setTotalPrice(TEST_BOOKING_PRICE)
//                .setPhoneNumber(TEST_USER_PHONE)
//                .setEmail(TEST_USER_EMAIL)
//                .setPetKilograms(TEST_USER_PET_KG)
//                .setPetName(TEST_USER_PET_NAME)
//                .setCheckIn(TEST_VALID_CHECKIN)
//                .setCheckOut(TEST_VALID_CHECKOUT)
//                .setTotalNights(TEST_BOOKING_NIGHTS)
//                .setBookedRooms(bookedRoomsEntities)
//                .setBookingTime(TEST_BOOKING_TIME)
//                .setPayment(paymentEntity)
//                .setId(id);
//        this.bookingRepository.save(bookingEntity);
//
//
//        bookedRooms
//                .setNumberOfRooms(TEST_BOOKED_ROOMS_NUMBER_OF_ROOMS)
//                .setBooking(bookingEntity)
//                .setPrice(TEST_BOOKED_ROOMS_PRICE)
//                .setRoom(roomEntity)
//                .setId(TEST_BOOKED_ROOMS_ID);
//        bookedRoomsEntities.add(bookedRooms);
//        this.bookedRoomsRepository.save(bookedRooms);
//
//        return bookingEntity;
//    }
//
//    private PaymentEntity getPaymentEntity() {
//        PaymentEntity paymentEntity = new PaymentEntity();
//        paymentEntity.setStatusEnum(PaymentStatusEnum.UNPAID);
//        this.paymentRepository.save(paymentEntity);
//        return paymentEntity;
//    }
//
//    private RoomEntity getRoomEntity(AccommodationEntity accommodationEntity) {
//        RoomEntity roomEntity = new RoomEntity();
//        roomEntity
//                .setRoomType(TEST_ROOM_TYPE)
//                .setAccommodationEntity(accommodationEntity)
//                .setCurrentPrice(TEST_ROOM_CURRENT_PRICE)
//                .setDescription(TEST_ROOM_DESCRIPTION)
//                .setId(TEST_ROOM_ID);
//        this.roomRepository.save(roomEntity);
//        return roomEntity;
//    }
//
//    private AccommodationEntity getAccommodationEntity() {
//
//        AccommodationTypeEntity accommodationTypeEntity = getAccommodationTypeEntity();
//        AccommodationEntity accommodationEntity = new AccommodationEntity();
//        accommodationEntity
//                .setName(TEST_HOTEL_NAME)
//                .setType(accommodationTypeEntity)
//                .setCity(TEST_HOTEL_CITY)
//                .setAddress(TEST_HOTEL_ADDRESS)
//                .setPostalCode(TEST_HOTEL_PK)
//                .setImageUrl(TEST_HOTEL_IMAGE)
//                .setId(TEST_HOTEL_ID);
//        this.accommodationRepository.save(accommodationEntity);
//        return accommodationEntity;
//    }
//
//    private UserEntity getTestUser() {
//        UserRoleEntity userRoleEntity = getUserRoleEntity();
//
//        UserEntity userEntity = new UserEntity();
//        userEntity
//                .setPassword(TEST_USER_PASSWORD)
//                .setFirstName(TEST_USER_FIRST_NAME)
//                .setLastName(TEST_USER_LAST_NAME)
//                .setEmail(TEST_USER_EMAIL)
//                .setRoles(Set.of(userRoleEntity))
//                .setPhoneNumber(TEST_USER_PHONE)
//                .setId(TEST_USER_ID);
//
//        return this.userRepository.save(userEntity);
//    }
//
//    private UserRoleEntity getUserRoleEntity() {
//        UserRoleEntity userRoleEntity = new UserRoleEntity();
//        userRoleEntity.setRole(UserRoleEnum.USER);
//        this.userRoleRepository.save(userRoleEntity);
//        return userRoleEntity;
//    }
//
//    private AccommodationTypeEntity getAccommodationTypeEntity() {
//        AccommodationTypeEntity accommodationTypeEntity = new AccommodationTypeEntity();
//        accommodationTypeEntity.setType(AccommodationTypeEnum.HOTEL);
//        this.accommodationTypeRepository.save(accommodationTypeEntity);
//        return accommodationTypeEntity;
//    }
//
//
//
//
//
//}