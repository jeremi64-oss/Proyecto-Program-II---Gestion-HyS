package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.MetaHabito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetaHabitoTest {

    private MetaHabito metaHabito;

    @BeforeEach
    public void setUp() {
        metaHabito = new MetaHabito(4, 7);
    }

    @Test
    void metaHabitoDatosValidos() {
        MetaHabito m = new MetaHabito(5, 10);

        assertEquals(5, m.getFrecuenciaSemanal());
        assertEquals(10, m.getRachaMinimaObjetivo());
    }

    @Test
    void metaHabitoDefecto() {
        MetaHabito m = new MetaHabito();

        assertEquals(3, m.getFrecuenciaSemanal());
        assertEquals(5, m.getRachaMinimaObjetivo());
    }

    @Test
    public void testCrearMetaHabito() {
        assertNotNull(metaHabito);
    }

    @Test
    public void testSetFrecuenciaSemanalValida() {
        assertTrue(metaHabito.setFrecuenciaSemanal(1));
        assertEquals(1, metaHabito.getFrecuenciaSemanal());

        assertTrue(metaHabito.setFrecuenciaSemanal(7));
        assertEquals(7, metaHabito.getFrecuenciaSemanal());
    }

    @Test
    public void frecuenciaSemanalInvalida() {
        int frecuenciaAntes = metaHabito.getFrecuenciaSemanal();
        assertFalse(metaHabito.setFrecuenciaSemanal(0));
        assertFalse(metaHabito.setFrecuenciaSemanal(8));
        assertFalse(metaHabito.setFrecuenciaSemanal(-2));
        assertEquals(frecuenciaAntes, metaHabito.getFrecuenciaSemanal());
    }

    @Test
    public void testSetRachaMinimaObjetivoValida() {
        assertTrue(metaHabito.setRachaMinimaObjetivo(0));
        assertEquals(0, metaHabito.getRachaMinimaObjetivo());

        assertTrue(metaHabito.setRachaMinimaObjetivo(14));
        assertEquals(14, metaHabito.getRachaMinimaObjetivo());
    }

    @Test
    public void rachaMinimaObjetivoInvalida() {
        int rachaAntes = metaHabito.getRachaMinimaObjetivo();
        assertFalse(metaHabito.setRachaMinimaObjetivo(-1));
        assertEquals(rachaAntes, metaHabito.getRachaMinimaObjetivo());
    }

    @Test
    public void evaluarCumplimientoTest() {
        assertTrue(metaHabito.evaluarCumplimiento(4.0)); // Igual
        assertTrue(metaHabito.evaluarCumplimiento(5.0)); // Mayor
        assertFalse(metaHabito.evaluarCumplimiento(3.0)); // Menor
    }

    @Test
    public void verificarRachaAlcanzadaTest() {
        assertTrue(metaHabito.verificarRachaAlcanzada(7)); // Igual
        assertTrue(metaHabito.verificarRachaAlcanzada(10)); // Mayor
        assertFalse(metaHabito.verificarRachaAlcanzada(5)); // Menor
    }

    @Test
    public void testToString() {
        String resultado = metaHabito.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("MetaHabito"));
        assertTrue(resultado.contains("frecuenciaSemanal=4"));
        assertTrue(resultado.contains("rachaMinimaObjetivo=7"));
    }
}
