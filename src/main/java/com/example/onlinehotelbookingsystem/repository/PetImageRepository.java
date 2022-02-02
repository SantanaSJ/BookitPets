package com.example.onlinehotelbookingsystem.repository;

import com.example.onlinehotelbookingsystem.model.entity.PetImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetImageRepository extends JpaRepository<PetImageEntity, Long> {
}
