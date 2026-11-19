package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Habito;
import ec.edu.uce.sghs.dominio.HabitoRecreativo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HabitoRecreativoTest {

    private HabitoRecreativo habitoRecreativo;

    @BeforeEach
    public void setUp() {
        habitoRecreativo = new HabitoRecreativo(
                1L,
                "Tocar Guitarra",
                Habito.PolaridadHabito.POSITIVO,
                4,
                14,
                true,
                "Musica",
                45,
                false
        );
    }

    @Test
    void habitoRecreativoDatosValidos() {
        HabitoRecreativo h = new HabitoRecreativo(
                2L,
                "Pintar",
                Habito.PolaridadHabito.POSITIVO,
                2,
                8,
                true,
                "Arte",
                60,
                true
        );

        assertEquals(2L, h.getIdHabito());
        assertEquals("Pintar", h.getNombre());
        assertEquals(Habito.PolaridadHabito.POSITIVO, h.getPolaridad());
        assertEquals(2, h.getRachaActual());
        assertEquals(8, h.getDiasCumplidos());
        assertTrue(h.isActivo());
        assertEquals("Arte", h.getTipoActividad());
        assertEquals(60, h.getDuracionObjetivoMinutos());
        assertTrue(h.isRequiereCompania());
    }

    @Test
    void habitoRecreativoDefecto() {
        HabitoRecreativo h = new HabitoRecreativo();

        assertNull(h.getIdHabito());
        assertNull(h.getNombre());
        assertEquals("Lectura", h.getTipoActividad());
        assertEquals(30, h.getDuracionObjetivoMinutos());
        assertFalse(h.isRequiereCompania());
    }

    @Test
    public void testCrearHabitoRecreativo() {
        assertNotNull(habitoRecreativo);
    }

    @Test
    public void testSetTipoActividadValida() {
        assertTrue(habitoRecreativo.setTipoActividad("Deporte"));
        assertEquals("Deporte", habitoRecreativo.getTipoActividad());
    }

    @Test
    public void tipoActividadInvalida() {
        String tipoAntes = habitoRecreativo.getTipoActividad();
        assertFalse(habitoRecreativo.setTipoActividad(null));
        assertFalse(habitoRecentivoSetVacioSiAplica("   "));
        assertEquals(tipoAntes, habitoRecreativo.getTipoActividad());
    }

    private boolean habitoRecentivoSetVacioSiAplica(String valor) {
        return habitoRecreativo.setTipoActividad(valor);
    }

    @Test
    public void testSetDuracionObjetivoMinutosValido() {
        assertTrue(habitoRecreativo.setDuracionObjetivoMinutos(90));
        assertEquals(90, habitoRecreativo.getDuracionObjetivoMinutos());
    }

    @Test
    public void duracionObjetivoMinutosInvalida() {
        int duracionAntes = habitoRecreativo.getDuracionObjetivoMinutos();
        assertFalse(habitoRecreativo.setDuracionObjetivoMinutos(0));
        assertFalse(habitoRecreativo.setDuracionObjetivoMinutos(-15));
        assertEquals(duracionAntes, habitoRecreativo.getDuracionObjetivoMinutos());
    }

    @Test
    public void testSetYGetRequiereCompania() {
        habitoRecreativo.setRequiereCompania(true);
        assertTrue(habitoRecreativo.isRequiereCompania());

        habitoRecreativo.setRequiereCompania(false);
        assertFalse(habitoRecreativo.isRequiereCompania());
    }

    @Test
    public void configurarActividadTest() {
        habitoRecreativo.configurarActividad("Ciclismo", 60, true);
        assertEquals("Ciclismo", habitoRecreativo.getTipoActividad());
        assertEquals(60, habitoRecreativo.getDuracionObjetivoMinutos());
        assertTrue(habitoRecreativo.isRequiereCompania());
    }

    @Test
    public void evaluarDuracionTest() {
        // duracionObjetivoMinutos configurado en setUp es 45
        assertTrue(habitoRecreativo.evaluarDuracion(45)); // Igual
        assertTrue(habitoRecreativo.evaluarDuracion(60)); // Mayor
        assertFalse(habitoRecreativo.evaluarDuracion(30)); // Menor
    }

    @Test
    public void testToString() {
        String resultado = habitoRecreativo.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("HabitoRecreativo"));
        assertTrue(resultado.contains("Musica"));
    }
}
