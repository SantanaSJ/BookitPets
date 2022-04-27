# CREATE TRIGGER tr_cancelled_bookings
#     AFTER DELETE
#     ON bookings
#     FOR EACH ROW
# BEGIN
#     INSERT INTO booking_history
#     (booking_time, cancelled_on, check_in,
#      check_out, comments, email, first_name, last_name, pet_kilograms,
#      pet_name, phone_number, total_nights, total_price, updated, guest_id,
#      payment_id, property_id, is_completed, is_cancelled, deleted_booking_id)
#     VALUES (OLD.booking_time, CURRENT_TIMESTAMP, OLD.check_in,
#             OLD.check_out, OLD.comments, OLD.email, OLD.first_name, OLD.last_name, OLD.pet_kilograms,
#             OLD.pet_name, OLD.phone_number, OLD.total_nights, OLD.total_price, OLD.updated, OLD.guest_id,
#             OLD.payment_id, OLD.property_id, false, true, OLD.id);
#
#     INSERT INTO rooms_history
#     (number_of_rooms, price, room_id, booking_history_id)
#     SELECT br.number_of_rooms, br.price, br.room_id, h.id
#     FROM booked_rooms as br
#              JOIN booking_history h on br.booking_id = h.deleted_booking_id
#     WHERE h.deleted_booking_id = br.booking_id;
# END;

CREATE PROCEDURE usp_move_cancelled_booking(IN booking_id_param bigint)
BEGIN
    INSERT INTO booking_history
    (booking_time, check_in, check_out, comments, email,
       first_name, last_name, pet_kilograms,
     pet_name, phone_number, total_nights, total_price, updated, guest_id,
     payment_id, property_id, deleted_booking_id, is_completed, is_cancelled,  cancelled_on)
    SELECT b.booking_time,
           b.check_in,
           b.check_out,
           b.comments,
           b.email,
           b.first_name,
           b.last_name,
           b.pet_kilograms,
           b.pet_name,
           b.phone_number,
           b.total_nights,
           b.total_price,
           b.updated,
           b.guest_id,
           b.payment_id,
           b.property_id,
           b.id,
           false,
           true, CURRENT_TIMESTAMP
    FROM bookings as b
    WHERE b.id = booking_id_param;

    INSERT INTO rooms_history
        (number_of_rooms, price, room_id, booking_history_id)
    SELECT br.number_of_rooms, br.price, br.room_id, h.id
    FROM booked_rooms as br
             JOIN booking_history h on br.booking_id = h.deleted_booking_id
    WHERE h.deleted_booking_id = br.booking_id;
end;

# CREATE procedure usp_set_first_night_free(IN booking_id_param bigint)
# BEGIN
#     DECLARE
#         price_per_night INT;
#
#     SELECT b.price
#     INTO price_per_night
#     FROM booked_rooms as b
#     WHERE b.booking_id = booking_id_param
#     LIMIT 1;
# #     call log_msg(concat('myvar is: ', price_per_night));
#
#     UPDATE bookings as b
#         JOIN booked_rooms b2 on b2.id = b.id
#     SET b.total_price = b.total_price - price_per_night
#     WHERE b.id = booking_id_param;
# #     call log_msg(concat('myvar is: ', 'passed'));
# END;

CREATE procedure usp_move_completed_bookings()
BEGIN

    #             IF (SELECT* FROM bookings as b) = CURRENT_DATE THEN
    INSERT INTO booking_history
    (booking_time, check_in, check_out,
     comments, email, first_name, last_name, pet_kilograms,
     pet_name, phone_number, total_nights, total_price, updated,
     guest_id, payment_id, property_id, deleted_booking_id, is_cancelled, is_completed)
    SELECT b.booking_time,
           b.check_in,
           b.check_out,
           b.comments,
           b.email,
           b.first_name,
           b.last_name,
           b.pet_kilograms,
           b.pet_name,
           b.phone_number,
           b.total_nights,
           b.total_price,
           b.updated,
           b.guest_id,
           b.payment_id,
           b.property_id,
           b.id,
           false,
           true
    FROM bookings as b
    WHERE b.check_out = CURRENT_DATE;


    INSERT INTO rooms_history
        (number_of_rooms, price, room_id, booking_history_id)
    SELECT br.number_of_rooms, br.price, br.room_id, h.id
    FROM booked_rooms as br
             JOIN booking_history h on br.booking_id = h.deleted_booking_id
    WHERE h.deleted_booking_id = br.booking_id;

END;

#     call usp_move_completed_bookings();
# ALTER TABLE bookings ADD CONSTRAINT fk_b FOREIGN KEY (id) references booked_rooms(id);

#     DROP PROCEDURE usp_move_completed_bookings;

# https://dev.mysql.com/doc/refman/8.0/en/timestamp-initialization.html
ALTER TABLE bookings
#     MODIFY cancelled_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    MODIFY updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

# to delete bookings with booked_rooms -> from bookings -> foreign keys -> Modify Foreign key
# ALTER TABLE booked_rooms
#     DROP FOREIGN KEY FK3x1lpikb2vk75nx41lxhdicvn;
# ALTER TABLE booked_rooms
#     ADD CONSTRAINT `fk_br_b`
#         FOREIGN KEY (`booking_id`)
#             REFERENCES bookings(`id`)
#             ON DELETE CASCADE ;


# CREATE TRIGGER tr_completed_bookings
#
#     after update
#     on bookings
#     FOR EACH ROW
#
# BEGIN
#     IF NOT(NEW.is_completed <=> OLD.is_completed) THEN
#         INSERT INTO completed_bookings
#         (booking_time, cancellation_date, check_in, check_out,
#          comments, email, first_name, last_name, pet_kilograms,
#          pet_name, phone_number, total_nights, total_price, updated, guest_id,
#          payment_id, property_id)
#         VALUES (OLD.booking_time, OLD.check_in, OLD.cancellation_date,
#                 OLD.check_out, OLD.comments, OLD.email, OLD.first_name, OLD.last_name, OLD.pet_kilograms,
#                 OLD.pet_name, OLD.phone_number, OLD.total_nights, OLD.total_price, OLD.updated, OLD.guest_id,
#                 OLD.payment_id, OLD.property_id);
#     END IF;
#
# END;


#
# CREATE procedure usp_move_completed_bookings()
# BEGIN
#
#     IF ((SELECT b.is_completed FROM bookings as b) = true) THEN
#         INSERT INTO completed_bookings
#         (booking_time, cancellation_date, check_in, check_out,
#          comments, email, first_name, last_name, pet_kilograms,
#          pet_name, phone_number, total_nights, total_price, updated,
#          guest_id, payment_id, property_id, is_completed)
#         SELECT b.booking_time, b.cancellation_date, b.check_in, b.check_out,
#                b.comments, b.email, b.first_name, b.last_name, b.pet_kilograms,
#                b.pet_name, b.phone_number, b.total_nights, b.total_price, b.updated,
#                b.guest_id, b.payment_id, b.property_id, b.is_completed
#         FROM bookings as b;
#     end if;
# END;

# create
#     procedure usp_move_completed_bookings()
# BEGIN
#
#     INSERT INTO completed_bookings
#     SELECT * FROM bookings
#     WHERE check_out = CURRENT_DATE;
#
#
# END;
# SET GLOBAL explicit_defaults_for_timestamp = 0;
# ALTER TABLE bookings MODIFY cancelled_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

# create
#     definer = root@localhost procedure usp_move_completed_bookings()
# BEGIN
#
#     INSERT INTO completed_bookings
#     SELECT *
#     FROM bookings as b
#     WHERE b.check_out = CURRENT_DATE;
#
# #     DELETE
# #     FROM bookings as b
# #     WHERE b.check_out = CURRENT_DATE;
#
#
# END;

CREATE TABLE log_table (
    id int PRIMARY KEY AUTO_INCREMENT
);

# DROP TABLE log_table;
# DROP PROCEDURE `log_msg`;
CREATE PROCEDURE `log_msg`(msg VARCHAR(255))
BEGIN
    insert into log_table select 0, msg;
END
# call log_msg(concat('myvar is: ', myvar, ' and myvar2 is: ', myvar2));

# EXAMPLE PROCEDURE WITH WHILE - NOT WORKING
# CREATE procedure usp_move_completed_bookings()
# BEGIN
#
#     DECLARE
#         counter INT DEFAULT 1;
#     DECLARE
#         max INT DEFAULT 0;
#     SELECT COUNT(b.id) FROM bookings as b INTO max;
#
#     WHILE counter <= max
#         DO
#             #             IF (SELECT* FROM bookings as b) = CURRENT_DATE THEN
#             INSERT INTO completed_bookings
#             (booking_time, cancellation_date, check_in, check_out,
#              comments, email, first_name, last_name, pet_kilograms,
#              pet_name, phone_number, total_nights, total_price, updated,
#              guest_id, payment_id, property_id, is_completed)
#             SELECT b.booking_time, b.cancellation_date, b.check_in, b.check_out,
#                    b.comments, b.email, b.first_name, b.last_name, b.pet_kilograms,
#                    b.pet_name, b.phone_number, b.total_nights, b.total_price, b.updated,
#                    b.guest_id, b.payment_id, b.property_id, b.is_completed
#             FROM bookings as b
#             WHERE b.check_out = CURRENT_DATE;
#             SET counter = counter + 1;
#         end while;
#
# #     IF ((SELECT b.is_completed FROM bookings as b) = true) THEN
#
#
# END;
#
# call usp_move_completed_bookings();
