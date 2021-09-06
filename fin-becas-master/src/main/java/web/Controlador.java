package web;

import datos.ProjectDAO;
import dominio.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import email.*;

/**
 *
 * @author pablo
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Mostrar_E me = new Mostrar_E();
    Solicitud s = new Solicitud();
    
    SolicitudA soli = new SolicitudA();
    Reporte re = new Reporte();
    ProjectDAO Pdao = new ProjectDAO();
    Usuario us = new Usuario();
    Log Log = new Log();
    int ide;
    int ida;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        /*
            La idea principal es pasar los metodos a un solo servlet el cual es el "contenedor"
            ya que tiene los metodos actualizados.
        */
        if (menu.equals("Solicitudes")) {
            switch (accion) {
                case "Listar":
                    List solia = Pdao.listarSolicitudG();
                    request.setAttribute("lista_sol", solia);
                    //request.setAttribute("mostrar", request.getAttribute("mostrar"));
                    break;
                case "Mostrar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Mostrar_E me = Pdao.listarId(ide);
                    request.setAttribute("mostrar", me);
                    request.setAttribute("id_beneficio", Pdao.getIdBeneficio(ide));
                    request.getRequestDispatcher("Controlador?menu=Solicitudes&accion=Listar").forward(request, response);
                    break;
                case "estado":
                    /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
                    int id_fomulario = Integer.parseInt(request.getParameter("txtIdFormulario"));
                    String fecha_solicitud = request.getParameter("txtFechaSolicitud");
                    java.util.Date fs = formatter.parse(fecha_solicitud);
                    int anio_ingreso = Integer.parseInt(request.getParameter("txtAnioIngreso"));
                    String semestre = request.getParameter("txtSemestre");
                    int anio_egreso = Integer.parseInt(request.getParameter("txtAnioEgreso"));
                    String r2_hermano = request.getParameter("txtR2Hermano");
                    String r3_hermano = request.getParameter("txtR3Hermano");
                    String archivo = request.getParameter("txtArchivo");
                    int alumno_id_alumno = Integer.parseInt(request.getParameter("txtAlumnoIdAlumno"));
                    int estado_id_estado = Integer.parseInt(request.getParameter("txtEstadoIdEstado"));
                    int beneficio_id_beneficio = Integer.parseInt(request.getParameter("txtIdFormulario"));
                    int tipo_solicitud_id_tipoS = Integer.parseInt(request.getParameter("txtTipoSolicitudIdTipoS"));
                    String fecha_update = request.getParameter("txtFechaUpdate");
                    java.util.Date fu = formatter.parse(fecha_update);
                    s.setId_formulario(id_fomulario);
                    s.setFecha_solicitud((Date) fs);
                    s.setAnio_ingreso(anio_ingreso);
                    s.setSemestre(semestre);
                    s.setAnio_egreso(anio_egreso);
                    s.setR2_hermano(r2_hermano);
                    s.setR3_hermano(r3_hermano);
                    s.setArchivo(archivo);
                    s.setAlumno_id_alumno(alumno_id_alumno);
                    s.setEstado_id_estado(estado_id_estado);
                    s.setBeneficio_id_beneficio(beneficio_id_beneficio);
                    s.setTipo_solicitud_id_tipoS(tipo_solicitud_id_tipoS);
                    s.setFecha_update((Date) fu);
                    Pdao.actualizar(s);
                    */
                    
                    //Nuevo
                    int id_sol = Integer.parseInt(request.getParameter("id"));
                    int id_estado = Integer.parseInt(request.getParameter("estado"));
                    int id_porc = 0;
                    
                    //
                    String nombre_usu = request.getParameter("nombre_usuario");
                    int id_usu = Integer.parseInt(request.getParameter("id_usuario"));
                    String email_usu = request.getParameter("email_usuario");
                    //
                    if (id_estado == 3){
                        //
                        String tipo_usu="Financiamento";
                        Log.setTipo_usuario(tipo_usu);                        
                        Log.setNombre(nombre_usu);
                        Log.setEmail(email_usu);
                        String ac= "Modificó una solicitud";
                        Log.setAccion(ac);
                        Log.setId_formulario(id_sol);
                        Log.setId_usuario(id_usu);
                        Pdao.InsertarLog(Log);
                        //
                        
                        id_porc = Integer.parseInt(request.getParameter("porcentaje"));
                        Pdao.actualizarEstadoSolicitud(id_sol, id_estado, id_porc);
                        
                    }
                    else{
                        Pdao.actualizarEstadoSolicitud(id_sol, id_estado);
                        
                        //
                        String tipo_usu="Financiamento";
                        Log.setTipo_usuario(tipo_usu);
                        Log.setNombre(nombre_usu);
                        Log.setEmail(email_usu);
                        String ac= "Modificó la solicitud";
                        Log.setAccion(ac);
                        Log.setId_formulario(id_sol);
                        Log.setId_usuario(id_usu);
                        Pdao.InsertarLog(Log);
                        //
                    }
                    request.setAttribute("mostrar", null);
                    //Fin nuevo
                    
                    //Envio de correo
                    try{
                        SolicitudA sa = Pdao.getSolicitudA(id_sol);
                        Letter letter = new Letter();
                        letter.emailTo =  Pdao.getEmail(id_sol);
                        letter.text = "ID Solicitud: " + id_sol + "\nActualizado el estado de la solicitud: " + sa.getNombreE();
                        if (id_porc > 0){
                            String nombre_porcentaje = Pdao.getPorc(id_porc);
                            if (nombre_porcentaje != null){
                                letter.text += "\nPorcentaje: " + nombre_porcentaje;
                            }
                        }
                        Postman.send(letter);
                    }
                    catch (Exception ex){
                        System.out.println(">>> Excepcion en el envio de correo de actualizacion estado \n" + ex.getMessage());
                    }
                    
                    //Fin envio correo
                    
                    request.getRequestDispatcher("Controlador?menu=Solicitudes&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Solicitudes.jsp").forward(request, response);
        }

        if (menu.equals("Reporte")) {
            /*switch (accion) {
                case "ListarR":
                    List report = Pdao.listarReporte();
                    request.setAttribute("reportes", report);
                    break;
                default:
                    throw new AssertionError();
            }*/
            request.getRequestDispatcher("Reporte.jsp").forward(request, response);
        }
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        
        if (menu.equals("PrincipalA")) {
            request.getRequestDispatcher("PrincipalA.jsp").forward(request, response);
        }

        //alumno
        if (menu.equals("Formulario")) {
            request.getRequestDispatcher("Formulario.jsp").forward(request, response);
        }
        
       

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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
