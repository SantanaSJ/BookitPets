$('#delete').click(function () {
    if (confirm('Are you sure you want to delete the selected booking?')) {
        let bookingId = $(this).data('id');
        console.log("Booking id to delete is " + bookingId);

        fetch('http://localhost:8080/delete/' + bookingId, {
            method: 'DELETE'
        }).then(() => window.location = 'http://localhost:8080/view-all')
        console.log('deleted');
        // window.location = 'http://localhost:8080/view-all';
    }
})