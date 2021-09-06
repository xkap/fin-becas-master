<%@page import="datos.ProjectDAO"%>
<%@page import="dominio.Usuario"%>
<%@page import="dominio.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.List"%>
<%@page import="dominio.Tipo_solicitud"%>
<%@page import="datos.Conexion"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="img/png" href="img/favicon.png" />
        <link rel="stylesheet" href="css/style.css">
        <title>Solicitud de Becas y Convenios</title>
    </head>
    <body>
        <jsp:include page="Encabezado.jsp"/>
        <div class="container">  
            <form id="contact" action="Contenedor" method="POST" enctype="multipart/form-data">
                <h3>Solicitud de Becas y Convenios</h3>
                <h4>Rellene el formulario con sus datos</h4>

                <fieldset>
                    <input placeholder="11111111-k" id="rut_alumno" name="rut" type="text" tabindex="1" required maxlength="10" oninput="checkRut(this)" readonly=»readonly value="${usuario.getRut()}" autofocus>
                </fieldset>

                <fieldset>
                    <input placeholder="Nombre" id="nombre" name="nombre" type="text" tabindex="2" pattern="^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$" readonly=»readonly value="${usuario.getNombre_completo()}">
                </fieldset>

                <fieldset>
                    <input placeholder="Email" id="email" name="email" type="email" tabindex="3" readonly=»readonly value="${usuario.getEmail()}" required>
                </fieldset>

                <fieldset>
                    <input placeholder="Carrera" id="carrera" name="carrera"  type="text" tabindex="4" readonly=»readonly value="${gestion.buscarCarrera(usuario.getCarrera())}" required>
                </fieldset>

                <fieldset>
                    <input placeholder="Telefono Ej:+56955204260"  type="text" id="telefono" name="telefono" pattern="(\+56)\d{9}" tabindex="6" value="${usuario.getTelefono()}" required>
                </fieldset>

                <fieldset>
                    <input placeholder="Año de Ingreso" id="anio_ingreso" name="anio_ingreso" type="text" maxlength="5" pattern="\d{4}" tabindex="4" required>
                </fieldset>

                <!-- Listar cbx tipo de solicitud -->
                <fieldset>
                    <select class="form-control" id="estado_id_estado" name="estado_id_estado">
                        <option value="">Seleccione tipo de Solicitud</option>
                        <%
                            Conexion c = new Conexion();
                            Connection conn = null;
                            PreparedStatement stmt = null;
                            ResultSet rs = null;
                            try {
                                conn = c.Conectar();
                                stmt = conn.prepareStatement("select * from tipo_solicitud");
                                rs = stmt.executeQuery();
                                while (rs.next()) {
                                    out.println("<option>" + rs.getString(2) + "</option>");
                                }
                            } catch (Exception e) {
                                out.print(e.toString());
                            }

                        %>
                    </select>
                </fieldset>

                <!-- Listar cbx tipo de beneficio -->   
                <fieldset>
                    <select class="form-control" id="tipo_solicitud_id_tipoS" name="tipo_solicitud_id_tipoS" onchange="cargarBecaconve(this.value);">
                        <option value="">Seleccione tipo de beneficio</option>
                        <%                            try {
                                conn = c.Conectar();
                                stmt = conn.prepareStatement("select * from tipo_beneficio");
                                rs = stmt.executeQuery();
                                while (rs.next()) {
                                    out.println("<option>" + rs.getString(2) + "</option>");
                                }
                            } catch (Exception e) {
                                out.print(e.toString());
                            }

                        %>
                    </select>

                </fieldset>

                <!-- Listar cbx beneficio -->
                <fieldset>
                    <select class="form-control" id="beneficio_id_beneficio" name="beneficio_id_beneficio" >
                        <option value="">Seleccione un beneficio</option>
                        <%                            try {
                                conn = c.Conectar();
                                stmt = conn.prepareStatement("select * from beneficio where tipo_beneficio_id_tipoB");
                                rs = stmt.executeQuery();
                                while (rs.next()) {
                                    out.println("<option>" + rs.getString(2) + "</option>");
                                }
                            } catch (Exception e) {
                                out.print(e.toString());
                            }

                        %>
                    </select>
                </fieldset>



                <!-- Depende si sale beca Hermano -->
                <div class="DivSegundoHermano" hidden="true">
                    <fieldset>
                        <input class="RutHermano" placeholder="Rut Hermano" name="r2_hermano" id="r2_hermano" type="text" tabindex="10" maxlength="10" oninput="checkRut(this)">
                    </fieldset>
                    <div class="DivTercerHermano" hidden="true">
                        <fieldset>
                            <input placeholder="Rut Hermano" name="r3_hermano" id="r3_hermano" type="text" tabindex="11" maxlength="10" oninput="checkRut(this)">
                        </fieldset>
                    </div>
                </div>

                <!-- Depende de la beca año de egreso -->
                <div class="divaniodeegreso" hidden="true">
                    <fieldset>
                        <input class="aniodeegreso" placeholder="Año de Egreso"  name="anio_egreso" id="anio_egreso" type="text" maxlength="4" pattern="\d{4}" tabindex="12">
                    </fieldset>
                </div>

                <!-- Depende de la beca Subir archivo -->
                <div class="divfile" hidden="true">
                    <fieldset>
                        <input class="file-upload__input" type="file" name="archivo" id="archivo" accept=".pdf,.rar,image/*,.xlsx,.docx" tabindex="13" multiple>
                    </fieldset>
                </div>

                <!-- boton enviar -->
                <fieldset>
                    <button name="menu" value="EnviarA" type="submit" id="contact-submit" data-submit="...Sending">ENVIAR</button>
                    <div class="alert alert-success" id="mensaje">
                        <a href="#" class="alert-link">${message}</a>
                    </div>
                </fieldset>
            </form>
        </div>



       <!-- <script src="js/form.js"></script>-->

    </body>
    <jsp:include page="WEB-INF/comunes/ValidarRut.jsp"/>




</html>