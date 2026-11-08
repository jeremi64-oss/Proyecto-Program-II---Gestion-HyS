package ec.edu.uce.sistemahabitosysueno.dominio;

import java.util.Date;

public class RegistroCumplimiento {

    private int idRegistro;
    private Date fecha;
    private boolean cumplido;

    public void RegistroCumplimiento(){
    }

    public void RegistroCumplimineto(int idRegistro, Date fecha, boolean cumplido){
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.cumplido = cumplido;
    }

    public void marcarCumplimiento(){
    }

    public void calcularConstancia(){
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


    public boolean isCumplido() {
        return cumplido;
    }
    public void setCumplido(boolean cumplido) {
        this.cumplido = cumplido;
    }
}
