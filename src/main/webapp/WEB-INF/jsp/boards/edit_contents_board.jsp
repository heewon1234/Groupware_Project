<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>  
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_edit_contents.css" />
    <script src="/js/commons/body_form/body_form_default.js" defer></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
</head>
<body>
	<div class="top">TOP</div>
    <div class="body_form">
        <div class="left_item" id="left_item">

        </div>
        <div class="right_item">
            <div class="content_tab">
                <form action="/board/updateContentsBoard" id="frm" method="post" enctype="multipart/form-data">
                	<input type="hidden" name="seq" value="${seq }">
                	<input type="hidden" name="prevContents" value='${boardContents.contents }'>
                    <div class="board_title_box">
                        <div class="board_title_text">게시판</div>
                        <div>
                            <select name="board_title" id="board_title">
                                <c:forEach items="${boardList }" var="i">
                                	<c:choose>
                                		<c:when test="${board_title eq i }">
                                			<option selected>${i }</option>
                                		</c:when>
                                		<c:otherwise>
                                			<option>${i }</option>
                                		</c:otherwise>
                                	</c:choose>         	
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <select id="header" name="header">
                                <c:forEach var="i" items="${headerList }"> 
                                	<c:choose>
                                		<c:when test="${boardContents.header eq i }">
                                			<option selected value="${i }">${i }</option>
                                		</c:when>
                                		<c:otherwise>
                                			<option value="${i }">${i }</option>
                                		</c:otherwise>
                                	</c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div></div>
                    </div>
                    <div class="board_contents_title_box">
                        <div class="info_text_title">제목</div>
                        <div>
                            <input type="text" placeholder="제목을 입력하세요" name="title" value='${boardContents.title }'>
                        </div>
                        <div>
                        	<c:choose>
                        		<c:when test="${boardContents.notice eq 'true'}">
                        			<input type="checkbox" name="notice" value="true" checked> &nbsp;공지로 등록
                        		</c:when>
                        		<c:otherwise>
                        			<input type="checkbox" name="notice" value="false"> &nbsp;공지로 등록
                        		</c:otherwise>
                        	</c:choose>
                            
                        </div>
                    </div>
                    <div class="board_contents_file_box">
                        <div class="file_box_text">파일 첨부 &nbsp;&nbsp;<span id="fileAddBtn">+</span></div>
                        <div class="file_box_input_file" id="file_box_input_file">

                        </div>
                    </div>
                    <input type="hidden" name="inputFileDelBtn" id="inputFileDelBtn">
                    <div class="contents-file">
						<div>첨부 파일 목록</div>
						<div>
							<c:choose>
								<c:when test="${fileList.size()>0 }">
									<c:forEach var="i" items="${fileList }">
										<div class="fileListDiv" data-type="${i.type }">
											<i class="fa-regular fa-file fa-sm"></i>
											<a>${i.ori_name }</a>
											<c:if test="${i.type eq 'input' }">
												&nbsp;&nbsp;<a href="#" class="inputFileDelBtn" data-name="${i.sys_name }">X</a>
											</c:if>
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
                    <div class="board_contents_box">
                        <textarea id="summernote" name="contents">${boardContents.contents }</textarea>
                    </div>
                    <div class="board_survey">
                        <div>설문 여부</div>
                        <div>
                        	<c:choose>
                        		<c:when test="${empty boardContents.survey_question}">
                        			<div><input type="radio" name="useSurvey" value="true"> 사용</div>
                            		<div><input type="radio" name="useSurvey" value="false" checked> 사용 안 함</div>
                        		</c:when>
                        		<c:otherwise>
                        			<div><input type="radio" name="useSurvey" value="true" checked> 사용</div>
                            		<div><input type="radio" name="useSurvey" value="false"> 사용 안 함</div>
                        		</c:otherwise>
                        	</c:choose>
                        </div>
                        <c:choose>
                        	<c:when test="${not empty boardContents.survey_question and !isExistVote}">
                        		<div id="surveyBox">
                            		<div class="questionBox surveyContents">
                                		<div class="surveyText">질문</div>
                                		<div class="surveyInputBox"><input type="text" placeholder="질문을 입력해주세요" name="survey_question" value="${boardContents.survey_question }"></div>
                            		</div>
                            		<c:forEach items="${surveyItemList }" var="i">
                            			<div class="itemBox surveyContents">
                                			<div class="surveyText">항목</div>
                                			<div class="surveyInputBox"><input type="text" placeholder="항목을 입력해주세요" name="items" value="${i.items }"></div>
                                			<div><img src="/images/commons/body_form/left_item/default/plus.png" class="add_item_btn"/></div>
                            			</div>
                            		</c:forEach>  	
                        		</div>
                        	</c:when>
                        	<c:when test="${empty boardContents.survey_question}">
                        		<div id="surveyBox" class="notUseSurveyBox">
                            		<div class="questionBox surveyContents">
                                		<div class="surveyText">질문</div>
                                		<div class="surveyInputBox"><input type="text" placeholder="질문을 입력해주세요" name="survey_question"></div>
                            		</div>
                            		<div class="itemBox surveyContents">
                                		<div class="surveyText">항목</div>
                                		<div class="surveyInputBox"><input type="text" placeholder="항목을 입력해주세요" name="items"></div>
                                		<div><img src="/images/commons/body_form/left_item/default/plus.png" class="add_item_btn"/></div>
                            		</div>
                            		<div class="itemBox surveyContents">
                               			<div class="surveyText">항목</div>
                                		<div class="surveyInputBox"><input type="text" placeholder="항목을 입력해주세요" name="items"></div>
                                		<div><img src="/images/commons/body_form/left_item/default/plus.png" class="add_item_btn"/></div>
                            		</div>
                        		</div>
                        	</c:when>
                        </c:choose>                                           
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