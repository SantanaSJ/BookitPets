package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.AccommodationEntity;
import com.example.onlinehotelbookingsystem.model.entity.RoomEntity;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.model.service.AccommodationServiceModel;
import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
import com.example.onlinehotelbookingsystem.repository.AccommodationRepository;
import com.example.onlinehotelbookingsystem.repository.AccommodationTypeRepository;
import com.example.onlinehotelbookingsystem.repository.RoomRepository;
import com.example.onlinehotelbookingsystem.service.AccommodationService;
import com.example.onlinehotelbookingsystem.service.RoomService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.onlinehotelbookingsystem.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccommodationServiceImplTest {

    @Mock
    AccommodationRepository mockAccommodationRepository;

    @Mock
    RoomRepository mockRoomRepository;

    @Mock
    AccommodationTypeRepository mockAccommodationTypeRepository;

    private AccommodationEntity accommodationEntityTest1, accommodationEntityTest2;
    private RoomEntity roomEntityToTest;
    private AccommodationService accommodationServiceToTest;
    private List<AccommodationEntity> accommodationEntities;
    private List<RoomEntity> roomEntities;

    private RoomService roomServiceToTest;


    @BeforeEach
    void setUp() {


        this.accommodationEntityTest1 = new AccommodationEntity();
        this.accommodationEntityTest1
                .setName(TEST_HOTEL_NAME)
                .setCategory(TEST_HOTEL_CAT)
                .setCity(TEST_HOTEL_CITY)
                .setType(AccommodationTypeEnum.HOTEL)
                .setDescription(TEST_HOTEL_DESCRIPTION)
                .setAddress(TEST_HOTEL_ADDRESS)
                .setId(TEST_HOTEL_ID);

        this.accommodationEntityTest2 = new AccommodationEntity();
        this.accommodationEntityTest2
                .setName(TEST_HOTEL_NAME_1)
                .setAddress(TEST_HOTEL_ADDRESS_1)
                .setCategory(TEST_HOTEL_CAT)
                .setCity(TEST_HOTEL_CITY_1)
                .setType(AccommodationTypeEnum.HOTEL)
                .setDescription(TEST_HOTEL_DESCRIPTION)
                .setId(TEST_HOTEL_ID_1);

//        this.mockAccommodationRepository.saveAll(List.of(this.accommodationEntityTest1, this.accommodationEntityTest2));
        this.roomEntityToTest = new RoomEntity();
        this.roomEntityToTest
                .setAccommodationEntity(this.accommodationEntityTest1)
                .setCurrentPrice(TEST_ROOM_CURRENT_PRICE)
                .setDescription(TEST_ROOM_DESCRIPTION)
                .setRoomType(TEST_ROOM_TYPE)
                .setId(TEST_ROOM_ID);

        this.roomEntities = new ArrayList<>();
        this.roomEntities.add(this.roomEntityToTest);
        this.accommodationEntities = new ArrayList<>();
        this.accommodationEntities.add(this.accommodationEntityTest1);
        this.accommodationEntities.add(this.accommodationEntityTest2);

        this.roomServiceToTest = new RoomServiceImpl(this.mockRoomRepository, new ModelMapper());
        this.accommodationServiceToTest = new AccommodationServiceImpl(this.mockAccommodationRepository, mockRoomRepository, new ModelMapper(), this.roomServiceToTest);
    }


    @Test
    void findAll_should_not_return_empty_list_when_hotels_exist() {
        when(this.mockAccommodationRepository.findAll()).thenReturn(this.accommodationEntities);
        List<AccommodationViewModel> all = this.accommodationServiceToTest.findAll();
        assertFalse(all.isEmpty());
        assertEquals(2, all.size());
    }

    @Test
    void findAll_should_return_empty_list_when_no_hotels_exist() {
        this.accommodationEntities.clear();
        when(this.mockAccommodationRepository.findAll()).thenReturn(this.accommodationEntities);

        List<AccommodationViewModel> all = this.accommodationServiceToTest.findAll();
        assertTrue(all.isEmpty());

    }

    @Test
    void findById_should_return_list_with_rooms_when_hotel_with_matching_id_exists() {
        when(this.mockAccommodationRepository.findById(TEST_HOTEL_ID_1)).thenReturn(Optional.of(this.accommodationEntityTest2));
        when(this.mockRoomRepository.findByAccommodationEntity_Id(TEST_HOTEL_ID_1)).thenReturn(this.roomEntities);

        AccommodationServiceModel byId = this.accommodationServiceToTest.findById(TEST_HOTEL_ID_1);
        System.out.println();
        assertNotNull(byId);
    }

    @Test
    void findHotels_should_return_list_with_hotel_entities_when_hotels_exist() {
//       search term
    }

    @Test
    void findById_should_throw_exception_when_hotel_with_given_id_not_exists() {
        when(this.mockAccommodationRepository.findById(1L)).thenThrow(new ObjectNotFoundException("id not found!"));
        assertThrows(ObjectNotFoundException.class, () -> this.accommodationServiceToTest.findById(1L));
    }
}