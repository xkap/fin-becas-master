/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.Mantenedores;

/**
 *
 * @author Sebastian Groselj
 */
public class RegistroMantenedorBeneficios {

    public int id;
    public String nombre;
    public int id_tipo;
    public String nombre_tipo;
    public boolean vigente;

    public RegistroMantenedorBeneficios(int id, String nombre, int id_tipo, boolean vigente) {
        super(); 
        this.id = id;
        this.nombre = nombre;
        this.id_tipo = id_tipo;
        this.vigente = vigente;
    }

    public RegistroMantenedorBeneficios() {
       
    }


 

    @Override
    public String toString() {
        return "RegistroMantenedorBeneficios{" + "id=" + id + ", nombre=" + nombre + ", id_tipo=" + id_tipo + ", vigente=" + vigente + '}';
    }

 
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }


 
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public boolean isVigente() {
        return vigente;
    }
    
    
    
}
