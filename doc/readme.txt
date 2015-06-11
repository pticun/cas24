AterQ Administrator
-------------------
USER: goldbittledev@gmail.com
PASS: SysAlterQAdmin


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
	user/pwd:racsor/Segunda2
	

Para conectar con la BBDD:

mongo ds035338.mongolab.com:35338/racsor -u quinimobile -p quinimobile

para insertar una jornada:

db.round.insert({season:2013, round:9, games : [{pos:1, player1:"VILLARREAL", player2:"VALENCIA"}, {pos:2, player1:"ELCHE", player2:"GRANADA"}, {pos:3, player1:"AT. MADRID", player2:"BETIS"}, {pos:4, player1:"LEVANTE", player2:"ESPANYOL"}, {pos:5, player1:"MÁLAGA", player2:"CELTA"}, {pos:6, player1:"SEVILLA", player2:"OSASUNA"}, {pos:7, player1:"R. SOCIEDAD", player2:"ALMERÍA"}, {pos:8, player1:"RECREATIVO", player2:"MALLORCA"}, {pos:9, player1:"TENERIFE", player2:"DEPORTIVO"}, {pos:10, player1:"ZARAGOZA", player2:"ALAVÉS"}, {pos:11, player1:"MIRANDÉS", player2:"NUMANCIA"}, {pos:12, player1:"HÉRCULES", player2:"JAEN"}, {pos:13, player1:"MURCIA", player2:"CÓRDOBA"}, {pos:14, player1:"LUGO", player2:"SABADELL"}, {pos:15, player1:"BARCELONA", player2:"R. MADRID"}]});
db.round.insert({season:2013, round:10, games : [{pos:1, player1:"GETAFE", player2:"VALENCIA"}, {pos:2, player1:"AT. MADRID", player2:"ATHLETIC CLUB"}, {pos:3, player1:"LEVANTE", player2:"GRANADA"}, {pos:4, player1:"SEVILLA", player2:"CELTA"}, {pos:5, player1:"RAYO VALLECANO", player2:"R. MADRID"}, {pos:6, player1:"R. SOCIEDAD", player2:"OSASUNA"}, {pos:7, player1:"ALMERÍA", player2:"VALLADOLID"}, {pos:8, player1:"MALLORCA", player2:"LUGO"}, {pos:9, player1:"PONFERRADINA", player2:"RECREATIVO"}, {pos:10, player1:"EIBAR", player2:"ZARAGOZA"}, {pos:11, player1:"JAEN", player2:"MIRANDÉS"}, {pos:12, player1:"GIRONA", player2:"HÉRCULES"}, {pos:13, player1:"LAS PALMAS", player2:"ALCORCÓN"}, {pos:14, player1:"CÓRDOBA", player2:"SPORTING"}, {pos:15, player1:"MÁLAGA", player2:"BETIS"}]});
db.round.insert({season:2013, round:11, games : [{pos:1, player1:"GETAFE", player2:"VALENCIA"}, {pos:2, player1:"AT. MADRID", player2:"ATHLETIC CLUB"}, {pos:3, player1:"LEVANTE", player2:"GRANADA"}, {pos:4, player1:"SEVILLA", player2:"CELTA"}, {pos:5, player1:"RAYO VALLECANO", player2:"R. MADRID"}, {pos:6, player1:"R. SOCIEDAD", player2:"OSASUNA"}, {pos:7, player1:"ALMERÍA", player2:"VALLADOLID"}, {pos:8, player1:"MALLORCA", player2:"LUGO"}, {pos:9, player1:"PONFERRADINA", player2:"RECREATIVO"}, {pos:10, player1:"EIBAR", player2:"ZARAGOZA"}, {pos:11, player1:"JAEN", player2:"MIRANDÉS"}, {pos:12, player1:"GIRONA", player2:"HÉRCULES"}, {pos:13, player1:"LAS PALMAS", player2:"ALCORCÓN"}, {pos:14, player1:"CÓRDOBA", player2:"SPORTING"}, {pos:15, player1:"MÁLAGA", player2:"BETIS"}]});

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
          
Merge branch developer to branch master

step 1 - set local/master
step 2 - Merge with local/master - (select local/developer)
step 3 - pull remote master

Pull master to remote repository(openshift pre and openshift pro)
step 1 - pull master to remote repository

PRE quinimobile-goldbittle  user/pwd:goldbittledev@gmail.com/gbd2013
ssh://51dd73e55973ca1d89000148@quinimobile-goldbittle.rhcloud.com/~/git/quinimobile.git/

PRO quinigold  user/pwd:admin@quinigold.com/adminAlterQ2014
ssh://54a72909e0b8cd121c000053@quinigold-quinigold.rhcloud.com/~/git/quinigold.git/

********************************************
		MONGOLAB
********************************************		

MongoDB - PRE
	mongolab
	www.mongolab.com   user/pwd:racsor/Segunda2
	mongo ds035338.mongolab.com:35338/racsor -u quinimobile -p quinimobile
	NOFUNCIONA mongodump -h ds035338.mongolab.com:35338 -d racsor -u quinimobile -p quinimobile -o /home/kotto/Downloads/20150223_quinimobile 
	NOPROBAR EXPORTA A EXTENDED JSON Y NO SE IMPORTA BIEN EN ROCKMONGO
	*********mongoexport -h ds035338.mongolab.com:35338 -d racsor -c generalData -u quinimobile -p quinimobile -o /home/kotto/Downloads/20150223_generalData
	*********mongoexport -h ds035338.mongolab.com:35338 -d racsor -c round -u quinimobile -p quinimobile -o /home/kotto/Downloads/20150223_round
	*********mongoexport -h ds035338.mongolab.com:35338 -d racsor -c roundBets -u quinimobile -p quinimobile -o /home/kotto/Downloads/20150223_roundBets
	*********mongoexport -h ds035338.mongolab.com:35338 -d racsor -c roundRanking -u quinimobile -p quinimobile -o /home/kotto/Downloads/20150223_roundRanking
	*********mongoexport -h ds035338.mongolab.com:35338 -d racsor -c sessionalterq -u quinimobile -p quinimobile -o /home/kotto/Downloads/20150223_sessionalterq
	*********mongoexport -h ds035338.mongolab.com:35338 -d racsor -c useralterq -u quinimobile -p quinimobile -o /home/kotto/Downloads/20150223_useralterq
	mongodump -h ds035338.mongolab.com:35338 -d racsor -c generalData -u quinimobile -p quinimobile -o /home/kotto/Downloads/quinigold/
	mongodump -h ds035338.mongolab.com:35338 -d racsor -c round -u quinimobile -p quinimobile -o /home/kotto/Downloads/quinigold/
	mongodump -h ds035338.mongolab.com:35338 -d racsor -c roundBets -u quinimobile -p quinimobile -o /home/kotto/Downloads/quinigold/
	mongodump -h ds035338.mongolab.com:35338 -d racsor -c roundRanking -u quinimobile -p quinimobile -o /home/kotto/Downloads/quinigold/
	mongodump -h ds035338.mongolab.com:35338 -d racsor -c useralterq -u quinimobile -p quinimobile -o /home/kotto/Downloads/quinigold/
	
MongoDB - PRO
	mongolab
	www.mongolab.com   user/pwd:racsor/Segunda2
	En mongolab crear un clone de la base de datos de pre y crear usuario
	*********Database: quinigold User: admin Password: SN5JEtlTdewd
	*********https://quinigold-quinigold.rhcloud.com/rockmongo/index.php?action=login.index&host=0
	*********mongo ds039351.mongolab.com:39351/pro_quinigold -u quinigold -p quinigold
	NOPROBAR NO SE IMPORTA BIEN EN ROCKMONGO
	*********mongoimport -h ds039351.mongolab.com:39351 -d pro_quinigold -c generalData -u quinigold -p quinigold --file /home/kotto/Downloads/20150223_generalData
	mongorestore -h ds039351.mongolab.com:39351 -d pro_quinigold -c generalData -u quinigold -p quinigold /home/kotto/Downloads/quinigold/racsor/generalData.bson 	
	mongorestore -h ds039351.mongolab.com:39351 -d pro_quinigold -c round -u quinigold -p quinigold /home/kotto/Downloads/quinigold/racsor/round.bson 	
	mongorestore -h ds039351.mongolab.com:39351 -d pro_quinigold -c roundBets -u quinigold -p quinigold /home/kotto/Downloads/quinigold/racsor/roundBets.bson 	
	mongorestore -h ds039351.mongolab.com:39351 -d pro_quinigold -c roundRanking -u quinigold -p quinigold /home/kotto/Downloads/quinigold/racsor/roundRanking.bson 	
	mongorestore -h ds039351.mongolab.com:39351 -d pro_quinigold -c useralterq -u quinigold -p quinigold /home/kotto/Downloads/quinigold/racsor/useralterq.bson 	
	
	
*************************************
		OPENSHIFT
*************************************		

	PRE

		http://quinimobile-goldbittle.rhcloud.com/
		
		acceso a openshift con usuario goldbittledev@gmail.com/gbd2013
		
		gmail:
			user: goldbittledev@gmail.com
			pass: gold7bittle4
		
		SSH de openshift
			ssh://51dd73e55973ca1d89000148@quinimobile-goldbittle.rhcloud.com/~/git/quinimobile.git/
			
		Jenkins
			https://jenkins-goldbittle.rhcloud.com/me/configure
				User: admin
   				Password: 163JSUX4W3Uv

 
 	PRO
 		
 		http://quinigold-quinigold.rhcloud.com/
 		
 		acceso a openshift con user/pwd: admin@quinigold.com / adminAlterQ2014
 	
 	 	(MAIL)  www.zoho.com
		    administrator:admin@quinigold.com / adminAlterQ2014
			user:quinigold@quinigold.com / alterQ2014
	
 		SSH de openshift
 			ssh://54a72909e0b8cd121c000053@quinigold-quinigold.rhcloud.com/~/git/quinigold.git/
          
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

	ADM admin
	ARC arquitectura
	DOC doc
	MVC modelo-vista-controlador 
	WEB json-html
	MOB	mobile
	MDB mongo
	SEC security
	SYS system


Plantilla html5 (bootstrap-twitter) usado bajado de
	 http://www.alessioatzeni.com/blog/brushed-template/
	 
Hay más plantillas en http://bootswatch.com/
	la más parecida http://bootswatch.com/	 

JMeter-BlazeMeter-AlterQ
	Se ha realizado una grabación de un script simple:
		*ir a http://quinimobile-goldbittle.rhcloud.com
		*login nuevo@nuevo.es/nuevo
		*get a /myaccount/mail@mail.es/season/2014/round/1
		*hacer un /logout
	BlazeMeter (https://a.blazemeter.com)
	user/pwd:racsor@gmail.com/alterQ
	


esto es mi cambio	
no me enterado de lo que ha pasado
http://examples.javacodegeeks.com/enterprise-java/spring/mail-spring/spring-e-mail-support-gmail-smtp-server-example/

mi cambio
mi cambio