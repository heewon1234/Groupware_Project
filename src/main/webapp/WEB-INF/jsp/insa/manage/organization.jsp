<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<title>Insert title here</title>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/organization.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/common.css">
</head>
<body>
	<div class="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<div class="title">
					<h3>조직 관리</h3>
					<hr class="title_hr">
				</div>
				<div class="chart-box">
					<div id="chart_div"></div>
					<button class="modalButton button_apply">조직 추가</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modalContainer hidden">
		<form action="/org/insert">
			<div class="modalContent">
				<div class="input-group">
                	<input type="text" class="form-control" id="org_input" name="organization" placeholder="조직명">
                </div>
                <div class="input-group">
                	<select id="manager" class="form-control" name="manager">
                		<option>상위조직명</option>
                		<c:forEach items="${orgList}" var="org">
                			<option>${org.organization }</option>
                		</c:forEach>
                	</select>
                </div>
				<br>
				<div align="center" class="button_div">
					<button type="submit" class="button_apply permit" id="submit_btn">제출</button>
					<button type="button" class="modalButton button_cancel">닫기</button>
				</div>
			</div>
		</form>
	</div>
	<script src="/js/insa/manage/organization.js"></script>
	<script>
		google.charts.load('current', {packages : [ "orgchart" ]});
		google.charts.setOnLoadCallback(drawChart);

		function drawChart() {
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Name');
			data.addColumn('string', 'Manager');
			data.addColumn('string', 'ToolTip');

			// For each orgchart box, provide the name, manager, and tooltip to show.

			let array = [];

			<c:forEach items="${orgList}" var="org">
		    <c:choose>
		        <c:when test="${org.organization eq '하이웍스산업'}">
		            array.push([{
		                v : "${org.organization}",
		                f : '<span class="org_span">${org.organization}</span> <div>${org.count}</div>'
		            }, "${org.manager}", "${org.level}" ]);
		        </c:when>
		        <c:otherwise>
		            array.push([{
		                v : "${org.organization}",
		                f : '<span class="org_span">${org.organization}</span> <a class="remove_org_btn btn"><i class="fa-solid fa-circle-xmark" style="color: #bec3c9;"></i></a><div>${org.count}</div>'
		            }, "${org.manager}", "${org.level}" ]);
		        </c:otherwise>
		    </c:choose>
			</c:forEach>
			data.addRows(array);

			// Create the chart.
			var chart = new google.visualization.OrgChart(document.getElementById('chart_div'));
			// Draw the chart, setting the allowHtml option to true for the tooltips.
			const option = {
				'allowHtml' : true,
				'color' : '#ffffff'
			}
			chart.draw(data, option);
		}
	</script>
</body>
</html>