package com.example.onlinehotelbookingsystem.init;

import com.example.onlinehotelbookingsystem.service.AccommodationService;
import com.example.onlinehotelbookingsystem.service.AccommodationTypeService;
import com.example.onlinehotelbookingsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final AccommodationTypeService propertyTypeService;
    private final AccommodationService accommodationService;


    public DBInit(UserService userService, AccommodationTypeService propertyTypeService,
                  AccommodationService accommodationService) {
        this.userService = userService;
        this.propertyTypeService = propertyTypeService;
        this.accommodationService = accommodationService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.userService.initializeUsersAndRoles();
        this.propertyTypeService.initAccommodationTypes();
        this.accommodationService.initHotels();

    }
}
