

$(document).ready(function () {
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var articleId = url.searchParams.get("articleId");

	$.ajax({
		async: false,
		url: "http://localhost:1555/article/findByIdArticle/"+articleId,
        type: "GET",
        dataType: "json",
        success: function (dataArt) {
        	$('#formRegister-title').val(dataArt.title);
        	$('#formRegister-keywords').val(dataArt.keyWords);
        	$('#formRegister-abstract').val(dataArt.abstract_description);
        	$('#formRegister-authorname').val(dataArt.author_name);
        	$('#formRegister-scfield').val(dataArt.scientific_field_name);
        	$('#linkfile_name').val(dataArt.file_name);
        	$("#linkfile_name").attr("href", "http://localhost:1555/download/download/"+dataArt.id+"");
        	$("#linkfile_name").text( ""+dataArt.file_name+"");
        	$("#linkfile_name").html( ""+dataArt.file_name+"");
    
        	
	},
    error: function (jqxhr, textStatus, errorThrown) {
    	toastr['error']('Ne radi');;
        
    }
});

})


function buttonAcceptClick(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var articleId = url.searchParams.get("articleId");
	var idTask = url.searchParams.get("idTask");
	
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/acceptElementaryData/"+articleId+"/"+idTask,
        type: "GET",
        success: function () {
        	$('#buttonsDiv').empty();
        	var str="";
        	str+="<button type=\"button\" onclick=\"buttonAcceptPDFClick()\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-arrow-right\"></span>Accept PDF</button>";
        	str+="<button type=\"button\" onclick=\"enterComment()\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-arrow-right\"></span>Decline PDF</button>";
        	$('#buttonsDiv').append(str);
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');;
            
        }
    });
	
}

function buttonDeclineClick(){
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var articleId = url.searchParams.get("articleId");
	var idTask = url.searchParams.get("idTask");
	
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/declineElementaryData/"+articleId+"/"+idTask,
        type: "GET",
        success: function () {
        	alert('success');
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');;
            
        }
    });
	
}

function enterComment(){
	var divComment = $("#CommentDiv");
	var str="";
	str+="<input id=\"comment\" type=\"text\" placeholder=\"Enter comment\"><br/>";
	str+="<button onclick=\"buttonDeclinePDFClick()\" class=\"btn btn-primary\">Confirm</button>";
	divComment.append(str);
	
}
function buttonDeclinePDFClick(){

	var url_string = window.location.href;
	var url = new URL(url_string);
	var articleId = url.searchParams.get("articleId");
	var idTask = url.searchParams.get("idTask");
	var PID =url.searchParams.get("PID");
	var comment = $("#comment").val();
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/declinePDF/"+articleId+"/"+PID,
        type: "POST",
        contentType:"text/plain",
        data:comment,
        success: function () {
        	toastr['success']('You have successfully declined this PDF');
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
            
        }
    });
}

function buttonAcceptPDFClick(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var articleId = url.searchParams.get("articleId");
	var idTask = url.searchParams.get("idTask");
	var PID =url.searchParams.get("PID");
	
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/acceptPDF/"+articleId+"/"+PID,
        type: "GET",
        success: function () {
        	toastr['success']('You have successfully confirmed PDF');
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');;
            
        }
    });
	
}