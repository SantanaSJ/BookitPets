package com.example.onlinehotelbookingsystem.repository;

import com.example.onlinehotelbookingsystem.model.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
                    "    OR (b.check_out > :checkIn AND b.check_out <= :checkOut)" +
                    "    OR (b.check_in < :checkIn AND b.check_out > :checkOut))", nativeQuery = true)
    Integer isRoomAvailableBetween(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut,
                                   @Param("hotelId") Long hotelId,
                                   @Param("roomId") Long roomId);

    //     @Query("SELECT b FROM BookingEntity as b ORDER BY b.id DESC LIMIT 1")
//    @Query(value = "SELECT * FROM bookings as b  ORDER BY b.id DESC LIMIT 1", nativeQuery = true)
//    BookingEntity getLastByUserId(Long id);

//    @Query("SELECT b FROM BookingEntity b WHERE b.guest.id = :id")
//    List<BookingEntity> findAllByUserId(@Param("id") Long id);

    //    @Procedure(name = "usp_set_first_night_free")
//    @Modifying
//    @Transactional
//    @Query(value = "CALL usp_set_first_night_free(:id)", nativeQuery = true)
//    void setFirstNightFree(@Param("id") Long id);

    //    ResultSet is from UPDATE. No Data. exc
//    @Modifying
////    TransactionRequiredException: Executing an update/delete query
//    @Transactional
//    @Query(value = "UPDATE bookings as b" +
//            " SET b.cancelled_on = CURRENT_TIMESTAMP" +
//            " WHERE b.id = :id", nativeQuery = true)
//    void setCancelledOn(@Param("id") Long id);

//    @Modifying
//    @Transactional
//    @Query(value = "CALL usp_move_completed_bookings()", nativeQuery = true)
//    void moveBookings();

//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM bookings as b WHERE b.check_out = CURRENT_DATE", nativeQuery = true)
//    void deleteCompletedBookings();

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE bookings as b SET b.is_completed = 1 WHERE b.check_out = CURRENT_DATE", nativeQuery = true)
//    void setCompleted();


    //active all
    // @Query(value = "SELECT * FROM bookings as b WHERE b.guest_id = :userId", nativeQuery = true)
    @Query(value = "SELECT b FROM BookingEntity as b WHERE b.guest.id = :userId")
    List<BookingEntity> findAllActiveBookingsBy(@Param("userId") Long userId);


    //delete all completed from bookings -> ok
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM BookingEntity b WHERE b.checkOut = CURRENT_DATE")
    void deleteAllCompleted();

    //@Procedure(name = "usp_move_completed_bookings") -> ok
//    @Modifying
//    @Transactional
//    @Query(value = "CALL usp_move_completed_bookings", nativeQuery = true)
//    void moveCompletedBookingsToHistory();

//    get all Completed from BookingEntity
//    @Query(value = "SELECT b FROM BookingEntity b WHERE b.checkOut = CURRENT_DATE")
//    List<BookingEntity> getAllCompletedBookings();

//    @Modifying
//    @Transactional
//    @Query(value = "CALL usp_move_cancelled_booking(:id)", nativeQuery = true)
//    void moveCancelledBookingToHistory(Long id);
}
