# hotels
INSERT INTO `properties` (id, address, category, city, image_url, name, postal_code, type_id, check_in_time,
                          check_out_time)
VALUES (1, '4 Narodno sabranie', '5', 'Sofia',
        'https://digital.ihg.com/is/image/ihg/intercontinental-sofia-5488872488-2x1?wid=1440&fit=fit,1',
        'InterContinental Sofia', 1000, 1, '15:00', '12:00'),
       (2, '11 Brussels, Blvd.', '4', 'Sofia',
        'https://pix8.agoda.net/hotelImages/4843379/-1/f3fd0b7aa01da8aed8f4c6ab6cf7c007.jpg?ca=0&ce=1&s=1024x768',
        'Best Western Premier Sofia Airport Hotel', 1592, 1, '15:00', '12:00'),
       (3, '46 Hristo Belchev str.', '3', 'Sofia',
        'https://q-xx.bstatic.com/xdata/images/hotel/840x460/59123177.jpg?k=637ee48140339b496ad0c4f25abc059a58e25b27851d0e0516601967878bc76e&o=',
        'Best Western Art Plaza Hotel', 1000, 1, '15:00', '12:00'),
       (4, 'Maria Luiza 131 Blvd,', '4', 'Sofia',
        'https://q-xx.bstatic.com/xdata/images/hotel/840x460/56567025.jpg?k=fb78488aa6e14d2cabc47680fef6229f4f8649ec40ac2eb40aa1c53843b5334a&o=',
        'Ramada by Wyndham Sofia City Center', 1000, 1, '15:00', '12:00'),
       (5, '52, Hristo Botev Blvd.', '4', 'Sofia', 'https://central-hotel.com/wp-content/uploads/2014/12/histori.jpg',
        'Central Hotel Sofia', 1000, 1, '15:00', '12:00'),
       (6, '2a, Makedonia blvd', '1', 'Sofia',
        'https://pix8.agoda.net/hotelImages/261/261128/261128_1212021023008873354.jpg?ca=0&ce=1&s=1024x768',
        'Hostel Mostel Sofia', 1606, 3, '15:00', '12:00'),
       (7, 'Sofia City Center', 'n/a', 'Sofia',
        'https://cf.bstatic.com/xdata/images/hotel/max1024x768/192570537.jpg?k=d550f05422d6a0ea3c66e2a9771ee650c64357a5bb364cce5b08a0efe8849b82&o=&hp=1',
        'Apartment Audrey', 1000, 4, '15:00', '12:00'),
       (8, 'Sofia City Center', 'n/a', 'Sofia',
        'https://q-xx.bstatic.com/xdata/images/hotel/840x460/272096959.jpg?k=9165a019634a9446e79adda58279427819bedf8a54c5a2e92fa189a23644d1fa&o=',
        'SunShine Apartment - Top Center', 1000, 4, '15:00', '12:00'),
       (9, '1, Gourko Str.', '5', 'Sofia',
        'https://q-xx.bstatic.com/xdata/images/hotel/840x460/323358408.jpg?k=26e647a67ebb6dd51f6fcb0e42e422b99d63991c4fcde6b6225bcfd3407a8e18&o=',
        'Grand Hotel Sofia', 1000, 1, '15:00', '12:00'),
       (10, '64 Tsarigradsko shosse Blvd', '5', 'Sofia',
        'https://pix8.agoda.net/hotelImages/969/96972/96972_1112230202005330218.jpg?s=1024x768',
        'Metropolitan Hotel Sofia', 1784, 1, '15:00', '12:00');


# rooms
INSERT INTO `rooms` (id, room_type, current_price, accommodation_entity_id)
VALUES (1, 'single', 135, 1),
       (2, 'double', 200, 1),
       (3, 'single', 120, 2),
       (4, 'double', 200, 2),
       (5, 'single', 135, 3),
       (6, 'double', 200, 3),
       (7, 'single', 135, 4),
       (8, 'double', 200, 4),
       (9, 'single', 135, 5),
       (10, 'double', 200, 5);
# to add more rooms


# Booking test
INSERT INTO `bookings` (id, booking_time, check_out, check_in, email, number_of_people, guest_id, property_id, pet_name,
                        pet_kilograms)
VALUES (1, '20:00:00', '2022-01-27', '2022-01-20', 'sisi@abv.bg', 2, 1, 1, 'Bren', 34),
       (2, '21:00:00', '2022-01-29', '2022-01-28', 'sisi@abv.bg', 2, 1, 1, 'Rocky', 25);

INSERT INTO `booked_rooms`(id, number_of_rooms, price, booking_id, room_id)
VALUES (1, 2, 135, 1, 1),
       (2, 1, 200, 2, 2);

# ==============================================================
# queries
SELECT count(r.id)
FROM bookings as b
         JOIN rooms as r ON b.id = r.id
WHERE r.id = :roomId
  AND r.accommodation_entity_id = :hotelId
  AND b.check_out > :checkIn
  AND b.check_in < :checkOut;

SELECT count(br.id)
FROM bookings as b
         JOIN booked_rooms br on b.id = br.booking_id
WHERE b.property_id = 1
    AND br.room_id = 1
    AND b.check_in >= '2022-01-29' AND b.check_in < '2022-01-30'
   OR b.check_out > '2022-01-29' AND b.check_out < '2022-01-30'
   OR b.check_in < '2022-01-29' AND b.check_out > '2022-01-30'
#          LEFT JOIN bookings b
#                    on br.booking_id = b.id
# WHERE b.property_id = 1
#   AND b.check_in = '2022-01-27'
#   AND b.check_out = '2022-01-20'
;

SELECT *
FROM booked_rooms as br
         JOIN bookings b on br.booking_id = b.id
WHERE b.property_id = 1
  AND br.room_id = 1
  AND '2022-01-20' >= b.check_out
  AND '2022-01-27' <= b.check_in
;

SELECT *
FROM bookings as b
WHERE check_in >= '2022-01-29'
  AND check_out <= '2022-01-30';

# Last
SELECT count(br.id)
FROM bookings as b
         JOIN booked_rooms br on b.id = br.booking_id
WHERE b.property_id = 1
  AND br.room_id = 2
  AND ((b.check_in >= '2022-01-28' AND b.check_in <= '2022-01-29'
    OR (b.check_out > '2022-01-28' AND b.check_out < '2022-01-29')
    OR (b.check_in < '2022-01-28' AND b.check_out > '2022-01-29')));
# ==============================================================