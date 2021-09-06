package dominio;

public class AlumnoF {

    private int id_alumno;
    private String rut_alumno;
    private String nombre;
    private String carrera;
    private String telefono;
    private String email;

    public AlumnoF() {
    }

    public AlumnoF(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public AlumnoF(String rut_alumno, String nombre, String carrera, String telefono, String email) {
        this.rut_alumno = rut_alumno;
        this.nombre = nombre;
        this.carrera = carrera;
        this.telefono = telefono;
        this.email = email;
    }

    public AlumnoF(int id_alumno, String rut_alumno, String nombre, String carrera, String telefono, String email) {
        this.id_alumno = id_alumno;
        this.rut_alumno = rut_alumno;
        this.nombre = nombre;
        this.carrera = carrera;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
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

    @Override
    public String toString() {
        return "AlumnoF{" + "id_alumno=" + id_alumno + ", rut_alumno=" + rut_alumno + ", nombre=" + nombre + ", carrera=" + carrera + ", telefono=" + telefono + ", email=" + email + '}';
    }
    
    
}
