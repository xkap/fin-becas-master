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
public class RegistroMantenedorPorcentajes {

    public int id;
    public String nombre;
    public int id_beneficio;
    public String nombre_beneficio;
    public boolean vigente;
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId_beneficio() {
        return id_beneficio;
    }

    public String getNombre_beneficio() {
        return nombre_beneficio;
    }

    public boolean isVigente() {
        return vigente;
    }
    
    
}
