$(document).ready(function() {
	$(".left_item").load("/members/left_item");
	$(".top_container").load("/commons/topForm");
});

// Kakao Post API
function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('postcode').value = data.zonecode;
			document.getElementById("address").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("detailAddress").focus();
		}
	}).open();
}

let pwRegex = /^([\w\d\W]){8,}$/;

let pwCheck = false;
let pwReCheck = false;
let phoneCheck = false;

$(".button_apply").on("click", function() {

	if ($("pw").val() != "") {
		if (!pwRegex.test($("#pw").val()) && $("#pw").val() != "") {
			pwCheck = false;
		} else {
			pwCheck = true;
		}

		if ($("#pw").val() != $("#repw").val()) {
			pwReCheck = false;
		} else {
			pwReCheck = true;
		}
	}

	if (pwCheck == false) {
		alert("비밀번호는 대문자와 특수문자 포함 8글자 이상이여야 합니다.");
		return false;
	}
	if (pwReCheck == false) {
		alert("비밀번호 재입력이 일치하지 않습니다.");
		return false;
	}

	let signup = confirm("내 정보를 수정하시겠습니까?");
	if (!signup) {
		return false;
	} else {
		alert("수정이 완료되었습니다.");
		return true;
	}
});

$("#input-file").on("change", function(e) {
	$("#profileImage-div").empty();
	$("#profile_image").val("");

	let formData = new FormData(); // 자바스크립트로 form태그를 만듬
	for (let i = 0; i < e.target.files.length; i++) {
		formData.append("files", e.target.files[i]); // 각 이미지를 FormData에 추가
	}
	$.ajax({
		url: "/file/imageUpload",
		method: "post",
		data: formData,
		processData: false, // 파일 바이너리로 그래로 가야해 인코딩도 하지마
		contentType: false, // 데이터 타입 건드리지마
	}).done(function(resp) {
		for (let i = 0; i < e.target.files.length; i++) {
			var imgTag = "<img src='" + resp[i] + "'>";
			$('#profileImage-div').append(imgTag);
			$("#profile_image").val(resp[i]);
		}
	});
});

$("#back_btn").on("click", function() {
	location.href="/";
});