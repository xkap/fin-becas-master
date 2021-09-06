/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.Mantenedores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datos.Mantenedores.BeneficiosDB;
import dominio.Mantenedores.RegistroMantenedorBeneficios;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Sebastian Groselj
 */
@WebServlet(name = "MantenedorBeneficios", urlPatterns = {"/MantenedorBeneficios"})
public class MantenedorBeneficios extends HttpServlet {

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

        String accion = request.getParameter("accion");
        if (accion == null) {accion = "listar";
        }
        switch (accion) {
            case "listar":
                obtenerBeneficios(request, response);
                break;
            case "vigencia":
                VigenciaBeneficio(request, response);
                break;
            case "insertar":
                InsertarBeneficio(request, response);
                break;
            case "cargar": {
                try {
                    CargarBeneficio(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(MantenedorBeneficios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "modificar": {
                try {
                    ModificarBeneficio(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(MantenedorBeneficios.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
            }
                break;
            case "eliminar":
            {
                try {
                    Eliminarbeneficio(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(MantenedorBeneficios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            default:
                obtenerBeneficios(request, response);
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

    private void obtenerBeneficios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RegistroMantenedorBeneficios> registros_beneficios = BeneficiosDB.ListaBeneficios();
        request.setAttribute("lista_beneficios", registros_beneficios);
        request.getRequestDispatcher("Mantenedores/MantenedorBeneficios.jsp").forward(request, response);

    }

    private void VigenciaBeneficio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_ben = Integer.parseInt(request.getParameter("id"));
        boolean is_vig = Boolean.parseBoolean(request.getParameter("bool"));

        BeneficiosDB.ActualizarVigenciaBeneficio(id_ben, is_vig);
        obtenerBeneficios(request, response);
    }

    private void InsertarBeneficio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombreBe");
        int id_tipo = Integer.parseInt(request.getParameter("id_tipo"));
        int vigente = Integer.parseInt(request.getParameter("bool"));

        BeneficiosDB.InsertarNuevoBeneficio(nombre, id_tipo, vigente);
        obtenerBeneficios(request, response);
    }

    private void CargarBeneficio(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Leer id_beneficio del listado
        int id_bene = Integer.parseInt(request.getParameter("id"));
        //Enviar el id_beneficio al beneficioDB
        RegistroMantenedorBeneficios elBeneficio = BeneficiosDB.cargarBeneficio(id_bene);
        // atributos que corresponden a id_beneficio
        request.setAttribute("list_beneficio", elBeneficio);
        //Enviar el beneficio al form
        RequestDispatcher dispatcher = request.getRequestDispatcher("Mantenedores/actualizarBeneficio.jsp");
        dispatcher.forward(request, response);

    }

    private void ModificarBeneficio(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, Exception {
        String nombre = request.getParameter("nombreBe");
        int id = Integer.parseInt(request.getParameter("id"));
        int id_tipo = Integer.parseInt(request.getParameter("id_tipo"));
        boolean vigente = Boolean.parseBoolean(request.getParameter("vigente"));
        // Crea un objeto de tipo RegistroMantenedorBeneficios con la info del formulario
        RegistroMantenedorBeneficios BeneficioActualizado = new RegistroMantenedorBeneficios(id, nombre, id_tipo, vigente);
        //Actualiza la BBDD con la info del objeto
        BeneficiosDB.ModificarBeneficio(BeneficioActualizado);
        //Vuelve al listado
        obtenerBeneficios(request, response);
    }

    private void Eliminarbeneficio(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id_bene"));
        BeneficiosDB.Eliminarbeneficio(id);
        obtenerBeneficios(request, response);

        
    }

}
