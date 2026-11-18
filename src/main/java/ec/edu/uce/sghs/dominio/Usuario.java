package ec.edu.uce.sghs.dominio;

public class Usuario {

    private Long idUsuario;
    private String nombre;
    private String correo;
    private String passwordHash;
    private boolean activo;

    public Usuario() {
        this.idUsuario = 1L;
        this.nombre = "Usuario Base";
        this.correo = "usuario@uce.edu.ec";
        this.passwordHash = "hashSeguro123";
        this.activo = true;
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
        this.correo = correo;
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
        return this.activo && this.correo.equals(correo) && this.passwordHash.equals(clave);
    }

    public void actualizarPerfil(String nombre, String correo) {
        setNombre(nombre);
        setCorreo(correo);
    }

    private Rol rol;
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

    @Override
    public String toString() {
        return "Usuario: " + "idUsuario=" + idUsuario + ", nombre='" + nombre + ", correo='" + correo + ", activo=" + activo;
    }
}

