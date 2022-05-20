//package com.example.onlinehotelbookingsystem.web;
//
//import com.example.onlinehotelbookingsystem.model.entity.*;
//import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
//import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
//import com.example.onlinehotelbookingsystem.repository.*;
//import com.example.onlinehotelbookingsystem.service.AccommodationService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import static com.example.onlinehotelbookingsystem.constants.Constants.*;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class HotelRestControllerTest {
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
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//    @Autowired
//    private AccommodationService accommodationService;
//
//
//    private UserEntity testUser;
//    private AccommodationEntity testAccommodationEntity;
//    private AccommodationEntity testAccommodationEntity1;
//    private List<AccommodationEntity> accommodationEntityList = new ArrayList<>();
//
//    @BeforeEach
//    public void setUp() {
//        this.testUser = getTestUser();
//
////        AccommodationTypeEntity accommodationTypeEntity1 = new AccommodationTypeEntity();
////        accommodationTypeEntity1.setType(AccommodationTypeEnum.HOTEL);
//
////        this.accommodationTypeRepository.save(accommodationTypeEntity1);
//
//
////        this.testAccommodationEntity1 = new AccommodationEntity();
////        this.testAccommodationEntity1
////                .setName(TEST_HOTEL_NAME_1)
////                .setType(accommodationTypeEntity1)
////                .setCity(TEST_HOTEL_CITY_1)
////                .setAddress(TEST_HOTEL_ADDRESS_1)
////                .setPostalCode(TEST_HOTEL_PK_1)
////                .setImageUrl(TEST_HOTEL_IMAGE_1)
////                .setId(TEST_HOTEL_ID_1);
//
////        this.accommodationRepository.save(this.testAccommodationEntity1);
////        List<AccommodationEntity> accommodationEntityList = new ArrayList<>();
//
////        int size = all.size();
//        System.out.println();
//
//
//    }
//
//    @AfterEach
//    public void tearDown() {
////        this.userRepository.deleteAll();
////        this.accommodationRepository.deleteAll();
//    }
//
////java.lang.AssertionError: JSON path "$"
////Expected: a collection with size <2>
////     but: collection size was <0>
//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void when_accommodations_is_accessed_return_accommodation_entities_when_they_exist() throws Exception {
//        this.roomRepository.deleteAll();
//        this.accommodationRepository.deleteAll();
//        List<AccommodationEntity> all1 = this.accommodationRepository.findAll();
//        AccommodationTypeEntity accommodationTypeEntity = new AccommodationTypeEntity();
//        accommodationTypeEntity.setType(AccommodationTypeEnum.HOTEL);
//        this.accommodationTypeRepository.save(accommodationTypeEntity);
//
//        this.testAccommodationEntity = new AccommodationEntity();
//        this.testAccommodationEntity
//                .setName(TEST_HOTEL_NAME)
//                .setType(accommodationTypeEntity)
//                .setCity(TEST_HOTEL_CITY)
//                .setAddress(TEST_HOTEL_ADDRESS)
//                .setPostalCode(TEST_HOTEL_PK)
//                .setImageUrl(TEST_HOTEL_IMAGE)
//                .setId(TEST_HOTEL_ID);
//
//        this.accommodationRepository.save(this.testAccommodationEntity);
////        this.accommodationEntityList = List.of(this.testAccommodationEntity);
////        List<AccommodationEntity> all = this.accommodationRepository.findAll();
//
//
////        Mockito.when(this.accommodationRepository.findAll()).thenReturn(this.accommodationEntityList);
//
//        this.mockMvc.perform(get("/accommodations"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id", is(this.testAccommodationEntity.getId().intValue())));
////                .andExpect(jsonPath("$[1].id", is(this.testAccommodationEntity1.getId().intValue())));
//    }
//
//    private UserEntity getTestUser() {
////        getUserRoleEntity method
//        UserRoleEntity userRoleEntity = new UserRoleEntity();
//        userRoleEntity.setRole(UserRoleEnum.USER);
//        this.userRoleRepository.save(userRoleEntity);
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
//}