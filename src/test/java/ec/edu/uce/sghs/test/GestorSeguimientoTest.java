package ec.edu.uce.sghs.test;
import ec.edu.uce.sghs.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class GestorSeguimientoTest {

    private GestorSeguimiento gestor;
    private Usuario usuarioPrueba;
    private Usuario adminPrueba;
    private CatalogoHabitos catalogoPrueba;

    @BeforeEach
    public void setUp() {
        catalogoPrueba = new CatalogoHabitos(1L, "Sueno", "v1.0");
        gestor = new GestorSeguimiento(5, catalogoPrueba);

        adminPrueba = new Usuario();
        adminPrueba.setIdUsuario(1L);
        adminPrueba.setActivo(true);
        adminPrueba.setNombre("Administrador");
        adminPrueba.setRol(new Rol(1L, Rol.TipoRol.ADMINISTRADOR, "Administrador"));

        usuarioPrueba = new Usuario();
        usuarioPrueba.setIdUsuario(2L);
        usuarioPrueba.setActivo(true);
        usuarioPrueba.setNombre("Estudiante Prueba");
    }

    @Test
    void gestorDatosValidos() {
        CatalogoHabitos cat = new CatalogoHabitos(2L, "Bienestar", "v2.0");
        GestorSeguimiento gs = new GestorSeguimiento(3, cat);

        assertNotNull(gs.getUsuarios());
        assertEquals(3, gs.getUsuarios().length);
        assertNotNull(gs.getHistorialSueno());
        assertEquals(50, gs.getHistorialSueno().length);
        assertEquals(cat, gs.getCatalogo());
    }

    @Test
    void gestorDefecto() {
        GestorSeguimiento gs = new GestorSeguimiento();

        assertNotNull(gs.getUsuarios());
        assertEquals(10, gs.getUsuarios().length);
        assertNotNull(gs.getHistorialSueno());
        assertEquals(50, gs.getHistorialSueno().length);
        assertNull(gs.getCatalogo());
    }

    @Test
    public void testCrearGestor() {
        assertNotNull(gestor);
    }

    @Test
    public void testGetYSetUsuarios() {
        Usuario[] nuevosUsuarios = new Usuario[2];
        gestor.setUsuarios(nuevosUsuarios);
        assertTrue(java.util.Arrays.equals(nuevosUsuarios, gestor.getUsuarios()));
    }

    @Test
    public void testGetYSetCatalogo() {
        CatalogoHabitos nuevoCatalogo = new CatalogoHabitos(5L, "Nutricion", "v3.0");
        gestor.setCatalogo(nuevoCatalogo);
        assertEquals(nuevoCatalogo, gestor.getCatalogo());
    }

    @Test
    public void testGetYSetHistorialSueno() {
        String[] nuevoHistorial = new String[10];
        gestor.setHistorialSueno(nuevoHistorial);
        assertEquals(nuevoHistorial, gestor.getHistorialSueno());
    }

    @Test
    public void testAgregarUsuarioExitoso() {
        assertTrue(gestor.agregarUsuario(usuarioPrueba));
        assertEquals(usuarioPrueba, gestor.getUsuarios()[0]);
    }

    @Test
    public void agregarUsuarioCapacidadMaxima() {

        for (int i = 0; i < 5; i++) {
            Usuario u = new Usuario();
            u.setIdUsuario((long) (10 + i));
            u.setCorreo("estudiante" + i + "@uce.edu.ec");
            assertTrue(gestor.agregarUsuario(u));
        }

        Usuario usuarioExtra = new Usuario();
        usuarioExtra.setIdUsuario(99L);
        assertFalse(gestor.agregarUsuario(usuarioExtra));
    }

    @Test
    public void gestionarCuentaAdminValido() {
        assertTrue(gestor.gestionarCuenta(adminPrueba, usuarioPrueba, false));
        assertFalse(usuarioPrueba.isActivo());
    }

    @Test
    public void gestionarCuentaAdminInvalidoONoActivo() {
        Usuario adminInactivo = new Usuario();
        adminInactivo.setActivo(false);

        assertFalse(gestor.gestionarCuenta(null, usuarioPrueba, false));
        assertFalse(gestor.gestionarCuenta(adminInactivo, usuarioPrueba, false));
        assertTrue(usuarioPrueba.isActivo()); // No debe cambiar el estado
    }

    @Test
    public void asignarRolAdministradorTest() {
        assertTrue(gestor.asignarRolAdministrador(adminPrueba, usuarioPrueba));
    }

    @Test
    public void registrarSuenoExitoso() {
        LocalDate fecha = LocalDate.of(2026, 11, 17);
        LocalTime dormir = LocalTime.of(22, 0);
        LocalTime despertar = LocalTime.of(6, 0);

        assertTrue(gestor.registrarSueno(usuarioPrueba, fecha, dormir, despertar));
        assertNotNull(gestor.getHistorialSueno()[0]);
        assertTrue(gestor.getHistorialSueno()[0].contains("ID Usuario: 2"));
    }

    @Test
    public void generarPlanSeguimientoValido() {
        Habito[] habitos = new Habito[1];
        CronogramaSemanal cronograma = gestor.generarPlanSeguimiento(usuarioPrueba, habitos);

        assertNotNull(cronograma);
        assertEquals(1L, cronograma.getIdCronograma());
    }

    @Test
    public void generarPlanSeguimientoUsuarioNulo() {
        Habito[] habitos = new Habito[1];
        CronogramaSemanal cronograma = gestor.generarPlanSeguimiento(null, habitos);

        assertNull(cronograma);
    }

    @Test
    public void generarReporteProgresoTest() {
        LocalDate inicio = LocalDate.of(2026, 11, 1);
        LocalDate fin = LocalDate.of(2026, 11, 17);

        String reporte = gestor.generarReporteProgreso(usuarioPrueba, inicio, fin);
        assertNotNull(reporte);
        assertTrue(reporte.contains("Estudiante Prueba"));
    }

    @Test
    public void testToString() {
        assertNotNull(gestor.toString());
    }
    @Test
    void crudUsuariosValidaIdentificadorYCorreo() {
        Usuario otro = new Usuario(3L, "Otro", "otro@uce.edu.ec", "clave1234", true);
        assertTrue(gestor.agregarUsuario(usuarioPrueba));
        assertTrue(gestor.agregarUsuario(otro));
        assertFalse(gestor.agregarUsuario(new Usuario(2L, "Duplicado", "distinto@uce.edu.ec", "clave1234", true)));
        assertFalse(gestor.agregarUsuario(new Usuario(4L, "Duplicado", "OTRO@uce.edu.ec", "clave1234", true)));

        Usuario datos = new Usuario(3L, "Nombre editado", "USUARIO@uce.edu.ec", "clave1234", false);
        assertFalse(gestor.editarUsuario(datos));
        assertEquals("Otro", gestor.consultarUsuario(3L).getNombre());
        datos.setCorreo("nuevo@uce.edu.ec");
        assertTrue(gestor.editarUsuario(datos));
        assertEquals("Nombre editado", gestor.consultarUsuario(3L).getNombre());
        assertTrue(gestor.eliminarUsuario(2L));
        assertFalse(gestor.eliminarUsuario(2L));
        assertNull(gestor.consultarUsuario(2L));
        assertEquals(4L, gestor.siguienteIdUsuario());
        assertEquals(3L, gestor.getUsuarios()[0].getIdUsuario());
    }

    @Test
    void cadaUsuarioConservaSuPropioCronograma() {
        Habito habito = new Habito(15L, "Leer", Habito.PolaridadHabito.POSITIVO, 0, 0, true);
        CronogramaSemanal cronograma = gestor.generarPlanSeguimiento(usuarioPrueba, new Habito[]{habito});
        assertTrue(cronograma == usuarioPrueba.getCronograma());
        assertTrue(cronograma == gestor.generarPlanSeguimiento(usuarioPrueba, new Habito[0]));
        assertTrue(cronograma.getItems()[0].getHabito() == habito);
        assertFalse(cronograma.equals(gestor.generarPlanSeguimiento(adminPrueba, null)));
        assertFalse(gestor.gestionarCuenta(usuarioPrueba, adminPrueba, false));
    }
}
