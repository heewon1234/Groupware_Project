<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/commons/body_form/left_form/body_form_default.css">
<script src="/js/commons/body_form/body_form_default.js"></script>
<script src="/js/insa/manage/common.js"></script>
</head>

<body>
	<!-- 왼쪽 부분(메뉴) -->
	<div class="left_item">
		<!-- 최상단에 버튼만 있는 부분 -->
		<div class="button_tab">
			<button class="main_button">
				<span>휴가 신청</span>
			</button>
		</div>

		<!-- 메뉴 리스트 있는 부분 -->
		<div class="menu_tab">
		
			<!-- 일반 메뉴 -->
			<div class="menu_item">
				<img class="menu_item_img" src="../imges/commons/body_form/left_item/default/trash.png" /> <span class="menu_item_text">시차출퇴근제 신청</span>
			</div>
		
			<!-- 구별선 -->
			<hr class="div_hr">

			
			<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
			<div class="menu_list">
				<div class="menu_list_button">
					<div class="menu_list_button_drop">
						<img src="../imges/commons/body_form/left_item/default/drop.png" />
					</div>
					<span class="menu_list_button_text">내 근무</span>
				</div>
				<div class="menu_list_box">
					<div id="work_leave" class="mywork menu_list_item ">
						<img src="../imges/commons/body_form/left_item/default/company.png"> <span class="menu_list_item_text">휴가/근무 </span>
					</div>
					<div id="work_current" class="menu_list_item mywork">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">근무 현황</span>
					</div>
					<div id="dpwork_current" class="menu_list_item mywork">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">부서 근무 현황</span>
					</div>
				</div>
			</div>
			<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
			
			<div class="menu_list">
				<div class="menu_list_button">
					<div class="menu_list_button_drop">
						<img src="../imges/commons/body_form/left_item/default/drop.png" />
					</div>
					<span class="menu_list_button_text">근무 관리</span>
				</div>
				<div class="menu_list_box">
					<div id="workPlan" class="menu_list_item work_plan">
						<img src="../imges/commons/body_form/left_item/default/company.png"> <span class="menu_list_item_text">근무계획 수립 </span>
					</div>
					<div id="workstatistics" class="menu_list_item work_manage">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">근무 통계</span>
					</div>
					<div id="workmanage" class="menu_list_item work_manage">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">전사 근무관리</span>
					</div>
				</div>
			</div>
			

			<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
			<div class="menu_list">
				<div class="menu_list_button">
					<div class="menu_list_button_drop">
						<img src="../imges/commons/body_form/left_item/default/drop.png" />
					</div>
					<span class="menu_list_button_text">조직/임직원 관리</span>
				</div>
				<div class="menu_list_box">
					<div id="org" class="menu_list_item manage">
						<img src="../imges/commons/body_form/left_item/default/company.png"> <span class="menu_list_item_text">조직 관리 </span>
					</div>
					<div id="members" class="menu_list_item manage">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">임직원 관리</span>
					</div>
					<div id="job" class="menu_list_item manage">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">직위/직무 설정</span>
					</div>
				</div>
			</div>

			<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
			<div class="menu_list">
				<div class="menu_list_button">
					<div class="menu_list_button_drop">
						<img src="../imges/commons/body_form/left_item/default/drop.png" />
					</div>
					<span class="menu_list_button_text">인사설정</span>
				</div>
				<div class="menu_list_box">
					<div class="menu_list_item manage">
						<img src="../imges/commons/body_form/left_item/default/company.png"> <span class="menu_list_item_text">기본 설정</span>
					</div>
					<div class="menu_list_item manage">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">근로형태</span>
					</div>
					<div class="menu_list_item manage">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">결재양식</span>
					</div>
					<div class="menu_list_item manage">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">결재양식</span>
					</div>
					<div class="menu_list_item manage">
						<img src="../imges/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">인사관리자</span>
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