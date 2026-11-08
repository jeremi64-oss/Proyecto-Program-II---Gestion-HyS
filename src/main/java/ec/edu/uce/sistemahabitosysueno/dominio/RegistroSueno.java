package ec.edu.uce.sistemahabitosysueno.dominio;

import java.util.Date;

public class RegistroSueno {

    private int idRegistro;
    private Date fecha;
    private double horasDescanso;
    private int calidadPercibida;
    private String comentario;

    public void RegistroSueno(){
    }

    public void RegistroSueno(int idRegistro, Date fecha, double horasDescanso, int calidadPercibida, String comentario){
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.horasDescanso = horasDescanso;
        this.calidadPercibida = calidadPercibida;
        this.comentario = comentario;
    }

    public void registrar(){
    }
    public void consultarHistorial(){
    }


    public int getIdRegistro() {
        return idRegistro;
    }
    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }


    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public double getHorasDescanso() {
        return horasDescanso;
    }
    public void setHorasDescanso(double horasDescanso) {
        this.horasDescanso = horasDescanso;
    }


    public int getCalidadPercibida() {
        return calidadPercibida;
    }
    public void setCalidadPercibida(int calidadPercibida) {
        this.calidadPercibida = calidadPercibida;
    }


    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
