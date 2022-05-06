const search = document.getElementById('Search_Property');
const output = document.getElementById('output');

//search hotels.json and filter it
const searchHotels = async searchText => {
  const res = await fetch('hotelNamesAutocomplete');
  const hotels = await res.json();
  // console.log(hotels);

//  Get matches to current text input
//redundant
  let matches = hotels.filter(hotel => {
      const regex = new RegExp(`^${searchText}`, 'gi');
      return hotel.name.match(regex);
  });

  if (searchText.length === 0) {
    matches = [];
    output.innerHTML = '';
  }
  // console.log(matches);
  outputHTML(matches);

};
// Show results in html


  const outputHTML = matches => {
    if (matches.length > 0) {
      let output = '<h2> Search Results </h2>'
      matches.forEach(constructOutput);

      function constructOutput(hotel) {
        console.dir(hotel)

        output += `
                                    <div class="hotel py-4 px-2 pb-4 border-bottom">
                                        <div class="row">
                                            <div class="col-lg-3 px-4">
                                            <img src= ${hotel.imageUrl}
                                                    height="200" width="250" alt="" class="hotel-img rounded">
                                            </div>
                                            <div class="col-lg-3">
                                                <div class="d-md-flex align-items-md-center">
                                                    <div class="fw-bold">${hotel.name}</div>
                                                </div>
                                                <div class="rating">Category: ${hotel.category}</div>
                                                <div class="row">

                                <div class="col fw-light fs-6">${hotel.address}, ${hotel.postalCode}</div>
                            </div>

                                                     <div class="row">
                             <div class="col fw-light fs-6">${hotel.city}</div>
                            </div>
                            <div class="row">
                                <div class="col fw-light fs-6">${hotel.type}</div>
                            </div>
                                          </div>
                                            <div class="col-lg-3">
                                            Description
                                            </div>
                                            <div class="col-lg-3 px-0">
                                                <iframe class="mb-4 mb-lg-0 rounded" src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d12097.433213460943!2d-74.0062269!3d40.7101282!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb89d1fe6bc499443!2sDowntown+Conference+Center!5e0!3m2!1smk!2sbg!4v1539943755621" frameborder="0" style="border:0; width: 90%; height: 200px;" allowfullscreen></iframe>
                                            </div>
                                        </div>
<!--                                        Book Now button -->
                                        <div class="d-flex justify-content-end mt-4 px-4">
                                            <button class="btn btn-primary booking-btn" data-hotel-id='${hotel.id}'>Book Now</button>
                                        </div>
                                    </div>`;
      }

      document.getElementById('output').innerHTML = output

    }

      //     matches.map(match =>
      //     `<div class="hotel py-4 px-2 pb-4 border-bottom">
      //   <div class="row">
      //     <div class="col-lg-3 px-4">
      //       <img src= ${match.imageUrl}
      //            height="200" width="250" alt="" class="hotel-img rounded">
      //     </div>
      //     <div class="col-lg-3">
      //       <div class="d-md-flex align-items-md-center">
      //         <div class="fw-bold">${match.name}</div>
      //       </div>
      //       <div class="rating">Category: ${match.category}</div>
      //       <div class="row">
      //
      //         <div class="col fw-light fs-6">${match.address}, ${match.postalCode}</div>
      //       </div>
      //
      //       <div class="row">
      //         <div class="col fw-light fs-6">${match.city}</div>
      //       </div>
      //       <div class="row">
      //         <div class="col fw-light fs-6">${match.type}</div>
      //       </div>
      //     </div>
      //     <div class="col-lg-3">
      //       Description
      //     </div>
      //     <div class="col-lg-3 px-0">
      //       <iframe class="mb-4 mb-lg-0 rounded" src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d12097.433213460943!2d-74.0062269!3d40.7101282!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb89d1fe6bc499443!2sDowntown+Conference+Center!5e0!3m2!1smk!2sbg!4v1539943755621" frameborder="0" style="border:0; width: 90%; height: 200px;" allowfullscreen></iframe>
      //     </div>
      //   </div>
      //   <!--                                        Book Now button -->
      //   <div class="d-flex justify-content-end mt-4 px-4">
      //     <button class="btn btn-primary booking-btn" data-hotel-id='${match.id}'>Book Now</button>
      //   </div>
      // </div>`).join('');
      // output.innerHTML = html;
     else {
      const msg =  `
      <h4>No Matches Found!</h4>`;
      output.innerHTML = msg;
    }
  }

search.addEventListener('input', () => searchHotels(search.value))
