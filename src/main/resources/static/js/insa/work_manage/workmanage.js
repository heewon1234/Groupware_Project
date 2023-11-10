$(document).ready(function() {
	$(".left_item").load("/works/manage_left_item?selectItem=workmanage");
});

function updateWorkformTable() {
	    $.ajax({
	        url: "/works/workmanagelist",
	    }).done(function (resp) {
	        console.log(resp.length);
	        for (let i = 0; i < resp.length; i++) {
	            let tr = $("<tr>");
	            tr.html("<td>" + (resp[i].name)+"("+(resp[i].id) + ")</td><td>" +  (resp[i].time) +
	            "</td><td>" + (resp[i].all_leave) +"</td><td>" + (resp[i].leave_regular) +
	            "</td><td>" + (resp[i].leave_bonus) + "</td><td>" + (resp[i].leave_other_bonus) +
	            "</td><td>" + (resp[i].year_leave) + "</td><td>" + (resp[i].bonus_leave) +
	            "</td><td>" + (resp[i].month_leave) + "</td><td>" + (resp[i].family_event_leave) +
	            "</td><td>" + (resp[i].remainder_leave) +  "</td></tr>")
	            $("#workbox").append(tr);
	        }
	        $(".div").after("총 인원 :" + resp.length);
	    });
	}
    updateWorkformTable()