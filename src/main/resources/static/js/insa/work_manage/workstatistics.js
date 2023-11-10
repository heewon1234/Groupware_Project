$(document).ready(function() {
	$(".left_item").load("/works/manage_left_item?selectItem=workstatistics");
});

function updateWorkformTable() {
	    $.ajax({
	        url: "/works/statisticslist",
	    }).done(function (resp) {
	        console.log(resp.length);
	        for (let i = 0; i < resp.length; i++) {
	            let tr = $("<tr>");
	            tr.html("<td>" + (resp[i].name)+"("+(resp[i].id) + ")</td><td>" +  (resp[i].organization) +
	            "</td><td>" + (resp[i].leave_remainder+resp[i].leave_use) +"</td><td>" + (resp[i].leave_use) +
	            "</td><td>" + (resp[i].leave_remainder) + "</td><td>" + (resp[i].work_late) +
	            "</td><td>" + (resp[i].work_early) + "</td><td>" + (resp[i].work_notcheck) +
	            "</td><td>" + (resp[i].work_truancy) + "</td><td>" + (resp[i].all_workday) +
	            "</td><td>" + parseInt((resp[i].all_worktime)/60)+":"+(resp[i].all_worktime%60).toString().padStart(2, '0') + "</td><td>" 
	            + parseInt((resp[i].all_worktime)/(resp[i].all_workday)/60)+":"+((resp[i].all_worktime)/(resp[i].all_workday)%60).toString().padStart(2, '0')  
	             +"</td></tr>")
	            $("#workbox").append(tr);
	        }
	    });
	}
    updateWorkformTable()