package ec.edu.uce.sghs.dominio;

public class MetaHabito {

    private int frecuenciaSemanal;
    private int rachaMinimaObjetivo;

    public MetaHabito() {
        this.frecuenciaSemanal = 3;
        this.rachaMinimaObjetivo = 5;
    }

    public MetaHabito(int frecuenciaSemanal, int rachaMinimaObjetivo) {
        setFrecuenciaSemanal(frecuenciaSemanal);
        setRachaMinimaObjetivo(rachaMinimaObjetivo);
    }

    public int getFrecuenciaSemanal() {
        return frecuenciaSemanal;
    }

    public int getRachaMinimaObjetivo() {
        return rachaMinimaObjetivo;
    }

    public boolean setFrecuenciaSemanal(int frecuenciaSemanal) {
        if (frecuenciaSemanal < 1 || frecuenciaSemanal > 7) {
            return false;
        }
        this.frecuenciaSemanal = frecuenciaSemanal;
        return true;
    }

    public boolean setRachaMinimaObjetivo(int rachaMinimaObjetivo) {
        if (rachaMinimaObjetivo < 0) {
            return false;
        }
        this.rachaMinimaObjetivo = rachaMinimaObjetivo;
        return true;
    }

    public boolean evaluarCumplimiento(double diasCumplidos) {
        return diasCumplidos >= this.frecuenciaSemanal;
    }

    public boolean verificarRachaAlcanzada(int rachaActual) {
        return rachaActual >= this.rachaMinimaObjetivo;
    }

    @Override
    public String toString() {
        return "MetaHabito{" +
                "frecuenciaSemanal=" + frecuenciaSemanal +", rachaMinimaObjetivo=" + rachaMinimaObjetivo ;
    }
}
