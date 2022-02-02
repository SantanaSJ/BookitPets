// document.getElementById('showAll').addEventListener('click', showAll);
//
// function showAll() {
//     // window.addEventListener('DOMContentLoaded', (event) => {
//
//         fetch('http://localhost:8080/accommodations')
//             .then(response => {
//                 return response.json()
//             })
//             .then((data) => {
//                 console.dir(data) // what does it show?
//
//                 let output = '<h2> Search Results </h2>'
//
//                 data.forEach(function (hotel) {
//                     console.dir(hotel) // what does this show? is there a hotel.id?
//
//                     output += `
//                                     <div class="hotel py-2 px-2 pb-4 border-bottom">
//                                         <div class="row">
//                                             <div class="col-lg-3">
//                                             <img src= ${hotel.imageUrl}
//                                                     height="100" width="100" alt="" class="hotel-img">
//                                             </div>
//                                             <div class="col-lg-3">
//                                                 <div class="d-md-flex align-items-md-center">
//                                                     <div class="name">${hotel.name}</div>
//                                                 </div>
//                                                 <div class="rating">Category: ${hotel.category}</div>
//                                                     <div>City: ${hotel.city}</div>
//                                                     <div>Address: ${hotel.address}</div>
//                                                     <div>Postal Code: ${hotel.postalCode}</div>
//                                                     <div>Type: ${hotel.type}</div>
//                                           </div>
//                                             <div class="col-lg-3">
//                                             Description
//                                             </div>
//                                             <div class="col-lg-3 ">
//                                                 <iframe class="mb-4 mb-lg-0" src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d12097.433213460943!2d-74.0062269!3d40.7101282!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb89d1fe6bc499443!2sDowntown+Conference+Center!5e0!3m2!1smk!2sbg!4v1539943755621" frameborder="0" style="border:0; width: 90%; height: 200px;" allowfullscreen></iframe>
//                                             </div>
//                                         </div>
// <!--                                        Book Now button -->
//                                         <div class="d-flex justify-content-end mt-1">
//                                             <div class="btn btn-primary booking-btn text-uppercase" data-hotel-id='${hotel.id}'>Book Now</div>
//                                         </div>
//                                     </div>`;
//                 });
//
//                 document.getElementById('output').innerHTML = output
//             })
//     }

    //NOT WORKING

// $('body').on('click', '.btn-primary', function () {
//     console.log(this) // are you able to see the element and the dataset attribute hotel-id?
//     // const hotelId = $(this).data('hotel-id')
//     const hotelId = $(this).attr('data-hotel-id')
//     // window.location.href = 'http://localhost:8080/hotels/' + hotelId + '/booking-form'
//
//     console.log("Hotel id is " + hotelId);
//     fetch('http://localhost:8080/hotels/' + hotelId + '/booking-form', {
//          method: 'GET'
//     //
//     // })
//       // .then((response) => {
//       //
//         // if (response.success) {
//         //     window.location.href = response.redirect('http://localhost:8080/hotels/' + hotelId + '/booking-form')
//         // }
//     })
//     console.log("visible")
//
// });
// document.getElementById('bookingBtn').addEventListener('click', booking);

