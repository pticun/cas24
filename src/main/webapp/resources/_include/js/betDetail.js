var sCompany ="";
var sCompanyDefault ="LAE";

//--------------------------------------------------------
//Variables a recoger desde BetDetailController
//--------------------------------------------------------
//var myBet ="1234567444444412";
//var myCompany = 1;
//var mySeason = 2015;
//var myRound = 8;

//--------------------------------------------------------


Array.prototype.uniqueArray = function()
{
	this.sort();
	var re=[this[0]];
	for(var i = 1; i < this.length; i++)
	{
		if( this[i] !== re[re.length-1])
		{
			re.push(this[i]);
		}
	}
	return re;
};


$(document).ready(function() {
	
	consoleAlterQ("betDetail ready.");
		
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
	
	getAdminData();
	getQuiniela();
	disableQuiniela();
	putBet();
	
});

function getAdminData(){
	consoleAlterQ("getAdminData.");
	window.company = myCompany;
	window.season = mySeason;
	window.round = myRound;
	sCompany=myScompany;
}



function disableQuiniela(){
	$("#0_1").attr("disabled", true);$("#0_X").attr("disabled", true);$("#0_2").attr("disabled", true);
	$("#1_1").attr("disabled", true);$("#1_X").attr("disabled", true);$("#1_2").attr("disabled", true);
	$("#2_1").attr("disabled", true);$("#2_X").attr("disabled", true);$("#2_2").attr("disabled", true);
	$("#3_1").attr("disabled", true);$("#3_X").attr("disabled", true);$("#3_2").attr("disabled", true);
	$("#4_1").attr("disabled", true);$("#4_X").attr("disabled", true);$("#4_2").attr("disabled", true);
	$("#5_1").attr("disabled", true);$("#5_X").attr("disabled", true);$("#5_2").attr("disabled", true);
	$("#6_1").attr("disabled", true);$("#6_X").attr("disabled", true);$("#6_2").attr("disabled", true);
	$("#7_1").attr("disabled", true);$("#7_X").attr("disabled", true);$("#7_2").attr("disabled", true);
	$("#8_1").attr("disabled", true);$("#8_X").attr("disabled", true);$("#8_2").attr("disabled", true);
	$("#9_1").attr("disabled", true);$("#9_X").attr("disabled", true);$("#9_2").attr("disabled", true);
	$("#10_1").attr("disabled", true);$("#10_X").attr("disabled", true);$("#10_2").attr("disabled", true);
	$("#11_1").attr("disabled", true);$("#11_X").attr("disabled", true);$("#11_2").attr("disabled", true);
	$("#12_1").attr("disabled", true);$("#12_X").attr("disabled", true);$("#12_2").attr("disabled", true);
	$("#13_1").attr("disabled", true);$("#13_X").attr("disabled", true);$("#13_2").attr("disabled", true);
	$("#14_0").attr("disabled", true);$("#14_1").attr("disabled", true);$("#14_2").attr("disabled", true);$("#14_3").attr("disabled", true);
	$("#15_0").attr("disabled", true);$("#15_1").attr("disabled", true);$("#15_2").attr("disabled", true);$("#15_3").attr("disabled", true);
}
function putBet(){
	var elem;
	consoleAlterQ("putBet() inicio");
	consoleAlterQ("putBet - myBet="+myBet);
	for (var i = 0; i< myBet.length; i++) {
        var caracter = myBet.charAt(i);
        if ((i==14)||(i==15)){
        	switch (caracter){
        	
        	case "1":
            	elem = "#"+i+"_0";
            	$(elem).prop("checked", true);
            	break;
    		case "2":
	        	elem = "#"+i+"_1";
	        	$(elem).prop("checked", true);
	        	break;
    		case "3":
	        	elem = "#"+i+"_0";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_1";
	        	$(elem).prop("checked", true);
	        	break;
    		case "4":
	        	elem = "#"+i+"_2";
	        	$(elem).prop("checked", true);
	        	break;
    		case "5":
	        	elem = "#"+i+"_0";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_2";
	        	$(elem).prop("checked", true);
	        	break;
    		case "6":
	        	elem = "#"+i+"_1";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_2";
	        	$(elem).prop("checked", true);
	        	break;
    		case "7":
	        	elem = "#"+i+"_0";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_1";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_2";
	        	$(elem).prop("checked", true);
	        	break;
    		case "8":
	        	elem = "#"+i+"_3";
	        	$(elem).prop("checked", true);
	        	break;
    		case "9":
	        	elem = "#"+i+"_0";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_3";
	        	$(elem).prop("checked", true);
	        	break;
    		case "a":
	        	elem = "#"+i+"_1";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_3";
	        	$(elem).prop("checked", true);
	        	break;
    		case "b":
	        	elem = "#"+i+"_0";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_1";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_3";
	        	$(elem).prop("checked", true);
	        	break;
    		case "c":
	        	elem = "#"+i+"_0";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_2";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_3";
	        	$(elem).prop("checked", true);
	        	break;
    		case "e":
	        	elem = "#"+i+"_1";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_2";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_3";
	        	$(elem).prop("checked", true);
	        	break;
    		case "f":
	        	elem = "#"+i+"_0";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_1";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_2";
	        	$(elem).prop("checked", true);
	        	elem = "#"+i+"_3";
	        	$(elem).prop("checked", true);
	        	break;
        	}
        }
        else{
        	switch (caracter){
        	case "1":
            	elem = "#"+i+"_2";
            	$(elem).prop("checked", true);
            	break;
        	case "2":
            	elem = "#"+i+"_X";
            	$(elem).prop("checked", true);
            	break;
        	case "3":
            	elem = "#"+i+"_X";
            	$(elem).prop("checked", true);
            	elem = "#"+i+"_2";
            	$(elem).prop("checked", true);
            	break;
        	case "4":
            	elem = "#"+i+"_1";
            	$(elem).prop("checked", true);
            	break;
        	case "5":
            	elem = "#"+i+"_1";
            	$(elem).prop("checked", true);
            	elem = "#"+i+"_2";
            	$(elem).prop("checked", true);
            	break;
        	case "6":
            	elem = "#"+i+"_1";
            	$(elem).prop("checked", true);
            	elem = "#"+i+"_X";
            	$(elem).prop("checked", true);
            	break;
        	case "7":
            	elem = "#"+i+"_1";
            	$(elem).prop("checked", true);
            	elem = "#"+i+"_X";
            	$(elem).prop("checked", true);
            	elem = "#"+i+"_2";
            	$(elem).prop("checked", true);
            	break;
        	}
        }
    }
   
	consoleAlterQ("putBet() final");
}

function consoleAlterQ(text){
	if( (window['console'] !== undefined) ){
		console.log(text);
	}

}



