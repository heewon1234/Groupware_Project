<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_edit.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/commons/body_form/body_form_default.js" defer></script>
</head>
<body>
	
    <div class="top" id="top_container"></div>
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
                        <div class="board_index_check"></div>
                        <div class="board_index_board_type">종류</div>
                        <div class="board_index_name_type">유형</div>
                        <div class="board_index_board_title">게시판 이름</div>
                        <div class="board_index_board_admin">관리자</div>
                        <div class="board_index_mk_date">만든 날짜</div>
                        <div class="buttons">관리</div>
                    </div>
                	<c:choose>
                		<c:when test="${ boardList.size()==0}">
                			<div class="board_index">게시판이 존재하지 않습니다</div>
                		</c:when>
                		<c:otherwise>
                			<c:forEach items="${boardList }" var="i">
                				<div class="board_list">
                        			<div class="board_index_check">${i.seq }</div>
                        			<div class="board_index_board_type">
                        				<c:choose>
                        					<c:when test="${i.board_type eq 'all'  }">전사</c:when>
                        					<c:otherwise>그룹</c:otherwise>
                        				</c:choose>
                        			</div>
                        			<div class="board_index_name_type">
                        				<c:choose>
                        					<c:when test="${i.name_type eq 'identity'  }">일반형</c:when>
                        					<c:otherwise>익명형</c:otherwise>
                        				</c:choose>
                        			</div>
                        			<div class="board_index_board_title">${i.board_title }</div>
                        			<div class="board_index_board_admin">${i.id }</div>
                        			<div class="board_index_mk_date">${i.mk_date }</div>
                        			<div class="buttons">
                          				<button class="updateBtn">수정</button>
                            			<button class="delBtn">삭제</button>
                       				</div>
                    			</div>
                			</c:forEach>
                		</c:otherwise>
                	</c:choose>
                </div>
            </div>
        </div>
    </div>	
    <script src="/js/board/sideBar.js" defer></script>
    <script src="/js/board/edit_board.js" defer></script>
             
</body>
</html>