package dominio;

import java.sql.Date;

public class formulario {
    private String rut_alumno;
    private String nombre;
    private String email;
    private String carrera;
    private Date fecha_solicitud;
    private int anio_ingreso;
    private int anio_egreso;
    private String telefono;
    private String r2_hermano;
    private String r3_hermano;
    private String archivo;
    private int estado_id_estado;
    private int beneficio_id_beneficio;
    private int tipo_solicitud_id_tipoS;
    private Date fecha_update;

    public formulario() {
    }

    public formulario(String rut_alumno) {
        this.rut_alumno = rut_alumno;
    }

    public formulario(String nombre, String email, String carrera, Date fecha_solicitud, int anio_ingreso, int anio_egreso, String telefono, String r2_hermano, String r3_hermano, String archivo, int estado_id_estado, int beneficio_id_beneficio, int tipo_solicitud_id_tipoS, Date fecha_update) {
        this.nombre = nombre;
        this.email = email;
        this.carrera = carrera;
        this.fecha_solicitud = fecha_solicitud;
        this.anio_ingreso = anio_ingreso;
        this.anio_egreso = anio_egreso;
        this.telefono = telefono;
        this.r2_hermano = r2_hermano;
        this.r3_hermano = r3_hermano;
        this.archivo = archivo;
        this.estado_id_estado = estado_id_estado;
        this.beneficio_id_beneficio = beneficio_id_beneficio;
        this.tipo_solicitud_id_tipoS = tipo_solicitud_id_tipoS;
        this.fecha_update = fecha_update;
    }

    public formulario(String rut_alumno, String nombre, String email, String carrera, Date fecha_solicitud, int anio_ingreso, int anio_egreso, String telefono, String r2_hermano, String r3_hermano, String archivo, int estado_id_estado, int beneficio_id_beneficio, int tipo_solicitud_id_tipoS, Date fecha_update) {
        this.rut_alumno = rut_alumno;
        this.nombre = nombre;
        this.email = email;
        this.carrera = carrera;
        this.fecha_solicitud = fecha_solicitud;
        this.anio_ingreso = anio_ingreso;
        this.anio_egreso = anio_egreso;
        this.telefono = telefono;
        this.r2_hermano = r2_hermano;
        this.r3_hermano = r3_hermano;
        this.archivo = archivo;
        this.estado_id_estado = estado_id_estado;
        this.beneficio_id_beneficio = beneficio_id_beneficio;
        this.tipo_solicitud_id_tipoS = tipo_solicitud_id_tipoS;
        this.fecha_update = fecha_update;
    }

    public String getRut_alumno() {
        return rut_alumno;
    }

    public void setRut_alumno(String rut_alumno) {
        this.rut_alumno = rut_alumno;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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

    public int getAnio_egreso() {
        return anio_egreso;
    }

    public void setAnio_egreso(int anio_egreso) {
        this.anio_egreso = anio_egreso;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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

    public Date getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(Date fecha_update) {
        this.fecha_update = fecha_update;
    }

    @Override
    public String toString() {
        return "formulario{" + "rut_alumno=" + rut_alumno + ", nombre=" + nombre + ", email=" + email + ", carrera=" + carrera + ", fecha_solicitud=" + fecha_solicitud + ", anio_ingreso=" + anio_ingreso + ", anio_egreso=" + anio_egreso + ", telefono=" + telefono + ", r2_hermano=" + r2_hermano + ", r3_hermano=" + r3_hermano + ", archivo=" + archivo + ", estado_id_estado=" + estado_id_estado + ", beneficio_id_beneficio=" + beneficio_id_beneficio + ", tipo_solicitud_id_tipoS=" + tipo_solicitud_id_tipoS + ", fecha_update=" + fecha_update + '}';
    }
    
    
}
