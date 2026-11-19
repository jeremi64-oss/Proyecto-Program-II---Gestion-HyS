package ec.edu.uce.sghs.dominio;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class PlanItem {

    private Long idPlanItem;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
    private Habito habito;

    public PlanItem() {
        this.idPlanItem = 0L;
        this.diaSemana = "";
        this.horaInicio = null;
        this.horaFin = null;
        this.estado = "PENDIENTE";
    }

    public PlanItem(Long idPlanItem, String diaSemana, LocalTime horaInicio,
                    LocalTime horaFin, String estado) {
        this(idPlanItem, diaSemana, horaInicio, horaFin, estado, null);
    }

    public PlanItem(Long idPlanItem, String diaSemana, LocalTime horaInicio,
                    LocalTime horaFin, String estado, Habito habito) {
        setIdPlanItem(idPlanItem);
        reprogramar(diaSemana, horaInicio, horaFin);
        setEstado(estado);
        this.habito = habito;
    }

    public Habito getHabito() {
        return habito;
    }

    public void setHabito(Habito habito) {
        this.habito = habito;
    }

    public Long getIdPlanItem() {
        return idPlanItem;
    }

    public void setIdPlanItem(Long idPlanItem) {
        if (idPlanItem == null || idPlanItem <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo.");
        }
        this.idPlanItem = idPlanItem;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = validarDia(diaSemana);
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        validarHora(horaInicio);

        if (horaFin != null) {
            validarHorario(horaInicio, horaFin);
        }

        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        validarHora(horaFin);

        if (horaInicio != null) {
            validarHorario(horaInicio, horaFin);
        }

        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null
                || (!estado.strip().equalsIgnoreCase("PENDIENTE")
                && !estado.strip().equalsIgnoreCase("COMPLETADO"))) {
            throw new IllegalArgumentException(
                    "El estado debe ser PENDIENTE o COMPLETADO.");
        }
        this.estado = estado.strip().toUpperCase(Locale.ROOT);
    }

    public void marcarCompletado() {
        setEstado("COMPLETADO");
    }

    public void reprogramar(String dia, LocalTime inicio, LocalTime fin) {
        String diaValidado = validarDia(dia);
        validarHorario(inicio, fin);

        this.diaSemana = diaValidado;
        this.horaInicio = inicio;
        this.horaFin = fin;
    }

    public int obtenerDuracionMinutos() {
        if (horaInicio == null || horaFin == null) {
            throw new IllegalStateException("Primero configura el horario.");
        }
        return (int) Duration.between(horaInicio, horaFin).toMinutes();
    }

    private String validarDia(String dia) {
        if (dia == null || dia.isBlank()) {
            throw new IllegalArgumentException("El dia es obligatorio.");
        }

        String valor = dia.strip().toUpperCase(Locale.ROOT)
                .replace('Á', 'A').replace('É', 'E').replace('Í', 'I')
                .replace('Ó', 'O').replace('Ú', 'U');

        if (!List.of("LUNES", "MARTES", "MIERCOLES", "JUEVES",
                "VIERNES", "SABADO", "DOMINGO").contains(valor)) {
            throw new IllegalArgumentException(
                    "El dia de la semana no es valido.");
        }

        return valor;
    }

    private void validarHora(LocalTime hora) {
        if (hora == null || hora.getSecond() != 0 || hora.getNano() != 0) {
            throw new IllegalArgumentException(
                    "La hora es obligatoria y debe tener minutos completos.");
        }
    }

    private void validarHorario(LocalTime inicio, LocalTime fin) {
        validarHora(inicio);
        validarHora(fin);

        if (!fin.isAfter(inicio)) {
            throw new IllegalArgumentException(
                    "El fin debe ser posterior al inicio, dentro del mismo dia.");
        }
    }

    @Override
    public String toString() {
        return "PlanItem{idPlanItem=" + idPlanItem
                + ", diaSemana=" + diaSemana
                + ", horaInicio=" + horaInicio
                + ", horaFin=" + horaFin
                + ", estado=" + estado + ", habito=" + habito + "}";
    }
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof PlanItem) || getClass() != objeto.getClass()) {
            return false;
        }
        PlanItem otro = (PlanItem) objeto;
        return idPlanItem != null && idPlanItem > 0 && idPlanItem.equals(otro.idPlanItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), idPlanItem);
    }
}
