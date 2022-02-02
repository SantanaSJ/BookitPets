package com.example.onlinehotelbookingsystem.repository;

import com.example.onlinehotelbookingsystem.model.entity.BookedRoomsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedRoomsRepository extends JpaRepository<BookedRoomsEntity, Long> {
}
