/*$(document).ready(function () {
	
	/*$.ajax({
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
					str+="&nbsp;&nbsp;<button type=\"button\" onclick=\"moreLikeThis("+id+")\" class=\"btn btn-danger\">More like this</button>";
					str+="</div></div></div></div></div>";
					divColSm.append(str);
					str="";						
	        	}
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });	*/

//})*/

function moreLikeThis(){
	var content = $('#formRegister-content').val();
	
        	$.ajax({
        		async: false,
        		url: "http://localhost:1239/radovi/moreLikeThis",
                type: "POST",
                contentType:"text/plain",
                data:content,
                crossDomain: true,
                withCredentials: true,
                headers: {  'Access-Control-Allow-Origin': '*' },
                success: function (dataArtFinal) {
                	var divColSmA = $("#articlesResult");
                	divColSmA.empty();
                	var str = "";
                	
                	if(dataArtFinal.length > 0){
                		for(i=0;i<dataArtFinal.length;i++){
                		str+="<div class=\"form-box\">";
    	                str+="<div class=\"panel panel-primary\">";
    	                str+="<div class=\"form-top\">";
    	                str+="<div id=\"headerCo\" class=\"panel-heading\">Reviewer#"+i+"</div></div>";
    	                str+="<label>First name: "+dataArtFinal[i].firstName+"</label><br/>";
    					str+="<label>Last name: "+dataArtFinal[i].lastName+"</label><br/>";
    					str+="<label>Email: "+dataArtFinal[i].email+"</label><br/>";
    					str+="</div></div></div></div></div>";
    					divColSmA.append(str);
    					str="";
                		}
                	}
                	/*if(dataArtFinal.length > 0 ){
        	        	for(i=0;i<dataArtFinal.length;i++){
        	        		str+="<div class=\"form-box\">";
        	                str+="<div class=\"panel panel-primary\">";
        	                str+="<div class=\"form-top\">";
        	                str+="<div id=\"headerCo\" class=\"panel-heading\">Article#"+i+"</div></div>";
        					str+="<div class=\"form-bottom\"><div  class=\"panel-body\" ><div id=\"bodyCo\" >";
        					str+="<label>Title: "+dataArtFinal[i].title+"</label><br/>";
        					str+="<label>File name: "+dataArtFinal[i].file_name+"</label><br/>";
        					str+="<label>Name of magazine: "+dataArtFinal[i].name_of_magazine+"</label><br/>";
        					str+="<label>Abstract: "+dataArtFinal[i].apstract+"</label><br/>";
        					str+="<label>Scientific field: "+dataArtFinal[i].scientific_field+"</label><br/>";
        					str+="<label>Keywords: ";
        					
        					for(k=0;k<dataArtFinal[i].keywords.length;k++){
        						if(k < dataArtFinal[i].keywords.length-1){
        							str+=dataArtFinal[i].keywords[k]+", ";
        						}else{
        							str+=dataArtFinal[i].keywords[k];
        						}
        					}
        					str+="</label><br/>";
        					str+="<label>AUTHORS: <div style=\"margin-left:30px;\">";
        					for(j=0;j<dataArtFinal[i].authors.length;j++){
        							str+=dataArtFinal[i].authors[j].firstName+" "+dataArtFinal[i].authors[j].lastName+"<br/>";
        							str+=dataArtFinal[i].authors[j].email;
        							str+="<hr/>";
        						
        					}
        					str+="</div></label><br/>";
        					
        					str+="<label>REVIEWERS: <div style=\"margin-left:30px;\">";
        					for(t=0;t<dataArtFinal[i].reviewers.length;t++){
        							str+=dataArtFinal[i].reviewers[t].firstName+" "+dataArtFinal[i].reviewers[t].lastName+"<br/>";
        							str+=dataArtFinal[i].reviewers[t].email;
        							str+="<hr/>";
        						
        					}
        					str+="</div></label><br/>";
        					//str+="&nbsp;&nbsp;<button type=\"button\" onclick=\"moreLikeThis("+dataArtFinal[i].article_id+")\" class=\"btn btn-danger\">More like this</button>";
        					str+="</div></div></div></div></div>";
        					divColSmA.append(str);
        					str="";						
        	        	}
                	}*/
                	
                },
                error: function (jqxhr, textStatus, errorThrown) {
                	toastr['error']('Ne radi');
                } 
                });	
        	
        	
        	
	
}