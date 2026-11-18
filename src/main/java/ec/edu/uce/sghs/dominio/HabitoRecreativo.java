package ec.edu.uce.sghs.dominio;
public class HabitoRecreativo extends Habito {

    private String tipoActividad;
    private int duracionObjetivoMinutos;
    private boolean requiereCompania;

    public HabitoRecreativo() {
        super();
        this.tipoActividad = "Lectura";
        this.duracionObjetivoMinutos = 30;
        this.requiereCompania = false;
    }

    public HabitoRecreativo(Long idHabito, String nombre, PolaridadHabito polaridad, int rachaActual, int diasCumplidos, boolean activo, String tipoActividad, int duracionObjetivoMinutos, boolean requiereCompania) {
        super(idHabito, nombre, polaridad, rachaActual, diasCumplidos, activo);
        setTipoActividad(tipoActividad);
        setDuracionObjetivoMinutos(duracionObjetivoMinutos);
        this.requiereCompania = requiereCompania;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public int getDuracionObjetivoMinutos() {
        return duracionObjetivoMinutos;
    }

    public boolean isRequiereCompania() {
        return requiereCompania;
    }

    public void setRequiereCompania(boolean requiereCompania) {
        this.requiereCompania = requiereCompania;
    }

    public boolean setTipoActividad(String tipoActividad) {
        if (tipoActividad == null || tipoActividad.trim().isEmpty()) {
            return false;
        }
        this.tipoActividad = tipoActividad;
        return true;
    }

    public boolean setDuracionObjetivoMinutos(int duracionObjetivoMinutos) {
        if (duracionObjetivoMinutos <= 0) {
            return false;
        }
        this.duracionObjetivoMinutos = duracionObjetivoMinutos;
        return true;
    }


    public void configurarActividad(String tipo, int minutos, boolean enCompania) {
        setTipoActividad(tipo);
        setDuracionObjetivoMinutos(minutos);
        setRequiereCompania(enCompania);
    }

    public boolean evaluarDuracion(int minutosRealizados) {
        return minutosRealizados >= this.duracionObjetivoMinutos;
    }

    @Override
    public String toString() {
        return "HabitoRecreativo: " + "idHabito=" + getIdHabito() + ", nombre='" + getNombre() +", tipoActividad='" + tipoActividad + ", duracionObjetivoMinutos=" + duracionObjetivoMinutos + ", requiereCompania=" + requiereCompania ;
    }
}
