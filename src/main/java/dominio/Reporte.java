package dominio;

import java.sql.Date;

public class Reporte {

    private int id_formulario;
    private String rut_alummno;
    private String nombre;
    private String carrera;
    private String nombreBe;
    private String nombreP;
    private Date fecha_solicitud;
    private Date fecha_update;
    private String nombreF;
    private String nombreE;

    public Reporte() {
    }

    public Reporte(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public Reporte(String rut_alummno, String nombre, String carrera, String nombreBe, String nombreP, Date fecha_solicitud, Date fecha_update, String nombreF, String nombreE) {
        this.rut_alummno = rut_alummno;
        this.nombre = nombre;
        this.carrera = carrera;
        this.nombreBe = nombreBe;
        this.nombreP = nombreP;
        this.fecha_solicitud = fecha_solicitud;
        this.fecha_update = fecha_update;
        this.nombreF = nombreF;
        this.nombreE = nombreE;
    }

    public Reporte(int id_formulario, String rut_alummno, String nombre, String carrera, String nombreBe, String nombreP, Date fecha_solicitud, Date fecha_update, String nombreF, String nombreE) {
        this.id_formulario = id_formulario;
        this.rut_alummno = rut_alummno;
        this.nombre = nombre;
        this.carrera = carrera;
        this.nombreBe = nombreBe;
        this.nombreP = nombreP;
        this.fecha_solicitud = fecha_solicitud;
        this.fecha_update = fecha_update;
        this.nombreF = nombreF;
        this.nombreE = nombreE;
    }

    public Reporte(int id_formulario, String rut_alumno, String nombre, String carrera, String nombreBe, Date fecha_solicitud, Date fecha_update, String nombreF, String nombreE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public String getRut_alummno() {
        return rut_alummno;
    }

    public void setRut_alummno(String rut_alummno) {
        this.rut_alummno = rut_alummno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getNombreBe() {
        return nombreBe;
    }

    public void setNombreBe(String nombreBe) {
        this.nombreBe = nombreBe;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public Date getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(Date fecha_update) {
        this.fecha_update = fecha_update;
    }

    public String getNombreF() {
        return nombreF;
    }

    public void setNombreF(String nombreF) {
        this.nombreF = nombreF;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    @Override
    public String toString() {
        return "Reporte{" + "id_formulario=" + id_formulario + ", rut_alummno=" + rut_alummno + ", nombre=" + nombre + ", carrera=" + carrera + ", nombreBe=" + nombreBe + ", nombreP=" + nombreP + ", fecha_solicitud=" + fecha_solicitud + ", fecha_update=" + fecha_update + ", nombreF=" + nombreF + ", nombreE=" + nombreE + '}';
    }
    
    
}
