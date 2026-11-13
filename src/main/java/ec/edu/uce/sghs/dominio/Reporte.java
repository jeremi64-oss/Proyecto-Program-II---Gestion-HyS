package ec.edu.uce.sghs.dominio;

import java.util.Date;

public class Reporte {

    private int idReporte;
    private String tipo;
    private Date fechaInicio;
    private Date fechaFin;

    public Reporte (){
        idReporte = 0;
        tipo = "Predeterminado";
        fechaInicio = new Date();
        fechaFin = new Date();
    }

    public Reporte (int idReporte, String tipo, Date fechaInicio, Date fechaFin){
        this.idReporte = idReporte;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean reportarCumplimiento(){
        if (this.tipo == null || this.tipo == "" ) {
            return false;
        }
        return true;
    }
    public void reportarEvolucion(){
    }
    public void correlacionar(){
    }


    public int getIdReporte(){
        return idReporte;
    }
    public boolean setIdReporte(int idReporte){
        if (idReporte> 0){
            this.idReporte = idReporte;
            return true;
        }else{
            return false;
        }
    }


    public String getTipo() {
        return tipo;
    }
    public boolean setTipo(String tipo) {
        if (tipo != null && tipo != " " && !tipo.matches(".*\\d.*")){
            this.tipo = tipo;
            return true;
        }else{
            return false;
        }
    }


    public Date getFechaInicio() {
        return fechaInicio;
    }
    public boolean setFechaInicio(Date fechaInicio) {
        if (fechaInicio == null) {
            return false;
        }
        Date hoy = new Date();
        if (fechaInicio.after(hoy)) {
            return false;
        }
        this.fechaInicio = fechaInicio;
        return true;
    }


    public Date getFechaFin() {
        return fechaFin;
    }

    public boolean setFechaFin(Date fechaFin) {
        if (fechaFin != null && this.fechaInicio != null && !fechaFin.before(this.fechaInicio)) {
            this.fechaFin = fechaFin;
            return true;
        }
        return false;
    }

}
