# Plantilla de Microservicio Java — Bootcamp

Plantilla base para desarrollar microservicios REST utilizando **Java, Spring Boot y Maven**.

Este repositorio proporciona un punto de partida para que los alumnos creen sus propios microservicios durante las actividades del bootcamp. Incluye una estructura inicial de proyecto, un contrato de API con OpenAPI, pruebas y herramientas de apoyo para la calidad del código, la observabilidad y la generación de un inventario de componentes de software (SBOM).

La plantilla es intencionalmente sencilla: permite concentrarse en el desarrollo del microservicio y ampliar sus funcionalidades conforme avanzan las actividades.

## Objetivo

Proporcionar una estructura común para iniciar el desarrollo de microservicios durante el bootcamp, facilitando la aplicación progresiva de prácticas de desarrollo, pruebas, calidad y seguridad de software.

## Alcance

Este repositorio contiene exclusivamente la plantilla de código para desarrollar un microservicio.

No contiene la infraestructura completa del laboratorio ni implementa por sí mismo la solución de tienda en línea utilizada como ejemplo en el bootcamp.

El repositorio proporciona una base que los alumnos pueden copiar, adaptar y ampliar según los requerimientos de cada ejercicio.

## Tecnologías y herramientas

La plantilla utiliza las siguientes tecnologías y herramientas:

- **Java:** lenguaje de programación.
- **Spring Boot:** framework para desarrollar la aplicación.
- **Apache Maven:** gestión de dependencias y ciclo de construcción.
- **OpenAPI:** definición del contrato de la API REST.
- **Swagger UI:** documentación interactiva de la API.
- **Spring Boot Actuator y Micrometer:** información de salud y métricas de la aplicación.
- **Checkstyle y PMD:** análisis de estilo y revisión estática del código.
- **JaCoCo:** medición de cobertura de pruebas.
- **CycloneDX:** generación de un inventario de componentes de software (SBOM).
- **Docker:** construcción y ejecución de la aplicación en un contenedor.

## Estructura del proyecto

La estructura principal del repositorio es la siguiente:

```text
.
├── checkstyle.xml
├── Dockerfile
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── bootcamp
    │   │           └── template
    │   │               ├── controller
    │   │               └── TemplateApplication.java
    │   └── resources
    │       ├── application.properties
    │       ├── logback-spring.xml
    │       └── openapi
    │           └── openapi.yaml
    └── test
```

Los principales elementos son:

- `pom.xml`: define las dependencias, los plugins y las tareas de construcción del proyecto.
- `src/main/java`: contiene el código fuente de la aplicación.
- `controller`: contiene la implementación de los endpoints del microservicio.
- `src/main/resources/openapi/openapi.yaml`: define el contrato de la API.
- `src/main/resources/application.properties`: contiene la configuración de Spring Boot.
- `src/main/resources/logback-spring.xml`: configura el sistema de registro de eventos.
- `src/test`: contiene las pruebas automatizadas.
- `checkstyle.xml`: define las reglas de estilo del código.
- `Dockerfile`: define cómo construir la imagen de contenedor.

## Requisitos previos

Para trabajar con el proyecto necesitas:

- Java Development Kit (JDK) 17 o superior.
- Apache Maven 3.8 o superior.
- Git para clonar el repositorio.
- Docker Engine, si deseas construir y ejecutar la aplicación como contenedor.

Los comandos de esta guía están pensados para una terminal Linux. En otros sistemas operativos pueden requerirse ajustes menores.

## Obtener el código

Clona el repositorio y entra en el directorio del proyecto:

```bash
git clone https://github.com/bootcamp-att/template.git

cd template
```

Comprueba que Java y Maven estén instalados:

```bash
java -version
mvn -version
```

## Ejecutar el microservicio

### 1. Compilar el proyecto

Ejecuta:

```bash
mvn clean compile
```

Maven descarga las dependencias necesarias, compila el código y ejecuta las tareas de generación de código configuradas en el proyecto.

### 2. Ejecutar las pruebas

Para ejecutar las pruebas automatizadas:

```bash
mvn test
```

Si alguna prueba falla, revisa la salida de Maven antes de continuar.

### 3. Iniciar la aplicación

Ejecuta el microservicio localmente:

```bash
mvn spring-boot:run
```

La aplicación se configura para atender peticiones en el puerto `8080`.

### 4. Probar el endpoint de ejemplo

En otra terminal, ejecuta:

```bash
curl http://localhost:8080/hola
```

La respuesta esperada es:

```text
Hola
```

Este endpoint es un ejemplo inicial que puedes modificar o sustituir conforme a los requerimientos de tu microservicio.

Para detener la aplicación, utiliza `Ctrl+C` en la terminal donde se está ejecutando.

## Contrato de API con OpenAPI

La plantilla utiliza un contrato OpenAPI para describir la interfaz REST del microservicio.

El archivo de referencia es:

```text
src/main/resources/openapi/openapi.yaml
```

El contrato permite documentar aspectos como:

- Rutas y métodos HTTP.
- Parámetros de entrada.
- Estructura de las peticiones y respuestas.
- Códigos de estado HTTP.
- Esquemas de datos.

### Flujo de trabajo

Cuando necesites modificar o agregar un endpoint:

1. Revisa y actualiza el contrato en `openapi.yaml`.
2. Ejecuta la compilación para generar el código definido por la configuración de OpenAPI.
3. Implementa la lógica correspondiente en el controlador.
4. Ejecuta las pruebas para verificar el comportamiento de la API.

La configuración actual genera código asociado a la interfaz `HolaApi` y utiliza `HolaController` como implementación. Si cambias los nombres o las operaciones del contrato, revisa también las clases generadas y la implementación correspondiente.

### Documentación interactiva

Con la aplicación en ejecución, consulta las siguientes direcciones:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

Estas direcciones dependen de la configuración de la aplicación y de las dependencias incluidas en el proyecto.

## Calidad del código y pruebas

La plantilla incorpora herramientas que puedes utilizar durante el desarrollo para revisar el código y evaluar las pruebas automatizadas.

### Checkstyle

Permite revisar el cumplimiento de las reglas de estilo definidas en `checkstyle.xml`.

Ejecuta:

```bash
mvn checkstyle:checkstyle
```

El reporte HTML se genera en:

```text
target/site/checkstyle.html
```

### PMD

Permite analizar el código fuente e identificar posibles problemas, como código innecesario y patrones que pueden dificultar el mantenimiento.

Ejecuta:

```bash
mvn pmd:pmd
```

El reporte HTML se genera en:

```text
target/site/pmd.html
```

### JaCoCo

Permite medir la cobertura del código ejecutado por las pruebas.

Ejecuta:

```bash
mvn clean test jacoco:report
```

El reporte HTML se genera en:

```text
target/site/jacoco/index.html
```

La cobertura es un indicador útil, pero un porcentaje elevado no garantiza por sí solo que las pruebas sean suficientes ni que el software sea seguro.

## Inventario de componentes de software (SBOM)

La plantilla incluye el plugin CycloneDX para generar un **Software Bill of Materials (SBOM)**.

El SBOM describe los componentes de software identificados durante la construcción del proyecto. Esta información puede utilizarse como apoyo para analizar dependencias y gestionar riesgos asociados a la cadena de suministro de software.

Para generar el inventario, ejecuta:

```bash
mvn package
```

Los archivos generados se encuentran en:

```text
target/bom.json
target/bom.xml
```

El resultado depende de la configuración del plugin en `pom.xml`.

## Salud y métricas de la aplicación

La plantilla utiliza Spring Boot Actuator y Micrometer para exponer información de la aplicación.

Con el microservicio en ejecución, puedes consultar:

**Estado de salud:**

```bash
curl http://localhost:8080/actuator/health
```

**Métricas disponibles en Actuator:**

```bash
curl http://localhost:8080/actuator/metrics
```

**Métricas en formato Prometheus:**

```bash
curl http://localhost:8080/actuator/prometheus
```

La disponibilidad de estos endpoints depende de la configuración de Actuator y de las dependencias incluidas.

La exposición de endpoints de administración debe revisarse antes de desplegar la aplicación en un entorno compartido o accesible desde otras redes.

## Construir y ejecutar con Docker

La plantilla incluye un `Dockerfile` con un proceso de construcción de varias etapas.

### 1. Construir la imagen

Desde la raíz del proyecto, ejecuta:

```bash
docker build -t com.bootcamp/template:0.0.1 .
```

### 2. Ejecutar el contenedor

```bash
docker run -d \
  --name template-service \
  -p 8080:8080 \
  com.bootcamp/template:0.0.1
```

### 3. Verificar la aplicación

```bash
curl http://localhost:8080/hola
```

La respuesta esperada es:

```text
Hola
```

### 4. Consultar los registros

```bash
docker logs template-service
```

### 5. Detener y eliminar el contenedor

```bash
docker stop template-service
docker rm template-service
```

Si necesitas volver a construir la imagen, elimina primero el contenedor anterior para evitar conflictos con el nombre.

## Cómo utilizar la plantilla en las actividades del bootcamp

Cuando necesites desarrollar un nuevo microservicio, utiliza este repositorio como punto de partida.

Se recomienda seguir estos pasos:

1. **Crear una copia del proyecto.** Puedes utilizar la opción de crear una plantilla de GitHub si está habilitada para el repositorio, o clonar el repositorio y crear uno nuevo a partir del código.
2. **Definir el propósito del microservicio.** Identifica la responsabilidad que tendrá dentro de la solución.
3. **Definir el contrato.** Documenta los endpoints, los parámetros, las respuestas y los códigos HTTP en OpenAPI.
4. **Implementar la lógica.** Adapta los controladores y agrega las clases que necesites.
5. **Configurar las dependencias.** Incorpora únicamente las dependencias necesarias para las funcionalidades del servicio.
6. **Agregar pruebas.** Crea pruebas para verificar el comportamiento esperado y los casos de error.
7. **Revisar la calidad.** Ejecuta las herramientas disponibles y analiza sus reportes.
8. **Generar el SBOM.** Utiliza CycloneDX para obtener el inventario de componentes.
9. **Construir el contenedor.** Cuando la actividad lo requiera, construye y ejecuta la imagen Docker.
10. **Documentar los cambios.** Actualiza el README del nuevo microservicio para explicar su responsabilidad, configuración, endpoints y forma de ejecución.

Al crear una copia independiente, revisa también el nombre del proyecto en `pom.xml`, el nombre de la aplicación, el paquete Java, los nombres de las clases y la configuración del contenedor.

## Relación con los demás repositorios del bootcamp

Los repositorios están organizados por responsabilidad:

### Plantilla de microservicio

Este repositorio contiene la estructura inicial para desarrollar nuevos servicios.

[Repositorio template](https://github.com/bootcamp-att/template)

### Aplicaciones de ejemplo

Contiene los microservicios, pruebas y documentación utilizados en los ejercicios de desarrollo y modernización de aplicaciones.

[Repositorio aplicativos](https://github.com/bootcamp-att/aplicativos)

### Infraestructura del laboratorio

Contiene la infraestructura como código y las automatizaciones destinadas a preparar y configurar el laboratorio del bootcamp.

[Repositorio infraestructura](https://github.com/bootcamp-att/infraestructura)

Consulta el repositorio de infraestructura para conocer los requisitos y el procedimiento de despliegue del laboratorio. La plantilla de este repositorio puede ejecutarse localmente sin desplegar toda esa infraestructura.

## Consideraciones de seguridad

Esta plantilla es un punto de partida educativo y no debe considerarse una configuración lista para producción.

Antes de utilizar un microservicio en un entorno real, revisa al menos los siguientes aspectos:

- Autenticación y autorización de los endpoints.
- Validación de datos de entrada.
- Gestión de errores y mensajes de respuesta.
- Manejo de secretos y credenciales.
- Seguridad de las dependencias.
- Configuración de los logs y protección de datos sensibles.
- Exposición y protección de los endpoints de Actuator.
- Configuración segura de la imagen y del contenedor.
- Pruebas automatizadas y análisis de seguridad.
- Configuración específica para cada ambiente.

La presencia de herramientas de análisis y generación de reportes no implica que el código esté libre de vulnerabilidades.

## Licencia

Consulta la licencia definida en el repositorio antes de reutilizar o distribuir el código fuera de las actividades previstas para el bootcamp.

---

**Repositorio:** [bootcamp-att/template](https://github.com/bootcamp-att/template)

**Organización:** [bootcamp-att](https://github.com/bootcamp-att)