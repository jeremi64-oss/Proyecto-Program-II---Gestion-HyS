package ec.edu.uce.sistemahabitosysueno.dominio;

public class Rol {

    private int idRol;
    private String nombre;

    public void Rol (){
    }

    public Rol( int idRol, String nombre){
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public void asignar(){
    }


    public int getIdRol() {
        return idRol;
    }
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
