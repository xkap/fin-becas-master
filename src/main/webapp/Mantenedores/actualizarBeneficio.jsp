<%-- 
    Document   : MantenedorBeneficios
    Created on : 29-01-2021, 02:39:06
    Author     : Sebastian Groselj
--%>
<%@page import="datos.Mantenedores.BeneficiosDB"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.List"%>
<%@page import="dominio.*"%>
<%@page import="dominio.Mantenedores.RegistroMantenedorBeneficios"%>
<%@page import="datos.Conexion"%>
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
        <link rel="stylesheet" href="css/modal.css">
        <title>DUOC UC - IVARAS - Becas & Convenios</title>
    </head>
    <body>
        <jsp:include page="/Encabezado.jsp"/>

        <%-- Inicio, Contenido --%>

        <div class="container-fluid container-md container-table">

            <h1>Modificar de Beneficio</h1>

            <form method="get" action="MantenedorBeneficios" >
                <input type="hidden" name="accion" value="modificar">
                <style> 
                    input[type=text] {
                        width:30%;
                        

                    }
                    
                </style>
                
                <input type="hidden" name="id" value="${list_beneficio.id}">
                <h3>Modificar Beneficio</h3>
                <br />
                <label>  Nombre: </label>
                <input type="text" name="nombreBe" value="${list_beneficio.nombre}"/>
                <br />
                 <label>  Tipo de beneficio actual: </label>
                
                <br /> 
                <label>  Cambiar tipo de beneficio: </label>
                <select name="id_tipo"> </option> <option value="2">Convenio</option> <option value="1">Beca</option> </select>
                <br />
                <label>Activar beneficio: </label>
                <input type="checkbox" aria-label="Vigente" ${list_beneficio.isVigente()?"checked":""} onchange="ActualizarVigencia(${list_beneficio.getId()}, ${!list_beneficio.isVigente()})">
                    Vigente
                </td>
                
                <button type="submit" class="btn btn-primary" style = "position:relative; left:140px; top:2px;">  Modificar Beneficio </button>
                <br />
                <br />

            </form>
        </div>


        <%-- Fin, Contenido --%>
        <script src="js/mantenedorBeneficios.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>


    </body>
</html>