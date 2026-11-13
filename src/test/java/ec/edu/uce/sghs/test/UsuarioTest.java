package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.Usuario;
import ec.edu.uce.sghs.dominio.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;
    private Rol rolEstudiante;

    @BeforeEach
    public void setUp() {
        rolEstudiante = new Rol(1, "estudiante");
        usuario = new Usuario(1, "jaidrovo@uce.edu.ec", "12345678", true, 0, rolEstudiante);
    }

    @Test
    void usuarioDatosValidos() {
        Rol rolAdmin = new Rol(2, "administrador");
        Usuario usu = new Usuario(2, "admin@uce.edu.ec", "adminpass", true, 0, rolAdmin);

        assertEquals(2, usu.getIdUsuario());
        assertEquals("admin@uce.edu.ec", usu.getCorreo());
        assertEquals("adminpass", usu.getContrasena());
        assertTrue(usu.getEstadoCuenta());
        assertEquals(0, usu.getIntentosFallidos());
        assertEquals(rolAdmin, usu.getRol());
    }

    @Test
    void usuarioDefecto() {
        Usuario usu = new Usuario();

        assertEquals(1, usu.getIdUsuario());
        assertEquals("correogenerico@uce.edu.ec", usu.getCorreo());
        assertEquals("sin contraseña", usu.getContrasena());
        assertTrue(usu.getEstadoCuenta());
        assertEquals(0, usu.getIntentosFallidos());
        assertNull(usu.getRol());
    }

    @Test
    public void testCrearUsuario() {
        assertNotNull(usuario);
    }

    @Test
    public void testGetIdUsuario() {
        assertEquals(1, usuario.getIdUsuario());
    }

    @Test
    public void testGetCorreo() {
        assertEquals("jaidrovo@uce.edu.ec", usuario.getCorreo());
    }

    @Test
    public void testGetContrasena() {
        assertEquals("12345678", usuario.getContrasena());
    }

    @Test
    public void testGetEstadoCuenta() {
        assertTrue(usuario.getEstadoCuenta());
    }

    @Test
    public void testGetIntentosFallidos() {
        assertEquals(0, usuario.getIntentosFallidos());
    }

    @Test
    public void testGetRol() {
        assertEquals(rolEstudiante, usuario.getRol());
    }

    @Test
    public void testSetIdUsuarioValido() {
        assertTrue(usuario.setIdUsuario(10));
        assertEquals(10, usuario.getIdUsuario());
    }

    @Test
    public void idUsuarioInvalido() {
        int idAntes = usuario.getIdUsuario();
        assertFalse(usuario.setIdUsuario(0));
        assertFalse(usuario.setIdUsuario(-3));
        assertEquals(idAntes, usuario.getIdUsuario());
    }

    @Test
    public void testSetCorreoValido() {
        assertTrue(usuario.setCorreo("nuevocorreo@uce.edu.ec"));
        assertEquals("nuevocorreo@uce.edu.ec", usuario.getCorreo());
    }

    @Test
    public void correoInvalido() {
        String correoAntes = usuario.getCorreo();
        assertFalse(usuario.setCorreo(null));
        assertFalse(usuario.setCorreo("   "));
        assertFalse(usuario.setCorreo("correosinformato"));
        assertEquals(correoAntes, usuario.getCorreo());
    }

    @Test
    public void testSetContrasenaValida() {
        assertTrue(usuario.setContrasena("nuevopass123"));
        assertEquals("nuevopass123", usuario.getContrasena());
    }

    @Test
    public void contrasenaInvalida() {
        String passAntes = usuario.getContrasena();
        assertFalse(usuario.setContrasena(null));
        assertFalse(usuario.setContrasena("   "));
        assertFalse(usuario.setContrasena("12345"));
        assertEquals(passAntes, usuario.getContrasena());
    }

    @Test
    public void testSetEstadoCuenta() {
        usuario.setEstadoCuenta(false);
        assertFalse(usuario.getEstadoCuenta());

        usuario.setEstadoCuenta(true);
        assertTrue(usuario.getEstadoCuenta());
    }

    @Test
    public void testSetIntentosFallidosValido() {
        assertTrue(usuario.setIntentosFallidos(2));
        assertEquals(2, usuario.getIntentosFallidos());
    }

    @Test
    public void intentosFallidosInvalidos() {
        int intentosAntes = usuario.getIntentosFallidos();
        assertFalse(usuario.setIntentosFallidos(-1));
        assertFalse(usuario.setIntentosFallidos(4));
        assertEquals(intentosAntes, usuario.getIntentosFallidos());
    }

    @Test
    public void testSetRolValido() {
        Rol nuevoRol = new Rol(2, "administrador");
        assertTrue(usuario.setRol(nuevoRol));
        assertEquals(nuevoRol, usuario.getRol());
    }

    @Test
    public void rolInvalido() {
        Rol rolAntes = usuario.getRol();
        assertFalse(usuario.setRol(null));
        assertEquals(rolAntes, usuario.getRol());
    }
}
