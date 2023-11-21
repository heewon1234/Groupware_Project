<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" type="text/css" href="/css/commons/body_form/left_form/body_form_default.css">
<script src="/js/commons/body_form/body_form_default.js"></script>
<style>
i {
	margin-right: 8px;
}
</style>
</head>

<body>
	<!-- 왼쪽 부분(메뉴) -->
	<div class="left_item">
		<div id="myInfo" class="mywork menu_list_item select">
			<img src="/images/commons/body_form/left_item/members/person-fill.svg"> <span class="menu_list_item_text">내 정보 관리</span>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
		$('.menu_list_item').click(function() {
			location.href = "/members/" + $(this).attr('id');
		});
	});

</script>
</html>