package dominio;

import java.sql.Date;


public class Log {
    private int id_log;
    private Date fecha_accion;
    private String tipo_usuario;
    private String nombre;
    private String email;
    private String accion;
    private int id_formulario;
    private int id_usuario;
    
    
    public Log(){
        
    }

    public Log(int id_log) {
        this.id_log = id_log;
    }

    public Log(String tipo_usuario, String nombre, String email, String accion, int id_formulario, int id_usuario) {
        this.tipo_usuario = tipo_usuario;
        this.nombre = nombre;
        this.email = email;
        this.accion = accion;
        this.id_formulario = id_formulario;
        this.id_usuario = id_usuario;
    }

    public Log(int id_log, Date fecha_accion, String tipo_usuario, String nombre, String email, String accion, int id_formulario, int id_usuario) {
        this.id_log = id_log;
        this.fecha_accion = fecha_accion;
        this.tipo_usuario = tipo_usuario;
        this.nombre = nombre;
        this.email = email;
        this.accion = accion;
        this.id_formulario = id_formulario;
        this.id_usuario = id_usuario;
    }

    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    public Date getFecha_accion() {
        return fecha_accion;
    }

    public void setFecha_accion(Date fecha_accion) {
        this.fecha_accion = fecha_accion;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
    
    
    
    
}


