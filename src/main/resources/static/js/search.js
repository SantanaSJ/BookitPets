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
        let output = '<h2 style="padding-left: 27px"> Search Results </h2>'
        matches.forEach(constructOutput);

        function constructOutput(hotel) {
            console.dir(hotel)
            output += `
                                    <div class="hotel py-4 px-2 pb-4 border-bottom">
                                        <div class="row justify-content-center">
                                            <div class="col-lg-3 px-4">
                                                <img src= ${hotel.imageUrl}
                                                    height="200" width="250" alt="" class="hotel-img rounded">
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="d-md-flex align-items-md-center">
                                                    <div class="fw-bold">${hotel.name}</div>
                                                </div>
                                                <small class="rating text-muted">Category: ${hotel.category}</small>
                                                <div class="row">
                                                    <small class="col fw-light text-muted">${hotel.address}</small>
                                                </div>

                                                <div class="row">
                                                <small class="col fw-light text-muted">${hotel.city}, ${hotel.postalCode}</small>
                                                </div>
                                                 <div class="row">
                                                 <small class="col fw-light text-muted">${hotel.type}</small>
                                                 </div>
                                          </div>
                                           <div class="col-lg-4">
                                           <style>
                                           .col-lg-4 {
                                           margin-right: 14px;
                                           width: 31%;}
                                            </style>
                                              <div class="d-md-flex align-items-md-center">
                                              <div class="fw-bold">Description</div>

                                              </div>
                                              <small class="text-muted">${hotel.description}</small>

                                           </div>
                                            <div class="col-lg-3 px-0" id="map" data-hotel-lat='${hotel.lat}' data-hotel-lng='${hotel.lng}'>
<!--                                            <iframe class="mb-4 mb-lg-0 rounded" src="https://www.google.com/maps/embed/v1/place?key=AIzaSyA89q7WonzhNiDIbOqwi5CbHwpHDBXp0u4&q=InterContinental+Sofia,Sofia+Bulgaria" frameborder="0" style="border:0; width: 90%; height: 200px;" allowfullscreen></iframe>-->
                                            <iframe class="mb-4 mb-lg-0 rounded" src="https://www.google.com/maps/embed/v1/place?key=AIzaSyA89q7WonzhNiDIbOqwi5CbHwpHDBXp0u4&q=Best+Western+Premier+Sofia+Airport+Hotel,Sofia+Bulgaria" frameborder="0" style="border:0; width: 90%; height: 200px;" allowfullscreen></iframe>
                                            </div>
                                        </div>
<!--                                        Book Now button -->
                                        <div class="d-flex justify-content-end mt-4 px-4">
                                            <button class="btn btn-primary booking-btn" data-hotel-id='${hotel.id}'>Book Now</button>
                                        </div>
                                    </div>`;
        }

        document.getElementById('output').innerHTML = output

    } else {
        const msg = `<h4>No Matches Found!</h4>`;
        output.innerHTML = msg;
    }
}

search.addEventListener('input', () => searchHotels(search.value))
