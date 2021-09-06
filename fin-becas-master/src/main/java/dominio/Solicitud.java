package dominio;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Solicitud {
    
    private int id_formulario;
    private Date fecha_solicitud;
    private int anio_ingreso;
    private String semestre;
    private int anio_egreso;
    private String r2_hermano;
    private String r3_hermano;
    private InputStream archivo;
    private byte[] archivo2;
    private String nombre_archivo;
    private int alumno_id_alumno;
    private int estado_id_estado;
    private int beneficio_id_beneficio;
    private int tipo_solicitud_id_tipoS;
    private Timestamp fecha_update;

    public Solicitud() {
    }

    public Solicitud(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public Solicitud(Date fecha_solicitud, int anio_ingreso, String semestre, int anio_egreso, String r2_hermano, String r3_hermano, byte[] archivo,String nombre_archivo, int alumno_id_alumno, int estado_id_estado, int beneficio_id_beneficio, int tipo_solicitud_id_tipoS, Timestamp fecha_update) {
        this.fecha_solicitud = fecha_solicitud;
        this.anio_ingreso = anio_ingreso;
        this.semestre = semestre;
        this.anio_egreso = anio_egreso;
        this.r2_hermano = r2_hermano;
        this.r3_hermano = r3_hermano;
        this.archivo2 = archivo;
        this.nombre_archivo = nombre_archivo;
        this.alumno_id_alumno = alumno_id_alumno;
        this.estado_id_estado = estado_id_estado;
        this.beneficio_id_beneficio = beneficio_id_beneficio;
        this.tipo_solicitud_id_tipoS = tipo_solicitud_id_tipoS;
        this.fecha_update = fecha_update;
    }

    public Solicitud(int id_formulario, Date fecha_solicitud, int anio_ingreso, String semestre, int anio_egreso, String r2_hermano, String r3_hermano, byte[] archivo,String nombre_archivo, int alumno_id_alumno, int estado_id_estado, int beneficio_id_beneficio, int tipo_solicitud_id_tipoS, Timestamp fecha_update) {
        this.id_formulario = id_formulario;
        this.fecha_solicitud = fecha_solicitud;
        this.anio_ingreso = anio_ingreso;
        this.semestre = semestre;
        this.anio_egreso = anio_egreso;
        this.r2_hermano = r2_hermano;
        this.r3_hermano = r3_hermano;
        this.archivo2 = archivo;
        this.alumno_id_alumno = alumno_id_alumno;
        this.estado_id_estado = estado_id_estado;
        this.beneficio_id_beneficio = beneficio_id_beneficio;
        this.tipo_solicitud_id_tipoS = tipo_solicitud_id_tipoS;
        this.fecha_update = fecha_update;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public int getId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public int getAnio_ingreso() {
        return anio_ingreso;
    }

    public void setAnio_ingreso(int anio_ingreso) {
        this.anio_ingreso = anio_ingreso;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getAnio_egreso() {
        return anio_egreso;
    }

    public void setAnio_egreso(int anio_egreso) {
        this.anio_egreso = anio_egreso;
    }

    public String getR2_hermano() {
        return r2_hermano;
    }

    public void setR2_hermano(String r2_hermano) {
        this.r2_hermano = r2_hermano;
    }

    public String getR3_hermano() {
        return r3_hermano;
    }

    public void setR3_hermano(String r3_hermano) {
        this.r3_hermano = r3_hermano;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public int getAlumno_id_alumno() {
        return alumno_id_alumno;
    }

    public void setAlumno_id_alumno(int alumno_id_alumno) {
        this.alumno_id_alumno = alumno_id_alumno;
    }

    public int getEstado_id_estado() {
        return estado_id_estado;
    }

    public void setEstado_id_estado(int estado_id_estado) {
        this.estado_id_estado = estado_id_estado;
    }

    public int getBeneficio_id_beneficio() {
        return beneficio_id_beneficio;
    }

    public void setBeneficio_id_beneficio(int beneficio_id_beneficio) {
        this.beneficio_id_beneficio = beneficio_id_beneficio;
    }

    public int getTipo_solicitud_id_tipoS() {
        return tipo_solicitud_id_tipoS;
    }

    public void setTipo_solicitud_id_tipoS(int tipo_solicitud_id_tipoS) {
        this.tipo_solicitud_id_tipoS = tipo_solicitud_id_tipoS;
    }

    public Timestamp getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(Timestamp fecha_update) {
        this.fecha_update = fecha_update;
    }
    public byte[] getArchivo2() {
        return archivo2;
    }
    public void setArchivo2(byte[] archivopdf2) {
        this.archivo2 = archivopdf2;
    }
    @Override
    public String toString() {
        return "Solicitud{" + "id_formulario=" + id_formulario + ", fecha_solicitud=" + fecha_solicitud + ", anio_ingreso=" + anio_ingreso + ", semestre=" + semestre + ", anio_egreso=" + anio_egreso + ", r2_hermano=" + r2_hermano + ", r3_hermano=" + r3_hermano + ", archivo=" + archivo + ", alumno_id_alumno=" + alumno_id_alumno + ", estado_id_estado=" + estado_id_estado + ", beneficio_id_beneficio=" + beneficio_id_beneficio + ", tipo_solicitud_id_tipoS=" + tipo_solicitud_id_tipoS + ", fecha_update=" + fecha_update + '}';
    }
    
    
}
