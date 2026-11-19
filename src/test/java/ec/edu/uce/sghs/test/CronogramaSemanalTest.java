package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.CronogramaSemanal;
import ec.edu.uce.sghs.dominio.PlanItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class CronogramaSemanalTest {

    private CronogramaSemanal cronograma;
    private PlanItem itemPrueba1;
    private PlanItem itemPrueba2;

    @BeforeEach
    public void setUp() {
        LocalDate fechaInicio = LocalDate.of(2026, 11, 16);
        cronograma = new CronogramaSemanal(1L, fechaInicio, 0.0, 5);

        // Suponiendo constructores o setters en PlanItem
        itemPrueba1 = new PlanItem();
        itemPrueba1.setIdPlanItem(1L);
        itemPrueba1.setDiaSemana("Lunes");
        itemPrueba1.setHoraInicio(LocalTime.of(8, 0));
        itemPrueba1.setHoraFin(LocalTime.of(9, 0));
        itemPrueba1.setEstado("Pendiente");

        itemPrueba2 = new PlanItem();
        itemPrueba2.setIdPlanItem(2L);
        itemPrueba2.setDiaSemana("Lunes");
        itemPrueba2.setHoraInicio(LocalTime.of(10, 0));
        itemPrueba2.setHoraFin(LocalTime.of(11, 0));
        itemPrueba2.setEstado("Completado");
    }

    @Test
    void cronogramaDatosValidos() {
        LocalDate fecha = LocalDate.of(2026, 12, 1);
        CronogramaSemanal crono = new CronogramaSemanal(2L, fecha, 50.0, 3);

        assertEquals(2L, crono.getIdCronograma());
        assertEquals(fecha, crono.getFechaInicioSemana());
        assertEquals(50.0, crono.getCumplimientoSemanal());
        assertEquals(3, crono.getItems().length);
        assertEquals(0, crono.getContadorItems());
    }

    @Test
    void cronogramaDefecto() {
        CronogramaSemanal crono = new CronogramaSemanal();

        assertEquals(0L, crono.getIdCronograma());
        assertNotNull(crono.getFechaInicioSemana());
        assertEquals(0.0, crono.getCumplimientoSemanal());
        assertEquals(10, crono.getItems().length);
        assertEquals(0, crono.getContadorItems());
    }

    @Test
    public void testCrearCronograma() {
        assertNotNull(cronograma);
    }

    @Test
    public void testGetIdCronograma() {
        assertEquals(1L, cronograma.getIdCronograma());
    }

    @Test
    public void testSetIdCronograma() {
        cronograma.setIdCronograma(15L);
        assertEquals(15L, cronograma.getIdCronograma());
    }

    @Test
    public void testGetFechaInicioSemana() {
        assertEquals(LocalDate.of(2026, 11, 16), cronograma.getFechaInicioSemana());
    }

    @Test
    public void testSetFechaInicioSemana() {
        LocalDate nuevaFecha = LocalDate.of(2026, 11, 23);
        cronograma.setFechaInicioSemana(nuevaFecha);
        assertEquals(nuevaFecha, cronograma.getFechaInicioSemana());
    }

    @Test
    public void testGetCumplimientoSemanal() {
        assertEquals(0.0, cronograma.getCumplimientoSemanal());
    }

    @Test
    public void testSetCumplimientoSemanal() {
        cronograma.setCumplimientoSemanal(75.5);
        assertEquals(75.5, cronograma.getCumplimientoSemanal());
    }

    @Test
    public void testGetItemsYContador() {
        assertNotNull(cronograma.getItems());
        assertEquals(0, cronograma.getContadorItems());
    }

    @Test
    public void testAgregarPlanItemExitoso() {
        assertTrue(cronograma.agregarPlanItem(itemPrueba1));
        assertEquals(1, cronograma.getContadorItems());
        assertEquals(itemPrueba1, cronograma.getItems()[0]);
    }

    @Test
    public void agregarPlanItemCapacidadMaxima() {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        for (int i = 0; i < dias.length; i++) {
            PlanItem item = new PlanItem();
            item.setDiaSemana(dias[i]);
            item.setHoraInicio(LocalTime.of(8 + i, 0));
            item.setHoraFin(LocalTime.of(9 + i, 0));
            assertTrue(cronograma.agregarPlanItem(item));
        }
        assertEquals(5, cronograma.getContadorItems());

        PlanItem itemExtra = new PlanItem();
        itemExtra.setDiaSemana("Viernes");
        itemExtra.setHoraInicio(LocalTime.of(15, 0));
        itemExtra.setHoraFin(LocalTime.of(16, 0));

        assertFalse(cronograma.agregarPlanItem(itemExtra));
        assertEquals(5, cronograma.getContadorItems());
    }

    @Test
    public void verificarSolapamientoMismoDiaYHorarioCruzado() {
        assertTrue(cronograma.agregarPlanItem(itemPrueba1)); // 08:00 a 09:00 (Lunes)

        PlanItem itemSolapado = new PlanItem();
        itemSolapado.setDiaSemana("Lunes");
        itemSolapado.setHoraInicio(LocalTime.of(8, 30));
        itemSolapado.setHoraFin(LocalTime.of(9, 30));

        assertTrue(cronograma.verificarSolapamiento(itemSolapado));
        assertFalse(cronograma.agregarPlanItem(itemSolapado));
    }

    @Test
    public void verificarSolapamientoSinCruceMismoDia() {
        assertTrue(cronograma.agregarPlanItem(itemPrueba1)); // 08:00 a 09:00 (Lunes)

        PlanItem itemSinCruce = new PlanItem();
        itemSinCruce.setDiaSemana("Lunes");
        itemSinCruce.setHoraInicio(LocalTime.of(9, 0));
        itemSinCruce.setHoraFin(LocalTime.of(10, 0));

        assertFalse(cronograma.verificarSolapamiento(itemSinCruce));
        assertTrue(cronograma.agregarPlanItem(itemSinCruce));
    }

    @Test
    public void calcularBalanceSemanalSinItems() {
        assertEquals(0.0, cronograma.calcularBalanceSemanal());
        assertEquals(0.0, cronograma.getCumplimientoSemanal());
    }

    @Test
    public void calcularBalanceSemanalConItemsMixtos() {
        itemPrueba1.setEstado("Pendiente"); // No completado
        itemPrueba2.setEstado("Completado"); // Completado

        cronograma.agregarPlanItem(itemPrueba1);
        cronograma.agregarPlanItem(itemPrueba2);

        assertEquals(50.0, cronograma.calcularBalanceSemanal());
        assertEquals(50.0, cronograma.getCumplimientoSemanal());
    }

    @Test
    public void testToString() {
        assertNotNull(cronograma.toString());
    }
    @Test
    void editarYEliminarMantieneLosHorariosYLaComposicion() {
        assertTrue(cronograma.agregarPlanItem(itemPrueba1));
        assertTrue(cronograma.agregarPlanItem(itemPrueba2));
        PlanItem duplicado = new PlanItem(1L, "Martes", LocalTime.of(8, 0),
                LocalTime.of(9, 0), "Pendiente");
        assertFalse(cronograma.agregarPlanItem(duplicado));

        PlanItem copia = cronograma.consultarPlanItem(2L);
        copia.reprogramar("Lunes", LocalTime.of(8, 30), LocalTime.of(9, 30));
        assertFalse(cronograma.editarPlanItem(copia));
        assertEquals(LocalTime.of(10, 0), cronograma.consultarPlanItem(2L).getHoraInicio());

        copia.reprogramar("Lunes", LocalTime.of(9, 0), LocalTime.of(10, 0));
        assertTrue(cronograma.editarPlanItem(copia));
        copia.setEstado("Pendiente");
        assertEquals("COMPLETADO", cronograma.consultarPlanItem(2L).getEstado());
        assertTrue(cronograma.eliminarPlanItem(1L));
        assertEquals(2L, cronograma.getItems()[0].getIdPlanItem());
        assertEquals(100.0, cronograma.getCumplimientoSemanal());
        assertFalse(cronograma.eliminarPlanItem(1L));
    }
}
