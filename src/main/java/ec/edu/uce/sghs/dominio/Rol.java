package ec.edu.uce.sghs.dominio;

import java.util.List;

public class Rol {

    private Long idRol;
    private TipoRol tipo;
    private String descripcion;

    public Rol (){
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

    public enum TipoRol {
        ESTUDIANTE,
        ADMINISTRADOR
    }
}

