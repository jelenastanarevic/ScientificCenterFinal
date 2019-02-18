$(document).ready(function () {
	var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PID");
	
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/getCurrent/"+PInsId,
        type: "GET",
        dataType: "json",
        success: function (dataArt) {
					$.ajax({
						async: false,
						url: "http://localhost:1555/user/getFormFields/"+PInsId,
				        type: "GET",
				        dataType: "json",
				        success: function (data) {
				        	divGlavni = $('#formaArt');
				        	var str="";
				        	
				        	for(i=0;i<data.formField.length;i++){
				        		str+="<div class=\"form-group\">";
				        		
				        		if(data.formField[i].label !="MagazineId" && data.formField[i].label !="FileContent" && data.formField[i].label !="FileName"){
				        			str+="<label class=\"sr-only\" >"+data.formField[i].label+"</label>";
				        			//magazine id je jedan od parametara forme, i ispis se radi sve bez magazine id
				        			//ako je keywords, zbog prekicanja reci, taj field se tretira posebno (ispod)
					        		if(data.formField[i].label=="Keywords, seperated with comma"){
					        			//u slucaju da se menjaju podaci o clanku, radi se provera dole
					        			if(dataArt.title ==null){
					        			str+="<input type=\"text\"  placeholder=\"Keywords, separated with comma\" class=\"form-first-name form-control\" id="+data.formField[i].id+">";
					        			}else{
					        				str+="<input type=\"text\"  placeholder=\"Keywords, separated with comma\" class=\"form-first-name form-control\" value=\""+dataArt.keyWords+"\" id="+data.formField[i].id+">";
						        				
					        			}
					        		}else{
					        			//ako nisu keywords, nego su apstract title ili nesto trece, radi se donja provera
					        			//u prvom if-u je slucaj potpuno novog rada
					        			if(dataArt.title ==null){
					        			
						        			//if(data.formField[i].label=="PDF"){
					        					//str+="<input type=\"file\" onchange=\"uploadFile(event)\"  placeholder=\"PDF\" class=\"form-first-name form-control\" id="+data.formField[i].id+">";
							        			
					        				//}else{
					        					//ovde se dodaju prazni
					        					str+="<input type=\"text\"  placeholder="+data.formField[i].label+" class=\"form-first-name form-control\" id="+data.formField[i].id+">";
					        				//}
					        			}else{
					        				//ako rad vec postoji
					        				if(data.formField[i].label=="Title"){
					        				str+="<input type=\"text\"  placeholder=\"Title\" class=\"form-first-name form-control\" value=\""+dataArt.title+"\" id="+data.formField[i].id+">";
					        				}else if(data.formField[i].label=="Apstract"){
					        					str+="<textarea  placeholder=\"Apstract\" class=\"form-first-name form-control\" value=\""+dataArt.abstract_description+"\" id="+data.formField[i].id+"></textarea>";
						        					
					        				}
					        			}
					        		}
				        		}
				        		str+="</div>";
				        		divGlavni.append(str);
				        		str="";
				        	}
				        	//append za upload fajla,taskid i processInstanceId
				        	str+="<input type=\"file\" onchange=\"uploadFile(event)\"  placeholder=\"PDF\" class=\"form-file-field form-control\" id=\"file_field\">";
				        	str+="<input type=\"hidden\" id=\"taskId\" name=\"taskId\" value=\""+data.taskId+"\">";
							str+="<input type=\"hidden\" id=\"processInstanceId\" name=\"processInstanceId\" value=\""+data.processInstanceId+"\">";
							
				        	divGlavni.append(str);
				        },
				        error: function (jqxhr, textStatus, errorThrown) {
				        	toastr['error']('Ne radi');;
				            
				        }
				    });
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

function buttonScientificFieldClick(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var magazine = url.searchParams.get("idMagazine");
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PID");
	
	title = $("#title").val();
	apstract = $("#apstract").val();
	keywords = $("#keywords").val();
	
	taskId = $("#taskId").val();
	
	var data = JSON.stringify([
	              {"fieldId":"title",
	            	  "fieldValue":title},
	            	  {"fieldId":"apstract",
		            	  "fieldValue":apstract},
		            	  {"fieldId":"keywords",
			            	  "fieldValue":keywords},
			            	  {"fieldId":"id_magazine",
				            	  "fieldValue":magazine},
				            	  {"fieldId":"file_name",
					            	  "fieldValue":name}
				              
	            ]);
	
	
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/createArticle/"+taskId,
        type: "POST",
        contentType: "application/json",
        data: data,
        success: function () {
        	var dataPDF = JSON.stringify({
        		"file_name":name,
        		"file_content":fileContent
        	});
        	$.ajax({
        		async: false,
        		url: "http://localhost:1555/article/addPDF",
                type: "POST",
                contentType: "application/json",
                data: dataPDF,
                success: function () {
                		top.location.href = "addScientificField.html?PID="+PInsId;
                },
                error: function (jqxhr, textStatus, errorThrown) {
                	toastr['error']('Ne radi');;
                    
                }
            });
               
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');;
            
        }
    });
	
}