package ec.edu.uce.sghs.dominio;

import java.time.LocalTime;

public class HabitoAlimenticio extends Habito {

    private String tipoComida;
    private int porcionesObjetivo;
    private LocalTime horaComidaObjetivo;

    public HabitoAlimenticio() {
        super();
        this.tipoComida = "Almuerzo Saludable";
        this.porcionesObjetivo = 3;
        this.horaComidaObjetivo = LocalTime.of(12, 30);
    }

    public HabitoAlimenticio(Long idHabito, String nombre, PolaridadHabito polaridad, int rachaActual, int diasCumplidos, boolean activo,
                             String tipoComida, int porcionesObjetivo, LocalTime horaComidaObjetivo) {
        super(idHabito, nombre, polaridad, rachaActual, diasCumplidos, activo);
        setTipoComida(tipoComida);
        setPorcionesObjetivo(porcionesObjetivo);
        setHoraComidaObjetivo(horaComidaObjetivo);
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public boolean setTipoComida(String tipoComida) {
        if (tipoComida == null || tipoComida.trim().isEmpty()) {
            return false;
        }
        this.tipoComida = tipoComida;
        return true;
    }

    public int getPorcionesObjetivo() {
        return porcionesObjetivo;
    }

    public boolean setPorcionesObjetivo(int porcionesObjetivo) {
        if (porcionesObjetivo <= 0) {
            return false;
        }
        this.porcionesObjetivo = porcionesObjetivo;
        return true;
    }

    public LocalTime getHoraComidaObjetivo() {
        return horaComidaObjetivo;
    }

    public boolean setHoraComidaObjetivo(LocalTime horaComidaObjetivo) {
        if (horaComidaObjetivo == null) {
            return false;
        }
        this.horaComidaObjetivo = horaComidaObjetivo;
        return true;
    }

    // --- Métodos del diagrama ---

    public void configurarComida(String tipo, int porciones, LocalTime hora) {
        setTipoComida(tipo);
        setPorcionesObjetivo(porciones);
        setHoraComidaObjetivo(hora);
    }

    public boolean evaluarPorciones(int porcionesConsumidas) {
        return porcionesConsumidas >= this.porcionesObjetivo;
    }

    @Override
    public String toString() {
        return "HabitoAlimenticio{" +
                "idHabito=" + getIdHabito() +
                ", nombre='" + getNombre() + '\'' +
                ", tipoComida='" + tipoComida + '\'' +
                ", porcionesObjetivo=" + porcionesObjetivo +
                ", horaComidaObjetivo=" + horaComidaObjetivo +
                '}';
    }
}
