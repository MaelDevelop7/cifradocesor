# Práctica 10 - Desplazamiento de Caracteres

Este proyecto es una aplicación de consola en Java que permite cifrar o descifrar archivos de texto utilizando un desplazamiento de caracteres (tipo César).

## Estructura
```bash
├── pom.xml
├── src
│ └── main
│ └── java
│ └── com
│ └── p10
│ └── App.java
│ └── ficheros
│ └── archivo.txt
├── target
└── run.bat
```

## Requisitos

- Java 15+
- Maven

## Compilación

```bash
mvn clean package
```
Esto generará un .jar en la carpeta target/.

Ejecución
```bash

java -jar target/gruandmael_practica10-1.0-SNAPSHOT.jar
```
O bien con el script fourni :

```bash

./run.bat
Funcionalidad
```
Selecciona un archivo .txt en la carpeta src/ficheros.

Elige si deseas cifrar (c) o descifrar (d) el archivo.

Introduce un valor de desplazamiento (entero).

Introduce el nombre del archivo de salida.

Créditos

Práctica realizada por Maël Gruand para la asignatura de Programación.
