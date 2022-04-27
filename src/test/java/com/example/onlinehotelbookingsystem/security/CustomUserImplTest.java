package com.example.onlinehotelbookingsystem.security;

import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.model.entity.UserRoleEntity;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CustomUserImplTest {

    private UserEntity testUser;

    private CustomUserImpl customUserTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {

        // Arrange
        this.customUserTest = new CustomUserImpl(this.mockUserRepository);

        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        this.testUser = new UserEntity()
                .setEmail("sisi@sisi.bg")
                .setFirstName("Sisi")
                .setLastName("Sisi")
                .setPassword("topsecret")
                .setRoles(Set.of(adminRole, userRole));
    }

    @Test
    void test_User_not_found() {
        assertThrows(UsernameNotFoundException.class, () -> {
            this.customUserTest.loadUserByUsername("invalid_user_email@not-exist.com");
        });
    }

    @Test
    void test_user_found() {
//        Arrange
        Optional<UserEntity> userByEmail = this.mockUserRepository.findByEmail(testUser.getEmail());
        Mockito.when(userByEmail).thenReturn(Optional.of(this.testUser));
//        Act
        UserDetails userDetails = this.customUserTest.loadUserByUsername(this.testUser.getEmail());

//        Assert
        String actualRoles = userDetails.getAuthorities().stream().map(ga -> ga.getAuthority()).collect(Collectors.joining(", "));
        String expectedRoles = "ROLE_ADMIN, ROLE_USER";


        assertEquals(userDetails.getUsername(), this.testUser.getEmail());
        assertEquals(expectedRoles, actualRoles);
    }
}
