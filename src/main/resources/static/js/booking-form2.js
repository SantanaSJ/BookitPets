// //Clone the hidden element and shows it
// let formData;
//
// $('.add-one').click(function () {
//     $('.dynamic-element').first().clone().appendTo('.dynamic-stuff').show();
//     formData = $('form').serializeArray();
//     let roomType = {};
//     roomType["name"] = $('#roomType');
//     roomType["value"] = $('#roomType').val();
//     let numberOfRooms = {};
//     numberOfRooms["name"] = $('#numberOfRooms');
//     numberOfRooms["value"] = $('#numberOfRooms').val();
//     formData.push(roomType, numberOfRooms);
//     attach_delete();
// });
//
// //Attach functionality to delete buttons
// function attach_delete() {
//     $('.delete').off();
//     $('.delete').click(function () {
//         console.log("click");
//         $(this).closest('.form-group').remove();
//     });
// }
//
// // let formData = $('form').serializeArray();
// // const numberOfPeople = $('#numberOfPeople').val();
// // const checkIn = $('#checkIn').val();
// // const checkOut = $('#checkOut').val();
//
//
// ('#checkAvailability').onclick(() => {
//     checkAvailability()
// });
//
// function checkAvailability() {
//     // Example POST method implementation:
//     async function postData(url = 'http://localhost:8080/check-availability', data = {formData}) {
//         // Default options are marked with *
//         const response = await fetch(url, {
//             method: 'GET', // *GET, POST, PUT, DELETE, etc.
//             mode: 'cors', // no-cors, *cors, same-origin
//             cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
//             credentials: 'same-origin', // include, *same-origin, omit
//             // headers: {
//             //     'Content-Type': 'application/json'
//             //     // 'Content-Type': 'application/x-www-form-urlencoded',
//             // },
//             redirect: 'follow', // manual, *follow, error
//             referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
//             body: JSON.stringify(data) // body data type must match "Content-Type" header
//         });
//         return response.json(); // parses JSON response into native JavaScript objects
//     }
// }
//
//
// // postData('https://example.com/answer', { answer: 42 })
// //     .then(data => {
// //         console.log(data); // JSON data parsed by `data.json()` call
// //     });
//
// // function checkAvailability() {
// // fetch("http://localhost:8080/check-availability")
// //     .then(response => response.json())
// //     .then(json => json)
// // }