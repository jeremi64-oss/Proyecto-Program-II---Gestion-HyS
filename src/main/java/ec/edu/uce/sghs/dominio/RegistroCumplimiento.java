package ec.edu.uce.sghs.dominio;

import java.util.Date;

public class RegistroCumplimiento {

    private int idRegistro;
    private Date fecha;
    private boolean cumplido;

    public RegistroCumplimiento(){
        this.idRegistro = 1;
        this.fecha = new Date();
        this.cumplido = false;
    }

    public RegistroCumplimiento(int idRegistro, Date fecha, boolean cumplido){
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.cumplido = cumplido;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public boolean getCumplido() {
        return cumplido;
    }

    public boolean setIdRegistro(int idRegistro) {
        boolean resp = false;
        if(idRegistro < 0){
            resp = false;
        }else{
            this.idRegistro = idRegistro;
            return true;
        }
        return resp;
    }

    public boolean setFecha(Date fecha) {
        boolean resp = false;
        if (fecha == null) {
            resp = false;
        } else {
            this.fecha = fecha;
            resp = true;
        }
        return resp;
    }

    public void setCumplido(boolean cumplido) {
        this.cumplido = cumplido;
    }

    //metodos de la clase
    public void marcarCumplimiento(){

    }

    public void calcularConstancia(){

    }
}
