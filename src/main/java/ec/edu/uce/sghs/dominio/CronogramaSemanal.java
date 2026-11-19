package ec.edu.uce.sghs.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public class CronogramaSemanal {
    private Long idCronograma;
    private LocalDate fechaInicioSemana;
    private double cumplimientoSemanal;
    private final PlanItem[] items;
    private int contadorItems;

    public CronogramaSemanal() {
        this(0L, LocalDate.now(), 0.0, 10);
    }

    public CronogramaSemanal(Long idCronograma, LocalDate fechaInicioSemana,
                             double cumplimientoSemanal, int capacidadMaxima) {
        this.idCronograma = idCronograma;
        this.fechaInicioSemana = fechaInicioSemana;
        this.cumplimientoSemanal = cumplimientoSemanal;
        this.items = new PlanItem[capacidadMaxima];
    }

    public boolean agregarPlanItem(PlanItem item) {
        if (!tieneHorario(item) || contadorItems == items.length || verificarSolapamiento(item)) {
            return false;
        }

        Long id = item.getIdPlanItem();
        if (id == 0L) {
            id = siguienteIdPlanItem();
        } else if (indiceItem(id) >= 0) {
            return false;
        }

        items[contadorItems] = copiarItem(item, id);
        contadorItems++;
        calcularBalanceSemanal();
        return true;
    }

    public boolean agregarPlanItem(String dia, LocalTime inicio, LocalTime fin, Habito habito) {
        return agregarPlanItem(new PlanItem(siguienteIdPlanItem(), dia, inicio, fin, "PENDIENTE", habito));
    }

    public PlanItem consultarPlanItem(Long idPlanItem) {
        int posicion = indiceItem(idPlanItem);
        if (posicion < 0) {
            return null;
        }
        return copiarItem(items[posicion], idPlanItem);
    }

    public boolean editarPlanItem(PlanItem datos) {
        if (!tieneHorario(datos)) {
            return false;
        }
        int posicion = indiceItem(datos.getIdPlanItem());
        if (posicion < 0 || verificarSolapamiento(datos, posicion)) {
            return false;
        }

        items[posicion] = copiarItem(datos, datos.getIdPlanItem());
        calcularBalanceSemanal();
        return true;
    }

    public boolean eliminarPlanItem(Long idPlanItem) {
        int posicion = indiceItem(idPlanItem);
        if (posicion < 0) {
            return false;
        }

        System.arraycopy(items, posicion + 1, items, posicion, contadorItems - posicion - 1);
        contadorItems--;
        items[contadorItems] = null;
        calcularBalanceSemanal();
        return true;
    }

    public boolean verificarSolapamiento(PlanItem nuevoItem) {
        return tieneHorario(nuevoItem) && verificarSolapamiento(nuevoItem, -1);
    }

    private boolean verificarSolapamiento(PlanItem nuevoItem, int posicionEditada) {
        for (int i = 0; i < contadorItems; i++) {
            PlanItem existente = items[i];
            if (i != posicionEditada && existente.getDiaSemana().equals(nuevoItem.getDiaSemana())
                    && nuevoItem.getHoraInicio().isBefore(existente.getHoraFin())
                    && nuevoItem.getHoraFin().isAfter(existente.getHoraInicio())) {
                return true;
            }
        }
        return false;
    }

    private boolean tieneHorario(PlanItem item) {
        return item != null && item.getIdPlanItem() != null && !item.getDiaSemana().isBlank()
                && item.getHoraInicio() != null && item.getHoraFin() != null;
    }

    private int indiceItem(Long idPlanItem) {
        if (idPlanItem == null || idPlanItem <= 0) {
            return -1;
        }
        for (int i = 0; i < contadorItems; i++) {
            if (idPlanItem.equals(items[i].getIdPlanItem())) {
                return i;
            }
        }
        return -1;
    }

    private long siguienteIdPlanItem() {
        long id = 1;
        while (indiceItem(id) >= 0) {
            id++;
        }
        return id;
    }

    private PlanItem copiarItem(PlanItem item, Long idPlanItem) {
        return new PlanItem(idPlanItem, item.getDiaSemana(), item.getHoraInicio(),
                item.getHoraFin(), item.getEstado(), item.getHabito());
    }

    public double calcularBalanceSemanal() {
        int completados = 0;
        for (int i = 0; i < contadorItems; i++) {
            if ("COMPLETADO".equals(items[i].getEstado())) {
                completados++;
            }
        }
        cumplimientoSemanal = contadorItems == 0 ? 0.0 : completados * 100.0 / contadorItems;
        return cumplimientoSemanal;
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
        PlanItem[] copia = new PlanItem[items.length];
        for (int i = 0; i < contadorItems; i++) {
            copia[i] = copiarItem(items[i], items[i].getIdPlanItem());
        }
        return copia;
    }

    public int getContadorItems() {
        return contadorItems;
    }

    @Override
    public String toString() {
        return "CronogramaSemanal{idCronograma=" + idCronograma + ", fechaInicioSemana=" + fechaInicioSemana
                + ", cumplimientoSemanal=" + cumplimientoSemanal + ", contadorItems=" + contadorItems
                + ", items=" + Arrays.toString(Arrays.copyOf(items, contadorItems)) + "}";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof CronogramaSemanal) || getClass() != objeto.getClass()) {
            return false;
        }
        CronogramaSemanal otro = (CronogramaSemanal) objeto;
        return idCronograma != null && idCronograma > 0 && idCronograma.equals(otro.idCronograma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), idCronograma);
    }
}
