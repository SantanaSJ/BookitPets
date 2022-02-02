package com.example.onlinehotelbookingsystem.repository;

import com.example.onlinehotelbookingsystem.model.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {


    @Query
            (value = "SELECT count(br.id)" +
                    "FROM bookings as b" +
                    "         JOIN booked_rooms br on b.id = br.booking_id " +
                    "WHERE b.property_id = :hotelId" +
                    "  AND br.room_id = :roomId" +
                    "  AND ((b.check_in >= :checkIn AND b.check_in <= :checkOut)" +
                    "    OR (b.check_out > :checkIn AND b.check_out < :checkOut)" +
                    "    OR (b.check_in < :checkIn AND b.check_out > :checkOut))", nativeQuery = true)
    Integer isRoomAvailableBetween(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut,
                                   @Param("hotelId") Long hotelId,
                                   @Param("roomId") Long roomId);

    //     @Query("SELECT b FROM BookingEntity as b ORDER BY b.id DESC LIMIT 1")
    @Query(value = "SELECT * FROM bookings as b  ORDER BY b.id DESC LIMIT 1", nativeQuery = true)
    BookingEntity getLastByUserId(Long id);

    @Query("SELECT b FROM BookingEntity b WHERE b.guest.id = :id")
    List<BookingEntity> findAllByUserId(@Param("id") Long id);
}
