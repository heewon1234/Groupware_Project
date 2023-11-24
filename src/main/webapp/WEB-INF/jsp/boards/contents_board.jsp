<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_contents.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
</head>
<body>
	<div class="top" id="top_container"></div>
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
                    <div class="profileDiv">
                    	<c:choose>
                    		<c:when test="${empty writerProfile}">
                    			<img src="/images/commons/person-circle.svg" />
                    		</c:when>
                    		<c:otherwise>
                    			<img src="${writerProfile }" style="border-radius:100%; border:1px solid black;">
                    		</c:otherwise>
                    	</c:choose>
                    </div>
                    <div class="contents_info">
                        <div class="board_contents_title">
                        	<c:if test="${empty boardContents.header}">
                            	<span>${boardContents.header }&nbsp;&nbsp;</span>
                            </c:if>
                        	${boardContents.title}
                        </div>
                        <div class="contents_info_datail">
                        	<div id="boardSeq">글번호 ${boardContents.seq}</div> |
                            <div>
                            	<c:choose>
                            		<c:when test="${name_type eq 'identity' }">
                            			${writerName }
                            		</c:when>
                            		<c:otherwise>
                            			익명
                            		</c:otherwise>
                            	</c:choose>
                            </div> |
                            <div>${board_title}</div> |
                            <div>조회수 ${boardContents.view_count }</div>
                        </div>
                    </div>
                    <div class="contents_write_date">
                    	<div>${boardContents.write_date }</div>
                    	<div><c:if test="${boardContents.writer eq loginId or auth}"><button id="contentsDelBtn">삭제</button></c:if></div>
                    </div>
                </div>
                <div class="contents-file">
					<div>첨부 파일 목록</div>
						<div>
							<c:choose>
								<c:when test="${fileList.size()>0 }">
									<c:forEach var="i" items="${fileList }">
										<div id="fileListDiv">
											<i class="fa-regular fa-file fa-sm"></i>
											<a href="/file/download?oriName=${i.ori_name }&sysName=${i.sys_name}">${i.ori_name }</a>
										</div>
									</c:forEach>	
								</c:when>
								<c:otherwise>
									<i class="fa-regular fa-file fa-sm"></i>
								 	첨부파일이 없습니다.
								</c:otherwise>
						</c:choose>		
					</div>
				</div>
                <div class="contents" data-vote="${isVote }">
                   ${boardContents.contents }
                   	<c:if test="${not empty boardContents.survey_question or surveyList.size()>0}">
                   		<div class="survey_box" id="surveyVoteBox">
                       		<div class="survey_question">${boardContents.survey_question }</div>
                        	<hr>
                        	<div class="survey_items">
                        		<c:forEach items="${surveyList }" var="i">
                        			<div class="survey_item">
                                		<input type="radio" name="item" class="survey_item_input" data-index="${i.seq }"> ${i.items }
                           			</div>
                        		</c:forEach>                      
                        	</div>
                        	<hr>
                        	<div class="survey_btns">
                            	<button id="showResultBtn">결과보기</button>
                           		<button id="voteSurveyBtn">투표</button>
                        	</div>
                    	</div>
                    	<div class="survey_box" id="surveyResultBox">
                        	<div class="survey_question">질문</div>
                        	<hr>
                       		<div class="survey_items" id="surveyResult">
                       
                        	</div>
                    	</div>
                   	</c:if>        
                </div>
                <div class="buttons">
                 	<button id="backBtn">뒤로가기</button>
                    <c:if test="${boardContents.writer eq loginId or auth}"><button id="contentsUpdateBtn">수정</button></c:if>
                </div>
                <div class="reply_box">
                	<form action="/reply/insertReply" method="post">
                		<input type="hidden" name="parent_seq" value='${boardContents.seq }'>
                    	<div class="input_reply_box">
                      		<div class="reply_profile">
                      			<c:choose>
                      				<c:when test="${empty myProfile }">
                      					<img src="/images/commons/person-circle.svg" />
                      				</c:when>
                      				<c:otherwise>
                      					<img src="${myProfile}" style="border-radius:100%; border:1px solid black;"/>
                      				</c:otherwise>
                      			</c:choose>
                       	    	
                        	</div>
                        	<div class="reply_input">
                            	<textarea placeholder="댓글을 입력하세요" name="contents" maxlength="200"></textarea>
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
        										<div>
        											<c:choose>
                            							<c:when test="${name_type eq 'identity' }">
                            								${i.name }
                            							</c:when>
                            							<c:otherwise>
                            								익명
                            							</c:otherwise>
                            						</c:choose>
        										</div>
        										<div>${i.write_date }</div>
    										</div>
    										<div class="reply_list_contents">
        										${i.contents }
    										</div>
    										<div class="reply_list_edit_buttons">
    											<c:if test="${i.writer eq loginId or auth}">
    												<button class="replyDelBtn" type="button">삭제</button>
        											<button class="replyUpdateBtn" type="button">수정</button>
    											</c:if>
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
                    <div id="replyFooter"></div>
                </div>
            </div>
        </div>
    </div>
    <script src="/js/board/sideBar.js" defer></script>
    <script src="/js/board/contents_board.js" defer></script>
	<script>
	$(document).ready(function(){
		let replyListSize = ${replyListSize};
		if(replyListSize>0){
			let replyFooter = document.getElementById("replyFooter");
			let recordTotalCount = replyListSize;
			let recordCountPerPage = ${recordCountPerPage};
			let naviCountPerPage = ${naviCountPerPage};
			let currentReplyPage = ${currentReplyPage};
			
			let pageTotalCount = 0;

			if((recordTotalCount%recordCountPerPage)>0){
				pageTotalCount = Math.floor( recordTotalCount/recordCountPerPage ) + 1;
			} else {
				pageTotalCount =  recordTotalCount/ recordCountPerPage;
			}

			if(currentReplyPage<1){currenReplyPage=1;}
			else if(currentReplyPage>pageTotalCount){currentReplyPage=pageTotalCount;}
			
			let startNavi = Math.floor(( currentReplyPage - 1 ) / naviCountPerPage) * naviCountPerPage + 1;
			let endNavi = startNavi + naviCountPerPage - 1;
			if(endNavi > pageTotalCount) {endNavi = pageTotalCount;}

			let needPrev = true;
			let needNext = true;
			
			if( startNavi == 1 ) { needPrev=false; }
			if( endNavi == pageTotalCount ) { needNext = false; }
			
			if(needPrev) {
				$("#replyFooter").append("<a href='/board/toContentsBoard?rNum="+(startNavi-1)+"&seq=${boardContents.seq}'>"+ "<<"+ "</a>");
			}
			for(let i = startNavi; i<=endNavi; i++) {
				$("#replyFooter").append("<a href='/board/toContentsBoard?rNum="+ i +"&seq=${boardContents.seq}' class='naviNum'>" + i + "</a>");
			}
			if(needNext) {$("#replyFooter").append("<a href='/board/toContentsBoard?rNum="+(endNavi+1)+"&seq=${boardContents.seq}'>"+ ">>"+ "</a>");}
			
			let childNum = currentReplyPage;
			if(childNum>10){childNum = childNum-9;}
			$(".naviNum:nth-child("+childNum+")").css("color","red").css("text-decoration","underline");
		}
	});
	
	</script>
</body>
</html>