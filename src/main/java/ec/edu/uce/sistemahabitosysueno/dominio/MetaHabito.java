package ec.edu.uce.sistemahabitosysueno.dominio;

public class MetaHabito {

    private int idMeta;
    private int vecesPorSemana;

    public void MetaHabito(){
    }

    public void MetaHabito(int idMeta, int vecesPorSemana){
        this.idMeta = idMeta;
        this.vecesPorSemana = vecesPorSemana;
    }

    public void guardar(){
    }


    public int getIdMeta() {
        return idMeta;
    }
    public void setIdMeta(int idMeta) {
        this.idMeta = idMeta;
    }


    public int getVecesPorSemana() {
        return vecesPorSemana;
    }
    public void setVecesPorSemana(int vecesPorSemana) {
        this.vecesPorSemana = vecesPorSemana;
    }
}
