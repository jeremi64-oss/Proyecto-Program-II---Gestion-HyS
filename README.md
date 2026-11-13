# Sistema de Gestión de Hábitos y Sueño

Proyecto de Programación II de la Universidad Central del Ecuador, desarrollado en Java con estructura Maven para IntelliJ IDEA.

## Contenido

- Clases de dominio para usuarios, roles, hábitos, metas, registros de sueño y reportes.
- Menús de consola para estudiantes y administradores.
- Validación de correo y contraseña.
- Diez clases de pruebas con JUnit Jupiter 5.10.2.

Los paquetes de esta versión están bajo `ec.edu.uce.sghs`. El inicio de sesión y el registro son simulados.

## Abrir en IntelliJ IDEA

1. Clonar o descargar este repositorio.
2. Abrir el archivo `pom.xml` como proyecto Maven.
3. Configurar el SDK del proyecto y de Maven con JDK 27, según la versión declarada en `pom.xml`.
4. Recargar el proyecto Maven para resolver la dependencia de JUnit.
5. Para ejecutar las pruebas, seleccionar `src/test/java` y usar la opción de ejecutar todas las pruebas en IntelliJ.

Esta versión no incluye un método `main`. El menú principal se encuentra en `ec.edu.uce.sghs.gui.MenuPrincipal` y expone el método estático `mostrarMenu()`.

## Archivos del proyecto

- `src/main/java/ec/edu/uce/sghs`: código fuente.
- `src/test/java/ec/edu/uce/sghs/test`: pruebas JUnit.
- `pom.xml`: configuración Maven y dependencia de pruebas.

Los archivos compilados (`target/`) y la configuración local del editor (`.idea/`) se excluyen del repositorio.
