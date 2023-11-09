$(document).ready(function() {
	$(".left_item").load("/approval/document/left_item");
});

$(".modalButton_apply").on("click", function() {
	// 모달 폼 보이기/숨기기 토글
	$("#modalContainer_apply").toggleClass("hidden show");

});

$(".modalButton_process").on("click", function() {
	// 모달 폼 보이기/숨기기 토글
	$("#modalContainer_process").toggleClass("hidden show");

});