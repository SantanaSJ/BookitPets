package com.example.onlinehotelbookingsystem.repository;

import com.example.onlinehotelbookingsystem.model.entity.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {

    List<AccommodationEntity> findPropertyEntityByNameIsStartingWith(String term);

}
