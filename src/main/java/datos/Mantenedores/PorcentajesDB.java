/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.Mantenedores;

import datos.Conexion;
import dominio.Mantenedores.RegistroMantenedorPorcentajes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Sebastian Groselj
 */
public class PorcentajesDB {
    static Conexion c = new Conexion();

    public static List<RegistroMantenedorPorcentajes> ListarPorcentajes(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroMantenedorPorcentajes> lista_porcentajes = new ArrayList<RegistroMantenedorPorcentajes>();
        RegistroMantenedorPorcentajes registro = null;
        
        try{
            conn = c.Conectar();
            stmt = conn.prepareStatement("select id_porcentaje as id, nombreP as nombre,porcentaje as porcentaje, pb.id_beneficio as id_beneficio, nombreBe, pb.vigente as vigente from porcentaje pb join beneficio b on (pb.id_beneficio = b.id_beneficio)");
            rs = stmt.executeQuery();
            while(rs.next()){
                registro = new RegistroMantenedorPorcentajes();
                registro.id = rs.getInt("id");
                registro.nombre = rs.getString("nombre");
                registro.porcentaje = rs.getInt("porcentaje");
                registro.id_beneficio = rs.getInt("id_beneficio");
                registro.nombre_beneficio = rs.getString("nombreBe");
                registro.vigente = rs.getBoolean("vigente");
                lista_porcentajes.add(registro);
            }
        }
        catch (Exception ex){
            System.out.println(">>> Exception PorcentajeDB/ListarPorcentajes \n" + ex.getMessage());
        }
        finally{
            c.cerrarConexion(conn);
        }
        
        return lista_porcentajes;
    }
    
    public static int ActualizarVigenciaPorcentaje(int id_porcentaje, boolean vigencia){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = c.Conectar();
            stmt = conn.prepareStatement("UPDATE porcentaje SET vigente = "+ (vigencia?"true":"false") +" WHERE id_porcentaje = " + id_porcentaje);
            rows = stmt.executeUpdate();
        }
        catch (Exception ex){
            System.out.println(">>> Exception PorcentajesDB/ActualizarVigenciaPorcentaje \n" + ex.getMessage());
        }
        finally{
            c.cerrarConexion(conn);
        }
        
        return rows;
    }
    
    public static int InsertarNuevoPorcentaje(String nombre, int porcentaje ,int id_beneficio, int vigente){
        Connection conn = null;
        PreparedStatement stmt = null;
        RegistroMantenedorPorcentajes registro = null;
        ResultSet rs = null;
        int rows = 0;
        
        try{
            conn = c.Conectar();
            stmt = conn.prepareStatement("INSERT INTO porcentaje(nombreP, porcentaje, id_beneficio, vigente) VALUES (?,?,?,?)");
                        

                
                stmt.setString(1, nombre);
                stmt.setInt(2, porcentaje);
                stmt.setInt(3, id_beneficio);
                stmt.setInt(4, vigente);
            
                rows = stmt.executeUpdate();

            
            
        }
        catch (Exception ex){
            ex.printStackTrace(System.out);
            System.out.println(">>> Exception PorcentajesDB/InsertarNuevoPorcentaje \n" + ex.getMessage());
        }
        finally{
            c.cerrarConexion(conn);
        }
        
        return rows;
    }
    
    
}
