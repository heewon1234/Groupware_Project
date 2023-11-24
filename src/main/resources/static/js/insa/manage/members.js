// DataTable 설정
	var table = $("#myTable").DataTable({
		// 테이블 크기 조절 기능 끄기
		lengthChange: false,
		// 테이블 정보 출력 기능 끄기
		info: false,
		"language": {
			"search": "으로 검색:"	
		}
	});
	
	// Search 셀렉트 추가
	$('#myTable_filter').prepend('<select id="select"></select>');
	
	// Search 셀렉트에 항목 자동 추가
	$('#myTable > thead > tr').children().each(function(indexInArray, valueOfElement) {
		$('#select').append('<option>' + valueOfElement.innerHTML + '</option>');
	});
	
	// Search를 셀렉트의 값만 검색하도록 설정
	// 기존 있던 검색 기능을 unbind()하고 재설정
	$('.dataTables_filter input').unbind().bind('keyup', function() {
	
		var colIndex = document.querySelector('#select').selectedIndex;
	
		table.column(colIndex).search(this.value).draw();
	
	});
//

$(document).on("change", "input[type='checkbox']", function() {
	// 체크박스 이벤트 발생하면 체크된 인원 수 반영
	$("#checked_count").html($(".chk_box:checked").length);

	// 체크가 하나라도 되어있다면 수정 버튼 보이기
	if ($("input[type='checkbox']:checked").length > 0) {
		$(".modalButton").removeClass("invisible");
	}
	// 그렇지 않다면 수정 버튼 숨기기
	else {
		$(".modalButton").addClass("invisible");
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
	$(".top_container").load("/commons/topForm");
});


// 회원가입 div 출력
let signup = false;

$("#add_btn").on("click", function() {
	if (signup == false) {
		$("#createUser").css("display", "flex");
		signup = true;
	} else {
		$("#createUser").css("display", "none");
		signup = false;
	}
});



// 회원가입 regex
let idRegex = /^[\w\d]{7,13}$/;
let pwRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
let nameRegex = /^[가-힣]{2,5}$/;

let idCheck = false;
let pwCheck = false;
let nameCheck = false;

var duplCheck = false;

var workFormSelect = document.getElementsByName("workForm")[0];
var orgSelect = document.getElementsByName("org")[0];
var positionSelect = document.getElementsByName("position")[0];
var jobNameSelect = document.getElementsByName("jobName")[0];

$("#id").on("keyup",function() {
	$.ajax({
		url: "/members/idCheck",
		data: {
			id: $("#id").val()
		}
	}).done(function(resp) {
		if (resp == "true") {
			duplCheck = false;
			return;
		} else {
			duplCheck = true;
		}
	});
});

$("#signupForm").on("submit", function() {
	if (!idRegex.test($("#id").val())) {
		idCheck = false;
	} else {
		idCheck = true;
	}
	
	if (!pwRegex.test($("#pw").val())) {
		pwCheck = false;
	} else {
		pwCheck = true;
	}
	
	if (!nameRegex.test($("#name").val())) {
		nameCheck = false;
	} else {
		$("#nameCheck").html("");
		nameCheck = true;
	}
	
	if (idCheck == false) {
		alert("아이디는 7~13자의 소문자와 숫자로 이루어져야 합니다.");
		return false;
	}
	if (duplCheck == false) {
		alert("이미 사용중인 아이디 입니다.");
		return false;
	}
	if (pwCheck == false) {
		alert("비밀번호는 영문자, 숫자, 특수문자 포함 8글자 이상 15글자 이하여야 합니다.");
		return false;
	}
	if (nameCheck == false) {
		alert("이름은 2~5글자의 한글이여야 합니다.");
		return false;
	}
	if (
	    workFormSelect.selectedIndex === 0 ||
	    orgSelect.selectedIndex === 0 ||
	    positionSelect.selectedIndex === 0 ||
	    jobNameSelect.selectedIndex === 0
	  ) {
	    alert("선택하지 않은 필수 항목이 있습니다.");
	    return false;
	  }
	
	let signup = confirm("회원가입 하시겠습니까?");
	if (!signup) {
		return false;
	} else {
		alert("회원가입이 완료되었습니다.");
		return true;
	}
});