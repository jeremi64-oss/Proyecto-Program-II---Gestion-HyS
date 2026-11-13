package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Rol;
import ec.edu.uce.sghs.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RolTest {

    private Rol rol;
    private Usuario usuario;

    @BeforeEach
    void preparar() {
        rol = new Rol(1, "estudiante");
        usuario = new Usuario();
    }

    @Test
    void crearConConstructorCompleto() {
        assertNotNull(rol);
        assertNotNull(rol.getNombre());
        assertEquals(1, rol.getIdRol());
        assertEquals("estudiante", rol.getNombre());
        assertTrue(rol.esEstudiante());
        assertFalse(rol.esAdministrador());
    }

    @Test
    void completarConstructorVacioConSetters() {
        Rol nuevo = new Rol();

        nuevo.setIdRol(2);
        nuevo.setNombre("administrador");

        assertEquals(2, nuevo.getIdRol());
        assertEquals("administrador", nuevo.getNombre());
        assertTrue(nuevo.esAdministrador());
        assertFalse(nuevo.esEstudiante());
    }

    @Test
    void normalizarNombre() {
        rol.setNombre(" ADMINISTRADOR ");

        assertEquals("administrador", rol.getNombre());
    }

    @Test
    void rechazarNombreNuloOVacio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> rol.setNombre(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> rol.setNombre("")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> rol.setNombre("   ")
        );

        assertEquals("estudiante", rol.getNombre());
    }

    @Test
    void rechazarOpcionNoPermitida() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Rol(1, "profesor")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> rol.setNombre("admin")
        );
    }

    @Test
    void rechazarIdentificadorInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Rol(0, "estudiante")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> rol.setIdRol(-1)
        );

        assertEquals(1, rol.getIdRol());
    }

    @Test
    void asignarRolAUsuario() {
        rol.asignar(usuario);

        assertNotNull(usuario.getRol());
        assertEquals(rol, usuario.getRol());
        assertEquals("estudiante", usuario.getRol().getNombre());
    }

    @Test
    void rechazarAsignacionSinUsuario() {
        assertThrows(
                IllegalArgumentException.class,
                () -> rol.asignar(null)
        );
    }

    @Test
    void rechazarAsignacionDeRolIncompleto() {
        Rol incompleto = new Rol();

        assertThrows(
                IllegalArgumentException.class,
                () -> incompleto.asignar(usuario)
        );
    }
}
