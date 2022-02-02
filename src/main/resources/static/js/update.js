// $(document).ready(function () {
//
//     let bookingId = getCookie("bookingId");
//     getBooking(bookingId);
//
// })
//
// function getBooking(id) {
//
//     fetch('http://localhost:8080/view-bookings/update' + id, {
//         method: 'GET'
//     })
//         .then(response => response.json())
//         .then(data => {
//             console.log('Success', data)
//         })
// }
//
// function getCookie(cname) {
//     let name = cname + "=";
//     let decodedCookie = decodeURIComponent(document.cookie);
//     let ca = decodedCookie.split(';');
//     for(let i = 0; i <ca.length; i++) {
//         let c = ca[i];
//         while (c.charAt(0) === ' ') {
//             c = c.substring(1);
//         }
//         if (c.indexOf(name) === 0) {
//             return c.substring(name.length, c.length);
//         }
//     }
//     return "";
// }