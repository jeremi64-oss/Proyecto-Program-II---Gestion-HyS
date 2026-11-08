package ec.edu.uce.sistemahabitosysueno.dominio;

import java.util.Date;

public class Reporte {

    private int idReporte;
    private String tipo;
    private Date fechaInicio;
    private Date fechaFin;

    public void Reporte (){
    }

    public void Reporte (int idReporte, String tipo, Date fechaInicio, Date fechaFin){
        this.idReporte = idReporte;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public void reportarCumplimiento(){
    }
    public void reportarEvolucion(){
    }
    public void correlacionar(){
    }


    public int getIdReporte(){
        return idReporte;
    }
    public void setIdReporte(int idReporte){
        this.idReporte = idReporte;
    }


    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
