var table = $("#myTable").DataTable({
	lengthChange: false,
	info: false,
	"language": {
		"search": "으로 검색:"	
	}
	
});

// Search 셀렉트 추가
$('#myTable_filter').prepend('<select id="select"></select>');

// Search 셀렉트에 항목 자동 추가
$('#myTable > thead > tr').children().each(function(indexInArray, valueOfElement) {

	$('#select').append('<option>' + valueOfElement.innerHTML + '</option>');

});

// Search를 셀렉트의 값만 검색하도록 설정
// 기존 있던 검색 기능을 unbind()하고 재설정
$('.dataTables_filter input').unbind().bind('keyup', function() {

	var colIndex = document.querySelector('#select').selectedIndex;

	table.column(colIndex).search(this.value).draw();

});