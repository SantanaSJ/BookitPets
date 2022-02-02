package com.example.onlinehotelbookingsystem.repository;

import com.example.onlinehotelbookingsystem.model.entity.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {


//    TODO: find results containing the search, not only in the beginning

//    @Query("select p from PropertyEntity p where p.name like %:searchTerm%")
//    List<PropertyEntity> findHotels(@Param("searchTerm")String searchTerm);
    List<AccommodationEntity> findPropertyEntityByNameIsStartingWith(String term);


//    @Query("SELECT r.type FROM Properties as p")
//     void isRoomAvailableBetween(LocalDate checkIn, LocalDate checkOut, String type);

    //    @Query("SELECT ")
//    boolean isAvailableBetween(LocalDate checkIn, LocalDate checkOut);
}
