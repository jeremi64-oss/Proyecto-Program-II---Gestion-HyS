package ec.edu.uce.sistemahabitosysueno.dominio;

public class Usuario {

    private int idUsuario;
    private String correo;
    private String contrasena;
    private boolean estadoCuenta;
    private int intentosFallidos;
    private Rol rol;

    public Usuario(){
    }

    public Usuario (int idUsuario, String correo, String contrasena, boolean estadoCuenta, int intentosFallidos, Rol rol){
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.estadoCuenta = estadoCuenta;
        this.intentosFallidos = intentosFallidos;
        this.rol = rol;
    }

    public void registrarse (){
    }
    public void iniciarSesion(){
    }
    public void cambiarEstado(){
    }
    public void listarCuentas(){
    }

    public int getIdUsuario(){
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }


    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }


    public String getContrasena(){
        return contrasena;
    }
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }


    public boolean getEstadoCuenta(){
        return estadoCuenta;
    }
    public void setEstadoCuenta(boolean estadoCuenta){
        this.estadoCuenta = estadoCuenta;
    }


    public int getIntentosFallidos(){
        return intentosFallidos;
    }
    public void setIntentosFallidos(int intentosFallidos){
        this.intentosFallidos = intentosFallidos;
    }

    public Rol getRol(){
        return rol;
    }
    public void setRol(Rol rol){
        this.rol = rol;
    }
}

