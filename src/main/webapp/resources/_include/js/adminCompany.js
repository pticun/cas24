
alert("context:"+ctx);
$(document).ready(function() {
alert("$(document).ready: INICIO");	
	consoleAlterQ("AdminCompany - ready");
	consoleAlterQ("000document ready: round="+requestUserDto.round+" season="+requestUserDto.season);

	
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
	
	
	var jqxhr =
	    $.ajax({
	        url: ctx+"/login",
 	    })
	    .success (function(response) { 
		    if(response.errorDto!=0){
		    	if (bActual == bLogin)
		    		showDiv(bLogin);
		    	else
					showDiv(bHome);
		    	
				window.userLoged=false;
		    }
		    else{
		    	if (response.userAlterQ!=null){
	    			showDiv(bHome);
		    			
					fillUserData(response);
					
					window.userLoged=true;
					window.loadBetUser = true;
					consoleAlterQ("loadBetUser: TRUE");
		    	}
		    	else{
		    		showDiv(bHome);
		    		window.userLoged=false;
		    	}
		    }
		    fillRoundSeasonCompany(response);

			//Paint Main Menu Items
			consoleAlterQ("Menu: pintamos los elementos del menu");
			getMainMenuItems(window.userLoged, window.userLoged?response.userAlterQ.name:null);
	    });
	
	$('form#closeACForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#closeACForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/adminCompany'+ '/company/' + window.company + '/season/'+ $("input[id=seasonCloseAC]").val() + '/round/' + $("input[id=roundCloseAC]").val() + '/closeAC',
//			 url: ctx+'/adminCompany/closeAC',
			 	type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("closeAC: response="+objeto.stringError);
		   		    		$('#closeACFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("closeAC: response= OK");
						$('#closeACFormResponse').text("AdminCompany - Close - OK");
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

	$('form#finalQuinielaForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#quinielaForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/adminCompany'+ '/company/' + window.company + '/season/'+ $("input[id=seasonQuiniela]").val() + '/round/' + $("input[id=roundQuiniela]").val() + '/finalBet/' + $("input[id=tipoQuiniela]").val(),
			    type: "POST",
			    data: dataJson,
			    //contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("result: response="+objeto.errorDto);
		   		    		$('#quinielaFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("result: response= OK");
						$('#quinielaFormResponse').text("Admin - Quiniela - OK");
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

$("#homeBtn").on('click', function(event){
	menuEvent($(this).text(),  "home");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#quinielaFinalBtn").on('click', function(event){
	window.quinielaFinal = true;
	getSummary();
	getQuiniela();
	menuEvent($(this).text(),  "#quinielaDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#closeACBtn").on('click', function(event){
//alert("closeACBtn CLICK");	
	menuEvent($(this).text(),  "#closeACDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});

$("#homeBtn9").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn10").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});

function setRounSeasoCloseAC (round, season){
	if (round != 0)
		$('#seasonCloseAC').val(season);
	if (season != 0)
		$('#roundCloseAC').val(round);
}

function consoleAlterQ(text){
	if( (window['console'] !== undefined) ){
		console.log(text);
	}
}




