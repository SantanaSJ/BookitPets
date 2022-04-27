package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.entity.PhotoEntity;
import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.model.entity.UserRoleEntity;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.repository.PhotoRepository;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import com.example.onlinehotelbookingsystem.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhotoRepository photoRepository;

    private UserEntity testUser;
    private PhotoEntity testPhotoEntity;

    @BeforeEach
    public void setUp() {
        this.testUser = getTestUser();
        this.testPhotoEntity = getTestPhotoEntity();
    }

    @AfterEach
    public void tearDown() {

        this.userRepository.deleteAll();
        System.out.println();
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void when_view_album_is_opened_by_authorized_user_and_photos_exist_expect_status_200() throws Exception {

        this.mockMvc
                .perform(get("/view-album/" + this.testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("album-photos"))
                .andExpect(model().attributeExists("photoViewModels"));
    }

    @Test
    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void when_view_album_is_opened_by_authorized_user_and_no_photos_exist_return_view_noContent() throws Exception {
        this.photoRepository.deleteAll();

        this.mockMvc
                .perform(get("/view-album/" + this.testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("album-photos"))
                .andExpect(model().attributeExists("noContent"))
                .andExpect(view().name("album-photos"));
    }

//    @Test
//    @WithMockUser(value = TEST_USER_NON_EXISTING_EMAIL)
//    void when_view_album_is_accessed_by_unauthorized_user_expect_status_403() throws Exception {
//        this.mockMvc
//                .perform(get("/view-album/" + this.testUser.getId()))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(value = TEST_USER_EMAIL, roles = "USER")
//    void when_upload_page_is_accessed_expect_status_200() throws Exception {
//        this.mockMvc
//                .perform(get("/upload"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("album-upload"));
//    }

//https://stackoverflow.com/questions/62862635/mockmvc-calling-a-put-endpoint-that-accepts-a-multipart-file
//    java.lang.IllegalArgumentException: Illegal character in path at index 33: https://api.cloudinary.com/v1_1/${CLOUDINARY_NAME}/image/upload
//    @Test
//    @WithUserDetails(value = TEST_USER_EMAIL, setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    void upload_an_image() throws Exception {
//        MockMultipartFile mockFile = new MockMultipartFile("file", "1870286df4874739fd.jpg", "text/plain", "Some dataset...".getBytes());
////        MockMultipartHttpServletRequestBuilder builder =
////                multipart("/album/upload/user/" + this.testUser.getId());
////        builder.with(request -> {
////            request.setMethod(HttpMethod.PUT.name());
////            return request;
////        });
//
//        this.mockMvc
//                .perform(multipart("/album/upload/user/" + this.testUser.getId())
//                        .file(mockFile)
////                        .with(request -> {request.setMethod("PUT"); return request;})
//                        .param("userId", String.valueOf(1L))
//                        .param("title", "some title")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/view-album/" + this.testUser.getId()));
//    }



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

    private PhotoEntity getTestPhotoEntity() {
        this.testPhotoEntity = new PhotoEntity();
        this.testPhotoEntity
                .setUrl("some/image/url.jpeg")
                .setUser(this.testUser)
                .setId(1L);
        PhotoEntity saved = this.photoRepository.save(this.testPhotoEntity);
        return saved;
    }

}