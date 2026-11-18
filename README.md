# Sistema de Gestión de Hábitos y Sueño

Proyecto de Programación II de la Universidad Central del Ecuador, desarrollado en Java con estructura Maven para IntelliJ IDEA.

## Contenido

- Catálogo de hábitos alimenticios, de estudio, de higiene y recreativos.
- Metas de hábitos y de sueño, planificación y cronograma semanal.
- Gestión de usuarios, roles y seguimiento mediante un menú de consola.
- Catorce clases de pruebas con JUnit Jupiter 5.10.2.

Los paquetes del proyecto están bajo `ec.edu.uce.sghs`. El menú incluye usuarios de demostración para probar el sistema.

## Abrir y ejecutar en IntelliJ IDEA

1. Clonar o descargar este repositorio.
2. Abrir el archivo `pom.xml` como proyecto Maven.
3. Configurar el SDK del proyecto y de Maven con JDK 27, según la versión declarada en `pom.xml`.
4. Recargar el proyecto Maven para resolver la dependencia de JUnit.
5. Ejecutar la clase `ec.edu.uce.sghs.Main` para iniciar el sistema.
6. Para ejecutar las pruebas, seleccionar `src/test/java` y usar la opción de ejecutar todas las pruebas en IntelliJ.

## Archivos del proyecto

- `src/main/java/ec/edu/uce/sghs`: código fuente y clase de inicio.
- `src/test/java/ec/edu/uce/sghs/test`: pruebas JUnit.
- `pom.xml`: configuración Maven y dependencia de pruebas.

Los archivos compilados (`target/`) y la configuración local del editor (`.idea/`) se excluyen del repositorio.
