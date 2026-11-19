package ec.edu.uce.sghs.dominio;

import java.util.List;
import java.util.Objects;

public class Rol {

    private Long idRol;
    private TipoRol tipo;
    private String descripcion;

    public Rol() {
        this(null, null, null);
    }

    public Rol (Long idRol, TipoRol tipo, String descripcion){
        this.idRol = idRol;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }


    public Long getIdRol() {
        return idRol;
    }
    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public TipoRol getTipo() {
        return tipo;
    }
    public void setTipo(TipoRol tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // --Metodos del Diagrama--
    public List<String> obtenerPermisos(){
        return null;
    }

    @Override
    public String toString() {
        return "Rol{idRol=" + idRol + ", tipo=" + tipo + ", descripcion='" + descripcion + "'}";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Rol) || getClass() != objeto.getClass()) {
            return false;
        }
        Rol otro = (Rol) objeto;
        return idRol != null && idRol > 0 && idRol.equals(otro.idRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), idRol);
    }

    public enum TipoRol {
        ESTUDIANTE,
        ADMINISTRADOR
    }
}

