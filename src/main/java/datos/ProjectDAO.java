package datos;

import datos.Conexion;
import dominio.*;
import java.sql.*;
import java.util.*;

public class ProjectDAO {
    Conexion c = new Conexion();
    //Grilla principal de solicitudes °Bien°
    public List<SolicitudA> listarSolicitudG() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SolicitudA solicituda = null;
        List<SolicitudA> solia = new ArrayList<>();
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("call sp_listarSolicitud()");
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_formulario = rs.getInt("id_formulario");
                String nombre = rs.getString("nombre");
                String carrera = rs.getString("carrera");
                String nombreS = rs.getString("nombreS");
                String nombreBe = rs.getString("nombreBe");
                java.sql.Date fecha_solicitud = rs.getDate("fecha_solicitud");
                java.sql.Date fecha_update = rs.getDate("fecha_update");
                String nombreE = rs.getString("nombreE");
                byte[] archivo = rs.getBytes("archivo");
                solicituda = new SolicitudA(id_formulario, nombre, carrera, nombreS, nombreBe, fecha_solicitud, fecha_update, nombreE, archivo);
                solia.add(solicituda);
            }
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> exception ListarSolicitudG \n" + ex.getMessage());
        }
        finally{
            c.cerrarConexion(conn);
        }
        return solia;
    }

    //Grilla de reporte °Problemas°
    public List<Reporte> listarReporte() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Reporte rep = null;
        List<Reporte> report = new ArrayList<>();
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("call sp_listarReporte()");
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_formulario = rs.getInt("id_formulario");
                String rut_alumno = rs.getString("rut_alumno");
                String nombre = rs.getString("nombre");
                String carrera = rs.getString("carrera");
                String nombreBe = rs.getString("nombreBe");
                java.sql.Date fecha_solicitud = rs.getDate("fecha_solicitud");
                java.sql.Date fecha_update = rs.getDate("fecha_update");
                String nombreF = rs.getString("nombreF");
                String nombreE = rs.getString("nombreE");
                rep = new Reporte(id_formulario, rut_alumno, nombre, carrera, nombreBe, fecha_solicitud, fecha_update, nombreF, nombreE);
                report.add(rep);
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
        finally
        {
            c.cerrarConexion(conn);
        }
        return report;
    }
    public AlumnoF listarAlumnoRut(String rut) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AlumnoF al = new AlumnoF();
        String sql = "SELECT id_usuario, rut, nombre_completo, descripcion, telefono, email FROM usuario z JOIN carrera x on(z.id_carrera=x.id_carrera) where rut= ? and id_tipo_usuario=1";
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, rut);
            rs = stmt.executeQuery();

            while (rs.next()) {
                al.setId_alumno(rs.getInt(1));
                al.setRut_alumno(rs.getString(2));
                al.setNombre(rs.getString(3));
                al.setCarrera(rs.getString(4));
                al.setTelefono(rs.getString(5));
                al.setEmail(rs.getString(6));
            }
        } catch (Exception e) {
        }
        finally {
            c.cerrarConexion(conn);
        }
        return al;
    }
    public String buscarCarrera(int id_carrera){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String carrera = "";
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("select * from carrera where id_carrera=?");
            stmt.setInt(1, id_carrera);
            rs = stmt.executeQuery();
             while (rs.next()) {
                 carrera = rs.getString(2);
            }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        return carrera;
    }
    //Validar alumno °Bien°
    public Usuario iniciarSesion(String email, String pass) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario us = new Usuario();
        
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("SELECT * from usuario where email=? and pass=?");
            stmt.setString(1, email);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            while (rs.next()) {
                us.setId_usuario(rs.getInt("id_usuario"));
                us.setEmail(rs.getString("email"));
                us.setPass(rs.getString("pass"));
                us.setNombre_completo(rs.getString("nombre_completo"));
                us.setRut(rs.getString("rut"));
                us.setCarrera(rs.getInt("id_carrera"));
                us.setId_tipo_usuario(rs.getInt("id_tipo_usuario"));
                us.setTelefono(rs.getString("telefono"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            c.cerrarConexion(conn);
        }
        return us;
    }

    //Validar Funcionario °Bien°
    public Funcionario validarF(String emailF, String passF) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario fn = new Funcionario();
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("select * from funcionario where emailF=? and passF=?");
            stmt.setString(1, emailF);
            stmt.setString(2, passF);
            rs = stmt.executeQuery();
            while (rs.next()) {
                fn.setId_funcionario(rs.getInt("id_funcionario"));
                fn.setEmailF(rs.getString("emailF"));
                fn.setPassF(rs.getString("passF"));
                fn.setNombreF(rs.getString("nombreF"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            c.cerrarConexion(conn);
        }
        return fn;
    }

    //Cargar los datos en el formulario de Solicitudes (Asignacion de Estados y porcentajes) °Bien°
    public Mostrar_E listarId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Mostrar_E me = new Mostrar_E();
        String sql = "SELECT s.id_formulario, a.RUT, a.NOMBRE_completo, c.descripcion, b.nombreBe, s.archivo is not null "
                + "from usuario a, beneficio b, solicitud s, carrera c "
                + "where s.id_alumno = a.id_usuario and a.id_carrera = c.id_carrera and s.id_beneficio = b.id_beneficio and id_Formulario=" + id;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                me.setId_formulario(rs.getInt(1));
                me.setRut_alumno(rs.getString(2));
                me.setNombre(rs.getString(3));
                me.setCarrera(rs.getString(4));
                me.setNombreBe(rs.getString(5));
                me.setArchivos(rs.getBoolean(6));
            }
        } catch (Exception e) {
            System.out.println(">>> exception listarID: \n" + e.getMessage() );
        }
        finally {
            c.cerrarConexion(conn);
        }
        return me;
    }

    //Cargar los datos en el formulario de Solicitudes (Asignacion de Estados y porcentajes) °Problemas°
    public AlumnoF listarAlumnoFormulario(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AlumnoF al = new AlumnoF();
        //String sql = "SELECT id_alumno, rut_alumno, nombre, carrera, telefono, email FROM alumno"
        //        + "where id_alumno= " + id;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("SELECT id_alumno, rut_alumno, nombre, carrera, telefono, email FROM alumno where id_alumno=? ");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                al.setRut_alumno(rs.getString(2));
                al.setNombre(rs.getString(3));
                al.setCarrera(rs.getString(4));
                al.setTelefono(rs.getString(5));
                al.setEmail(rs.getString(6));
            }
        } catch (Exception e) {

        }
        c.cerrarConexion(conn);
        return al;
    }

    //Segunda Version de los dat
    public int insertar(Solicitud s) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        String mensaje;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("insert into solicitud(fecha_solicitud, anio_ingreso, semestre, anio_egreso, r2_hermano, "
                    + "r3_hermano, archivo,nombre_archivo, id_alumno, id_estado, id_beneficio, id_tipo_solicitud, fecha_update )"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            //System.out.println("date:" + s.getFecha_solicitud().toString());
            stmt.setDate(1, s.getFecha_solicitud());
            stmt.setInt(2, s.getAnio_ingreso());
            stmt.setString(3, s.getSemestre());
            stmt.setInt(4, s.getAnio_egreso());
            stmt.setString(5, s.getR2_hermano());
            stmt.setString(6, s.getR3_hermano());
            stmt.setBlob(7, s.getArchivo());
            stmt.setString(8, s.getNombre_archivo());
            stmt.setInt(9, s.getAlumno_id_alumno());
            stmt.setInt(10, s.getEstado_id_estado());
            stmt.setInt(11, s.getBeneficio_id_beneficio());
            stmt.setInt(12, s.getTipo_solicitud_id_tipoS());
            stmt.setTimestamp(13, s.getFecha_update());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println(ex);
        } finally {
            c.cerrarConexion(conn);
        }
        return rows;
    }
    //Asignar estado
    public int actualizar(Solicitud s){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("update into solicitud set fecha_solicitud=?, anio_ingreso=?, semestre=?, anio_egreso=?, r2_hermano=?, "
                    + "r3_hermano=?, archivo=?, alumno_id_alumno=?, estado_id_estado=?, beneficio_id_beneficio=?, tipo_solicitud_id_tipoS=?, fecha_update=? where id_fomulario=?");
            stmt.setDate(1, s.getFecha_solicitud());
            stmt.setInt(2, s.getAnio_ingreso());
            stmt.setString(3, s.getSemestre());
            stmt.setInt(4, s.getAnio_egreso());
            stmt.setString(5, s.getR2_hermano());
            stmt.setString(6, s.getR3_hermano());
            stmt.setBlob(7, s.getArchivo());
            stmt.setInt(8, s.getAlumno_id_alumno());
            stmt.setInt(9, s.getEstado_id_estado());
            stmt.setInt(10, s.getBeneficio_id_beneficio());
            stmt.setInt(11, s.getTipo_solicitud_id_tipoS());
            stmt.setTimestamp(12, s.getFecha_update());
            stmt.setInt(13, s.getId_formulario());
            
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            c.cerrarConexion(conn);
        }
        return rows;
             
    }

    public int getIdBeneficio(int id_solicitud){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int ide = -1;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("select id_beneficio from financiamiento.solicitud where id_formulario = " + id_solicitud);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ide = rs.getInt("id_beneficio");
            }
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> exception getIdBeneficio \n" + ex.getMessage());
         } finally {
            c.cerrarConexion(conn);
        }
        
        return ide;
    }

    public int actualizarEstadoSolicitud(int id_solicitud, int id_estado, int id_porcentaje){
        //System.out.println(">>> id_sol " + id_solicitud);
        //System.out.println(">>> id_estado " + id_estado);
        //System.out.println(">>> id_porc " + id_porcentaje);
        int rows = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("update solicitud set id_estado = " + id_estado +" , fecha_update = NOW(), id_porcentaje = "+ id_porcentaje +" where id_formulario = " + id_solicitud);
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> exception actualizarEstadoSolicitud (estado, beneficio/porcentaje) \n" + ex.getMessage());
        }
        finally {
            c.cerrarConexion(conn);
        }
        
        return rows;
    }
    
    public int actualizarEstadoSolicitud(int id_solicitud, int id_estado){
        //System.out.println(">>> id_sol " + id_solicitud);
        //System.out.println(">>> id_estado " + id_estado);
        int rows = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("update solicitud set id_estado = " + id_estado +" , fecha_update = NOW(), id_porcentaje = null where id_formulario = " + id_solicitud);
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> exception actualizarEstadoSolicitud (solo estado) \n" + ex.getMessage());
        }
        finally {
            c.cerrarConexion(conn);
        }
        
        return rows;
    }
    
    public SolicitudA getSolicitudA(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SolicitudA solicituda = null;
        try {
            conn = c.Conectar();
            
            stmt = conn.prepareStatement("call sp_listarSolicitud()");
            rs = stmt.executeQuery();
            System.out.println(">>> start select");
            while (rs.next()) {
                System.out.println(">>> row");
                int id_formulario = rs.getInt("id_formulario");
                if (id_formulario != id){
                    continue;
                }
                String nombre = rs.getString("nombre");
                String carrera = rs.getString("carrera");
                String nombreS = rs.getString("nombreS");
                String nombreBe = rs.getString("nombreBe");
                java.sql.Date fecha_solicitud = rs.getDate("fecha_solicitud");
                java.sql.Date fecha_update = rs.getDate("fecha_update");
                String nombreE = rs.getString("nombreE");
                byte[] archivo = rs.getBytes("archivo");
                solicituda = new SolicitudA(id_formulario, nombre, carrera, nombreS, nombreBe, fecha_solicitud, fecha_update, nombreE, archivo);
                break;
            }
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> exception getSolicitudA \n" + ex.getMessage());
        }
        finally {
            c.cerrarConexion(conn);
        }
        
        return solicituda;
    }
    
    public String getEmail(int id_solicitud) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String salida = null;
        try {
            conn = c.Conectar();
            
            stmt = conn.prepareStatement("select email from solicitud s, usuario u where s.id_alumno = u.id_usuario and id_formulario ="+id_solicitud);
            rs = stmt.executeQuery();
            if (rs.next()) {
                salida = rs.getString(1);
            }
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> exception getEmail \n" + ex.getMessage());
        }
        finally {
            c.cerrarConexion(conn);
        }
        
        return salida;
    }
    
    public String getPorc(int id_porcentaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String salida = null;
        try {
            conn = c.Conectar();
            
            stmt = conn.prepareStatement("select nombreP from porcentaje where id_porcentaje =" + id_porcentaje);
            rs = stmt.executeQuery();
            if (rs.next()) {
                salida = rs.getString(1);
            }
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> exception getEmail \n" + ex.getMessage());
        }
       finally {
            c.cerrarConexion(conn);
        }
        
        return salida;
    }
    
    // INSERTAR EN TABLA LOG
    public int InsertarLog (Log L){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = c.Conectar();
            stmt = conn.prepareStatement("INSERT INTO LOG(tipo_usuario, nombre, email, accion, id_formulario, id_usuario) values(?,?,?,?,?,?) ");
            stmt.setString(1, L.getTipo_usuario());
            stmt.setString(2, L.getNombre());
            stmt.setString(3, L.getEmail());
            stmt.setString(4, L.getAccion());
            stmt.setInt(5, L.getId_formulario());
            stmt.setInt(6, L.getId_usuario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println(ex);
        } finally {
            c.cerrarConexion(conn);
        }
        return rows;
    }
        
    // Buscar solicitudes de alumno      
    public ArrayList<Solicitud> listaSolicitud(int id) {
        ArrayList<Solicitud> Soli = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("select * from solicitud where id_alumno="+id);     
            rs = stmt.executeQuery(); 
            while (rs.next()) {
                Solicitud unaSoli = new Solicitud();
                unaSoli.setId_formulario(rs.getInt(1));
                unaSoli.setFecha_solicitud(rs.getDate(2));
                unaSoli.setAnio_ingreso(rs.getInt(3));
                unaSoli.setSemestre(rs.getString(4));
                unaSoli.setAnio_egreso(rs.getInt(5));
                unaSoli.setR2_hermano(rs.getString(6));
                unaSoli.setR3_hermano(rs.getString(7));
                unaSoli.setNombre_archivo(rs.getString(10));                               
                unaSoli.setAlumno_id_alumno(rs.getInt(11));
                unaSoli.setEstado_id_estado(rs.getInt(12));
                unaSoli.setBeneficio_id_beneficio(rs.getInt(13));
                unaSoli.setTipo_solicitud_id_tipoS(rs.getInt(14));  
                unaSoli.setFecha_update(rs.getTimestamp(15)); 
                

                Soli.add(unaSoli);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println(ex);
        }finally {
            c.cerrarConexion(conn);
        }
       return Soli;
    }
    
    public boolean editarSolAl(Solicitud s){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("update solicitud set fecha_solicitud=?, anio_ingreso=?, semestre=?, anio_egreso=?, r2_hermano=?, "
                    + "r3_hermano=?, fecha_update= NOW()  ,archivo=?, nombre_archivo=?, id_alumno=?, id_estado=?, id_beneficio=?, id_tipo_solicitud=? where id_formulario=?");
            stmt.setDate(1, s.getFecha_solicitud());
            stmt.setInt(2, s.getAnio_ingreso());
            stmt.setString(3, s.getSemestre());
            stmt.setInt(4, s.getAnio_egreso());
            stmt.setString(5, s.getR2_hermano());
            stmt.setString(6, s.getR3_hermano());
            stmt.setBlob(7, s.getArchivo());
            stmt.setString(8, s.getNombre_archivo());
            stmt.setInt(9, s.getAlumno_id_alumno());
            stmt.setInt(10, s.getEstado_id_estado());
            stmt.setInt(11, s.getBeneficio_id_beneficio());
            stmt.setInt(12, s.getTipo_solicitud_id_tipoS());
            stmt.setInt(13, s.getId_formulario());

            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            c.cerrarConexion(conn);
        }
        return true;
             
    }
        
    
        
}

 //Insertar de la primera version
    /*public int insertar(formulario f) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = c.Conectar();
            stmt = conn.prepareStatement("call insertar_alumno_solicitud(rut_alumno, nombre, email, carrera, fecha_solicitud, anio_ingreso, anio_egreso, telefono, "
                    + "r2_hermano, r3_hermano, archivo, estado_id_estado, beneficio_id_beneficio, tipo_solicitud_id_tipoS, fecha_update)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, f.getRut_alumno());
            stmt.setString(2, f.getNombre());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getCarrera());
            stmt.setDate(5, f.getFecha_solicitud());
            stmt.setInt(6, f.getAnio_ingreso());
            stmt.setInt(7, f.getAnio_egreso());
            stmt.setString(8, f.getTelefono());
            stmt.setString(9, f.getR2_hermano());
            stmt.setString(10, f.getR3_hermano());
            stmt.setString(11, f.getArchivo());
            stmt.setInt(12, f.getEstado_id_estado());
            stmt.setInt(13, f.getBeneficio_id_beneficio());
            stmt.setInt(14, f.getTipo_solicitud_id_tipoS());
            stmt.setDate(15, f.getFecha_update());

            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }*/