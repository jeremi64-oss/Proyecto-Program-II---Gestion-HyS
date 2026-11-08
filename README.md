# Sistema de Gestión de Hábitos y Sueño

Proyecto de Programación II de la Universidad Central del Ecuador, desarrollado en Java con estructura Maven para IntelliJ IDEA.

## Contenido

- Clases de dominio para usuarios, roles, hábitos, metas, registros de sueño y reportes.
- Menús de consola para estudiantes y administradores.
- Validación de correo y contraseña.
- Programas de prueba de acceso, roles y navegación.

El inicio de sesión y el registro son simulados en esta versión.

## Abrir y ejecutar en IntelliJ IDEA

1. Clonar o descargar este repositorio.
2. Abrir el archivo `pom.xml` como proyecto Maven.
3. Configurar el SDK del proyecto y de Maven con JDK 27, según la versión declarada en `pom.xml`.
4. Ejecutar `ec.edu.uce.sistemahabitosysueno.test.MenuTest`, en `src/test/java`, para iniciar el menú del sistema.
5. Ejecutar `ec.edu.uce.sistemahabitosysueno.test.SistemaTest` para comprobar la asignación de roles y la detección de intentos fallidos.

La clase `Main` conserva el ejemplo inicial de IntelliJ; el menú del sistema se inicia desde `MenuTest`.

## Archivos del proyecto

- `src/main/java`: código fuente.
- `src/test/java`: programas de prueba ejecutables.
- `pom.xml`: configuración Maven.

Los archivos compilados (`target/`) y la configuración local del editor (`.idea/`) se excluyen del repositorio.
