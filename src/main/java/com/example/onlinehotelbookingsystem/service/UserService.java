package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.service.AllUsersServiceModel;
import com.example.onlinehotelbookingsystem.model.service.ProfileServiceModel;
import com.example.onlinehotelbookingsystem.model.service.ProfileUpdateServiceModel;
import com.example.onlinehotelbookingsystem.model.service.UserRegistrationServiceModel;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface UserService {

    void initializeUsersAndRoles();

    void registerAndLogin(UserRegistrationServiceModel serviceModel);

    boolean isEmailFree(String email);

    boolean existsBy(String email);

    ProfileServiceModel findById(Long id);

    ProfileUpdateServiceModel getProfileUpdateServiceModelById(Long id);

    void update(ProfileUpdateServiceModel updateServiceModel) throws IOException;

    void makeAdmin(String email);

    void removeAdmin(String email);

    Page<AllUsersServiceModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

    void deleteUser(String email);

    boolean isOwnerOfProfile(String currentUserEmail, Long id);

}
