package ec.edu.uce.sghs.dominio;

public class MetaSueno {
    private int idMeta;
    private double horasObjetivo;

    public MetaSueno(){
        this.idMeta = 1;
        this.horasObjetivo = 8.00;
    }

    public MetaSueno(int idMeta, double horasObjetivo){
        this.idMeta = idMeta;
        this.horasObjetivo = horasObjetivo;
    }

    public int getIdMeta() {
        return idMeta;
    }

    public double getHorasObjetivo() {
        return horasObjetivo;
    }

    public boolean setIdMeta(int idMeta) {
        boolean resp = false;
        if(idMeta < 0){
            resp = false;
        }else{
            this.idMeta = idMeta;
            resp = true;
        }
        return resp;
    }

    public boolean setHorasObjetivo(double horasObjetivo) {
        boolean resp = false;
        if(horasObjetivo < 0 || horasObjetivo > 12 || horasObjetivo < 4){
            resp = false;
        }else{
            this.horasObjetivo = horasObjetivo;
            resp = true;
        }
        return resp;
    }

    //metodos de la clase
    public void guardar(){

    }
}
