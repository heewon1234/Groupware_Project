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
<link rel="stylesheet" type="text/css" href="/css/insa/myInfo.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/common.css">
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
									<td><input name="pw" type='password'></td>
								</tr>
								<tr>
									<th>비밀번호 확인</th>
									<td><input type='password'></td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td><input type='text' name="phone" value="${userDTO.phone }"></td>
								</tr>
								<tr>
									<th>email</th>
									<td><input type='text' name="email" value="${userDTO.email }"></td>
								</tr>
								<tr>
									<th>우편번호</th>
									<td>
										<input type='text' id="postcode" name='zipcode' value="${userDTO.zipcode }">
										<input type="button" class="apply_btn" id="postcode_btn" value="우편번호 찾기" onclick="execDaumPostcode()">
									</td>
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
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/insa/myInfo.js"></script>
	<script>
	// Kakao Post API
	    function execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function (data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	
	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById("address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("detailAddress").focus();
	            }
	        }).open();
	    }
	</script>
</body>
</html>