<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
            <!-- 최상단에 버튼만 있는 부분 -->
            <div class="button_tab">    
                <button class="main_button" id="toWriteContentsBoardBtn">
                    <span>작성하기</span>
                </button>
            </div>

            <!-- 메뉴 리스트 있는 부분 -->
            <div class="menu_tab">

                <!-- 일반 메뉴 -->
                <div class="menu_item">
                    <img src="/images/commons/body_form/left_item/default/favorites.png" />
                    <span class="menu_item_text">중요 게시물</span>
                </div>
                
                <!-- 드롭 다운 메뉴 (추가 효과 없음) -->
                <div class="menu_list">
                    <div class="menu_list_button">
                        <div class="menu_list_button_drop">
                            <img src="/images/commons/body_form/left_item/default/drop.png" />
                        </div>
                        <span class="menu_list_button_text">전사 게시판</span>
                    </div>
                    <div class="menu_list_box">
                    	<c:forEach items="${all_list}" var="i">
                        	<div class="menu_list_item">
                            	<img src="/images/commons/body_form/left_item/default/company.png">
                            	<span class="menu_list_item_text">${i.board_title }</span>
                        	</div>
                        </c:forEach>
                    </div> 
                </div>

                <!-- 드롭 다운 메뉴 (리스트 내용에 마우스 올리면 설정 메뉴 나옴) -->
                <div class="menu_list">
                    <div class="menu_list_button">
                        <div class="menu_list_button_drop">
                            <img src="/images/commons/body_form/left_item/default/drop.png" />
                        </div>
                        <span class="menu_list_button_text">그룹 게시판</span>
                    </div>
                    <div class="menu_list_box">
                    	<c:forEach var="i" items="${group_list }">
                        	<div class="menu_list_item">
                            	<img src="/images/commons/body_form/left_item/default/company.png">
                            	<span class="menu_list_item_text">${i.board_title }</span>
                            	<div class="menu_list_item_plus">
                                	<img src="/images/commons/body_form/left_item/default/point.png">
                           		</div>
                        	</div>
                        </c:forEach>
                    </div> 
                </div>

                <!-- 일반 메뉴 -->
                <div class="menu_item" id="toEditBoardBtn">
                    <img class="menu_item_img" src="/images/commons/body_form/left_item/default/plus.png" />
                    <span class="menu_item_text">게시판 만들기</span>
                </div>
                <div class="menu_item">
                    <img class="menu_item_img" src="/images/board/setting.png" />
                    <span class="menu_item_text">게시판 관리</span>
                </div>
                
            </div>

</body>
</html>