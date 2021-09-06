package dominio;

import java.io.InputStream;
import java.sql.Date;

public class SolicitudA {
    
    private int id_formulario;
    private String nombre;
    private String carrera;
    private String nombreS;
    private String nombreBe;
    private Date fecha_solicitud;
    private Date fecha_update;
    private String nombreE;
    private InputStream archivo;
    private byte[] archivo2;
    
    public SolicitudA() {
    }

    public SolicitudA(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public SolicitudA(String nombre, String carrera, String nombreS, String nombreBe, Date fecha_solicitud, Date fecha_update, String nombreE, byte[] archivo) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.nombreS = nombreS;
        this.nombreBe = nombreBe;
        this.fecha_solicitud = fecha_solicitud;
        this.fecha_update = fecha_update;
        this.nombreE = nombreE;
        this.archivo2 = archivo;
    }

    public SolicitudA(int id_formulario, String nombre, String carrera, String nombreS, String nombreBe, Date fecha_solicitud, Date fecha_update, String nombreE, byte[] archivo) {
        this.id_formulario = id_formulario;
        this.nombre = nombre;
        this.carrera = carrera;
        this.nombreS = nombreS;
        this.nombreBe = nombreBe;
        this.fecha_solicitud = fecha_solicitud;
        this.fecha_update = fecha_update;
        this.nombreE = nombreE;
        this.archivo2 = archivo;
    }

    public int getId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(int id_formulario) {
        this.id_formulario = id_formulario;
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

    public String getNombreS() {
        return nombreS;
    }

    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }

    public String getNombreBe() {
        return nombreBe;
    }

    public void setNombreBe(String nombreBe) {
        this.nombreBe = nombreBe;
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

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }
    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }
    public byte[] getArchivo2() {
        return archivo2;
    }
    public void setArchivo2(byte[] archivopdf2) {
        this.archivo2 = archivopdf2;
    }
    @Override
    public String toString() {
        return "SolicitudA{" + "id_formulario=" + id_formulario + ", nombre=" + nombre + ", carrera=" + carrera + ", nombreS=" + nombreS + ", nombreBe=" + nombreBe + ", fecha_solicitud=" + fecha_solicitud + ", fecha_update=" + fecha_update + ", nombreE=" + nombreE + '}';
    }
    
    
}
