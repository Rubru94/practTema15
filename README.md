# PRACTICA TEMA 15 - Integración y entrega continua

Se considerará un proyecto basado en un modelo de desarrollo TBD (Trunk Based Development), por lo tanto los commits se harán siempre en máster, a excepción de *features* cortas en el tiempo que sí se sacarán a pequeñas ramas para posteriormente integrar en máster mediante *Pull Request*.

Debido al elevado número de commits en máster que se realizarán a lo largo del proyecto, por la naturaleza de desarrollo del mismo, se considera oportuno, por su barato coste de tiempo, pasar test unitarios con cada *push* o *pull request* a máster, por ello se definirá un *job* para tal fin.

Por otra parte, además, el proyecto cuenta con tests de integración (*MockMVC*) y también E2E (*RestAssured*), que junto con los análisis de calidad y seguridad implementados con *SonarCloud*, se dispararán una vez al día a las 21:00h, para favorecer que la ejecución de pipelines con mayor demora de tiempo no interfieran en las horas de desarrollo del equipo del proyecto.

También contaremos con empaquetado y publicación de artefactos de desarrollo y releases, de manera programada y manual respectivamente, en un repositorio de artefactos *Nexus*.

La integración continua se implementa con *Jenkins* en *Github*, y el proyecto se despliega empleando *docker-compose*, creando la imagen de la aplicación al momento de construirla y utilizando una imagen de *mysql* para la base de datos.

El entorno de integración contará con 4 jobs, que se ejecutarán según el esquema siguiente:  

```
JOB DE MÁSTER ---> Evento push o pull request desde repositorio
```

```
NIGHTLY ---> Programado para su ejecución todos los días a las 21:00h.
```

```
NEXUS RELEASE ---> Ejecución manual desde Jenkins, cada vez que se desee publicar una release.
```

```
NEXUS SNAPSHOT ---> Programado para su ejecución todos los días a las 00:00h.
```

## JOB DE MÁSTER - STAGES

* **Preparation**: Se clona el repositorio
      
* **Create app**: Se ejecuta *docker-compose build* 
      
* **Start app**: Se lanzan los contenedores, ejecutando *docker-compose up -d*
      
* **Unit test**: Se lanzan únicamente los test unitarios
      
* **Post**: Se archivan los distintos logs, separando aplicación y base de datos, y se ejecuta *docker-compose down*
      
* **Create jar**: Construimos el *jar* de nuestra aplicación sin pasar test
      
* **Archive jar**: Archivamos en el entorno de CI el *jar* actualizado

## NIGHTLY - STAGES

* **Preparation**: Se clona el repositorio
      
* **Create app**: Se ejecuta *docker-compose build* 
      
* **Start app**: Se lanzan los contenedores, ejecutando *docker-compose up -d*
      
* **Integration test**: Se lanzan los test con *MockMVC*
      
* **E2E test**: Se lanzan los test con *RestAssured*

* **Sonarqube**: Se ejecuta el análisis de calidad y seguridad del código con *Sonarcloud*

* **Post**: Se archivan los distintos logs, separando aplicación y base de datos, y se ejecuta *docker-compose down*
      
* **Build image Docker**: Construimos la imágen *docker* con la *tag* pasada por parámetro
      
* **Push image to DockerHub**: Subimos la imágen a *DockerHub*

## NEXUS RELEASE - STAGES

* **Preparation**: Se clona el repositorio
      
* **Build Java**: Se construye la aplicación java sin pasar test
      
* **Obtain pom version**: Recuperamos la versión de la aplicación leyendo el *pom.xml*

* **Publish Release on Nexus**: Se publica el *.jar* construido como *release* con la versión pasada por parámetro

## NEXUS SNAPSHOT - STAGES

* **Preparation**: Se clona el repositorio

* **Tag repository**: Se modifica la versión de la aplicación en el *pom.xml* según la etiqueta pasada por parámetro
      
* **Build Java**: Se construye la aplicación java sin pasar test
      
* **Obtain pom version**: Recuperamos la versión de la aplicación leyendo el *pom.xml*

* **Publish Release on Nexus**: Se publica el *.jar* construido como *SNAPSHOT* con la versión pasada por parámetro

###################################

**Nota:**
```
La práctica se lleva a cabo ejecutando JENKINS en local, en el puerto 8090, y exponiendolo a una URL pública mediante la herramienta ngrok. 

La aplicación se despliega empleando docker-compose en el puerto 8080 local y utiliza una BBDD mysql que ocupará el puerto 3306 local.

Sonar no se despliega en local, se utiliza su versión cloud.

Nexus se encuentra desplegado en el puerto 8081 local.
```