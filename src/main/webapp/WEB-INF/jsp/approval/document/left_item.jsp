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
<!-- font-awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
	i {
		margin-right : 8px;
	}
	.count {
		margin : 0 0 3px 5px;
	}
</style>
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
						<img src="/images/commons/body_form/left_item/default/drop.png">
					</div>
					<span class="menu_list_button_text">진행 중인 문서</span>
				</div>
				<div id="lists" class="menu_list_box">
					<div id="all"" class="menu_list_item">
						<img src="/images/commons/body_form/left_item/approval/file-text.svg"> <span class="menu_list_item_text">전체</span> <span class="count">${allCount }</span>
					</div>
					<div id="wait" class="menu_list_item">
						<img src="/images/commons/body_form/left_item/approval/hourglass.svg"> <span class="menu_list_item_text">대기</span> <span class="count">${waitCount }</span>
					</div>
					<div id="complete" class="menu_list_item">
						<img src="/images/commons/body_form/left_item/approval/check-circle.svg"> <span class="menu_list_item_text"> <span class="menu_list_item_text">완료</span> <span class="count">${completeCount }</span>
					</div>
					<div id="progress" class="menu_list_item">
						<i class="fa-solid fa-spinner"></i> <span class="menu_list_item_text">진행</span> <span class="count">${processCount }</span>
					</div>
				</div>
			</div>

			<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
			<div class="menu_list">
				<div class="menu_list_button">
					<div class="menu_list_button_drop">
						<img src="/images/commons/body_form/left_item/default/drop.png">
					</div>
					<span class="menu_list_button_text">문서함</span>
				</div>
				<div id="box" class="menu_list_box">
					<div id="every" class="menu_list_item">
						<img src="/images/commons/body_form/left_item/approval/file-text.svg"> <span class="menu_list_item_text">전체</span> <span class="count">${everyCount }</span>
					</div>
					<div id="pending" class="menu_list_item">
						<img src="/images/commons/body_form/left_item/approval/file-text.svg"> <span class="menu_list_item_text">미결</span> <span class="count">${pendingCount }</span>
					</div>
					<div id="approve" class="menu_list_item">
						<img src="/images/commons/body_form/left_item/approval/checklist.svg"> <span class="menu_list_item_text">결재</span> <span class="count">${approveCount }</span>
					</div>
					<div id="return" class="menu_list_item">
						<img src="/images/commons/body_form/left_item/approval/circle-slash.svg"> <span class="menu_list_item_text">반려</span> <span class="count">${returnCount }</span>
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