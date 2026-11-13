package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.ProgramacionSemanal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramacionSemanalTest {

    private ProgramacionSemanal programacion;
    private List<ProgramacionSemanal> programaciones;

    @BeforeEach
    void preparar() {
        programacion = new ProgramacionSemanal(1, "lunes");
        programaciones = new ArrayList<>();
    }

    @Test
    void crearConConstructorCompleto() {
        assertNotNull(programacion);
        assertNotNull(programacion.getDiaSemana());
        assertEquals(1, programacion.getIdProgramacion());
        assertEquals("lunes", programacion.getDiaSemana());
    }

    @Test
    void completarConstructorVacioConSetters() {
        ProgramacionSemanal nueva = new ProgramacionSemanal();

        nueva.setIdProgramacion(2);
        nueva.setDiaSemana("martes");

        assertEquals(2, nueva.getIdProgramacion());
        assertEquals("martes", nueva.getDiaSemana());
    }

    @Test
    void aceptarLosSieteDiasDeLaSemana() {
        String[] dias = {
                "lunes", "martes", "miercoles", "jueves",
                "viernes", "sabado", "domingo"
        };

        for (String dia : dias) {
            programacion.setDiaSemana(dia);

            assertEquals(dia, programacion.getDiaSemana());
        }
    }

    @Test
    void normalizarMayusculasEspaciosYTildes() {
        programacion.setDiaSemana(" MIÉRCOLES ");

        assertEquals("miercoles", programacion.getDiaSemana());

        programacion.setDiaSemana(" SÁBADO ");

        assertEquals("sabado", programacion.getDiaSemana());
    }

    @Test
    void rechazarDiaNuloOVacio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.setDiaSemana(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.setDiaSemana("")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.setDiaSemana("   ")
        );

        assertEquals("lunes", programacion.getDiaSemana());
    }

    @Test
    void rechazarOpcionesNoPermitidas() {
        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.setDiaSemana("feriado")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.setDiaSemana("1")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.setDiaSemana("lunes,martes")
        );

        assertEquals("lunes", programacion.getDiaSemana());
    }

    @Test
    void rechazarDatosInvalidosEnConstructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProgramacionSemanal(1, null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new ProgramacionSemanal(1, "")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new ProgramacionSemanal(1, "otro")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new ProgramacionSemanal(0, "lunes")
        );
    }

    @Test
    void rechazarIdentificadorInvalidoSinCambiarElAnterior() {
        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.setIdProgramacion(0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.setIdProgramacion(-1)
        );

        assertEquals(1, programacion.getIdProgramacion());
    }

    @Test
    void asignarDiaAlHabito() {
        programacion.asignarDias(programaciones);

        assertFalse(programaciones.isEmpty());
        assertEquals(1, programaciones.size());
        assertEquals(1, programaciones.get(0).getIdProgramacion());
        assertEquals("lunes", programaciones.get(0).getDiaSemana());
    }

    @Test
    void asignarTodaLaSemana() {
        String[] dias = {
                "lunes", "martes", "miercoles", "jueves",
                "viernes", "sabado", "domingo"
        };

        for (int i = 0; i < dias.length; i++) {
            ProgramacionSemanal nueva =
                    new ProgramacionSemanal(i + 1, dias[i]);

            nueva.asignarDias(programaciones);
        }

        assertEquals(7, programaciones.size());
    }

    @Test
    void rechazarDiaRepetido() {
        programacion.asignarDias(programaciones);

        ProgramacionSemanal repetida =
                new ProgramacionSemanal(2, " LUNES ");

        assertThrows(
                IllegalArgumentException.class,
                () -> repetida.asignarDias(programaciones)
        );

        assertEquals(1, programaciones.size());
    }

    @Test
    void rechazarIdentificadorRepetido() {
        programacion.asignarDias(programaciones);

        ProgramacionSemanal repetida =
                new ProgramacionSemanal(1, "martes");

        assertThrows(
                IllegalArgumentException.class,
                () -> repetida.asignarDias(programaciones)
        );

        assertEquals(1, programaciones.size());
    }

    @Test
    void permitirMismoDiaParaOtroHabito() {
        List<ProgramacionSemanal> otroHabito = new ArrayList<>();

        programacion.asignarDias(programaciones);

        ProgramacionSemanal otra =
                new ProgramacionSemanal(2, "lunes");

        otra.asignarDias(otroHabito);

        assertEquals(1, programaciones.size());
        assertEquals(1, otroHabito.size());
    }

    @Test
    void guardarCopiaIndependiente() {
        programacion.asignarDias(programaciones);

        programacion.setIdProgramacion(2);
        programacion.setDiaSemana("viernes");

        assertEquals(1, programaciones.get(0).getIdProgramacion());
        assertEquals("lunes", programaciones.get(0).getDiaSemana());

        assertEquals(2, programacion.getIdProgramacion());
        assertEquals("viernes", programacion.getDiaSemana());
    }

    @Test
    void rechazarProgramacionIncompleta() {
        ProgramacionSemanal vacia = new ProgramacionSemanal();

        assertThrows(
                IllegalArgumentException.class,
                () -> vacia.asignarDias(programaciones)
        );

        vacia.setIdProgramacion(2);

        assertThrows(
                IllegalArgumentException.class,
                () -> vacia.asignarDias(programaciones)
        );

        assertTrue(programaciones.isEmpty());
    }

    @Test
    void rechazarListaNula() {
        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.asignarDias(null)
        );
    }

    @Test
    void rechazarListaConElementosNulosOIncompletos() {
        programaciones.add(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.asignarDias(programaciones)
        );

        assertEquals(1, programaciones.size());

        programaciones.clear();
        programaciones.add(new ProgramacionSemanal());

        assertThrows(
                IllegalArgumentException.class,
                () -> programacion.asignarDias(programaciones)
        );

        assertEquals(1, programaciones.size());
    }
}
