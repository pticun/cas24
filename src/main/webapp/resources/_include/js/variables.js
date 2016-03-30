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
