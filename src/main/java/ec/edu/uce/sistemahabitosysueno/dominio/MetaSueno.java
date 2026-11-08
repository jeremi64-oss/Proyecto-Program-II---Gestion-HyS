package ec.edu.uce.sistemahabitosysueno.dominio;

public class MetaSueno {
    private int idMeta;
    private double horasObjetivo;

    public void MetaSueno(){
    }

    public void MetaSueno(int idMeta, double horasObjetivo){
        this.idMeta = idMeta;
        this.horasObjetivo = horasObjetivo;
    }

    public void guardar(){
    }


    public int getIdMeta() {
        return idMeta;
    }
    public void setIdMeta(int idMeta) {
        this.idMeta = idMeta;
    }


    public double getHorasObjetivo() {
        return horasObjetivo;
    }
    public void setHorasObjetivo(double horasObjetivo) {
        this.horasObjetivo = horasObjetivo;
    }
}
