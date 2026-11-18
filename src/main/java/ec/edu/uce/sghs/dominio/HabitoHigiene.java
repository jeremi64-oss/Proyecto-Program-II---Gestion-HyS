package ec.edu.uce.sghs.dominio;

public class HabitoHigiene extends Habito {

    private String zonaCuidado;
    private int vecesAlDiaObjetivo;
    private int duracionMinutos;

    public HabitoHigiene() {
        super();
        this.zonaCuidado = "";
        this.vecesAlDiaObjetivo = 0;
        this.duracionMinutos = 0;
    }

    public HabitoHigiene(String zonaCuidado,
                         int vecesAlDiaObjetivo, int duracionMinutos) {
        super();
        actualizarRutina(zonaCuidado, vecesAlDiaObjetivo, duracionMinutos);
    }

    public HabitoHigiene(Long idHabito, String nombre, PolaridadHabito polaridad,
                         int rachaActual, int diasCumplidos, boolean activo,
                         String zonaCuidado, int vecesAlDiaObjetivo,
                         int duracionMinutos) {
        super(idHabito, nombre, polaridad, rachaActual, diasCumplidos, activo);
        this.zonaCuidado = zonaCuidado;
        this.vecesAlDiaObjetivo = vecesAlDiaObjetivo;
        this.duracionMinutos = duracionMinutos;
    }

    public String getZonaCuidado() {
        return zonaCuidado;
    }

    public void setZonaCuidado(String zonaCuidado) {
        validarZona(zonaCuidado);
        this.zonaCuidado = zonaCuidado.strip();
    }

    public int getVecesAlDiaObjetivo() {
        return vecesAlDiaObjetivo;
    }

    public void setVecesAlDiaObjetivo(int vecesAlDiaObjetivo) {
        validarPositivo(vecesAlDiaObjetivo, "La frecuencia");
        this.vecesAlDiaObjetivo = vecesAlDiaObjetivo;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        validarPositivo(duracionMinutos, "La duracion");
        this.duracionMinutos = duracionMinutos;
    }

    public void actualizarRutina(String zona, int veces, int minutos) {
        validarZona(zona);
        validarPositivo(veces, "La frecuencia");
        validarPositivo(minutos, "La duracion");

        this.zonaCuidado = zona.strip();
        this.vecesAlDiaObjetivo = veces;
        this.duracionMinutos = minutos;
    }

    public boolean evaluarFrecuencia(int vecesRealizadas) {
        if (vecesRealizadas < 0) {
            throw new IllegalArgumentException(
                    "Las veces realizadas no pueden ser negativas.");
        }

        if (vecesAlDiaObjetivo <= 0) {
            throw new IllegalStateException(
                    "Primero configura la frecuencia objetivo.");
        }

        return vecesRealizadas >= vecesAlDiaObjetivo;
    }

    private void validarZona(String zona) {
        if (zona == null || zona.isBlank()) {
            throw new IllegalArgumentException(
                    "La zona de cuidado no puede estar vacia.");
        }
    }

    private void validarPositivo(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(
                    campo + " debe ser mayor que cero.");
        }
    }

    @Override
    public String toString() {
        return "HabitoHigiene{" + super.toString()
                + ", zonaCuidado='" + zonaCuidado + '\''
                + ", vecesAlDiaObjetivo=" + vecesAlDiaObjetivo
                + ", duracionMinutos=" + duracionMinutos + "}";
    }
}
