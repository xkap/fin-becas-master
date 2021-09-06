package dominio;

public class Tipo_solicitud {
    private int id_tipoS;
    private String nombreS;

    public Tipo_solicitud() {
    }

    public Tipo_solicitud(int id_tipoS) {
        this.id_tipoS = id_tipoS;
    }

    public Tipo_solicitud(String nombreS) {
        this.nombreS = nombreS;
    }

    public Tipo_solicitud(int id_tipoS, String nombreS) {
        this.id_tipoS = id_tipoS;
        this.nombreS = nombreS;
    }

    public int getId_tipoS() {
        return id_tipoS;
    }

    public void setId_tipoS(int id_tipoS) {
        this.id_tipoS = id_tipoS;
    }

    public String getNombreS() {
        return nombreS;
    }

    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }

    @Override
    public String toString() {
        return "Tipo_solicitud{" + "id_tipoS=" + id_tipoS + ", nombreS=" + nombreS + '}';
    }
}
