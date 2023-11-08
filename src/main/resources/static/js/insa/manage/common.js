$(document).ready(function() {
	$('.menu_item, .menu_list_item').click(function() {
		location.href = "/insa/manage/" + $(this).attr('id');
	});
});