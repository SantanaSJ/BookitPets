package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.model.entity.UserRoleEntity;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import com.example.onlinehotelbookingsystem.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SetEnvironmentVariable(key = "",value = "")
//@SetEnvironmentVariable(key = "",value = "")
//https://fullstackcode.dev/2021/02/07/junit-handling-system-and-environment-properties-in-unit-testing/

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    private UserEntity testUser;

    @BeforeEach
    public void setUp() {
        this.testUser = getTestUser();
    }

    @AfterEach
    public void tearDown() {
        this.userRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
    void when_profile_page_is_opened_by_authorized_user_expect_status_200() throws Exception {
        this.mockMvc
                .perform(get("/users/profile/" + this.testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));

    }

    @Test
    @WithMockUser(value = TEST_USER_NON_EXISTING_EMAIL)
    void when_profile_page_is_accessed_by_unauthorized_user_expect_403() throws Exception {
        this.mockMvc
                .perform(get("/users/profile/" + this.testUser.getId()))
                .andExpect(status().isForbidden());
    }

    @Test
    void when_profile_page_is_accessed_by_unauthenticated_user_return_login_form() throws Exception {
        this.mockMvc
                .perform(get("/users/profile/" + this.testUser.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }


    @Test
    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
    void when_profile_update_form_is_accessed_by_authorized_user_expect_status_200() throws Exception {

        this.mockMvc
                .perform(get("/users/profile/update/" + this.testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("update-profile"));
    }


//    Request method 'POST' not supported]
//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void when_valid_update_params_are_entered_update_user_profile() throws Exception {
//
//
//       MockMultipartFile userMultipartFile =  new MockMultipartFile
//               ("userImageFile", "file.txt", "text/plain", "Generate bytes to simulate a picture".getBytes());
//
//        MockMultipartFile petMultipartFile = new MockMultipartFile
//                ("petImageFile", "file.txt", "text/plain", "Generate bytes to simulate a picture".getBytes());
//
//        MockMultipartHttpServletRequestBuilder builder = multipart("/users/profile/update/" + this.testUser.getId());
////        builder.with(request -> request.setMethod())
//
//        this.mockMvc
//                .perform(builder
//                        .file(userMultipartFile)
//                        .file(petMultipartFile)
//                        .param("firstName", TEST_USER_UPDATE_FIRST_NAME)
//                        .param("lastName", TEST_USER_UPDATE_LAST_NAME)
//                        .param("email", TEST_USER_UPDATE_EMAIL)
//                        .param("phoneNumber", TEST_USER_UPDATE_PHONE)
//                        .param("petName", TEST_USER_UPDATE_PET_NAME)
//                        .param("petKilograms", String.valueOf(TEST_USER_UPDATE_PET_KG))
//                        .param("title", TEST_USER_UPDATE_ADD_IMAGE_TITLE)
//                        .param("title", TEST_USER_UPDATE_ADD_PET_IMAGE_TITLE)
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/users/profile/" + this.testUser.getId()));
//
//        UserEntity user = this.userRepository.findById(this.testUser.getId()).get();
//        assertEquals(TEST_USER_UPDATE_FIRST_NAME, user.getFirstName());
//        assertEquals(TEST_USER_UPDATE_LAST_NAME, user.getLastName());
//        assertEquals(TEST_USER_UPDATE_EMAIL, user.getEmail());
//        assertEquals(TEST_USER_UPDATE_PHONE, user.getPhoneNumber());
//        assertEquals(TEST_USER_UPDATE_PET_NAME, user.getPetName());
//
//
//    }

    private UserEntity getTestUser() {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRoleEnum.USER);
        this.userRoleRepository.save(userRoleEntity);

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


}