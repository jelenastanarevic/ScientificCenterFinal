$(document).ready(function () {
	
	$.ajax({
		async: false,
		url: "http://localhost:1239/radovi/findAll",
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	var divColSm = $("#allArticles");
        	var str = "";
        	
        	if(data.length > 0 ){
	        	for(i=0;i<data.length;i++){
	        		str+="<div class=\"form-box\">";
	                str+="<div class=\"panel panel-primary\">";
	                str+="<div class=\"form-top\">";
	                str+="<div id=\"headerCo\" class=\"panel-heading\">Article#"+i+"</div></div>";
					str+="<div class=\"form-bottom\"><div  class=\"panel-body\" ><div id=\"bodyCo\" >";
					str+="<label>Title: "+data[i].title+"</label><br/>";
					str+="<label>File name: "+data[i].file_name+"</label><br/>";
					str+="<label>Name of magazine: "+data[i].name_of_magazine+"</label><br/>";
					str+="<label>Abstract: "+data[i].apstract+"</label><br/>";
					str+="<label>Scientific field: "+data[i].scientific_field+"</label><br/>";
					str+="<label>Keywords: ";
					
					for(k=0;k<data[i].keywords.length;k++){
						if(k < data[i].keywords.length-1){
							str+=data[i].keywords[k]+", ";
						}else{
							str+=data[i].keywords[k];
						}
					}
					str+="</label><br/>";
					str+="<label>AUTHORS: <div style=\"margin-left:30px;\">";
					for(j=0;j<data[i].authors.length;j++){
							str+=data[i].authors[j].firstName+" "+data[i].authors[j].lastName+"<br/>";
							str+=data[i].authors[j].email;
							str+="<hr/>";
						
					}
					str+="</div></label><br/>";
					
					str+="<label>REVIEWERS: <div style=\"margin-left:30px;\">";
					for(t=0;t<data[i].reviewers.length;t++){
							str+=data[i].reviewers[t].firstName+" "+data[i].reviewers[t].lastName+"<br/>";
							str+=data[i].reviewers[t].email;
							str+="<hr/>";
						
					}
					var id = (data[i].article_id).toString();
					str+="</div></label><br/>";
					str+="&nbsp;&nbsp;<button type=\"button\" id=\""+data[i].article_id+"\" onclick=\"geospatial(event)\" class=\"btn btn-success\">Geospatial search</button>";
					str+="</div></div></div></div></div>";
					divColSm.append(str);
					str="";						
	        	}
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });	

})

function geospatial(event){
	var id = event.target.id;
	
	$.ajax({
		async: false,
		url: "http://localhost:1239/radovi/findById/"+id,
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (dataArt) {
        	var dataSend = JSON.stringify(dataArt);
        	$.ajax({
        		async: false,
        		url: "http://localhost:1239/radovi/findByDistance",
                type: "POST",
                contentType:"application/json",
                data:dataSend,
                crossDomain: true,
                withCredentials: true,
                headers: {  'Access-Control-Allow-Origin': '*' },
                success: function (dataUsers) {
                	var divColSmA = $("#articlesResult");
                	divColSmA.empty();
                	var str = "";
                	
                	if(dataUsers.length > 0){
                		for(i=0;i<dataUsers.length;i++){
                		str+="<div class=\"form-box\">";
    	                str+="<div class=\"panel panel-primary\">";
    	                str+="<div class=\"form-top\">";
    	                str+="<div id=\"headerCo\" class=\"panel-heading\">Reviewer#"+i+"</div></div>";
    	                str+="<label>First name: "+dataUsers[i].firstName+"</label><br/>";
    					str+="<label>Last name: "+dataUsers[i].lastName+"</label><br/>";
    					str+="<label>Email: "+dataUsers[i].email+"</label><br/>";
    					str+="</div></div></div></div></div>";
    					divColSmA.append(str);
    					str="";
                		}
                	}
                	
                },
                error: function (jqxhr, textStatus, errorThrown) {
                	toastr['error']('Ne radi');
                } 
                });
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });
}
