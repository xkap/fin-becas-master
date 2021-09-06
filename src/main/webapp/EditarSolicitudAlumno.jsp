<%@page import="java.util.Set"%>
<%@page import="web.Contenedor"%>
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <script defer src="js/all.js"></script> 
        <title>DUOC UC - IVARAS - Editar Solicitud</title>        
    </head>
    
    <body>
        <jsp:include page="Encabezado.jsp"/>
        <%            
            
            Solicitud unaSoli = null;
            Log Log = new Log();
            ProjectDAO Pdao = new ProjectDAO();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            int id = Integer.parseInt(request.getParameter("id"));
            int ver = Integer.parseInt(request.getParameter("ver"));
            conn = Conexion.Conectar();
            stmt = conn.prepareStatement("SELECT * FROM solicitud s join usuario u on (s.id_alumno = u.id_usuario) "
                                                                 + "join tipo_solicitud tsoli on (s.id_tipo_solicitud = tsoli.id_tipoS) "
                                                                 + "join estado on(s.id_estado=estado.id_estado)"
                                                                 + "join beneficio b on (s.id_beneficio = b.id_beneficio)"
                                                                 + "where id_formulario="+id);
            rs = stmt.executeQuery();
            while(rs.next()){                
        %>
        <div class="container">
            <form class="col-md-12 align-buttom"id="contact" action="Contenedor?menu=EditarAlumno" method="POST" enctype="multipart/form-data">
                <a type="button" class="btn btn-outline-dark" href="VerSolicitudAlumno.jsp">Volver</a>
                <h1>Modificar registro</h1>        
                
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">ID Solicitud:</label>
                    <div class="col-sm-10">
                        <input name="id_formulario" id="id_formulario" class="form-control" type="text" class="form-control-plaintext" value="<%=rs.getInt("id_formulario")%>" readonly>
                    </div>                                    
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Fecha de solicitud:</label>  
                    <div class="col-sm-10">
                        <input name="fecha_soli" id="fecha_soli" class="form-control" type="Date" readonly="" value="<%=rs.getDate("fecha_solicitud")%>">  
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Año de ingreso: </label>  
                    <div class="col-sm-10">
                        <input name="anio_ingreso" id="anio_ingreso" class="form-control" type="text" maxlength="4" pattern="\d{4}" name="txtAnio_ingreso" value="<%=rs.getInt("anio_ingreso")%>" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Semestre: </label>  
                    <div class="col-sm-10">
                        <input name="semestre" id="semestre" class="form-control" type="text" readonly="" value="<%=rs.getString("semestre")%>">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Año de egreso: </label>  
                    <div class="col-sm-10">
                        <input name="anio_egreso" id="anio_egreso" class="form-control" type="number" maxlength="4" pattern="\d{4}"  name="txtAnio_egreso" value="<%=rs.getInt("anio_egreso")%>">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Rut 2do hermano: </label> 
                    <div class="col-sm-10">
                        <input name="r2_hermano" id="r2_hermano" class="form-control" type="text" readonly="" value="<%=rs.getString("r2_hermano")%>">                       
                    </div>
                </div> 

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Rut 3er hermano: </label> 
                    <div class="col-sm-10">
                        <input name="r3_hermano" id="r3_hermano" class="form-control" type="text" readonly="" value="<%=rs.getString("r3_hermano")%>">                          
                                        
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Última modificación: </label>
                    <div class="col-sm-10">
                        <input name="fecha_update" id="fecha_update" class="form-control" type="Date" readonly="" value="<%=rs.getDate("fecha_update")%>">                
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Alumno: </label>  
                    <div class="col-sm-10">
                        <input name="nombre_completo" id="nombre_completo" class="form-control" type="text" readonly="" value="<%=rs.getString("nombre_completo")%>">                       
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Estado de la solicitud: </label>  
                    <div class="col-sm-10">
                        <input name="nombreE" id="nombreE" class="form-control" type="text" readonly="" value="<%=rs.getString("nombreE")%>">
                        <input hidden="true" name="idE" id="IdE" class="form-control" type="text" readonly="" value="<%=rs.getString("nombreE")%>">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Beneficio: </label>  
                    <div class="col-sm-10">
                        <input name="nombreBe" id="nombreBe" class="form-control" type="text" readonly="" value="<%=rs.getString("nombreBe")%>">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Tipo de solicitud: </label>  
                    <div class="col-sm-10">
                        <input name="nombreS" id="nombreS" class="form-control" type="text" readonly="" value="<%=rs.getString("nombreS")%>">
                    </div>
                </div>      
                
                <div class="form-group row divfile " >
                    <label class="col-sm-2 col-form-label">Archivos: </label>  
                    
                        <div class="col-sm-4">
                            <input class="file-upload__input" type="file" name="archivo" id="archivo" accept=".pdf,.rar,image/*,.xlsx,.docx" tabindex="13" multiple value="<%=rs.getBlob("archivo")%>">
                        </div>
                        <div class="form-group col-sm-4 divfile ">
                        <c:if test="${mostrar.getArchivos()}" >
                            <% if (rs.getString("nombre_archivo") == null){
                                %> <label class="">No existen archivos cargados </label> <%
                            }else{
                                %><a href="Contenedor?menu=BuscarArchivo&id=<%=rs.getString("id_formulario")%>" target="_blank"><%=rs.getString("nombre_archivo")%></a><%
                            }%>
                        </c:if>
                        </div> 
                </div>                
                <fieldset>
                    <% if (ver==1){
                        %><button disabled type="button" class="btn btn-secondary btn-lg btn-block disabled" aria-disabled="true" data-toggle="modal" data-target="#confirmacion" >
                                Actualizar
                          </button><%
                    }else{
                        %><button type="button" class="btn btn-success btn-lg btn-block" data-toggle="modal" data-target="#confirmacion">
                        Actualizar
                        </button><%
                    }%>
                    
                    <div class="modal fade" id="confirmacion" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title" id="confirmacionLabel">Confirmar cambios</h5>
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                              </button>
                            </div>
                            <div class="modal-body">
                              ¿Está seguro de confirmar cambios?
                              Una vez enviada la actualización no podrás volver a realizar cambios
                            </div>
                            <div class="modal-footer">                              
                                  <button class="btn btn-success" type="submit" id="contact-submit" submit="...Sending">Actualizar</button>                                
                            </div>
                          </div>
                        </div>
                      </div>  
                </fieldset>
                        <!--<!-- Los siguentes inputs están invisibles, son para cargar el log en la bdd -->
                <input hidden="true" name="id_estado" id="id_estado" value="<%=rs.getString("id_estado")%>">
                <input hidden="true" name="id_beneficio" id="id_beneficio" value="<%=rs.getString("id_beneficio")%>">
                <input hidden="true" name="id_tipo_solicitud" id="id_tipo_solicitud" value="<%=rs.getString("id_tipo_solicitud")%>">
                <input hidden="true" name="email" id="email" value="<%=rs.getString("email")%>">
            </form>
            <!-- Button trigger modal -->
            
                   
            <% 
                }
            %>

        </div>
    </body>    
    
</html>
