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
	
	
	
	
http://quinimobile-goldbittle.rhcloud.com/

acceso a openshift
con usuario goldbittledev@gmail.com/gbd2013



*********************************************
GIT
*********************************************

Create brach Strategy NONE

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
