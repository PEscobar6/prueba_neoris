
# Neoris Test

Esta es mi solución a la prueba técnica arquitectura Microservicio (2023) entregada por Neoris

El desarollo se hizo bajo contendores Docker, en el cual se encuentra los dos microservicios en diferentes contenedores, un contenedor para la base de datos y otro para una gestor de base de datos llamado pgAdmin

## Docker
En el archivo **docker-compose.yml** se encuentra toda la configuración de red de los contendores y ella los datos de configuración para la base de datos. Se dejó esta información expuesta dado a que se alcanzó a realizar la configuración de secrets en herramientas como AWS.

## Levantar servicio localmente
Primero se debe clonar el proyecto

```bash
  git clone https://github.com/PEscobar6/prueba_neoris
```

Navegar al directorio del proyecto

```bash
  cd prueba_neoris
```

Contruir y descargar las imagenes para los contenedores

```bash
  docker-compose build
```

Levantar los contenedores para ejcutar los microservicios y la base de datos

```bash
  docker-compose up
```

Se puede realizar todo con un solo comando
```bash
  docker-compose up -d --build
```

## OpenApi Definition
Para ver los endpoints creados y el ampping de cada uno ellos podemos acceder a las iguientes urls

OpenApi del contenedor Admin (usuarios y clientes)
```bash
  localhost:8080/swagger-ui/index.html
```

OpenApi del contenedor Transactions (Cuentas y Movimientos)
```bash
  localhost:8082/swagger-ui/index.html
```

## Base de datos
En el proyecto se establece que se creo a través de una imagen en docker

Los datos de acceso para la base de datos son:
```bash
  username: Test
  password: 123456
```

Los datos para la interfaz de pgAdmin son:
```bash
  email: test@google.com
  password: 123456
```

También se deja a través del archivo **db_prueba.sql** el query correspondiente del backup de las pruebas y la definición de las tablas.


