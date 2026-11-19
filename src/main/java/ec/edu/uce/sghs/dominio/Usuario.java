package ec.edu.uce.sghs.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {

    private Long idUsuario;
    private String nombre;
    private String correo;
    private String passwordHash;
    private boolean activo;
    private Rol rol;
    private CronogramaSemanal cronograma;

    public Usuario() {
        this(1L, "Usuario Base", "usuario@uce.edu.ec", "hashSeguro123", true);
    }

    public Usuario(Long idUsuario, String nombre, String correo, String passwordHash, boolean activo) {
        setIdUsuario(idUsuario);
        setNombre(nombre);
        setCorreo(correo);
        setPasswordHash(passwordHash);
        this.activo = activo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public boolean setIdUsuario(Long idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            return false;
        }
        this.idUsuario = idUsuario;
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        this.nombre = nombre;
        return true;
    }

    public String getCorreo() {
        return correo;
    }

    public boolean setCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        this.correo = correo.strip();
        return true;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean setPasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            return false;
        }
        this.passwordHash = passwordHash;
        return true;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean autenticar(String correo, String clave) {
        return this.activo && this.correo != null && correo != null
                && this.correo.equalsIgnoreCase(correo.strip()) && Objects.equals(this.passwordHash, clave);
    }

    public void actualizarPerfil(String nombre, String correo) {
        setNombre(nombre);
        setCorreo(correo);
    }

    public Rol getRol() {
        return rol;
    }
    public boolean setRol(Rol rol) {
        if (rol == null) {
            return false;
        }
        this.rol = rol;
        return true;
    }

    public CronogramaSemanal getCronograma() {
        return cronograma;
    }

    CronogramaSemanal iniciarCronograma(Long idCronograma, LocalDate inicio, int capacidad) {
        cronograma = new CronogramaSemanal(idCronograma, inicio, 0.0, capacidad);
        return cronograma;
    }

    @Override
    public String toString() {
        return "Usuario{idUsuario=" + idUsuario + ", nombre='" + nombre + "', correo='" + correo
                + "', passwordHash='[oculto]', activo=" + activo + ", rol=" + rol
                + ", idCronograma=" + (cronograma == null ? null : cronograma.getIdCronograma()) + "}";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Usuario) || getClass() != objeto.getClass()) {
            return false;
        }
        Usuario otro = (Usuario) objeto;
        return idUsuario != null && idUsuario > 0 && idUsuario.equals(otro.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), idUsuario);
    }
}
