package com.example.onlinehotelbookingsystem.repository;

import com.example.onlinehotelbookingsystem.model.entity.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    //    admin all active bookings pageable /ok
    @Query("SELECT b FROM BookingEntity as b WHERE b.isCancelled = false AND b.isCompleted = false")
    Page<BookingEntity> findAllActive(Pageable pageable);

    //    admin/user active booking by id/ details /ok
    @Query("SELECT b FROM BookingEntity as b WHERE b.id = :bookingId AND (b.isCompleted = false AND b.isCancelled = false)")
    Optional<BookingEntity> findActiveBookingById(@Param("bookingId") Long bookingId);

    //    admin all passed bookings paginated
    @Query("SELECT b FROM BookingEntity as b WHERE b.isCompleted = true OR b.isCancelled = true")
    Page<BookingEntity> findAllCompletedAndCancelledBookings(Pageable pageable);

    //    admin/user passed booking by id/ details/ok
    @Query("SELECT b FROM BookingEntity as b WHERE b.id = :bookingId AND (b.isCompleted = true OR b.isCancelled = true)")
//    @Query(value = "SELECT * FROM bookings as b WHERE b.id = :bookingId AND (b.is_completed = true OR b.is_cancelled = true)", nativeQuery = true)
    Optional<BookingEntity> findPassedBookingById(@Param("bookingId") Long bookingId);

    //user
    //active all /ok
    // @Query(value = "SELECT * FROM bookings as b WHERE b.guest_id = :userId", nativeQuery = true)
    @Query("SELECT b FROM BookingEntity as b WHERE b.guest.id = :userId AND (b.isCancelled = false AND b.isCompleted = false)")
    List<BookingEntity> findAllActiveBookingsBy(@Param("userId") Long userId);

    @Query("SELECT b FROM BookingEntity as b WHERE b.checkOut <= CURRENT_DATE AND b.isCancelled = false")
    List<BookingEntity> findAllActiveBookingsWithCheckOutToday();

    //user
    //all passed bookings /ok
//    @Query(value = "SELECT * from bookings as b WHERE b.guest_id = :userId AND (b.is_completed = true OR b.is_cancelled = true)", nativeQuery = true)
    @Query("SELECT b FROM BookingEntity  as b WHERE b.guest.id = :userId AND (b.isCancelled = TRUE OR b.isCompleted = TRUE)")
    List<BookingEntity> findAllCompleteAndCancelledBookingsByUserId(@Param("userId") Long userId);


    //delete all completed from bookings -> ok
//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM BookingEntity b WHERE b.checkOut = CURRENT_DATE")
//    void deleteAllCompleted();


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



//    CREATE procedure usp_set_first_night_free(IN booking_id_param bigint)
//    BEGIN
//    #     DECLARE @price_per_night INT;
//
//    SET @price_per_night = (SELECT b.price
//    FROM booked_rooms as b
//    WHERE b.booking_id = booking_id_param);
//
//    UPDATE bookings as b
//    JOIN booked_rooms b2 on b2.id = b.id
//    SET b.total_price = b.total_price - @price_per_night
//    WHERE b.id = b2.booking_id;
//    END;




//    create trigger tr_cancelled_bookings
//    after delete
//    on bookings
//    for each row
//    BEGIN INSERT INTO cancelled_bookings
//            (booking_time, cancellation_date, cancelled_on, check_in,
//             check_out, comments, email, first_name, last_name, pet_kilograms,
//             pet_name, phone_number, total_nights, total_price, updated, guest_id,
//             payment_id, property_id)
//    VALUES (OLD.booking_time, OLD.cancellation_date, OLD.cancelled_on, OLD.check_in,
//            OLD.check_out, OLD.comments, OLD.email, OLD.first_name, OLD.last_name, OLD.pet_kilograms,
//            OLD.pet_name, OLD.phone_number, OLD.total_nights, OLD.total_price, OLD.updated, OLD.guest_id,
//            OLD.payment_id, OLD.property_id);
//    END;




//    CREATE PROCEDURE usp_move_cancelled_booking(IN booking_id_param bigint)
//      BEGIN
//     INSERT INTO booking_history
//     (booking_time, check_in, check_out, comments, email,
//                   first_name, last_name, pet_kilograms,
//                  pet_name, phone_number, total_nights, total_price, updated, guest_id,
//      payment_id, property_id, deleted_booking_id, is_completed, is_cancelled, cancelled_on, lat, lng)
//
//    SELECT b.booking_time,
//    b.check_in,
//    b.check_out,
//    b.comments,
//    b.email,
//    b.first_name,
//    b.last_name,
//    b.pet_kilograms,
//    b.pet_name,
//    b.phone_number,
//    b.total_nights,
//    b.total_price,
//    b.updated,
//    b.guest_id,
//    b.payment_id,
//    b.property_id,
//    b.id,
//            false,
//            true,
//    CURRENT_TIMESTAMP,
//    p.lat,
//    p.lng
//    FROM bookings
//    as b
//    JOIN properties
//    as p
//    on b.property_id =p.id
//    WHERE b.id =booking_id_param;
//
//    INSERT INTO
//
//    rooms_history
//            (number_of_rooms, price, room_id, booking_history_id)
//
//    SELECT br.number_of_rooms,br.price,br.room_id,h.id
//    FROM booked_rooms
//    as br
//    JOIN booking_history
//    h on
//    br.booking_id =h.deleted_booking_id
//    WHERE h.deleted_booking_id =br.booking_id;
//    END
//            DELIMITER;


}
