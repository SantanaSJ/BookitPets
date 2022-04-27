window.onload = (function () {
     loadBookings();

})

function loadBookings() {

    let counter = 1;
    fetch("http://localhost:8080/all-bookings")
        .then(response => response.json())
        .then(json => {
            if (json.length > 0) {
                json.forEach(b => {
                    console.log(json)
                    let ci = new Date(b.checkIn).toLocaleDateString();
                    let co = new Date(b.checkOut).toLocaleDateString();
                    let tableRow =
                        '<tr>' +
                        '<td>' + counter + '</td>' +
                        '<td>' + b.firstName + '</td>' +
                        '<td>' + b.lastName + '</td>' +
                        '<td>' + b.hotelName + '</td>' +
                        '<td>' + b.city + '</td>' +
                        '<td>' + ci + '</td>' +
                        '<td>' + co + '</td>' +
                        // '<td>' + b.cancelBefore + '</td>' +
                        '<td>' +
                        '<button class="btn btn-primary update-btn m-2" data-booking-id="' + b.bookingId + '">Details</button>' +
                        '<button class="btn btn-danger btn-sm delete-btn" data-booking-id="' + b.bookingId + '">Cancel Booking</button>' +
                        '</td>' +
                        '</tr>'
                    counter = counter + 1;
                    $("#table-container").append(tableRow);
                })
            } else {
                const errorMsg = `
                        <h5>You currently have no bookings!</h5>`;
                $("#table-container").append(errorMsg);
            }

        })
}

    $('body').on('click', '.update-btn', function () {
        let bookingId = $(this).data('booking-id');
        // $.cookie('bookingId', bookingId);
        window.location.href = '/bookings/details/' + bookingId;

    })

    $('body').on('click', '.delete-btn', function () {
        if (confirm('Are you sure you want to delete the selected booking?')) {
            let bookingId = $(this).data('booking-id');
            console.log("Booking id to delete is " + bookingId);

            fetch('http://localhost:8080/delete/' + bookingId, {
                method: 'DELETE'
            }).then(_ => loadBookings())
            location.reload();
            console.log('deleted');
        } else {
            console.log('Thing was not deleted from the database.');
        }
    });



