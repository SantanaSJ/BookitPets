//WORKING

// $('#loadBookings').click(() => {
//     reloadBookings()
// });
//
// function reloadBookings() {
//     $("#table-container").empty();
//
//     let counter = 1;
//     fetch("http://localhost:8080/all-bookings")
//         .then(response => response.json())
//         .then(json => json.forEach(b => {
//             console.log(json)
//             let tableRow =
//                 '<tr>' +
//                 '<td>' + counter + '</td>' +
//                 '<td>' + b.firstName + '</td>' +
//                 '<td>' + b.lastName + '</td>' +
//                 '<td>' + b.hotelName + '</td>' +
//                 '<td>' + b.city + '</td>' +
//                 '<td>' + b.checkIn + '</td>' +
//                 '<td>' + b.checkOut + '</td>' +
//                 // '<td>' + b.cancelBefore + '</td>' +
//                 '<td>' +
//                 '<button class="update-btn" data-booking-id="' + b.bookingId + '">Details</button>' +
//                 '<button class="delete-btn" data-booking-id="' + b.bookingId + '">Cancel Booking</button>' +
//                 '</td>' +
//                 '</tr>'
//             counter = counter + 1;
//             $("#table-container").append(tableRow);
//         }))
// }
//
//
// $('body').on('click', '.update-btn', function () {
//     let bookingId = $(this).data('booking-id');
//     // $.cookie('bookingId', bookingId);
//     window.location.href = 'bookings/details/' + bookingId;
//
// })
//
// $('body').on('click', '.delete-btn', function () {
//     if (confirm('Are you sure you want to delete the selected booking?')) {
//         let bookingId = $(this).data('booking-id');
//         console.log("Booking id to delete is " + bookingId);
//
//         fetch('http://localhost:8080/delete/' + bookingId, {
//             method: 'DELETE'
//         }).then(_ => reloadBookings())
//         console.log('deleted');
//     } else {
//         console.log('Thing was not deleted from the database.');
//     }
// });
//
//
// // let ciStrings = JSON.stringify(ci).split('T'[0]);
// // let coStrings = JSON.stringify(co).split('T'[0]);
//
// //booking id?
// $(document).ready(function () {
//     fetch("http://localhost:8080/last-booking/")
//         .then(response => response.json())
//         .then((data) => {
//             $('#hotelName').append(data.hotelName);
//             $('.hotel-img').attr("src", data.hotelImage);
//             $('#cat').append(data.category);
//             $('#city').append(data.city);
//             $('#address').append(data.address);
//             $('#type').append(data.type);
//             $('#firstName').append(data.firstName);
//             $('#lastName').append(data.lastName);
//
//             let ci = new Date(data.checkIn).toLocaleDateString();
//             $('#checkIn').append(ci);
//             let co = new Date(data.checkOut).toLocaleDateString();
//             console.log(ci);
//             console.log(co);
//             $('#checkOut').append(co);
//             $('#totalNights').append(data.totalNights);
//             $('#totalPrice').append(data.totalPrice);
//             $('#numberOfPeople').append(data.numberOfPeople);
//             $.each(data.roomTypesCostPerNight, function (key, value) {
//                 $("#roomType").append(`<div class="border-bottom border-top">` + key + `</div>`)
//                 $("#price").append(`<div class="border-bottom border-top">` + value + `</div>`)
//             })
//
//             $('#pet').append(data.petName);
//             $.cookie('lastBookingId', data.bookingId);
//         })
// })
//
//
//
//
//
