package ec.edu.uce.sghs.dominio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Objects;

public class GestorSeguimiento {
    private Usuario[] usuarios;
    private CatalogoHabitos catalogo;
    private String[] historialSueno;
    private int contadorUsuarios;
    private int contadorSuenos;
    private long siguienteIdCronograma = 1L;
    private long proximoIdUsuario = 1L;

    public GestorSeguimiento() {
        this(10, null);
    }

    public GestorSeguimiento(int capacidadUsuarios, CatalogoHabitos catalogo) {
        this.usuarios = new Usuario[capacidadUsuarios];
        this.historialSueno = new String[50];
        this.catalogo = catalogo;
    }

    public boolean agregarUsuario(Usuario usuario) {
        if (!datosUsuarioValidos(usuario) || contadorUsuarios == usuarios.length
                || hayUsuarioDuplicado(usuarios, contadorUsuarios, usuario, null)) {
            return false;
        }
        usuarios[contadorUsuarios++] = usuario;
        actualizarProximoIdUsuario(usuario.getIdUsuario());
        return true;
    }

    public Usuario consultarUsuario(Long idUsuario) {
        int posicion = indiceUsuario(idUsuario);
        return posicion < 0 ? null : usuarios[posicion];
    }

    public boolean editarUsuario(Usuario datos) {
        if (!datosUsuarioValidos(datos)) {
            return false;
        }
        Usuario actual = consultarUsuario(datos.getIdUsuario());
        if (actual == null || hayUsuarioDuplicado(usuarios, contadorUsuarios, datos, actual)) {
            return false;
        }

        actual.actualizarPerfil(datos.getNombre(), datos.getCorreo());
        actual.setPasswordHash(datos.getPasswordHash());
        actual.setActivo(datos.isActivo());
        return true;
    }

    public boolean eliminarUsuario(Long idUsuario) {
        int posicion = indiceUsuario(idUsuario);
        if (posicion < 0) {
            return false;
        }
        System.arraycopy(usuarios, posicion + 1, usuarios, posicion, contadorUsuarios - posicion - 1);
        usuarios[--contadorUsuarios] = null;
        return true;
    }

    public long siguienteIdUsuario() {
        long id = proximoIdUsuario;
        while (indiceUsuario(id) >= 0) {
            id = id == Long.MAX_VALUE ? 1L : id + 1;
        }
        return id;
    }

    private void actualizarProximoIdUsuario(long idUsado) {
        if (idUsado >= proximoIdUsuario && idUsado < Long.MAX_VALUE) {
            proximoIdUsuario = idUsado + 1;
        }
    }

    private boolean datosUsuarioValidos(Usuario usuario) {
        return usuario != null && usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0
                && usuario.getNombre() != null && !usuario.getNombre().isBlank()
                && usuario.getCorreo() != null && !usuario.getCorreo().isBlank()
                && usuario.getPasswordHash() != null && !usuario.getPasswordHash().isBlank();
    }

    private boolean hayUsuarioDuplicado(Usuario[] lista, int cantidad, Usuario usuario, Usuario ignorado) {
        for (int i = 0; i < cantidad; i++) {
            Usuario existente = lista[i];
            if (existente != ignorado && (existente.equals(usuario)
                    || usuario.getIdUsuario().equals(existente.getIdUsuario())
                    || usuario.getCorreo().equalsIgnoreCase(existente.getCorreo()))) {
                return true;
            }
        }
        return false;
    }

    private int indiceUsuario(Long idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            return -1;
        }
        for (int i = 0; i < contadorUsuarios; i++) {
            if (idUsuario.equals(usuarios[i].getIdUsuario())) {
                return i;
            }
        }
        return -1;
    }

    public boolean gestionarCuenta(Usuario admin, Usuario objetivo, boolean activo) {
        if (!esAdministradorActivo(admin) || objetivo == null) {
            return false;
        }
        objetivo.setActivo(activo);
        return true;
    }

    public boolean asignarRolAdministrador(Usuario admin, Usuario objetivo) {
        return esAdministradorActivo(admin) && objetivo != null && objetivo.setRol(admin.getRol());
    }

    private boolean esAdministradorActivo(Usuario usuario) {
        return usuario != null && usuario.isActivo() && usuario.getRol() != null
                && usuario.getRol().getTipo() == Rol.TipoRol.ADMINISTRADOR;
    }

    public boolean registrarSueno(Usuario usuario, LocalDate fecha, LocalTime dormir, LocalTime despertar) {
        if (usuario == null || fecha == null || dormir == null || despertar == null
                || contadorSuenos == historialSueno.length) {
            return false;
        }
        historialSueno[contadorSuenos++] = "ID Usuario: " + usuario.getIdUsuario()
                + " | Fecha: " + fecha + " | Dormir: " + dormir + " | Despertar: " + despertar;
        return true;
    }

    public CronogramaSemanal generarPlanSeguimiento(Usuario usuario, Habito[] habitos) {
        if (usuario == null) {
            return null;
        }
        if (usuario.getCronograma() != null) {
            return usuario.getCronograma();
        }

        LocalDate inicio = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        CronogramaSemanal cronograma = usuario.iniciarCronograma(siguienteIdCronograma++, inicio, 10);
        if (habitos != null) {
            LocalTime hora = LocalTime.of(8, 0);
            for (Habito habito : habitos) {
                if (habito != null) {
                    LocalTime fin = hora.plusMinutes(30);
                    if (!cronograma.agregarPlanItem("Lunes", hora, fin, habito)) {
                        break;
                    }
                    hora = fin;
                }
            }
        }
        return cronograma;
    }

    public String generarReporteProgreso(Usuario usuario, LocalDate inicio, LocalDate fin) {
        return " -- Reporte de Progreso [Usuario: -- " + usuario.getNombre() + "] - Periodo: " + inicio + " al " + fin;
    }

    public Usuario[] getUsuarios() {
        return usuarios.clone();
    }

    public void setUsuarios(Usuario[] usuarios) {
        Objects.requireNonNull(usuarios, "El arreglo de usuarios es obligatorio.");
        Usuario[] nuevoArreglo = new Usuario[usuarios.length];
        int cantidad = 0;
        for (Usuario usuario : usuarios) {
            if (usuario != null) {
                if (!datosUsuarioValidos(usuario) || hayUsuarioDuplicado(nuevoArreglo, cantidad, usuario, null)) {
                    throw new IllegalArgumentException("El arreglo contiene un usuario inválido o duplicado.");
                }
                nuevoArreglo[cantidad++] = usuario;
            }
        }
        this.usuarios = nuevoArreglo;
        this.contadorUsuarios = cantidad;
        for (int i = 0; i < cantidad; i++) {
            actualizarProximoIdUsuario(nuevoArreglo[i].getIdUsuario());
        }
    }

    public CatalogoHabitos getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(CatalogoHabitos catalogo) {
        this.catalogo = catalogo;
    }

    public String[] getHistorialSueno() {
        return historialSueno;
    }

    public void setHistorialSueno(String[] historialSueno) {
        this.historialSueno = Objects.requireNonNull(historialSueno);
        this.contadorSuenos = 0;
        while (contadorSuenos < historialSueno.length && historialSueno[contadorSuenos] != null) {
            contadorSuenos++;
        }
    }

    @Override
    public String toString() {
        return "GestorSeguimiento{usuarios=" + Arrays.toString(Arrays.copyOf(usuarios, contadorUsuarios))
                + ", catalogo=" + catalogo + ", historialSueno="
                + Arrays.toString(Arrays.copyOf(historialSueno, contadorSuenos))
                + ", contadorUsuarios=" + contadorUsuarios + ", contadorSuenos=" + contadorSuenos
                + ", siguienteIdCronograma=" + siguienteIdCronograma
                + ", proximoIdUsuario=" + proximoIdUsuario + "}";
    }
}
