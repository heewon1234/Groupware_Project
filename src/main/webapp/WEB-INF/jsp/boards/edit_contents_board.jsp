<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="top">TOP</div>
    <div class="body_form">
        <div class="left_item" id="left_item">

        </div>
        <div class="right_item">
            <div class="content_tab">
                <form action="/board/insertBoardContents" id="frm" method="post">
                    <div class="board_title_box">
                        <div class="board_title_text">게시판</div>
                        <div>
                            <select name="board_title" id="board_title">
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
                            <input type="text" placeholder="제목을 입력하세요" name="title">
                        </div>
                        <div>
                            <input type="checkbox" name="notice" value="false"> &nbsp;공지로 등록
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
                            <div><input type="radio" name="useServey" value="true"> 사용</div>
                            <div><input type="radio" name="useServey" value="false" checked> 사용 안 함</div>
                        </div>
                        <div id="surveyBox">
                            <div class="questionBox surveyContents">
                                <div class="surveyText">질문</div>
                                <div class="surveyInputBox"><input type="text" placeholder="질문을 입력해주세요" name="servey_question"></div>
                            </div>
                            <div class="itemBox surveyContents">
                                <div class="surveyText">항목</div>
                                <div class="surveyInputBox"><input type="text" placeholder="항목을 입력해주세요" name="items"></div>
                                <div><img src="/images/commons/body_form/left_item/default/plus.png" class="add_item_btn"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="buttons">
                        <button type="button">취소</button>
                        <button id="frmBtn">등록</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
	<script src="/js/board/sideBar.js" defer></script>
    <script src="/js/board/edit_contents_board.js" defer></script>
</body>
</html>