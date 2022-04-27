package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.*;
import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import com.example.onlinehotelbookingsystem.model.service.*;
import com.example.onlinehotelbookingsystem.repository.ProfileImageRepository;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import com.example.onlinehotelbookingsystem.security.CustomUserImpl;
import com.example.onlinehotelbookingsystem.repository.UserRoleRepository;
import com.example.onlinehotelbookingsystem.service.CloudinaryService;
import com.example.onlinehotelbookingsystem.service.PetImageService;
import com.example.onlinehotelbookingsystem.service.UserService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final CloudinaryService cloudinaryService;
    private final ProfileImageRepository profileImageRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                           UserRoleRepository userRoleRepository, CustomUserImpl appUserService,
                           ModelMapper mapper, PetImageService petImageService,
                           CloudinaryService cloudinaryService,
                           ProfileImageRepository profileImageRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userDetails = appUserService;
        this.mapper = mapper;
        this.petImageService = petImageService;
        this.cloudinaryService = cloudinaryService;
        this.profileImageRepository = profileImageRepository;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (this.userRepository.count() == 0) {

            UserRoleEntity adminRole = this.userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER);

//            TODO: to change dog to pet and to add select option for pet - dog, cat, etc (enum)
            UserEntity admin = new UserEntity();
            admin
                    .setEmail("admin@admin.bg")
                    .setPassword(this.passwordEncoder.encode("a123456789A#"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
//                    .setPetImage( "https://i.insider.com/5484e527ecad04de4324638b?width=1000&format=jpeg&auto=webp")
                    .setPetName("Max")
                    .setPetKilograms(25)
                    .setPhoneNumber("0887654311")
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
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }


    @Override
    public void registerAndLogin(UserRegistrationServiceModel serviceModel) {
        UserRoleEntity userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity user = this.mapper.map(serviceModel, UserEntity.class);
        user
                .setPassword(this.passwordEncoder.encode(serviceModel.getPassword()))
                .setRoles(Set.of(userRole))
                .setJoined(LocalDateTime.now());

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
    public boolean existsBy(String email) {
        boolean exists = this.userRepository.existsByEmail(email);
        return exists;
    }

    @Transactional
    @Override
    public ProfileServiceModel findById(Long id) {

        ProfileServiceModel serviceModel = this.userRepository
                .findById(id)
                .map(user -> this.mapper.map(user, ProfileServiceModel.class))
                .orElseThrow(() -> new ObjectNotFoundException("User with id " + id + " not found!"));

        System.out.println();
        return serviceModel;
    }

    @Override
    public ProfileUpdateServiceModel getProfileUpdateServiceModelById(Long id) {
        ProfileUpdateServiceModel profileUpdateServiceModel = this.userRepository
                .findById(id)
                .map(user -> this.mapper.map(user, ProfileUpdateServiceModel.class))
                .orElseThrow(() -> new ObjectNotFoundException("User with id " + id + " not found!"));

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

//        PET
        String pTitle = updateServiceModel.getPetImageTitle();
        MultipartFile pFile = updateServiceModel.getPetImageFile();

        PetImageEntity petImage = new PetImageEntity();
        petImage = this.petImageService.addImage(pFile, pTitle);

//        USER
//        String title = updateServiceModel.getAddUserImageBindingModel().getTitle();
        String title = updateServiceModel.getUserImageTitle();
        MultipartFile file = updateServiceModel.getUserImageFile();

        ProfileImageEntity userImageEntity = createUserImageEntity(file, title);
        userEntity.setPetImage(petImage);
        userEntity.setProfilePicture(userImageEntity);

        this.userRepository.save(userEntity);
    }

//    @Override
//    public List<AllUsersServiceModel> findAll() {
//        List<UserEntity> entities = this.userRepository.findAll();
//
//        List<AllUsersServiceModel> allUsers = entities
//                .stream()
//                .map(userEntity -> {
//                    AllUsersServiceModel serviceModel = this.mapper.map(userEntity, AllUsersServiceModel.class);
//                    serviceModel.setNumberOfBookings(userEntity.getBookings().size());
//                    for (UserRoleEntity role : userEntity.getRoles()) {
//                        serviceModel.getRoles().add(role.getRole());
//                    }
//                    serviceModel.getRoles().remove(null);
//                    return serviceModel;
//                })
//                .collect(Collectors.toList());
//
//        return allUsers;
//    }

    @Override
    public void makeAdmin(String email) {
        UserEntity user = this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User with email " + email + " not found!"));
        user.getRoles().add(this.userRoleRepository.findByRole(UserRoleEnum.ADMIN));
        this.userRepository.save(user);
    }

    @Override
    public void removeAdmin(String email) {
        UserEntity user = this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User with email " + email + " not found!"));
        user.getRoles().remove(this.userRoleRepository.findByRole(UserRoleEnum.ADMIN));
        this.userRepository.save(user);

    }

    @Override
    public Page<AllUsersServiceModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort =
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<UserEntity> page = this.userRepository.findAll(pageable);
        Page<AllUsersServiceModel> map = page.map(this::mapToServiceModel);
        return map;
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        this.userRepository.deleteByEmail(email);
    }

    @Override
    public boolean isOwnerOfProfile(String currentUserEmail, Long id) {
        UserEntity user = this.userRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User with id " + id + " not found!"));

        String email = user.getEmail();

        if (email.equals(currentUserEmail)) {
            return true;
        }
        return false;

    }

    private ProfileImageEntity createUserImageEntity(MultipartFile file, String title) throws IOException {
        CloudinaryImage uploaded = this.cloudinaryService.upload(file);
        ProfileImageEntity image = new ProfileImageEntity();
        image
                .setPublicId(uploaded.getPublicId())
                .setTitle(title)
                .setUrl(uploaded.getUrl());
        ProfileImageEntity saved = this.profileImageRepository.save(image);
        return saved;
    }

    private AllUsersServiceModel mapToServiceModel(UserEntity entity) {
//        AllUsersServiceModel serviceModel = this.mapper.map(entity, AllUsersServiceModel.class);
//        or else null/optional/or else throw???
        AllUsersServiceModel usersServiceModel = new AllUsersServiceModel();
        usersServiceModel
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setEmail(entity.getEmail())
                .setJoined(entity.getJoined())
                .setNumberOfBookings(entity.getBookings().size())
                .setId(entity.getId());
        for (UserRoleEntity role : entity.getRoles()) {
            String name = role.getRole().name();
            usersServiceModel.getRoles().add(name);
        }
        usersServiceModel.getRoles().remove(null);

        return usersServiceModel;
    }


}

