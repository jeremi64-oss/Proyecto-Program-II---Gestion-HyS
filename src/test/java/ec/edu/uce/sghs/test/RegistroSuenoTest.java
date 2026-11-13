package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.RegistroSueno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroSuenoTest {

    private Date fecha;
    private RegistroSueno registro;
    private List<RegistroSueno> registros;

    @BeforeEach
    void preparar() {
        fecha = fechaConDias(-1);
        registro = new RegistroSueno(1, fecha, 8.0, 4, "Dormí bien");
        registros = new ArrayList<>();
    }

    private Date fechaConDias(int dias) {
        return Date.from(
                LocalDate.now()
                        .plusDays(dias)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
        );
    }

    @Test
    void crearConConstructorCompleto() {
        assertNotNull(registro);
        assertNotNull(registro.getFecha());
        assertNotNull(registro.getHorasDescanso());
        assertNotNull(registro.getCalidadPercibida());
        assertNotNull(registro.getComentario());

        assertEquals(1, registro.getIdRegistro());
        assertEquals(fecha, registro.getFecha());
        assertEquals(8.0, registro.getHorasDescanso(), 0.001);
        assertEquals(4, registro.getCalidadPercibida());
        assertEquals("Dormí bien", registro.getComentario());
    }

    @Test
    void completarConstructorVacioConSetters() {
        RegistroSueno nuevo = new RegistroSueno();

        nuevo.setIdRegistro(2);
        nuevo.setFecha(fecha);
        nuevo.setHorasDescanso(7.5);
        nuevo.setCalidadPercibida(3);
        nuevo.setComentario(" Descanso normal ");

        assertEquals(2, nuevo.getIdRegistro());
        assertEquals(fecha, nuevo.getFecha());
        assertEquals(7.5, nuevo.getHorasDescanso(), 0.001);
        assertEquals(3, nuevo.getCalidadPercibida());
        assertEquals("Descanso normal", nuevo.getComentario());
    }

    @Test
    void rechazarRegistroIncompleto() {
        RegistroSueno incompleto = new RegistroSueno();

        assertThrows(
                IllegalArgumentException.class,
                () -> incompleto.registrar(registros)
        );

        assertTrue(registros.isEmpty());
    }

    @Test
    void aceptarFechaActual() {
        Date hoy = fechaConDias(0);

        registro.setFecha(hoy);

        assertEquals(hoy, registro.getFecha());
    }

    @Test
    void rechazarFechaNulaOFutura() {
        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setFecha(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setFecha(fechaConDias(2))
        );

        assertEquals(fecha, registro.getFecha());
    }

    @Test
    void aceptarLimitesDeHoras() {
        registro.setHorasDescanso(0.0);
        assertEquals(0.0, registro.getHorasDescanso(), 0.001);

        registro.setHorasDescanso(24.0);
        assertEquals(24.0, registro.getHorasDescanso(), 0.001);
    }

    @Test
    void rechazarHorasNulasOFueraDelRango() {
        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setHorasDescanso(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setHorasDescanso(-0.1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setHorasDescanso(24.1)
        );

        assertEquals(8.0, registro.getHorasDescanso(), 0.001);
    }

    @Test
    void rechazarHorasNoFinitas() {
        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setHorasDescanso(Double.NaN)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setHorasDescanso(Double.POSITIVE_INFINITY)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setHorasDescanso(Double.NEGATIVE_INFINITY)
        );
    }

    @Test
    void aceptarCalidadDeUnoACinco() {
        for (int calidad = 1; calidad <= 5; calidad++) {
            registro.setCalidadPercibida(calidad);

            assertEquals(calidad, registro.getCalidadPercibida());
        }
    }

    @Test
    void rechazarCalidadNulaOFueraDelRango() {
        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setCalidadPercibida(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setCalidadPercibida(0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setCalidadPercibida(6)
        );

        assertEquals(4, registro.getCalidadPercibida());
    }

    @Test
    void permitirComentarioOpcional() {
        registro.setComentario(null);

        assertNotNull(registro.getComentario());
        assertEquals("", registro.getComentario());

        registro.setComentario("");
        assertEquals("", registro.getComentario());

        registro.setComentario("   ");
        assertEquals("", registro.getComentario());
    }

    @Test
    void rechazarIdentificadorInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setIdRegistro(0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> registro.setIdRegistro(-1)
        );

        assertEquals(1, registro.getIdRegistro());
    }

    @Test
    void registrarSueno() {
        registro.registrar(registros);

        assertFalse(registros.isEmpty());
        assertEquals(1, registros.size());
        assertEquals(1, registros.get(0).getIdRegistro());
        assertEquals(fecha, registros.get(0).getFecha());
    }

    @Test
    void guardarCopiaIndependienteDelRegistro() {
        registro.registrar(registros);

        registro.setHorasDescanso(6.0);
        registro.setFecha(fechaConDias(-2));

        assertEquals(8.0, registros.get(0).getHorasDescanso(), 0.001);
        assertEquals(fecha, registros.get(0).getFecha());
    }

    @Test
    void rechazarFechaRepetida() {
        registro.registrar(registros);

        RegistroSueno repetido =
                new RegistroSueno(2, fecha, 6.0, 2, "");

        assertThrows(
                IllegalArgumentException.class,
                () -> repetido.registrar(registros)
        );

        assertEquals(1, registros.size());
    }

    @Test
    void rechazarMismoDiaAunqueCambieLaHora() {
        registro.registrar(registros);

        Date otraHora = new Date(fecha.getTime() + 60000);

        RegistroSueno repetido =
                new RegistroSueno(2, otraHora, 6.0, 2, "");

        assertThrows(
                IllegalArgumentException.class,
                () -> repetido.registrar(registros)
        );

        assertEquals(1, registros.size());
    }

    @Test
    void rechazarIdentificadorRepetido() {
        registro.registrar(registros);

        RegistroSueno repetido =
                new RegistroSueno(1, fechaConDias(-2), 7.0, 3, "");

        assertThrows(
                IllegalArgumentException.class,
                () -> repetido.registrar(registros)
        );

        assertEquals(1, registros.size());
    }

    @Test
    void permitirMismaFechaParaOtroEstudiante() {
        List<RegistroSueno> otrosRegistros = new ArrayList<>();

        registro.registrar(registros);

        RegistroSueno otro =
                new RegistroSueno(2, fecha, 6.0, 3, "");

        otro.registrar(otrosRegistros);

        assertEquals(1, registros.size());
        assertEquals(1, otrosRegistros.size());
    }

    @Test
    void consultarHistorialOrdenado() {
        RegistroSueno antiguo =
                new RegistroSueno(2, fechaConDias(-3), 7.0, 3, "");

        registro.registrar(registros);
        antiguo.registrar(registros);

        List<RegistroSueno> historial =
                RegistroSueno.consultarHistorial(registros);

        assertNotNull(historial);
        assertEquals(2, historial.size());
        assertEquals(2, historial.get(0).getIdRegistro());
        assertEquals(1, historial.get(1).getIdRegistro());

        assertEquals(1, registros.get(0).getIdRegistro());
    }

    @Test
    void consultarHistorialVacio() {
        List<RegistroSueno> historial =
                RegistroSueno.consultarHistorial(registros);

        assertNotNull(historial);
        assertTrue(historial.isEmpty());
    }

    @Test
    void historialNoModificaRegistrosGuardados() {
        registro.registrar(registros);

        List<RegistroSueno> historial =
                RegistroSueno.consultarHistorial(registros);

        historial.get(0).setHorasDescanso(4.0);
        historial.clear();

        assertTrue(historial.isEmpty());
        assertEquals(1, registros.size());
        assertEquals(8.0, registros.get(0).getHorasDescanso(), 0.001);
    }

    @Test
    void protegerFechaDeCambiosExternos() {
        Date fechaOriginal = registro.getFecha();

        fecha.setTime(fechaConDias(-10).getTime());

        assertEquals(fechaOriginal, registro.getFecha());

        Date fechaObtenida = registro.getFecha();
        fechaObtenida.setTime(fechaConDias(-20).getTime());

        assertEquals(fechaOriginal, registro.getFecha());
    }

    @Test
    void rechazarListasNulas() {
        assertThrows(
                IllegalArgumentException.class,
                () -> registro.registrar(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> RegistroSueno.consultarHistorial(null)
        );
    }

    @Test
    void rechazarListaConElementosNulos() {
        registros.add(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> registro.registrar(registros)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> RegistroSueno.consultarHistorial(registros)
        );
    }
}
