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
$(document).ready(function() {
	$('.menu_item, .work_plan').click(function() {
		location.href = "/works_plan/" + $(this).attr('id');
	});
});
$(document).ready(function() {
	$('.main_button').click(function() {
		location.href = "/works/leave_apply";
	});
});