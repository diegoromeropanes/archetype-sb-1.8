# Spring Microservice Archetype SB 1.8

Arquetipo para desarrollo de Microservicios REST API en Sping Boot Java 1.8


## Control de Versiones 

| Version 	| Etapa      										| Fecha      |
| ----------| ------------------------------------------------- | ---------- |
| 1.0     	| Desarrollo 										| 21-03-2021 |
| 2.0		| Actualizacion JPA y Consumo Microservicio REST	| 07-10-2021 |
| 2.0		| Actualizacion Repo								| 23-10-2021 |


## Aspectos Técnicos

Diseñado con las siguientes tecnologías y dependencias:

* [Spring Boot] Microservicio REST
* [Lombok] Getter, Setter, Constructor y ToString
* [OJDBC] Driver Conexión Oracle
* [Actuator] Healthcheck
* [Swagger] Documentación
* [AMQP] Colas MQ Rabbit
* [JPA] Java Persistence API
* [Feing] Consumo Microservice REST


## Estructura

La estructura del proyecto que se creará es la siguiente:

```
${artifactId}
	--src
		-- main
			-- Java
				-- ${package}
					-- amqp
						-- listener
							-- impl
						-- sender
							-- impl
					-- annotation
						-- impl
					-- configuration
					-- controller
					-- dto
					-- entity
					-- enums
					-- repository
					-- request
					-- response
					-- rest
					-- service
						-- impl
					-- util
			-- resource
				-- application.properties
	-- pom.xml
	-- README.md
```


## Packages

|Package						| Descripción |
|-------------------------------|-------------|
| ${package}					| Contiene la clase Java que inicia el servidor, para correr aplicaciones Spring Boot en Tomcat |
| ${package}.amqp.listener		| Contiene las Interfaces Java que escuchan Colas MQ |
| ${package}.amqp.listener.impl	| Contiene las Clases Java que implementan las interfaces escuchan Colas MQ |
| ${package}.amqp.sender		| Contiene las Interfaces Java que envian a Colas MQ |
| ${package}.amqp.sender.impl	| Contiene las clases Java que implementan las interfaces que envian a Colas MQ |
| ${package}.annotation			| Coniene las interfaces Java que definen anotaciones |
| ${package}.annotation.impl	| Coniene las claves Java que implementan las interfaces que definen anotaciones |
| ${package}.configuration		| Contiene las clases Java que permiten realizar configuraciones adicionales, para despliegue de swagger, colas MQ, varios JDBC u otros |
| ${package}.controller			| Contiene las clases Java que identifican los controladores (métodos) a ser invocados en el servicio a construir |
| ${package}.dto				| Contiene las clase Java que permite mapear el objeto resultando de una consulta SQL |
| ${package}.entity				| Contiene las clases Java que definen la estructura de una tabla a consumir mediante el repository |
| ${package}.enums				| Contiene los enums definidos para el proyecto |
| ${package}.repository			| Contiene las Interfaces que definen los métodos a ser implementados, para querys a BD |
| ${package}.request			| Contiene las clases Java que define el request a recibir para los métodos implementados |
| ${package}.response			| Contiene las clases Java que define el objeto a retornar a la consulta de los métodos implementados |
| ${package}.rest				| Contiene las Interfaces Java que definen el consumo de Microservice REST	|
| ${package}.service			| Contiene las Interfaces que definen los diversos servicios utilizados para obtener la respuesta a los controladores implementados |
| ${package}.service.impl		| Contiene las clases Java que implementan las interfaces definidas utilizadas para obtener la respuesta a los controladores implementados |
| ${package}.util				| Contiene las clases Java que permite implementar funcionalidades transversales a los servicios, que den utilidad a los mismos |


## Comando Maven Generación Proyecto

```
mvn install

```


```
mvn archetype:generate -DarchetypeGroupId=cl.salcobrand.microservice.archetype -DarchetypeArtifactId=spring-microservice-archetype-sb-1.8

```