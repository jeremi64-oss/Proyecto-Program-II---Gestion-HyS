package ec.edu.uce.sghs.dominio;

import java.util.List;

public class MetaHabito {

    private int idMeta;
    private int idHabito;
    private int vecesPorSemana;
    private int diasProgramados;
    private int diasCumplidos;

    public MetaHabito() {
    }

    public MetaHabito(int idMeta, Integer vecesPorSemana) {
        setIdMeta(idMeta);
        setVecesPorSemana(vecesPorSemana);
    }

    public MetaHabito(
            int idMeta,
            Integer idHabito,
            Integer vecesPorSemana,
            Integer diasProgramados
    ) {
        setIdMeta(idMeta);
        setIdHabito(idHabito);
        setDiasProgramados(diasProgramados);
        setVecesPorSemana(vecesPorSemana);
    }

    public void guardar(List<MetaHabito> metas) {
        if (metas == null) {
            throw new IllegalArgumentException(
                    "La lista de metas es obligatoria."
            );
        }

        MetaHabito copia = new MetaHabito(
                idMeta,
                idHabito,
                vecesPorSemana,
                diasProgramados
        );

        copia.setDiasCumplidos(diasCumplidos);

        for (MetaHabito meta : metas) {
            if (meta == null) {
                throw new IllegalArgumentException(
                        "La lista no puede contener metas nulas."
                );
            }

            if (meta.getIdMeta() == idMeta) {
                throw new IllegalArgumentException(
                        "Ya existe una meta con ese identificador."
                );
            }

            if (meta.getIdHabito() == idHabito) {
                throw new IllegalArgumentException(
                        "El hábito ya tiene una meta."
                );
            }
        }

        metas.add(copia);
    }

    public void actualizarCumplimiento(int diasCumplidos) {
        setDiasCumplidos(diasCumplidos);
    }

    public boolean isCumplida() {
        return vecesPorSemana > 0
                && diasProgramados > 0
                && diasCumplidos >= vecesPorSemana;
    }

    public String getEstado() {
        return isCumplida() ? "cumplida" : "no cumplida";
    }

    public int getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(int idMeta) {
        if (idMeta <= 0) {
            throw new IllegalArgumentException(
                    "El identificador de la meta debe ser positivo."
            );
        }

        this.idMeta = idMeta;
    }

    public int getIdHabito() {
        return idHabito;
    }

    public void setIdHabito(Integer idHabito) {
        if (idHabito == null || idHabito <= 0) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un hábito válido."
            );
        }

        this.idHabito = idHabito;
    }

    public int getVecesPorSemana() {
        return vecesPorSemana;
    }

    public void setVecesPorSemana(Integer vecesPorSemana) {
        if (vecesPorSemana == null
                || vecesPorSemana < 1
                || vecesPorSemana > 7) {
            throw new IllegalArgumentException(
                    "Las veces por semana deben estar entre 1 y 7."
            );
        }

        if (diasProgramados > 0 && vecesPorSemana > diasProgramados) {
            throw new IllegalArgumentException(
                    "La meta no puede superar los días programados."
            );
        }

        this.vecesPorSemana = vecesPorSemana;
    }

    public int getDiasProgramados() {
        return diasProgramados;
    }

    public void setDiasProgramados(Integer diasProgramados) {
        if (diasProgramados == null
                || diasProgramados < 1
                || diasProgramados > 7) {
            throw new IllegalArgumentException(
                    "Los días programados deben estar entre 1 y 7."
            );
        }

        if (vecesPorSemana > diasProgramados
                || diasCumplidos > diasProgramados) {
            throw new IllegalArgumentException(
                    "Los días programados no pueden ser menores "
                            + "que la meta o los días cumplidos."
            );
        }

        this.diasProgramados = diasProgramados;
    }

    public int getDiasCumplidos() {
        return diasCumplidos;
    }

    public void setDiasCumplidos(int diasCumplidos) {
        if (diasProgramados == 0) {
            throw new IllegalArgumentException(
                    "Primero debe establecer los días programados."
            );
        }

        if (diasCumplidos < 0 || diasCumplidos > diasProgramados) {
            throw new IllegalArgumentException(
                    "Los días cumplidos no son válidos."
            );
        }

        this.diasCumplidos = diasCumplidos;
    }
}
