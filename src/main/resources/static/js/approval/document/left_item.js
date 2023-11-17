$(document).ready(function() {
	$('.menu_list_item').click(function() {
		location.href = "/approval/document/" + $(this).parent().attr('id') + "/" + $(this).attr('id');
	});
});

$(".main_button").on("click", function() {
	location.href = "/approval/document/write";
});