$(document).ready(function () {

    // Invoke the corresponding URL to update the dynamic fields section using Ajax
    $('.dynamic-update-rows').on('click', 'button[data-dynamic-update-rows-url]', function () {
        //event event.preventDefault();
        console.log(this);
        let url = $(this).data('dynamic-update-rows-url');

        // adding the row index, needed when deleting a dynamic row
        let formData = $('form').serializeArray();
        let param = {};
        param["name"] = $(this).attr('name');
        param["value"] = $(this).val();
        formData.push(param);

        // updating the dynamic section
        $('#dynamicTableContents').load(url, formData);
    });
});






// const api_url = "http://localhost:8080";
//
// const id = $.cookie('id');
// //if no cookie is returned
// getSearchResults(id);
//
// function getSearchResults(id) {
//     const uri = "/hotels/" + id;
//     fetch(api_url + uri)
//         .then(response => {
//             return response.json()
//         })
//         .then((data) => {
//             console.dir(data)
//         })
// }

// <script th:inline="javascript">
//     /*<![CDATA[*/
//     $(document).ready(function () {
//     $('#typeNumber').val();
//     // $('#typeNumber').change()
// })
//
//     $("#typeNumber").change(function () {
//     const countRoom = $(this).val();
//     // $(".numberOfRooms").empty()
//     for (let i = 1; i <= countRoom; i++) {
//     $(".numberOfRooms").css("width", "100%")
//     .append(' <div class="col"><label class="label-align"><span class="required"></span></label>Room' + i + '<select id="roomTypeId" name="roomType" class="form-control"><optgroup label="Select a room type"><option selected>Choose an option</option><option /*[# th:each="r : ${T(com.example.onlinehotelbookingsystem.model.entity.enums.RoomTypeEnum).values()}" th:value="${r}" th:text="${r}"]*/>Single</option></option></optgroup></select></div>')
// }
// });
//
//     /*]]>*/
// </script>

//<option value="' + rooms[j].type + '">' + rooms[j].type + '</option>
//const rooms = [[${roomViewModels}]];


//<div class="numberOfRooms">
//
//</div>
// <script th:inline="javascript">
//     /*<![CDATA[*/
//     $(document).ready(function () {
//     $('#typeNumber').val();
//     // $('#typeNumber').change()
// })
//
//     $("#typeNumber").change(function () {
//     const countRoom = $(this).val();
//     const rooms = [[${roomViewModels}]];
//
//     const array = [];
//     for (let i = 0; i < rooms.length; i++) {
//     const el = rooms[i];
//     const type = el.type;
//     array.push(type);
// }
//     $(".numberOfRooms").empty()
//     for (let i = 1; i <= countRoom; i++) {
//     $(".numberOfRooms").css("width", "100%")
//     .append('<div class="col">' +
//     '<label class="label-align"><span class="required"></span></label>Room' + i + '' +
//     '<select id="roomTypeId" name="roomType" class="form-control">' +
//     '<optgroup label="Select a room type">' +
//     '<option selected>Choose an option<option value="t">t</option></optgroup></select></div>')
//
//     // $(".numberOfRooms").css("width", "100%")
//     //     .append(`<div class="col"><label class="label-align"><span class="required"></span></label>Room` + i + `<select id="roomTypeId" name="roomType" class="form-control"><optgroup label="Select a room type"><option selected>Choose an option</option><option value="">``</option></option></optgroup></select></div>`)
//
// }})
//
//     /*]]>*/
// </script>