package ec.edu.uce.sghs.dominio;

import java.time.LocalDate;

public class CronogramaSemanal {
    private Long idCronograma;
    private LocalDate fechaInicioSemana;
    private double cumplimientoSemanal;
    private PlanItem[] items;
    private int contadorItems;

    public CronogramaSemanal() {
        this.idCronograma = 0L;
        this.fechaInicioSemana = LocalDate.now();
        this.cumplimientoSemanal = 0.0;
        this.items = new PlanItem[10]; // Ejemplo: 10 actividades
        this.contadorItems = 0;
    }

    public CronogramaSemanal(Long idCronograma, LocalDate fechaInicioSemana, double cumplimientoSemanal, int capacidadMaxima) {
        this.idCronograma = idCronograma;
        this.fechaInicioSemana = fechaInicioSemana;
        this.cumplimientoSemanal = cumplimientoSemanal;
        this.items = new PlanItem[capacidadMaxima];
        this.contadorItems = 0;
    }

    public boolean agregarPlanItem(PlanItem item) {

        if (contadorItems >= items.length) {
            System.out.println(" -- Error: El cronograma semanal ha alcanzado su capacidad máxima -- ");
            return false;
        }

        if (verificarSolapamiento(item)) {
            System.out.println(" -- Error: El bloque horario se cruza con otra actividad ya programada -- ");
            return false;
        }

        items[contadorItems] = item;
        contadorItems++;
        System.out.println(" -- Éxito: Actividad agregada correctamente al cronograma semanal -- ");
        return true;
    }

    public boolean verificarSolapamiento(PlanItem nuevoItem) {
        for (int i = 0; i < contadorItems; i++) {
            PlanItem existente = items[i];
            if (existente != null && existente.getDiaSemana().equals(nuevoItem.getDiaSemana())) {
                boolean seSolapa = nuevoItem.getHoraInicio().isBefore(existente.getHoraFin()) &&
                        nuevoItem.getHoraFin().isAfter(existente.getHoraInicio());
                if (seSolapa) {
                    return true;
                }
            }
        }
        return false;
    }

    public double calcularBalanceSemanal() {
        if (contadorItems == 0) {
            this.cumplimientoSemanal = 0.0;
            return 0.0;
        }

        int completados = 0;
        for (int i = 0; i < contadorItems; i++) {
            if ("Completado".equalsIgnoreCase(items[i].getEstado())) {
                completados++;
            }
        }

        this.cumplimientoSemanal = ((double) completados / contadorItems) * 100.0;
        return this.cumplimientoSemanal;
    }

    public Long getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Long idCronograma) {
        this.idCronograma = idCronograma;
    }

    public LocalDate getFechaInicioSemana() {
        return fechaInicioSemana;
    }

    public void setFechaInicioSemana(LocalDate fechaInicioSemana) {
        this.fechaInicioSemana = fechaInicioSemana;
    }

    public double getCumplimientoSemanal() {
        return cumplimientoSemanal;
    }

    public void setCumplimientoSemanal(double cumplimientoSemanal) {
        this.cumplimientoSemanal = cumplimientoSemanal;
    }

    public PlanItem[] getItems() {
        return items;
    }

    public int getContadorItems() {
        return contadorItems;
    }

}
