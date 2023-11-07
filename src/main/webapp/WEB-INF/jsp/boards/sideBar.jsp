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
<div class="button_tab">
    <button class="main_button">
        <span>작성하기</span>
    </button>
</div>
<div class="menu_tab">
    <div class="menu_item">
        <img class="menu_item_img" src="/images/commons/body_form/left_item/default/favorites.png" />
        <span class="menu_item_text">중요 게시물</span>
    </div>
    <div class="menu_list">
        <div class="menu_list_button">
            <img src="/images/commons/body_form/left_item/default/drop.png" />
            <span class="menu_list_button_text">전사 게시판</span>
        </div>
        <c:forEach items="${list }" var="i">
        	<div class="menu_list_item">
                <img src="/images/commons/body_form/left_item/default/company.png">
                <span class="menu_list_item_text">메뉴 1번</span>
            </div>
        </c:forEach>
        <div class="menu_list_box">
            
            <div class="menu_list_item">
                <img src="/images/commons/body_form/left_item/default/company.png">
                <span class "menu_list_item_text">메뉴 2번</span>
            </div>
        </div> 
    </div>
    <div class="menu_list">
        <div class="menu_list_button">
            <img src="/images/commons/body_form/left_item/default/drop.png" />
            <span class="menu_list_button_text">그룹 게시판</span>
        </div>
        <div class="menu_list_box">
            <div class="menu_list_item select">
                <img src="/images/commons/body_form/left_item/default/company.png">
                <span class="menu_list_item_text">메뉴 1번</span>
            </div>
            <div class="menu_list_item">
                <img src="/images/commons/body_form/left_item/default/company.png">
                <span class="menu_list_item_text">메뉴 2번</span>
            </div>
        </div> 
    </div>
    <hr class="div_hr">
    <div class="menu_item">
        <img class="menu_item_img" src="/images/commons/body_form/left_item/default/plus.png" />
        <span class="menu_item_text">게시판 만들기</span>
    </div>
    <div class="menu_item">
        <img class="menu_item_img" src="/images/commons/body_form/left_item/default/trash.png" />
        <span class="menu_item_text">게시판 관리</span>
    </div>
</div>

</body>
</html>