package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.binding.AddImageBindingModel;
import com.example.onlinehotelbookingsystem.model.entity.PetImageEntity;
import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.model.entity.UserRoleEntity;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.model.service.ProfileServiceModel;
import com.example.onlinehotelbookingsystem.model.service.ProfileUpdateServiceModel;
import com.example.onlinehotelbookingsystem.model.service.UserRegistrationServiceModel;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import com.example.onlinehotelbookingsystem.security.CustomUserImpl;
import com.example.onlinehotelbookingsystem.repository.UserRoleRepository;
import com.example.onlinehotelbookingsystem.service.PetImageService;
import com.example.onlinehotelbookingsystem.service.UserService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final CustomUserImpl userDetails;
    private final ModelMapper mapper;
    private final PetImageService petImageService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, CustomUserImpl appUserService, ModelMapper mapper, PetImageService petImageService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userDetails = appUserService;
        this.mapper = mapper;
        this.petImageService = petImageService;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (this.userRepository.count() == 0) {

            UserRoleEntity adminRole = this.userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = this.userRoleRepository.findByRole(UserRoleEnum.GUEST);

//            TODO: to change dog to pet and to add select option for pet - dog, cat, etc (enum)
            UserEntity admin = new UserEntity();
            admin
                    .setEmail("admin@admin.bg")
                    .setPassword(this.passwordEncoder.encode("test"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
//                    .setPetImage( "https://i.insider.com/5484e527ecad04de4324638b?width=1000&format=jpeg&auto=webp")
                    .setPetName("Max")
                    .setPetKilograms(25)
                    .setPhoneNumber("11231")
                    .setJoined(LocalDateTime.now());
//                    .setActive(true);

            admin.setRoles(Set.of(adminRole, userRole));
            this.userRepository.save(admin);

            UserEntity pesho = new UserEntity();
            pesho
                    .setEmail("pesho@abv.bg")
                    .setPassword(this.passwordEncoder.encode("test"))
                    .setFirstName("Pesho")
                    .setLastName("Petrov")
                    .setPetName("Tobi")
                    .setPetKilograms(20)
//                    .setPetImageUrl("https://i.insider.com/5484d9d1eab8ea3017b17e29?width=1000&format=jpeg&auto=webp")
                    .setPhoneNumber("11231")
                    .setJoined(LocalDateTime.now());
//                    .setActive(true);

            pesho.setRoles(Set.of(userRole));
            this.userRepository.save(pesho);
        }
    }


    private void initializeRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.GUEST);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }


    @Override
    public void registerAndLogin(UserRegistrationServiceModel serviceModel) {
        UserRoleEntity userRole = this.userRoleRepository.findByRole(UserRoleEnum.GUEST);

        UserEntity user = this.mapper.map(serviceModel, UserEntity.class);
        user
                .setPassword(this.passwordEncoder.encode(serviceModel.getPassword()))
                .setRoles(Set.of(userRole));

        user = this.userRepository.save(user);

        // this is the Spring representation of a user
        UserDetails principal = this.userDetails.loadUserByUsername(user.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }


    @Override
    public boolean isEmailFree(String email) {
        return this.userRepository
                .findByEmail(email)
                .isEmpty();
    }
//    TODO: to add  where needed .orElseThrow(() -> new ObjectNotFoundException("User with this email " + email + " not found", email));
    @Override
    public ProfileServiceModel findUserProfileByEmail(String currentUserEmail) {
        ProfileServiceModel serviceModel = this.userRepository
                .findByEmail(currentUserEmail)
                .map(user -> this.mapper.map(user, ProfileServiceModel.class))
                .orElse(null);

        return serviceModel;


    }

    @Transactional
    @Override
    public ProfileServiceModel findById(Long id) {

        ProfileServiceModel serviceModel = this.userRepository
                .findById(id)
                .map(user -> this.mapper.map(user, ProfileServiceModel.class))
                .orElse(null);

        System.out.println();
        return serviceModel;
    }

    @Override
    public ProfileUpdateServiceModel getProfileUpdateServiceModelById(Long id) {
        ProfileUpdateServiceModel profileUpdateServiceModel = this.userRepository
                .findById(id)
                .map(user -> this.mapper.map(user, ProfileUpdateServiceModel.class))
                .orElse(null);

        return profileUpdateServiceModel;

    }

    @Override
    public void update(ProfileUpdateServiceModel updateServiceModel) throws IOException {
        UserEntity userEntity = this.userRepository.findById(updateServiceModel.getUserId()).orElseThrow(() ->
                new ObjectNotFoundException("User with id " + updateServiceModel.getUserId() + " not found!"));
        userEntity
                .setFirstName(updateServiceModel.getFirstName())
                .setLastName(updateServiceModel.getLastName())
                .setEmail(updateServiceModel.getEmail())
                .setPetName(updateServiceModel.getPetName())
                .setPetKilograms(updateServiceModel.getPetKilograms())
                    .setPhoneNumber(updateServiceModel.getPhoneNumber());
        PetImageEntity petImage = new PetImageEntity();
        String title = updateServiceModel.getAddImageBindingModel().getTitle();
        MultipartFile file = updateServiceModel.getAddImageBindingModel().getFile();

        petImage = this.petImageService.addImage(file,title);

        userEntity.setPetImage(petImage);

        this.userRepository.save(userEntity);
    }

//    @Override
//    public String findUserByEmail(String currentUserEmail) {
//        UserEntity userEntity = this.userRepository
//                .findByEmail(currentUserEmail)
//                .orElse(null);
//
//    }

}

