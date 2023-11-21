let tag_list_open = false;
let selectedTags = [];
let editToggle = false;
let contactType = "personal";

let persion_action = "/contact/personalContactInsert";
let persion_action_ajax = "/contact/personalContactInsertList";
let persion_action_update = "/contact/personalContactUpdate";
let persion_action_delete = "/contact/personalContactDelete";

let share_action = "/contact/shareContactInsert";
let share_action_ajax = "/contact/shareContactInsertList";
let share_action_update = "/contact/shareContactUpdate";
let share_action_delete = "/contact/shareContactDelete";

let read_seq;
let select_category_seq;

let plus_open = false;
let plus_y = null;
let plus_x = null;
let plus_y_now = null;
let plus_x_now = null;
let plus_parent;
let plus_parent_name;
let plus_index;
let plus_tag_type;
let plus_tag_name;

// TOP 불러오기
$(document).ready(function() {
	$("#top_container").load("/commons/topForm");
});

// 선택한 요소에 Select 클래스 붙이기 // 온로드
$(document).ready(function() {
	$("#select_menu_list .menu_list_item_text").each(function() {
		// 각 요소의 내용을 가져옵니다. trim()을 추가하여 앞뒤 공백을 제거합니다.
		var itemContent = $(this).html().trim();

		if ($("#select_contact_tag").html() == "개인 주소록" || $("#select_contact_tag").html() == "공유 주소록") {
			if (itemContent == "전체") {
				$(this).closest(".menu_list_item").addClass("select");
			}
		} else {
			if (itemContent == $("#select_contact_tag").html()) {
				$(this).closest(".menu_list_item").addClass("select");
			}
		}
	});
});


// 모달 창 OPEN
function modal_open_button(type) {
	// 메인 작성하기 버튼 눌러서 들어오는 모달
	if (type == "main_modal") {
		$('.modal_form').css('display', 'block');
		$('#main_modal').css('display', 'block');
		contactType = "personal";
	}

}

// 모달 창 CANCEL
$(document).on("click", "#modal_cancel_button", function(event) {
	// onclick="modal_cancel_button('main_modal',event)"
	event.preventDefault();
	$('.modal_form').css('display', 'none');
	$('#main_modal').css('display', 'none');
	$(".modal_tag_list").html('');
	selectedTags = [];
	tag_list_open = false;
});

function modal_cancel_button(type, event) {
	// 주소록 읽기 눌러서 들어오는 모달 닫기
	if (type == "read_modal") {
		$('.modal_form').css('display', 'none');
		$('.modal_contact_read').css('display', 'none');
		event.preventDefault();
	}

	// 주소록 수정 눌러서 들어오는 모달 닫기
	else if (type == "contact_update_modal") {
		$('.modal_form').css('display', 'none');
		$('#modal_contact_add_edit').css('display', 'none');
		$(".modal_tag_list").html('');
		selectedTags = [];
		event.preventDefault();
	}

	else if (type == "direct_tag_modal") {
		$("#modal_new_tag_input_direct").val("");
		$(".modal_form").css('display', 'none');
		$('#modal_tag_add_direct').css('display', 'none');
		$('#button_apply_tag_direct').addClass('permit');
		$('.modal_tag_duplication_error').css('display', 'none');
		$('.modal_tag_regex_error').css('display', 'none');
		event.preventDefault();
	}
}


// 모달 창 READ
function modal_contact_read_add_data(element, type) {

	select_category_seq = $(element).find('.main_category_seq').text();

	$(".modal_form").css('display', 'block');
	$(".modal_contact_read").css('display', 'block');

	if (type == "personal") {
		$.ajax({
			url: "/contact/personalContactRead?seq=" + select_category_seq,
			type: "GET",
			dataType: "json"
		}).done(function(resp) {
			for (let i = 0; i < resp.length; i++) {
				let tag_list = resp[i].tag.split(',');
				read_seq = resp[i].seq;

				$("#read_title").html('');
				$("#read_content_form").html('');

				if (resp[i].name) {
					$("#read_title").
						append(resp[i].name);
				}
				if (resp[i].seq) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read' style='display:none;'><div class='modal_body_content_input_left read'>" +
							"SEQ</div><div class='modal_body_content_input_right read' id='read_seq'>" + resp[i].seq + "</div></div>");
				}
				if (resp[i].email) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"이메일</div><div class='modal_body_content_input_right read'>" + resp[i].email + "</div></div>");
				}
				if (resp[i].phone) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"전화</div><div class='modal_body_content_input_right read'>" + resp[i].phone + "</div></div>");
				}
				if (resp[i].company_name) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"회사</div><div class='modal_body_content_input_right read'>" + resp[i].company_name + "</div></div>");
				}
				if (resp[i].company_rank) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"직급</div><div class='modal_body_content_input_right read'>" + resp[i].company_rank + "</div></div>");
				}
				if (resp[i].company_department) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"부서</div><div class='modal_body_content_input_right read'>" + resp[i].company_department + "</div></div>");
				}
				if (resp[i].tag) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"태그</div><div class='modal_body_content_input_right read'>" +
							"<div class='main_tag_list' id='read_tag_content'></div></div></div>");
					for (let i = 0; i < tag_list.length; i++) {
						$("#read_tag_content").append("<div class='main_tag_list_item'>" + tag_list[i] + "</div>");
					}
				}
				if (resp[i].address) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"주소</div><div class='modal_body_content_input_right read'>" + resp[i].address + "</div></div>");
				}
				if (resp[i].link) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"홈페이지</div><div class='modal_body_content_input_right read'>" + resp[i].link + "</div></div>");
				}
				if (resp[i].birthday) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"생일</div><div class='modal_body_content_input_right read'>" + resp[i].birthday + "</div></div>");
				}
				if (resp[i].memo) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"메모</div><div class='modal_body_content_input_right read'>" + resp[i].memo + "</div></div>");
				}
			}
		});
	}

	else if (type == "share") {
		$.ajax({
			url: "/contact/shareContactRead?seq=" + select_category_seq,
			type: "GET",
			dataType: "json"
		}).done(function(respon) {
			$("#modal_footer_read").html('');

			let resp = respon.shareContactList;
			let loginId = respon.id;

			for (let i = 0; i < resp.length; i++) {
				let tag_list = resp[i].tag.split(',');
				read_seq = resp[i].seq;

				$("#read_title").html('');
				$("#read_content_form").html('');

				if (resp[i].name) {
					$("#read_title").
						append(resp[i].name);
				}
				if (resp[i].seq) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read' style='display:none;'><div class='modal_body_content_input_left read'>" +
							"SEQ</div><div class='modal_body_content_input_right read' id='read_seq'>" + resp[i].seq + "</div></div>");
				}
				if (resp[i].email) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"이메일</div><div class='modal_body_content_input_right read'>" + resp[i].email + "</div></div>");
				}
				if (resp[i].phone) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"전화</div><div class='modal_body_content_input_right read'>" + resp[i].phone + "</div></div>");
				}
				if (resp[i].company_name) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"회사</div><div class='modal_body_content_input_right read'>" + resp[i].company_name + "</div></div>");
				}
				if (resp[i].company_rank) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"직급</div><div class='modal_body_content_input_right read'>" + resp[i].company_rank + "</div></div>");
				}
				if (resp[i].company_department) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"부서</div><div class='modal_body_content_input_right read'>" + resp[i].company_department + "</div></div>");
				}
				if (resp[i].tag) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"태그</div><div class='modal_body_content_input_right read'>" +
							"<div class='main_tag_list' id='read_tag_content'></div></div></div>");
					for (let i = 0; i < tag_list.length; i++) {
						$("#read_tag_content").append("<div class='main_tag_list_item'>" + tag_list[i] + "</div>");
					}
				}
				if (resp[i].address) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"주소</div><div class='modal_body_content_input_right read'>" + resp[i].address + "</div></div>");
				}
				if (resp[i].link) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"홈페이지</div><div class='modal_body_content_input_right read'>" + resp[i].link + "</div></div>");
				}
				if (resp[i].birthday) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"생일</div><div class='modal_body_content_input_right read'>" + resp[i].birthday + "</div></div>");
				}
				if (resp[i].memo) {
					$("#read_content_form").
						append("<div class='modal_body_content_input read'><div class='modal_body_content_input_left read'>" +
							"메모</div><div class='modal_body_content_input_right read'>" + resp[i].memo + "</div></div>");
				}
				if (resp[i].edit == "true") {
					$("#modal_footer_read").
						append(`<button class="button_cancel" onclick="modal_cancel_button('read_modal',event)">닫기</button>
								<button class="button_apply" onclick="modal_read_edit('share')" style="margin-left: 14px;">수정</button>`);
				}


				else if (resp[i].edit == "false") {
					if (loginId == resp[i].writer) {
						$("#modal_footer_read").
							append(`<button class="button_cancel" onclick="modal_cancel_button('read_modal',event)">닫기</button>
								<button class="button_apply" onclick="modal_read_edit('share')" style="margin-left: 14px;">수정</button>`);
					}
					else {
						$("#modal_footer_read").
							append(`<button class="button_cancel" onclick="modal_cancel_button('read_modal',event)">닫기</button>`);
					}
				}
				console.log(resp[i].edit);
			}
		});
	}
};

// 연락처 수정 버튼 누를때 modal_read_edit
function modal_read_edit(type) {
	// 지난 READ 내용 비워주기
	$("#read_title").html('');
	$("#read_content_form").html('');

	// 추가 입력 항목 닫기 버튼, 편집 허용 
	$('.modal_body_content_minus').css('display', 'block');
	$("#modal_contact_add_edit").css('display', 'block');

	// 추가 입력 항목 폼 열어주기
	$('.modal_body_content_form_plus').css('display', 'block');

	// 읽기 창, 추가 입력 항목 열기 버튼
	$(".modal_contact_read").css('display', 'none');
	$('.modal_body_content_plus').css('display', 'none');

	if (type == "personal") {
		$.ajax({
			url: "/contact/personalContactRead?seq=" + read_seq,
			type: "GET",
			dataType: "json"
		}).done(function(resp) {
			let edit_list = resp[0].tag.split(',');
			if (resp[0].seq) {
				$("#modal_body_content_edit_right_seq").val(resp[0].seq);
			}
			if (resp[0].name) {
				$("#modal_body_content_edit_right_name").val(resp[0].name);
			}
			if (resp[0].email) {
				$("#modal_body_content_edit_right_email").val(resp[0].email);
			}
			if (resp[0].phone) {
				$("#modal_body_content_edit_right_phone").val(resp[0].phone);
			}
			if (resp[0].phone) {
				$("#modal_body_content_edit_right_phone").val(resp[0].phone);
			}
			if (resp[0].company_name) {
				$("#modal_body_content_edit_right_companyName").val(resp[0].company_name);
			}
			if (resp[0].company_rank) {
				$("#modal_body_content_edit_right_companyRank").val(resp[0].company_rank);
			}
			if (resp[0].company_department) {
				$("#modal_body_content_edit_right_companyDepartment").val(resp[0].company_department);
			}
			if (resp[0].address) {
				$("#modal_body_content_edit_right_address").val(resp[0].address);
			}
			if (resp[0].link) {
				$("#modal_body_content_edit_right_link").val(resp[0].link);
			}
			if (resp[0].birthday) {
				$("#modal_body_content_edit_right_birthday").val(resp[0].birthday);
			}
			if (resp[0].memo) {
				$("#modal_body_content_edit_right_memo").val(resp[0].memo);
			}
			if (resp[0].tag) {
				for (let i = 0; i < edit_list.length; i++) {
					$(".modal_tag_list").append("<div class='modal_tag_list_item'><div class='modal_tag_list_item_text'>" +
						edit_list[i] + "</div><div class='modal_tag_list_item_delete'><img src='/images/contact/X.png'></div></div>");
					selectedTags.push(edit_list[i]);
				}
			}
		});
	}

	else if (type == "share") {
		$.ajax({
			url: "/contact/shareContactRead?seq=" + read_seq,
			type: "GET",
			dataType: "json"
		}).done(function(respon) {
			resp = respon.shareContactList;
			let edit_list = resp[0].tag.split(',');
			if (resp[0].seq) {
				$("#modal_body_content_edit_right_seq").val(resp[0].seq);
			}
			if (resp[0].name) {
				$("#modal_body_content_edit_right_name").val(resp[0].name);
			}
			if (resp[0].email) {
				$("#modal_body_content_edit_right_email").val(resp[0].email);
			}
			if (resp[0].phone) {
				$("#modal_body_content_edit_right_phone").val(resp[0].phone);
			}
			if (resp[0].phone) {
				$("#modal_body_content_edit_right_phone").val(resp[0].phone);
			}
			if (resp[0].company_name) {
				$("#modal_body_content_edit_right_companyName").val(resp[0].company_name);
			}
			if (resp[0].company_rank) {
				$("#modal_body_content_edit_right_companyRank").val(resp[0].company_rank);
			}
			if (resp[0].company_department) {
				$("#modal_body_content_edit_right_companyDepartment").val(resp[0].company_department);
			}
			if (resp[0].address) {
				$("#modal_body_content_edit_right_address").val(resp[0].address);
			}
			if (resp[0].link) {
				$("#modal_body_content_edit_right_link").val(resp[0].link);
			}
			if (resp[0].birthday) {
				$("#modal_body_content_edit_right_birthday").val(resp[0].birthday);
			}
			if (resp[0].memo) {
				$("#modal_body_content_edit_right_memo").val(resp[0].memo);
			}
			if (resp[0].tag) {
				for (let i = 0; i < edit_list.length; i++) {
					$(".modal_tag_list").append("<div class='modal_tag_list_item'><div class='modal_tag_list_item_text'>" +
						edit_list[i] + "</div><div class='modal_tag_list_item_delete'><img src='/images/contact/X.png'></div></div>");
					selectedTags.push(edit_list[i]);
				}
			}
		});
	}
}

// 태그 리스트 열고 닫기
function tag_list_opener(type) {

	// 작성하기 버튼 눌러서 들어가는 곳 태그 불러오기
	if (type == "nomal") {
		if (tag_list_open) {
			$('.modal_tag_select_list').css('display', 'none');
			tag_list_open = false;
		} else {
			$('.modal_tag_select_list').empty();
			$('.modal_tag_select_list').css('display', 'block');

			if (contactType == "personal") {
				$.ajax({
					url: "/contact/personalContactTagSelectAll",
					type: "GET",
					dataType: "json"
				}).done(function(resp) {
					for (let i = 0; i < resp.length; i++) {
						const isTagSelected = selectedTags.includes(resp[i].tag);
						const tagClass = isTagSelected ? "modal_tag_select_item select" : "modal_tag_select_item";
						$(".modal_tag_select_list").append("<div class='" + tagClass + "'>" + resp[i].tag + "</div>");
					}
				});
			} else if (contactType == "share") {
				$.ajax({
					url: "/contact/shareContactTagSelectAll",
					type: "GET",
					dataType: "json"
				}).done(function(resp) {
					if (!resp.length == "0") {
						for (let i = 0; i < resp.length; i++) {
							const isTagSelected = selectedTags.includes(resp[i].tag);
							const tagClass = isTagSelected ? "modal_tag_select_item select" : "modal_tag_select_item";
							$(".modal_tag_select_list").append("<div class='" + tagClass + "'>" + resp[i].tag + "</div>");
						}
					} else {
						$(".modal_tag_select_list").append('<div class="modal_tag_select_item nothing">데이터가 없습니다.</div>');
					}
				});
			}
			tag_list_open = true;
		}
	}

	// 게시물 수정에서 퍼스널로 불러오기
	if (type == "personal") {
		if (tag_list_open) {
			$('.modal_tag_select_list').css('display', 'none');
			tag_list_open = false;
		} else {
			$('.modal_tag_select_list').empty();
			$('.modal_tag_select_list').css('display', 'block');

			if (contactType == "personal") {
				$.ajax({
					url: "/contact/personalContactTagSelectAll",
					type: "GET",
					dataType: "json"
				}).done(function(resp) {
					for (let i = 0; i < resp.length; i++) {
						const isTagSelected = selectedTags.includes(resp[i].tag);
						const tagClass = isTagSelected ? "modal_tag_select_item select" : "modal_tag_select_item";
						$(".modal_tag_select_list").append("<div class='" + tagClass + "'>" + resp[i].tag + "</div>");
					}
				});
			}
			tag_list_open = true;
		}
	}

	// 게시물 수정에서 공유로 불러오기
	if (type == "share") {
		if (tag_list_open) {
			$('.modal_tag_select_list').css('display', 'none');
			tag_list_open = false;
		} else {
			$('.modal_tag_select_list').empty();
			$('.modal_tag_select_list').css('display', 'block');

			if (contactType == "personal") {
				$.ajax({
					url: "/contact/shareContactTagSelectAll",
					type: "GET",
					dataType: "json"
				}).done(function(resp) {
					for (let i = 0; i < resp.length; i++) {
						const isTagSelected = selectedTags.includes(resp[i].tag);
						const tagClass = isTagSelected ? "modal_tag_select_item select" : "modal_tag_select_item";
						$(".modal_tag_select_list").append("<div class='" + tagClass + "'>" + resp[i].tag + "</div>");
					}
				});
			}
			tag_list_open = true;
		}
	}

}

// 모달 메인 태그 Select Add
$(document).on("click", ".modal_tag_select_item", function() {
	var tagValue = $(this).text();

	$(this).toggleClass("select");

	// 배열에 클릭한 태그의 값을 추가 또는 제거
	if ($(this).hasClass("select")) {
		// 선택된 태그라면 배열에 추가
		selectedTags.push(tagValue);
	} else {
		// 선택이 해제된 태그라면 배열에서 제거
		const index = selectedTags.indexOf(tagValue);
		if (index !== -1) {
			selectedTags.splice(index, 1);
		}
	}

	$(".modal_tag_list").empty(); // 기존 목록을 비우기

	// selectedTags의 각 요소에 대해 태그를 추가
	selectedTags.forEach(function(tag) {
		$(".modal_tag_list").append("<div class='modal_tag_list_item'><div class='modal_tag_list_item_text'>" +
			tag + "</div><div class='modal_tag_list_item_delete'><img src='/images/contact/X.png'></div></div>");
	});


});

// 모달 메인 태그 Select Delete
$(document).on("click", ".modal_tag_list_item_delete", function() {
	// 클릭한 div의 가장 가까운 부모인 .select_tag 요소를 찾음
	var parentTag = $(this).closest(".modal_tag_list_item");

	// .select_tag 안에 있는 .tag_name의 텍스트 값을 가져옴
	var tagName = parentTag.find(".modal_tag_list_item_text").text();

	// selectedTags 배열에서 tagName을 찾아서 제거
	var index = selectedTags.indexOf(tagName);
	if (index !== -1) {
		selectedTags.splice(index, 1);
	}

	// 해당 .select_tag를 화면에서 제거
	parentTag.remove();
});

// 태그 선택 박스 이외에 다른 곳이 클릭 됐을 때 
$(document).on("click", function(event) {
	if (tag_list_open) {
		if (!$(event.target).closest('.modal_tag').length) {
			$('.modal_tag_select_list').css('display', 'none');
			tag_list_open = false;
		}
	}
});

// 새 태그 만들기 클릭 했을때
function modal_body_content_input_bonus_text() {
	$('#modal_contact_add').css('z-index', '3');
	$('#modal_contact_add').css('border', 'solid 1px #707070');
	$('#modal_contact_add').css('box-shadow', '2px 5px 4px 0 rgba(0,0,0,.16)');
	$('.modal_background').css('z-index', '5');
	$('#modal_tag_add_nomal').css('display', 'block');
};

// 글 수정하는 곳에서 새 태그 만들기 클릭 했을때
function modal_body_content_input_bonus_text_update() {
	$('#modal_contact_add').css('z-index', '3');
	$('#modal_contact_add').css('border', 'solid 1px #707070');
	$('#modal_contact_add').css('box-shadow', '2px 5px 4px 0 rgba(0,0,0,.16)');
	$('#modal_tag_update_nomal').css('display', 'block');
	$('.modal_background').css('z-index', '5');
};

// 새 태그 만들기 취소 했을 때
function button_cancel_tag() {
	$("#modal_new_tag_input").val("");
	$('#modal_contact_add').css('z-index', '4');
	$('.modal_background').css('z-index', '4');
	$('#modal_contact_add').css('border', '');
	$('#modal_contact_add').css('box-shadow', '');
	$('.modal_tag_add').css('display', 'none');
	$('#modal_tag_update_nomal').css('display', 'none');
	$('#button_apply_tag').addClass('permit');
	$('.modal_tag_duplication_error').css('display', 'none');
	$('.modal_tag_regex_error').css('display', 'none');

};



// 새 태그 중복 확인
function edit_form_new_tag_deplicate(type, element) {

	if (type == "nomal") {
		$('#modal_new_tag_input').on('keyup', function() {

			let tag_input = $(this).val();
			console.log("현재 입력 값" + tag_input);

			if (tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
				$('.modal_tag_regex_error').css('display', 'block');
				$('.button_apply.tag').addClass('permit');
			}

			if (!tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
				$('.modal_tag_regex_error').css('display', 'none');
			}

			if (!tag_input) {
				console.log("공백임");
				$('.button_apply.tag').addClass('permit');
				$(".modal_tag_duplication_error").css('display', 'none');
				$(".modal_tag_regex_error").css('display', 'none');

			}

			if (contactType == "personal") {
				$.ajax({
					url: "/contact/personalContactTagSelectAllDeplicate",
					type: "GET",
					data: {
						tag: tag_input
					}
				}).done(function(resp) {
					if (resp.length == 1) {
						$('.modal_tag_duplication_error').css('display', 'flex');
						$('.button_apply.tag').addClass('permit');
					} else {
						$('.modal_tag_duplication_error').css('display', 'none');
						$('.button_apply.tag').removeClass('permit');
					}
				});

			} else if (contactType == "share") {
				console.log("공유로 들어까지는 옴");
				$.ajax({
					url: "/contact/shareContactTagSelectAllDeplicate",
					type: "GET",
					data: {
						tag: tag_input
					}
				}).done(function(resp) {
					if (resp.length == 1) {
						$('.modal_tag_duplication_error').css('display', 'flex');
						$('.button_apply.tag').addClass('permit');
					} else {
						$('.modal_tag_duplication_error').css('display', 'none');
						$('.button_apply.tag').removeClass('permit');
					}
				});
			}
		});
	}

	else if (type == "direct") {
		$('#modal_new_tag_input_direct').on('keyup', function() {
			let tag_input = $(element).val();

			if (tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
				$('.modal_tag_regex_error').css('display', 'block');
				$('.button_apply.tag').addClass('permit');
				return;
			}

			if (!tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
				$('.modal_tag_regex_error').css('display', 'none');
			}

			if (!tag_input) {
				$('.button_apply.tag').addClass('permit');
				$(".modal_tag_duplication_error").css('display', 'none');
				$(".modal_tag_regex_error").css('display', 'none');
				return;
			}

			if (contactType == "personal") {
				$.ajax({
					url: "/contact/personalContactTagSelectAllDeplicate",
					type: "GET",
					data: {
						tag: tag_input
					}
				}).done(function(resp) {
					if (resp.length == 1) {
						$('.modal_tag_duplication_error').css('display', 'flex');
						$('.button_apply.tag').addClass('permit');
					} else {
						$('.modal_tag_duplication_error').css('display', 'none');
						$('.button_apply.tag').removeClass('permit');
					}
				});
			}
			else if (contactType == "share") {
				$.ajax({
					url: "/contact/shareContactTagSelectAllDeplicate",
					type: "GET",
					data: {
						tag: tag_input
					}
				}).done(function(resp) {
					if (resp.length == 1) {
						$('.modal_tag_duplication_error').css('display', 'flex');
						$('.button_apply.tag').addClass('permit');
					} else {
						$('.modal_tag_duplication_error').css('display', 'none');
						$('.button_apply.tag').removeClass('permit');
					}
				});
			}
		});
	}

	else if (type == "personal") { // 수정 -> 태그 만들기
		$('#modal_new_tag_input_from_edit').on('keyup', function() {
			let tag_input = $(this).val();
			console.log("현재 입력 값" + tag_input);
			console.log("찍");
			console.log(tag_input);

			if (tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
				$('.modal_tag_regex_error').css('display', 'block');
				$('.button_apply.tag').addClass('permit');
			}


			if (!tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
				$('.modal_tag_regex_error').css('display', 'none');
			}


			if (!tag_input) {
				$('.button_apply.tag').addClass('permit');
				$(".modal_tag_duplication_error").css('display', 'none');
				$(".modal_tag_regex_error").css('display', 'none');
			}

			$.ajax({
				url: "/contact/personalContactTagSelectAllDeplicate",
				type: "GET",
				data: {
					tag: tag_input
				}
			}).done(function(resp) {
				if (resp.length == 1) {
					$('.modal_tag_duplication_error').css('display', 'flex');
					$('.button_apply.tag').addClass('permit');
				} else {
					$('.modal_tag_duplication_error').css('display', 'none');
					$('.button_apply.tag').removeClass('permit');
				}
			});
		});
	}

	else if (type == "share") { // 수정 -> 태그 만들기
		$('#modal_new_tag_input_from_edit').on('keyup', function() {
			let tag_input = $(this).val();
			console.log("현재 입력 값" + tag_input);
			console.log("찍");
			console.log(tag_input);

			if (tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
				$('.modal_tag_regex_error').css('display', 'block');
				$('.button_apply.tag').addClass('permit');
			}

			if (!tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
				$('.modal_tag_regex_error').css('display', 'none');
			}

			if (!tag_input) {
				$('.button_apply.tag').addClass('permit');
				$(".modal_tag_duplication_error").css('display', 'none');
				$(".modal_tag_regex_error").css('display', 'none');
			}

			$.ajax({
				url: "/contact/shareContactTagSelectAllDeplicate",
				type: "GET",
				data: {
					tag: tag_input
				}
			}).done(function(resp) {
				if (resp.length == 1) {
					$('.modal_tag_duplication_error').css('display', 'flex');
					$('.button_apply.tag').addClass('permit');
				} else {
					$('.modal_tag_duplication_error').css('display', 'none');
					$('.button_apply.tag').removeClass('permit');
				}
			});
		});
	}

	else if (type == "edit") { // 왼쪽 메뉴 -> 이름 수정하기
		let tag_input = $(element).val();
		console.log("현재 입력 값" + tag_input);
		console.log("찍");
		console.log(tag_input);

		if (tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
			$('.modal_tag_regex_error').css('display', 'block');
			$('.button_apply.tag').addClass('permit');
		}

		if (!tag_input.includes(',') || tag_input.includes('미등록 태그') || tag_input.includes('[') || tag_input.includes(']')) {
			$('.modal_tag_regex_error').css('display', 'none');
		}

		if (!tag_input) {
			$('.button_apply.tag').addClass('permit');
			$(".modal_tag_duplication_error").css('display', 'none');
			$(".modal_tag_regex_error").css('display', 'none');
		}

		if (plus_tag_type == "개인 주소록") {
			$.ajax({
				url: "/contact/personalContactTagSelectAllDeplicate",
				type: "GET",
				data: {
					tag: tag_input
				}
			}).done(function(resp) {
				if (resp.length == 1) {
					$('.modal_tag_duplication_error').css('display', 'flex');
					$('.button_apply.tag').addClass('permit');
				} else {
					$('.modal_tag_duplication_error').css('display', 'none');
					$('.button_apply.tag').removeClass('permit');
				}
			});
		}

		else if (plus_tag_type == "공유 주소록") {
			$.ajax({
				url: "/contact/shareContactTagSelectAllDeplicate",
				type: "GET",
				data: {
					tag: tag_input
				}
			}).done(function(resp) {
				if (resp.length == 1) {
					$('.modal_tag_duplication_error').css('display', 'flex');
					$('.button_apply.tag').addClass('permit');
				} else {
					$('.modal_tag_duplication_error').css('display', 'none');
					$('.button_apply.tag').removeClass('permit');
				}
			});
		}



	}

}

// 새 태그 생성(노말)
$(document).on("click", "#button_apply_tag", function() {
	let tag_input = $("#modal_new_tag_input").val();

	if (contactType == "personal") {
		$.ajax({
			url: "/contact/personalContactTagInsert",
			type: "GET",
			data: {
				tag: tag_input
			}
		}).done(function() {
			$("#modal_new_tag_input").val("");
			$('#modal_contact_add').css('z-index', '5');
			$('#modal_contact_add').css('border', '');
			$('#modal_contact_add').css('box-shadow', '');
			$('#modal_tag_add_nomal').css('display', 'none');
			$('#button_apply_tag').addClass('permit');
			$('.modal_background').css('z-index', '4');
		});
	} else if (contactType == "share") {
		$.ajax({
			url: "/contact/shareContactTagInsert",
			type: "GET",
			data: {
				tag: tag_input
			}
		}).done(function() {
			$("#modal_new_tag_input").val("");
			$('#modal_contact_add').css('z-index', '5');
			$('#modal_contact_add').css('border', '');
			$('#modal_contact_add').css('box-shadow', '');
			$('#modal_tag_add_nomal').css('display', 'none');
			$('#button_apply_tag').addClass('permit');
			$('.modal_background').css('z-index', '4');
		});
	}
});

// 새 태그 생성(수정창)
function button_apply_tag_update(type) {

	if (type == "personal") {
		let tag_input = $("#modal_new_tag_input_from_edit").val();
		$.ajax({
			url: "/contact/personalContactTagInsert",
			type: "GET",
			data: {
				tag: tag_input
			}
		}).done(function() {
			$("#modal_new_tag_input_from_edit").val("");
			$('.modal_background').css('z-index', '4');
			$('#modal_contact_add').css('z-index', '5');
			$('#modal_contact_add').css('border', '');
			$('#modal_contact_add').css('box-shadow', '');
			$('#modal_tag_update_nomal').css('display', 'none');
			$('.button_apply_tag').addClass('permit');
		});
	}
	else if (type == "share") {
		let tag_input = $("#modal_new_tag_input_from_edit").val();
		$.ajax({
			url: "/contact/shareContactTagInsert",
			type: "GET",
			data: {
				tag: tag_input
			}
		}).done(function() {
			$("#modal_new_tag_input_from_edit").val("");
			$('.modal_background').css('z-index', '4');
			$('#modal_contact_add').css('z-index', '5');
			$('#modal_contact_add').css('border', '');
			$('#modal_contact_add').css('box-shadow', '');
			$('#modal_tag_update_nomal').css('display', 'none');
			$('.button_apply_tag').addClass('permit');
		});
	}
};

// 새 태그 생성 다이렉트
$(document).on("click", "#button_apply_tag_direct", function() {
	let tag_input = $("#modal_new_tag_input_direct").val();

	if (contactType == "personal") {
		window.location.href = "/contact/personalContactTagInsertDirect?tag=" + tag_input;
	} else if (contactType == "share") {
		window.location.href = "/contact/shareContactTagInsertDirect?tag=" + tag_input;
	}
});

// 입력 항목 추가 눌렀을 때
$(document).on("click", ".modal_body_content_plus", function() {
	$('.modal_body_content_plus').css('display', 'none');
	$('.modal_body_content_minus').css('display', 'block');
	$('.modal_body_content_form_plus').css('display', 'block');
});

// 추가 입력 항목 닫기 눌렀을 때
$(document).on("click", ".modal_body_content_minus", function() {
	$('.modal_body_content_minus').css('display', 'none');
	$('.modal_body_content_plus').css('display', 'block');
	$('.modal_body_content_form_plus').css('display', 'none');
});

// 개인 주소록, 공유 주소록을 눌렀을 때 편집 허용 창 보여주고 안 보여주고 선택한거에 select 붙여주고
$(document).ready(function() {
	$('.modal_toggle_button').click(function() {
		$('.modal_toggle_button').removeClass('select');
		$(this).addClass('select');

		var classList = $(this).attr('class').split(/\s+/);

		if (classList.includes('personal')) {
			$('.modal_body_content_form_edit').css('display', 'none');
			contactType = "personal";
			selectedTags = [];
			$(".modal_tag_list").html("");
		} else if (classList.includes('share')) {
			$('.modal_body_content_form_edit').css('display', 'flex');
			contactType = "share";
			selectedTags = [];
			$(".modal_tag_list").html("");
		}
	});
});

// 편집 허용, 허용 안 하는거 토글 관리
$(document).on("click", ".edit_toggle", function() {
	if (editToggle) {
		$('#edit_toggle_text').html('허용 안함');
		editToggle = false;
		$('#hidden_edit_toggle_value').val(editToggle);
	} else {
		$('#edit_toggle_text').html('허용 함');
		editToggle = true;
		$('#hidden_edit_toggle_value').val(editToggle);
	}
});

// READY
$(document).ready(function() {

	// 이름이 NULL 값인지 체크 (주소록 변경) 
	$('#modal_body_content_edit_right_name').on('keyup', function() {
		var inputValue = $(this).val();

		if (inputValue !== "") {
			$('.button_apply.float_right').removeClass('permit');
		} else {
			$('.button_apply.float_right').addClass('permit');
		}
	});

	// 이름이 NULL 값인지 체크 (주소록 만들기)
	$('#modal_body_content_input_right_name').on('keyup', function() {
		var inputValue = $(this).val();

		if (inputValue !== "") {
			$('.button_apply.float_right').removeClass('permit');
		} else {
			$('.button_apply.float_right').addClass('permit');
		}
	});
});



// 저장 버튼 눌렀을때 하나만 저장
$(document).ready(function() {
	$('#modal_apply_button').on('click', function() {
		$('#contact_form').removeAttr('action');
		$('#hidden_tag_list').val(selectedTags);

		if (contactType == "personal") {
			$('#contact_form').attr('action', persion_action);
			$("#contact_form").submit();
		} else if (contactType == "share") {
			$('#contact_form').attr('action', share_action);
			$("#contact_form").submit();
		}
	});
});

// 저장 버튼 눌렀을때 계속 저장
$(document).ready(function() {
	$('#modal_apply_list_button').on('click', function(event) {
		event.preventDefault();
		$('#hidden_tag_list').val(selectedTags);
		let formData = $("#contact_form").serialize();

		if (contactType == "personal") {
			$.ajax({
				type: 'POST', // 또는 'GET'
				url: persion_action_ajax, // 폼 데이터를 보낼 엔드포인트
				data: formData,
				success: function() {
					$('#contact_form :input').val('');
					selectedTags = [];
					$(".modal_tag_list").html("");
				},
			});
		} else if (contactType == "share") {
			$.ajax({
				type: 'POST', // 또는 'GET'
				url: share_action_ajax, // 폼 데이터를 보낼 엔드포인트
				data: formData,
				success: function() {
					$('#contact_form :input').val('');
					selectedTags = [];
					$(".modal_tag_list").html("");
				},
			});
		}
	});
});

// 왼쪽 메뉴에서 항목 눌렀을때

$(document).ready(function() {
	$('.menu_list_item').click(function() {
		let item_name = $(this).find('.menu_list_item_text').text().trim();
		let parent_name = $(this).closest('.menu_list').find('.menu_list_button_text').text().trim();

		if (parent_name == "개인 주소록") {
			if (item_name == "전체") {
				window.location.href = "/contact/personal";
			} else {
				window.location.href = "/contact/personal?tag=" + item_name;
				//console.log(item_name);
			}
		} else if (parent_name == "공유 주소록") {
			if (item_name == "전체") {
				window.location.href = "/contact/share";
			} else {
				window.location.href = "/contact/share?tag=" + item_name;
			}
		}
	});

});


// 검색창 포커싱 됐을때
$(document).ready(function() {
	var myInput = $('#searchTextBoxInput');
	var searchBox = $('.searchBox');

	myInput.on('focus', function() {
		searchBox.addClass('focus');
	});

	myInput.on('blur', function() {
		searchBox.removeClass('focus');
	});
});

// 연락처 수정 확인 누를때
function modal_update_apply(type) {
	console.log("ㄴㅁㅁ1;1");
	$('#contact_form').removeAttr('action');
	$('#hidden_tag_list_update').val(selectedTags);

	if (type == "personal") {
		$('#contact_form_edit').attr('action', persion_action_update);
		$("#contact_form_edit").submit();
	}

	else if (type == "share") {
		$('#contact_form_edit').attr('action', share_action_update);
		$("#contact_form_edit").submit();
	}
};


// 연락처 수정 삭제 누를때
function modal_update_delete(type) {
	if (type == "personal") {
		$('#contact_form').removeAttr('action');
		$('#contact_form_edit').attr('action', persion_action_delete);
		$("#contact_form_edit").submit();
	}

	else if (type == "share") {
		$('#contact_form').removeAttr('action');
		$('#contact_form_edit').attr('action', share_action_delete);
		$("#contact_form_edit").submit();
	}

}

function search(event, type) {
	if (event.keyCode === 13) {
		if (type == "personal") {
			window.location.href = "/contact/personalContactSearch?keyword=" + $("#searchTextBoxInput").val();
		}
		else if (type == "share") {
			window.location.href = "/contact/shareContactSearch?keyword=" + $("#searchTextBoxInput").val();
		}
	}
}

// 드롭 메뉴에서 개인 주소록 태그 추가
$(document).ready(function() {
	$("#personal_drop_tag_add").on('click', function() {
		contactType = "personal";
		$('.modal_form').css('display', 'block');
		$("#modal_tag_add_direct").css('display', 'block');
		plus_parent = $(this).closest(".menu_list");
		plus_tag_type = plus_parent.find(".menu_list_button_text").html();
		return false;
	});
});

// 드롭 메뉴에서 공유 주소록 태그 추가
$(document).ready(function() {
	$("#share_drop_tag_add").on('click', function() {
		contactType = "share";
		$('.modal_form').css('display', 'block');
		$("#modal_tag_add_direct").css('display', 'block');
		plus_parent = $(this).closest(".menu_list");
		plus_tag_type = plus_parent.find(".menu_list_button_text").html();
		return false;
	});
});

// point 눌렀을때 메뉴 이름과 누른 리스트 이름이 뭔지 출력
// 그 이후 포인트 사용 가능하게 창을 상황에 맞게 띄워줌
$(document).ready(function() {
	$(".menu_list_item_plus.point").click(function(event) {
		event.stopPropagation();

		plus_parent = $(this).closest(".menu_list");

		plus_tag_type = plus_parent.find(".menu_list_button_text").html();
		plus_index = plus_parent.find(".menu_list_item_plus.point").index(this) + 1;
		plus_tag_name = plus_parent.find(".menu_list_item_text").eq(plus_index).text().trim();

		var clickedPosition = $(this).offset();
		plus_x_now = clickedPosition.left;
		plus_y_now = clickedPosition.top + $(this).height() + 30;

		if (!plus_open) {
			$(".menu_list_item_plus_click").css({
				top: clickedPosition.top + $(this).height() + 30,
				left: clickedPosition.left,
				display: "block",
			});

			$(".menu_list_item_plus_click").show();
			plus_open = true;
			plus_y = clickedPosition.top + $(this).height() + 30;
			plus_x = clickedPosition.left;
		} else if (plus_open) {
			if (plus_y != plus_y_now) {
				$(".menu_list_item_plus_click").css({
					top: clickedPosition.top + $(this).height() + 30,
					left: clickedPosition.left,
					display: "block",
				});

				$(".menu_list_item_plus_click").show();
				plus_open = true;
				plus_y = clickedPosition.top + $(this).height() + 30;
				plus_x = clickedPosition.left;
			} else {
				$(".menu_list_item_plus_click").css("display", "none");
				plus_open = false;
				plus_y = clickedPosition.top + $(this).height() + 30;
				plus_x = clickedPosition.left;
			}
		}
	});
});

// 태그 선택 박스 이외에 다른 곳이 클릭 됐을 때
$(document).on("click", function(event) {
	if (plus_open) {
		if (!$(event.target).closest(".menu_list_item_plus_click").length) {
			$(".menu_list_item_plus_click").css("display", "none");
			plus_open = false;
		}
	}
});

// 플러스 클릭 탭에서 이름 변경하기 클릭 했을때 창 띄우고 값 넣기
$(document).ready(function() {
	$("#check_item_update").on("click", function() {
		$(".menu_list_item_plus_click").css("display", "none");
		$('.modal_form').css('display', 'block');
		$(".modal_tag_edit").css('display', 'block');
		$("#modal_tag_input_edit").val(plus_tag_name);
	});
});

// 플러스 클릭 수정 탭에서 취소 눌렀을때
$(document).ready(function() {
	$("#button_cancel_tag_edit").on("click", function() {
		$("#modal_tag_input_edit").val("");
		$('.modal_form').css('display', 'none');
		$(".modal_tag_edit").css('display', 'none');
		$(".modal_tag_duplication_error").css('display', 'none');
		$(".modal_tag_regex_error").css('display', 'none');
	});
});

// 플러스 클릭 태그 이름 변경 수정 탭에서 저장 눌렀을 때
$(document).ready(function() {
	$("#button_apply_tag_edit").on("click", function() {
		if (plus_tag_type == "개인 주소록") {
			window.location.href = "/contact/personalContactTagRename?ori_tagname="
				+ plus_tag_name + "&new_tagname=" + $("#modal_tag_input_edit").val();
		}
		else if (plus_tag_type == "공유 주소록") {
			window.location.href = "/contact/shareContactTagRename?ori_tagname="
				+ plus_tag_name + "&new_tagname=" + $("#modal_tag_input_edit").val();
		}
	});
});

// 플러스 클릭 탭에서 삭제 버튼 누르고 모달 창 띄울때
$(document).ready(function() {
	$("#check_item_delete").on("click", function() {
		$(".menu_list_item_plus_click").css("display", "none");
		$(".modal_form").css("display", "block");
		$(".modal_tag_delete").css("display", "block");

		$(".modal_body_delete_message").html('');
		if (plus_tag_type == "개인 주소록") {
			$(".modal_body_delete_message").append(
				"<b>" + plus_tag_name + "</b> 태그를 삭제 하시겠습니까?"
			);
			$(".modal_body_delete_message").append(
				"<br>삭제 방법을 선택하세요."
			);
		}

		else if (plus_tag_type = "공유 주소록") {
			$(".modal_body_delete_message").append(
				"<b>" + plus_tag_name + "</b> 태그를 삭제 하시겠습니까?"
			);
			$(".modal_body_delete_message").append(
				"<br>삭제를 하면 전직원의 주소록에서도 삭제되고, 삭제된 태그는 복원할 수 없습니다."
			);
		}

		//  window.location.href = "/contact/personal?tag=" + tag_input;
	});
});

// 플러스 클릭 탭에서 삭제 모달 속 라디오 체크 박스
$(document).ready(function() {
	$(".modal_body_delete_radio_select").on("click", function() {
		$(
			".modal_body_delete_radio_select_left, .modal_body_delete_radio_select_right"
		).removeClass("checked");
		$(this).find(".modal_body_delete_radio_select_left").addClass("checked");
		$(this).find(".modal_body_delete_radio_select_right").addClass("checked");
	});
});

// 플러스 클릭 탭에서 삭제 모달 속 취소 버튼
$(document).ready(function() {
	$("#button_cancel_tag_delete").on("click", function() {
		$(".modal_form").css("display", "none");
		$(".modal_tag_delete").css("display", "none");
		$(
			".modal_body_delete_radio_select_left, .modal_body_delete_radio_select_right"
		).removeClass("checked");
		$(".modal_body_delete_radio_select_left").first().addClass("checked");
		$(".modal_body_delete_radio_select_right").first().addClass("checked");
	});
});

// window.location.href = "/contact/personal?tag=" + tag_input;
// 플러스 클릭 탭에서 삭제 모달 속 삭제 버튼
$(document).ready(function() {
	$("#button_delete_tag_delete").on("click", function() {
		let alert = confirm("정말 삭제하시겠습니까??");

		if (alert) {
			var check_id = $(".modal_body_delete_radio_select_left.checked").closest('.modal_body_delete_radio_select').attr('id');
			if (check_id == "radio_tag") {
				if (plus_tag_type == "개인 주소록") {
					window.location.href = "/contact/personalContactTagDeleteRadioTag?tag=" + plus_tag_name;
				} else if (plus_tag_type == "공유 주소록") {
					window.location.href = "/contact/shareContactTagDeleteRadioTag?tag=" + plus_tag_name;
				}
			} else if (check_id == "radio_all") { // 전부 삭제하는겅
				if (plus_tag_type == "개인 주소록") {
					window.location.href = "/contact/personalContactTagDeleteRadioAll?tag=" + plus_tag_name;
				} else if (plus_tag_type == "공유 주소록") {
					window.location.href = "/contact/shareContactTagDeleteRadioAll?tag=" + plus_tag_name;
				}
			}

		} else {
			return false;
		}
	});
});

