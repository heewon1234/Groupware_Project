$("#back_btn").on("click", function() {
	window.history.back();
});

$("img").each(function() {
	if ($(this).attr('id') == "check") {
		$("select." + $(this).attr("class")).val("결재").prop("selected", true);
	} else if ($(this).attr('id') == "X") {
		$("select." + $(this).attr("class")).val("반려").prop("selected", true);
	} else {
		$("select." + $(this).attr("class")).val("미결").prop("selected", true);
	}
});

$("select[name='status']").on("change", function() {
	$("td." + $(this).attr('class')).empty();

	if ($(this).val() == '결재') {
		$("td." + $(this).attr('class')).append('<img src="/images/approval/check.svg">');
	} else if ($(this).val() == '반려') {
		$("td." + $(this).attr('class')).append('<img src="/images/approval/X.svg">');
	} else if ($(this).val() == '미결') {
		$("td." + $(this).attr('class')).append('<img src="/images/approval/minus.svg">');
	}
});

$(document).ready(function() {
	$(".top_container").load("/commons/topForm");
	$(".left_item").load("/approval/document/left_item");
});