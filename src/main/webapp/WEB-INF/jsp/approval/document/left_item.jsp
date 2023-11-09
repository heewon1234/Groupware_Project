<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/commons/body_form/left_form/body_form_default.css">
<script src="/js/commons/body_form/body_form_default.js"></script>
<script src="/js/approval/document/common.js"></script>
</head>

<body>
	<!-- 왼쪽 부분(메뉴) -->
	<div class="left_item">
		<!-- 최상단에 버튼만 있는 부분 -->
		<div class="button_tab">
			<button class="main_button">
				<span>작성하기</span>
			</button>
		</div>

		<!-- 메뉴 리스트 있는 부분 -->
		<div class="menu_tab">

			<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
			<div class="menu_list">
				<div class="menu_list_button">
					<div class="menu_list_button_drop">
						<img src="../imges/commons/body_form/left_item/default/drop.png" />
					</div>
					<span class="menu_list_button_text">진행 중인 문서</span>
				</div>
				<div id="lists" class="menu_list_box">
					<div id="all"" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/company.png"> <span class="menu_list_item_text">전체</span>
					</div>
					<div id="wait" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">대기</span>
					</div>
					<div id="" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">확인</span>
					</div>
					<div id="" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">예정</span>
					</div>
					<div id="" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">진행</span>
					</div>
				</div>
			</div>

			<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
			<div class="menu_list">
				<div class="menu_list_button">
					<div class="menu_list_button_drop">
						<img src="../imges/commons/body_form/left_item/default/drop.png" />
					</div>
					<span class="menu_list_button_text">문서함</span>
				</div>
				<div class="menu_list_box">
					<div id="" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/company.png"> <span class="menu_list_item_text">전체</span>
					</div>
					<div id="" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">기안</span>
					</div>
					<div id="" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">결재</span>
					</div>
					<div id="" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">수신</span>
					</div>
					<div id="" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">회람/참조</span>
					</div>
					<div id="" class="menu_list_item">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">반려</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#${selectItem}").addClass('select');
	</script>
</body>
</html>