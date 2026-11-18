package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RolTest {

    private Rol rol;

    @BeforeEach
    public void setUp() {
        rol = new Rol(1L, Rol.TipoRol.ESTUDIANTE, "Rol asignado a estudiantes del sistema");
    }

    @Test
    void rolDatosValidos() {
        Rol r = new Rol(2L, Rol.TipoRol.ADMINISTRADOR, "Rol con privilegios de administracion");

        assertEquals(2L, r.getIdRol());
        assertEquals(Rol.TipoRol.ADMINISTRADOR, r.getTipo());
        assertEquals("Rol con privilegios de administracion", r.getDescripcion());
    }

    @Test
    void rolDefecto() {
        Rol r = new Rol();

        assertNull(r.getIdRol());
        assertNull(r.getTipo());
        assertNull(r.getDescripcion());
    }

    @Test
    public void testCrearRol() {
        assertNotNull(rol);
    }

    @Test
    public void testGetYSetIdRol() {
        rol.setIdRol(10L);
        assertEquals(10L, rol.getIdRol());
    }

    @Test
    public void testGetYSetTipo() {
        rol.setTipo(Rol.TipoRol.ADMINISTRADOR);
        assertEquals(Rol.TipoRol.ADMINISTRADOR, rol.getTipo());
    }

    @Test
    public void testGetYSetDescripcion() {
        rol.setDescripcion("Nueva descripcion del rol");
        assertEquals("Nueva descripcion del rol", rol.getDescripcion());
    }

    @Test
    public void obtenerPermisosTest() {
        List<String> permisos = rol.obtenerPermisos();
        assertNull(permisos);
    }

    @Test
    public void testToString() {
        assertNotNull(rol.toString());
    }
}