<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_edit.css" />
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
                <div class="title">게시판 만들기</div>
                <div class="select_box">
                    <select class="select">
                        <option value="every">모든 게시판</option>
                        <option value="all">전사 게시판</option>
                        <option value="group">그룹 게시판</option>
                    </select>
                </div>
                <div class="board_list_box">
                    <div class="board_index">
                        <div class="board_index_check"><input type="checkbox"></div>
                        <div class="board_index_board_type">종류</div>
                        <div class="board_index_name_type">유형</div>
                        <div class="board_index_board_title">게시판 이름</div>
                        <div class="board_index_board_admin">관리자</div>
                        <div class="board_index_mk_date">만든 날짜</div>
                        <div class="buttons">관리</div>
                    </div>
                    <div class="board_list">
                        <div class="board_index_check"><input type="checkbox"></div>
                        <div class="board_index_board_type">전사</div>
                        <div class="board_index_name_type">일반형</div>
                        <div class="board_index_board_title">자유게시판</div>
                        <div class="board_index_board_admin">대표이사</div>
                        <div class="board_index_mk_date">2023-11-07</div>
                        <div class="buttons">
                            <button>수정</button>
                            <button>삭제</button>
                        </div>
                    </div>
                    <div class="board_list">
                        <div class="board_index_check"><input type="checkbox"></div>
                        <div class="board_index_board_type">전사</div>
                        <div class="board_index_name_type">일반형</div>
                        <div class="board_index_board_title">자유게시판</div>
                        <div class="board_index_board_admin">대표이사</div>
                        <div class="board_index_mk_date">2023-11-07</div>
                        <div class="buttons">
                            <button>수정</button>
                            <button>삭제</button>
                        </div>
                    </div>
                    <div class="board_list">
                        <div class="board_index_check"><input type="checkbox"></div>
                        <div class="board_index_board_type">전사</div>
                        <div class="board_index_name_type">일반형</div>
                        <div class="board_index_board_title">자유게시판</div>
                        <div class="board_index_board_admin">대표이사</div>
                        <div class="board_index_mk_date">2023-11-07</div>
                        <div class="buttons">
                            <button>수정</button>
                            <button>삭제</button>
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