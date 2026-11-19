package ec.edu.uce.sghs.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CatalogoHabitos {

    private Long idCatalogo;
    private String categoria;
    private String version;
    private Habito[] plantillas = new Habito[0];
    private boolean[] relacionadasConSueno = new boolean[0];

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
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
        this.categoria = categoria.strip();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        if (version == null || version.isBlank()) {
            throw new IllegalArgumentException("La versión no puede estar vacía.");
        }
        this.version = version.strip();
    }

    public List<Habito> getPlantillas() {
        return new ArrayList<>(Arrays.asList(plantillas));
    }

    public void agregarPlantilla(Habito habito) {
        agregarPlantilla(habito, false);
    }

    public void agregarPlantilla(Habito habito, boolean relacionadoConSueno) {
        if (habito == null || habito.getIdHabito() == null || habito.getIdHabito() <= 0) {
            throw new IllegalArgumentException("La plantilla debe tener un identificador positivo.");
        }
        if (indicePlantilla(habito.getIdHabito()) >= 0) {
            throw new IllegalArgumentException("Ya existe una plantilla con ese identificador.");
        }

        int posicion = plantillas.length;
        plantillas = Arrays.copyOf(plantillas, posicion + 1);
        relacionadasConSueno = Arrays.copyOf(relacionadasConSueno, posicion + 1);
        plantillas[posicion] = habito;
        relacionadasConSueno[posicion] = relacionadoConSueno;
    }

    public Habito obtenerPlantilla(Long idHabito) {
        return plantillas[indiceExistente(idHabito)];
    }

    public void editarPlantilla(Habito habito, boolean relacionadoConSueno) {
        if (habito == null) {
            throw new IllegalArgumentException("La plantilla es obligatoria.");
        }
        int posicion = indiceExistente(habito.getIdHabito());
        plantillas[posicion] = habito;
        relacionadasConSueno[posicion] = relacionadoConSueno;
    }

    public void eliminarPlantilla(Long idHabito) {
        int posicion = indiceExistente(idHabito);
        int cantidadPosterior = plantillas.length - posicion - 1;
        System.arraycopy(plantillas, posicion + 1, plantillas, posicion, cantidadPosterior);
        System.arraycopy(relacionadasConSueno, posicion + 1, relacionadasConSueno, posicion, cantidadPosterior);
        plantillas = Arrays.copyOf(plantillas, plantillas.length - 1);
        relacionadasConSueno = Arrays.copyOf(relacionadasConSueno, relacionadasConSueno.length - 1);
    }

    public List<Habito> listarPorPolaridad(String polaridad) {
        if (polaridad == null || polaridad.isBlank()) {
            throw new IllegalArgumentException("La polaridad no puede estar vacía.");
        }
        Habito.PolaridadHabito polaridadBuscada =
                Habito.PolaridadHabito.valueOf(polaridad.strip().toUpperCase(Locale.ROOT));

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
        for (int i = 0; i < plantillas.length; i++) {
            Habito habito = plantillas[i];
            if (relacionadasConSueno[i] && habito.isActivo()
                    && habito.getPolaridad() == Habito.PolaridadHabito.POSITIVO) {
                resultado.add(habito);
            }
        }
        return resultado;
    }

    private int indiceExistente(Long idHabito) {
        if (idHabito == null || idHabito <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo.");
        }
        int posicion = indicePlantilla(idHabito);
        if (posicion < 0) {
            throw new IllegalArgumentException("No existe una plantilla con ese identificador.");
        }
        return posicion;
    }

    private int indicePlantilla(Long idHabito) {
        for (int i = 0; i < plantillas.length; i++) {
            if (idHabito.equals(plantillas[i].getIdHabito())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "CatalogoHabitos{idCatalogo=" + idCatalogo + ", categoria='" + categoria + "'"
                + ", version='" + version + "', cantidadPlantillas=" + plantillas.length
                + ", plantillas=" + Arrays.toString(plantillas)
                + ", relacionadasConSueno=" + Arrays.toString(relacionadasConSueno) + "}";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof CatalogoHabitos) || getClass() != objeto.getClass()) {
            return false;
        }
        CatalogoHabitos otro = (CatalogoHabitos) objeto;
        return idCatalogo != null && idCatalogo > 0 && idCatalogo.equals(otro.idCatalogo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), idCatalogo);
    }
}
