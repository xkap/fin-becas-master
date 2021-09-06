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
        <title>Solicitud de Becas y Convenios</title>
    </head>
    <body>
        <jsp:include page="Encabezado.jsp"/>
        <div class="d-flex">

            <div class="container" >  
                <form id="contact" action="BuscaAlumno" method="POST">
                    <h3>Solicitud de Becas y Convenios</h3>
                    <h4>Busque al estudiante al cual se le asignaran los beneficios</h4>

                    <fieldset>
                        <input placeholder="Rut alumno" id="rut_alumno" name="txtrut" type="text" tabindex="1"  maxlength="10" oninput="checkRut(this)" autofocus required>
                    </fieldset>

                    <fieldset>
                        <button type="submit" name="menu" value="BuscarR" class="btn btn-primary btn-block">BUSCAR</button>
                    </fieldset>
                     </fieldset>
                    <div class="alert alert-success" id="mensaje">
                    <fieldset>
                        <a href="#" class="alert-link" >${message}</a>
                    </fieldset>
                </div>
                </form>
                
            </div>
        </div>
                    

        <!--<script src="js/form.js"></script>-->

    </body>
    <jsp:include page="WEB-INF/comunes/ValidarRut.jsp"/>




</html>