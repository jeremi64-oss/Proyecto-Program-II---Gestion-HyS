package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.PlanItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class PlanItemTest {

    private PlanItem planItem;

    @BeforeEach
    public void setUp() {
        planItem = new PlanItem(
                1L,
                "Lunes",
                LocalTime.of(8, 0),
                LocalTime.of(9, 30),
                "PENDIENTE"
        );
    }

    @Test
    void planItemDatosValidos() {
        PlanItem item = new PlanItem(
                2L,
                "miércoles",
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                "completado"
        );

        assertEquals(2L, item.getIdPlanItem());
        assertEquals("MIERCOLES", item.getDiaSemana());
        assertEquals(LocalTime.of(14, 0), item.getHoraInicio());
        assertEquals(LocalTime.of(15, 0), item.getHoraFin());
        assertEquals("COMPLETADO", item.getEstado());
    }

    @Test
    void planItemDefecto() {
        PlanItem item = new PlanItem();

        assertEquals(0L, item.getIdPlanItem());
        assertEquals("", item.getDiaSemana());
        assertNull(item.getHoraInicio());
        assertNull(item.getHoraFin());
        assertEquals("PENDIENTE", item.getEstado());
    }

    @Test
    public void testCrearPlanItem() {
        assertNotNull(planItem);
    }

    @Test
    public void testSetIdPlanItemValido() {
        planItem.setIdPlanItem(10L);
        assertEquals(10L, planItem.getIdPlanItem());
    }

    @Test
    public void idPlanItemInvalido() {
        Long idAntes = planItem.getIdPlanItem();
        assertThrows(IllegalArgumentException.class, () -> planItem.setIdPlanItem(null));
        assertThrows(IllegalArgumentException.class, () -> planItem.setIdPlanItem(0L));
        assertThrows(IllegalArgumentException.class, () -> planItem.setIdPlanItem(-5L));
        assertEquals(idAntes, planItem.getIdPlanItem());
    }

    @Test
    public void testSetDiaSemanaValido() {
        planItem.setDiaSemana("martes");
        assertEquals("MARTES", planItem.getDiaSemana());

        // Acentos normalizados
        planItem.setDiaSemana("Miércoles");
        assertEquals("MIERCOLES", planItem.getDiaSemana());
    }

    @Test
    public void diaSemanaInvalido() {
        String diaAntes = planItem.getDiaSemana();
        assertThrows(IllegalArgumentException.class, () -> planItem.setDiaSemana(null));
        assertThrows(IllegalArgumentException.class, () -> planItem.setDiaSemana("   "));
        assertThrows(IllegalArgumentException.class, () -> planItem.setDiaSemana("DiaInvalido"));
        assertEquals(diaAntes, planItem.getDiaSemana());
    }

    @Test
    public void testSetHoraInicioValida() {
        planItem.setHoraInicio(LocalTime.of(7, 0));
        assertEquals(LocalTime.of(7, 0), planItem.getHoraInicio());
    }

    @Test
    public void horaInicioInvalida() {
        assertThrows(IllegalArgumentException.class, () -> planItem.setHoraInicio(null));
        // Con segundos o nanosegundos distintos de 0
        assertThrows(IllegalArgumentException.class, () -> planItem.setHoraInicio(LocalTime.of(8, 0, 30)));
        // Fin es 09:30, inicio no puede ser posterior o igual a fin
        assertThrows(IllegalArgumentException.class, () -> planItem.setHoraInicio(LocalTime.of(10, 0)));
    }

    @Test
    public void testSetHoraFinValida() {
        planItem.setHoraFin(LocalTime.of(10, 0));
        assertEquals(LocalTime.of(10, 0), planItem.getHoraFin());
    }

    @Test
    public void horaFinInvalida() {
        assertThrows(IllegalArgumentException.class, () -> planItem.setHoraFin(null));
        assertThrows(IllegalArgumentException.class, () -> planItem.setHoraFin(LocalTime.of(9, 0, 15)));
        // Inicio es 08:00, fin no puede ser anterior o igual a inicio
        assertThrows(IllegalArgumentException.class, () -> planItem.setHoraFin(LocalTime.of(7, 0)));
    }

    @Test
    public void testSetEstadoValido() {
        planItem.setEstado("completado");
        assertEquals("COMPLETADO", planItem.getEstado());

        planItem.setEstado("  pendiente  ");
        assertEquals("PENDIENTE", planItem.getEstado());
    }

    @Test
    public void estadoInvalido() {
        String estadoAntes = planItem.getEstado();
        assertThrows(IllegalArgumentException.class, () -> planItem.setEstado(null));
        assertThrows(IllegalArgumentException.class, () -> planItem.setEstado("   "));
        assertThrows(IllegalArgumentException.class, () -> planItem.setEstado("INDEFINIDO"));
        assertEquals(estadoAntes, planItem.getEstado());
    }

    @Test
    public void marcarCompletadoTest() {
        planItem.marcarCompletado();
        assertEquals("COMPLETADO", planItem.getEstado());
    }

    @Test
    public void reprogramarTest() {
        planItem.reprogramar("Viernes", LocalTime.of(10, 0), LocalTime.of(12, 0));
        assertEquals("VIERNES", planItem.getDiaSemana());
        assertEquals(LocalTime.of(10, 0), planItem.getHoraInicio());
        assertEquals(LocalTime.of(12, 0), planItem.getHoraFin());
    }

    @Test
    public void reprogramarInvalido() {
        assertThrows(IllegalArgumentException.class, () -> planItem.reprogramar("DiaFalso", LocalTime.of(10, 0), LocalTime.of(12, 0)));
        assertThrows(IllegalArgumentException.class, () -> planItem.reprogramar("Lunes", LocalTime.of(12, 0), LocalTime.of(10, 0)));
    }

    @Test
    public void obtenerDuracionMinutosTest() {
        // De 08:00 a 09:30 son 90 minutos
        assertEquals(90, planItem.obtenerDuracionMinutos());
    }

    @Test
    public void obtenerDuracionMinutosSinConfigurar() {
        PlanItem itemSinHorario = new PlanItem();
        assertThrows(IllegalStateException.class, itemSinHorario::obtenerDuracionMinutos);
    }

    @Test
    public void testToString() {
        String resultado = planItem.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("PlanItem"));
        assertTrue(resultado.contains("LUNES"));
        assertTrue(resultado.contains("PENDIENTE"));
    }
}
