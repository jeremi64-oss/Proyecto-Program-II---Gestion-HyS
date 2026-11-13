package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Comentario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ComentarioTest {

    private Comentario comentario;

    @BeforeEach
    public void setUp() {
        comentario = new Comentario(1, new Date(), "Buen descanso nocturno");
    }

    @Test
    void comentarioDatosValidos() {
        Date fechaRef = new Date();
        Comentario com = new Comentario(2, fechaRef, "Meta cumplida hoy");

        assertEquals(2, com.getIdComentario());
        assertEquals(fechaRef, com.getFecha());
        assertEquals("Meta cumplida hoy", com.getTexto());
    }

    @Test
    void comentarioDefecto() {
        Comentario com = new Comentario();

        assertEquals(1, com.getIdComentario());
        assertNotNull(com.getFecha());
        assertEquals("Sin comentario", com.getTexto());
    }

    @Test
    public void testCrearComentario() {
        assertNotNull(comentario);
    }

    @Test
    public void testGetIdComentario() {
        assertEquals(1, comentario.getIdComentario());
    }

    @Test
    public void testGetFecha() {
        assertNotNull(comentario.getFecha());
    }

    @Test
    public void testGetTexto() {
        assertEquals("Buen descanso nocturno", comentario.getTexto());
    }

    @Test
    public void testSetIdComentarioValido() {
        assertTrue(comentario.setIdComentario(10));
        assertEquals(10, comentario.getIdComentario());
    }

    @Test
    public void idComentarioInvalido() {
        int idAntes = comentario.getIdComentario();
        assertFalse(comentario.setIdComentario(-5));
        assertEquals(idAntes, comentario.getIdComentario());
    }

    @Test
    public void testSetFechaValida() {
        Date nuevaFecha = new Date();
        assertTrue(comentario.setFecha(nuevaFecha));
        assertEquals(nuevaFecha, comentario.getFecha());
    }

    @Test
    public void fechaInvalida() {
        Date fechaAntes = comentario.getFecha();
        assertFalse(comentario.setFecha(null));
        assertEquals(fechaAntes, comentario.getFecha());
    }

    @Test
    public void testSetTextoValido() {
        assertTrue(comentario.setTexto("Hoy dormí 8 horas"));
        assertEquals("Hoy dormí 8 horas", comentario.getTexto());
    }

    @Test
    public void textoInvalido() {
        String textoAntes = comentario.getTexto();
        assertFalse(comentario.setTexto(null));
        assertFalse(comentario.setTexto("   "));
        String textoLargo = "a".repeat(251);
        assertFalse(comentario.setTexto(textoLargo));
        assertEquals(textoAntes, comentario.getTexto());
    }
}
