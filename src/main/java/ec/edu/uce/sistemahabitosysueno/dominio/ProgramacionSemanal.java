package ec.edu.uce.sistemahabitosysueno.dominio;

public class ProgramacionSemanal {

    private int idProgramacion;
    private String diasSemana;

    public void ProgramacionSemanal(){
    }

    public void ProgramacionSemanal(int idProgramacion, String diasSemana){
        this.idProgramacion = idProgramacion;
        this.diasSemana = diasSemana;
    }

    public void asignarDias(){
    }


    public int getIdProgramacion() {
        return idProgramacion;
    }
    public void setIdProgramacion(int idProgramacion) {
        this.idProgramacion = idProgramacion;
    }


    public String getDiasSemana() {
        return diasSemana;
    }
    public void setDiasSemana(String diasSemana) {
        this.diasSemana = diasSemana;
    }
}
