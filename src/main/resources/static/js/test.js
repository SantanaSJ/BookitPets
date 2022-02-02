$("#showAll").on("click", showAll);

async function showAll() {
    console.log("showAll");
    let json = await getHotelInfo();

    console.log("buildButtons");
    console.log(json);

    let output = '<h2> Search Results </h2>';
    json.forEach(hotel=> {
        console.log(hotel.author);
        output += "<!-- Book Now button -->";
        output += '<div class="d-flex justify-content-end mt-1">';
        output +=
            '<div class="btn btn-primary bookingBtn" data-hotel-id="${hotel.id}">BOOK NOW ${hotel.id}</div>';
        output += "</div>";
        output += "</div>";
    });
    document.getElementById("output").innerHTML = output;
    $(".bookingBtn").on("click", clickBookingButton);
}

async function getHotelInfo() {
    console.log("getHotelInfo");
    let response = await fetch("https://picsum.photos/v2/list");
    return await response.json();
}

$("body").on("click", "bookingBtn", clickBookingButton);

function clickBookingButton() {
    let hotelId = $(this).data("hotel-id");
    console.log("Hotel id is " + hotelId);
    fetch("http://localhost:8080/hotels/" + hotelId + "/booking-form", {
        method: "POST"
    });
}