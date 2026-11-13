package ec.edu.uce.sghs.dominio;

import java.util.Locale;

public class Rol {

    private int idRol;
    private String nombre;

    public Rol() {
    }

    public Rol(int idRol, String nombre) {
        setIdRol(idRol);
        setNombre(nombre);
    }

    public void asignar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un usuario."
            );
        }

        if (idRol <= 0 || nombre == null) {
            throw new IllegalArgumentException(
                    "Debe completar los datos del rol."
            );
        }

        usuario.setRol(this);
    }

    public boolean esEstudiante() {
        return "estudiante".equals(nombre);
    }

    public boolean esAdministrador() {
        return "administrador".equals(nombre);
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        if (idRol <= 0) {
            throw new IllegalArgumentException(
                    "El identificador del rol debe ser positivo."
            );
        }

        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del rol es obligatorio."
            );
        }

        String nuevoNombre = nombre.trim().toLowerCase(Locale.ROOT);

        if (!nuevoNombre.equals("estudiante")
                && !nuevoNombre.equals("administrador")) {
            throw new IllegalArgumentException(
                    "El rol debe ser estudiante o administrador."
            );
        }

        this.nombre = nuevoNombre;
    }
}
