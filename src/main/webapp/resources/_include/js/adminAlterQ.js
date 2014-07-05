var round=0;
var season=0;
var idUserAlterQ="";

//alert("context:"+ctx);
$(document).ready(function() {
//alert("$(document).ready: INICIO");	
	consoleAlterQ("Admin - ready");
	$.fn.serializeObject = function()
	{
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
	
	initDiv();
    
	
	$('form#openForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#openForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin'+ '/company/' + '1' + '/season/'+ $("input[name=season]").val() + '/round/' + $("input[name=round]").val() + '/open',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		   		    	consoleAlterQ("open: response="+response.errorDto.stringError);
		   		    	$('#openFormResponse').text(response.errorDto.stringError);
		   		    	userLoged=false;
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("open: response= OK");
						$('#openFormResponse').text("Admin - Open - OK");
						//userLoged=true;
						//idUserAlterQ=response.userAlterQ.id;
						//$('#idData').val(response.userAlterQ.id);
						//$('#nameData').val(response.userAlterQ.name);
						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						//$('#idSaldo').val(response.userAlterQ.id);
						//$('#balanceSaldo').val(response.userAlterQ.balance);
						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						//showDiv(bHome);
		   		    }
				    //round=response.generalData.round;
				    //season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
	
	$('form#closeForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#closeForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin'+ '/company/' + '1' + '/season/'+ $("input[id=seasonClose]").val() + '/round/' + $("input[id=roundClose]").val() + '/close',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		   		    	consoleAlterQ("open: response="+response.errorDto.stringError);
		   		    	$('#closeFormResponse').text(response.errorDto.stringError);
		   		    	userLoged=false;
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("close: response= OK");
						$('#closeFormResponse').text("Admin - Close - OK");
						//userLoged=true;
						//idUserAlterQ=response.userAlterQ.id;
						//$('#idData').val(response.userAlterQ.id);
						//$('#nameData').val(response.userAlterQ.name);
						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						//$('#idSaldo').val(response.userAlterQ.id);
						//$('#balanceSaldo').val(response.userAlterQ.balance);
						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						//showDiv(bHome);
		   		    }
				    //round=response.generalData.round;
				    //season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
	$('form#matchesForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#matchesForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin'+ '/company/' + '1' + '/season/'+ $("input[id=seasonMatches]").val() + '/round/' + $("input[id=roundMatches]").val() + '/matches',
			    type: "POST",
			    data: $(this).serialize(),
			    //contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		   		    	consoleAlterQ("matches: response="+response.errorDto.stringError);
		   		    	$('#matchesFormResponse').text(response.errorDto.stringError);
		   		    	userLoged=false;
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("matches: response= OK");
						$('#matchesFormResponse').text("Admin - Matches - OK");
						//userLoged=true;
						//idUserAlterQ=response.userAlterQ.id;
						//$('#idData').val(response.userAlterQ.id);
						//$('#nameData').val(response.userAlterQ.name);
						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						//$('#idSaldo').val(response.userAlterQ.id);
						//$('#balanceSaldo').val(response.userAlterQ.balance);
						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						//showDiv(bHome);
		   		    }
				    //round=response.generalData.round;
				    //season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
	$('form#resultForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#resultForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin'+ '/company/' + '1' + '/season/'+ $("input[id=seasonResults]").val() + '/round/' + $("input[id=roundResults]").val() + '/resultBet/' + $("input[id=results]").val(),
			    type: "POST",
			    data: dataJson,
			    //contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		   		    	consoleAlterQ("result: response="+response.errorDto.stringError);
		   		    	$('#matchesFormResponse').text(response.errorDto.stringError);
		   		    	userLoged=false;
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("result: response= OK");
						$('#resutFormResponse').text("Admin - Result - OK");
						//userLoged=true;
						//idUserAlterQ=response.userAlterQ.id;
						//$('#idData').val(response.userAlterQ.id);
						//$('#nameData').val(response.userAlterQ.name);
						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						//$('#idSaldo').val(response.userAlterQ.id);
						//$('#balanceSaldo').val(response.userAlterQ.balance);
						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						//showDiv(bHome);
		   		    }
				    //round=response.generalData.round;
				    //season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
	$('form#quinielaForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#quinielaForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin'+ '/company/' + '1' + '/season/'+ $("input[id=seasonQuiniela]").val() + '/round/' + $("input[id=roundQuiniela]").val() + '/finalBet/' + $("input[id=tipoQuiniela]").val(),
			    type: "POST",
			    data: dataJson,
			    //contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		   		    	consoleAlterQ("result: response="+response.errorDto.stringError);
		   		    	$('#quinielaFormResponse').text(response.errorDto.stringError);
		   		    	userLoged=false;
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("result: response= OK");
						$('#quinielaFormResponse').text("Admin - Result - OK");
						//userLoged=true;
						//idUserAlterQ=response.userAlterQ.id;
						//$('#idData').val(response.userAlterQ.id);
						//$('#nameData').val(response.userAlterQ.name);
						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						//$('#idSaldo').val(response.userAlterQ.id);
						//$('#balanceSaldo').val(response.userAlterQ.balance);
						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						//showDiv(bHome);
		   		    }
				    //round=response.generalData.round;
				    //season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
});

$("#openBtn").on('click', function(event){
	menuEvent($(this).text(),  "#openDiv");
});
$("#closeBtn").on('click', function(event){
	menuEvent($(this).text(),  "#closeDiv");
});
$("#matchesBtn").on('click', function(event){
	menuEvent($(this).text(),  "#matchesDiv");
});
$("#quinielaFinalBtn").on('click', function(event){
	menuEvent($(this).text(),  "#quinielaDiv");
});
$("#resultsBtn").on('click', function(event){
	menuEvent($(this).text(),  "#resultsDiv");
});
$("#homeBtn1").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
});
$("#homeBtn1").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
});
$("#homeBtn2").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
});
$("#homeBtn3").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
});
$("#homeBtn4").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
});
$("#homeBtn5").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
});


function consoleAlterQ(text){
	if( (window['console'] !== undefined) ){
		console.log(text);
	}

}

