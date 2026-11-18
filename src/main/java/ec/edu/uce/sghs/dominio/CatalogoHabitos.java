package ec.edu.uce.sghs.dominio;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatalogoHabitos {

    private Long idCatalogo;
    private String categoria;
    private String version;

    private final List<Habito> plantillas = new ArrayList<>();
    private final Set<Habito> habitosSueno = new HashSet<>();

    public CatalogoHabitos() {
        this.idCatalogo = 0L;
        this.categoria = "";
        this.version = "";
    }

    public CatalogoHabitos(Long idCatalogo, String categoria, String version) {
        setIdCatalogo(idCatalogo);
        setCategoria(categoria);
        setVersion(version);
    }

    public Long getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Long idCatalogo) {
        if (idCatalogo == null || idCatalogo <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo.");
        }
        this.idCatalogo = idCatalogo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException(
                    "La categoria no puede estar vacia.");
        }
        this.categoria = categoria.strip();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        if (version == null || version.isBlank()) {
            throw new IllegalArgumentException(
                    "La version no puede estar vacia.");
        }
        this.version = version.strip();
    }

    public List<Habito> getPlantillas() {
        return new ArrayList<>(plantillas);
    }

    public void agregarPlantilla(Habito habito, boolean relacionadoConSueno) {
        if (habito == null || habito.getIdHabito() == null
                || habito.getIdHabito() <= 0) {
            throw new IllegalArgumentException("El habito es obligatorio.");
        }

        for (Habito existente : plantillas) {
            if (existente.getIdHabito().equals(habito.getIdHabito())) {
                throw new IllegalArgumentException(
                        "Ya existe una plantilla con ese identificador.");
            }
        }

        plantillas.add(habito);

        if (relacionadoConSueno) {
            habitosSueno.add(habito);
        }
    }

    public List<Habito> listarPorPolaridad(String polaridad) {
        if (polaridad == null || polaridad.isBlank()) {
            throw new IllegalArgumentException("La polaridad no puede estar vacía.");
        }
        Habito.PolaridadHabito polaridadBuscada = Habito.PolaridadHabito.valueOf(polaridad.strip().toUpperCase());

        List<Habito> resultado = new ArrayList<>();

        for (Habito habito : plantillas) {
            if (habito.getPolaridad() == polaridadBuscada) {
                resultado.add(habito);
            }
        }

        return resultado;
    }

    public List<Habito> sugerirHabitosSueno() {
        List<Habito> resultado = new ArrayList<>();

        for (Habito habito : plantillas) {
            if (habitosSueno.contains(habito)
                    && habito.isActivo()
                    && habito.getPolaridad().equals("POSITIVO")) {
                resultado.add(habito);
            }
        }

        return resultado;
    }

    public Habito obtenerPlantilla(Long idHabito) {
        if (idHabito == null || idHabito <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo.");
        }

        for (Habito habito : plantillas) {
            if (habito.getIdHabito().equals(idHabito)) {
                return habito;
            }
        }

        throw new IllegalArgumentException(
                "No existe una plantilla con ese identificador.");
    }

    @Override
    public String toString() {
        return "CatalogoHabitos{idCatalogo=" + idCatalogo
                + ", categoria='" + categoria + '\''
                + ", version='" + version + '\''
                + ", cantidadPlantillas=" + plantillas.size() + "}";
    }
}
