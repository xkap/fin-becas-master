/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import datos.Conexion;
import datos.ProjectDAO;
import dominio.AlumnoF;
import dominio.Log;
import dominio.Solicitud;
import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cesar
 */
@WebServlet(name = "BuscaAlumno", urlPatterns = {"/BuscaAlumno"})
public class BuscaAlumno extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
            Solicitud s = new Solicitud();
    ProjectDAO Pdao = new ProjectDAO();
    AlumnoF lr = new AlumnoF();
    Usuario us = new Usuario();
    email.Letter letter = new email.Letter();
    Log Log = new Log();
    HttpSession ses = request.getSession();
    //Conexion c = new Conexion();
    String rut = request.getParameter("txtrut");
            lr = Pdao.listarAlumnoRut(rut);
            if (lr.getRut_alumno() != null) {
                ses.setAttribute("AlumnoF", lr);
                response.sendRedirect("Formulario_Financiamiento.jsp");
                //.getRequestDispatcher("Formulario_Financiamiento.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("Formulario_Financiamiento.jsp").forward(request, response);
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
