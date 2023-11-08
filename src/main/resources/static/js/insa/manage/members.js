$("#myTable").DataTable({
	lengthChange: false,
	info: false
});

$("input[type='checkbox']").on("change", function() {
	// 체크박스 이벤트 발생하면 체크된 인원 수 반영
	$("#checked_count").html($(".chk_box:checked").length);

	// 체크가 하나라도 되어있다면 수정 버튼 보이기
	if ($("input[type='checkbox']:checked").length > 0) {
		$(".modalButton").removeClass("invisbale");
	}
	// 그렇지 않다면 수정 버튼 숨기기
	else {
		$(".modalButton").addClass("invisbale");
	}
});

// 수정 버튼 클릭시 공통 이벤트
$(".modalButton").on("click", function() {
	// 체크된 아이디 form 안쪽에 반영
	$(".updateID").val("");

	// 체크된 아이디를 ','로 구분하여 하나의 input[type='text']에 반영
	// service 에리어에서 split
	const checkedValues = [];
	$("input[type='checkbox']:checked").not("#all_check").each(function() {
		checkedValues.push($(this).val());
	});

	$(".updateID").val(checkedValues);
});

// 근로형태 수정 버튼 클릭
// 근로형태 모달창 닫기 버튼 클릭
$(".modalButton_workForm").on("click", function() {
	// 모달 폼 보이기/숨기기 토글
	$("#modalContainer_workForm").toggleClass("hidden show");

});

// 소속조직 수정 버튼 클릭
// 소속조직 모달창 닫기 버튼 클릭
$(".modalButton_org").on("click", function() {
	// 모달 폼 보이기/숨기기 토글
	$("#modalContainer_org").toggleClass("hidden show");

});

// 직위 수정 버튼 클릭
// 직위 모달창 닫기 버튼 클릭
$(".modalButton_position").on("click", function() {
	// 모달 폼 보이기/숨기기 토글
	$("#modalContainer_position").toggleClass("hidden show");

});

// 삭제 버튼 클릭
// 삭제 모달창 취소 버튼 클릭
$(".modalButton_delete").on("click", function() {
	// 모달 폼 보이기/숨기기 토글
	$("#modalContainer_delete").toggleClass("hidden show");
});

// 모두 체크
$("#all_check").on("click", function() {
	if ($("#all_check:checked").length > 0) {
		$('input:checkbox').prop('checked', true);
	} else {
		$('input:checkbox').prop('checked', false);
	}
});

$(document).ready(function() {
	$(".left_item").load("/insa/manage/left_item?selectItem=members");
});