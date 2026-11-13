package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Habito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HabitoTest {

    private Habito habito;
    private int idHabito;
    private String nombre;
    private String descripcion;
    private String estado;

    @BeforeEach
    public void setUp(){ habito = new Habito( 2, "Dormir",
            "Cuerpo y mente entran a un estado de reposo, diriamente", "Activo");
    }

    @Test
    void habitoValido(){
        Habito hab = new Habito( 1, "Dibujar", "Forma de distraerme", "Activo");
        assertEquals( 1, hab.getIdHabito());
        assertEquals("Dibujar", hab.getNombre());
        assertEquals("Forma de distraerme", hab.getDescripcion());
        assertEquals( "Activo", hab.getEstado());
    }

    @Test
    void HabitoNoNulo(){
        Habito hab1 = new Habito();
        assertNotNull(hab1.getIdHabito());
        assertNotNull(hab1.getNombre());
        assertNotNull(hab1.getDescripcion());
        assertNotNull(hab1.getEstado());
    }

    @Test
    void habitoPorDefecto(){
        Habito hab = new Habito();
        assertEquals(0, hab.getIdHabito());
        assertEquals("Sin nombre", hab.getNombre());
        assertEquals("Sin descripcion", hab.getDescripcion());
        assertEquals("nulo", hab.getEstado());
    }

    @Test
    void CrearHabito(){
        assertNotNull(habito);
    }

    @Test
    void getIdHabito(){
        assertEquals( 2, habito.getIdHabito());
    }

    @Test
    void getNombre(){
        assertEquals("Dormir", habito.getNombre());
    }

    @Test
    void getDescripcion(){
        assertEquals("Cuerpo y mente entran a un estado de reposo, diriamente", habito.getDescripcion());
    }

    @Test
    void getEstado(){
        assertEquals( "Activo", habito.getEstado());
    }

    @Test
    void setIdHabitoValido(){
        assertTrue(habito.setIdHabito(5));
        assertEquals(5, habito.getIdHabito());
    }
    @Test
    void setIdHabitoInvalido(){
        int idHabitoAntes = habito.getIdHabito();
        assertFalse(habito.setIdHabito(0));
        assertFalse(habito.setIdHabito(-2));
        assertEquals(idHabitoAntes, habito.getIdHabito());
    }

    @Test
    void setNombreValido(){
        assertTrue(habito.setNombre("Videojuegos"));
        assertEquals("Videojuegos", habito.getNombre());
    }
    @Test
    void setNombreInvalido(){
        String nombreAntes = habito.getNombre();
        assertFalse(habito.setNombre(null));
        assertFalse(habito.setNombre(" "));
        assertFalse(habito.setNombre( "Videojuego1"));
        assertFalse(habito.setNombre("1Videojuego"));
        assertFalse(habito.setNombre("123"));
        assertEquals(nombreAntes, habito.getNombre());
    }

    @Test
    void setDescripcionValido(){
        assertTrue(habito.setDescripcion("Horas para relajarme"));
        assertEquals( "Horas para relajarme", habito.getDescripcion());

    }
    @Test
    void setDescripcionInvalido(){
        String descAntes = habito.getDescripcion();
        assertFalse(habito.setDescripcion(null));
        assertFalse(habito.setDescripcion(""));
        assertFalse(habito.setDescripcion("   "));
        assertEquals(descAntes, habito.getDescripcion());
    }

    @Test
    void setEstadoValido() {
        assertTrue(habito.setEstado("Activo"));
        assertEquals("Activo", habito.getEstado());
        assertTrue(habito.setEstado("Inactivo"));
        assertEquals("Inactivo", habito.getEstado());
    }
    @Test
    void setEstadoInvalido() {
        String estadoAntes = habito.getEstado();
        assertFalse(habito.setEstado(null));
        assertFalse(habito.setEstado(""));
        assertFalse(habito.setEstado("   "));
        assertEquals(estadoAntes, habito.getEstado());
    }

    @Test
    void mostrarDatos() {
        String esperado = "ID: 2\nNombre: Dormir\nDescripción: Cuerpo y mente entran a un estado de reposo, diriamente\nEstado: Activo";
        assertEquals(esperado, habito.toString());
    }
}

