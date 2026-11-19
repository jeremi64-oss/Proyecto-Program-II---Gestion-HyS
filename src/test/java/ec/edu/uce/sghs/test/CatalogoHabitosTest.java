package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.CatalogoHabitos;
import ec.edu.uce.sghs.dominio.Habito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogoHabitosTest {

    private CatalogoHabitos catalogo;
    private Habito habitoPrueba;

    @BeforeEach
    public void setUp() {
        catalogo = new CatalogoHabitos(1L, "Salud y Bienestar", "v1.0");
        // Suponiendo un objeto Habito básico para las pruebas de plantillas
        habitoPrueba = new Habito(); // O el constructor correspondiente de Habito
        habitoPrueba.setIdHabito(100L);
        habitoPrueba.setPolaridad(Habito.PolaridadHabito.POSITIVO);
        habitoPrueba.setActivo(true);
    }

    @Test
    void catalogoDatosValidos() {
        CatalogoHabitos cat = new CatalogoHabitos(2L, "Nutricion", "v2.0");

        assertEquals(2L, cat.getIdCatalogo());
        assertEquals("Nutricion", cat.getCategoria());
        assertEquals("v2.0", cat.getVersion());
        assertNotNull(cat.getPlantillas());
        assertTrue(cat.getPlantillas().isEmpty());
    }

    @Test
    void catalogoDefecto() {
        CatalogoHabitos cat = new CatalogoHabitos();

        assertEquals(0L, cat.getIdCatalogo());
        assertEquals("", cat.getCategoria());
        assertEquals("", cat.getVersion());
        assertTrue(cat.getPlantillas().isEmpty());
    }

    @Test
    public void testCrearCatalogo() {
        assertNotNull(catalogo);
    }

    @Test
    public void testGetIdCatalogo() {
        assertEquals(1L, catalogo.getIdCatalogo());
    }

    @Test
    public void testGetCategoria() {
        assertEquals("Salud y Bienestar", catalogo.getCategoria());
    }

    @Test
    public void testGetVersion() {
        assertEquals("v1.0", catalogo.getVersion());
    }

    @Test
    public void testSetIdCatalogoValido() {
        catalogo.setIdCatalogo(10L);
        assertEquals(10L, catalogo.getIdCatalogo());
    }

    @Test
    public void idCatalogoInvalido() {
        Long idAntes = catalogo.getIdCatalogo();
        assertThrows(IllegalArgumentException.class, () -> catalogo.setIdCatalogo(null));
        assertThrows(IllegalArgumentException.class, () -> catalogo.setIdCatalogo(0L));
        assertThrows(IllegalArgumentException.class, () -> catalogo.setIdCatalogo(-5L));
        assertEquals(idAntes, catalogo.getIdCatalogo());
    }

    @Test
    public void testSetCategoriaValida() {
        catalogo.setCategoria("  Sueno y Descanso  ");
        assertEquals("Sueno y Descanso", catalogo.getCategoria());
    }

    @Test
    public void categoriaInvalida() {
        String categoriaAntes = catalogo.getCategoria();
        assertThrows(IllegalArgumentException.class, () -> catalogo.setCategoria(null));
        assertThrows(IllegalArgumentException.class, () -> catalogo.setCategoria("   "));
        assertEquals(categoriaAntes, catalogo.getCategoria());
    }

    @Test
    public void testSetVersionValida() {
        catalogo.setVersion("  v1.2.3  ");
        assertEquals("v1.2.3", catalogo.getVersion());
    }

    @Test
    public void versionInvalida() {
        String versionAntes = catalogo.getVersion();
        assertThrows(IllegalArgumentException.class, () -> catalogo.setVersion(null));
        assertThrows(IllegalArgumentException.class, () -> catalogo.setVersion("   "));
        assertEquals(versionAntes, catalogo.getVersion());
    }

    @Test
    public void testAgregarPlantillaValida() {
        catalogo.agregarPlantilla(habitoPrueba, true);
        assertEquals(1, catalogo.getPlantillas().size());
        assertEquals(habitoPrueba, catalogo.obtenerPlantilla(100L));
    }

    @Test
    public void agregarPlantillaNulaOInvalida() {
        assertThrows(IllegalArgumentException.class, () -> catalogo.agregarPlantilla(null, false));

        Habito habitoInvalido = new Habito();
        habitoInvalido.setIdHabito(null);
        assertThrows(IllegalArgumentException.class, () -> catalogo.agregarPlantilla(habitoInvalido, false));
    }

    @Test
    public void agregarPlantillaDuplicada() {
        catalogo.agregarPlantilla(habitoPrueba, false);

        Habito habitoDuplicado = new Habito();
        habitoDuplicado.setIdHabito(100L);

        assertThrows(IllegalArgumentException.class, () -> catalogo.agregarPlantilla(habitoDuplicado, false));
    }

    @Test
    public void testListarPorPolaridadValida() {
        catalogo.agregarPlantilla(habitoPrueba, false);
        List<Habito> resultados = catalogo.listarPorPolaridad("POSITIVO");

        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size());
    }

    @Test
    public void listarPorPolaridadInvalida() {
        assertThrows(IllegalArgumentException.class, () -> catalogo.listarPorPolaridad(null));
        assertThrows(IllegalArgumentException.class, () -> catalogo.listarPorPolaridad("   "));
        assertThrows(IllegalArgumentException.class, () -> catalogo.listarPorPolaridad("POLARIDAD_INEXISTENTE"));
    }

    @Test
    public void testObtenerPlantillaInexistente() {
        assertThrows(IllegalArgumentException.class, () -> catalogo.obtenerPlantilla(999L));
        assertThrows(IllegalArgumentException.class, () -> catalogo.obtenerPlantilla(0L));
        assertThrows(IllegalArgumentException.class, () -> catalogo.obtenerPlantilla(null));
    }

    @Test
    public void testToString() {
        catalogo.agregarPlantilla(habitoPrueba, true);
        String descripcion = catalogo.toString();
        assertTrue(descripcion.contains("cantidadPlantillas=1"));
        assertTrue(descripcion.contains("idHabito=100"));
    }
    @Test
    void editarYEliminarPlantillasConservaLasSugerenciasDeSueno() {
        Habito primero = new Habito(31L, "Leer", Habito.PolaridadHabito.POSITIVO, 0, 0, true);
        Habito segundo = new Habito(32L, "Descansar", Habito.PolaridadHabito.POSITIVO, 0, 0, true);
        catalogo.agregarPlantilla(primero);
        catalogo.agregarPlantilla(segundo, true);
        assertThrows(IllegalArgumentException.class, () -> catalogo.agregarPlantilla(primero));
        catalogo.eliminarPlantilla(31L);
        assertEquals(segundo, catalogo.sugerirHabitosSueno().get(0));

        Habito editado = new Habito(32L, "Dormir a tiempo", Habito.PolaridadHabito.POSITIVO, 0, 0, true);
        catalogo.editarPlantilla(editado, false);
        assertEquals("Dormir a tiempo", catalogo.obtenerPlantilla(32L).getNombre());
        assertTrue(catalogo.sugerirHabitosSueno().isEmpty());
        assertThrows(IllegalArgumentException.class, () -> catalogo.obtenerPlantilla(31L));
    }
}
