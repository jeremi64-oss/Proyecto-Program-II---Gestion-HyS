package ec.edu.uce.sghs.dominio;

public class HabitoEstudio extends Habito {

    private String asignatura;
    private int minutosPorSesion;
    private int minutosDescanso;

    public HabitoEstudio() {
        super();
        this.asignatura = "Matemáticas";
        this.minutosPorSesion = 45;
        this.minutosDescanso = 10;
    }

    public HabitoEstudio(Long idHabito, String nombre, PolaridadHabito polaridad, int rachaActual, int diasCumplidos, boolean activo, String asignatura, int minutosPorSesion, int minutosDescanso) {
        super(idHabito, nombre, polaridad, rachaActual, diasCumplidos, activo);
        setAsignatura(asignatura);
        setMinutosPorSesion(minutosPorSesion);
        setMinutosDescanso(minutosDescanso);
    }

    public String getAsignatura() {
        return asignatura;
    }

    public int getMinutosPorSesion() {
        return minutosPorSesion;
    }

    public int getMinutosDescanso() {
        return minutosDescanso;
    }

    public boolean setAsignatura(String asignatura) {
        if (asignatura == null || asignatura.trim().isEmpty()) {
            return false;
        }
        this.asignatura = asignatura;
        return true;
    }

    public boolean setMinutosPorSesion(int minutosPorSesion) {
        if (minutosPorSesion <= 0) {
            return false;
        }
        this.minutosPorSesion = minutosPorSesion;
        return true;
    }

    public boolean setMinutosDescanso(int minutosDescanso) {
        if (minutosDescanso < 0) {
            return false;
        }
        this.minutosDescanso = minutosDescanso;
        return true;
    }

    public void configurarSesion(String asignatura, int estudio, int descanso) {
        setAsignatura(asignatura);
        setMinutosPorSesion(estudio);
        setMinutosDescanso(descanso);
    }

    public int calcularTiempoTotal(int cantidadSesiones) {
        if (cantidadSesiones <= 0) {
            return 0;
        }
        return cantidadSesiones * (this.minutosPorSesion + this.minutosDescanso);
    }

    @Override
    public String toString() {
        return "HabitoEstudio: " + "idHabito=" + getIdHabito() + ", nombre='" + getNombre() + ", asignatura='" + asignatura + ", minutosPorSesion=" + minutosPorSesion + ", minutosDescanso=" + minutosDescanso ;
    }
}

