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
