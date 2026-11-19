package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.MetaSmart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MetaSmartTest {

    private MetaSmart metaSmart;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @BeforeEach
    public void setUp() {
        fechaInicio = LocalDate.of(2026, 11, 1);
        fechaFin = LocalDate.of(2026, 11, 30);
        metaSmart = new MetaSmart(1L, "Dormir al menos 8 horas diarias", 8.0, "horas", fechaInicio, fechaFin);
    }

    @Test
    void metaSmartDatosValidos() {
        LocalDate inicio = LocalDate.of(2026, 12, 1);
        LocalDate fin = LocalDate.of(2026, 12, 31);
        MetaSmart m = new MetaSmart(2L, "Beber agua suficiente", 2.0, "litros", inicio, fin);

        assertEquals(2L, m.getIdMeta());
        assertEquals("Beber agua suficiente", m.getDescripcionEspecifica());
        assertEquals(2.0, m.getValorMedible());
        assertEquals("litros", m.getUnidadMedida());
        assertEquals(inicio, m.getFechaInicio());
        assertEquals(fin, m.getFechaFin());
    }

    @Test
    void metaSmartDefecto() {
        MetaSmart m = new MetaSmart();

        assertNull(m.getIdMeta());
        assertNull(m.getDescripcionEspecifica());
        assertEquals(0.0, m.getValorMedible());
        assertNull(m.getUnidadMedida());
        assertNull(m.getFechaInicio());
        assertNull(m.getFechaFin());
    }

    @Test
    public void testCrearMetaSmart() {
        assertNotNull(metaSmart);
    }

    @Test
    public void testGetYSetIdMeta() {
        metaSmart.setIdMeta(10L);
        assertEquals(10L, metaSmart.getIdMeta());
    }

    @Test
    public void testGetYSetDescripcionEspecifica() {
        metaSmart.setDescripcionEspecifica("Reducir uso de pantalla");
        assertEquals("Reducir uso de pantalla", metaSmart.getDescripcionEspecifica());
    }

    @Test
    public void testGetYSetValorMedible() {
        metaSmart.setValorMedible(7.5);
        assertEquals(7.5, metaSmart.getValorMedible());
    }

    @Test
    public void testGetYSetUnidadMedida() {
        metaSmart.setUnidadMedida("minutos");
        assertEquals("minutos", metaSmart.getUnidadMedida());
    }

    @Test
    public void testGetYSetFechaInicio() {
        LocalDate nuevaFecha = LocalDate.of(2026, 11, 15);
        metaSmart.setFechaInicio(nuevaFecha);
        assertEquals(nuevaFecha, metaSmart.getFechaInicio());
    }

    @Test
    public void testGetYSetFechaFin() {
        LocalDate nuevaFecha = LocalDate.of(2026, 12, 15);
        metaSmart.setFechaFin(nuevaFecha);
        assertEquals(nuevaFecha, metaSmart.getFechaFin());
    }

    @Test
    public void validaCriteriosSMARTExitoso() {
        assertTrue(metaSmart.validaCriteriosSMART());
    }

    @Test
    public void validaCriteriosSMARTFallido() {
        MetaSmart metaInvalida = new MetaSmart();
        assertFalse(metaInvalida.validaCriteriosSMART());

        metaInvalida.setDescripcionEspecifica("Meta sin valor positivo");
        metaInvalida.setValorMedible(0.0);
        assertFalse(metaInvalida.validaCriteriosSMART());

        metaInvalida.setValorMedible(-5.0);
        assertFalse(metaInvalida.validaCriteriosSMART());
    }

    @Test
    public void evaluarCumplimientoTest() {
        // valorMedible configurado en setUp es 8.0
        assertTrue(metaSmart.evaluarCumplimiento(8.0));  // Igual
        assertTrue(metaSmart.evaluarCumplimiento(9.5));  // Mayor
        assertFalse(metaSmart.evaluarCumplimiento(6.0)); // Menor
    }

    @Test
    public void testToString() {
        assertNotNull(metaSmart.toString());
    }
}
