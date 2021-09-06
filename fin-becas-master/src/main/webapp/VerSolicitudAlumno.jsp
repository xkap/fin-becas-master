<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ProjectDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.List"%>
<%@page import="dominio.*"%>
<%@page import="datos.Conexion"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="img/png" href="img/favicon.png" />
        <link rel="stylesheet" href="css/style.css">
        <script defer src="js/all.js"></script> 
        <title>DUOC UC - IVARAS - Mis Solicitudes</title>
    </head>
    <body>
        <jsp:include page="Encabezado.jsp"/>
         
        <div class="container p-5 d-flex shadow p-3 mb-5 bg-white rounded">
            <div class="table-responsive">
                <h3>Solicitudes</h3>
                
                <table class="table table-responsive table-striped table-hover table-bordered">
                    <thead>
                    <th scope="col">Id</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Carrera</th>
                    <th scope="col">Solicitud</th>
                    <th scope="col">Beneficio Solicitado</th>
                    <th scope="col">Fecha Solicitud</th>
                    <th scope="col">Fecha Modificada</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Accion</th>
                    </thead>
                    
                    
                    <% 
                        ProjectDAO PDAO = new ProjectDAO();
                        Usuario us = (Usuario) session.getAttribute("usuario");
                        ArrayList<Solicitud> solicitudes = PDAO.listaSolicitud(us.getId_usuario());
                        Iterator<Solicitud> iter = solicitudes.iterator();
                        Solicitud unaSoli = null;
                        Connection conn = null;
                        PreparedStatement stmt = null;
                        ResultSet rs = null;
                        conn = Conexion.getConnection();
                        
                        while (iter.hasNext()) {
                           unaSoli = iter.next();                           
                        
                    %>    
                    
                    <tbody>
                        
                        <td class="align-middle"><%= unaSoli.getId_formulario()%></td>
                        <td class="align-middle"><%= us.getNombre_completo()%></td>
                        
                        <%                            
                        
                        try {
                            
                            int idCarrera = us.getCarrera();
                            stmt = conn.prepareStatement("select * from carrera where id_carrera="+idCarrera);
                            rs = stmt.executeQuery();
                            while (rs.next()) {
                                out.println("<td class="+"align-middle>" + rs.getString(2) + "</td>");
                            }
                        }
                        catch (Exception e) {
                            out.print(e.toString());
                        }                      
                        %>                       
                        
                        
                        <%                            
                        
                        try {
                            
                            int idTipoSoli = unaSoli.getTipo_solicitud_id_tipoS();
                            stmt = conn.prepareStatement("select * from tipo_solicitud where id_tipoS="+idTipoSoli);
                            rs = stmt.executeQuery();
                            while (rs.next()) {
                                out.println("<td class="+"align-middle>" + rs.getString(2) + "</td>");
                            }
                        }
                        catch (Exception e) {
                            out.print(e.toString());
                        }                      
                        %>                       
                        
                        <%                            
                        
                        try {
                            
                            int idBeneficio = unaSoli.getBeneficio_id_beneficio();
                            stmt = conn.prepareStatement("select * from beneficio where id_beneficio="+idBeneficio);
                            rs = stmt.executeQuery();
                            while (rs.next()) {
                                out.println("<td class="+"align-middle>" + rs.getString(2) + "</td>");
                            }
                        }
                        catch (Exception e) {
                            out.print(e.toString());
                        }                      
                        %>
                        <td><%= unaSoli.getFecha_solicitud()%></td>
                        <%
                        try {
                            stmt = conn.prepareStatement("select fecha_update from solicitud where id_formulario="+unaSoli.getId_formulario());
                            rs = stmt.executeQuery();
                            while (rs.next()) {
                                if(rs.getString(1)==null){
                                    out.println("<td class="+"align-middle>No registra modificaciones</td>");
                                }else{
                                    out.println("<td class="+"align-middle>" + rs.getString(1) + "</td>");
                                }                        
                            }
                        }catch (Exception e) {
                            out.print(e.toString());
                        } 
                        %>
                        <%
                        try {
                            
                            int idEstado = unaSoli.getEstado_id_estado();
                            stmt = conn.prepareStatement("select * from estado where id_estado="+idEstado);
                            rs = stmt.executeQuery();
                            while (rs.next()) {
                                out.println("<td class="+"align-middle>" + rs.getString(2) + "</td>");
                            }
                        }
                        catch (Exception e) {
                            out.print(e.toString());
                        }                      
                        %>
                        
                        <%
                        try {
                            int idEstado = unaSoli.getEstado_id_estado();
                            stmt = conn.prepareStatement("select * from estado where id_estado="+idEstado);
                            rs = stmt.executeQuery();
                            while (rs.next()) {
                                if(idEstado==5){
                                    %> 
                                    <div>
                                    <td class="align-middle">
                                        <a  class="d-flex justify-content-center" href="EditarSolicitudAlumno.jsp?id=<%= unaSoli.getId_formulario()%>&ver=2" style="display: inline-table; margin-left: 10%"><i class="fas fa-edit" style="margin-left:1%;"></i></a>
                                    </td> 
                                    </div><%
                                }else{
                                        %> <td class="align-middle">
                                            <a  class="d-flex justify-content-center" href="EditarSolicitudAlumno.jsp?id=<%= unaSoli.getId_formulario()%>&ver=1" style="display: inline-table; margin-left: 10%"><i class="fas fa-eye" style="margin-left:1%;"></i></a> 
                                        </td><%
                                }                            
                                
                                
                            }
                        }
                        catch (Exception e) {
                            out.print(e.toString());
                        }                      
                        %>
                        
                        
                        
                        
                        
                        <% } %>
                    </tbody>
                </table>
            </div>
            
        </div>
    </body>
</html>
