package ec.edu.uce.sghs.dominio;

import java.util.Objects;

public class MetaHabito {

    private int frecuenciaSemanal;
    private int rachaMinimaObjetivo;

    public MetaHabito() {
        this(3, 5);
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

    public boolean evaluarCumplimiento(Habito habito) {
        return habito != null && evaluarCumplimiento(habito.getDiasCumplidos());
    }

    @Override
    public String toString() {
        return "MetaHabito{frecuenciaSemanal=" + frecuenciaSemanal
                + ", rachaMinimaObjetivo=" + rachaMinimaObjetivo + "}";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof MetaHabito) || getClass() != objeto.getClass()) {
            return false;
        }
        MetaHabito otra = (MetaHabito) objeto;
        return frecuenciaSemanal == otra.frecuenciaSemanal
                && rachaMinimaObjetivo == otra.rachaMinimaObjetivo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frecuenciaSemanal, rachaMinimaObjetivo);
    }
}
