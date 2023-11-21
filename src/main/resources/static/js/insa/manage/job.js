// 직무 입력칸을 하나만 생성할 수 있게 하기 위한 변수
let jr_count = 0;
// 직위 입력칸을 하나만 생성할 수 있게 하기 위한 변수
let jt_count = 0;

// 직무 입력칸과 그 입력칸을 삭제할 수 있는 버튼 생성
$("#add_jr_btn").on("click", function() {
	if (jr_count == 0) {
		let div = $("<div>");
		div.css("display", "inline-block");

		let input = $("<input type='text' >");
		input.attr("id", "job_name_input");
		input.attr("name", "job_name");

		let del_btn = $("<button>");
		del_btn.attr("id", "del_jr_btn");
		del_btn.attr("href", "#");
		del_btn.html("X");

		div.append(input);
		div.append(del_btn);

		$("#jr_contents").append(div);
		jr_count = 1;
	}
});

// 직위 입력칸과 그 입력칸을 삭제할 수 있는 버튼 생성
$(".fa-circle-plus").on("click", function() {
	if (jt_count == 0) {
		let div = $("<div>");
		div.css("display", "inline-block");

		let input_position = $("<input type='text' >");
		input_position.attr("id", "job_position_input");
		input_position.attr("name", "position");

		let input_job_rank = $("<input type='text' hidden>");
		input_job_rank.attr("name", "job_rank");
		let job_rank = $(this).parent().prev().prev().attr("id");
		input_job_rank.val(job_rank);

		let del_btn = $("<button>");
		del_btn.attr("id", "del_jt_btn");
		del_btn.attr("href", "#");
		del_btn.html("X");

		div.append(input_job_rank);
		div.append(input_position);
		div.append(del_btn);

		$(this).parent().prev().append(div);

		jt_count = 1;
	}
});

// 생성된 직무 입력칸 삭제
$(document).on("click", "#del_jr_btn", function() {
	$(this).parent().remove();
	jr_count = 0;
});

// 생성된 직위 입력칸 삭제
$(document).on("click", "#del_jt_btn", function() {
	$(this).parent().remove();
	jt_count = 0;
});

// 직무 리스트에서 직무 삭제
$(".remove_jr_btn").on("click", function() {
	let result = confirm('삭제하시겠습니까?');
	if (result) {
		location.href = "/job/deleteJobRole?job_name="
			+ $(this).prev().html();
	} else {
	}
});

// 직위 리스트에서 직위 삭제
$(".remove_jt_btn").on("click", function() {
	let result = confirm('삭제하시겠습니까?');
	if (result) {
		location.href = "/job/deleteJobTitle?position="
			+ $(this).prev().html();
	} else {
	}
});

// 텍스트 클릭 시 'X' 버튼 보이기/숨기기
$(".parent_span").on("click", "span", function() {
	$(this).next().toggleClass("hidden show");
});

$(document).ready(function() {
	$(".left_item").load("/insa/manage/left_item?selectItem=job");
	$(".top_container").load("/commons/topForm");
});


// 직무명&직위명 리스트 생성
let jobPositionList = [];
let jobNameList = [];

for (let i = 0; i < $(".jobPosition_span").length; i++) {
	jobPositionList.push($(".jobPosition_span").eq(i).html());
}

for (let i = 0; i < $(".jobName_span").length; i++) {
	jobNameList.push($(".jobName_span").eq(i).html());
}
//

// 중복되는 직위명을 입력시 경고창
$("#apply_jobTitle").on("click", function() {
	if (jobPositionList.some((job) => job == $("#job_position_input").val())) {
		alert("동일한 직위명이 존재합니다.");
		return false;
	} else { }
});

// 중복되는 직무명 입력시 경고창
$("#apply_jobRole").on("click", function() {
	if (jobNameList.some((job) => job == $("#job_name_input").val())) {
		alert("동일한 직무명이 존재합니다.");
		return false;
	} else { }
});