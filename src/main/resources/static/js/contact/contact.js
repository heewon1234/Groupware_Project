let tag_list_open = false;
let selectedTags = [];
let editToggle = false;
let contactType = "personal";

let persion_action = "/contact/personalContactInsert";
let persion_action_ajax = "?";

let share_action = "/contact/shareContactInsert";
let share_action_ajax = "?";

// 작성하기 버튼 눌렀을때 모달 창 띄우는거
$(document).ready(function() {
    $('#modal_open_button').click(function() {
        $('.modal_form').css('display', 'block');
    });
});

// 모달창에서 취소 누르면 모달창 꺼지는거
$(document).ready(function() {
    $('#modal_cancel_button').click(function() {
        $('.modal_form').css('display', 'none');
    });
});

// 태그 선택창 열고 닫기
$(document).ready(function() {
    $('.modal_tag').click(function() {
		console.log(contactType);
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
            } else if(contactType == "share") {
            $.ajax({
                url: "/contact/shareContactTagSelectAll",
                type: "GET",
                dataType: "json"
            }).done(function(resp) {
				if(!resp.length == "0") {
					for (let i = 0; i < resp.length; i++) {
                    const isTagSelected = selectedTags.includes(resp[i].tag);
                    const tagClass = isTagSelected ? "modal_tag_select_item select" : "modal_tag_select_item";
                    $(".modal_tag_select_list").append("<div class='" + tagClass + "'>" + resp[i].tag + "</div>");
                }
				}
				else {
					$(".modal_tag_select_list").append('<div class="modal_tag_select_item nothing">데이터가 없습니다.</div>');
				}
            });
            }
            tag_list_open = true;
        }
    });
});

// 태그 select 붙이기
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

// 선택한 태그 삭제
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
$(document).on("click", ".modal_body_content_input_bonus_text", function() {
    $('.modal_contact_add').css('z-index', '3');
    $('.modal_contact_add').css('border', 'solid 1px #707070');
    $('.modal_contact_add').css('box-shadow', '2px 5px 4px 0 rgba(0,0,0,.16)');
    $('.modal_tag_add').css('display', 'block');
});

// 새 태그 만들기 취소 했을 때
$(document).on("click", "#button_cancel_tag", function() {
    $("#modal_new_tag_input").val("");
    $('.modal_contact_add').css('z-index', '4');
    $('.modal_contact_add').css('border', '');
    $('.modal_contact_add').css('box-shadow', '');
    $('.modal_tag_add').css('display', 'none');
});

// 새 태그 중복 확인
$(document).ready(function() {
    $('#modal_new_tag_input').on('keyup', function() {

        let tag_input = $("#modal_new_tag_input").val();

        if (!tag_input) {
            $('#button_apply_tag').addClass('permit');
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
                    $('#button_apply_tag').addClass('permit');
                } else {
                    $('.modal_tag_duplication_error').css('display', 'none');
                    $('#button_apply_tag').removeClass('permit');
                }
            });

        } else if (contactType == "share") {

            // 추가 예정
        }
    });
});

// 새 태그 생성
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
            $('.modal_contact_add').css('z-index', '4');
            $('.modal_contact_add').css('border', '');
            $('.modal_contact_add').css('box-shadow', '');
            $('.modal_tag_add').css('display', 'none');
            $('#button_apply_tag').addClass('permit');
        });
    } else if (contactType == "share") {
        console.log("공유로 새 태그 넣을거임")
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


// 페이지 이동 하는거 나중에 세션으로 쓸듯
$(document).ready(function() {
    $(".modal_tag_select_item").on("click", function() {
        console.log("요소가 클릭되었습니다.");
        // 원하는 동작을 추가하세요.
    });
});


// 이름 값 추가되면 버튼 바뀌는거
$(document).ready(function() {
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
            console.log("공유 주소록 등록");
            console.log("편집 허용 여부" + editToggle);
            $('#contact_form').attr('action', share_action);
            $("#contact_form").submit();
        }
    });
});

// 저장 버튼 눌렀을때 계속 저장
$(document).ready(function() {
    $('#modal_apply_list_button').on('click', function(event) {
        event.preventDefault();
        console.log("여러개");
    });
});