package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.AccommodationEntity;
import com.example.onlinehotelbookingsystem.model.entity.RoomEntity;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.model.service.AccommodationServiceModel;
import com.example.onlinehotelbookingsystem.model.service.RoomServiceModel;
import com.example.onlinehotelbookingsystem.model.view.AccommodationViewModel;
import com.example.onlinehotelbookingsystem.repository.AccommodationRepository;
import com.example.onlinehotelbookingsystem.repository.RoomRepository;
import com.example.onlinehotelbookingsystem.service.AccommodationService;
import com.example.onlinehotelbookingsystem.service.RoomService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper mapper;
    private final RoomService roomService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, RoomRepository roomRepository,
                                    ModelMapper mapper, RoomService roomService) {
        this.accommodationRepository = accommodationRepository;
        this.roomRepository = roomRepository;
        this.mapper = mapper;
        this.roomService = roomService;
    }


    @Override
    public List<AccommodationViewModel> findHotels(String searchTerm) {

        List<AccommodationEntity> properties = this.accommodationRepository.findPropertyEntityByNameIsStartingWith(searchTerm);
        return getAccommodationViewModels(properties);
    }

    @Override
    public List<AccommodationViewModel> findAll() {
        List<AccommodationEntity> properties = this.accommodationRepository.findAll();
        return getAccommodationViewModels(properties);
    }

    @Override
    public AccommodationServiceModel findById(Long id) {
        return this.accommodationRepository
                .findById(id)
                .map(a -> {
                    AccommodationServiceModel serviceModel = this.mapper.map(a, AccommodationServiceModel.class);
                    serviceModel.setType(a.getType().name());
                    List<RoomServiceModel> roomServiceModels = this.roomService.findByHotelId(id);
                    serviceModel.setRooms(roomServiceModels);
                    return serviceModel;
                })
                .orElseThrow(() -> new ObjectNotFoundException("Accommodation with id " + id + " not found!"));

    }

    @Override
    public void initHotels() {
//        ?? &&
        if (this.accommodationRepository.count() == 0 && this.roomRepository.count() == 0) {
            AccommodationEntity accommodationEntity1 = new AccommodationEntity();
            accommodationEntity1
                    .setName("InterContinental Sofia")
                    .setAddress("4 Narodno sabranie")
                    .setCategory(5)
                    .setCity("Sofia")
                    .setImageUrl("https://digital.ihg.com/is/image/ihg/intercontinental-sofia-5488872488-2x1?wid=1440&fit=fit,1")
                    .setPostalCode(1000)
                    .setType(AccommodationTypeEnum.HOTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("Located in the heart of Sofia, InterContinental Sofia offers two restaurants,\n" +
                            "        a 24/7 fitness centre and city and mountain views. The hotel’s location provides easy access to the main historical and cultural buildings,\n" +
                            "        as well as to the shopping area. Among the facilities at this property are a concierge service and ticket service, along with free WiFi throughout the property.\n" +
                            "        The accommodation features a 24-hour front desk, room service and currency exchange for guests.")
                    .setLat("42.69327")
                    .setLng("23.33212");

            RoomEntity roomEntity1 = new RoomEntity();
            roomEntity1
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(135))
                    .setAccommodationEntity(accommodationEntity1)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity2 = new RoomEntity();
            roomEntity2
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(200))
                    .setAccommodationEntity(accommodationEntity1)
                    .setDescription("Sleeps two people. Breakfast is included.");

            RoomEntity roomEntity3 = new RoomEntity();
            roomEntity3
                    .setRoomType("triple")
                    .setCurrentPrice(BigDecimal.valueOf(295))
                    .setAccommodationEntity(accommodationEntity1)
                    .setDescription("Sleeps three people. Breakfast is included.");


            AccommodationEntity accommodationEntity2 = new AccommodationEntity();
            accommodationEntity2
                    .setName("Best Western Premier Sofia Airport Hotel")
                    .setAddress("11 Brussels, Blvd.")
                    .setCategory(4)
                    .setCity("Sofia")
                    .setImageUrl("https://pix8.agoda.net/hotelImages/4843379/-1/f3fd0b7aa01da8aed8f4c6ab6cf7c007.jpg?ca=0&ce=1&s=1024x768")
                    .setPostalCode(1592)
                    .setType(AccommodationTypeEnum.HOTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("The modern 4-star Best Western Premier Sofia Airport Hotel is located in a new business district,\n" +
                            "         only 750 m away by car from Sofia International Airport Terminal 1 and 1 km from Sofia International Airport Terminal 2. It is 900 m away from Sofia Airport Centre and 2.3 km away from Trade Center Europe.\n" +
                            "         The hotel offers room service by a robot. Roomy the robot butler''s assistance consists of delivering food, beverages and room amenities. An onsite Grab&Go early breakfast is offered for guests from 03:00 to 07:00,\n" +
                            "         and a buffet breakfast is available in the Barillon 1909 restaurant from 07:00 to 10:00.")
                    .setLat("42.6809944")
                    .setLng("23.3986898");

            RoomEntity roomEntity4 = new RoomEntity();
            roomEntity4
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(120))
                    .setAccommodationEntity(accommodationEntity2)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity5 = new RoomEntity();
            roomEntity5
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(200))
                    .setAccommodationEntity(accommodationEntity2)
                    .setDescription("Sleeps two people. Breakfast is included.");

            RoomEntity roomEntity6 = new RoomEntity();
            roomEntity6
                    .setRoomType("triple")
                    .setCurrentPrice(BigDecimal.valueOf(260))
                    .setAccommodationEntity(accommodationEntity2)
                    .setDescription("Sleeps three people. Breakfast is included.");


            AccommodationEntity accommodationEntity3 = new AccommodationEntity();
            accommodationEntity3
                    .setName("Best Western Art Plaza Hotel")
                    .setAddress("46 Hristo Belchev str.")
                    .setCategory(3)
                    .setCity("Sofia")
                    .setImageUrl("https://q-xx.bstatic.com/xdata/images/hotel/840x460/59123177.jpg?k=637ee48140339b496ad0c4f25abc059a58e25b27851d0e0516601967878bc76e&o=")
                    .setPostalCode(1000)
                    .setType(AccommodationTypeEnum.HOTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("Art Plaza Hotel is located in the city centre of Sofia. Vitosha boulevard pedestrian street with cafes and shops is a 1-minute walk away.\n" +
                            "         Free WiFi access is available at the property. Each room here will provide you with a TV, air conditioning and cable channels. There is also an electric kettle.\n" +
                            "         Featuring a hairdryer, private bathroom also comes with bathrobes and free toiletries. Extras include bed linen. At Art Plaza Hotel you will find a 24-hour front desk. Other facilities offered at the property include luggage storage.\n" +
                            "         Complimentary coffee and tea facilities, as well as a business center with copy machine are at guests disposal.")
                    .setLat("42.68948")
                    .setLng("23.32049");

            RoomEntity roomEntity7 = new RoomEntity();
            roomEntity7
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(135))
                    .setAccommodationEntity(accommodationEntity3)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity8 = new RoomEntity();
            roomEntity8
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(200))
                    .setAccommodationEntity(accommodationEntity3)
                    .setDescription("Sleeps two people. Breakfast is included.");

            RoomEntity roomEntity9 = new RoomEntity();
            roomEntity9
                    .setRoomType("triple")
                    .setCurrentPrice(BigDecimal.valueOf(285))
                    .setAccommodationEntity(accommodationEntity3)
                    .setDescription("Sleeps three people. Breakfast is included.");


            AccommodationEntity accommodationEntity4 = new AccommodationEntity();
            accommodationEntity4
                    .setName("Ramada by Wyndham Sofia City Center")
                    .setAddress("Maria Luiza 131 Blvd.")
                    .setCategory(4)
                    .setCity("Sofia")
                    .setImageUrl("https://q-xx.bstatic.com/xdata/images/hotel/840x460/56567025.jpg?k=fb78488aa6e14d2cabc47680fef6229f4f8649ec40ac2eb40aa1c53843b5334a&o=")
                    .setPostalCode(1000)
                    .setType(AccommodationTypeEnum.HOTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("The Ramada by Wyndham Sofia City Center is situated within walking distance to tourist sights and commercial areas of the city, offering comfortable rooms in a modern building.\n" +
                            "         Free WiFi is available in all areas. Being 12 km from Sofia International Airport, near to Central Railway and International Bus Station, the hotel enjoys a convenient location and won the Bulgaria''s 2009 Best Convention Hotel prize.\n" +
                            "         An airport shuttle service is available on request and for a surcharge.")
                    .setLat("42.708236")
                    .setLng("23.322257");

            RoomEntity roomEntity10 = new RoomEntity();
            roomEntity10
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(135))
                    .setAccommodationEntity(accommodationEntity4)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity11 = new RoomEntity();
            roomEntity11
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(200))
                    .setAccommodationEntity(accommodationEntity4)
                    .setDescription("Sleeps two people. Breakfast is included.");

            AccommodationEntity accommodationEntity5 = new AccommodationEntity();
            accommodationEntity5
                    .setName("Central Hotel Sofia")
                    .setAddress("52, Hristo Botev Blvd.")
                    .setCategory(4)
                    .setCity("Sofia")
                    .setImageUrl("https://central-hotel.com/wp-content/uploads/2014/12/histori.jpg")
                    .setPostalCode(1000)
                    .setType(AccommodationTypeEnum.HOTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("Central Hotel Sofia offers accommodation in the heart of Sofia. The hotel has an underground valet parking at a surcharge and a relax centre with a hot tub,\n" +
                            "         a sauna, a steam bath and a relax zone. Guests can enjoy a meal at the on site restaurant. Free WiFi is at guests disposal. Shuttle services can be organized upon request and at a surcharge.")
                    .setLat("42.6981")
                    .setLng("23.3157");

            RoomEntity roomEntity12 = new RoomEntity();
            roomEntity12
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(135))
                    .setAccommodationEntity(accommodationEntity5)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity13 = new RoomEntity();
            roomEntity13
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(200))
                    .setAccommodationEntity(accommodationEntity5)
                    .setDescription("Sleeps two people. Breakfast is included.");

            AccommodationEntity accommodationEntity6 = new AccommodationEntity();
            accommodationEntity6
                    .setName("Hostel Mostel Sofia")
                    .setAddress("2a, Makedonia blvd.")
                    .setCategory(1)
                    .setCity("Sofia")
                    .setImageUrl("https://pix8.agoda.net/hotelImages/261/261128/261128_1212021023008873354.jpg?ca=0&ce=1&s=1024x768")
                    .setPostalCode(1606)
                    .setType(AccommodationTypeEnum.HOSTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("Hostel Mostel Sofia is set in a renovated building from the 19th century, enjoying a quiet location in the centre of Sofia. A modern, shared kitchen is at guests’ disposal.\n" +
                            "         Serdika underground railway station is a 10-minute walk away.\n" +
                            "         The comfortably furnished rooms have private and shared facilities. All rooms have access to free Wi-Fi and shared computers are available upon request. Guests may use the common living room which features a billiard table.\n" +
                            "         A communal washing machine and dryer are available, while lockers are provided in the dormitory rooms. Hairdryer and ironing facilities are upon request.")
                    .setLat("42.6948")
                    .setLng("23.3147");

            RoomEntity roomEntity14 = new RoomEntity();
            roomEntity14
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(30))
                    .setAccommodationEntity(accommodationEntity6)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity15 = new RoomEntity();
            roomEntity15
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(50))
                    .setAccommodationEntity(accommodationEntity6)
                    .setDescription("Sleeps two person. Breakfast is included.");

            RoomEntity roomEntity16 = new RoomEntity();
            roomEntity16
                    .setRoomType("triple")
                    .setCurrentPrice(BigDecimal.valueOf(70))
                    .setAccommodationEntity(accommodationEntity6)
                    .setDescription("Sleeps three people. Breakfast is included.");

            AccommodationEntity accommodationEntity7 = new AccommodationEntity();
            accommodationEntity7
                    .setName("Sofia Palace Hotel by HMG")
                    .setAddress("8 Tri ushi street")
                    .setCategory(4)
                    .setCity("Sofia")
                    .setImageUrl("https://cf.bstatic.com/xdata/images/hotel/max1280x900/240252483.jpg?k=3a9b61176e4c68be19f28d09b94087cda7b2cfc37b28fc0ebb5be145df58bafc&o=&hp=1")
                    .setPostalCode(1000)
                    .setType(AccommodationTypeEnum.HOTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("Situated in Sofia, 300 m from the main shopping street Vitosha, Sofia Palace Hotel by HMG features accommodation with a restaurant, private parking, a fitness centre and a bar.\n" +
                            "         The property is set 1.5 km from Ivan Vazov Theater, 2 km from Cathedral Saint Alexandar Nevski and 2.8 km from NDK. The accommodation offers a 24-hour front desk, airport transfers, room service and free WiFi throughout the property.")
                    .setLat("42.6959")
                    .setLng("23.3164");

            RoomEntity roomEntity17 = new RoomEntity();
            roomEntity17
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(80))
                    .setAccommodationEntity(accommodationEntity7)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity18 = new RoomEntity();
            roomEntity18
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(120))
                    .setAccommodationEntity(accommodationEntity7)
                    .setDescription("Sleeps two people. Breakfast is included.");

            AccommodationEntity accommodationEntity8 = new AccommodationEntity();
            accommodationEntity8
                    .setName("Hotel Budapest")
                    .setAddress("Sofia City Center")
                    .setCategory(3)
                    .setCity("Sofia")
                    .setImageUrl("https://q-xx.bstatic.com/xdata/images/hotel/840x460/272096959.jpg?k=9165a019634a9446e79adda58279427819bedf8a54c5a2e92fa189a23644d1fa&o=")
                    .setPostalCode(1000)
                    .setType(AccommodationTypeEnum.HOTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("Budapest Hotel is within a 5-minute walk from the Central Train Station, in the very centre of Sofia. All rooms feature free Wi-Fi access. The metro station Luvov Most is within a 5-minute walk away.\n" +
                            "         The Budapest Restaurant serves buffet and à la carte dishes. Guests can choose among modern Bulgarian and Hungarian cuisine. In the lobby bar there is a wide range of refreshments, cocktails, snacks and desserts on offer.")
                    .setLat("42.7075")
                    .setLng("23.3266");

            RoomEntity roomEntity19 = new RoomEntity();
            roomEntity19
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(65))
                    .setAccommodationEntity(accommodationEntity8)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity20 = new RoomEntity();
            roomEntity20
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(125))
                    .setAccommodationEntity(accommodationEntity8)
                    .setDescription("Sleeps two people. Breakfast is included.");

            AccommodationEntity accommodationEntity9 = new AccommodationEntity();
            accommodationEntity9
                    .setName("Grand Hotel Sofia")
                    .setAddress("Gourko Str.")
                    .setCategory(5)
                    .setCity("Sofia")
                    .setImageUrl("https://q-xx.bstatic.com/xdata/images/hotel/840x460/323358408.jpg?k=26e647a67ebb6dd51f6fcb0e42e422b99d63991c4fcde6b6225bcfd3407a8e18&o=")
                    .setPostalCode(1000)
                    .setType(AccommodationTypeEnum.HOTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("Set a 5-minute walk from the Serdika underground railway station, the 5-star Grand Hotel Sofia is located right in the heart of Sofia, overlooking the City Garden.\n" +
                            "         It offers large rooms, free Wi-Fi and free indoor parking. The National Theatre, the Bulgarian National bank and all government institutions are just a few steps away from the Grand Hotel Sofia. The closest metro station is a 5-minute walk away.")
                    .setLat("42.6940")
                    .setLng("23.3248");

            RoomEntity roomEntity21 = new RoomEntity();
            roomEntity21
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(100))
                    .setAccommodationEntity(accommodationEntity9)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity22 = new RoomEntity();
            roomEntity22
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(195))
                    .setAccommodationEntity(accommodationEntity9)
                    .setDescription("Sleeps two people. Breakfast is included.");

            AccommodationEntity accommodationEntity10 = new AccommodationEntity();
            accommodationEntity10
                    .setName("Metropolitan Hotel Sofia")
                    .setAddress("64 Tsarigradsko shosse Blvd")
                    .setCategory(5)
                    .setCity("Sofia")
                    .setImageUrl("https://pix8.agoda.net/hotelImages/969/96972/96972_1112230202005330218.jpg?s=1024x768")
                    .setPostalCode(1784)
                    .setType(AccommodationTypeEnum.HOTEL)
                    .setCheckInTime(LocalTime.of(15, 0))
                    .setCheckOutTime(LocalTime.of(12, 0))
                    .setDescription("Metropolitan Hotel Sofia provides free WiFi, free airport shuttle, upon request and availability and free private parking on site. The relax centre comes with a gym, a sauna and a steam bath.\n" +
                            "         The International Expo Centre and Sofia Airport are both set 5 km away and the Mall shopping centre can be reached in a 5-minute walk.\n" +
                            "         The stylish air-conditioned rooms offer a free minibar and a free safety deposit box. A flat-screen TV with cable and satellite channels and a work desk are featured as well. The bathroom is equipped with a shower or a bath, free toiletries and a hairdryer.\n" +
                            "         Panoramic city views can be enjoyed from all rooms.")
                    .setLat("42.6570")
                    .setLng("23.3828");

            RoomEntity roomEntity23 = new RoomEntity();
            roomEntity23
                    .setRoomType("single")
                    .setCurrentPrice(BigDecimal.valueOf(85))
                    .setAccommodationEntity(accommodationEntity10)
                    .setDescription("Sleeps one person. Breakfast is included.");

            RoomEntity roomEntity24 = new RoomEntity();
            roomEntity24
                    .setRoomType("double")
                    .setCurrentPrice(BigDecimal.valueOf(165))
                    .setAccommodationEntity(accommodationEntity10)
                    .setDescription("Sleeps two people. Breakfast is included.");

            this.accommodationRepository.saveAll(List.of(
                    accommodationEntity1, accommodationEntity2,
                    accommodationEntity3, accommodationEntity4,
                    accommodationEntity5, accommodationEntity6,
                    accommodationEntity7, accommodationEntity8,
                    accommodationEntity9, accommodationEntity10));
            this.roomRepository.saveAll(List.of(
                    roomEntity1, roomEntity2,
                    roomEntity3, roomEntity4,
                    roomEntity5, roomEntity6,
                    roomEntity7, roomEntity8,
                    roomEntity9, roomEntity10,
                    roomEntity11, roomEntity12,
                    roomEntity13, roomEntity14,
                    roomEntity15, roomEntity16,
                    roomEntity17, roomEntity18,
                    roomEntity19, roomEntity20,
                    roomEntity21, roomEntity22,
                    roomEntity23, roomEntity24));
        }

    }


    private List<AccommodationViewModel> getAccommodationViewModels(List<AccommodationEntity> properties) {
        List<AccommodationServiceModel> serviceModels = properties
                .stream()
                .map(a -> {
                    AccommodationServiceModel map = this.mapper.map(a, AccommodationServiceModel.class);
                    map.setType(a.getType().name());
                    return map;
                })
                .collect(Collectors.toList());
        return serviceModels
                .stream()
                .map(s -> this.mapper.map(s, AccommodationViewModel.class))

                .collect(Collectors.toList());
    }


}
