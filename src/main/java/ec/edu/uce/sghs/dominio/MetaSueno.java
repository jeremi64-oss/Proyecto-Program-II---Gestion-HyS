package ec.edu.uce.sghs.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class MetaSueno extends MetaSmart {

    private LocalTime horaDormirObjetivo;
    private LocalTime horaDespertarObjetivo;
    private double horasSuenoOptimas;

    public MetaSueno(){
    }

    public MetaSueno(Long idMeta, String descripcionEspecifica, double valorMedible, String unidadMedida,
                     LocalDate fechaInicio, LocalDate fechaFin,
                     LocalTime horaDormirObjetivo, LocalTime horaDespertarObjetivo, double horasSuenoOptimas) {
        super(idMeta, descripcionEspecifica, valorMedible, unidadMedida, fechaInicio, fechaFin);
        this.horaDormirObjetivo = horaDormirObjetivo;
        this.horaDespertarObjetivo = horaDespertarObjetivo;
        this.horasSuenoOptimas = horasSuenoOptimas;
    }


    public LocalTime getHoraDormirObjetivo() {
        return horaDormirObjetivo;
    }
    public void setHoraDormirObjetivo(LocalTime horaDormirObjetivo) {
        this.horaDormirObjetivo = horaDormirObjetivo;
    }

    public LocalTime getHoraDespertarObjetivo() {
        return horaDespertarObjetivo;
    }
    public void setHoraDespertarObjetivo(LocalTime horaDespertarObjetivo) {
        this.horaDespertarObjetivo = horaDespertarObjetivo;
    }

    public double getHorasSuenoOptimas() {
        return horasSuenoOptimas;
    }
    public void setHorasSuenoOptimas(double horasSuenoOptimas) {
        this.horasSuenoOptimas = horasSuenoOptimas;
    }


    // --- Métodos

    public boolean evaluarCumplimiento(double horasDormidas) {
        return horasDormidas >= this.horasSuenoOptimas;
    }

    public int calcularDesviacionMinutos(LocalTime horaReal) {
        if (horaReal == null || this.horaDormirObjetivo == null) {
            return 0;
        }
        return (int) java.time.Duration.between(this.horaDormirObjetivo, horaReal).toMinutes();
    }
}

