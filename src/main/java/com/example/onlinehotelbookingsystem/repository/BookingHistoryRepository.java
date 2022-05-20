//package com.example.onlinehotelbookingsystem.repository;
//
//import com.example.onlinehotelbookingsystem.model.entity.BookingHistoryEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface BookingHistoryRepository extends JpaRepository<BookingHistoryEntity, Long> {
//
//    //completed by id
//    Optional<BookingHistoryEntity> findById(Long bookingId);
//
//    //completed all
//    @Query(value = "SELECT b FROM BookingHistoryEntity as b WHERE b.guest.id = :userId")
//    List<BookingHistoryEntity> findAllByGuestId(@Param("userId") Long userId);
//}
