package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.MetaSueno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class MetaSuenoTest {

    private MetaSueno metaSueno;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaDormir;
    private LocalTime horaDespertar;

    @BeforeEach
    public void setUp() {
        fechaInicio = LocalDate.of(2026, 11, 1);
        fechaFin = LocalDate.of(2026, 11, 30);
        horaDormir = LocalTime.of(22, 0);
        horaDespertar = LocalTime.of(6, 0);

        metaSueno = new MetaSueno(
                1L,
                "Dormir temprano y cumplir 8 horas",
                8.0,
                "horas",
                fechaInicio,
                fechaFin,
                horaDormir,
                horaDespertar,
                8.0
        );
    }

    @Test
    void metaSuenoDatosValidos() {
        LocalDate inicio = LocalDate.of(2026, 12, 1);
        LocalDate fin = LocalDate.of(2026, 12, 31);
        LocalTime dormir = LocalTime.of(23, 0);
        LocalTime despertar = LocalTime.of(7, 0);

        MetaSueno m = new MetaSueno(
                2L,
                "Rutina nocturna",
                7.5,
                "horas",
                inicio,
                fin,
                dormir,
                despertar,
                7.5
        );

        assertEquals(2L, m.getIdMeta());
        assertEquals("Rutina nocturna", m.getDescripcionEspecifica());
        assertEquals(7.5, m.getValorMedible());
        assertEquals("horas", m.getUnidadMedida());
        assertEquals(inicio, m.getFechaInicio());
        assertEquals(fin, m.getFechaFin());
        assertEquals(dormir, m.getHoraDormirObjetivo());
        assertEquals(despertar, m.getHoraDespertarObjetivo());
        assertEquals(7.5, m.getHorasSuenoOptimas());
    }

    @Test
    void metaSuenoDefecto() {
        MetaSueno m = new MetaSueno();

        assertNull(m.getIdMeta());
        assertNull(m.getDescripcionEspecifica());
        assertEquals(0.0, m.getValorMedible());
        assertNull(m.getUnidadMedida());
        assertNull(m.getFechaInicio());
        assertNull(m.getFechaFin());
        assertNull(m.getHoraDormirObjetivo());
        assertNull(m.getHoraDespertarObjetivo());
        assertEquals(0.0, m.getHorasSuenoOptimas());
    }

    @Test
    public void testCrearMetaSueno() {
        assertNotNull(metaSueno);
    }

    @Test
    public void testGetYSetHoraDormirObjetivo() {
        LocalTime nuevaHora = LocalTime.of(23, 30);
        metaSueno.setHoraDormirObjetivo(nuevaHora);
        assertEquals(nuevaHora, metaSueno.getHoraDormirObjetivo());
    }

    @Test
    public void testGetYSetHoraDespertarObjetivo() {
        LocalTime nuevaHora = LocalTime.of(6, 30);
        metaSueno.setHoraDespertarObjetivo(nuevaHora);
        assertEquals(nuevaHora, metaSueno.getHoraDespertarObjetivo());
    }

    @Test
    public void testGetYSetHorasSuenoOptimas() {
        metaSueno.setHorasSuenoOptimas(9.0);
        assertEquals(9.0, metaSueno.getHorasSuenoOptimas());
    }

    @Test
    public void evaluarCumplimientoTest() {
        assertTrue(metaSueno.evaluarCumplimiento(8.0));  // Igual
        assertTrue(metaSueno.evaluarCumplimiento(9.0));  // Mayor
        assertFalse(metaSueno.evaluarCumplimiento(7.0)); // Menor
    }

    @Test
    public void calcularDesviacionMinutosPositiva() {
        LocalTime horaReal = LocalTime.of(22, 30);
        assertEquals(30, metaSueno.calcularDesviacionMinutos(horaReal));
    }

    @Test
    public void calcularDesviacionMinutosNegativa() {
        LocalTime horaReal = LocalTime.of(21, 45);
        assertEquals(-15, metaSueno.calcularDesviacionMinutos(horaReal));
    }

    @Test
    public void calcularDesviacionMinutosNula() {
        assertEquals(0, metaSueno.calcularDesviacionMinutos(null));

        MetaSueno metaSinHora = new MetaSueno();
        assertEquals(0, metaSinHora.calcularDesviacionMinutos(LocalTime.of(22, 0)));
    }

    @Test
    public void testToString() {
        assertNotNull(metaSueno.toString());
    }
}
