package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@DirtiesContext
@SpringBootTest
// Content is not allowed in prolog.
//@ContextConfiguration(initializers = TestApplicationContextInitializer.class, locations = "classpath:application.properties")
//@TestPropertySource(locations = "classpath:application.properties")
//@TestPropertySource(properties = "stripe.keys.secret=${STRIPE_KEY_SECRET}")
//@ContextConfiguration(locations = "classpath:application.properties")
//@ImportResource("classpath:application.properties")
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() {
        this.userRepository.deleteAll();
    }


    @Test
    void when_register_form_is_opened_expect_status_200() throws Exception {
        this.mockMvc
                .perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void when_login_form_is_opened_expect_status_200() throws Exception {
        this.mockMvc
                .perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }


    @Test
    void when_user_is_registered_create_new_user() throws Exception {
        this.userRepository.deleteAll();
        this.mockMvc
                .perform(post("/users/register")
                        .param("firstName", TEST_USER_FIRST_NAME)
                        .param("lastName", TEST_USER_LAST_NAME)
                        .param("phoneNumber", TEST_USER_PHONE)
                        .param("email", TEST_USER_EMAIL)
                        .param("petName", TEST_USER_PET_NAME)
                        .param("petKilograms", String.valueOf(TEST_USER_PET_KG))
                        .param("password", TEST_USER_PASSWORD)
                        .param("confirmPassword", TEST_USER_CONFIRM_PASSWORD)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        assertEquals(1, this.userRepository.count());
        Optional<UserEntity> newlyCreatedUserOpt = this.userRepository.findByEmail(TEST_USER_EMAIL);

        assertTrue(newlyCreatedUserOpt.isPresent());
        UserEntity user = newlyCreatedUserOpt.get();
        assertEquals(TEST_USER_FIRST_NAME, user.getFirstName());
        assertEquals(TEST_USER_LAST_NAME, user.getLastName());
        assertEquals(TEST_USER_PHONE, user.getPhoneNumber());
        assertEquals(TEST_USER_EMAIL, user.getEmail());
        assertEquals(TEST_USER_PET_NAME, user.getPetName());
        assertEquals(TEST_USER_PET_KG, user.getPetKilograms());
        assertTrue(this.passwordEncoder.matches(TEST_USER_CONFIRM_PASSWORD, user.getPassword()));
    }

    @Test
    void when_invalid_details_entered_redirect_with_errors() throws Exception {
        BindingResult bindingResult = (BindingResult) this.mockMvc
                .perform(post("/users/register")
                        .param("firstName", TEST_USER_INVALID_FIRST_NAME)
                        .param("lastName", TEST_USER_INVALID_LAST_NAME)
                        .param("phoneNumber", TEST_USER_INVALID_PHONE)
                        .param("email", TEST_USER_INVALID_EMAIL)
                        .param("petName", TEST_USER_INVALID_PET_NAME)
                        .param("petKilograms", String.valueOf(TEST_USER_INVALID_PET_KG))
                        .param("password", TEST_USER_INVALID_PASSWORD)
                        .param("confirmPassword", TEST_USER_INVALID_CONFIRM_PASSWORD)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"))
//                .andExpect(model().attributeHasFieldErrors("firstName"))
//                Assert the existence of the given flash attributes.
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.userModel"))
//                Return the "output" flash attributes saved during request processing.
                .andReturn().getFlashMap().get("org.springframework.validation.BindingResult.userModel");

//        assertEquals(0, this.userRepository.count());
        assertTrue(bindingResult.hasErrors());
        assertTrue(bindingResult.hasFieldErrors("firstName"));
        assertTrue(bindingResult.hasFieldErrors("lastName"));
        assertTrue(bindingResult.hasFieldErrors("phoneNumber"));
        assertTrue(bindingResult.hasFieldErrors("email"));
        assertTrue(bindingResult.hasFieldErrors("petName"));
        assertTrue(bindingResult.hasFieldErrors("petKilograms"));
        assertTrue(bindingResult.hasFieldErrors("password"));
        assertTrue(bindingResult.hasFieldErrors("confirmPassword"));

    }

}