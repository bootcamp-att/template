# Java Spring Boot Microservice Template

Este repositorio sirve como una **plantilla base (chasis de microservicio)** profesional para el desarrollo de microservicios en Java utilizando **Spring Boot 3** y **Maven**. 

Está diseñado para acelerar el inicio de nuevos proyectos incorporando de manera preconfigurada las mejores prácticas de la industria:
- **Contract-First Development:** Generación de interfaces REST a partir de especificaciones **OpenAPI 3.0**.
- **Observabilidad & Métricas:** Integración con **Micrometer**, **Prometheus**, **OpenTelemetry (OTLP/Jaeger)** y logs estructurados con **Logstash**.
- **Calidad de Código y Seguridad:** Herramientas integradas para análisis estático, cobertura de pruebas y generación de inventarios de software (SBOM).
- **Contenerización:** Configuración de `Dockerfile` en multi-etapa con imágenes base ligeras (`alpine`).

---

## Tabla de Contenidos

1. [Estructura del Proyecto](#estructura-del-proyecto)
2. [Requisitos Previos](#requisitos-previos)
3. [Comandos de Desarrollo y Compilación](#comandos-de-desarrollo-y-compilación)
4. [Documentación OpenAPI & Swagger](#documentación-openapi--swagger)
5. [Análisis Estático y Calidad de Código](#análisis-estático-y-calidad-de-código)
6. [Generación de SBOM (CycloneDX)](#generación-de-sbom-cyclonedx)
7. [Métricas y Observabilidad](#métricas-y-observabilidad)
8. [Construcción y Ejecución con Docker](#construcción-y-ejecución-con-docker)

---

## Estructura del Proyecto

```text
.
├── checkstyle.xml                 # Reglas de estilo de código
├── Dockerfile                     # Configuración de compilación y ejecución ligera
├── pom.xml                        # Proyecto y dependencias Maven
└── src
    ├── main
    │   ├── java
    │   │   └── com/bootcamp/template
    │   │       ├── controller     # Implementación de endpoints
    │   │       └── TemplateApplication.java
    │   └── resources
    │       ├── application.properties
    │       ├── logback-spring.xml # Configuración de logs
    │       └── openapi
    │           └── openapi.yaml   # Contrato API del microservicio
    └── test                       # Pruebas unitarias e integración
```

---

## Requisitos Previos

- **Java Development Kit (JDK):** 17 o superior
- **Apache Maven:** 3.8+ 
- **Docker Engine:** Para contenedorización de la aplicación

---

## Comandos de Desarrollo y Compilación

### 1. Compilar el proyecto y generar código OpenAPI
Este comando descarga dependencias, procesa el archivo `src/main/resources/openapi/openapi.yaml` y genera las interfaces de los controladores en `target/generated-sources/openapi`.

```bash
mvn clean compile
```

### 2. Ejecutar Pruebas Unitarias
Ejecuta la suite de pruebas del proyecto:

```bash
mvn test
```

### 3. Empaquetar el Proyecto (JAR)
Genera el artefacto `.jar` ejecutable omitiendo las pruebas unitarias si es necesario:

```bash
mvn clean package -DskipTests
```

### 4. Ejecutar Localmente con Spring Boot
Inicia la aplicación en el puerto `8080`:

```bash
mvn spring-boot:run
```

---

## Documentación OpenAPI & Swagger

El microservicio utiliza el enfoque *Contract-First*. Para modificar o agregar endpoints:
1. Edita el contrato en: `src/main/resources/openapi/openapi.yaml`.
2. Ejecuta `mvn compile` para regenerar la interfaz `HolaApi`.
3. Implementa el método en `HolaController.java`.

### Acceso a la Documentación Interactiva
Una vez iniciada la aplicación, puedes acceder a la interfaz gráfica y especificación JSON/YAML en las siguientes URLs:

- **Swagger UI (Interactive Docs):** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI Spec (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## Análisis Estático y Calidad de Código

El proyecto incluye plugins para garantizar estándares de código y cobertura de pruebas:

### Checkstyle
Valida la adherencia a las normas de estilo definidas en `checkstyle.xml`:

```bash
mvn checkstyle:checkstyle
```
*El reporte se genera en: `target/site/checkstyle.html`*

### PMD
Analiza el código fuente en busca de posibles errores, código muerto o malas prácticas:

```bash
mvn pmd:pmd
```
*El reporte se genera en: `target/site/pmd.html`*

### JaCoCo (Cobertura de Pruebas)
Genera reportes sobre el porcentaje de código cubierto por las pruebas unitarias:

```bash
mvn clean test jacoco:report
```
*El reporte interactivo en HTML estará disponible en: `target/site/jacoco/index.html`*

---

## Generación de SBOM (CycloneDX)

Para cumplir con estándares de seguridad en la cadena de suministro de software, el proyecto incluye el plugin **CycloneDX** para generar un **Software Bill of Materials (SBOM)** en formato estándar JSON/XML.

Ejecuta el empaquetado para generar la lista de componentes:

```bash
mvn package
```

*Los archivos SBOM resultantes se ubican en:*
- `target/bom.json`
- `target/bom.xml`

---

## Métricas y Observabilidad

El microservicio está expuesto para monitoreo mediante **Spring Boot Actuator** y **Micrometer**:

- **Health Check:** `GET http://localhost:8080/actuator/health`
- **Métricas Prometheus:** `GET http://localhost:8080/actuator/prometheus`
- **Métricas Actuator General:** `GET http://localhost:8080/actuator/metrics`

---

## Construcción y Ejecución con Docker

El `Dockerfile` utiliza un proceso de compilación de **dos etapas (Multi-stage build)** para optimizar el tamaño de la imagen final usando un JRE Alpine.

### 1. Construir la Imagen Docker

```bash
docker build -t com.bootcamp/template:0.0.1 .
```

### 2. Ejecutar el Contenedor Docker

```bash
docker run -d \
  --name template-service \
  -p 8080:8080 \
  com.bootcamp/template:0.0.1
```

### 3. Verificar el Endpoint
Puedes probar el microservicio ejecutando:

```bash
curl http://localhost:8080/hola
```
*Respuesta esperada:* `Hola`