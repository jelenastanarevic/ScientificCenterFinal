$(document).ready(function () {
	var url_string = window.location.href;
	var url = new URL(url_string);
	var idMagazine = url.searchParams.get("magazine");
	
	$.ajax({
		async: false,
		url: "http://localhost:1555/naucna_centrala/getMagazineName/"+idMagazine,
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	var title = $("#tableTitle");
        	tableTitle.append(data.title);
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });	
	
	
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/getArticlesOfMagazine/"+idMagazine,
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	var articlesDiv = $('#articlesDiv');
        	var articlesTable = $('#articlesTable');
        	for(i=0;i<data.length;i++){
        		if(data !=null){
        			articlesTable.append('<tr><td>'+data[i].title+'</td><td>'+data[i].keyWords+'</td><td>'+data[i].abstract_description+'</td><td>'+data[i].scientific_field_name+'</td><td>'+data[i].author_name+'</td><td>'+data[i].price+'</td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"buyArticle('+data[i].id+ ')\">Buy article</button></td></tr>');
        			articlesDiv.append(articlesTable);
	        	}
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });	


})

function buyArticle(idArticle){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var idMagazine = url.searchParams.get("magazine");
	var titleArticle="";
	var priceArticle=null;
	$.ajax({
		async: false,
		url: "http://localhost:1555/article/getArticleById/"+idArticle,
	    type: "GET",
	    dataType:"json",
	    crossDomain: true,
	    withCredentials: true,
	    headers: {  'Access-Control-Allow-Origin': '*' },
	    success: function (dataArt) {
	    	titleArticle = dataArt.title;
	    	priceArticle = dataArt.price;
	    },
	    error: function (jqxhr, textStatus, errorThrown) {
	    	toastr['error']('Ne radi');
	    } 
	    });
	
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
	    		"userEmail": "marko@gmail.com",
	    		"articleTitle":titleArticle,
	    		"articlePrice":priceArticle});
	    	
	    	$.ajax({
	    	async: false,
			url: "http://localhost:1555/article/buyArticle/"+idMagazine+"/"+idArticle,
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
	
	/*$.ajax({
	async: false,
	url: "http://localhost:1555/article/buyArticle/"+idMagazine+"/"+idArticle,
    type: "GET",
    dataType:"json",
    crossDomain: true,
    withCredentials: true,
    headers: {  'Access-Control-Allow-Origin': '*' },
    success: function (data) {
    	toastr['success']('Order created!');
    },
    error: function (jqxhr, textStatus, errorThrown) {
    	toastr['error']('Ne radi');
    } 
    });*/
}