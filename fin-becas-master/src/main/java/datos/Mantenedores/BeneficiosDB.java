/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.Mantenedores;

import datos.Conexion;
import dominio.Mantenedores.RegistroMantenedorBeneficios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian Groselj
 */
public class BeneficiosDB {
    
    /**
     * Lista los beneficios
     * @return 
     */
    public static List<RegistroMantenedorBeneficios> ListaBeneficios(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroMantenedorBeneficios> lista_registros = new ArrayList<RegistroMantenedorBeneficios>();
        RegistroMantenedorBeneficios registro = null;
        
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT id_beneficio, nombreBe as nombre, id_tipo_beneficio, nombreB as tipo_beneficio, vigente  "
                    + "FROM beneficio b join tipo_beneficio tb on (b.id_tipo_beneficio = tb.id_tipoB) "
                    + "order by id_beneficio");
            rs = stmt.executeQuery();
            while(rs.next()){
                registro = new RegistroMantenedorBeneficios();
                registro.id = rs.getInt("id_beneficio");
                registro.nombre = rs.getString("nombre");
                registro.id_tipo = rs.getInt("id_tipo_beneficio");
                registro.nombre_tipo = rs.getString("tipo_beneficio");
                registro.vigente = rs.getBoolean("vigente");
                lista_registros.add(registro);
            }
        }
        catch (Exception ex){
            System.out.println(">>> Exception BeneficiosDB/ListaBeneficios \n" + ex.getMessage());
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return lista_registros;
    }
    
    
    public static int ActualizarVigenciaBeneficio(int id_beneficio, boolean vigencia){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("UPDATE beneficio SET vigente = "+ (vigencia?"true":"false") +" WHERE id_beneficio = " + id_beneficio);
            rows = stmt.executeUpdate();
        }
        catch (Exception ex){
            System.out.println(">>> Exception BeneficiosDB/ActualizarVigenciaBeneficios \n" + ex.getMessage());
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
}
