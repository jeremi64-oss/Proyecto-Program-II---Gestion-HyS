package ec.edu.uce.sghs.dominio;

import java.time.LocalDate;

public class MetaSmart {

    private Long idMeta;
    private String descripcionEspecifica;
    private double valorMedible;
    private String unidadMedida;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public MetaSmart (){
    }

    public MetaSmart (Long idMeta, String descripcionEspecifica, double valorMedible, String unidadMedida, LocalDate fechaInicio, LocalDate fechaFin){
        this.idMeta = idMeta;
        this.descripcionEspecifica = descripcionEspecifica;
        this.valorMedible = valorMedible;
        this.unidadMedida = unidadMedida;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;

    }

    public Long getIdMeta() {
        return idMeta;
    }
    public void setIdMeta(Long idMeta) {
        this.idMeta = idMeta;
    }

    public String getDescripcionEspecifica() {
        return descripcionEspecifica;
    }
    public void setDescripcionEspecifica(String descripcionEspecifica) {
        this.descripcionEspecifica = descripcionEspecifica;
    }

    public double getValorMedible() {
        return valorMedible;
    }
    public void setValorMedible(double valorMedible) {
        this.valorMedible = valorMedible;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    // --- Métodos

    public boolean validaCriteriosSMART() {
        return this.descripcionEspecifica != null && this.valorMedible > 0;
    }

    public boolean evaluarCumplimiento(double valorActual) {
        return valorActual >= this.valorMedible;
    }
}
