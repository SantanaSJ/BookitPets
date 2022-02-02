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
      const  html = matches.map(match => `
                                    <div class="hotel py-2 px-2 pb-4 border-bottom">
                                        <div class="row">
                                            <div class="col-lg-3">
                                            <img src= ${match.imageUrl}
                                                    height="100" width="100" alt="" class="hotel-img">
                                            </div>
                                            <div class="col-lg-3">
                                                <div class="d-md-flex align-items-md-center">
                                                    <div class="name">${match.name}</div>
                                                </div>
                                                <div class="rating">Category: ${match.category}</div>
                                                    <div>City: ${match.city}</div>
                                                    <div>Address: ${match.address}</div>
                                                    <div>Postal Code: ${match.postalCode}</div>
                                                    <div>Type: ${match.type}</div>
                                          </div>
                                            <div class="col-lg-3">
                                            Description
                                            </div>
                                        </div>
<!--                                        Book Now button -->
                                        <div class="d-flex justify-content-end mt-1">
                                            <div class="btn btn-primary text-uppercase">Book Now</div>
                                        </div>
                                    </div>`).join('');
      output.innerHTML = html;
    } else {
      const errorMsg =  `
      <h4>No Matches Found!</h4>`;
      output.innerHTML = errorMsg;
    //  TODO to handle it better
    }
  }



// const outputHTML = matches => {
//   if (matches.length > 0) {
//     const  html = matches.map(match => `
//     <div class="card card-body mb-1">
//     <h4>${match.name} (${match.city})</h4>
//     </div>
//     `).join('');
//     output.innerHTML = html;
//   }
// }


search.addEventListener('input', () => searchHotels(search.value))
