<%-- 
    Document   : MantenedorBeneficios
    Created on : 29-01-2021, 02:39:06
    Author     : Sebastian Groselj
--%>

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
    </head>
    <body>
        <jsp:include page="/Encabezado.jsp"/>

        <%-- Inicio, Contenido --%>

        <div class="container-fluid container-md container-table">
            <h1>Mantenedor de Beneficios</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Vigente</th>
                        <th scope="col">Porcentajes</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="reg" items="${lista_beneficios}" varStatus="status">
                        <tr>
                            <th scope="row">${reg.getId()}</th>
                            <td>${reg.getNombre()}</td>
                            <td>${reg.getNombre_tipo()}</td>
                            <td>
                                <input type="checkbox" aria-label="Vigente" ${reg.isVigente()?"checked":""} onchange="ActualizarVigencia(${reg.getId()}, ${!reg.isVigente()})">
                                Vigente
                            </td>
                            <td>
                                <button type="button" class="btn btn-primary" onclick="location.href='MantenedorPorcentajes?id_beneficio=${reg.getId()}';">Porcentajes</button>
                            </td>
                        </tr>
                    </c:forEach>
                    <%--
                    <tr>
                        <th scope="row">1</th>
                        <td>BECA HERMANOX</td>
                        <td>BECAX</td>
                        <td>
                            <input type="checkbox" aria-label="Vigente" onchange="">
                            Vigente
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary">Porcentajes</button>
                        </td>
                    </tr>
                    --%>
                </tbody>
            </table>
        </div>

        <%-- Fin, Contenido --%>
        <script src="js/mantenedorBeneficios.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
        
    </body>
</html>