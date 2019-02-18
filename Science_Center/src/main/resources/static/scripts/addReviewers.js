var list=[];
$(document).ready(function () {
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var articleId = url.searchParams.get("articleId");

	$.ajax({
		async: false,
		url: "http://localhost:1555/reviewer/findReviewers/"+articleId,
        type: "GET",
        dataType: "json",
        success: function (dataRev) {
        	var reviewersDiv = $('#reviewersDiv');
        	var reviewersTable=$('#reviewersTable');
        	for(i=0;i<dataRev.length;i++){
        	reviewersTable.append('<tr><td>'+dataRev[i].first_name+'</td><td>'+dataRev[i].last_name+'</td><td id=\"'+dataRev[i].id+'\">'+dataRev[i].email+'</td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"addRev('+dataRev[i].id+ ')\">Add reviewer</button></td></tr>');
        	reviewersDiv.append(reviewersTable);
        	}
        	
	},
    error: function (jqxhr, textStatus, errorThrown) {
    	toastr['error']('Ne radi');;
        
    }
});

})

function addRev(idRev){
	
	list.push(idRev);
	var email = $('#'+idRev+'').html();
	$('#pAdded').append(email + " ");
	
	
}

function finish(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var articleId = url.searchParams.get("articleId");
	var idTask = url.searchParams.get("idTask");
	var listJSON = JSON.stringify(list);
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/addReviewers/"+idTask+"/"+articleId,
        type: "POST",
        contentType:"application/json",
        data:listJSON,
        success: function () {
        	toastr['success']('Successfully added reviewers');
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } });
	
}