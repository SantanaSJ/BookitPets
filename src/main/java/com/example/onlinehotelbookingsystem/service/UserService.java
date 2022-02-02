package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.model.service.ProfileServiceModel;
import com.example.onlinehotelbookingsystem.model.service.ProfileUpdateServiceModel;
import com.example.onlinehotelbookingsystem.model.service.UserRegistrationServiceModel;
import com.example.onlinehotelbookingsystem.model.view.ProfileViewModel;

import java.io.IOException;

public interface UserService {

    void initializeUsersAndRoles();

    void registerAndLogin(UserRegistrationServiceModel serviceModel);

    boolean isEmailFree(String email);

    ProfileServiceModel findUserProfileByEmail(String currentUserEmail);

    ProfileServiceModel findById(Long id);

    ProfileUpdateServiceModel getProfileUpdateServiceModelById(Long id);

    void update(ProfileUpdateServiceModel updateServiceModel) throws IOException;


//    String findUserByEmail(String currentUserEmail);
}
