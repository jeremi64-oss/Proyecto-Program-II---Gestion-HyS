package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Reporte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReporteTest {

    private Reporte reporte;
    private Date fechaInicio;
    private Date fechaFin;

    @BeforeEach
    public void setUp() {
        fechaInicio = new Date();
        fechaFin = new Date(fechaInicio.getTime() + 864000000L);
        reporte = new Reporte(2, "Semanal", fechaInicio, fechaFin);
    }

    @Test
    void reportarCumplimientoProcesoValido() {
        Reporte resultado = new Reporte(1, "Semanal", fechaInicio, fechaFin);
        assertEquals(1, resultado.getIdReporte());
        assertEquals("Semanal", resultado.getTipo());
        assertEquals(fechaInicio, resultado.getFechaInicio());
        assertEquals(fechaFin, resultado.getFechaFin());
    }

    @Test
    void reporteCumplimientoNoNulo() {
        Reporte reporte1 = new Reporte();
        assertNotNull(reporte1.getIdReporte());
        assertNotNull(reporte1.getTipo());
        assertNotNull(reporte1.getFechaInicio());
        assertNotNull(reporte1.getFechaFin());
    }

    @Test
    void creacionReporte() {
        assertNotNull(reporte);
    }

    @Test
    void reportarCumplimientoFechasInvalidas() {
        Date inicio = new Date();
        Date finAnterior = new Date(inicio.getTime() - 86400000L);
        Reporte reporte = new Reporte();
        reporte.setFechaInicio(inicio);
        boolean resultado = reporte.setFechaFin(finAnterior);
        assertFalse(resultado);
    }

    @Test
    void getIdReporte() {
        assertEquals(2, reporte.getIdReporte());
    }

    @Test
    void getTipo() {
        assertEquals("Semanal", reporte.getTipo());
    }

    @Test
    void getFechaInicio() {
        assertEquals(fechaInicio, reporte.getFechaInicio());
    }

    @Test
    void getFechaFin() {
        assertEquals(fechaFin, reporte.getFechaFin());
    }

    @Test
    void setIdReporteValido() {
        assertTrue(reporte.setIdReporte(3));
        assertEquals(3, reporte.getIdReporte());
    }

    @Test
    void setIdReporteInvalido() {
        int idReporteAntes = reporte.getIdReporte();
        assertFalse(reporte.setIdReporte(0));
        assertFalse(reporte.setIdReporte(-2));
        assertEquals(idReporteAntes, reporte.getIdReporte());
    }

    @Test
    void setTipoValido() {
        assertTrue(reporte.setTipo("Semanal"));
        assertEquals("Semanal", reporte.getTipo());
    }

    @Test
    void setTipoInvalido() {
        String tipoAntes = reporte.getTipo();
        assertFalse(reporte.setTipo(null));
        assertFalse(reporte.setTipo(" "));
        assertFalse(reporte.setTipo("Semanal1"));
        assertFalse(reporte.setTipo("1Semanal"));
        assertFalse(reporte.setTipo("123"));
        assertEquals(tipoAntes, reporte.getTipo());
    }

    @Test
    void setFechaInicioValido() {
        assertTrue(reporte.setFechaInicio(fechaInicio));
        assertEquals(fechaInicio, reporte.getFechaInicio());
    }

    @Test
    void setFechaInicioInvalida() {
        Date fechaFutura = new Date(fechaInicio.getTime() + 86400000L);
        Date fechaAntes = reporte.getFechaInicio();
        assertFalse(reporte.setFechaInicio(fechaFutura));
        assertFalse(reporte.setFechaInicio(null));
        assertEquals(fechaAntes, reporte.getFechaInicio());
    }

    @Test
    void setFechaFinValido() {
        assertTrue(reporte.setFechaFin(fechaFin));
        assertEquals(fechaFin, reporte.getFechaFin());
    }

    @Test
    void setFechaFinInvalido() {
        Date fechaInicio = new Date();
        reporte.setFechaInicio(fechaInicio);
        Date fechaAnterior = new Date(fechaInicio.getTime() - 86400000L);
        Date fechaFinAntes = reporte.getFechaFin();
        assertFalse(reporte.setFechaFin(fechaAnterior));
        assertFalse(reporte.setFechaFin(null));
        assertEquals(fechaFinAntes, reporte.getFechaFin());
    }
}
