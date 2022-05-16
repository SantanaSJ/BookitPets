package com.example.onlinehotelbookingsystem.constants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class Constants {

    public static final Long TEST_USER_ID = 1L;
    public static final String TEST_USER_FIRST_NAME = "John";
    public static final String TEST_USER_LAST_NAME = "Travolta";
    public static final String TEST_USER_PHONE = "0884443331";
    public static final String TEST_USER_EMAIL = "john_travolta@yahoo.com";
    public static final String TEST_USER_PET_NAME = "Rex";
    public static final int TEST_USER_PET_KG = 35;
    public static final String TEST_USER_PASSWORD = "a123456789A#";
    public static final String TEST_USER_CONFIRM_PASSWORD = "a123456789A#";

    public static final String TEST_USER_INVALID_FIRST_NAME = "";
    public static final String TEST_USER_INVALID_LAST_NAME = "T";
    public static final String TEST_USER_INVALID_PHONE = "88844433333";
    public static final String TEST_USER_INVALID_EMAIL = "john_travolta.com";
    public static final String TEST_USER_INVALID_PET_NAME = "";
    public static final int TEST_USER_INVALID_PET_KG = 0;
    public static final String TEST_USER_INVALID_PASSWORD = "test123";
    public static final String TEST_USER_INVALID_CONFIRM_PASSWORD = "test123";
    
    public static final String TEST_USER_NON_EXISTING_EMAIL = "brad_pitt@yahoo.com";

    public static final LocalDate TEST_INVALID_CHECKIN = LocalDate.of(1999, 9, 9);
    public static final LocalDate TEST_INVALID_CHECKOUT = LocalDate.of(1999, 9, 10);

    public static final LocalDate TEST_VALID_CHECKIN = LocalDate.of(2022, 7, 1);
    public static final LocalDate TEST_VALID_CHECKOUT = LocalDate.of(2022, 7, 2);



    public static final String TEST_ADMIN_EMAIL = "quentin_tarantino@yahoo.com";
    public static final String TEST_ADMIN_PASSWORD = "pulp_fiction";
    public static final Long TEST_ADMIN_ID = 2L;
    public static final String TEST_ADMIN_FIRST_NAME = "Quentin";
    public static final String TEST_ADMIN_LAST_NAME = "Tarantino";
    public static final String TEST_ADMIN_PHONE= "0884443331";


    public static final long TEST_BOOKING_NIGHTS = 1;
    public static final Long TEST_BOOKING_ID = 1L;

    public static final Long TEST_HOTEL_ID = 1L;
    public static final String TEST_HOTEL_IMAGE = "image_url1";
    public static final Integer TEST_HOTEL_PK = 1000;
    public static final String TEST_HOTEL_ADDRESS = "Mulholland dive";
    public static final String TEST_HOTEL_CITY = "Sofia";
    public static final int TEST_HOTEL_CAT = 4;
    public static final String TEST_HOTEL_NAME = "Hotel Transylvania";

    public static final String TEST_HOTEL_NAME_1 = "Hilton";
    public static final String TEST_HOTEL_CITY_1 = "Plovdiv";
    public static final String TEST_HOTEL_ADDRESS_1 = "2 Mulholland drive";
    public static final Integer TEST_HOTEL_PK_1 = 1001;
    public static final String TEST_HOTEL_IMAGE_1 = "image_url2";
    public static final Long TEST_HOTEL_ID_1 = 2L;

    public static final BigDecimal TEST_BOOKED_ROOM_PRICE = BigDecimal.valueOf(100);
    public static final Integer TEST_BOOKED_ROOM_NUMBER_OF_ROOMS = 1;
    public static final Long TEST_BOOKED_ROOM_ID = 1L;

    public static final String TEST_ROOM_TYPE = "single";
    public static final BigDecimal TEST_ROOM_CURRENT_PRICE = BigDecimal.valueOf(100);
    public static final String TEST_ROOM_DESCRIPTION = "some description";
    public static final Long TEST_ROOM_ID = 1L;

    public static final Long TEST_BOOKING_HISTORY_ID = 1L;
    public static final Long TEST_BOOKING_ID1 = 2L;
    public static final LocalDateTime TEST_BOOKING_TIME = LocalDateTime.of(2022, 4, 10, 11, 10);
    public static final BigDecimal TEST_BOOKING_PRICE = BigDecimal.valueOf(100);
    public static final String TEST_HOTEL_DESCRIPTION = "some description";
    public static final Long TEST_BOOKING_HISTORY_ID_1 = 2L;
    public static final String TEST_ROOM_TYPE_1 = "double";
    public static final Long TEST_ROOM_ID_1 = 2L;
    public static final String TEST_SEARCH_TERM = "Tra";
    public static final String TEST_URL = "some/image/url.jpeg";
    public static final String TEST_USER_UPDATE_FIRST_NAME = "Michael";
    public static final String TEST_USER_UPDATE_LAST_NAME = "Madsen";
    public static final String TEST_USER_UPDATE_EMAIL = "mmadsen@abv.bg";
    public static final String TEST_USER_UPDATE_PHONE = "0994433234";
    public static final String TEST_USER_UPDATE_PET_NAME = "Tim";
    public static final int TEST_USER_UPDATE_PET_KG = 98;
    public static final String TEST_USER_UPDATE_ADD_IMAGE_TITLE = "updated user image title";
    public static final String TEST_USER_UPDATE_ADD_PET_IMAGE_TITLE = "updated pet image title";


    private Constants() {
    }
}
