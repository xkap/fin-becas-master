<%-- 
    Document   : formulario
    Created on : 19-01-2021, 15:53:12
    Author     : solor
--%>

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
        <title>DUOC UC - IVARAS - Becas & Convenios</title>
    </head>
    <body>
        <jsp:include page="Encabezado.jsp"/>
        <form action="Contenedor" method="POST">            
            <div class="container my-5 " style="background-color: rgba(245, 245, 245, 0.9)">
                <h3 class="text-center mt-2">FORMULARIO SOLICITUD DE BECA O CONVENIO</h3><br>                

                <div class="d-md-flex">
                    <div class="col">

                        <!--RUT-->
                        <div class="form-group">
                            <label for="forRut">Rut</label>
                            <input type="text" value='${usuario.getRut()}' name="rut" class="form-control" id="forRut" placeholder="18648131-8">
                        </div>

                        <!--NOMBRE-->
                        <div class="form-group">
                            <label for="forNombre_completo">Nombre</label>
                            <input type="text" value='${usuario.getNombre_completo()}' name="nombre" class="form-control" id="forNombre_completo"
                                   placeholder="Manuel Antonio Toro López">
                        </div>

                        <!--EMAIL-->
                        <div class="form-group">
                            <label for="forEmail">Email</label>
                            <input type="text" value='${usuario.getEmail()}' name="email" class="form-control" id="forEmail" placeholder="m.toro@alumnos.duoc.cl">
                        </div>

                        <!--CARRERA-->
                        <div class="form-group">
                            <label for="forCarrera">Carrera</label>
                            <select id="forCarrera" class="form-control" name="carrera">
                                <option selected>Seleccione una carrera</option>
                                <%
                                    Connection conn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    try {
                                        conn = Conexion.getConnection();
                                        stmt = conn.prepareStatement("select * from carrera");
                                        rs = stmt.executeQuery();
                                        while (rs.next()) {
                                            out.println("<option value=" + rs.getString(1) + ">" + rs.getString(2) + "</option>");
                                        }
                                    } catch (Exception e) {
                                        out.print(e.toString());
                                    }

                                %>
                            </select>
                        </div>

                        <!--TELÉFONO-->
                        <div class="form-group">
                            <label for="forTelefono">Teléfono</label>
                            <input type="text" class="form-control" id="forTelefono" name="telefono" placeholder="+56965478912">
                        </div>

                        <!--AÑO INGRESO-->
                        <div class="form-group">
                            <label for="forIngreso">Año ingreso</label>
                            <input type="text" class="form-control" id="forIngreso" name="ingreso" placeholder="2018">
                        </div>
                    </div>

                    <div class="col">

                        <!--TIPO SOLICITUD-->
                        <div class="form-group">
                            <label for="forTipoSolicitud">Seleccione tipo de solicitud</label>
                            <select id="forTipoSolicitud" class="form-control" name="estado_id_estado">
                                <option selected>Seleccione un tipo de solicitud</option>
                                <%                                    try {
                                        conn = Conexion.getConnection();
                                        stmt = conn.prepareStatement("select * from tipo_solicitud");
                                        rs = stmt.executeQuery();
                                        while (rs.next()) {
                                            out.println("<option value=" + rs.getString(1) + ">" + rs.getString(2) + "</option>");
                                        }
                                    } catch (Exception e) {
                                        out.print(e.toString());
                                    }

                                %>
                            </select>
                        </div>

                        <!--TIPO BENEFICIO-->
                        <div class="form-group">
                            <label for="forTipoBeneficio">Seleccione tipo de beneficio</label>
                            <select id="tipo_solicitud_id_tipoS" name="tipo_solicitud_id_tipoS" onchange="cargarBecaconve(this.value);" class="form-control">
                                <option selected value="0">Seleccione tipo de benefico</option>
                                <%                            try {
                                        conn = Conexion.getConnection();
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
                        </div>

                        <!-- BENEFICIO-->
                        <div class="form-group">
                            <label for="forBeneficio">Seleccione beneficio</label>
                            <select id="beneficio_id_beneficio" name="beneficio_id_beneficio" class="form-control">
                                <option selected>Beca Deportiva</option>
                                <%                                    try {
                                        conn = Conexion.getConnection();
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
                        </div>


                        <!--AÑO EGRESO-->
                        <div class="form-group">
                            <label for="forEgreso">Año egreso</label>
                            <input type="text" name="anio_egreso" class="form-control" id="forEgreso" placeholder="2021">
                        </div>


                        <!--SEGUNDO HERMANO-->
                        <div class="form-group">
                            <label for="forRutSegundoHermano">Rut segundo hermano</label>
                            <input type="text" name="r2_hermano" class="form-control" id="forRutSegundoHermano" placeholder="20648131-6">
                        </div>

                        <!--TERCER HERMANO-->
                        <div class="form-group">
                            <label for="forRutTercerHermano">Rut tercer hermano</label>
                            <input type="text" name="r3_hermano" class="form-control" id="forRutTercerHermano" placeholder="22648131-2">
                        </div>

                        <!--TODO: ARREGLAR CAMPO ARCHIVO, NO HAY NADA-->
                        <!--ARCHIVOS-->
                        <div class="custom-file mt-2 mb-3" >
                            <label class="custom-file-label" for="customFileLang">Seleccionar Archivo</label>
                            <input type="file" class="custom-file-input" id="customFileLang" lang="es" name="archivo">
                        </div>
                        <fieldset>
                            <button name="menu" value="EnviarA" type="submit" id="contact-submit" data-submit="...Sending">ENVIAR</button>
                            <div class="alert alert-success" id="mensaje">
                                <a href="#" class="alert-link">${message}</a>
                            </div>
                        </fieldset>
                    </div>
                </div>
            </div>
        </form>


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
        <script src="js/bootstrap.min.js"></script>

        <script>
                                document.getElementById("forRut").readOnly = true;
                                document.getElementById("forNombre_completo").readOnly = true;
                                document.getElementById("forEmail").readOnly = true;
                                document.getElementById("forTelefono").readOnly = true;
                                $('#forCarrera').prop('disabled', true);

                                var id_carrera = ${usuario.getId_carrera()};
                                $("#forCarrera").val(id_carrera);

        </script>
        <script src="js/form.js"></script>
        <jsp:include page="WEB-INF/comunes/ValidarRut.jsp"/>

    </body>
</html>
