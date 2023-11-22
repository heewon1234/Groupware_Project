
$(".modalButton").on("click", function() {
	// 모달 폼 보이기/숨기기 토글
	$(".modalContainer").toggleClass("hidden show");
	// 모달 컨테이너 내용 비우기
	$(".modalContainer input").val("");
});

$(document).on("click", ".remove_org_btn", function() {
	let result = confirm('삭제하시겠습니까?');
	if (result) {
		location.href = "/org/delete?organization=" + $(this).prev().html();
	} else { }
});

$(document).ready(function() {
	$(".left_item").load("/insa/manage/left_item?selectItem=org");
	$(".top_container").load("/commons/topForm");
});

function checkFields() {

	if ($("#level").val() != "" && $("#org_input").val() != "" && $("#manager").val() != "상위조직명") {
		$("#submit_btn").removeClass("permit");
	} else {
		$("#submit_btn").addClass("permit");
	}
}

$("#level, #org_input").on("keyup", function() {
	checkFields();
});

$("#manager").on("change", function() {
	checkFields();
});

// 동일 조직명 존재시 경고창 출력
$("button[type='submit']").on("click", function() {
	// 조직명 리스트 생성
	let organizationList = [];
	// 조직명 리스트에 값 삽입
	for (let i = 0; i < $(".org_span").length; i++) {
		organizationList.push($(".org_span").eq(i).html());
	}


	if (organizationList.some((org) => org == $("#org_input").val())) {
		alert("동일한 조직명이 존재합니다.");
		return false;
	} else { }
});