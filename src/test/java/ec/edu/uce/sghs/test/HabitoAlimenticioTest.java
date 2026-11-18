package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Habito;
import ec.edu.uce.sghs.dominio.HabitoAlimenticio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class HabitoAlimenticioTest {

    private HabitoAlimenticio habitoAlimenticio;

    @BeforeEach
    public void setUp() {
        habitoAlimenticio = new HabitoAlimenticio(
                1L,
                "Desayuno Balanceado",
                Habito.PolaridadHabito.POSITIVO,
                4,
                15,
                true,
                "Desayuno",
                2,
                LocalTime.of(7, 30)
        );
    }

    @Test
    void habitoAlimenticioDatosValidos() {
        HabitoAlimenticio h = new HabitoAlimenticio(
                2L,
                "Cena Ligera",
                Habito.PolaridadHabito.POSITIVO,
                2,
                5,
                true,
                "Cena",
                1,
                LocalTime.of(19, 0)
        );

        assertEquals(2L, h.getIdHabito());
        assertEquals("Cena Ligera", h.getNombre());
        assertEquals(Habito.PolaridadHabito.POSITIVO, h.getPolaridad());
        assertEquals(2, h.getRachaActual());
        assertEquals(5, h.getDiasCumplidos());
        assertTrue(h.isActivo());
        assertEquals("Cena", h.getTipoComida());
        assertEquals(1, h.getPorcionesObjetivo());
        assertEquals(LocalTime.of(19, 0), h.getHoraComidaObjetivo());
    }

    @Test
    void habitoAlimenticioDefecto() {
        HabitoAlimenticio h = new HabitoAlimenticio();

        assertNull(h.getIdHabito());
        assertNull(h.getNombre());
        assertEquals("Almuerzo Saludable", h.getTipoComida());
        assertEquals(3, h.getPorcionesObjetivo());
        assertEquals(LocalTime.of(12, 30), h.getHoraComidaObjetivo());
    }

    @Test
    public void testCrearHabitoAlimenticio() {
        assertNotNull(habitoAlimenticio);
    }

    @Test
    public void testSetTipoComidaValido() {
        assertTrue(habitoAlimenticio.setTipoComida("Colacion"));
        assertEquals("Colacion", habitoAlimenticio.getTipoComida());
    }

    @Test
    public void tipoComidaInvalido() {
        String comidaAntes = habitoAlimenticio.getTipoComida();
        assertFalse(habitoAlimenticio.setTipoComida(null));
        assertFalse(habitoAlimenticio.setTipoComida("   "));
        assertEquals(comidaAntes, habitoAlimenticio.getTipoComida());
    }

    @Test
    public void testSetPorcionesObjetivoValido() {
        assertTrue(habitoAlimenticio.setPorcionesObjetivo(5));
        assertEquals(5, habitoAlimenticio.getPorcionesObjetivo());
    }

    @Test
    public void porcionesObjetivoInvalidas() {
        int porcionesAntes = habitoAlimenticio.getPorcionesObjetivo();
        assertFalse(habitoAlimenticio.setPorcionesObjetivo(0));
        assertFalse(habitoAlimenticio.setPorcionesObjetivo(-2));
        assertEquals(porcionesAntes, habitoAlimenticio.getPorcionesObjetivo());
    }

    @Test
    public void testSetHoraComidaObjetivoValida() {
        LocalTime nuevaHora = LocalTime.of(14, 0);
        assertTrue(habitoAlimenticio.setHoraComidaObjetivo(nuevaHora));
        assertEquals(nuevaHora, habitoAlimenticio.getHoraComidaObjetivo());
    }

    @Test
    public void horaComidaObjetivoInvalida() {
        LocalTime horaAntes = habitoAlimenticio.getHoraComidaObjetivo();
        assertFalse(habitoAlimenticio.setHoraComidaObjetivo(null));
        assertEquals(horaAntes, habitoAlimenticio.getHoraComidaObjetivo());
    }

    @Test
    public void configurarComidaTest() {
        habitoAlimenticio.configurarComida("Merienda", 4, LocalTime.of(18, 0));
        assertEquals("Merienda", habitoAlimenticio.getTipoComida());
        assertEquals(4, habitoAlimenticio.getPorcionesObjetivo());
        assertEquals(LocalTime.of(18, 0), habitoAlimenticio.getHoraComidaObjetivo());
    }

    @Test
    public void evaluarPorcionesTest() {
        // porcionesObjetivo configurado en setUp es 2
        assertTrue(habitoAlimenticio.evaluarPorciones(2)); // Igual
        assertTrue(habitoAlimenticio.evaluarPorciones(3)); // Mayor
        assertFalse(habitoAlimenticio.evaluarPorciones(1)); // Menor
    }

    @Test
    public void testToString() {
        String resultado = habitoAlimenticio.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("HabitoAlimenticio"));
        assertTrue(resultado.contains("Desayuno"));
    }
}
