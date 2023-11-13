<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_contents.css" />
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
            	<c:if test="${boardContents.notice=='true'}">
                	<div class="noticeBox">
                		<button>공지</button>             
               	 	</div>
                </c:if>
               <div class="board_contents_info">
                    <div class="favorite"><img src="/images/commons/body_form/left_item/default/favorites.png" /></div>
                    <div class="profile">
                        <img src="/images/commons/body_form/left_item/default/information.png" />
                    </div>
                    <div class="contents_info">
                        <div class="board_contents_title">${boardContents.title}</div>
                        <div class="contents_info_datail">
                        	<div id="boardSeq">글번호 ${boardContents.seq}</div> |
                            <div>${boardContents.writer}</div> |
                            <div>${board_title}</div> |
                            <div>조회수 ${boardContents.view_count }</div>
                        </div>
                    </div>
                    <div class="contents_write_date">
                    	<div>${boardContents.write_date }</div>
                    	<div><button id="contentsDelBtn">삭제</button></div>
                    </div>
                </div>
                <div class="contents">
                   ${boardContents.contents }
                   <div class="survey_box">
                        <div class="survey_question">${boardContents.survey_question }</div>
                        <hr>
                        <div class="survey_items">
                            <div class="survey_item">
                                <input type="radio" name="item"> 항목1
                            </div>
                            <div class="survey_item">
                                <input type="radio" name="item"> 항목2
                            </div>
                            <div class="survey_item">
                                <input type="radio" name="item"> 항목2
                            </div>
                        </div>
                        <hr>
                        <div class="survey_btns">
                            <button>결과보기</button>
                            <button>투표</button>
                        </div>
                    </div>
                </div>
                <div class="buttons">
                 	<button id="backBtn">뒤로가기</button>
                    <button id="contentsUpdateBtn">수정</button> 
                </div>
                <div class="reply_box">
                	<form action="/reply/insertReply" method="post">
                		<input type="hidden" name="parent_seq" value='${boardContents.seq }'>
                    	<div class="input_reply_box">
                      		<div class="reply_profile">
                       	    	<img src="/images/commons/body_form/left_item/default/information.png" />
                        	</div>
                        	<div class="reply_input">
                            	<textarea placeholder="댓글을 입력하세요" name="contents"></textarea>
                        	</div>
                        	<div class="reply_button">
                            	<button>등록</button>
                        	</div>
                    	</div>
                    </form>
                    <div class="reply_list_box">
                    	<c:choose>
                    		<c:when test="${replyList.size()>0 }"> 
                    			<form action="/reply/updateReply" method="post">  
                    				<input type="hidden" name="parent_seq" value='${boardContents.seq }'>               			
                    				<c:forEach items="${replyList }" var="i">
										<div class="reply_list" data-index='${i.seq }'>
    										<div class="reply_list_info">
    											<input type="hidden" name="seq" value='${i.seq }'>
        										<div>${i.writer }</div>
        										<div>${i.write_date }</div>
    										</div>
    										<div class="reply_list_contents">
        										${i.contents }
    										</div>
    										<div class="reply_list_edit_buttons">
    											<button class="replyDelBtn" type="button">삭제</button>
        										<button class="replyUpdateBtn" type="button">수정</button>
        										<button class="updateCancelBtn" type="button">취소</button>
        										<button class="updateSuccessBtn">수정완료</button>
    										</div>
										</div>	
									</c:forEach>
								</form> 
                    		</c:when>
                    		<c:otherwise>
                    			<div class="reply_list" text-align="center">
                    				댓글 없음
                    			</div>
                    		</c:otherwise>
                    	</c:choose>
						
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="/js/board/sideBar.js" defer></script>
    <script src="/js/board/contents_board.js" defer></script>
</body>
</html>