package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Habito;
import ec.edu.uce.sghs.dominio.HabitoEstudio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HabitoEstudioTest {

    private HabitoEstudio habitoEstudio;

    @BeforeEach
    public void setUp() {
        habitoEstudio = new HabitoEstudio(
                1L,
                "Sesion de Programacion",
                Habito.PolaridadHabito.POSITIVO,
                3,
                12,
                true,
                "Programacion II",
                60,
                15
        );
    }

    @Test
    void habitoEstudioDatosValidos() {
        HabitoEstudio h = new HabitoEstudio(
                2L,
                "Calculo Integral",
                Habito.PolaridadHabito.POSITIVO,
                5,
                20,
                true,
                "Matematicas",
                50,
                10
        );

        assertEquals(2L, h.getIdHabito());
        assertEquals("Calculo Integral", h.getNombre());
        assertEquals(Habito.PolaridadHabito.POSITIVO, h.getPolaridad());
        assertEquals(5, h.getRachaActual());
        assertEquals(20, h.getDiasCumplidos());
        assertTrue(h.isActivo());
        assertEquals("Matematicas", h.getAsignatura());
        assertEquals(50, h.getMinutosPorSesion());
        assertEquals(10, h.getMinutosDescanso());
    }

    @Test
    void habitoEstudioDefecto() {
        HabitoEstudio h = new HabitoEstudio();

        assertNull(h.getIdHabito());
        assertNull(h.getNombre());
        assertEquals("Matemáticas", h.getAsignatura());
        assertEquals(45, h.getMinutosPorSesion());
        assertEquals(10, h.getMinutosDescanso());
    }

    @Test
    public void testCrearHabitoEstudio() {
        assertNotNull(habitoEstudio);
    }

    @Test
    public void testSetAsignaturaValida() {
        assertTrue(habitoEstudio.setAsignatura("Base de Datos"));
        assertEquals("Base de Datos", habitoEstudio.getAsignatura());
    }

    @Test
    public void asignaturaInvalida() {
        String asignaturaAntes = habitoEstudio.getAsignatura();
        assertFalse(habitoEstudio.setAsignatura(null));
        assertFalse(habitoEstudio.setAsignatura("   "));
        assertEquals(asignaturaAntes, habitoEstudio.getAsignatura());
    }

    @Test
    public void testSetMinutosPorSesionValido() {
        assertTrue(habitoEstudio.setMinutosPorSesion(90));
        assertEquals(90, habitoEstudio.getMinutosPorSesion());
    }

    @Test
    public void minutosPorSesionInvalidos() {
        int minutosAntes = habitoEstudio.getMinutosPorSesion();
        assertFalse(habitoEstudio.setMinutosPorSesion(0));
        assertFalse(habitoEstudio.setMinutosPorSesion(-15));
        assertEquals(minutosAntes, habitoEstudio.getMinutosPorSesion());
    }

    @Test
    public void testSetMinutosDescansoValido() {
        assertTrue(habitoEstudio.setMinutosDescanso(20));
        assertEquals(20, habitoEstudio.getMinutosDescanso());

        assertTrue(habitoEstudio.setMinutosDescanso(0));
        assertEquals(0, habitoEstudio.getMinutosDescanso());
    }

    @Test
    public void minutosDescansoInvalidos() {
        int descansoAntes = habitoEstudio.getMinutosDescanso();
        assertFalse(habitoEstudio.setMinutosDescanso(-5));
        assertEquals(descansoAntes, habitoEstudio.getMinutosDescanso());
    }

    @Test
    public void configurarSesionTest() {
        habitoEstudio.configurarSesion("Fisica", 50, 10);
        assertEquals("Fisica", habitoEstudio.getAsignatura());
        assertEquals(50, habitoEstudio.getMinutosPorSesion());
        assertEquals(10, habitoEstudio.getMinutosDescanso());
    }

    @Test
    public void calcularTiempoTotalValido() {
        int tiempoTotal = habitoEstudio.calcularTiempoTotal(3);
        assertEquals(225, tiempoTotal);
    }

    @Test
    public void calcularTiempoTotalCantidadInvalida() {
        assertEquals(0, habitoEstudio.calcularTiempoTotal(0));
        assertEquals(0, habitoEstudio.calcularTiempoTotal(-2));
    }

    @Test
    public void testToString() {
        String resultado = habitoEstudio.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("HabitoEstudio"));
        assertTrue(resultado.contains("Programacion II"));
    }
}
