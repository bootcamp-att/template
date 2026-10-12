# Ciclo de Vida del Desarrollo de Software (SDLC) y Ecosistema de Herramientas

Este documento define la visión general del Ciclo de Vida del Desarrollo de Software (SDLC), las herramientas estándar que componen la cadena de suministro (toolchain) y la guía de trabajo diario para los colaboradores del proyecto.

---

## 1. Visión General del SDLC

Adoptamos un enfoque de **Shift Left** y **DevSecOps**, integrando la calidad, las pruebas y los controles de seguridad desde las fases iniciales del desarrollo para minimizar costos y riesgos en producción.

+-----------+     +-----------+     +-------------+     +----------+     +---------------+
| 1. Diseño | --> | 2. Código | --> | 3. Build &  | --> | 4. Deploy| --> | 5. Operación  |
|  & Spec   |     |  & Pruebas|     | Validaciones|     |  & Staging|     | & Observación |
+-----------+     +-----------+     +-------------+     +----------+     +---------------+

### Fases del Ciclo de Vida

1. **Diseño y Especificación:** Definición de requisitos de negocio, arquitectura, modelos de datos y contratos de API (OpenAPI/Swagger).
2. **Desarrollo y Pruebas Locales:** Codificación basada en TDD/BDD, pruebas unitarias locales y análisis estático inicial.
3. **Integración Continua (CI):** Compilación automatizada, ejecución de suite de pruebas, análisis de calidad de código y escaneo de vulnerabilidades.
4. **Despliegue Continuo (CD):** Empaquetado en contenedores y despliegue automatizado en entornos de pruebas/producción mediante GitOps.
5. **Operación y Observabilidad:** Monitoreo activo de salud, métricas de rendimiento, trazabilidad distribuida y gestión de logs.

---

## 2. Mapa del Ecosistema de Herramientas (Toolchain)

El proyecto utiliza las siguientes herramientas y marcos de trabajo estandarizados:

| Categoria | Herramienta / Tecnología | Propósito |
| :--- | :--- | :--- |
| **Control de Versiones** | Git / GitHub (o GitLab) | Gestión del código fuente, ramas y Pull Requests. |
| **Construcción & Dependencias**| Maven / Gradle | Gestor de proyectos, compilación y resolución de dependencias. |
| **Lenguaje & Runtime** | Java OpenJDK (LTS) | Lenguaje base para el desarrollo del sistema backend. |
| **Pruebas Unitarias & Cobertura**| JUnit 5 / JaCoCo | Ejecución de pruebas unitarias y medición del porcentaje de cobertura. |
| **Pruebas Integrales/Funcionales**| RestAssured / Testcontainers | Validación de contratos de API y pruebas de integración con dependencias en contenedores. |
| **Seguridad de Dependencias** | OWASP Dependency-Check / Snyk | Escaneo de librerías para detección de vulnerabilidades conocidas (CVEs). |
| **Contenedorización** | Docker / Podman | Empaquetado y aislamiento de la aplicación en imágenes livianas. |
| **Orquestación & Despliegue** | Kubernetes / Helm | Gestión del ciclo de vida de los contenedores en entornos de ejecución. |

---

## 3. Guía de Trabajo Diario para Desarrolladores (Workflow)

Para mantener la calidad y estabilidad de la base de código, todos los colaboradores deben seguir el siguiente flujo de trabajo:

### Paso 1: Creación de la Rama de Trabajo
Cree una rama corta orientada a la tarea específica a partir de la rama principal (`main` o `develop` según la política de ramas activa):
```bash
git checkout main
git pull origin main
git checkout -b feature/nombre-de-la-funcionalidad