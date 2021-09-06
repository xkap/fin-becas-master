/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.Mantenedores;

import dominio.Mantenedores.RegistroMantenedorPorcentajes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datos.Mantenedores.PorcentajesDB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Hancov
 */
@WebServlet(name = "MantenedorPorcentajes", urlPatterns = {"/MantenedorPorcentajes"})
public class MantenedorPorcentajes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_beneficio_str = request.getParameter("id_beneficio");
        /*
        request.setAttribute("id_bene", id_beneficio_str);
        */
        String accion = request.getParameter("accion");
        //Actualizar vigencia
        if (accion == null) accion = "listar";
        switch (accion) {
            
            case "listar":
                /*
                ListarPorcentaje(request, response, id_beneficio_str);
                */
                List<RegistroMantenedorPorcentajes> lista_porcentajes = PorcentajesDB.ListarPorcentajes();
                if (id_beneficio_str != null) {

                    int id_ben = Integer.parseInt(id_beneficio_str);
                    FiltrarIdBeneficio(lista_porcentajes, id_ben);

                }
                request.setAttribute("lista_porcentajes", lista_porcentajes);
                request.getRequestDispatcher("Mantenedores/MantenedorPorcentajes.jsp").forward(request, response);
                
            break;
            
            case "vigencia":
                try {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean vig = Boolean.parseBoolean(request.getParameter("bool"));

                PorcentajesDB.ActualizarVigenciaPorcentaje(id, vig);
                } catch (Exception ex) {
                System.out.println(">>> Exception ActualizarVigencia \n+" + ex.getMessage());
                }
            break;
            
            case "insertar":
                
                InsertarPorcentaje(request,response);
                /*
                String nombre = request.getParameter("nombreP");
                int porcentaje = Integer.parseInt(request.getParameter("porcentaje"));
                int id_beneficio = Integer.parseInt(request.getParameter("id_bene"));
                int vigente = Integer.parseInt(request.getParameter("bool"));
                PorcentajesDB.InsertarNuevoPorcentaje(nombre, porcentaje, id_beneficio, vigente);
                */
            break;
            
            default:
                
                
                /*
                ListarPorcentaje(request, response);
                */
                
            
        }

        /*
        List<RegistroMantenedorPorcentajes> lista_porcentajes = PorcentajesDB.ListarPorcentajes();
        
        
        if (id_beneficio_str != null) {
            
            int id_ben = Integer.parseInt(id_beneficio_str);
            FiltrarIdBeneficio(lista_porcentajes, id_ben);
     
        }
        */
        /*
        if (accion != null && accion.equals("insertar")) {
            String nombre = request.getParameter("nombreP");
            int porcentaje = Integer.parseInt(request.getParameter("porcentaje"));
            int id_beneficio = Integer.parseInt(request.getParameter("id_bene"));           
            int vigente = Integer.parseInt(request.getParameter("bool"));
            PorcentajesDB.InsertarNuevoPorcentaje(nombre, porcentaje, id_beneficio, vigente);
        }
        */
        /*
        request.setAttribute("lista_porcentajes", lista_porcentajes);
        request.getRequestDispatcher("Mantenedores/MantenedorPorcentajes.jsp").forward(request, response);
        */
        
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
    /*
    private void ListarPorcentaje(HttpServletRequest request, HttpServletResponse response, String id_beneficio_str) throws ServletException, IOException {
        List<RegistroMantenedorPorcentajes> lista_porcentajes = PorcentajesDB.ListarPorcentajes();
        if (id_beneficio_str != null) {
            int id_ben = Integer.parseInt(id_beneficio_str);
            FiltrarIdBeneficio(lista_porcentajes, id_ben);
        }
        request.setAttribute("ListarPorcentajes", lista_porcentajes);
        request.getRequestDispatcher("Mantenedores/MantenedorPorcentajes.jsp").forward(request, response);
        
    }
    */
    
    private void InsertarPorcentaje(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombreP");
        int porcentaje = Integer.parseInt(request.getParameter("porcentaje"));
        int id_beneficio = Integer.parseInt(request.getParameter("id_bene"));
        int vigente = Integer.parseInt(request.getParameter("bool"));
        PorcentajesDB.InsertarNuevoPorcentaje(nombre, porcentaje, id_beneficio, vigente);
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Utilidades">
    /**
     * Filtra una lista de porcentajes por ID_Beneficio
     *
     * @param lista Lista de porcentajes
     * @param id_beneficio Id del beneficio para filtrar
     */
    public void FiltrarIdBeneficio(List<RegistroMantenedorPorcentajes> lista, int id_beneficio) {
        for (int i = lista.size() - 1; i >= 0; i--) {
            if (lista.get(i).getId_beneficio() != id_beneficio) {
                lista.remove(i);
            }
        }
    }

    // </editor-fold>
}
