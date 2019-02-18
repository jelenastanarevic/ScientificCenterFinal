$(document).ready(function () {
	$.ajax({
		async: false,
		url: "http://localhost:1555/scientific_field/getAllScientificFields",
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	
        	var fieldsSelect = $('#scfield-select');
        	fieldsSelect.append('<option>None</option>');
        	for(i=0;i<data.length;i++){
        		if(data !=null){
        			
        			fieldsSelect.append('<option id=\"'+data[i].name+'\">'+data[i].name+'</option>');
        			//fieldsDiv.append(fieldsTable);
	        	}
        	}
        	
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });
	
	/*$.ajax({
		async: false,
		url: "http://localhost:1239/radovi/findAll",
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	
        	alert('success');
        	
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });*/
	
	
})

function finishSetting(){
	var magazineName = $('#formRegister-magazine-name').val();
	var articleTitle = $('#formRegister-article-title').val();
	var nameAndSurname = $('#formRegister-name-surname').val();
	var keywords = $('#formRegister-keywords').val();
	var apstract = $('#formRegister-form-apstract').val();
	var content = $('#formRegister-content').val();
	var scfield  = $("#scfield-select").val();
	var resKeywords=null;
		if(keywords !=""){
			resKeywords = keywords.split(" ");
		}
		
	
if($('input[id=and-magazinename]').prop('checked') == false && $('input[id=or-magazinename]').prop('checked') == false && $('input[id=and-scfield]').prop('checked') == false && $('input[id=or-scfield]').prop('checked') == false && $('input[id=and-apstract]').prop('checked') == false && $('input[id=or-apstract]').prop('checked') == false && $('input[id=and-content]').prop('checked') == false && $('input[id=or-content]').prop('checked') == false && $('input[id=and-articletitle]').prop('checked') == false && $('input[id=or-articletitle]').prop('checked') == false &&
		$('input[id=and-namesurname]').prop('checked') == false && $('input[id=or-namesurname]').prop('checked') == false && $('input[id=and-keywords]').prop('checked') == false && $('input[id=or-keywords]').prop('checked') == false){
			
	//magazine name samo
	if(magazineName!=""){
		var phrase="";	
		if($('input[id=phrase-query-magazinename]').prop('checked') == false){
			phrase = "regular";
		}else{
			phrase = "phrase";
		}
			$.ajax({
				async: false,
				url: "http://localhost:1239/radovi/findByMagazineName/"+magazineName+"/"+phrase,
		        type: "GET",
		        dataType:"json",
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
	
	//science field obicna pretraga
	if(scfield!="None"){
		
			
			$.ajax({
				async: false,
				url: "http://localhost:1239/radovi/findByScienceField/"+scfield,
		        type: "GET",
		        dataType:"json",
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
	
	//obicna pretraga po abstractu
	if(apstract!=""){
		var phrase="";	
		if($('input[id=phrase-query-apstract]').prop('checked') == false){
			phrase = "regular";
		}else{
			phrase = "phrase";
		}
			
			$.ajax({
				async: false,
				url: "http://localhost:1239/radovi/findByApstract/"+apstract+"/"+phrase,
		        type: "GET",
		        dataType:"json",
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
	
	//obicna pretraga po sadrzaju
	if(content!=""){
		var phrase="";	
		if($('input[id=phrase-query-content]').prop('checked') == false){
			phrase = "regular";
		}else{
			phrase = "phrase";
		}
			
			$.ajax({
				async: false,
				url: "http://localhost:1239/radovi/findByContent/"+phrase,
		        type: "POST",
		        contentType:"text/plain",
		        data:content,
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
	
	//obicna pretraga po naslovu clanka
	if(articleTitle!=""){
		var phrase="";	
		if($('input[id=phrase-query-articletitle]').prop('checked') == false){
			phrase = "regular";
		}else{
			phrase = "phrase";
		}
	
			$.ajax({
				async: false,
				url: "http://localhost:1239/radovi/findByTitle/"+articleTitle+"/"+phrase,
		        type: "GET",
		        dataType:"json",
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
	
	//obicna pretraga po imenu i prezimenu autora
	if(nameAndSurname!=""){
		
			$.ajax({
				async: false,
				url: "http://localhost:1239/radovi/findByNameAndSurname/"+nameAndSurname,
		        type: "GET",
		        dataType:"json",
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
	
	//obicna pretraga po kljucnim recima
	if(resKeywords != "" && resKeywords !=null){

			var list=[];
			for (var i = 0; i < resKeywords.length; i++) {
			   list.push(resKeywords[i]);
			}
			var listData = JSON.stringify(list);
			$.ajax({
				async: false,
				url: "http://localhost:1239/radovi/findByKeywords",
		        type: "POST",
		        contentType:"application/json",
		        data:listData,
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
	
}else{
	var listOfQueryModels=[];
	//magazine name
	var QMmagazinename = "";
	var operationMN = "";
	var phraseMN="";
	var QMJSONmagazine={};
	if($('#formRegister-magazine-name').val()!=""){
		QMmagazinename = $('#formRegister-magazine-name').val();
		if($('input[id=and-magazinename]').prop('checked') == true ){
			
			operationMN="AND";
		}else if($('input[id=or-magazinename]').prop('checked') == true){
			
			operationMN="OR";
		}
		if($('input[id=phrase-query-magazinename]').prop('checked') == false){
			phraseMN = "regular";
		}else{
			phraseMN = "phrase";
		}
		
		QMJSONmagazine = {
				"field":"name_of_magazine",
				"value":QMmagazinename,
				"operation":operationMN,
				"searchType":phraseMN
		}
		listOfQueryModels.push(QMJSONmagazine);
		
	}
	
	//article title
	var QMarticletitle = "";
	var operationAT = "";
	var phraseAT="";
	var QMJSONarticletitle = {};
	if($('#formRegister-article-title').val()!=""){
		QMarticletitle = $('#formRegister-article-title').val();
		if($('input[id=and-articletitle]').prop('checked') == true ){
			
			operationAT="AND";
		}else if($('input[id=or-articletitle]').prop('checked') == true ){
			
			operationAT="OR";
		}
		if($('input[id=phrase-query-articletitle]').prop('checked') == false){
			phraseAT = "regular";
		}else{
			phraseAT = "phrase";
		}
		QMJSONarticletitle = {
				"field":"title",
				"value":QMarticletitle,
				"operation":operationAT,
				"searchType":phraseAT
		}
		listOfQueryModels.push(QMJSONarticletitle);
		
	}
	
	//name and surname
	var QMname = "";
	var QMsurname = "";
	
	var operationNS = "";
	var phraseNS="";
	var QMJSONname = {};
	var QMJSONsurname = {};
	if($('#formRegister-name-surname').val()!=""){
		var splitRes = $('#formRegister-name-surname').val().split(" ");
		QMname = splitRes[0];
		QMsurname = splitRes[1];
		if($('input[id=and-namesurname]').prop('checked') == true ){
			
			operationNS="AND";
		}else if($('input[id=or-namesurname]').prop('checked') == true ){
			
			operationNS="OR";
		}
		phraseNS="phrase";
		QMJSONname = {
				"field":"authors.firstName",
				"value":QMname,
				"operation":operationNS,
				"searchType":phraseNS
		}
		QMJSONsurname = {
				"field":"authors.lastName",
				"value":QMsurname,
				"operation":operationNS,
				"searchType":phraseNS
		}
		listOfQueryModels.push(QMJSONname);
		listOfQueryModels.push(QMJSONsurname);
		
		
	}
	
	
	
	//keywords
	var QMkeywords = "";
	var operationKW = "";
	var phraseKW="";
	var QMJSONkeywords = {};
	
	if($('#formRegister-keywords').val()!=""){
		QMkeywords = $('#formRegister-keywords').val();
		
		if($('input[id=and-keywords]').prop('checked') == true ){
			
			operationKW="AND";
		}else if($('input[id=or-keywords]').prop('checked') == true ){
			
			operationKW="OR";
		}
		phraseKW="regular";
		
		var keyWordsSplit = QMkeywords.split(" ");
		for(i=0;i<keyWordsSplit.length;i++){
			QMJSONkeywords = {
					"field":"keywords",
					"value":keyWordsSplit[i],
					"operation":operationKW,
					"searchType":phraseKW
			}
			listOfQueryModels.push(QMJSONkeywords);
			QMJSONkeywords={};
		}
		
		
	}
	
	//abstract
	var QMabstract = "";
	var operationAB = "";
	var phraseAB="";
	var QMJSONapstract = {};
	if($('#formRegister-form-apstract').val()!=""){
		QMabstract = $('#formRegister-form-apstract').val();
		if($('input[id=and-apstract]').prop('checked') == true ){
			
			operationAB="AND";
		}else if($('input[id=or-apstract]').prop('checked') == true ){
			
			operationAB="OR";
		}
		if($('input[id=phrase-query-apstract]').prop('checked') == false){
			phraseAB = "regular";
		}else{
			phraseAB = "phrase";
		}

		QMJSONapstract = {
				"field":"apstract",
				"value":QMabstract,
				"operation":operationAB,
				"searchType":phraseAB
		}
		listOfQueryModels.push(QMJSONapstract);
		
	}
	
	//content
	var QMcontent = "";
	var operationCNT = "";
	var phraseCNT="";
	var QMJSONcontent = {};
	if($('#formRegister-content').val()!=""){
		QMcontent = $('#formRegister-content').val();
		if($('input[id=and-content]').prop('checked') == true ){
			
			operationCNT="AND";
		}else if($('input[id=or-content]').prop('checked') == true ){
			
			operationCNT="OR";
		}
		if($('input[id=phrase-query-content]').prop('checked') == false){
			phraseCNT = "regular";
		}else{
			phraseCNT = "phrase";
		}

		QMJSONcontent = {
				"field":"content",
				"value":QMcontent,
				"operation":operationCNT,
				"searchType":phraseCNT
		}
		listOfQueryModels.push(QMJSONcontent);
		
	}
	
	//scientific field
	var QMscfield = "";
	var operationSCF = "";
	var phraseSCF="";
	var QMJSONscfield = {};
	if($("#scfield-select").val()!="None"){
		QMscfield = $("#scfield-select").val();
		if($('input[id=and-scfield]').prop('checked') == true ){
			
			operationSCF="AND";
		}else if($('input[id=or-scfield]').prop('checked') == true ){
			
			operationSCF="OR";
		}
		phraseSCF = "regular";
		
		QMJSONscfield = {
				"field":"scientific_field",
				"value":QMscfield,
				"operation":operationSCF,
				"searchType":phraseSCF
		}
		listOfQueryModels.push(QMJSONscfield);
		
	}
	
	
	
	
	
	var data = JSON.stringify(listOfQueryModels);
	$.ajax({
		async: false,
		url: "http://localhost:1239/radovi/booleanQuery",
        type: "POST",
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
}

function drawResult(dataArtFinal){
	var divColSmA = $("#results");
	divColSmA.empty();
	var str = "";
	if(dataArtFinal.length > 0 ){
    	for(i=0;i<dataArtFinal.length;i++){
    		str+="<div class=\"form-box\">";
            str+="<div class=\"panel panel-primary\">";
            str+="<div class=\"form-top\">";
            str+="<div id=\"headerCo\" class=\"panel-heading\">Article#"+i+"</div></div>";
			str+="<div class=\"form-bottom\"><div  class=\"panel-body\" ><div id=\"bodyCo\" >";
			if(dataArtFinal[i].highlight!="" && dataArtFinal[i].highlight!=null ){
				str+="<label>"+dataArtFinal[i].highlight+"</label><hr/><br/>";
			}
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
	}
}