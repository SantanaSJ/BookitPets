package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.model.service.*;
import com.example.onlinehotelbookingsystem.model.view.ProfileViewModel;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

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
