package ec.edu.uce.sghs.dominio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RegistroSueno {

    private int idRegistro;
    private Date fecha;
    private Double horasDescanso;
    private Integer calidadPercibida;
    private String comentario;

    public RegistroSueno() {
        comentario = "";
    }

    public RegistroSueno(
            int idRegistro,
            Date fecha,
            Double horasDescanso,
            Integer calidadPercibida,
            String comentario
    ) {
        setIdRegistro(idRegistro);
        setFecha(fecha);
        setHorasDescanso(horasDescanso);
        setCalidadPercibida(calidadPercibida);
        setComentario(comentario);
    }

    public void registrar(List<RegistroSueno> registros) {
        validarLista(registros);

        RegistroSueno copia = new RegistroSueno(
                idRegistro,
                fecha,
                horasDescanso,
                calidadPercibida,
                comentario
        );

        for (RegistroSueno registro : registros) {
            if (convertirFecha(registro.getFecha())
                    .equals(convertirFecha(fecha))) {
                throw new IllegalArgumentException(
                        "Ya existe un registro en esa fecha."
                );
            }

            if (registro.getIdRegistro() == idRegistro) {
                throw new IllegalArgumentException(
                        "Ya existe un registro con ese identificador."
                );
            }
        }

        registros.add(copia);
    }

    public static List<RegistroSueno> consultarHistorial(
            List<RegistroSueno> registros
    ) {
        validarLista(registros);

        List<RegistroSueno> historial = new ArrayList<>();

        for (RegistroSueno registro : registros) {
            historial.add(new RegistroSueno(
                    registro.getIdRegistro(),
                    registro.getFecha(),
                    registro.getHorasDescanso(),
                    registro.getCalidadPercibida(),
                    registro.getComentario()
            ));
        }

        historial.sort(Comparator.comparing(RegistroSueno::getFecha));

        return historial;
    }

    private static void validarLista(List<RegistroSueno> registros) {
        if (registros == null) {
            throw new IllegalArgumentException(
                    "La lista de registros es obligatoria."
            );
        }

        for (RegistroSueno registro : registros) {
            if (registro == null
                    || registro.idRegistro <= 0
                    || registro.fecha == null
                    || registro.horasDescanso == null
                    || registro.calidadPercibida == null) {
                throw new IllegalArgumentException(
                        "La lista contiene registros nulos o incompletos."
                );
            }
        }
    }

    private static LocalDate convertirFecha(Date fecha) {
        return new Date(fecha.getTime())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        if (idRegistro <= 0) {
            throw new IllegalArgumentException(
                    "El identificador del registro debe ser positivo."
            );
        }

        this.idRegistro = idRegistro;
    }

    public Date getFecha() {
        return fecha == null ? null : new Date(fecha.getTime());
    }

    public void setFecha(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException(
                    "La fecha es obligatoria."
            );
        }

        if (convertirFecha(fecha).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La fecha no puede ser futura."
            );
        }

        this.fecha = new Date(fecha.getTime());
    }

    public Double getHorasDescanso() {
        return horasDescanso;
    }

    public void setHorasDescanso(Double horasDescanso) {
        if (horasDescanso == null
                || !Double.isFinite(horasDescanso)
                || horasDescanso < 0
                || horasDescanso > 24) {
            throw new IllegalArgumentException(
                    "Las horas son obligatorias y deben estar entre 0 y 24."
            );
        }

        this.horasDescanso = horasDescanso;
    }

    public Integer getCalidadPercibida() {
        return calidadPercibida;
    }

    public void setCalidadPercibida(Integer calidadPercibida) {
        if (calidadPercibida == null
                || calidadPercibida < 1
                || calidadPercibida > 5) {
            throw new IllegalArgumentException(
                    "La calidad es obligatoria y debe estar entre 1 y 5."
            );
        }

        this.calidadPercibida = calidadPercibida;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario == null ? "" : comentario.trim();
    }
}
