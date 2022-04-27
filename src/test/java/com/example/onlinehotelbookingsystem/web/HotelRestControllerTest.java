//package com.example.onlinehotelbookingsystem.web;
//
//import com.example.onlinehotelbookingsystem.model.entity.AccommodationEntity;
//import com.example.onlinehotelbookingsystem.model.entity.AccommodationTypeEntity;
//import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
//import com.example.onlinehotelbookingsystem.model.entity.UserRoleEntity;
//import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
//import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
//import com.example.onlinehotelbookingsystem.repository.AccommodationRepository;
//import com.example.onlinehotelbookingsystem.repository.AccommodationTypeRepository;
//import com.example.onlinehotelbookingsystem.repository.UserRepository;
//import com.example.onlinehotelbookingsystem.repository.UserRoleRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Set;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static com.example.onlinehotelbookingsystem.constants.Constants.*;
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
//
//    private UserEntity testUser;
//    private AccommodationEntity testAccommodationEntity;
//    private AccommodationEntity testAccommodationEntity1;
//
//    @BeforeEach
//    public void setUp() {
//        this.testUser = getTestUser();
//        AccommodationTypeEntity accommodationTypeEntity = new AccommodationTypeEntity();
//        accommodationTypeEntity.setType(AccommodationTypeEnum.HOTEL);
//
//        AccommodationTypeEntity accommodationTypeEntity1 = new AccommodationTypeEntity();
//        accommodationTypeEntity1.setType(AccommodationTypeEnum.HOTEL);
//        this.accommodationTypeRepository.save(accommodationTypeEntity);
//        this.accommodationTypeRepository.save(accommodationTypeEntity1);
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
//        this.testAccommodationEntity1 = new AccommodationEntity();
//        this.testAccommodationEntity1
//                .setName(TEST_HOTEL_NAME_1)
//                .setType(accommodationTypeEntity1)
//                .setCity(TEST_HOTEL_CITY_1)
//                .setAddress(TEST_HOTEL_ADDRESS_1)
//                .setPostalCode(TEST_HOTEL_PK_1)
//                .setImageUrl(TEST_HOTEL_IMAGE_1)
//                .setId(TEST_HOTEL_ID_1);
//
//        this.accommodationRepository.save(this.testAccommodationEntity);
//        this.accommodationRepository.save(this.testAccommodationEntity1);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        this.userRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void test_get_all_accommodations() throws Exception {
//        this.mockMvc.perform(get("/accommodations"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(this.testAccommodationEntity.getId().intValue())))
//                .andExpect(jsonPath("$[1].id", is(this.testAccommodationEntity1.getId().intValue())));
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
//
//
//}