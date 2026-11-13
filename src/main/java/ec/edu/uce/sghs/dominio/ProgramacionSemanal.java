package ec.edu.uce.sghs.dominio;

import java.util.List;
import java.util.Locale;

public class ProgramacionSemanal {

    private int idProgramacion;
    private String diaSemana;

    public ProgramacionSemanal() {
    }
    public ProgramacionSemanal(int idProgramacion, String diaSemana) {
        setIdProgramacion(idProgramacion);
        setDiaSemana(diaSemana);
    }

    public void asignarDias(List<ProgramacionSemanal> programaciones) {
        if (programaciones == null) {
            throw new IllegalArgumentException("La lista de programaciones es obligatoria.");
        }

        ProgramacionSemanal copia =
                new ProgramacionSemanal(idProgramacion, diaSemana);

        for (ProgramacionSemanal programacion : programaciones) {
            if (programacion == null
                    || programacion.getIdProgramacion() <= 0
                    || programacion.getDiaSemana() == null) {
                throw new IllegalArgumentException("La lista contiene programaciones nulas o incompletas.");
            }

            if (programacion.getIdProgramacion() == idProgramacion) {
                throw new IllegalArgumentException("Ya existe una programación con ese identificador.");
            }

            if (programacion.getDiaSemana().equals(diaSemana)) {
                throw new IllegalArgumentException("El hábito ya está programado para ese día.");
            }
        }

        programaciones.add(copia);
    }

    public int getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(int idProgramacion) {
        if (idProgramacion <= 0) {
            throw new IllegalArgumentException("El identificador de la programación debe ser positivo.");
        }

        this.idProgramacion = idProgramacion;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        if (diaSemana == null || diaSemana.isBlank()) {
            throw new IllegalArgumentException("El día de la semana es obligatorio.");
        }

        String nuevoDia = diaSemana.trim().toLowerCase(Locale.ROOT);

        nuevoDia = nuevoDia.replace("miércoles", "miercoles");
        nuevoDia = nuevoDia.replace("sábado", "sabado");

        if (!nuevoDia.equals("lunes")
                && !nuevoDia.equals("martes")
                && !nuevoDia.equals("miercoles")
                && !nuevoDia.equals("jueves")
                && !nuevoDia.equals("viernes")
                && !nuevoDia.equals("sabado")
                && !nuevoDia.equals("domingo")) {
            throw new IllegalArgumentException("Debe ingresar un día válido de lunes a domingo.");
        }

        this.diaSemana = nuevoDia;
    }
}
