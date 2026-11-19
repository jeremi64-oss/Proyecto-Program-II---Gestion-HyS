package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Habito;
import ec.edu.uce.sghs.dominio.HabitoHigiene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HabitoHigieneTest {

    private HabitoHigiene habitoHigiene;

    @BeforeEach
    public void setUp() {
        habitoHigiene = new HabitoHigiene(
                1L,
                "Cepillado Dental",
                Habito.PolaridadHabito.POSITIVO,
                6,
                30,
                true,
                "Boca",
                3,
                2
        );
    }

    @Test
    void habitoHigieneDatosValidos() {
        HabitoHigiene h = new HabitoHigiene(
                2L,
                "Lavado de Manos",
                Habito.PolaridadHabito.POSITIVO,
                4,
                15,
                true,
                "Manos",
                5,
                1
        );

        assertEquals(2L, h.getIdHabito());
        assertEquals("Lavado de Manos", h.getNombre());
        assertEquals(Habito.PolaridadHabito.POSITIVO, h.getPolaridad());
        assertEquals(4, h.getRachaActual());
        assertEquals(15, h.getDiasCumplidos());
        assertTrue(h.isActivo());
        assertEquals("Manos", h.getZonaCuidado());
        assertEquals(5, h.getVecesAlDiaObjetivo());
        assertEquals(1, h.getDuracionMinutos());
    }

    @Test
    void habitoHigieneConstructorZonas() {
        HabitoHigiene h = new HabitoHigiene("Cabello", 1, 10);
        assertEquals("Cabello", h.getZonaCuidado());
        assertEquals(1, h.getVecesAlDiaObjetivo());
        assertEquals(10, h.getDuracionMinutos());
    }

    @Test
    void habitoHigieneDefecto() {
        HabitoHigiene h = new HabitoHigiene();

        assertNull(h.getIdHabito());
        assertNull(h.getNombre());
        assertEquals("", h.getZonaCuidado());
        assertEquals(0, h.getVecesAlDiaObjetivo());
        assertEquals(0, h.getDuracionMinutos());
    }

    @Test
    public void testCrearHabitoHigiene() {
        assertNotNull(habitoHigiene);
    }

    @Test
    public void testSetZonaCuidadoValida() {
        habitoHigiene.setZonaCuidado("  Facial  ");
        assertEquals("Facial", habitoHigiene.getZonaCuidado());
    }

    @Test
    public void zonaCuidadoInvalida() {
        String zonaAntes = habitoHigiene.getZonaCuidado();
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.setZonaCuidado(null));
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.setZonaCuidado("   "));
        assertEquals(zonaAntes, habitoHigiene.getZonaCuidado());
    }

    @Test
    public void testSetVecesAlDiaObjetivoValido() {
        habitoHigiene.setVecesAlDiaObjetivo(4);
        assertEquals(4, habitoHigiene.getVecesAlDiaObjetivo());
    }

    @Test
    public void vecesAlDiaObjetivoInvalido() {
        int vecesAntes = habitoHigiene.getVecesAlDiaObjetivo();
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.setVecesAlDiaObjetivo(0));
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.setVecesAlDiaObjetivo(-1));
        assertEquals(vecesAntes, habitoHigiene.getVecesAlDiaObjetivo());
    }

    @Test
    public void testSetDuracionMinutosValido() {
        habitoHigiene.setDuracionMinutos(5);
        assertEquals(5, habitoHigiene.getDuracionMinutos());
    }

    @Test
    public void duracionMinutosInvalida() {
        int duracionAntes = habitoHigiene.getDuracionMinutos();
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.setDuracionMinutos(0));
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.setDuracionMinutos(-10));
        assertEquals(duracionAntes, habitoHigiene.getDuracionMinutos());
    }

    @Test
    public void actualizarRutinaTest() {
        habitoHigiene.actualizarRutina("Corporal", 2, 15);
        assertEquals("Corporal", habitoHigiene.getZonaCuidado());
        assertEquals(2, habitoHigiene.getVecesAlDiaObjetivo());
        assertEquals(15, habitoHigiene.getDuracionMinutos());
    }

    @Test
    public void actualizarRutinaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.actualizarRutina(null, 2, 10));
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.actualizarRutina("Piel", 0, 10));
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.actualizarRutina("Piel", 2, -5));
    }

    @Test
    public void evaluarFrecuenciaTest() {
        assertTrue(habitoHigiene.evaluarFrecuencia(3)); // Igual
        assertTrue(habitoHigiene.evaluarFrecuencia(4)); // Mayor
        assertFalse(habitoHigiene.evaluarFrecuencia(2)); // Menor
    }

    @Test
    public void evaluarFrecuenciaExcepciones() {
        assertThrows(IllegalArgumentException.class, () -> habitoHigiene.evaluarFrecuencia(-1));

        HabitoHigiene habitoSinConfigurar = new HabitoHigiene();
        assertThrows(IllegalStateException.class, () -> habitoSinConfigurar.evaluarFrecuencia(2));
    }

    @Test
    public void testToString() {
        String resultado = habitoHigiene.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("HabitoHigiene"));
        assertTrue(resultado.contains("Boca"));
    }
}
