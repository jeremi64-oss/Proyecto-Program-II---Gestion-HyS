package ec.edu.uce.sghs.dominio;

import java.util.Date;

public class Comentario {

    private int idComentario;
    private Date fecha;
    private String texto;

    public Comentario (){
        this.idComentario = 1;
        this.fecha = new Date();
        this.texto = "Sin comentario";
    }

    public Comentario (int idComentario, Date fecha, String texto){
        this.idComentario = idComentario;
        this.fecha = fecha;
        this.texto = texto;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public String getTexto() {
        return texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public boolean setIdComentario(int idComentario) {
        boolean resp = false;
        if(idComentario < 0){
            resp = false;
        }else{
            this.idComentario = idComentario;
            return true;
        }
        return resp;
    }

    public boolean setFecha(Date fecha) {
        boolean resp = false;
        if (fecha == null) {
            resp = false;
        } else {
            this.fecha = fecha;
            resp = true;
        }
        return resp;
    }

    public boolean setTexto(String texto) {
        boolean resp = false;
        if (texto == null || texto.trim().isEmpty() || texto.length() > 250) {
            resp = false;
        }else{
            this.texto = texto;
            resp = true;
        }
        return resp;
    }

    //metodos de la clase
    public void escribir (){

    }
}
