package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;
import com.example.onlinehotelbookingsystem.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BookingHistoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    AccommodationTypeRepository accommodationTypeRepository;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private BookingHistoryRepository bookingHistoryRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private UserEntity testUser;
    private AccommodationEntity testAccommodationEntity;
    private BookingHistoryEntity testBookingHistoryEntity;

    @BeforeEach
    public void setUp() {
        this.testUser = getTestUser();
        this.testAccommodationEntity = getAccommodationEntity();
        this.testBookingHistoryEntity = getBookingHistoryEntity();
    }



    @AfterEach
    public void tearDown() {
        this.bookingHistoryRepository.deleteAll();
        this.userRepository.deleteAll();
//        this.userRoleRepository.deleteAll();
    }


    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void when_history_is_accessed_by_authenticated_user_expect_status_200() throws Exception {
        List<UserRoleEntity> all = this.userRoleRepository.findAll();
        System.out.println();
        this.mockMvc
                .perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("history"));
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void when_history_is_empty_return_emptyList() throws Exception {
        this.bookingHistoryRepository.deleteAll();

        this.mockMvc
                .perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("history"));

        assertEquals(0, this.bookingHistoryRepository.count());
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void when_history_is_not_empty_return_list_with_history_bookings() throws Exception {
        this.mockMvc
                .perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("history"));

        assertEquals(1, this.bookingHistoryRepository.count());
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void when_history_details_is_accessed_return_status_200() throws Exception {
        this.mockMvc
                .perform(get("/history/details/" + this.testBookingHistoryEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("history-details"))
                .andExpect(model().attributeExists("viewModel"));

    }

    private UserEntity getTestUser() {

        this.testUser = new UserEntity();
        this.testUser
                .setPassword(TEST_USER_PASSWORD)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setLastName(TEST_USER_LAST_NAME)
                .setEmail(TEST_USER_EMAIL)
                .setPhoneNumber(TEST_USER_PHONE)
                .setId(TEST_USER_ID);


        List<UserRoleEntity> all = this.userRoleRepository.findAll();
        return this.userRepository.save(this.testUser);
    }


    private AccommodationEntity getAccommodationEntity() {

        AccommodationTypeEntity accommodationTypeEntity = getAccommodationTypeEntity();
        this.testAccommodationEntity = new AccommodationEntity();
        this.testAccommodationEntity
                .setName(TEST_HOTEL_NAME)
                .setType(accommodationTypeEntity)
                .setCity(TEST_HOTEL_CITY)
                .setAddress(TEST_HOTEL_ADDRESS)
                .setPostalCode(1000)
                .setImageUrl(TEST_HOTEL_ADDRESS)
                .setId(TEST_HOTEL_ID);
        this.accommodationRepository.save(this.testAccommodationEntity);
        return this.testAccommodationEntity;
    }

    private AccommodationTypeEntity getAccommodationTypeEntity() {
        AccommodationTypeEntity accommodationTypeEntity = new AccommodationTypeEntity();
        accommodationTypeEntity.setType(AccommodationTypeEnum.HOTEL);
        this.accommodationTypeRepository.save(accommodationTypeEntity);
        return accommodationTypeEntity;
    }

    private BookingHistoryEntity getBookingHistoryEntity() {

        BookingHistoryEntity bookingHistoryEntity = new BookingHistoryEntity();
        bookingHistoryEntity
                .setBookingTime(LocalDateTime.now())
                .setCancelled(true)
                .setEmail(TEST_USER_EMAIL)
                .setFirstName(TEST_USER_FIRST_NAME)
                .setCancelledOn(LocalDateTime.now())
                .setCheckIn(TEST_VALID_CHECKIN)
                .setCheckOut(TEST_VALID_CHECKOUT)
                .setGuest(this.testUser)
                .setLastName(TEST_USER_LAST_NAME)
                .setProperty(this.testAccommodationEntity)
                .setPayment(getPaymentEntity())
                .setId(TEST_BOOKING_HISTORY_ID);

        return this.bookingHistoryRepository.save(bookingHistoryEntity);

    }

    private PaymentEntity getPaymentEntity() {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setStatusEnum(PaymentStatusEnum.UNPAID);
        return this.paymentRepository.save(paymentEntity);
    }


}