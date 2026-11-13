package ec.edu.uce.sghs.dominio;

import ec.edu.uce.sghs.util.Validador;

public class Usuario {

    private int idUsuario;
    private String correo;
    private String contrasena;
    private boolean estadoCuenta;
    private int intentosFallidos;
    private Rol rol;

    public Usuario(){
        this.idUsuario = 1;
        this.correo = "correogenerico@uce.edu.ec";
        this.contrasena = "sin contraseña";
        this.estadoCuenta = true;
        this.intentosFallidos = 0;
        this.rol = null;
    }

    public Usuario (int idUsuario, String correo, String contrasena, boolean estadoCuenta, int intentosFallidos, Rol rol){
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.estadoCuenta = estadoCuenta;
        this.intentosFallidos = intentosFallidos;
        this.rol = rol;
    }

    public int getIdUsuario(){
        return idUsuario;
    }

    public String getCorreo(){
        return correo;
    }

    public String getContrasena(){
        return contrasena;
    }

    public boolean getEstadoCuenta(){
        return estadoCuenta;
    }

    public int getIntentosFallidos(){
        return intentosFallidos;
    }

    public Rol getRol(){
        return rol;
    }

    public boolean setIdUsuario(int idUsuario){
        boolean resp = false;
        if (idUsuario <= 0) {
            resp = false;
        } else {
            this.idUsuario = idUsuario;
            resp = true;
        }
        return resp;
    }

    public boolean setCorreo(String correo){
        boolean resp = false;
        if (correo == null || correo.trim().isEmpty() || !Validador.validarCorreo(correo)) {
            resp = false;
        } else {
            this.correo = correo;
            resp = true;
        }
        return resp;
    }

    public boolean setContrasena(String contrasena){
        boolean resp = false;
        if (contrasena == null || contrasena.trim().isEmpty() || !Validador.validarContrasena(contrasena)) {
            resp = false;
        } else {
            this.contrasena = contrasena;
            resp = true;
        }
        return resp;
    }

    public void setEstadoCuenta(boolean estadoCuenta){
        this.estadoCuenta = estadoCuenta;
    }

    public boolean setIntentosFallidos(int intentosFallidos){
        boolean resp = false;
        if (intentosFallidos < 0 || intentosFallidos > 3) {
            resp = false;
        } else {
            this.intentosFallidos = intentosFallidos;
            resp = true;
        }
        return resp;
    }

    public boolean setRol(Rol rol){
        boolean resp = false;
        if (rol == null || rol.getNombre() == null || rol.getNombre().trim().isEmpty()) {
            resp = false;
        } else {
            this.rol = rol;
            resp = true;
        }
        return resp;
    }

    //metodos de la clase
    public void registrarse (){

    }

    public void iniciarSesion(){

    }

    public void cambiarEstado(){

    }

    public void listarCuentas(){

    }
}
