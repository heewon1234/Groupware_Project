<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_edit_detail.css" />
</head>
<body>
	<div class="background"></div>
    	<div class="mkboard modal">
        	<div>사용자 추가하기</div>
        	<div class="member">
            	<div class="member_list">
                	<div class="member_list_box">
                    	<div class="member_list_box_header">직원 목록</div>
                    	<div class="member_list_box_body" id="member_list_box_body">
                        <div>
							<c:forEach items="${organizationList}" var="i">
								<div>
                                	<img src="/images/commons/body_form/left_item/default/plus.png" class="add_dept_btn"/>
                                	<input type="checkbox" class="organization_check"> ${i }
                            	</div>
							</c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="member_add">
                <div>
                    <div class="auth_member_list_header">직원 목록</div>
                    <div class="auth_member_list" id="auth_member_list">
                        
                    </div>
                    <div class="auth_member_reset">
                        <button id="auth_member_reset_btn">모두 취소</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="member_add_buttons">
            <button id="member_add_cancel">취소</button>
            <button id="member_add_complete">추가</button>
        </div>
    </div>
    <div class="top" id="top_container"></div>
    <div class="body_form">
        <div class="left_item" id="left_item">

        </div>
        <form id="frm" action="/mk_board/editBoardDetail" method="post">
        <input type="hidden" name="authorityList" id="authorityList">
        <input type="hidden" name="headerList" id="headerList">
        <div class="right_item">
            <div class="content_tab">
                <div class="contents_title">게시판 수정</div>
                <div class="name">
                    <div class="name_text">이름</div>
                    <div class="name_input" >
                        <input type="text" class="form-control" placeholder="게시판 이름을 입력하세요." name="board_title" id="board_title_input" style="width:500px;" value="${boardDetail.board_title }">
                    </div>
                    <div id='dupCheckDiv'></div>
                </div>
                <div class="board_type">
                    <div class="board_type_text">게시판 종류</div>
                    <div class="board_type_select">
                    	<c:choose>
                    		<c:when test="${ boardDetail.board_type eq 'all'}">
                    			<div><input type="radio" name="board_type" value="all" checked> 전사 게시판 - 조직에 속한 모든 사람과 공유 ( 조직없음 제외 )</div>
                        		<div><input type="radio" name="board_type" value="group"> 그룹 게시판 - 소유자 및 관리자가 지정한 사람과 공유</div>
                    		</c:when>
                    		<c:otherwise>
                    			<div><input type="radio" name="board_type" value="all" > 전사 게시판 - 조직에 속한 모든 사람과 공유 ( 조직없음 제외 )</div>
                        		<div><input type="radio" name="board_type" value="group" checked> 그룹 게시판 - 소유자 및 관리자가 지정한 사람과 공유</div>
                    		</c:otherwise>
                    	</c:choose>                        
                    </div>
                </div>
                <div class="name_type">
                    <div class="name_type_text">게시판 유형</div>
                    <div class="name_type_select">
                    	<c:choose>
							<c:when test="${boardDetail.name_type eq 'identity' }">
								<div><input type="radio" name="name_type" value="identity" checked> 일반형</div>
                        		<div><input type="radio" name="name_type" value="anonymous"> 익명형</div>
							</c:when>
							<c:otherwise>
								<div><input type="radio" name="name_type" value="identity"> 일반형</div>
                        		<div><input type="radio" name="name_type" value="anonymous" checked> 익명형</div>
							</c:otherwise>                    	
                    	</c:choose>
                    </div>
                </div>
                <div class="authority">
                    <div class="authority_text">사용자 및 권한</div>
                    <div class="authority_member_box">
                        <div class="authority_member_add">
                            <div class="authority_member_count"> 멤버 1 명</div>
                            <div class="authority_member_add_btn"> + 멤버 추가</div>
                        </div>           
                        <div class="authority_member_list_box">
                        	<input type="hidden" value="${prevBoardTitle }" id="prevBoardTitle" name="prevBoardTitle">
                            <div class="authority_member_list_info">
                                <div>이름</div>
                                <div>기본 권한 : 읽기</div>
                            </div>
                            <div class="authority_member_list" id="authority_member_list">
                            	<c:if test="${authMember.size()>0 }">
                            		<c:forEach items="${authMember }" var="i" varStatus="seq">
                            			<div class="auth_member" data-index="${seq.index }">
                                    		<div>${i.name }</div>
                                    			<div>
                                        		<div>
                                        			<c:choose>
                                        				<c:when test="${i.authority eq '읽기' }">
                                        					<input type="checkbox" class="auth_write">&nbsp;쓰기
                                        				</c:when>
                                        				<c:otherwise>
                                        					<input type="checkbox" checked>&nbsp;쓰기
                                        				</c:otherwise>
                                        			</c:choose>
                                        			
                                        		</div>
                                        		<div>x</div>
                                    		</div>
                               	 		</div>
                            		</c:forEach>
                            	</c:if>                           	 
                            </div>
                        </div>
                    </div>
                </div>
                 <div class="header">
                    <div class="header_text">말머리 설정</div>
                    <div class="header_type">
                    	<c:choose>
                    		<c:when test="${boardDetail.use_header eq 'false' }">
                    			<div><input type="radio" name="use_header" value="true" class="header_input_check"> 사용</div>
                        		<div><input type="radio" name="use_header" value="false" class="header_input_check" checked> 사용 안 함</div>
                    		</c:when>
                    		<c:otherwise>
                    			<div><input type="radio" name="use_header" value="true" class="header_input_check" checked> 사용</div>
                        		<div><input type="radio" name="use_header" value="false" class="header_input_check"> 사용 안 함</div>
                    		</c:otherwise>
                    	</c:choose>    
                    </div>
                    <c:choose>
                    	<c:when test="${boardDetail.use_header eq 'true' }"><div class="headerBox useHeaderBox" id="headerBox" style="display:block;"></c:when>
                    	<c:otherwise><div class="headerBox useHeaderBox" id="headerBox"></c:otherwise>
                    </c:choose>
                        <div class="header_add">
                            <div><input placeholder="&nbsp;말머리를 입력하세요" id="header_add_input"></div>
                            <div><img src="/images/commons/body_form/left_item/default/plus.png" id="header_add_btn"></div>
                        </div>
                        
						<div id="header_list_border">
							<c:if test="${headerList.size()>0 }">
								<c:forEach var="i" items="${headerList }" varStatus="seq">
									<div class="header_list_box" data-index="${seq.index }">
                            			<div class="header_list">
                                			<div><span>-&nbsp;</span>${i }</div>
                                			<div><img src="/images/board/del.png" class="header_del_btn"></div>
                            			</div>
                        			</div>
								</c:forEach>
							</c:if>
						</div>
                    </div>
                </div>
                <div class="buttons">
                    <button type="button" id="backBtn">취소</button>
                    <button id="editFormBtn">생성</button>
                </div>
                <input type="hidden" name="changeHeader" value="false" id="changeHeader">
            </div>
        </div>
        </form>
    </div>
    <script src="/js/board/mk_board.js" defer></script>
    <script src="/js/board/edit_board_detail.js" defer></script>
   	<script src="/js/board/sideBar.js" defer></script>
</body>
</html>