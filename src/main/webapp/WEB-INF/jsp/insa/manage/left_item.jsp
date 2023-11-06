<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div class="button_tab">
		<button class="main_button">
			<span>작성하기</span>
		</button>
	</div>
	<div class="menu_tab">
		<div class="menu_list">
			<div class="menu_list_button">
				<img src="/images/commons/drop.png" /> <span class="menu_list_button_text">조직/임직원 관리</span>
			</div>
			<div class="menu_list_box">
				<div id="org" class="menu_list_item">
					<img src="/images/commons/img_4.png"> <span class="menu_list_item_text">조직 관리</span>
				</div>
				<div id="members" class="menu_list_item">
					<img src="/images/commons/img_4.png"> <span class="menu_list_item_text">임직원 관리</span>
				</div>
				<div id="job" class="menu_list_item">
					<img src="/images/commons/img_4.png"> <span class="menu_list_item_text">직위/직무 설정</span>
				</div>
			</div>
		</div>
	</div>
	<script>	
		$(document).ready(function() {
			$("#${selectItem}").addClass("select");
		});
		
		$(".menu_list_item").on("click", function() {
			location.href = "/insa/manage/"+$(this).attr("id");
		});
	</script>
</body>
</html>