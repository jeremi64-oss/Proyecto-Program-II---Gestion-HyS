package ec.edu.uce.sistemahabitosysueno.dominio;

public class Habito {

    private int idHabito;
    private String nombre;
    private String descripcion;
    private String estado;

    public Habito(){
    }

    public void Habito (int idHabito, String nombre, String descripcion, String estado){
        this.idHabito = idHabito;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public void crear(){
    }
    public void editar(){
    }
    public void eliminar(){
    }

    public int getIdHabito(){
        return idHabito;
    }
    public void setIdHabito(int idHabito){
        this.idHabito = idHabito;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getEstado(){
        return estado;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
}
