package ec.edu.uce.sistemahabitosysueno.dominio;

import java.util.Date;

public class Comentario {

    private int idComentario;
    private Date fecha;
    private String texto;

    public void Comentario (){
    }

    public void Comentario (int idComentario, Date fecha, String texto){
        this.idComentario = idComentario;
        this.fecha = fecha;
        this.texto = texto;
    }

    public void escribir (){
    }


    public int getIdComentario() {
        return idComentario;
    }
    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }


    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
}
