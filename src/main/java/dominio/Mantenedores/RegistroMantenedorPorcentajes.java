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
    public int porcentaje;
    public int id_beneficio;
    public String nombre_beneficio;
    public boolean vigente;

    public RegistroMantenedorPorcentajes(int id, String nombre, int porcentaje, int id_beneficio, String nombre_beneficio, boolean vigente) {
        this.id = id;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.id_beneficio = id_beneficio;
        this.nombre_beneficio = nombre_beneficio;
        this.vigente = vigente;
    }

    public RegistroMantenedorPorcentajes() {
        
    }
        
    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_beneficio(int id_beneficio) {
        this.id_beneficio = id_beneficio;
    }
    
    public void setNombre_beneficio(String nombre_beneficio) {
        this.nombre_beneficio = nombre_beneficio;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    
    public int getPorcentaje() {
        return porcentaje;
    }    
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
