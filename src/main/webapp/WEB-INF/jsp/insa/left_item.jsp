<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" type="text/css" href="/css/commons/body_form/left_form/body_form_default.css">
<script src="/js/commons/body_form/body_form_default.js"></script>
<script src="/js/insa/manage/common.js"></script>
<style>
i{margin-right: 8px;}
</style>
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
		
			<!-- 구별선 -->
			<hr class="div_hr">

			
			<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
			<div class="menu_list">
				<div class="menu_list_button">
					<div class="menu_list_button_drop">
						<img src="/images/commons/body_form/left_item/default/drop.png" />
					</div>
					<span class="menu_list_button_text">내 근무</span>
				</div>
				<div class="menu_list_box">
					<div id="work_leave" class="mywork menu_list_item ">
						<i class="fa-solid fa-calendar"></i> <span class="menu_list_item_text">휴가/근무 </span>
					</div>
					<div id="work_current" class="menu_list_item mywork">
						<i class="fa-solid fa-calendar-days"></i> <span class="menu_list_item_text">근무 현황</span>
					</div>
				</div>
			</div>
			<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
			
			<div class="menu_list">
				<div class="menu_list_button">
					<div class="menu_list_button_drop">
						<img src="/images/commons/body_form/left_item/default/drop.png" />
					</div>
					<span class="menu_list_button_text">근무 관리</span>
				</div>
				<div class="menu_list_box">
					<div id="workPlan" class="menu_list_item work_plan">
						<i class="fa-solid fa-calendar-days"></i> <span class="menu_list_item_text">근무계획 수립 </span>
					</div>
					<div id="workstatistics" class="menu_list_item work_manage" style="padding-left: 30px;">
						<img src="/images/commons/body_form/left_item/insa/pie-chart.svg" style="margin-right: 6px;"> <span class="menu_list_item_text">근무 통계</span>
					</div>
					<div id="workmanage" class="menu_list_item work_manage">
						<i class="fa-solid fa-building"></i> <span class="menu_list_item_text">전사 근무관리</span>
					</div>
				</div>
			</div>
			

			<c:if test="${userDTO.organization eq '관리부'}">
				<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
				<div class="menu_list">
					<div class="menu_list_button">
						<div class="menu_list_button_drop">
							<img src="/images/commons/body_form/left_item/default/drop.png" />
						</div>
						<span class="menu_list_button_text">조직/임직원 관리</span>
					</div>
					<div class="menu_list_box">
						<div id="org" class="menu_list_item manage">
							<img src="/images/commons/body_form/left_item/insa/people.svg"> <span class="menu_list_item_text">조직 관리 </span>
						</div>
						<div id="members" class="menu_list_item manage">
							<img src="/images/commons/body_form/left_item/insa/person-fill.svg"> <span class="menu_list_item_text">임직원 관리</span>
						</div>
						<div id="job" class="menu_list_item manage">
							<img src="/images/commons/body_form/left_item/insa/id-badge.svg"> <span class="menu_list_item_text">직위/직무 설정</span>
						</div>
					</div>
				</div>
			</c:if>

		</div>
	</div>
	<script>
		$("#${selectItem}").addClass('select');
	</script>
</body>
</html>