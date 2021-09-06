/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author solor
 */
public class Usuario {

    private int id_usuario;
    private String rut;
    private String nombre_completo;
    private String telefono;
    private String email;
    private String pass;
    private String estado;
    private int carrera;
    private int id_tipo_usuario;

    public Usuario() {
    }
    
    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    //Constructor para el tipo de usuario Alumno
    public Usuario (String rut, String nombre_completo, int carrera, String telefono, String email, String pass) {
        this.rut = rut;
        this.nombre_completo = nombre_completo;
        this.carrera = carrera;
        this.telefono = telefono;
        this.email = email;
        this.pass = pass;
    }
    
    //Constructor para el tipo de usuario Alumno
    public Usuario (String rut, String nombre_completo, String telefono, String email, String pass) {
        this.rut = rut;
        this.nombre_completo = nombre_completo;        
        this.telefono = telefono;
        this.email = email;
        this.pass = pass;
    }
    //Constructor para el tipo de usuario Funcionario
    public Usuario(int id_usuario, String rut, String nombre, int carrera, String telefono, String email, String pass) {
        this.id_usuario = id_usuario;
        this.rut = rut;
        this.nombre_completo = nombre_completo;
        this.carrera = carrera;
        this.telefono = telefono;
        this.email = email;
        this.pass = pass;
    }
    //Constructor para el tipo de usuario Funcionario
    public Usuario(int id_usuario, String rut, String nombre, String telefono, String email, String pass) {
        this.id_usuario = id_usuario;
        this.rut = rut;
        this.nombre_completo = nombre_completo;        
        this.telefono = telefono;
        this.email = email;
        this.pass = pass;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }

    public int getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public void setId_tipo_usuario(int id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", Rut=" + rut + ", Nombre=" + nombre_completo +  ", Teléfono=" + telefono + ", Email=" + email + ", Pass=" + pass + '}';
    }
  
}


