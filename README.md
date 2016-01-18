# PTSI-CityWeatherWebApp
Proyecto de la asignatura Protocolos, Tecnologías y Servicios de Internet que consistirá en una aplicación web para consultar pronósticos meteorológicos y que le permita al usuario guardar ciudades favoritas para recibir alertas sobre las previsiones.

PASOS PARA COMPILAR, PROBAR Y SUBIR EL PROYECTO A GOOGLE APP ENGINE
-------------------------------------------------------------------
1. Pasos para compilar el proyecto GAE, que hace uso de Swagger y Jersey:
	mvn clean 
	mvn -Dmaven.test.skip=true package
	mvn -Dmaven.test.skip=true appengine:devserver
2. Para ejecutar el servicio:
	a. Copiar el fichero CityWeatherWebApp.war generado en carpeta target a webapps en Tomcat
	b. Lanzar Tomcat: catalina.bat run
3. Para probar el servicio:
	- Ir a un navegador y probar el servicio RESTful:
		a. http://localhost:8080/
		b. http://localhost:8080/CityWeatherWebApp/index
4. Para subir la aplicación a Google App Engine	
	a. Ir Google Cloud Platform Developer Console: https://console.cloud.google.com/home/dashboard
	b. Crear un nombre de app: CityWeatherWebApp
	c. Actualizar el fichero CityWeatherWebApp\src\main\webapp\WEB-INF\appengine-web.xml
		- Campo <application>CityWeatherWebApp</application>
	d. Ejecutar el comando en el directorio donde está pom.xml: 
		mvn -Dmaven.test.skip=true appengine:update
	e. Probar la aplicación yendo a: http://CityWeatherWebApp.appspot.com/. 

Explicación de error que se producía al subir la app
----------------------------------------------------

Se producía un error como el siguiente:
loading application configuration:Unable to assign value does not match expression '^(?:^(\-]{0,62}[a-z\d]$)$'

La respuesta más votada en: http://stackoverflow.com/questions/18239234/bad-request-when-updating-appengine-with-mvn-appengineupdate

Es la solución al problema.