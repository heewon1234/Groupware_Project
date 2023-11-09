$(".modalButton").on("click", function() {
	$(".modalContainer").toggleClass("hidden show");
	$(".modalContainer input").val("");
});

$(document).on("click", ".remove_org_btn", function() {
	let result = confirm('삭제하시겠습니까?');
	if (result) {
		location.href = "/org/delete?organization="+ $(this).prev().html();
	} else { }
});

$(document).ready(function() {
	$(".left_item").load("/insa/manage/left_item?selectItem=org");
});

$("#level").keyup(function() {
	if($(this).val() != "" && $("#organization").val() != "") {
		$("#submit_btn").removeClass("permit");
	} else {
		$("#submit_btn").addClass("permit");
	}
});

$("#organization").keyup(function() {
	if($(this).val() != "" && $("#level").val() != "") {
		$("#submit_btn").removeClass("permit");
	} else {
		$("#submit_btn").addClass("permit");
	}
});