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

db.jornadas.insert({temporada:2013, jornada:9, partidos : [{pos:1, equipo1:"VILLARREAL", equipo2:"VALENCIA"}, {pos:2, equipo1:"ELCHE", equipo2:"GRANADA"}, {pos:3, equipo1:"AT. MADRID", equipo2:"BETIS"}, {pos:4, equipo1:"LEVANTE", equipo2:"ESPANYOL"}, {pos:5, equipo1:"MÁLAGA", equipo2:"CELTA"}, {pos:6, equipo1:"SEVILLA", equipo2:"OSASUNA"}, {pos:7, equipo1:"R. SOCIEDAD", equipo2:"ALMERÍA"}, {pos:8, equipo1:"RECREATIVO", equipo2:"MALLORCA"}, {pos:9, equipo1:"TENERIFE", equipo2:"DEPORTIVO"}, {pos:10, equipo1:"ZARAGOZA", equipo2:"ALAVÉS"}, {pos:11, equipo1:"MIRANDÉS", equipo2:"NUMANCIA"}, {pos:12, equipo1:"HÉRCULES", equipo2:"JAEN"}, {pos:13, equipo1:"MURCIA", equipo2:"CÓRDOBA"}, {pos:14, equipo1:"LUGO", equipo2:"SABADELL"}, {pos:15, equipo1:"BARCELONA", equipo2:"R. MADRID"}]});
db.jornadas.insert({temporada:2013, jornada:10, partidos : [{pos:1, equipo1:"GETAFE", equipo2:"VALENCIA"}, {pos:2, equipo1:"AT. MADRID", equipo2:"ATHLETIC CLUB"}, {pos:3, equipo1:"LEVANTE", equipo2:"GRANADA"}, {pos:4, equipo1:"SEVILLA", equipo2:"CELTA"}, {pos:5, equipo1:"RAYO VALLECANO", equipo2:"R. MADRID"}, {pos:6, equipo1:"R. SOCIEDAD", equipo2:"OSASUNA"}, {pos:7, equipo1:"ALMERÍA", equipo2:"VALLADOLID"}, {pos:8, equipo1:"MALLORCA", equipo2:"LUGO"}, {pos:9, equipo1:"PONFERRADINA", equipo2:"RECREATIVO"}, {pos:10, equipo1:"EIBAR", equipo2:"ZARAGOZA"}, {pos:11, equipo1:"JAEN", equipo2:"MIRANDÉS"}, {pos:12, equipo1:"GIRONA", equipo2:"HÉRCULES"}, {pos:13, equipo1:"LAS PALMAS", equipo2:"ALCORCÓN"}, {pos:14, equipo1:"CÓRDOBA", equipo2:"SPORTING"}, {pos:15, equipo1:"MÁLAGA", equipo2:"BETIS"}]});

db.roundBets.insert({season:2013, round:9, bets : [{user:"john.smith@mailinator.com", bet:"111111111111111"}, {user:"pepe.lopez@mailinator.com", bet:"222222222222222"}]});
	
	
	
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
