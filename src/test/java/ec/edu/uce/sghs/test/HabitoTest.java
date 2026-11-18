package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Habito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class HabitoTest {

    private Habito habito;

    @BeforeEach
    public void setUp() {
        habito = new Habito(1L, "Dormir 8 horas", Habito.PolaridadHabito.POSITIVO, 5, 20, true);
    }

    @Test
    void habitoDatosValidos() {
        Habito h = new Habito(2L, "Evitar pantallas", Habito.PolaridadHabito.NEGATIVO, 2, 10, false);

        assertEquals(2L, h.getIdHabito());
        assertEquals("Evitar pantallas", h.getNombre());
        assertEquals(Habito.PolaridadHabito.NEGATIVO, h.getPolaridad());
        assertEquals(2, h.getRachaActual());
        assertEquals(10, h.getDiasCumplidos());
        assertFalse(h.isActivo());
    }

    @Test
    void habitoDefecto() {
        Habito h = new Habito();

        assertNull(h.getIdHabito());
        assertNull(h.getNombre());
        assertNull(h.getPolaridad());
        assertEquals(0, h.getRachaActual());
        assertEquals(0, h.getDiasCumplidos());
        assertFalse(h.isActivo());
    }

    @Test
    public void testCrearHabito() {
        assertNotNull(habito);
    }

    @Test
    public void testGetYSetIdHabito() {
        habito.setIdHabito(15L);
        assertEquals(15L, habito.getIdHabito());
    }

    @Test
    public void testGetYSetNombre() {
        habito.setNombre("Meditar 10 minutos");
        assertEquals("Meditar 10 minutos", habito.getNombre());
    }

    @Test
    public void testGetYSetPolaridad() {
        habito.setPolaridad(Habito.PolaridadHabito.NEGATIVO);
        assertEquals(Habito.PolaridadHabito.NEGATIVO, habito.getPolaridad());
    }

    @Test
    public void testGetYSetRachaActual() {
        habito.setRachaActual(7);
        assertEquals(7, habito.getRachaActual());
    }

    @Test
    public void testGetYSetDiasCumplidos() {
        habito.setDiasCumplidos(25);
        assertEquals(25, habito.getDiasCumplidos());
    }

    @Test
    public void testGetYSetActivo() {
        habito.setActivo(false);
        assertFalse(habito.isActivo());

        habito.setActivo(true);
        assertTrue(habito.isActivo());
    }

    @Test
    public void registrarCumplimientoTest() {
        LocalDate fecha = LocalDate.of(2026, 11, 17);
        assertTrue(habito.registrarCumplimiento(fecha, true));
        assertFalse(habito.registrarCumplimiento(fecha, false));
    }

    @Test
    public void calcularGananciaValida() {
        // diasCumplidos = 20 (del setUp)
        // Si diasTotales = 40, ganancia debe ser 50.0%
        double ganancia = habito.calcularGanancia(40);
        assertEquals(50.0, ganancia);
    }

    @Test
    public void calcularGananciaDiasTotalesInvalidos() {
        assertEquals(0.0, habito.calcularGanancia(0));
        assertEquals(0.0, habito.calcularGanancia(-5));
    }

    @Test
    public void editarHabitoTest() {
        habito.editarHabito("Leer antes de dormir", Habito.PolaridadHabito.POSITIVO);
        assertEquals("Leer antes de dormir", habito.getNombre());
        assertEquals(Habito.PolaridadHabito.POSITIVO, habito.getPolaridad());
    }

    @Test
    public void testToString() {
        assertNotNull(habito.toString());
    }
}