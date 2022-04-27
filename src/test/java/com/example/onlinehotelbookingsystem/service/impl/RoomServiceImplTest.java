package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.AccommodationEntity;
import com.example.onlinehotelbookingsystem.model.entity.RoomEntity;
import com.example.onlinehotelbookingsystem.model.service.RoomServiceModel;
import com.example.onlinehotelbookingsystem.repository.RoomRepository;
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
class RoomServiceImplTest {

    @Mock
    private RoomRepository mockRoomRepository;

    private RoomService roomService;

    private List<RoomEntity> roomEntities;
    private RoomEntity roomEntityTest1, roomEntityTest2;
    private AccommodationEntity accommodationEntityTest;


    @BeforeEach
    void setUp() {
        this.roomEntities = new ArrayList<>();

        this.accommodationEntityTest = new AccommodationEntity();
        this.accommodationEntityTest.setId(TEST_HOTEL_ID);

        this.roomEntityTest1 = new RoomEntity();
        this.roomEntityTest2 = new RoomEntity();
        roomEntityTest1
                .setRoomType(TEST_ROOM_TYPE)
                .setAccommodationEntity(this.accommodationEntityTest)
                .setId(TEST_ROOM_ID);
        roomEntityTest2
                .setRoomType(TEST_ROOM_TYPE_1)
                .setAccommodationEntity(this.accommodationEntityTest)
                .setId(TEST_ROOM_ID_1);

        this.roomEntities.add(roomEntityTest1);
        this.roomEntities.add(roomEntityTest2);


        this.roomService = new RoomServiceImpl(this.mockRoomRepository, new ModelMapper());

    }

    @Test
    void whenFindByHotelId_verifyThatListIsNotEmpty() {
        when(this.mockRoomRepository.findByAccommodationEntity_Id(this.accommodationEntityTest.getId())).thenReturn(this.roomEntities);

        List<RoomServiceModel> roomsByHotelId = this.roomService.findByHotelId(TEST_HOTEL_ID);
        assertFalse(roomsByHotelId.isEmpty());

        assertEquals(2, roomsByHotelId.size());
    }

    @Test
    void whenFindByHotelId_verifyThatListIsEmptyWhenNoRooms() {
        this.roomEntities.clear();
        List<RoomServiceModel> roomsByHotelId = this.roomService.findByHotelId(TEST_HOTEL_ID);
        assertTrue(roomsByHotelId.isEmpty());
    }

    @Test
    void whenFindById_verifyThatIsNotNull() {
        when(this.mockRoomRepository.findById(this.accommodationEntityTest.getId())).thenReturn(Optional.of(roomEntityTest1));

        RoomEntity roomById = this.roomService.findById(TEST_ROOM_ID);
        assertNotNull(roomById);
        assertEquals(TEST_ROOM_ID, roomById.getId());
    }

    @Test
    void whenFindByIdIsNotFound_verifyThatThrowsException() {
        when(this.mockRoomRepository.findById(TEST_ROOM_ID_1)).thenThrow(new ObjectNotFoundException("id not found!"));
        assertThrows(ObjectNotFoundException.class, () -> this.roomService.findById(TEST_ROOM_ID_1));
    }

}