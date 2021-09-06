package dominio;

public class Mostrar_E {
    private int id_formulario;
    private String rut_alumno;
    private String nombre;
    private String carrera;
    private String nombreBe;
    private Boolean archivos;

    public Mostrar_E() {
    }

    public Mostrar_E(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public Mostrar_E(String rut_alumno, String nombre, String carrera, String nombreBe, Boolean archivos) {
        this.rut_alumno = rut_alumno;
        this.nombre = nombre;
        this.carrera = carrera;
        this.nombreBe = nombreBe;
        this.archivos = archivos;
    }

    public Mostrar_E(int id_formulario, String rut_alumno, String nombre, String carrera, String nombreBe, Boolean archivos) {
        this.id_formulario = id_formulario;
        this.rut_alumno = rut_alumno;
        this.nombre = nombre;
        this.carrera = carrera;
        this.nombreBe = nombreBe;
        this.archivos = archivos;
    }

    public int getId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(int id_formulario) {
        this.id_formulario = id_formulario;
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
        
    public void setArchivos(Boolean archivos) {
        this.archivos = archivos;
    }

    public Boolean getArchivos() {
        return archivos;
    }

    @Override
    public String toString() {
        return "Mostrar_E{" + "id_formulario=" + id_formulario + ", rut_alumno=" + rut_alumno + ", nombre=" + nombre + ", carrera=" + carrera + ", nombreBe=" + nombreBe + '}';
    }
}
