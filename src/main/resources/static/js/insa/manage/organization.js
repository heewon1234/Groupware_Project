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
	$(".top_container").load("/commons/topForm");
});

function checkFields() {

  if ($("#level").val() != "" && $("#organization").val() != "" && $("#manager").val() != "상위조직명") {
    $("#submit_btn").removeClass("permit");
  } else {
    $("#submit_btn").addClass("permit");
  }
}

$("#level, #organization").on("keyup", function() {
  checkFields();
});

$("#manager").on("change", function() {
  checkFields();
});