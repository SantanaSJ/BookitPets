$('#delete').click(function () {
    if (confirm('Are you sure you want to delete the selected booking?')) {
        let bookingId = $(this).data('id');
        console.log("Booking id to delete is " + bookingId);
        //https://bookitpets.herokuapp.com/delete/
        //http://localhost:8080/delete/
        fetch('https://bookitpets.herokuapp.com/delete/' + bookingId, {
            method: 'DELETE'
        //    https://bookitpets.herokuapp.com/view-all
        //    http://localhost:8080/view-all
        }).then(() => window.location = 'https://bookitpets.herokuapp.com/view-all')
        console.log('deleted');
        // window.location = 'http://localhost:8080/view-all';
    }
})