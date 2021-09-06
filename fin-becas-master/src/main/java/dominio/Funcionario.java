
package dominio;


public class Funcionario {
    private int id_funcionario;
    private String rut_funcionario;
    private String nombreF;
    private String telefonoF;
    private String emailF;
    private String passF;

    public Funcionario() {
    }

    public Funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Funcionario(String rut_funcionario, String nombreF, String telefonoF, String emailF, String passF) {
        this.rut_funcionario = rut_funcionario;
        this.nombreF = nombreF;
        this.telefonoF = telefonoF;
        this.emailF = emailF;
        this.passF = passF;
    }

    public Funcionario(int id_funcionario, String rut_funcionario, String nombreF, String telefonoF, String emailF, String passF) {
        this.id_funcionario = id_funcionario;
        this.rut_funcionario = rut_funcionario;
        this.nombreF = nombreF;
        this.telefonoF = telefonoF;
        this.emailF = emailF;
        this.passF = passF;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getRut_funcionario() {
        return rut_funcionario;
    }

    public void setRut_funcionario(String rut_funcionario) {
        this.rut_funcionario = rut_funcionario;
    }

    public String getNombreF() {
        return nombreF;
    }

    public void setNombreF(String nombreF) {
        this.nombreF = nombreF;
    }

    public String getTelefonoF() {
        return telefonoF;
    }

    public void setTelefonoF(String telefonoF) {
        this.telefonoF = telefonoF;
    }

    public String getEmailF() {
        return emailF;
    }

    public void setEmailF(String emailF) {
        this.emailF = emailF;
    }

    public String getPassF() {
        return passF;
    }

    public void setPassF(String passF) {
        this.passF = passF;
    }
    
    
}
