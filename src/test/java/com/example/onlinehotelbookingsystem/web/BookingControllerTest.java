package com.example.onlinehotelbookingsystem.web;

//@SpringBootTest
//@AutoConfigureMockMvc
//class BookingControllerTest {
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

//    private final CustomUser customUserDetails1 = new CustomUser
//            (TEST_ADMIN_EMAIL, TEST_ADMIN_PASSWORD, TEST_ADMIN_ID, TEST_ADMIN_FIRST_NAME,
//                    List.of(new SimpleGrantedAuthority("ADMIN")));

//    private UserEntity testUser;
//    private AccommodationEntity testAccommodationEntity;
//    private BookingEntity testBookingEntity;

//    @BeforeEach
//    public void setUp() {
//        this.testUser = getTestUser();
//        this.testAccommodationEntity = getAccommodationEntity();
//        this.testBookingEntity = getBookingEntity();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        this.bookedRoomsRepository.deleteAll();
//        this.roomRepository.deleteAll();
//        this.bookingRepository.deleteAll();
//        this.accommodationRepository.deleteAll();
//        this.userRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void when_booking_form_is_opened_by_authenticated_user_expect_status_200() throws Exception {
////        Long id = this.testAccommodationEntity.getId();
//        this.mockMvc
//                .perform(get("/booking-form/accommodation/" + this.testAccommodationEntity.getId()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("booking-form"));
//    }
//
//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void when_invalid_params_entered_in_booking_form_redirect_with_errors() throws Exception {
//        Long id = this.testAccommodationEntity.getId();
//        BindingResult bindingResult = (BindingResult) this.mockMvc
//                .perform(get("/room-availability")
//                        .param("checkIn", String.valueOf(TEST_INVALID_CHECKIN))
//                        .param("checkOut", String.valueOf(TEST_INVALID_CHECKOUT))
//                        .param("hotelId", String.valueOf(this.testAccommodationEntity.getId())))
//                .andExpect(redirectedUrl("/booking-form/accommodation/" + id))
//                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.bindingModel"))
//                .andReturn().getFlashMap().get("org.springframework.validation.BindingResult.bindingModel");
//
//        assertTrue(bindingResult.hasErrors());
//        assertTrue(bindingResult.hasFieldErrors("checkIn"));
//        assertTrue(bindingResult.hasFieldErrors("checkOut"));
//    }

    //                HOW TO SET PARAMS FOR   private List<RoomBindingModel> rooms = new ArrayList<>();
//    @Test
//    void when_valid_params_entered_check_availability_and_return_appropriate_message() throws Exception {
//        Mockito.when(this.bookingRepository.isRoomAvailableBetween(LocalDate.of(2022, 7, 1),
//                LocalDate.of(2022, 7, 2), 1L, 2L)).thenReturn(0);
//
//        this.mockMvc
//                .perform(get("/room-availability")
//                        .param("checkIn", String.valueOf(TEST_VALID_CHECKIN))
//                        .param("checkOut", String.valueOf(TEST_VALID_CHECKOUT))
//                        .param("hotelId", String.valueOf(this.testAccommodationEntity.getId())))
//    }

//    @Test
//// because I got Unable to create SecurityContext using @org.springframework.security.test.context.support.WithUserDetails
//// https://stackoverflow.com/questions/42466954/spring-security-junit-withuserdetails-for-user-created-in-before
//    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    @WithMockUser(username = TEST_USER_EMAIL, password = TEST_USER_PASSWORD, roles = "USER")
//    void when_create_booking_is_opened_from_authorized_user_expect_status_200() throws Exception {
//        this.mockMvc
//                .perform(get("/create-booking"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("create-booking"));
//    }

//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void when_invalid_params_entered_in_create_booking_form_redirect_with_errors() throws Exception {
//        BindingResult bindingResult = (BindingResult) this.mockMvc
//                .perform(post("/create-booking")
//                        .param("firstName", TEST_USER_INVALID_FIRST_NAME)
//                        .param("lastName", TEST_USER_INVALID_LAST_NAME)
//                        .param("petName", TEST_USER_INVALID_PET_NAME)
//                        .param("email", TEST_USER_INVALID_EMAIL)
//                        .param("petKilograms", String.valueOf(TEST_USER_INVALID_PET_KG))
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(redirectedUrl("create-booking"))
//                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.bookingBindingModel"))
//                .andReturn().getFlashMap().get("org.springframework.validation.BindingResult.bookingBindingModel");
//
//        assertTrue(bindingResult.hasErrors());
//        assertTrue(bindingResult.hasFieldErrors("firstName"));
//        assertTrue(bindingResult.hasFieldErrors("lastName"));
//        assertTrue(bindingResult.hasFieldErrors("petName"));
//        assertTrue(bindingResult.hasFieldErrors("email"));
//        assertTrue(bindingResult.hasFieldErrors("petKilograms"));
//    }

//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void when_booking_details_are_opened_by_authorized_user_expect_status_200() throws Exception {
//        this.mockMvc
//                .perform(get("/bookings/details/" + this.testBookingEntity.getId()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("details"));
//    }

//    @Test
//    @WithMockUser(value = TEST_USER_NON_EXISTING_EMAIL)
//    void when_booking_details_are_opened_by_unauthorized_user_expect_status_403() throws Exception {
//        this.mockMvc
//                .perform(get("/bookings/details/" + this.testBookingEntity.getId()))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    @WithMockUser(value = TEST_ADMIN_EMAIL, roles = "ADMIN")
//    void when_booking_details_are_opened_by_not_owner_admin_expect_status_200() throws Exception {
//        this.mockMvc
//                .perform(get("/bookings/details/" + this.testBookingEntity.getId()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("details"));
//    }

//    @Test
//    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    void when_update_booking_form_is_opened_by_authorized_user_show_update() throws Exception {
//        this.mockMvc
//                .perform(get("/bookings/update/" + this.testBookingEntity.getId()))
//                .andExpect(status().isOk());
//    }

//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void when_invalid_params_entered_in_update_booking_form_redirect_with_errors() throws Exception {
//       BindingResult bindingResult = (BindingResult) this.mockMvc
//                .perform(patch("/bookings/update/" + this.testBookingEntity.getId())
//                        .param("firstName", TEST_USER_INVALID_FIRST_NAME)
//                        .param("lastName", TEST_USER_INVALID_LAST_NAME)
//                        .param("petName", TEST_USER_INVALID_PET_NAME)
//                        .param("petKilograms", String.valueOf(TEST_USER_INVALID_PET_KG))
//                        .param("email", TEST_USER_INVALID_EMAIL)
//                        .param("phoneNumber", TEST_USER_INVALID_PHONE)
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(redirectedUrl("/bookings/update/" + this.testBookingEntity.getId()))
//                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.bookingUpdateBindingModel"))
//                .andReturn().getFlashMap().get("org.springframework.validation.BindingResult.bookingUpdateBindingModel");
//
//        assertTrue(bindingResult.hasErrors());
//        assertTrue(bindingResult.hasFieldErrors("firstName"));
//        assertTrue(bindingResult.hasFieldErrors("lastName"));
//        assertTrue(bindingResult.hasFieldErrors("petName"));
//        assertTrue(bindingResult.hasFieldErrors("email"));
//        assertTrue(bindingResult.hasFieldErrors("petKilograms"));
//    }

//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void when_valid_params_entered_in_update_booking_form_update_user_info() throws Exception {
//        this.mockMvc
//                .perform(patch("/bookings/update/" + this.testBookingEntity.getId())
//                        .param("firstName", TEST_USER_FIRST_NAME)
//                        .param("lastName", TEST_USER_LAST_NAME)
//                        .param("petName", TEST_USER_PET_NAME)
//                        .param("petKilograms", String.valueOf(TEST_USER_PET_KG))
//                        .param("email", TEST_USER_EMAIL)
//                        .param("phoneNumber", TEST_USER_PHONE)
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/bookings/details/" + this.testBookingEntity.getId()));
//
//        assertEquals(1, this.bookingRepository.count());
//
//        Optional<BookingEntity> newReservation = this.bookingRepository.findById(this.testBookingEntity.getId());
//        assertTrue(newReservation.isPresent());
//        BookingEntity bookingEntity = newReservation.get();
//        assertEquals(TEST_USER_FIRST_NAME, bookingEntity.getFirstName());
//        assertEquals(TEST_USER_LAST_NAME, bookingEntity.getLastName());
//        assertEquals(TEST_USER_PHONE, bookingEntity.getPhoneNumber());
//        assertEquals(TEST_USER_EMAIL, bookingEntity.getEmail());
//        assertEquals(TEST_USER_PET_NAME, bookingEntity.getPetName());
//        assertEquals(TEST_USER_PET_KG, bookingEntity.getPetKilograms());
//    }

//    //   Not populating service model - booked rooms
//HOW TO SET PARAMS FOR   List<RoomServiceModel> bookedRooms = new ArrayList<>();
//    @Test
//    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    void when_valid_params_entered_in_create_booking_form_create_booking() throws Exception {
//
//        RoomServiceModel roomServiceModel = new RoomServiceModel();
//        roomServiceModel
//                .setRoomId(1L)
//                .setPrice(BigDecimal.valueOf(100))
//                .setNumberOfRooms(1)
//                .setType("single");
//        List<RoomServiceModel> bookedRooms = new ArrayList<>();
//        bookedRooms.add(roomServiceModel);
//
//        this.mockMvc
//                .perform(post("/create-booking")
//                        .param("firstName", TEST_USER_FIRST_NAME)
//                        .param("lastName", TEST_USER_LAST_NAME)
//                        .param("email", TEST_USER_EMAIL)
//                        .param("phoneNumber", TEST_USER_PHONE)
//                        .param("petName", TEST_USER_PET_NAME)
//                        .param("petKilograms", String.valueOf(TEST_USER_PET_KILOGRAMS))
//                        .param("hotelId", String.valueOf(TEST_HOTEL_ID))
//                        .param("checkIn", String.valueOf(TEST_BOOKING_CHECKIN))
//                        .param("checkOut", String.valueOf(TEST_BOOKING_CHECKOUT))
//                        .param("bookedRooms[0].id", "1")
////                        .param("bookedRooms", String.valueOf(bookedRooms.get(0).getNumberOfRooms()))
////                        .param("bookedRooms[0].price", "100")
////                        .param("bookedRooms[0].type", "single")
//                        .param("bookedRooms[0].numberOfRooms", "1")
////                        .param("bookedRooms", String.valueOf(bookedRooms.get(0).getType()))
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().is3xxRedirection());
//
//        assertEquals(1, this.bookingRepository.count());
//    }

//    private BookingEntity getBookingEntity() {
//
//        RoomEntity roomEntity = new RoomEntity();
////        AccommodationEntity testAccommodationEntity = this.testAccommodationEntity;
//        roomEntity
//                .setRoomType(TEST_ROOM_TYPE)
//                .setAccommodationEntity(this.testAccommodationEntity)
//                .setCurrentPrice(TEST_ROOM_CURRENT_PRICE)
//                .setDescription(TEST_ROOM_DESCRIPTION)
//                .setId(TEST_ROOM_ID);
//        this.roomRepository.save(roomEntity);
//
//        List<BookedRoomsEntity> bookedRoomsEntities = new ArrayList<>();
//        BookedRoomsEntity bookedRooms = new BookedRoomsEntity();
//        bookedRooms.setNumberOfRooms(1)
//                .setBooking(this.testBookingEntity)
//                .setPrice(TEST_BOOKED_ROOMS_PRICE)
//                .setRoom(roomEntity)
//                .setNumberOfRooms(TEST_BOOKED_ROOMS_NUMBER_OF_ROOMS)
//                .setId(TEST_BOOKED_ROOMS_ID);
//        bookedRoomsEntities.add(bookedRooms);
//        this.bookedRoomsRepository.save(bookedRooms);
//
//        PaymentEntity paymentEntity = new PaymentEntity();
//        paymentEntity.setStatusEnum(PaymentStatusEnum.UNPAID);
//        this.paymentRepository.save(paymentEntity);
//
//
//        this.testBookingEntity = new BookingEntity();
//        this.testBookingEntity
//                .setGuest(this.testUser)
//                .setProperty(this.testAccommodationEntity)
//                .setFirstName(TEST_USER_FIRST_NAME)
//                .setLastName(TEST_USER_LAST_NAME)
//                .setTotalPrice(BigDecimal.valueOf(200))
//                .setPhoneNumber(TEST_USER_PHONE)
//                .setEmail(TEST_USER_EMAIL)
//                .setPetKilograms(TEST_USER_PET_KG)
//                .setPetName(TEST_USER_PET_NAME)
//                .setCheckIn(TEST_VALID_CHECKIN)
//                .setCheckOut(TEST_VALID_CHECKOUT)
//                .setTotalNights(TEST_BOOKING_NIGHTS)
//                .setBookedRooms(bookedRoomsEntities)
//                .setBookingTime(LocalDateTime.now())
//                .setPayment(paymentEntity)
//                .setId(TEST_BOOKING_ID);
//        this.bookingRepository.save(this.testBookingEntity);
//
//        return this.testBookingEntity;
//    }
//
//    private AccommodationEntity getAccommodationEntity() {
//
//        AccommodationTypeEntity accommodationTypeEntity = getAccommodationTypeEntity();
//        this.testAccommodationEntity = new AccommodationEntity();
//        this.testAccommodationEntity
//                .setName(TEST_HOTEL_NAME)
//                .setType(accommodationTypeEntity)
//                .setCity(TEST_HOTEL_CITY)
//                .setAddress(TEST_HOTEL_ADDRESS)
//                .setPostalCode(TEST_HOTEL_PK)
//                .setImageUrl(TEST_HOTEL_IMAGE)
//                .setId(TEST_HOTEL_ID);
//        this.accommodationRepository.save(this.testAccommodationEntity);
//        return this.testAccommodationEntity;
//    }
//
//    private UserEntity getTestUser() {
//        UserRoleEntity userRoleEntity = getUserRoleEntity();
//
//        this.testUser = new UserEntity();
//        this.testUser
//                .setPassword(TEST_USER_PASSWORD)
//                .setFirstName(TEST_USER_FIRST_NAME)
//                .setLastName(TEST_USER_LAST_NAME)
//                .setEmail(TEST_USER_EMAIL)
//                .setRoles(Set.of(userRoleEntity))
//                .setPhoneNumber(TEST_USER_PHONE)
//                .setId(TEST_USER_ID);
//
//        return this.userRepository.save(this.testUser);
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
//}