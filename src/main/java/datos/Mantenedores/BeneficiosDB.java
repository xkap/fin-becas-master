
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Sebastian Groselj
 */
@WebServlet(name = "BeneficiosDB", urlPatterns = {"/BeneficiosDB"})
public class BeneficiosDB {

    static Conexion c = new Conexion();

    /**
     * Lista los beneficios
     *
     * @return
     */
    public static List<RegistroMantenedorBeneficios> ListaBeneficios() {
        Connection conn = null;
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<RegistroMantenedorBeneficios> lista_registros = new ArrayList<RegistroMantenedorBeneficios>();
        RegistroMantenedorBeneficios registro = null;

        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("SELECT id_beneficio, nombreBe as nombre, id_tipo_beneficio, nombreB as tipo_beneficio, vigente  "
                    + "FROM beneficio b join tipo_beneficio tb on (b.id_tipo_beneficio = tb.id_tipoB) "
                    + "order by id_beneficio");
            rs = stmt.executeQuery();
            while (rs.next()) {
                registro = new RegistroMantenedorBeneficios();
                registro.id = rs.getInt("id_beneficio");
                registro.nombre = rs.getString("nombre");
                registro.id_tipo = rs.getInt("id_tipo_beneficio");
                registro.nombre_tipo = rs.getString("tipo_beneficio");
                registro.vigente = rs.getBoolean("vigente");
                lista_registros.add(registro);
            }
        } catch (Exception ex) {
            System.out.println(">>> Exception BeneficiosDB/ListaBeneficios \n" + ex.getMessage());
        } finally {
            c.cerrarConexion(conn);
        }

        return lista_registros;
    }

    public static int ActualizarVigenciaBeneficio(int id_beneficio, boolean vigencia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("UPDATE beneficio SET vigente = " + (vigencia ? "true" : "false") + " WHERE id_beneficio = " + id_beneficio);
            rows = stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(">>> Exception BeneficiosDB/ActualizarVigenciaBeneficios \n" + ex.getMessage());
        } finally {
            c.cerrarConexion(conn);
        }

        return rows;
    }

    public static int InsertarNuevoBeneficio(String nombre, int id_tipo, int vigente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        RegistroMantenedorBeneficios registro = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("INSERT INTO beneficio(nombreBe, id_tipo_beneficio, vigente) VALUES (?,?,?)");

            stmt.setString(1, nombre);
            stmt.setInt(2, id_tipo);
            stmt.setInt(3, vigente);

            rows = stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.out.println(">>> Exception BeneficiosDB/InsertarNuevoBeneficio \n" + ex.getMessage());
        } finally {
            c.cerrarConexion(conn);
        }

        return rows;
    }

    public static RegistroMantenedorBeneficios cargarBeneficio(int id_bene) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroMantenedorBeneficios> lista_beneficios = new ArrayList<RegistroMantenedorBeneficios>();
        RegistroMantenedorBeneficios elBeneficio = null;

        //int rows = 0;
        try {

            conn = c.Conectar();
            stmt = conn.prepareStatement("SELECT * FROM beneficio WHERE id_beneficio= ?");
            stmt.setInt(1, id_bene);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_beneficio");
                String nombre = rs.getString("nombreBe");
                int id_tipo = rs.getInt("id_tipo_beneficio");
                //String nombre_tipo=rs.getString("nombreB");
                boolean vigente = rs.getBoolean("vigente");

                elBeneficio = new RegistroMantenedorBeneficios(id, nombre, id_tipo, vigente);

            } else {
                throw new Exception("Beneficio no encontrado con id=" + id_bene);
            }
        } catch (Exception e) {
        } finally {
            c.cerrarConexion(conn);
        }
        return elBeneficio;
    }

    /*  public static void ModificarBeneficio(RegistroMantenedorBeneficios BeneficioActualizado) throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RegistroMantenedorBeneficios bene = null;
                
            conn = c.Conectar();
            String sql=("UPDATE beneficio SET nombreBe = ? , id_tipo_beneficio = ?, b.vigente= ? where id_beneficio= ?");
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, BeneficioActualizado.getId());
            stmt.setString(2, BeneficioActualizado.getNombre());
            stmt.setInt(3, BeneficioActualizado.getId_tipo());
            stmt.setBoolean(4, BeneficioActualizado.isVigente());
            stmt.execute();
        
    }*/
    public static boolean ModificarBeneficio(RegistroMantenedorBeneficios BeneficioActualizado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        //RegistroMantenedorBeneficios registro = null;
        //ResultSet rs = null;
        // int rows = 0;

        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("UPDATE beneficio SET nombreBe = ? ,id_tipo_beneficio = ?, vigente=? where id_beneficio= ?");

            stmt.setString(1, BeneficioActualizado.getNombre());
            stmt.setInt(2, BeneficioActualizado.getId_tipo());
            stmt.setBoolean(3, BeneficioActualizado.isVigente());
            stmt.setInt(4, BeneficioActualizado.getId());

            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.out.println(">>> Exception BeneficiosDB/ModificarBeneficio \n" + ex.getMessage());
        } finally {
            c.cerrarConexion(conn);
        }

        return true;
    }

    public static void Eliminarbeneficio(int id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("DELETE FROM beneficio WHERE id_beneficio=?");
            stmt.setInt(1, id);

            stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.out.println(">>> Exception BeneficiosDB/Eliminarbeneficio \n" + ex.getMessage());
        } finally {
            c.cerrarConexion(conn);
        }
    }
}
