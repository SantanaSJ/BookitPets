$(document).ready(function () {
    let counter = 1;
    fetch("http://localhost:8080/all-bookings")
        .then(response => response.json())
        .then(json => {
            if (json.length > 0) {
                json.forEach(b => {
                    console.log(json)
                    let tableRow =
                        '<tr>' +
                        '<td>' + counter + '</td>' +
                        '<td>' + b.firstName + '</td>' +
                        '<td>' + b.lastName + '</td>' +
                        '<td>' + b.hotelName + '</td>' +
                        '<td>' + b.city + '</td>' +
                        '<td>' + b.checkIn + '</td>' +
                        '<td>' + b.checkOut + '</td>' +
                        // '<td>' + b.cancelBefore + '</td>' +
                        '<td>' +
                        '<button class="update-btn" data-booking-id="' + b.bookingId + '">Details</button>' +
                        '<button class="delete-btn" data-booking-id="' + b.bookingId + '">Cancel Booking</button>' +
                        '</td>' +
                        '</tr>'
                    counter = counter + 1;
                    $("#table-container").append(tableRow);
                })
            } else {
                const errorMsg = `
                        <h4>You currently have no bookings!</h4>`;
                $("#table-container").append(errorMsg);
            }

        })

    $('body').on('click', '.update-btn', function () {
        let bookingId = $(this).data('booking-id');
        // $.cookie('bookingId', bookingId);
        window.location.href = 'bookings/details/' + bookingId;

    })

    $('body').on('click', '.delete-btn', function () {
        if (confirm('Are you sure you want to delete the selected booking?')) {
            let bookingId = $(this).data('booking-id');
            console.log("Booking id to delete is " + bookingId);

            fetch('http://localhost:8080/delete/' + bookingId, {
                method: 'DELETE'
            }).then(_ => reloadBookings())
            console.log('deleted');
        } else {
            console.log('Thing was not deleted from the database.');
        }
    });
})


//  function getBookings() {
//     const res =  fetch('http://localhost:8080/all-bookings');
//     const bookings =  res.json();
//     console.log(bookings)
//     console.log()
//     return outputHTML(bookings);
// }
//
// // Show results in html
//
//
// const outputHTML = bookings => {
//     let counter = 1;
//
//     if (bookings.length > 0) {
//         const  html = bookings.map(b =>
//
//                                             '<tr>' +
//                                             '<td>' + counter + '</td>' +
//                                             '<td>' + b.firstName + '</td>' +
//                                             '<td>' + b.lastName + '</td>' +
//                                             '<td>' + b.hotelName + '</td>' +
//                                             '<td>' + b.city + '</td>' +
//                                             '<td>' + b.checkIn + '</td>' +
//                                             '<td>' + b.checkOut + '</td>' +
//                                             // '<td>' + b.cancelBefore + '</td>' +
//                                             '<td>' +
//                                             '<button class="update-btn" data-booking-id="' + b.bookingId + '">Update</button>' +
//                                             '<button class="delete-btn" data-booking-id="' + b.bookingId + '">Cancel Booking</button>' +
//                                             '</td>' +
//                                             '</tr>').join('');
//                                         counter = counter + 1;
//                                         // $("#table-container").append(output);
//         output.innerHTML = html;
//     } else {
//         const errorMsg =  `
//       <h4>You have no bookings!</h4>`;
//         output.innerHTML = errorMsg;
//     }
// }