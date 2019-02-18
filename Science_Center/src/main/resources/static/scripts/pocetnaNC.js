
$(document).ready(function () {
	/*var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PInsId");*/
	//getCurrentUser
	
	//proverava se ko je ulogovani korisnik i spram toga se radi kreiranje pocetne stranice
	$.ajax({
		async: false,
		url: "http://localhost:1555/user/getCurrentUser",
        type: "GET",
        success: function (data) {
        	if(data == "author"){
        		
        		$.ajax({
        			async: false,
        			url: "http://localhost:1555/naucna_centrala/getMagazines",
        	        type: "GET",
        	        dataType:"json",
        	        success: function (data) {
        	        	var magazinesDiv = $('#magazinesDiv');
        	        	var magazinesTable = $('#magazinesTable');
        	        	for(i=0;i<data.length;i++){
        	        		if(data !=null){
        	        			magazinesTable.append('<tr><td>'+data[i].title+'</td><td>'+data[i].issn+'</td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"addArticle('+data[i].id+ ')\">Add article</button></td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"viewArticles('+data[i].id+ ')\">View all articles</button></td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"buyMagazine('+data[i].id+ ')\">Buy this magazine</button></td></tr>');
        	        			magazinesDiv.append(magazinesTable);
        		        	}
        	        	}
        	        },
        	        error: function (jqxhr, textStatus, errorThrown) {
        	        	toastr['error']('Ne radi');
        	        } 
        	        });	
        		
        		$.ajax({
        			async: false,
        			url: "http://localhost:1555/author/getAuthorTasks",
        	        type: "GET",
        	        dataType:"json",
        	        success: function (data1) {
        	        	var authorDiv = $('#authorTasksDiv');
        	        	
                		var authorTable = $('#authorTasksTable');
        	        	authorTable.empty();
        	        	for(i=0;i<data1.length;i++){
        	        		if(data1 !=null){
        	        			authorTable.append('<tr><td>'+data1[i].title+'</td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"viewFullArticle('+data1[i].id+ ')\">View article</button></td><td style=\"visibility:hidden;\" id=\"'+data1[i].id+'authorTask\">'+data1[i].idTask+'</td><td style=\"visibility:hidden;\" id=\"'+data1[i].id+'authorPID\">'+data1[i].processInstanceId+'</td></tr>');
        	        			authorDiv.append(authorTable);
        		        	}
        	        	}
        	        	
        	        },
        	        error: function (jqxhr, textStatus, errorThrown) {
        	        	toastr['error']('Ne radi');
        	        } 
        	        });

        	}else if(data == "head_editor"){
        		var editorDiv = $('#ArticlesTOReviewDiv');
        		var reviewersDiv = $('#reviewersDiv');
        		
        		$('#magazinesDiv').empty();
        		$('#authorTasksDiv').empty();
        		var editorTable = $('#ArticlesTOReviewTable');
        		var reviewersTable = $('#reviewersTable');
        		$.ajax({
        			async: false,
        			url: "http://localhost:1555/editor/getHeadEditorTasks",
        	        type: "GET",
        	        dataType:"json",
        	        success: function (data) {
        	        	
        	        	
        	        	for(i=0;i<data.length;i++){
        	        		if(data !=null){
        	        			editorTable.append('<tr><td>'+data[i].title+'</td><td>'+data[i].author_name+'</td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"viewArticle('+data[i].id+ ')\">View article</button></td><td style=\"visibility:hidden;\" id=\"'+data[i].id+'\">'+data[i].idTask+'</td><td style=\"visibility:hidden;\" id=\"'+data[i].id+'PID\">'+data[i].processInstanceId+'</td></tr>');
        	        			editorDiv.append(editorTable);
        		        	}
        	        	}
        	        	
        	        },
        	        error: function (jqxhr, textStatus, errorThrown) {
        	        	toastr['error']('Ne radi');
        	        } 
        	        });
        		
        		$.ajax({
        			async: false,
        			url: "http://localhost:1555/editor/getEditorTasks",
        	        type: "GET",
        	        dataType:"json",
        	        success: function (data) {
        	        	
        	        	
        	        	for(i=0;i<data.length;i++){
        	        		if(data !=null){
        	        			reviewersTable.append('<tr><td>'+data[i].title+'</td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"addReviewers('+data[i].id+ ')\">View article</button></td><td style=\"visibility:hidden;\" id=\"'+data[i].id+'reviewTask\">'+data[i].idTask+'</td><td style=\"visibility:hidden;\" id=\"'+data[i].id+'reviewPID\">'+data[i].processInstanceId+'</td></tr>');
        	        			reviewersDiv.append(reviewersTable);
        		        	}
        	        	}
        	        	
        	        },
        	        error: function (jqxhr, textStatus, errorThrown) {
        	        	toastr['error']('Ne radi');
        	        } 
        	        });
        	}else if(data=="editor"){
        		var reviewersDiv = $('#reviewersDiv');
        		
        		$('#magazinesDiv').empty();
        		$('#authorTasksDiv').empty();
        		var reviewersTable = $('#reviewersTable');
        		$.ajax({
        			async: false,
        			url: "http://localhost:1555/editor/getEditorTasks",
        	        type: "GET",
        	        dataType:"json",
        	        success: function (data) {
        	        	
        	        	
        	        	for(i=0;i<data.length;i++){
        	        		if(data !=null){
        	        			reviewersTable.append('<tr><td>'+data[i].title+'</td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"addReviewers('+data[i].id+ ')\">View article</button></td><td style=\"visibility:hidden;\" id=\"'+data[i].id+'reviewTask\">'+data[i].idTask+'</td><td style=\"visibility:hidden;\" id=\"'+data[i].id+'reviewPID\">'+data[i].processInstanceId+'</td></tr>');
        	        			reviewersDiv.append(reviewersTable);
        		        	}
        	        	}
        	        	
        	        },
        	        error: function (jqxhr, textStatus, errorThrown) {
        	        	toastr['error']('Ne radi');
        	        } 
        	        });
        	}else if(data=="reviewer"){
        		var claimDiv = $('#claimDiv');
        		
        		$('#magazinesDiv').empty();
        		$('#authorTasksDiv').empty();
        		$('#reviewersDiv').empty();
        		var claimTable = $('#claimTable');
        		$.ajax({
        			async: false,
        			url: "http://localhost:1555/reviewer/getReviewerTasks",
        	        type: "GET",
        	        dataType:"json",
        	        success: function (data) {
        	        	
        	        	
        	        	for(i=0;i<data.length;i++){
        	        		if(data !=null){
        	        			claimTable.append('<tr><td>'+data[i].title+'</td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"viewArticleReview('+data[i].id+ ')\">View article</button></td><td style=\"visibility:hidden;\" id=\"'+data[i].id+'reviewRecTask\">'+data[i].idTask+'</td><td style=\"visibility:hidden;\" id=\"'+data[i].id+'reviewRecPID\">'+data[i].processInstanceId+'</td></tr>');
        	        			claimDiv.append(claimTable);
        		        	}
        	        	}
        	        	
        	        },
        	        error: function (jqxhr, textStatus, errorThrown) {
        	        	toastr['error']('Ne radi');
        	        } 
        	        });
        		
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });
	

})

function viewArticleReview(idArticle){
	var idTask = $('#'+idArticle+'reviewRecTask').html();
	var PID = $('#'+idArticle+'reviewRecPID').html();
	top.location.href="articleInfo.html?articleId="+idArticle+"&idTask="+idTask+"&PID="+PID;
	
}

function viewFullArticle(idArticle){
	var idTask = $('#'+idArticle+'authorTask').html();
	var PID = $('#'+idArticle+'authorPID').html();
	top.location.href="articleInfoPDF.html?articleId="+idArticle+"&idTask="+idTask+"&PID="+PID;
	
}

function viewArticle(idArticle){
	var idTask = $('#'+idArticle+'').html();
	var PID = $('#'+idArticle+'PID').html();
	top.location.href="articleInfo.html?articleId="+idArticle+"&idTask="+idTask+"&PID="+PID;
}

function addArticle(idMagazine){
	/*var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PInsId");*/
	
		var process = "Process_1";
		$.ajax({
			async: false,
			url: "http://localhost:1555/user/startProcess/"+process,
	        type: "GET",
	        dataType:"json",
	        crossDomain: true,
	        withCredentials: true,
	        headers: {  'Access-Control-Allow-Origin': '*' },
	        success: function (data) {
				$.ajax({
					async: false,
					url: "http://localhost:1555/article/addArticleForm/"+data.processInstanceId+"/"+idMagazine,
			        type: "GET",
			        crossDomain: true,
			        withCredentials: true,
			        headers: {  'Access-Control-Allow-Origin': '*' },
			        success: function () {
			        	top.location.href = "addArticle.html?idMagazine="+idMagazine+"&PID="+data.processInstanceId;
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


function viewArticles(idMagazine){
	top.location.href = "articlesOfMagazine.html?magazine="+idMagazine;
}

function buyMagazine(idMagazine){
	$.ajax({
		async: false,
		url: "http://localhost:1555/naucna_centrala/getMagazineName/"+idMagazine,
	    type: "GET",
	    dataType:"json",
	    crossDomain: true,
	    withCredentials: true,
	    headers: {  'Access-Control-Allow-Origin': '*' },
	    success: function (data1) {
	    	var data = JSON.stringify({"magazineIssn": data1.issn, 
	    		"userEmail": "smiljana.dragoljevic-1@gmail.com",
	    		"articleTitle":"",
	    		"articlePrice":null});
	    	
	    	$.ajax({
	    	async: false,
			url: "http://localhost:1555/naucna_centrala/buyMagazine/"+idMagazine,
		    type: "GET",
		    crossDomain: true,
		    withCredentials: true,
		    headers: {  'Access-Control-Allow-Origin': '*' },
		    success: function (dataRet) {
		    	top.location.href = dataRet;
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

function addReviewers(idArticle){
	var idTask = $('#'+idArticle+'reviewTask').html();
	var PID = $('#'+idArticle+'reviewPID').html();
	top.location.href="addReviewers.html?articleId="+idArticle+"&idTask="+idTask+"&PID="+PID;
}
