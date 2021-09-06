<%-- 
    Document   : MantenedorBeneficios
    Created on : 29-01-2021, 02:39:06
    Author     : Sebastian Groselj
    Edit       : Lucas Aedo
    Date       : 04-06-2021
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
            <h1>Mantenedor de Beneficios</h1>

            <!-- Inicio Contenido Modal  -->
            <button type="button" class="myBtn btn btn-primary" href="#myModal">Agregar</button>

            <div id="myModal" class="modal" style="width:100%;"> 
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <form method="POST" action="MantenedorBeneficios" style="text-align: center;">
                        <input type="hidden" name="accion" value="insertar" />
                        <h3>Agregar nuevo Beneficio</h3>
                        <br />
                        <label>  Nombre: </label>
                        <input type="text" name="nombreBe" placeholder="Ingresar nombre...." required />
                        <br />
                        <label>  Tipo de beneficio: </label>

                        <select name="id_tipo"> <option value="2">Convenio</option> <option value="1">Beca</option> </select>        
                        <br />                    
                        <label>Activar beneficio: </label>
                        <select name="bool"> <option value="1">Activar</option> <option value="2">No activar</option> </select>
                        <br />
                        <br />
                        <button type="submit" class="btn btn-success"> Asignar nuevo Beneficio </button>
                        <script>
                            if (window.history.replaceState) {
                                window.history.replaceState(null, null, window.location.href);
                            }
                        </script>

                    </form>
                </div>
            </div>
            <!-- Fin Modal -->

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Vigente</th>
                        <th scope="col">Porcentajes</th>
                        <th scope="col" style="position:relative; left:70px;">Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <!--Datos cargar un beneficio-->
                    <c:forEach var="reg" items="${lista_beneficios}" varStatus="status">
                        <!-- link de los beneficios con su campo clave-->
                        <c:url var="beneTemp" value="MantenedorBeneficios">
                            <c:param name="accion" value="cargar"></c:param>
                            <c:param name="id" value="${reg.getId()}"></c:param>

                        </c:url>

                        <!--Datos para eliminar un beneficio-->
                        <!-- link de los beneficios con su campo clave-->
                        <c:url var="beneTempEliminar" value="MantenedorBeneficios">
                            <c:param name="accion" value="eliminar"></c:param>
                            <c:param name="id_bene" value="${reg.getId()}"></c:param>

                        </c:url>
                        <tr>
                            <th scope="row">${reg.getId()}</th>
                            <td>${reg.getNombre()}</td>
                            <td>${reg.getNombre_tipo()}</td>
                            <td><input type="checkbox" aria-label="Vigente" ${reg.isVigente()?"checked":""} onchange="ActualizarVigencia(${reg.getId()}, ${!reg.isVigente()})">
                                Vigente</td>
                            <td><button type="button" class="btn btn-primary" onclick="location.href = 'MantenedorPorcentajes?id_beneficio=${reg.getId()}';">Porcentajes</button> </td>
                            <td><a class="myBtn btn btn-primary" href="${beneTemp}">Modificar</a>&nbsp;&nbsp;<a class="myBtn btn btn-danger" href="${beneTempEliminar}" onclick="myFunctionEliminar()">Eliminar</a></td>

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
        <script>
            function myFunctionEliminar() {
                confirm("¿Está seguro que desea elminar el beneficio?");
            }
        </script>
        <script src="js/mantenedorBeneficios.js"></script>
        <script src="js/mantenedorPorcentajes.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>

        <!-- Inicio script Modal  --> 
        <script>
            // get botón que abre el modal
            var btn = document.querySelectorAll("button.myBtn");

            // Para todo el modal
            var modals = document.querySelectorAll('.modal');

            // Cierra el modal
            var spans = document.getElementsByClassName("close");

            // Abre el modal
            for (var i = 0; i < btn.length; i++) {
                btn[i].onclick = function (e) {
                    e.preventDefault();
                    modal = document.querySelector(e.target.getAttribute("href"));
                    modal.style.display = "block";
                }
            }
            //Cuando se hace click en (X) se cierra
            for (var i = 0; i < spans.length; i++) {
                spans[i].onclick = function () {
                    for (var index in modals) {
                        if (typeof modals[index].style !== 'undefined')
                            modals[index].style.display = "none";
                    }
                }
            }
            // Cuando se hacer click fuera del modal se cierrra
            window.onclick = function (event) {
                if (event.target.classList.contains('modal')) {
                    for (var index in modals) {
                        if (typeof modals[index].style !== 'undefined')
                            modals[index].style.display = "none";
                    }
                }
            }
        </script>
    </body>
</html>