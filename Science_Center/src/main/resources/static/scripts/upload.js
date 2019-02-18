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
}

function upload(){
	var title = $('#formRegister-title').val();
	var data = JSON.stringify({
		"scientific_field":"Information Technology",
		"title":title,
		"keywords":[".NET","model","technology","account","reserve","vacation","hotel"],
		   "magazineName":"Technopedia",
		   "apstract":"Internship project – Technical documentation",
		   "authors":[
		         		{
		         			"id":10,
		         			"first_name":"Dragan",
		         			"last_name":"Miljevic",
		         			"city":"Zurich",
		         			"country":"Switzerland",
		         			"email":"dragan.miljevic@gmail.com",
		         			"longitude":12.10,
		         			"latitude":21.0
		         		},
		         		{
		         			"id":11,
		         			"first_name":"John",
		         			"last_name":"Regan",
		         			"city":"New York",
		         			"country":"USA",
		         			"email":"john.regan@gmail.com",
		         			"longitude":20.0,
		         			"latitude":21.0
		         		}
		         	],
		         	"reviewers":[
		         		{
		         			"id":12,
		         			"first_name":"Porter",
		         			"last_name":"Hobs",
		         			"city":"Chicago Illinois",
		         			"country":"USA",
		         			"email":"porter.hobs@gmail.com",
		         			"longitude":64.54,
		         			"latitude":-11.61
		         		},
		         		{
		         			"id":13,
		         			"first_name":"Philip",
		         			"last_name":"Meier",
		         			"city":"Berlin",
		         			"country":"Germany",
		         			"email":"philip.leier@gmail.com",
		         			"longitude":44.80,
		         			"latitude":-83.08
		         		}
		         	],
	"filename":name,
	"file":fileContent
	});
	
	$.ajax({
		async: false,
		url: "http://localhost:1239/radovi/upload",
        type: "POST",
        enctype:"multipart/form-data",
        contentType:"application/json",
        data:data,
        crossDomain: true,
            withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (dataRet) {
        	drawResult(dataRet);
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });
}

function drawResult(dataArtFinal){
	var divColSmA = $("#results");
	divColSmA.empty();
	var str = "";
	
    	
    		str+="<div class=\"form-box\">";
            str+="<div class=\"panel panel-primary\">";
            str+="<div class=\"form-top\">";
            str+="<div id=\"headerCo\" class=\"panel-heading\">Article</div></div>";
			str+="<div class=\"form-bottom\"><div  class=\"panel-body\" ><div id=\"bodyCo\" >";
			str+="<label>Title: "+dataArtFinal.title+"</label><br/>";
			str+="<label>File name: "+dataArtFinal.file_name+"</label><br/>";
			str+="<label>Name of magazine: "+dataArtFinal.name_of_magazine+"</label><br/>";
			str+="<label>Abstract: "+dataArtFinal.apstract+"</label><br/>";
			str+="<label>Scientific field: "+dataArtFinal.scientific_field+"</label><br/>";
			str+="<label>Keywords: ";
			
			for(k=0;k<dataArtFinal.keywords.length;k++){
				if(k < dataArtFinal.keywords.length-1){
					str+=dataArtFinal.keywords[k]+", ";
				}else{
					str+=dataArtFinal.keywords[k];
				}
			}
			str+="</label><br/>";
			str+="<label>AUTHORS: <div style=\"margin-left:30px;\">";
			for(j=0;j<dataArtFinal.authors.length;j++){
					str+=dataArtFinal.authors[j].firstName+" "+dataArtFinal.authors[j].lastName+"<br/>";
					str+=dataArtFinal.authors[j].email;
					str+="<hr/>";
				
			}
			str+="</div></label><br/>";
			
			str+="<label>REVIEWERS: <div style=\"margin-left:30px;\">";
			for(t=0;t<dataArtFinal.reviewers.length;t++){
					str+=dataArtFinal.reviewers[t].firstName+" "+dataArtFinal.reviewers[t].lastName+"<br/>";
					str+=dataArtFinal.reviewers[t].email;
					str+="<hr/>";
				
			}
			str+="</div></label><br/>";
			str+="<a target=\"_blank\" href=http://localhost:1239/download/download/"+dataArtFinal.article_id+" download>Download file</a>";
			//str+="&nbsp;&nbsp;<button type=\"button\" onclick=\"moreLikeThis("+dataArtFinal[i].article_id+")\" class=\"btn btn-danger\">More like this</button>";
			str+="</div></div></div></div></div>";
			divColSmA.append(str);
			str="";						
    	
	
}