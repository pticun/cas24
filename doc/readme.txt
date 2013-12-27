MongoDB 2.2 database added.  
	Please make note of these credentials:
	   Root User:     admin
	   Root Password: yAMD7yIA3dbH
	   Database Name: quinimobile
	Connection URL: mongodb://$OPENSHIFT_MONGODB_DB_HOST:$OPENSHIFT_MONGODB_DB_PORT/
	
	
	 You can get a list of cartridges by running
	
	rhc cartridge list
	
	You can manage the cartridge by running one of these commands
	
	rhc cartridge start -a quinimobile -c mongodb-2.2
	rhc cartridge stop -a quinimobile -c mongodb-2.2
	rhc cartridge restart -a quinimobile -c mongodb-2.2
	rhc cartridge reload -a quinimobile -c mongodb-2.2
	rhc cartridge status -a quinimobile -c mongodb-2.2
	
	You can remove the cartridge by running the following command.
	Warning: make sure you've backed up any data you wish to keep before running this command
	
	rhc cartridge remove -a quinimobile -c mongodb-2.2

	
	
	MongoDB in cloud
	
	https://mongolab.com
	user/pwd:racsor/Primera1
	

Para conectar con la BBDD:

mongo ds035338.mongolab.com:35338/racsor -u quinimobile -p quinimobile

para insertar una jornada:

db.round.insert({season:2013, round:9, games : [{pos:1, player1:"VILLARREAL", player2:"VALENCIA"}, {pos:2, player1:"ELCHE", player2:"GRANADA"}, {pos:3, player1:"AT. MADRID", player2:"BETIS"}, {pos:4, player1:"LEVANTE", player2:"ESPANYOL"}, {pos:5, player1:"MÁLAGA", player2:"CELTA"}, {pos:6, player1:"SEVILLA", player2:"OSASUNA"}, {pos:7, player1:"R. SOCIEDAD", player2:"ALMERÍA"}, {pos:8, player1:"RECREATIVO", player2:"MALLORCA"}, {pos:9, player1:"TENERIFE", player2:"DEPORTIVO"}, {pos:10, player1:"ZARAGOZA", player2:"ALAVÉS"}, {pos:11, player1:"MIRANDÉS", player2:"NUMANCIA"}, {pos:12, player1:"HÉRCULES", player2:"JAEN"}, {pos:13, player1:"MURCIA", player2:"CÓRDOBA"}, {pos:14, player1:"LUGO", player2:"SABADELL"}, {pos:15, player1:"BARCELONA", player2:"R. MADRID"}]});
db.round.insert({season:2013, round:10, games : [{pos:1, player1:"GETAFE", player2:"VALENCIA"}, {pos:2, player1:"AT. MADRID", player2:"ATHLETIC CLUB"}, {pos:3, player1:"LEVANTE", player2:"GRANADA"}, {pos:4, player1:"SEVILLA", player2:"CELTA"}, {pos:5, player1:"RAYO VALLECANO", player2:"R. MADRID"}, {pos:6, player1:"R. SOCIEDAD", player2:"OSASUNA"}, {pos:7, player1:"ALMERÍA", player2:"VALLADOLID"}, {pos:8, player1:"MALLORCA", player2:"LUGO"}, {pos:9, player1:"PONFERRADINA", player2:"RECREATIVO"}, {pos:10, player1:"EIBAR", player2:"ZARAGOZA"}, {pos:11, player1:"JAEN", player2:"MIRANDÉS"}, {pos:12, player1:"GIRONA", player2:"HÉRCULES"}, {pos:13, player1:"LAS PALMAS", player2:"ALCORCÓN"}, {pos:14, player1:"CÓRDOBA", player2:"SPORTING"}, {pos:15, player1:"MÁLAGA", player2:"BETIS"}]});

db.roundBets.insert({season:2013, round:9, bets : [{user:"john.smith@mailinator.com", bet:"111111111111111"}, {user:"pepe.lopez@mailinator.com", bet:"222222222222222"}]});

Consulta Apuesta de Usuario para la temporada 2013 y la jornada 9	
db.runCommand({aggregate:"roundBets", pipeline:[
{$unwind:"$bets"}, 
{$match:
       {"bets.user":"john.smith@mailinator.com",
        "round": 9,
        "season": 2013}
},
{$group: 
	{
		_id:"$_id", 
		round:{$first : "$round"}, 
		season:{$first : "$season"}, 
		bets: {$push: "$bets"}
	}
}
]})

LLAMADAS A LAS FUNCIONES DEFINIDAS EN EL  "CONTROLER BET"
http://localhost:8080/quinimobile/bet/bets?season=2013&round=9
http://localhost:8080/quinimobile/bet/betsUser?season=2013&round=9&user=pepe.lopez@mailinator.com
http://localhost:8080/quinimobile/bet/addBet?season=2013&round=9&user=pepe.lopez@mailinator.com&bet=XXX111222XXX1X2
http://localhost:8080/quinimobile/bet/delUserBet?season=2013&round=9&user=pepe.lopez@mailinator.com&bet=XXX111222XXX1X2
http://localhost:8080/quinimobile/bet/delUserBets?season=2013&round=9&user=pepe.lopez@mailinator.com
http://localhost:8080/quinimobile/bet/delAllBets?season=2013&round=9


	
	
http://quinimobile-goldbittle.rhcloud.com/

acceso a openshift
con usuario goldbittledev@gmail.com/gbd2013

SSH de openshift
	ssh://51dd73e55973ca1d89000148@quinimobile-goldbittle.rhcloud.com/~/git/quinimobile.git/



*********************************************
GIT
*********************************************

Create brach Strategy MERGE  (parece que asi nos ahorramos un paso cuando hacemos el pull)

PASO1 - Bajar última versión:
          - Boton Derecho sobre el proyecto--Team--Fecth from Upstream
PASO2 - Fusionar los cambios con nuestra rama de developer:
          - Boton Derecho sobre el proyecto--Team--Merge
          		Local = developer
          		Remote Traking = origin/developer
PASO3 - Hacemos los cambios en los ficheros oportunos
PASO4 - Subir los cambios: 
          - Boton Derecho sobre el proyecto--Team--Push to Upstream
          - Ponemos en la descripción un comentario que identifique la modificacion
          - Push & Commit
          
          
====
1 = 100		=4
X = 010		=2
2 = 001		=1
1X=110		=6
X2 = 011	=3
12=101		=5
1X2 =111	=7
==

Comentarios GIT
	ARC arquitectura
	MVC modelo-vista-controlador 
	WEB json-html
	MOB	mobile
	
