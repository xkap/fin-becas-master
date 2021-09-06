<%@page import="java.util.List"%>
<%@page import="datos.Conexion"%>
<%@page import="java.sql.*"%>
<%@page import="dominio.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="img/png" href="img/favicon.png" />
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="css/mantenedor.css">
        <title>DUOC UC - IVARAS - Becas & Convenios</title>
        <% Usuario us = (Usuario) session.getAttribute("usuario"); %>
    </head>
    <body>
        <jsp:include page="/Encabezado.jsp"/>
        <div class="container-fluid p-5">
            <div class="d-flex">

                <!-- Grilla principal -->
                <div class="col-sm-8">
                    <h3>Solicitudes</h3>
                    <table id="example" class="table table-striped table-hover table-bordered container-table">
                        <thead>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Carrera</th>
                        <th>Solicitud</th>
                        <th>Beneficio Solicitado</th>
                        <th>Fecha Solicitud</th>
                        <th>Fecha Modificada</th>
                        <th>Estado</th>
                        <th>Documento</th>
                        <th>Accion</th>
                        </thead>
                        <tbody>

                            <c:forEach var="solia" items="${lista_sol}" varStatus="status">
                                <tr>
                                    <td>${solia.getId_formulario()}</td>
                                    <td>${solia.getNombre()}</td>
                                    <td>${solia.getCarrera()}</td>
                                    <td>${solia.getNombreS()}</td>
                                    <td>${solia.getNombreBe()}</td>
                                    <td>${solia.getFecha_solicitud()}</td>
                                    <td>${solia.getFecha_update()}</td>
                                    <td>${solia.getNombreE()}</td>
                                    <td>
                                        <c:if test="${solia.getArchivo2() != null}" >
                                            <a href="Contenedor?menu=BuscarArchivo&id=${solia.getId_formulario()}" target="_blank"><img src="img/icono-descarga.png" title="pdf"/></a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <a class="btn btn-primary" href="Controlador?menu=Solicitudes&accion=Mostrar&id=${solia.getId_formulario()}">Avanzar</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>

                <!-- editar -->
                <div id="form_modificador" class="card col-sm-4" ${mostrar==null?"hidden":""}>
                    <div class="card-body">
                        <form method="POST" action="Controlador?menu=Solicitudes&accion=estado&id=${mostrar.getId_formulario()}">

                            <!-- RUT -->
                            <div class="form-group">
                                <label for="inputEmail4">Rut Alumno</label>
                                <input type="text" name="rut_alumno" class="form-control " id="rut_alumno"
                                       maxlength="10" oninput="checkRut(this)" readonly=»readonly  value=${mostrar.getRut_alumno()} >
                            </div>

                            <!-- NOMBRE -->
                            <div class="form-group">
                                <label for="nalumno">Nombre Alumno</label>
                                <input type="text" name="nombre" id="nombre" class="form-control" readonly=»readonly value="${mostrar.getNombre()}"
                                       required
                                       />
                            </div>


                            <!-- CARRERA -->
                            <div class="form-group">
                                <label for="carrera">Carrera</label>
                                <input
                                    type="text"
                                    name="carrera"
                                    id="carrera"
                                    class="form-control form-control-sm"
                                    pattern="^[a-zA-Z?-?\u00f1\u00d1]+(\s*[a-zA-Z?-?\u00f1\u00d1]*)*[a-zA-Z?-?\u00f1\u00d1]+$"
                                    value="${mostrar.getCarrera()}"
                                    readonly=»readonly
                                    required
                                    />
                            </div>

                            <!-- BECA O CONVENIO -->
                            <div class="form-group">
                                <label for="inputCity">Beca o Convenio </label>
                                <input type="text" class="form-control" id="nombreBe" name="nombreBe" value="${mostrar.getNombreBe()}" readonly=»readonly>
                            </div>


                            <!-- ESTADO -->
                            <div class="form-group">
                                <label for="inputState">Estado</label>
                                <select id="inputState" name="estado" class="form-control" onchange="actualizarPorcentajesSegunEstado('inputPorcRoot', 'inputPorc', 'inputState');" required>
                                    <option selected disabled hidden>Seleccionar Estado</option>
                                    <%
                                        Connection conn = null;
                                        PreparedStatement stmt = null;
                                        ResultSet rs = null;
                                        try {
                                            conn = Conexion.getConnection();
                                            stmt = conn.prepareStatement("SELECT * FROM estado where id_estado between 2 and 5;");
                                            rs = stmt.executeQuery();
                                            while (rs.next()) {
                                                out.println("<option value=" + rs.getInt(1) + ">" + rs.getString(2) + "</option>");
                                            }
                                        } catch (Exception e) {
                                            out.print(e.toString());
                                        }

                                    %>
                                </select>
                            </div>

                            <!-- PORCENTAJE -->
                            <div class="form-group" id="inputPorcRoot">
                                <label for="inputPorc">Porcentaje %</label>
                                <select id="inputPorc" name="porcentaje" class="form-control">
                                    <option selected disabled hidden>Asignar Porcentaje</option>
                                    <%                                        try {
                                            conn = Conexion.getConnection();
                                            stmt = conn.prepareStatement("SELECT * FROM porcentaje WHERE id_beneficio=" + request.getAttribute("id_beneficio") + " and vigente");
                                            rs = stmt.executeQuery();
                                            while (rs.next()) {
                                                out.println("<option value=" + rs.getInt(1) + ">" + rs.getString(2) + "</option>");
                                            }
                                        } catch (Exception e) {
                                            out.print(e.toString());
                                        }
                                    %>
                                </select>
                            </div>

                             

                            <!-- DESCARGAR REPORTE DEL ALUMNO -->
                            <div class="form-group">
                                <c:if test="${mostrar.getArchivos()}" >
                                    <a href="Contenedor?menu=BuscarArchivo&id=${mostrar.getId_formulario()}" target="_blank">Descargar documento</a>
                                </c:if>
                            </div> 
                            
                            <input hidden="true" name="nombre_usuario" id="nombre_usuario" type="text" value="<%=us.getNombre_completo()%>">
                            <input hidden="true" name="id_usuario" id="id_usuario" type="text" value="<%=us.getId_usuario()%>">
                            <input hidden="true" name="email_usuario" id="email_usuario" type="text" value="<%=us.getEmail()%>">

                            <!-- CONFIRMAR -->
                            <div class="btn-group" role="group">
                                <button type="submit" class="btn btn-success"> 
                                    Asignar
                                </button>
                                <button type="button" class="btn btn-danger" onclick="ocultarModificadorReg('form_modificador')"> 
                                    Cancelar
                                </button>    
                            </div>
                        </form>



                    </div>

                </div>


            </div>
        </div>

        <jsp:include page="WEB-INF/comunes/script.jsp"/>

        <%-- JS --%>
        <script src="js/solicitudes.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
