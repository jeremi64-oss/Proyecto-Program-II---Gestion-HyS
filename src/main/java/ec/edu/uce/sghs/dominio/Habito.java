package ec.edu.uce.sghs.dominio;

public class Habito {

    private int idHabito;
    private String nombre;
    private String descripcion;
    private String estado;

    public Habito(){
        idHabito = 0;
        nombre = "Sin nombre";
        descripcion = "Sin descripcion";
        estado = "nulo";
    }

    public Habito (int idHabito, String nombre, String descripcion, String estado){
        this.idHabito = idHabito;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }


    public int getIdHabito(){
        return idHabito;
    }
    public boolean setIdHabito(int idHabito){
        if (idHabito> 0){
            this.idHabito = idHabito;
            return true;
        }else{
            return false;
        }
    }

    public String getNombre(){
        return nombre;
    }
    public boolean setNombre(String nombre){
        if (nombre != null && nombre != " " && !nombre.matches(".*\\d.*")){
            this.nombre = nombre;
            return true;
        }else{
            return false;
        }
    }

    public String getDescripcion(){
        return descripcion;
    }
    public boolean setDescripcion(String descripcion){
        if (descripcion == null || descripcion == "" || descripcion == "   ") {
            return false;
        }
        this.descripcion = descripcion;
        return true;
    }


    public String getEstado(){
        return estado;
    }
    public boolean setEstado(String estado){
        if (estado == null || estado == "" || estado == "   ") {
            return false;
        }else {
            this.estado = estado;
            return true;
        }
    }
    //Metodos de la clase
    public void crear(){

    }
    public void editar(){

    }
    public void eliminar(){

    }
    @Override
    public String toString() {
        return "ID: " + idHabito + "\nNombre: " + nombre
                + "\nDescripción: " + descripcion + "\nEstado: " + estado;
    }
}
