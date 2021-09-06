/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import datos.Conexion;
import datos.ProjectDAO;
import dominio.Alumno;
import dominio.AlumnoF;
import dominio.Log;
import dominio.Solicitud;
import dominio.Usuario;
import email.Letter;
import email.Postman;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.mail.internet.ContentDisposition;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.Filter;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

/**
 *
 * @author josel
 */
@WebServlet(name = "Contenedor", urlPatterns = {"/Contenedor"})
@MultipartConfig(maxFileSize = 16177215)
public class Contenedor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Solicitud s = new Solicitud();
    ProjectDAO Pdao = new ProjectDAO();
    AlumnoF lr = new AlumnoF();
    Usuario us = new Usuario();
    email.Letter letter = new email.Letter();
    Log Log = new Log();
    Conexion c = new Conexion();

    //private static final String UPLOAD_DIR = "uploads";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        String menu = request.getParameter("menu");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (menu == null) {
            return;
        }
        if (menu.equals("Buscar")) {
            request.getRequestDispatcher("Formulario_Financiamiento.jsp").forward(request, response);
        }
        if (menu.equals("Formulario")) {
            request.getRequestDispatcher("Formulario.jsp").forward(request, response);
        }
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("BuscarArchivo")) {

            response.setContentType("application/force-download");
            byte[] b = null;
            String nombre_documento = null;
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                conn = c.Conectar();
                stmt = conn.prepareStatement("select archivo,nombre_archivo from solicitud where id_formulario = ?");
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    b = rs.getBytes(1);
                    nombre_documento = rs.getString(2);
                }
                InputStream bos = new ByteArrayInputStream(b);

                int tamanoInput = bos.available();
                byte[] datosPDF = new byte[tamanoInput];

                response.setHeader("Content-Disposition", "attachment;filename=" + nombre_documento);

                bos.read(datosPDF, 0, tamanoInput);

                response.getOutputStream().write(datosPDF);
                bos.close();
                stmt.close();
                rs.close();
                c.cerrarConexion(conn);

            } catch (Exception ex) {
                System.out.println(">>> Excepcion al enviar archivo en contenedor.BuscarArchivo \n" + ex.getMessage());
            }
        }
        if (menu.equalsIgnoreCase("Enviar")) {
            int ano_ingreso = Integer.parseInt(request.getParameter("ingreso"));
            String semestre = "PRIMERO/SEGUNDO (No terminado)";
            int ano_egreso = 0;
            AlumnoF lr = new AlumnoF();
            String rut = request.getParameter("rut");
            lr = Pdao.listarAlumnoRut(rut);
            if (Objects.equals(request.getParameter("anio_egreso"), "")) {
                ano_egreso = 0;
            } else {
                ano_egreso = Integer.parseInt(request.getParameter("anio_egreso"));
            }
            String rut_hermano2 = request.getParameter("r2_hermano");
            String rut_hermano3 = request.getParameter("r3_hermano");
            int idA = lr.getId_alumno();
            int id_estado = 1; //Estado Recepcionado
            int id_beneficio = 1; //Cual beca o cual convenio
            int id_tipo_solicitud = 1; //Nuevo o renovante
            String message;
            // Log
            String tipo_usu = "Financiamiento";
            String nombreA = lr.getNombre();
            String correoA = lr.getEmail();
            int contador = 0;
            try{
                conn = Conexion.Conectar();
                stmt = conn.prepareStatement("select COUNT(id_formulario) from solicitud");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    contador = rs.getInt(1)+1;
                }                
            }catch (SQLException e) {
                System.out.println(e);
            }
            String acc = "Ingreso de solicitud";
            int id_form = contador;
            
            //String rut = request.getParameter("rut");
            String definido = "";
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            java.sql.Timestamp time = new java.sql.Timestamp(millis);
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select * from tipo_solicitud where nombreS=?");
                stmt.setString(1, request.getParameter("estado_id_estado"));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    id_tipo_solicitud = rs.getInt(1);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select * from beneficio where nombreBe=?");
                stmt.setString(1, request.getParameter("beneficio_id_beneficio"));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    id_beneficio = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            InputStream inputStream = null;
            String nombre_archivo = null;
            try {
                Part filePart = request.getPart("archivo");
                if (filePart.getSize() > 0) {

                    for (String content : filePart.getHeader("content-disposition").split(";")) {
                        if (content.trim().startsWith("filename")) {
                            nombre_archivo = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
                        }
                    }
                    inputStream = filePart.getInputStream();
                }
            } catch (Exception ex) {
                System.out.println("archivo: " + ex.getMessage());
            }

            if (Objects.equals(rut, definido)) {
                message = "Tienes campos sin rellenar!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("Contenedor?menu=Buscar").forward(request, response);
            } else {
                s.setFecha_solicitud(date);
                s.setAnio_ingreso(ano_ingreso);
                s.setSemestre(semestre);
                s.setAnio_egreso(ano_egreso);
                s.setR2_hermano(rut_hermano2);
                s.setR3_hermano(rut_hermano3);
                s.setArchivo(inputStream);
                s.setNombre_archivo(nombre_archivo);
                s.setAlumno_id_alumno(idA);
                s.setEstado_id_estado(id_estado);
                s.setBeneficio_id_beneficio(id_beneficio);
                s.setTipo_solicitud_id_tipoS(id_tipo_solicitud);
                //s.setFecha_update(time); Comentado ya que al crear la solicitud no necesita registrar una fecha de modificación
                
                //Crear Log
                Log.setTipo_usuario(tipo_usu);
                Log.setNombre(nombreA);
                Log.setEmail(correoA);
                Log.setAccion(acc);               
                Log.setId_formulario(id_form);
                Log.setId_usuario(us.getId_usuario());  

                //Envio correo
                letter.emailTo = request.getParameter("email");
                letter.text = "Formulario enviado (Creado por funcionario)";
                boolean sendCorrect = Postman.send(letter);
                if (sendCorrect) {
                    System.out.println(">>> Envio de correo notificacion correcto");
                } else {
                    System.out.println(">>> Error, no se pudo enviar el correo de notificacion");
                }

                //Devolver formulario
                int rows = Pdao.insertar(s);                
                System.out.println(">>> Solicitudes nuevas desde Enviar: " + rows);

                if (rows > 0) {
                    message = "Solicitud agregada correctamente";
                    Pdao.InsertarLog(Log);
                } else {
                    message = "No se ha podido agregar la solicitud. Comunicarse con soporte";
                }
                request.setAttribute("message", message);
                request.getRequestDispatcher("BuscaAlumno.jsp").forward(request, response);

            }
        }
        if (menu.equalsIgnoreCase("EnviarA")) {
            int ano_ingreso = Integer.parseInt(request.getParameter("anio_ingreso"));
            String semestre = "PRIMERO/SEGUNDO (No terminado)";
            int ano_egreso = 0;
            if (Objects.equals(request.getParameter("anio_egreso"), "")) {
                ano_egreso = 0;
            } else {
                ano_egreso = Integer.parseInt(request.getParameter("anio_egreso"));
            }
            String rut_hermano2 = request.getParameter("r2_hermano");
            String rut_hermano3 = request.getParameter("r3_hermano");
            String fotoName = "Carga";
            int idA = us.getId_usuario();
            int id_estado = 1; //Estado Recepcionado
            int id_beneficio = 1; //Cual beca o cual convenio
            int id_tipo_solicitud = 1; //Nuevo o renovante
            String message;
            
            // Log
            String tipo_usu = "Alumno";
            String nombreA = us.getNombre_completo();
            String correoA = us.getEmail();
            int contador = 0;
            try{
                conn = c.Conectar();
                stmt = conn.prepareStatement("select COUNT(id_formulario) from solicitud");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    contador = rs.getInt(1)+1;
                }                
            }catch (SQLException e) {
                System.out.println(e);
            }
            String acc = "Ingreso de solicitud";
            int id_form = contador;
            
            String rut = request.getParameter("rut");
            String definido = "";
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            java.sql.Timestamp time = new java.sql.Timestamp(millis);
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select * from tipo_solicitud where nombreS=?");
                stmt.setString(1, request.getParameter("estado_id_estado"));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    id_tipo_solicitud = rs.getInt(1);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select * from beneficio where nombreBe=?");
                stmt.setString(1, request.getParameter("beneficio_id_beneficio"));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    id_beneficio = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            InputStream inputStream = null;
            String nombre_archivo = null;
            try {
                Part filePart = request.getPart("archivo");
                if (filePart.getSize() > 0) {

                    for (String content : filePart.getHeader("content-disposition").split(";")) {
                        if (content.trim().startsWith("filename")) {
                            nombre_archivo = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
                        }
                    }
                    inputStream = filePart.getInputStream();
                }
            } catch (Exception ex) {
                System.out.println("archivo: " + ex.getMessage());
            }
            if (Objects.equals(rut, definido)) {
                message = "Tienes campos sin rellenar!";
                System.out.println(message);
                request.setAttribute("message", message);
                request.getRequestDispatcher("Contenedor?menu=Formulario").forward(request, response);
            } else {
                s.setFecha_solicitud(date);
                s.setAnio_ingreso(ano_ingreso);
                s.setSemestre(semestre);
                s.setAnio_egreso(ano_egreso);
                s.setR2_hermano(rut_hermano2);
                s.setR3_hermano(rut_hermano3);
                s.setArchivo(inputStream);
                s.setNombre_archivo(nombre_archivo);
                s.setAlumno_id_alumno(idA);
                s.setEstado_id_estado(id_estado);
                s.setBeneficio_id_beneficio(id_beneficio);
                s.setTipo_solicitud_id_tipoS(id_tipo_solicitud);
                //s.setFecha_update(time); Comentado ya que al crear la solicitud no necesita registrar una fecha de modificación
                
                //Crear Log
                Log.setTipo_usuario(tipo_usu);
                Log.setNombre(nombreA);
                Log.setEmail(correoA);
                Log.setAccion(acc);               
                Log.setId_formulario(id_form);
                Log.setId_usuario(idA);  

                //Preparar email
                letter.emailTo = request.getParameter("email");
                letter.text = "Formulario enviado";
                //Envio de correo
                boolean sendCorrect = Postman.send(letter);
                if (sendCorrect == true) {
                    System.out.println(">>> Email enviado");
                } else {
                    System.out.println(">>> Error, email no enviado");
                }

                //Devolver formulario
                int rows = Pdao.insertar(s);
                System.out.println(">>> Solicitudes nuevas desde EnviarA: " + rows);

                if (rows > 0) {
                    message = "Solicitud agregada correctamente";
                    Pdao.InsertarLog(Log);
                } else {
                    message = "No se ha podido agregar la solicitud. Comunicarse con soporte";
                }
                request.setAttribute("message", message);
                request.getRequestDispatcher("Contenedor?menu=Formulario").forward(request, response);

            }
        }
        if (menu.equalsIgnoreCase("Ingresar")) {
            String email = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");
            us = Pdao.iniciarSesion(email, pass);

            if (us.getEmail() != null) {

                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", us);
                request.setAttribute("usuario", us);
                sesion.setAttribute("gestion", Pdao);
                request.getRequestDispatcher("Contenedor?menu=Principal").forward(request, response);
            } else {
                request.setAttribute("nologin", "No se pudo iniciar sesión");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        
        
        if(menu.equalsIgnoreCase("EditarAlumno")){             
            int id_solicitud = Integer.parseInt(request.getParameter("id_formulario"));            
            int anio_ingreso = Integer.parseInt(request.getParameter("anio_ingreso"));         
            String semestre = request.getParameter("semestre");
            int anio_egreso = Integer.parseInt(request.getParameter("anio_egreso"));  
            String r2_hermano = request.getParameter("r2_hermano");
            String r3_hermano = request.getParameter("r3_hermano");  
            InputStream inputStream = null;
            String nombre_archivo = null;
            try {
                Part filePart = request.getPart("archivo");
                if (filePart.getSize() > 0) {

                    for (String content : filePart.getHeader("content-disposition").split(";")) {
                        if (content.trim().startsWith("filename")) {
                            nombre_archivo = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
                        }
                    }
                    inputStream = filePart.getInputStream();
                }
            } catch (Exception ex) {
                System.out.println("archivo: " + ex.getMessage());
            } 
            
            int id_alumno = us.getId_usuario();
            int id_estado = Integer.parseInt(request.getParameter("id_estado")); 
            if(id_estado==5){
                id_estado = 2;
            }
            int id_beneficio = Integer.parseInt(request.getParameter("id_beneficio"));  
            int id_tipo_solicitud = Integer.parseInt(request.getParameter("id_tipo_solicitud"));  
            
            s.setId_formulario(id_solicitud);
            Date fecha_solicitud;
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select fecha_solicitud from solicitud where id_formulario="+id_solicitud);                
                rs = stmt.executeQuery();
                while (rs.next()) {
                    fecha_solicitud = rs.getDate(1);
                    s.setFecha_solicitud(fecha_solicitud);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }finally {
                c.cerrarConexion(conn);
            }
            s.setAnio_ingreso(anio_ingreso);
            s.setSemestre(semestre);
            s.setAnio_egreso(anio_egreso);
            s.setR2_hermano(r2_hermano);
            s.setR3_hermano(r3_hermano);            
            s.setArchivo(inputStream);
            s.setNombre_archivo(nombre_archivo);
            s.setAlumno_id_alumno(id_alumno);
            s.setEstado_id_estado(id_estado);
            s.setBeneficio_id_beneficio(id_beneficio);
            s.setTipo_solicitud_id_tipoS(id_tipo_solicitud);
            
            
            
            //LOG 
            String tipo_usu = "Alumno";
            String accion = "Modificación";
            //Crear Log
            Log.setTipo_usuario(tipo_usu);
            Log.setNombre(request.getParameter("nombre_completo"));
            Log.setEmail(request.getParameter("email"));
            Log.setAccion(accion);               
            Log.setId_formulario(id_solicitud);
            Log.setId_usuario(us.getId_usuario()); 
            
            Pdao.InsertarLog(Log);
            Pdao.editarSolAl(s);
            
            request.getRequestDispatcher("VerSolicitudAlumno.jsp").forward(request, response);
        }
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        /*String menu = request.getParameter("menu");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        if (menu.equalsIgnoreCase("BuscarR")) {
            //request.getRequestDispatcher("Contenedor?menu=Formulario").forward(request, response);

            String rut = request.getParameter("txtrut");
            lr = Pdao.listarAlumnoRut(rut);
            if (lr.getRut_alumno() != null) {
                request.setAttribute("AlumnoF", lr);
                request.getRequestDispatcher("Contenedor?menu=Buscar").forward(request, response);
            } else {
                request.getRequestDispatcher("Contenedor?menu=Buscar").forward(request, response);
            }
        }
        if (menu.equalsIgnoreCase("Enviar")) {
            int ano_ingreso = Integer.parseInt(request.getParameter("ingreso"));
            String semestre = "PRIMERO/SEGUNDO (No terminado)";
            int ano_egreso = 0;
            if (Objects.equals(request.getParameter("anio_egreso"), "")) {
                ano_egreso = 0;
            } else {
                ano_egreso = Integer.parseInt(request.getParameter("anio_egreso"));
            }
            String rut_hermano2 = request.getParameter("r2_hermano");
            String rut_hermano3 = request.getParameter("r3_hermano");
            int idA = lr.getId_alumno();
            int id_estado = 1; //Estado Recepcionado
            int id_beneficio = 1; //Cual beca o cual convenio
            int id_tipo_solicitud = 1; //Nuevo o renovante
            String message;
            // Log
            String tipo_usu = "Financiamiento";
            String nombreA = lr.getNombre();
            String correoA = lr.getEmail();
            int contador = 0;
            try{
                conn = c.Conectar();
                stmt = conn.prepareStatement("select COUNT(id_formulario) from solicitud");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    contador = rs.getInt(1)+1;
                }                
            }catch (SQLException e) {
                System.out.println(e);
            }
            String acc = "Ingreso de solicitud";
            int id_form = contador;
            
            String rut = request.getParameter("rut");
            String definido = "";
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            java.sql.Timestamp time = new java.sql.Timestamp(millis);
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select * from tipo_solicitud where nombreS=?");
                stmt.setString(1, request.getParameter("estado_id_estado"));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    id_tipo_solicitud = rs.getInt(1);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select * from beneficio where nombreBe=?");
                stmt.setString(1, request.getParameter("beneficio_id_beneficio"));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    id_beneficio = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            InputStream inputStream = null;
            String nombre_archivo = null;
            try {
                Part filePart = request.getPart("archivo");
                if (filePart.getSize() > 0) {

                    for (String content : filePart.getHeader("content-disposition").split(";")) {
                        if (content.trim().startsWith("filename")) {
                            nombre_archivo = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
                        }
                    }
                    inputStream = filePart.getInputStream();
                }
            } catch (Exception ex) {
                System.out.println("archivo: " + ex.getMessage());
            }

            if (Objects.equals(rut, definido)) {
                message = "Tienes campos sin rellenar!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("Contenedor?menu=Buscar").forward(request, response);
            } else {
                s.setFecha_solicitud(date);
                s.setAnio_ingreso(ano_ingreso);
                s.setSemestre(semestre);
                s.setAnio_egreso(ano_egreso);
                s.setR2_hermano(rut_hermano2);
                s.setR3_hermano(rut_hermano3);
                s.setArchivo(inputStream);
                s.setNombre_archivo(nombre_archivo);
                s.setAlumno_id_alumno(idA);
                s.setEstado_id_estado(id_estado);
                s.setBeneficio_id_beneficio(id_beneficio);
                s.setTipo_solicitud_id_tipoS(id_tipo_solicitud);
                //s.setFecha_update(time); Comentado ya que al crear la solicitud no necesita registrar una fecha de modificación
                
                //Crear Log
                Log.setTipo_usuario(tipo_usu);
                Log.setNombre(nombreA);
                Log.setEmail(correoA);
                Log.setAccion(acc);               
                Log.setId_formulario(id_form);
                Log.setId_usuario(us.getId_usuario());  

                //Envio correo
                letter.emailTo = request.getParameter("email");
                letter.text = "Formulario enviado (Creado por funcionario)";
                boolean sendCorrect = Postman.send(letter);
                if (sendCorrect) {
                    System.out.println(">>> Envio de correo notificacion correcto");
                } else {
                    System.out.println(">>> Error, no se pudo enviar el correo de notificacion");
                }

                //Devolver formulario
                int rows = Pdao.insertar(s);                
                System.out.println(">>> Solicitudes nuevas desde Enviar: " + rows);

                if (rows > 0) {
                    message = "Solicitud agregada correctamente";
                    Pdao.InsertarLog(Log);
                } else {
                    message = "No se ha podido agregar la solicitud. Comunicarse con soporte";
                }
                request.setAttribute("message", message);
                request.getRequestDispatcher("Contenedor?menu=Buscar").forward(request, response);

            }
        }
        if (menu.equalsIgnoreCase("EnviarA")) {
            int ano_ingreso = Integer.parseInt(request.getParameter("anio_ingreso"));
            String semestre = "PRIMERO/SEGUNDO (No terminado)";
            int ano_egreso = 0;
            if (Objects.equals(request.getParameter("anio_egreso"), "")) {
                ano_egreso = 0;
            } else {
                ano_egreso = Integer.parseInt(request.getParameter("anio_egreso"));
            }
            String rut_hermano2 = request.getParameter("r2_hermano");
            String rut_hermano3 = request.getParameter("r3_hermano");
            String fotoName = "Carga";
            int idA = us.getId_usuario();
            int id_estado = 1; //Estado Recepcionado
            int id_beneficio = 1; //Cual beca o cual convenio
            int id_tipo_solicitud = 1; //Nuevo o renovante
            String message;
            
            // Log
            String tipo_usu = "Alumno";
            String nombreA = us.getNombre_completo();
            String correoA = us.getEmail();
            int contador = 0;
            try{
                conn = c.Conectar();
                stmt = conn.prepareStatement("select COUNT(id_formulario) from solicitud");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    contador = rs.getInt(1)+1;
                }                
            }catch (SQLException e) {
                System.out.println(e);
            }
            String acc = "Ingreso de solicitud";
            int id_form = contador;
            
            String rut = request.getParameter("rut");
            String definido = "";
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            java.sql.Timestamp time = new java.sql.Timestamp(millis);
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select * from tipo_solicitud where nombreS=?");
                stmt.setString(1, request.getParameter("estado_id_estado"));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    id_tipo_solicitud = rs.getInt(1);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select * from beneficio where nombreBe=?");
                stmt.setString(1, request.getParameter("beneficio_id_beneficio"));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    id_beneficio = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            InputStream inputStream = null;
            String nombre_archivo = null;
            try {
                Part filePart = request.getPart("archivo");
                if (filePart.getSize() > 0) {

                    for (String content : filePart.getHeader("content-disposition").split(";")) {
                        if (content.trim().startsWith("filename")) {
                            nombre_archivo = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
                        }
                    }
                    inputStream = filePart.getInputStream();
                }
            } catch (Exception ex) {
                System.out.println("archivo: " + ex.getMessage());
            }
            if (Objects.equals(rut, definido)) {
                message = "Tienes campos sin rellenar!";
                System.out.println(message);
                request.setAttribute("message", message);
                request.getRequestDispatcher("Contenedor?menu=Formulario").forward(request, response);
            } else {
                s.setFecha_solicitud(date);
                s.setAnio_ingreso(ano_ingreso);
                s.setSemestre(semestre);
                s.setAnio_egreso(ano_egreso);
                s.setR2_hermano(rut_hermano2);
                s.setR3_hermano(rut_hermano3);
                s.setArchivo(inputStream);
                s.setNombre_archivo(nombre_archivo);
                s.setAlumno_id_alumno(idA);
                s.setEstado_id_estado(id_estado);
                s.setBeneficio_id_beneficio(id_beneficio);
                s.setTipo_solicitud_id_tipoS(id_tipo_solicitud);
                //s.setFecha_update(time); Comentado ya que al crear la solicitud no necesita registrar una fecha de modificación
                
                //Crear Log
                Log.setTipo_usuario(tipo_usu);
                Log.setNombre(nombreA);
                Log.setEmail(correoA);
                Log.setAccion(acc);               
                Log.setId_formulario(id_form);
                Log.setId_usuario(idA);  

                //Preparar email
                letter.emailTo = request.getParameter("email");
                letter.text = "Formulario enviado";
                //Envio de correo
                boolean sendCorrect = Postman.send(letter);
                if (sendCorrect == true) {
                    System.out.println(">>> Email enviado");
                } else {
                    System.out.println(">>> Error, email no enviado");
                }

                //Devolver formulario
                int rows = Pdao.insertar(s);
                System.out.println(">>> Solicitudes nuevas desde EnviarA: " + rows);

                if (rows > 0) {
                    message = "Solicitud agregada correctamente";
                    Pdao.InsertarLog(Log);
                } else {
                    message = "No se ha podido agregar la solicitud. Comunicarse con soporte";
                }
                request.setAttribute("message", message);
                request.getRequestDispatcher("Contenedor?menu=Formulario").forward(request, response);

            }
        }
        if (menu.equalsIgnoreCase("Ingresar")) {
            String email = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");
            us = Pdao.iniciarSesion(email, pass);

            if (us.getEmail() != null) {

                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", us);
                request.setAttribute("usuario", us);
                sesion.setAttribute("gestion", Pdao);
                request.getRequestDispatcher("Contenedor?menu=Principal").forward(request, response);
            } else {
                request.setAttribute("nologin", "No se pudo iniciar sesión");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        
        
        if(menu.equalsIgnoreCase("EditarAlumno")){             
            int id_solicitud = Integer.parseInt(request.getParameter("id_formulario"));            
            int anio_ingreso = Integer.parseInt(request.getParameter("anio_ingreso"));         
            String semestre = request.getParameter("semestre");
            int anio_egreso = Integer.parseInt(request.getParameter("anio_egreso"));  
            String r2_hermano = request.getParameter("r2_hermano");
            String r3_hermano = request.getParameter("r3_hermano");  
            InputStream inputStream = null;
            String nombre_archivo = null;
            try {
                Part filePart = request.getPart("archivo");
                if (filePart.getSize() > 0) {

                    for (String content : filePart.getHeader("content-disposition").split(";")) {
                        if (content.trim().startsWith("filename")) {
                            nombre_archivo = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
                        }
                    }
                    inputStream = filePart.getInputStream();
                }
            } catch (Exception ex) {
                System.out.println("archivo: " + ex.getMessage());
            } 
            
            int id_alumno = us.getId_usuario();
            int id_estado = Integer.parseInt(request.getParameter("id_estado")); 
            if(id_estado==5){
                id_estado = 2;
            }
            int id_beneficio = Integer.parseInt(request.getParameter("id_beneficio"));  
            int id_tipo_solicitud = Integer.parseInt(request.getParameter("id_tipo_solicitud"));  
            
            s.setId_formulario(id_solicitud);
            Date fecha_solicitud;
            try {
                conn = c.Conectar();
                stmt = conn.prepareStatement("select fecha_solicitud from solicitud where id_formulario="+id_solicitud);                
                rs = stmt.executeQuery();
                while (rs.next()) {
                    fecha_solicitud = rs.getDate(1);
                    s.setFecha_solicitud(fecha_solicitud);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }finally {
                c.cerrarConexion(conn);
            }
            s.setAnio_ingreso(anio_ingreso);
            s.setSemestre(semestre);
            s.setAnio_egreso(anio_egreso);
            s.setR2_hermano(r2_hermano);
            s.setR3_hermano(r3_hermano);            
            s.setArchivo(inputStream);
            s.setNombre_archivo(nombre_archivo);
            s.setAlumno_id_alumno(id_alumno);
            s.setEstado_id_estado(id_estado);
            s.setBeneficio_id_beneficio(id_beneficio);
            s.setTipo_solicitud_id_tipoS(id_tipo_solicitud);
            
            
            
            //LOG 
            String tipo_usu = "Alumno";
            String accion = "Modificación";
            //Crear Log
            Log.setTipo_usuario(tipo_usu);
            Log.setNombre(request.getParameter("nombre_completo"));
            Log.setEmail(request.getParameter("email"));
            Log.setAccion(accion);               
            Log.setId_formulario(id_solicitud);
            Log.setId_usuario(us.getId_usuario()); 
            
            Pdao.InsertarLog(Log);
            Pdao.editarSolAl(s);
            
            request.getRequestDispatcher("VerSolicitudAlumno.jsp").forward(request, response);
        }
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
        */
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>s
}
