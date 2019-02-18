$(document).ready(function () {
	
	
	
	$.ajax({
		async: false,
		url: "http://localhost:9001/nc/article/getArticlesOfMagazineBought",
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
        			articlesTable.append('<tr><td>'+data[i].title+'</td><td>'+data[i].keyWords+'</td><td>'+data[i].abstract_description+'</td><td>'+data[i].scientific_field_name+'</td><td>'+data[i].author_name+'</td><td>'+data[i].price+'</td></tr>');
        			articlesDiv.append(articlesTable);
	        	}
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });	


})