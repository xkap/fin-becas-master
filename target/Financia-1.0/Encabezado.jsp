<%-- 
    Document   : encabezado
    Created on : 20-01-2021, 11:57:22
    Author     : solor
--%>

<%@page import="dominio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="img/png" href="img/favicon.png" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Inicia sesión con financiamiento</title>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="Contenedor?menu=Principal">
                <img src="img/logg2.png" height="50" width="140"/>
            </a>
            <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>   

            <div class="collapse navbar-collapse psAlum" id="collapsibleNavId">
                <%
                    Usuario us = (Usuario) session.getAttribute("usuario");
                    //System.out.println(us.getId_tipo_usuario());
                    if (us.getId_tipo_usuario() == 1) {%>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item " id="crearSolicitudAID" >
                        <a class="nav-link" id="CS" href="Formulario.jsp">Crear solicitud  <span class="sr-only"></span></a>
                    </li>
                    <li class="nav-item" id="verSolicitudID">
                        <a class="nav-link" href="VerSolicitudAlumno.jsp">Ver solicitud</a>
                    </li>
                </ul>
                <%
                } if (us.getId_tipo_usuario() == 2) {%>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item" id="crearSolicitudFID">
                        <a class="nav-link" href="BuscaAlumno.jsp">Crear solicitud</a>
                    </li>
                    <li class="nav-item" id="SolicitudID">
                        <a class="nav-link" href="Controlador?menu=Solicitudes&accion=Listar">Solicitudes</a>
                    </li>
                    <li class="nav-item" id="ReporteID">
                        <a class="nav-link" href="Reporte.jsp">Reportes</a>
                    </li>
                    <li class="nav-item" id="MantenedorBeneficios">
                        <a class="nav-link" href="MantenedorBeneficios">Mantenedor Beneficios</a>
                    </li>
                </ul>
                <%}%>

                <a class="nav-link text-white" href="#"> ${usuario.getNombre_completo()}</a>

                <form action="Contenedor" method="POST">
                    <button name="menu" value="salir" class="btn btn-danger" >Cerrar sesión</button>
                </form>

            </div>
        </nav>

        <div>
            <p>
                <%  String resultado = (String) request.getAttribute("mensaje");
                    String mensaje = "";
                    if (resultado != null) {
                        mensaje = resultado;
                    }
                %>
                <%=mensaje%>
            </p>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>


    </body>
</html>

<!--<a href="usuario?tipo=cerrarSesion">Cerrar sesión</a>-->