<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>결재 작성하기</title>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/approval/document/view.css">
<link rel="stylesheet" type="text/css" href="/css/commons/body_form/left_form/body_form_default.css">
</head>
<body>

	<div id="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<form action="/approval/document/updateStatus" method="post">
					<div id="submit_div">
						<input type='text' hidden name="doc_id" value="${docId }">
						<input type='text' hidden name="userId" value="${userDTO.id }">
						<c:forEach var="manager" items="${managerList}">
                            <c:if test="${manager.id eq userDTO.id}">
								<button type="submit" class="button_apply">결재하기</button>
							</c:if>
                       	</c:forEach>
                       	<c:if test="${app.approval_status eq '대기'}">
							<button type="button" id="delete_btn" class="button_apply">삭제하기</button>
						</c:if>
						<button type="button" id="back_btn" class="button_apply">
							뒤로가기
						</button>
					</div>

					<h4>결재선</h4>
					<table id="approvalTable" class="outTable" border='1'>
						<tbody>
							<tr>
								<th>신청</th>
								<td>
									<table class="innerTable" border='1'>
										<tr>
											<c:forEach var="manager" items="${managerList}" varStatus="i">
                            					<th>
                            						${manager.name}
                            						<c:if test="${manager.id eq userDTO.id}">
													    <select class="status${i.index}" name="status">
													    	<option>미결</option>
													        <option>결재</option>
													        <option>반려</option>
													    </select>
													</c:if>
                            					</th>
                       						</c:forEach>
										</tr>
										<tr>
											<c:forEach var="manager" items="${managerRBList}" varStatus="i">
                            					<td class="status${i.index }">
													<c:choose>
														<c:when test="${manager.approval_status eq '결재' }">
															<img id="check" src="/images/approval/check.svg" class="status${i.index }">
														</c:when>
														<c:when test="${manager.approval_status eq '반려' }">
															<img id="X" src="/images/approval/X.svg" class="status${i.index }">
														</c:when>
														<c:when test="${manager.approval_status eq '미결' }">
															<img id="minus" src="/images/approval/minus.svg" class="status${i.index }">
														</c:when>
													</c:choose>
                            					</td>
                       						</c:forEach>
										</tr>
										<tr>
											<c:forEach var="manager" items="${managerList}">
                            					<td>${manager.organization}</td>
                       						</c:forEach>
										</tr>
									</table>
								</td>
							</tr>
						</tbody>
					</table>

					<h4>제목</h4>
					<div class="input-group">
						<input type="text" class="form-control" value="${app.title }" readonly>
					</div>

					<h4>본문</h4>
					<div class="input-group">
						<textarea id="contents" readonly>${app.contents }</textarea>
					</div>

					<h4>파일 첨부</h4>
					<div>
						<c:forEach var="file" items="${filesList }">
							<a href="/approval/document/download?oriName=${file.ori_name}&sysName=${file.sys_name}">
								${file.ori_name} 
							</a><br>
						</c:forEach>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="/js/approval/document/write.js"></script>
	<script>
		$(document).ready(function() {
			$("#top_container").load("/commons/topForm");
		});
		$("#delete_btn").on("click", function() {
			location.href = "/approval/document/delete?doc_id=${docId}"
		});
		$("#back_btn").on("click", function() {
			window.history.back();
		});

		$("img").each(function() {
			if($(this).attr('id') == "check") {
				$("select."+$(this).attr("class")).val("결재").prop("selected", true);
			} else if($(this).attr('id') == "X") {
				$("select."+$(this).attr("class")).val("반려").prop("selected", true);
			} else {
				$("select."+$(this).attr("class")).val("미결").prop("selected", true);
			}
		});
		
		$("select[name='status']").on("change", function() {
			$("td."+$(this).attr('class')).empty();
			
			if ($(this).val() == '결재') {
	        	$("td."+$(this).attr('class')).append('<img src="/images/approval/check.svg">');
	        } else if ($(this).val() == '반려') {
	        	$("td."+$(this).attr('class')).append('<img src="/images/approval/X.svg">');
	        } else if($(this).val() == '미결') {
	        	$("td."+$(this).attr('class')).append('<img src="/images/approval/minus.svg">');
	        }
		});
	</script>
</body>
</html>