

//alert("context:"+ctx);
$(document).ready(function() {
	
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
	
	initDiv(bHome);
	//Paint Main Menu Items
	getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
    
	//Menu Click Events
	$('div').on("click", "nav#menu ul#menu-nav li a", function() {
		menuEvent($(this).text(), $(this).attr("href"));
  		event.preventDefault();
	});
	//Menu Mobile Click Events
	$('div').on("click", "nav#navigation-mobile ul#menu-nav-mobile li a", function() {
		menuEvent($(this).text(), $(this).attr("href"));
		event.preventDefault();
	});
	

	
	var jqxhr =
	    $.ajax({
	        url: ctx+"/login",
 	    })
	    .success (function(response) { 
		    if(response.errorDto!=null){
		    	if (bActual == bLogin)
		    		showDiv(bLogin);
		    	else
					showDiv(bHome);
		    	
				userLoged=false;
		    }
		    else{
		    	if (response.userAlterQ!=null){
					showDiv(bHome);

					$('#id').val(response.userAlterQ.id);
					$('#name').val(response.userAlterQ.name);
					$('#phoneNumber').val(response.userAlterQ.phoneNumber);
					$('#idSaldo').val(response.userAlterQ.id);
					$('#balance').val(response.userAlterQ.balance);
					
					userLoged=true;
		    	}
		    	else{
   					showDiv(bHome);
    				userLoged=false;
		    	}
		    }
			//Paint Main Menu Items
			consoleAlterQ("Menu: pintamos los elementos del menu");
			getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
	    });
	
	
	
	$("form a").click(function(){
//alert("Form ="+ $(this).text() +" href="+$(this).attr("href"));
		var elem = $(this).text();
		var href = $(this).attr("href");
		
		if (href == sSignRef){
			consoleAlterQ("Sign");
			showDiv(bSign);
		}else if (href == sForgotRef){
			consoleAlterQ("Forget");
			showDiv(bForgot);
		}		
    });
	
	$('form#loginForm').submit(function(e) {
		 var dataJson=JSON.stringify($('form#loginForm').serializeObject());
		consoleAlterQ(dataJson);
		 jQuery.ajax ({
			    url: ctx+'/login',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		   		    	consoleAlterQ("login: response="+response.errorDto.stringError);
		   		    	$('#loginFormResponse').text(response.errorDto.stringError);
		   		    	userLoged=false;
		   		    }
		   		    else{
		   		    	consoleAlterQ("login: response="+response.userAlterQ.name);
							$('#loginFormResponse').text(response.userAlterQ.name);
							userLoged=true;
							getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
							showDiv(bHome);
		   		    }
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
	
	 $('form#forgotPwdForm').submit(function(e) {
		 var dataJson=JSON.stringify($('form#forgotPwdForm').serializeObject());
		consoleAlterQ(dataJson);
		 jQuery.ajax ({
			    url: ctx+'/myaccount/forgotPwd',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		 		    	$('#forgotPwdFormResponse').text(response.errorDto.stringError);
		   		    }
		   		    else{
		   		    }
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	 });
	 $('form#signupForm').submit(function(e) {
		 var dataJson=JSON.stringify($('form#signupForm').serializeObject());
		consoleAlterQ(dataJson);
		 jQuery.ajax ({
			    url: ctx+'/myaccount',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		 		    	$('#signupFormResponse').text(response.errorDto.stringError);
						showDiv(bSign);
		   		    }
		   		    else{
						userLoged=true;
						getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						showDiv(bHome);
		   		    }
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	 });
	 $('form#betForm').submit(function(e) {
		consoleAlterQ('betForm');
		// will pass the form date using the jQuery serialize function
		var url= ctx+'/bet/';
		$.post(url, $(this).serialize(), function(response) {
			if(response.errorDto!=null){
				$('#quinielaFormResponse').text(response.errorDto.stringError);
			}
			else{
				$('#0_1').removeAttr('checked');$('#0_X').removeAttr('checked');$('#0_2').removeAttr('checked');
				$('#1_1').removeAttr('checked');$('#1_X').removeAttr('checked');$('#1_2').removeAttr('checked');
				$('#2_1').removeAttr('checked');$('#2_X').removeAttr('checked');$('#2_2').removeAttr('checked');
				$('#3_1').removeAttr('checked');$('#3_X').removeAttr('checked');$('#3_2').removeAttr('checked');
				$('#4_1').removeAttr('checked');$('#4_X').removeAttr('checked');$('#4_2').removeAttr('checked');
				$('#5_1').removeAttr('checked');$('#5_X').removeAttr('checked');$('#5_2').removeAttr('checked');
				$('#6_1').removeAttr('checked');$('#6_X').removeAttr('checked');$('#6_2').removeAttr('checked');
				$('#7_1').removeAttr('checked');$('#7_X').removeAttr('checked');$('#7_2').removeAttr('checked');
				$('#8_1').removeAttr('checked');$('#8_X').removeAttr('checked');$('#8_2').removeAttr('checked');
				$('#9_1').removeAttr('checked');$('#9_X').removeAttr('checked');$('#9_2').removeAttr('checked');
				$('#10_1').removeAttr('checked');$('#10_X').removeAttr('checked');$('#10_2').removeAttr('checked');
				$('#11_1').removeAttr('checked');$('#11_X').removeAttr('checked');$('#11_2').removeAttr('checked');
				$('#12_1').removeAttr('checked');$('#12_X').removeAttr('checked');$('#12_2').removeAttr('checked');
				$('#13_1').removeAttr('checked');$('#13_X').removeAttr('checked');$('#13_2').removeAttr('checked');
				$('#14_1').removeAttr('checked');$('#14_X').removeAttr('checked');$('#14_2').removeAttr('checked');
				$('#quinielaFormResponse').text("Apuesta realizada correctamente");
			}
		});
		event.preventDefault(); // prevent actual form submit and page reload
	 });	 
	
   	 $('form#myDataForm').submit(function(e) {
	       consoleAlterQ('update:userAlterQForm');
	        
	        // will pass the form date using the jQuery serialize function
	        var url= ctx+'/myaccount/'+ $('#id').val();
	        $.post(url, $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#userAlterQFormResponse').text(response.errorDto.stringError);
	    		    }
	    		    else{
						$('#userAlterQFormResponse').text(response.userAlterQ.name+", tus datos han sido actualizados.");
	    		    }
			});
			event.preventDefault(); // prevent actual form submit and page reload
		 });
			  	 
	  	 $('form#balanceAlterQForm').submit(function(e) {
		       consoleAlterQ('update:balanceAlterQForm');
		        
	        // will pass the form date using the jQuery serialize function
		        var url= '${pageContext.request.contextPath}/myaccount/'+ $('#id').val();
		        $.post(url, $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#balanceAlterQFormResponse').text(response.errorDto.stringError);
	    		    }
	    		    else{
						$('#balanceAlterQFormResponse').text(response.userAlterQ.name+", tu saldo ha sido actualizado.");
	    		    }
			});
			event.preventDefault(); // prevent actual form submit and page reload
		 });	
	$("#goUp").click(function(){
		menuEvent($(this).text(), $(this).attr("href"));
    });
	
	$("#quinielaBtn").click(function(){
		menuEvent($(this).text(), $(this).attr("href"));
    });
	$("#myDataBtn").click(function(){
		menuEvent($(this).text(), $(this).attr("href"));
    });
	$("#myBalanceBtn").click(function(){
		menuEvent($(this).text(), $(this).attr("href"));
    });
	$("#myBetsBtn").click(function(){
		menuEvent($(this).text(), $(this).attr("href"));
    });
	
});

function doLogout(){
	 jQuery.ajax ({
		    url: ctx+'/logout',
		    type: "GET",
		    data: null,
		    contentType: "application/json; charset=utf-8",
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			cache: false,    //This will force requested pages not to be cached by the browser  
			processData:false, //To avoid making query String instead of JSON
		    success: function(response){
	   		    if(response.errorDto!=null){
	   		    }
	   		    else{
					userLoged=false;
					getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
	   		    }
		    }
		});
		event.preventDefault(); // prevent actual form submit and page reload

	showDiv(bHome);
	consoleAlterQ("LogOut: userLoged="+userLoged);     
	
}

function consoleAlterQ(text){
	if( (window['console'] !== undefined) ){
		console.log(text);
	}

}

