$(document).ready(function() {
	$('.menu_item, .manage').click(function() {
		location.href = "/insa/manage/" + $(this).attr('id');
	});
});
$(document).ready(function() {
	$('.menu_item, .mywork,.work_manage').click(function() {
		location.href = "/works/" + $(this).attr('id');
	});
});