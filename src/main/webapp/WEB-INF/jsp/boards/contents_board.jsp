<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_contents.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/commons/body_form/body_form_default.js"></script>
</head>
<body>
	<div class="top">TOP</div>
    <div class="body_form">
        <div class="left_item" id="left_item">

        </div>
        <div class="right_item">
            <div class="content_tab">
                <div class="noticeBox">
                    <button>공지</button>
                </div>
               <div class="board_contents_info">
                    <div class="favorite"><img src="/images/commons/body_form/left_item/default/favorites.png" /></div>
                    <div class="profile">
                        <img src="/images/commons/body_form/left_item/default/information.png" />
                    </div>
                    <div class="contents_info">
                        <div class="board_contents_title">글1</div>
                        <div class="contents_info_datail">
                            <div>대표이사</div> |
                            <div>테스트1</div> |
                            <div>조회수 10</div>
                        </div>
                    </div>
                    <div class="contents_write_date">2023-11-06 00:06</div>
                </div>
                <div class="contents">
                    글3
                </div>
                <div class="buttons">
                    <button>수정</button>
                    <button>삭제</button>
                </div>
                <div class="reply_box">
                    <div class="input_reply_box">
                        <div class="reply_profile">
                            <img src="/images/commons/body_form/left_item/default/information.png" />
                        </div>
                        <div class="reply_input">
                            <textarea placeholder="댓글을 입력하세요"></textarea>
                        </div>
                        <div class="reply_button">
                            <button>등록</button>
                        </div>
                    </div>
                    <div class="reply_list_box">
                        <div class="reply_list">
                            <div class="reply_list_info">
                                <div>대표이사</div>
                                <div>2023-11-06 19:34</div>
                            </div>
                            <div class="reply_list_contents">
                                하하하
                            </div>
                            <div class="reply_list_edit_buttons">
                                <button>수정</button>
                                <button>삭제</button>
                            </div>
                        </div>
                        <div class="reply_list">
                            <div class="reply_list_info">
                                <div>대표이사</div>
                                <div>2023-11-06 19:34</div>
                            </div>
                            <div class="reply_list_contents">
                                하하하
                            </div>
                            <div class="reply_list_edit_buttons">
                                <button>수정</button>
                                <button>삭제</button>
                            </div>
                        </div>
                        <div class="reply_list">
                            <div class="reply_list_info">
                                <div>대표이사</div>
                                <div>2023-11-06 19:34</div>
                            </div>
                            <div class="reply_list_contents">
                                하하하
                            </div>
                            <div class="reply_list_edit_buttons">
                                <button>수정</button>
                                <button>삭제</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
     <script>
    	$(document).ready(function() {
			$("#left_item").load("/board/sideBar");
		});
    </script>
</body>
</html>