$(document).ready(function() {
	$(".left_item").load("/insa/manage/left_item?selectItem=workmanage");
});

var myModal = document.getElementById('myModal');

$(document).ready(function() {
	var myModal = document.getElementById('myModal');

	// 폼 제출 이벤트 리스너 추가
	$("#leaveCreationForm").on("click", function(e) {

		$(".idListbox").val("");
		$(".joindayListbox").val("");

		const idbox = [];
		const joindaybox = [];



		$('.joinday').each(function() {
			const inputValue = $(this).val().trim();
			// 정규 표현식을 사용하여 YYYY-MM-DD 형식 확인
			const regex = /^(2000|20[01]\d|202[0-2]|2023)-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;
			if (inputValue !== "" && regex.test(inputValue)) {
				const correspondingId = $(this).closest('tr').find('.id').val();
				idbox.push(correspondingId);
				joindaybox.push(inputValue);
			} else if (inputValue !== "") {
				// 올바른 형식이 아닌 경우에 대한 처리
				alert("날짜는 YYYY-MM-DD 형식이어야 하며 2000-01-01부터 입력 가능합니다. ");
				return;
			}
		});

		// 값이 없는 경우에 대한 처리
		if (joindaybox.length === 0) {
			alert("날짜를 입력하세요.");
			return;
		}
		console.log("idbox:", idbox);
		console.log("joindaybox:", joindaybox);

		$(".idListbox").val(idbox.join(','));
		$(".joindayListbox").val(joindaybox.join(','));

		myModal.style.display = 'none';
		overlay.style.display = 'none';
	});


	// 모달 열기
	document.getElementById('openModalBtn').addEventListener('click', function() {
		myModal.style.display = 'block';
		overlay.style.display = 'block';
	});

	// 모달 닫기
	closeModalBtn = document.getElementById('closeModalBtn').addEventListener('click', function(e) {
		e.preventDefault(); // 폼 제출 동작 방지
		myModal.style.display = 'none';
		overlay.style.display = 'none';
	});

	// 모달 외부 클릭 시 닫기
	document.getElementById('overlay').addEventListener('click', function() {
		myModal.style.display = 'none';
		overlay.style.display = 'none';
	});
});
function updateWorkformTable() {
	$.ajax({
		url: "/works/workmanagelist",
	}).done(function(resp) {
		console.log(resp.length);
		for (let i = 0; i < resp.length; i++) {
			let tr = $("<tr>");
			tr.html("<td>" + (resp[i].name) + "(" + (resp[i].id) + ")</td><td>" + (resp[i].joinday) +
				"</td><td>" + (resp[i].all_leave) + "</td><td>" + (resp[i].leave_regular) +
				"</td><td>" + (resp[i].leave_bonus) + "</td><td>" + (resp[i].leave_other_bonus) +
				"</td><td>" + (resp[i].year_leave) + "</td><td>" + (resp[i].bonus_leave) +
				"</td><td>" + (resp[i].month_leave) + "</td><td>" + (resp[i].family_event_leave) +
				"</td><td>" + (resp[i].remainder_leave) + "</td></tr>")
			$("#workbox").append(tr);
		}
		$(".div").append("총 인원 :" + resp.length);
	});
}

updateWorkformTable()
