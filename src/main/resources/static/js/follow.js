// (function($) {
//     const element = $('.follow-scroll'),
//         originalY = element.offset().top;
//
//     // Space between element and top of screen (when scrolling)
//     const topMargin = 20;
//
//     // Should probably be set in CSS; but here just for emphasis
//     element.css('position', 'relative');
//
//     $(window).on('scroll', function(event) {
//         var scrollTop = $(window).scrollTop();
//
//         element.stop(false, false).animate({
//             top: scrollTop < originalY
//                 ? 0
//                 : scrollTop - originalY + topMargin
//         }, 300);
//     });
// })(jQuery);