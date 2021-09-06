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
    
    public static List<RegistroMantenedorPorcentajes> ListarPorcentajes(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroMantenedorPorcentajes> lista_porcentajes = new ArrayList<RegistroMantenedorPorcentajes>();
        RegistroMantenedorPorcentajes registro = null;
        
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("select id_porcentaje as id, nombreP as nombre, pb.id_beneficio as id_beneficio, nombreBe as nombre_beneficio, pb.vigente as vigente "
                    + "from porcentaje pb join beneficio b on (pb.id_beneficio = b.id_beneficio)");
            rs = stmt.executeQuery();
            while(rs.next()){
                registro = new RegistroMantenedorPorcentajes();
                registro.id = rs.getInt("id");
                registro.nombre = rs.getString("nombre");
                registro.id_beneficio = rs.getInt("id_beneficio");
                registro.nombre_beneficio = rs.getString("nombre_beneficio");
                registro.vigente = rs.getBoolean("vigente");
                lista_porcentajes.add(registro);
            }
        }
        catch (Exception ex){
            System.out.println(">>> Exception PorcentajeDB/ListarPorcentajes \n" + ex.getMessage());
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return lista_porcentajes;
    }
    
    public static int ActualizarVigenciaPorcentaje(int id_porcentaje, boolean vigencia){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("UPDATE porcentaje SET vigente = "+ (vigencia?"true":"false") +" WHERE id_porcentaje = " + id_porcentaje);
            rows = stmt.executeUpdate();
        }
        catch (Exception ex){
            System.out.println(">>> Exception PorcentajesDB/ActualizarVigenciaPorcentaje \n" + ex.getMessage());
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
}
