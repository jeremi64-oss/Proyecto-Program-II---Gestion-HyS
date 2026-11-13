package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.RegistroCumplimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroCumplimientoTest {

    private RegistroCumplimiento registro;

    @BeforeEach
    public void setUp() {
        registro = new RegistroCumplimiento(1, new Date(), false);
    }

    @Test
    void registroCumplimientoDatosValidos() {
        Date fechaRef = new Date();
        RegistroCumplimiento reg = new RegistroCumplimiento(2, fechaRef, true);

        assertEquals(2, reg.getIdRegistro());
        assertEquals(fechaRef, reg.getFecha());
        assertTrue(reg.getCumplido());
    }

    @Test
    void registroCumplimientoDefecto() {
        RegistroCumplimiento reg = new RegistroCumplimiento();

        assertEquals(1, reg.getIdRegistro());
        assertNotNull(reg.getFecha());
        assertFalse(reg.getCumplido());
    }

    @Test
    public void testCrearRegistroCumplimiento() {
        assertNotNull(registro);
    }

    @Test
    public void testGetIdRegistro() {
        assertEquals(1, registro.getIdRegistro());
    }

    @Test
    public void testGetFecha() {
        assertNotNull(registro.getFecha());
    }

    @Test
    public void testGetCumplido() {
        assertFalse(registro.getCumplido());
    }

    @Test
    public void testSetIdRegistroValido() {
        assertTrue(registro.setIdRegistro(10));
        assertEquals(10, registro.getIdRegistro());
    }

    @Test
    public void idRegistroInvalido() {
        int idAntes = registro.getIdRegistro();
        assertFalse(registro.setIdRegistro(-1));
        assertEquals(idAntes, registro.getIdRegistro());
    }

    @Test
    public void testSetFechaValida() {
        Date nuevaFecha = new Date();
        assertTrue(registro.setFecha(nuevaFecha));
        assertEquals(nuevaFecha, registro.getFecha());
    }

    @Test
    public void fechaInvalida() {
        Date fechaAntes = registro.getFecha();
        assertFalse(registro.setFecha(null));
        assertEquals(fechaAntes, registro.getFecha());
    }

    @Test
    public void testSetCumplido() {
        registro.setCumplido(true);
        assertTrue(registro.getCumplido());

        registro.setCumplido(false);
        assertFalse(registro.getCumplido());
    }
}
