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
        	$('#formRegister-comment').val(dataArt.comment);
        	
    
        	
	},
    error: function (jqxhr, textStatus, errorThrown) {
    	toastr['error']('Ne radi');;
        
    }
});

})

var fileContent;
var name;
function uploadFile(event){
	var input = event.target;
	var reader = new FileReader();
	reader.onload = function(){
		fileContent = reader.result;
	}
	name = input.files[0].name;
	reader.readAsDataURL(input.files[0]);
	fileContent = reader.result;
}

function confirmChange(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var articleId = url.searchParams.get("articleId");
	var PID = url.searchParams.get("PID");
	var dataPDF = JSON.stringify({
		"file_name":name,
		"file_content":fileContent
	});
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/changePDF/"+articleId+"/"+PID,
        type: "POST",
        contentType: "application/json",
        data: dataPDF,
        success: function () {
        		toastr['success']('Uspesno zamenjen PDF');
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');;
            
        }
    });
	
}