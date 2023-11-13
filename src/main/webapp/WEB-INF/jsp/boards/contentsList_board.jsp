<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_favorite.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/commons/body_form/body_form_default.js" defer></script>
</head>
<body>
	<div class="top">TOP</div>
    <div class="body_form">
        <div class="left_item" id="left_item">
            
        </div>
        <div class="right_item">
            <div class="content_tab">
                <div class="searchBox">
                    <div class="searchIconBox">
                        <img src="/images/board/search.png">
                    </div>
                    <div class="searchTextBox">
                        <input type="text" placeholder="게시글 검색">
                    </div>
                </div>
                <div class="board_list">
                    <div class="board_top">
                        <select name="board_title">
                            <option value="all">모든 게시글</option>
                            <option value="mine">내가 쓴 게시글</option>
                        </select>
                    </div>
                    <div class="board_body">
                    	<c:choose>
                    		<c:when test="${boardContentsList.size()==0 }">
                    			<div class="board_contents_list" style="display:flex;align-items:center;justify-content:center;">게시글 없음</div>
                    		</c:when>
                    		<c:otherwise>
                    			<c:forEach var="i" items="${boardContentsList}">
                    				<div class="board_contents_list">
                            			<div class="favorite" data-index='${i.seq }'>
                            				<c:choose>
                            					<c:when test="${i.favorite }">
                            						<img src="/images/commons/body_form/left_item/default/favorites.png" >
                            					</c:when>
                            					<c:otherwise>
                            						<img src="/images/board/notFavorite.png" >
                            					</c:otherwise>
                            				</c:choose> 	
                            			</div>
                            			<div class="board_contents_title">
                            				<c:choose>
                            					<c:when test="${board_title == '중요게시물'}">
                            						<a href="/board/toContentsBoard?seq=${i.seq }&board_title=${i.board_title}">
                            							<c:if test="${i.header==''}">
                            								<span>${i.header }&nbsp;&nbsp;</span>
                            							</c:if>
                            							${i.title }
                            						</a>
                            					</c:when>
                            					<c:otherwise>
                            						<a href="/board/toContentsBoard?seq=${i.seq }">
                            							<c:if test="${i.header==''}">
                            								<span>${i.header }&nbsp;&nbsp;</span>
                            							</c:if>
                            							${i.title }
                            						</a>
                            					</c:otherwise>
                            				</c:choose>
                            			</div>
                           				<div class="board_contents_writer">${i.writer }</div>
                            			<div class="view_count">${i.view_count }</div>
                          	  			<div class="board_title">${i.board_title}</div>
                            			<div class="board_contents_write_date">${i.write_date }</div>
                        			</div>
                    			</c:forEach>
                    		</c:otherwise>
                    	</c:choose>
                    </div>
                </div>
                <div class="board_bottom">

                </div>
            </div>
        </div>
    </div>
	<script src="/js/board/sideBar.js" defer></script>
    <script src="/js/board/contentsList_board.js" defer></script>
</body>
</html>