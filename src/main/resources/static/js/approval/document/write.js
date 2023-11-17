$("input[type='checkbox']").on("click", function() {
	let count = $("input[type='checkbox']").length;

	if (count > 4) {
		$(this).prop("checked", false);
	}
});

$(".modalButton_apply").on("click", function() {
	// 모달 폼 보이기/숨기기 토글
	$("#modalContainer_apply").toggleClass("hidden show");
});

$("#apply_btn").on("click", function() {
	// 모달 폼 보이기/숨기기 토글
	$("#modalContainer_apply").toggleClass("hidden show");
	
	$(".app").empty();

	$("#modalContainer_apply input[type='checkbox']:checked").each(function(index) {
		let input = $("<input type='text' hidden>");
		input.attr("name", "managerID");
		input.val($(this).prev().html());
		
		$("#app_th" + (index + 1)).html($(this).prev().prev().html());
		$("#app_th" + (index + 1)).append(input);
		$("#app_td" + (index + 1)).html($(this).prev().prev().prev().html());
	});
});

$("#title").keyup(function() {
	if($(this).val() != "" && $("#contents").val() != "") {
		$("#submit_btn").removeClass("permit");
	} else {
		$("#submit_btn").addClass("permit");
	}
});

$("#contents").keyup(function() {
	if($(this).val() != "" && $("#title").val() != "") {
		$("#submit_btn").removeClass("permit");
	} else {
		$("#submit_btn").addClass("permit");
	}
});