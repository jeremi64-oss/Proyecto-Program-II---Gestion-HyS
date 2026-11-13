package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.MetaSueno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetaSuenoTest {

    private MetaSueno metaSueno;

    @BeforeEach
    public void setUp() {
        metaSueno = new MetaSueno(1, 8.0);
    }

    @Test
    void metaSuenoDatosValidos() {
        MetaSueno meta = new MetaSueno(2, 7.5);

        assertEquals(2, meta.getIdMeta());
        assertEquals(7.5, meta.getHorasObjetivo());
    }

    @Test
    void metaSuenoDefecto() {
        MetaSueno meta = new MetaSueno();

        assertEquals(1, meta.getIdMeta());
        assertEquals(8.0, meta.getHorasObjetivo());
    }

    @Test
    public void testCrearMetaSueno() {
        assertNotNull(metaSueno);
    }

    @Test
    public void testGetIdMeta() {
        assertEquals(1, metaSueno.getIdMeta());
    }

    @Test
    public void testGetHorasObjetivo() {
        assertEquals(8.0, metaSueno.getHorasObjetivo());
    }

    @Test
    public void testSetIdMetaValido() {
        assertTrue(metaSueno.setIdMeta(5));
        assertEquals(5, metaSueno.getIdMeta());
    }

    @Test
    public void idMetaInvalido() {
        int idAntes = metaSueno.getIdMeta();
        assertFalse(metaSueno.setIdMeta(-1));
        assertEquals(idAntes, metaSueno.getIdMeta());
    }

    @Test
    public void testSetHorasObjetivoValido() {
        assertTrue(metaSueno.setHorasObjetivo(4.0));
        assertEquals(4.0, metaSueno.getHorasObjetivo());
        assertTrue(metaSueno.setHorasObjetivo(12.0));
        assertEquals(12.0, metaSueno.getHorasObjetivo());
    }

    @Test
    public void horasObjetivoInvalidas() {
        double horasAntes = metaSueno.getHorasObjetivo();

        assertFalse(metaSueno.setHorasObjetivo(3.9));
        assertFalse(metaSueno.setHorasObjetivo(12.1));
        assertFalse(metaSueno.setHorasObjetivo(-5.0));
        assertEquals(horasAntes, metaSueno.getHorasObjetivo());
    }
}
