package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario(1L, "Juan Perez", "jperez@uce.edu.ec", "hashSecure456", true);
    }

    @Test
    void usuarioDatosValidos() {
        Usuario u = new Usuario(2L, "Maria Gomez", "mgomez@uce.edu.ec", "pass1234", false);

        assertEquals(2L, u.getIdUsuario());
        assertEquals("Maria Gomez", u.getNombre());
        assertEquals("mgomez@uce.edu.ec", u.getCorreo());
        assertEquals("pass1234", u.getPasswordHash());
        assertFalse(u.isActivo());
    }

    @Test
    void usuarioDefecto() {
        Usuario u = new Usuario();

        assertEquals(1L, u.getIdUsuario());
        assertEquals("Usuario Base", u.getNombre());
        assertEquals("usuario@uce.edu.ec", u.getCorreo());
        assertEquals("hashSeguro123", u.getPasswordHash());
        assertTrue(u.isActivo());
    }

    @Test
    public void testCrearUsuario() {
        assertNotNull(usuario);
    }

    @Test
    public void testSetIdUsuarioValido() {
        assertTrue(usuario.setIdUsuario(10L));
        assertEquals(10L, usuario.getIdUsuario());
    }

    @Test
    public void idUsuarioInvalido() {
        Long idAntes = usuario.getIdUsuario();
        assertFalse(usuario.setIdUsuario(null));
        assertFalse(usuario.setIdUsuario(0L));
        assertFalse(usuario.setIdUsuario(-3L));
        assertEquals(idAntes, usuario.getIdUsuario());
    }

    @Test
    public void testSetNombreValido() {
        assertTrue(usuario.setNombre("Carlos Andrade"));
        assertEquals("Carlos Andrade", usuario.getNombre());
    }

    @Test
    public void nombreInvalido() {
        String nombreAntes = usuario.getNombre();
        assertFalse(usuario.setNombre(null));
        assertFalse(usuario.setNombre("   "));
        assertEquals(nombreAntes, usuario.getNombre());
    }

    @Test
    public void testSetCorreoValido() {
        assertTrue(usuario.setCorreo("candrade@uce.edu.ec"));
        assertEquals("candrade@uce.edu.ec", usuario.getCorreo());
    }

    @Test
    public void correoInvalido() {
        String correoAntes = usuario.getCorreo();
        assertFalse(usuario.setCorreo(null));
        assertFalse(usuario.setCorreo("   "));
        assertEquals(correoAntes, usuario.getCorreo());
    }

    @Test
    public void testSetPasswordHashValido() {
        assertTrue(usuario.setPasswordHash("newHash789"));
        assertEquals("newHash789", usuario.getPasswordHash());
    }

    @Test
    public void passwordHashInvalido() {
        String passAntes = usuario.getPasswordHash();
        assertFalse(usuario.setPasswordHash(null));
        assertFalse(usuario.setPasswordHash("   "));
        assertEquals(passAntes, usuario.getPasswordHash());
    }

    @Test
    public void testSetYGetActivo() {
        usuario.setActivo(false);
        assertFalse(usuario.isActivo());

        usuario.setActivo(true);
        assertTrue(usuario.isActivo());
    }

    @Test
    public void autenticarExitoso() {
        // Datos correctos y activo
        assertTrue(usuario.autenticar("jperez@uce.edu.ec", "hashSecure456"));
    }

    @Test
    public void autenticarFallido() {
        // Correo incorrecto
        assertFalse(usuario.autenticar("incorrecto@uce.edu.ec", "hashSecure456"));
        // Clave incorrecta
        assertFalse(usuario.autenticar("jperez@uce.edu.ec", "claveIncorrecta"));
        // Usuario inactivo
        usuario.setActivo(false);
        assertFalse(usuario.autenticar("jperez@uce.edu.ec", "hashSecure456"));
    }

    @Test
    public void actualizarPerfilTest() {
        usuario.actualizarPerfil("Ana Lopez", "alopez@uce.edu.ec");
        assertEquals("Ana Lopez", usuario.getNombre());
        assertEquals("alopez@uce.edu.ec", usuario.getCorreo());
    }

    @Test
    public void testToString() {
        String resultado = usuario.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("Usuario"));
        assertTrue(resultado.contains("Juan Perez"));
        assertTrue(resultado.contains("jperez@uce.edu.ec"));
    }
}