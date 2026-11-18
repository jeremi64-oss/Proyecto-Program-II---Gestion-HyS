package ec.edu.uce.sghs.dominio;
import java.time.LocalDate;
import java.time.LocalTime;

public class GestorSeguimiento {
    private Usuario[] usuarios;
    private CatalogoHabitos catalogo;
    private String[] historialSueno;
    private int contadorUsuarios;
    private int contadorSuenos;

    public GestorSeguimiento() {
        this.usuarios = new Usuario[10]; // Capacidad fija
        this.historialSueno = new String[50]; // Capacidad fija de registros de sueño
        this.contadorUsuarios = 0;
        this.contadorSuenos = 0;
    }
    public GestorSeguimiento(int capacidadUsuarios, CatalogoHabitos catalogo) {
        this.usuarios = new Usuario[capacidadUsuarios];
        this.historialSueno = new String[50];
        this.catalogo = catalogo;
        this.contadorUsuarios = 0;
        this.contadorSuenos = 0;
    }
    public boolean agregarUsuario(Usuario usuario) {
        if (contadorUsuarios < usuarios.length) {
            usuarios[contadorUsuarios] = usuario;
            contadorUsuarios++;
            return true;
        }
        System.out.println(" Error: Capacidad máxima de usuarios alcanzada.");
        return false;
    }

    public boolean gestionarCuenta(Usuario admin, Usuario objetivo, boolean activo) {
        if (admin != null && admin.isActivo()) {
            objetivo.setActivo(activo);
            System.out.println(" Cuenta del usuario " + objetivo.getNombre() + " actualizada. Estado activo: " + activo);
            return true;
        }
        System.out.println(" -- Error: El administrador no es válido o no está activo --");
        return false;
    }

    public boolean asignarRolAdministrador(Usuario admin, Usuario objetivo) {
        System.out.println(" -- Rol de administrador asignado exitosamente a -- : " + objetivo.getNombre());
        return true;
    }

    public boolean registrarSueno(Usuario usuario, LocalDate fecha, LocalTime dormir, LocalTime despertar) {
        if (contadorSuenos < historialSueno.length) {
            String detalleSueño = "ID Usuario: " + usuario.getIdUsuario() + " | Fecha: " + fecha + " | Dormir: " + dormir + " | Despertar: " + despertar;
            historialSueno[contadorSuenos] = detalleSueño;
            contadorSuenos++;
            System.out.println(" -- Sueño registrado correctamente para el usuario: -- " + usuario.getNombre());
            return true;
        }
        System.out.println(" Error: Historial de sueño lleno.");
        return false;
    }

    public CronogramaSemanal generarPlanSeguimiento(Usuario usuario, Habito[] habitos) {
        if (usuario == null) {
            System.out.println(" -- Error: El usuario no puede ser nulo --");
            return null;
        }
        System.out.println(" -- Generando plan de seguimiento y cronograma para: " + usuario.getNombre() + " --");
        return new CronogramaSemanal(1L, LocalDate.now(), 0.0, 10);
    }

    public String generarReporteProgreso(Usuario usuario, LocalDate inicio, LocalDate fin) {
        return " -- Reporte de Progreso [Usuario: -- " + usuario.getNombre() + "] - Periodo: " + inicio + " al " + fin;
    }
    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public CatalogoHabitos getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(CatalogoHabitos catalogo) {
        this.catalogo = catalogo;
    }

    public String[] getHistorialSueno() {
        return historialSueno;
    }

    public void setHistorialSueno(String[] historialSueno) {
        this.historialSueno = historialSueno;
    }
}
