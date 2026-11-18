package ec.edu.uce.sghs.dominio;
import java.time.LocalDate;

public class Habito {

    private Long idHabito;
    private String nombre;
    private PolaridadHabito polaridad;
    private int rachaActual;
    private int diasCumplidos;
    private boolean activo;

    public Habito(){
    }

    public Habito(Long idHabito, String nombre, PolaridadHabito polaridad, int rachaActual, int diasCumplidos, boolean activo){

        this.idHabito = idHabito;
        this.nombre = nombre;
        this.polaridad= polaridad;
        this.rachaActual = rachaActual;
        this.diasCumplidos = diasCumplidos;
        this.activo = activo;
    }

    public Long getIdHabito() {
        return idHabito;
    }
    public void setIdHabito(Long idHabito) {
        this.idHabito = idHabito;
    }


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public PolaridadHabito getPolaridad() {
        return polaridad;
    }
    public void setPolaridad(PolaridadHabito polaridad) {
        this.polaridad = polaridad;
    }


    public int getRachaActual() {
        return rachaActual;
    }
    public void setRachaActual(int rachaActual) {
        this.rachaActual = rachaActual;
    }


    public int getDiasCumplidos() {
        return diasCumplidos;
    }
    public void setDiasCumplidos(int diasCumplidos) {
        this.diasCumplidos = diasCumplidos;
    }


    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }


// --- Métodos


    public boolean registrarCumplimiento(LocalDate fecha, boolean cumplido) {
        return cumplido;
    }

    public double calcularGanancia(int diasTotales) {
        if (diasTotales <= 0) {
            return 0.0;
        }
        return (double) this.diasCumplidos / diasTotales * 100.0;
    }

    public void editarHabito(String nombre, PolaridadHabito polaridad) {
        this.nombre = nombre;
        this.polaridad = polaridad;
    }

    public enum PolaridadHabito {
        POSITIVO,
        NEGATIVO
    }
}

