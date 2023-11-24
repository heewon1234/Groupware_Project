<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_contents_write.css" />
</head>
<body>
	<div class="top" id="top_container"></div>
    <div class="body_form">
        <div class="left_item" id="left_item">

        </div>
        <div class="right_item">
            <div class="content_tab">
                <form action="/board/insertBoardContents" id="frm" method="post" enctype="multipart/form-data">
                    <div class="board_title_box">
                        <div class="board_title_text">게시판</div>
                        <div>
                            <select name="board_title" id="board_title">
                                <option selected>게시판선택</option>
                                <c:forEach items="${boardList }" var="i">
                                	<option>${i }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <select id="header" name="header" disabled>
                                <option selected>말머리 선택</option>
                            </select>
                        </div>
                        <div></div>
                    </div>
                    <div class="board_contents_title_box">
                        <div class="info_text_title">제목</div>
                        <div>
                        	<input type="text" class="form-control" placeholder="제목을 입력하세요" name="title" maxlength="100" id="header_add_input">
                        </div>
                        <div>
                            <input type="checkbox" name="notice" value="false"> <span class="noticeTextBox">&nbsp;공지로 등록</span>
                        </div>
                    </div>
                    <div class="board_contents_file_box">
                        <div class="file_box_text">파일 첨부 &nbsp;&nbsp;<span id="fileAddBtn">+</span></div>
                        <div class="file_box_input_file" id="file_box_input_file">

                        </div>
                    </div>
                    <div class="board_contents_box">
                        <textarea id="summernote" name="contents"></textarea>
                    </div>
                    <div class="board_survey">
                        <div>설문 여부</div>
                        <div>
                            <div><input type="radio" name="useSurvey" value="true"> <span class="useSurveyTextBox">사용</span></div>
                            <div><input type="radio" name="useSurvey" value="false" checked> <span  class="useSurveyTextBox">사용 안 함</span></div>
                        </div>
                        <div id="surveyBox">
                            <div class="questionBox surveyContents">
                                <div class="surveyText">질문</div>
                                <div class="surveyInputBox">
                                	<input type="text" class="form-control" placeholder="질문을 입력해주세요" name="survey_question" maxlength="100">
                                </div>
                            </div>
                            <div class="itemBox surveyContents">
                                <div class="surveyText">항목</div>
                                <div class="surveyInputBox">
                                	<input type="text" class="form-control"placeholder="항목을 입력해주세요" name="items" maxlength="100">
                                </div>
                                <div><img src="/images/board/remove.png" class="del_item_btn"/></div>
                            </div>
                            <div class="itemBox surveyContents">
                                <div class="surveyText">항목</div>
                                <div class="surveyInputBox">
                                	<input type="text" class="form-control"placeholder="항목을 입력해주세요" name="items" maxlength="100">
                                </div>
                                <div><img src="/images/board/plus.png" class="add_item_btn"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="buttons">
                        <button type="button" id="backBtn">취소</button>
                        <button id="frmBtn">등록</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="/js/board/write_contents_board.js" defer></script>
    <script src="/js/board/sideBar.js" defer></script>
</body>
</html>