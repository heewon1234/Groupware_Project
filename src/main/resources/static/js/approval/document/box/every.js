$("#myTable").DataTable({
	lengthChange: false,
	info: false
});
$(document).ready(function() {
	$(".left_item").load("/approval/document/left_item?selectItem=every");
});