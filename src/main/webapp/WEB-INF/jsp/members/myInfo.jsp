<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<!-- Kakao Post API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/members/myInfo.css">
</head>

<body>
	<div class="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<div class="title">
					<h3>내 정보 관리</h3>
					<hr class="title_hr">
				</div>
				<div class="contents">
					<form action="/members/updateMember" method="post">
						<table>
							<tbody>
								<div id="profileImage-div" style="width: 150px; height: 150px">
									<img src="${userDTO.profile_image }">
								</div>
								<input type="text" id="profile_image" name="profile_image" value="${userDTO.profile_image }" hidden>
								<tr>
									<th>프로필 사진</th>
									<td><input type="file" id="input-file"></td>
								</tr>
								<tr>
									<th>이름</th>
									<td>${userDTO.name }</td>
								</tr>
								<tr>
									<th>아이디</th>
									<td>${userDTO.id }</td>
								</tr>
								<tr>
									<th>비밀번호</th>
									<td><input id="pw" name="pw" type='password'></td>
								</tr>
								<tr>
									<th>비밀번호 재입력</th>
									<td><input id="repw" type='password'></td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td><input type='number' name="phone" value="${userDTO.phone }" maxlength="11"></td>
								</tr>
								<tr>
									<th>email</th>
									<td><input type='text' name="email" value="${userDTO.email }"></td>
								</tr>
								<tr>
									<th>우편번호</th>
									<td><input type='text' id="postcode" name='zipcode' value="${userDTO.zipcode }"> <input type="button" class="apply_btn" id="postcode_btn" value="우편번호 찾기" onclick="execDaumPostcode()"></td>
								</tr>
								<tr>
									<th>주소</th>
									<td><input type='text' id="address" name='address1' value="${userDTO.address1 }"></td>
								</tr>
								<tr>
									<th>상세주소</th>
									<td><input type='text' id="detailAddress" name='address2' value="${userDTO.address2 }"></td>
								</tr>
							</tbody>
						</table>
						<button type="submit" class="button_apply">저장</button>
						<button type="button" id="back_btn" class="button_cancel">뒤로가기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/members/myInfo.js"></script>
</body>
</html>