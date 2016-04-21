var BetTypeEnum = {
		BET_NORMAL: 0,
		BET_FINAL: 10,
		BET_AUTOMATIC: 20,
		BET_RESULT:30,
		BET_FIXED:40,
		};

var RolNameEnum={
		ROL_SUPER_ADMIN:1000,
		ROL_ADMIN:100,
		ROL_USERADVANCED:20,
		ROL_USER:10,
		ROL_PUBLIC:0,
};


	window.DEFECT_COMPANY = 0;
	window.round=0;
	window.season=0;
	window.company=1; // "quiniGoldClassic = 1" depends environment
	window.rols="";
	window.idUserAlterQ="";

	window.loadBet=true;
	window.loadBetUser=true;
	window.loadCompanies=true;

	window.userLoged=false;

	window.companySelected = false;

	window.admin = false;
	window.superAdmin = false;
	
	window.quinielaFinal = false;

	function RequestUser(){
		this.company=window.DEFECT_COMPANY;
		this.round=window.roun;
		this.season=window.season;
		this.idUserAlterQ= window.idUserAlterQ;
	}
	
	var requestUserSession=new RequestUser ();
	
	function fillUserData(response){
		
		
		window.idUserAlterQ=response.userAlterQ.id;
		requestUserSession.idUserAlterQ =window.idUserAlterQ;
		
		$('#idData').val(response.userAlterQ.id);
		$('#nameData').val(response.userAlterQ.name);
		$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
		$('#nickData').val(response.userAlterQ.nick);
		$('#idSurnamesData').val(response.userAlterQ.surnames);
		$('#birthDateData').val(response.userAlterQ.birthday);
		$('#idCardData').val(response.userAlterQ.idCard);
		$('#citySign').val(response.userAlterQ.city);
		$('#typeIDSign').val(response.userAlterQ.typeID);
		
		$('#idSaldo').val(response.userAlterQ.id);
		$('#balanceSaldo').val(response.userAlterQ.balance);	
		window.rols=response.userAlterQ.rols;
		
		//if company == 0 (defect company) all user are admin 
		if (window.company!= window.DEFECT_COMPANY){
			window.admin = window.rols.some(function(boy) { return hasRolCompanyValue(boy, 100, window.company); });
		}
		window.superAdmin = window.rols.some(function(boy) { return hasRolCompanyValue(boy, 1000, window.DEFECT_COMPANY); });

		consoleAlterQ("Admin:"+window.admin);
		consoleAlterQ("superAdmin:"+window.superAdmin);
//		consoleAlterQ("tienes rol:"+rols.some(function(boy) { return hasValue(boy, "rol", 100); }));	
//		consoleAlterQ("tienes rolcompany:"+rols.some(function(boy) { return hasRolCompanyValue(boy, 1000, 0); }));	
		
	}


	function hasValue(obj, key, value) {
//		consoleAlterQ(obj+"-"+key+"-"+value);
	    return obj.hasOwnProperty(key) && obj[key] === value;

	}
	function hasRolCompanyValue(obj, rolValue, companyValue) {
//		consoleAlterQ("hasRolCompanyValue: obj[rol]=" + obj["rol"] + " rolValue="+rolValue + " obj[company]=" + obj["company"] + " companyValue="+companyValue);
		return (obj.hasOwnProperty("rol") && obj["rol"]) == rolValue && ( obj.hasOwnProperty("company") && obj["company"] == companyValue);
		
	}

	function fillRoundSeasonCompany(response){

		window.round = response.adminData.round;
		window.season = response.adminData.season;
		
		requestUserSession.round =window.round;
		requestUserSession.season =window.season;
//	    company = response.adminData.company;
		consoleAlterQ("fillRoundSeasonCompany: round="+window.round+" season="+window.season);	
	}

	function initializeVars(){
		window.round=0;
		window.season=0;
		window.company=1; //companyDefault "quiniGoldClassic = 1"
		window.rols="";
		window.idUserAlterQ="";

		window.loadBet=true;
		window.loadBetUser=true;
		consoleAlterQ("loadBetUser: TRUE");
		window.loadCompanies=true;

		window.userLoged=false;

		window.companySelected = false;

		window.admin = false;
		window.superAdmin = false;

		//delete combo company
		 $("#companyToChoose option:selected").remove();

		requestUserSession.idUserAlterQ =window.idUserAlterQ;
		requestUserSession.round =window.round;
		requestUserSession.season =window.season;
		requestUserSession.company =window.company;

	}
