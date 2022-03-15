# Spring Microservice Archetype SB

Arquetipo para desarrollo de Microservicios REST API en Sping Boot Java 1.8

## Control de Versiones 
| Version 	| Etapa      		| Fecha      |
| ----------| ----------------- | ---------- |
| 1.0     	| Desarrollo 		| 21-03-2021 |
| 2.0		| Actualizacion JPA | 07-10-2021 |

## Aspectos Técnicos

Diseñado con las siguientes tecnologías:

* [Spring Boot] Microservicio REST
* [Lombok] Getter, Setter, Constructor y ToString
* [Actuator] Healthcheck
* [Swagger] Documentación
* [AMQP] Colas MQ Rabbit
* [JPA] Java Persistence API
* [Feing] Consumo Microservice REST

## Configuracion de aplicacion

El servicio necesita configuraciones propias de la aplicación y que se configuren los datos necesarios para hacer conexión a la base de datos del ambiente correspondiente . Por lo cual, se definen las siguientes variables de entorno.

### Configuracion de Ambiente

| Variable	| Descripcion	| Por Defecto	|
| --------- | ------------- | ------------- |  
| HTTP_PORT	| Puerto por el cual se levantar la aplicacion	| 8080	|
| LOG_LEVEL	| Nivel de log que utilizará el aplicativo	| DEBUG	|
| DB_HOST	| Ip servidor base de datos	| 	|
| DB_PORT	| Puerto de salida servidor base de datos	| 	|
| DB_SERVICE_NAME	| Nombre de base de datos	|	|
| DB_DATASOURCE_USER	| Usuario para la conexión de la base de datos	| 	|
| DB_DATASOURCE_PASSWORD	| Contraseña del usuario para la conexión de la base de datos	| 	|
| DB_DATASOURCE_DRIVER	| Driver para que el JDBC haga la conexión de la base de datos	| oracle.jdbc.driver.OracleDriver	|
| DB_TIMEOUT | Tiempo de espara conexion (milisegundos) | 30000 |
| DB_MAX_LIFE_TIME | Tiempo maximo vida de una conexion (milisegundos) | 1800000 |
| DB_MAXIMUM_POOL_SIZE | Maximo conexiones | 10 |
| DB_TIME_OUT_IDLE | Tiempo vida de conexiones inactivas (milisegundos) | 600000 |
| DB_MINIMUM_IDLE | Minimo de conexiones que se mantienen abiertas | 10 |
| ACTUATOR_SHUTDOWN	| Activa y desactiva el actuador	| false	|
| ACTUATOR_EXPOSE_INC	| Expone via http el endpoint del actuador	| *	|
| ACTUATOR_EXPOSE_EXC	| Excluye de exponer via http el endpoint del actuador	| none	|
| ACTUATOR_DETAIL	| Muestra detalles adicionales del actuador	| always	| 
| RABBITMQ_HOST |Ip o Nombre del servidor RabbitMQ |  |
| RABBITMQ_PORT | Puerto del servidor RabbitMQ ||
| RABBITMQ_USER | Usuario para la conexión de RabbitMQ |  |
| RABBITMQ_PASSWORD | Contraseña para la conexión de RabbitMQ|  |
| RABBITMQ_IN_QUEUE	| QUEUE Name	| |
| MQ_CORE_POOL_SIZE | Minimo Pool Conexiones MQ | 5 |
| MQ_MAX_POOL_SIZE | Maximo Pool Conexiones MQ | 1000 |
| MQ_QUEUE_CAPACITY | Maximo Capacidad QUEUE | 1000 |
| MQ_ON_SHUTDOWN | ON/OFF | false |