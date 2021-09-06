<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">

        <!-- Required meta tags -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/js/bootstrap.min.js"></script>

        <!-- Datatables -->
        <link rel="stylesheet" href="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.18/datatables.min.css">
        <script src="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.18/datatables.min.js"></script>

        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.3/css/bootstrap.min.css" integrity="sha512-oc9+XSs1H243/FRN9Rw62Fn8EtxjEYWHXRvjS43YtueEewbS6ObfXcJNyohjHqVKFPoXXUxwc+q1K7Dee6vv9g==" crossorigin="anonymous" />

        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.bootstrap4.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.flash.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.html5.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.print.min.js"></script>

    </head>
    <body class="container-fluid p-5">

        <div class="table-responsive" id="mydatatable-container" >
            <table class="records_list table table-striped table-bordered table-hover" id="example">
                <thead>
                    <tr>
                        <th>Id solicitud</th>
                        <th>Rut</th>
                        <th>Nombre</th>
                        <th>Carrera</th>
                        <th>Beneficio</th>
                        <th>%de beneficio</th>
                        <th>Fecha de solicitud</th>
                        <th>Fecha de aplicación</th>
                        <th>Responsable</th>
                        <th>Estado de aplicación </th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Filter..</th>

                    </tr>
                </tfoot>
                <tbody>
                    <c:forEach var="re" items="${reportes}"  varStatus="status"> 
                        <tr>
                            <td>${re.getId_formulario()}</td>
                            <td>${re.getRut_alumno()}</td>
                            <td>${re.getNombre()}</td>
                            <td>${re.getCarrera()}</td>
                            <td>${re.getNombreBe()}</td>
                            <td>${re.getNombreP}"</td>
                            <td>${re.getFecha_solicitud()}</td>
                            <td>${re.getFecha_update()}</td>
                            <td>${re.getNombreF()}</td>
                            <td>${re.getNombreE()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <style>
            #example tfoot input{
                width: 100% !important;
            }
            #example tfoot {
                display: table-header-group !important;
            }
        </style>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#example tfoot th').each(function () {
                    var title = $(this).text();
                    $(this).html('<input type="text" placeholder="Filtrar.." />');
                });

                var table = $('#example').DataTable({
                    "dom": 'B<"float-left"i><"float-right"f>t<"float-left"l><"float-right"p><"clearfix">',
                    "responsive": false,
                    "language": {
                        "url": "https://cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
                    },
                    "order": [[0, "desc"]],
                    "initComplete": function () {
                        this.api().columns().every(function () {
                            var that = this;

                            $('input', this.footer()).on('keyup change', function () {
                                if (that.search() !== this.value) {
                                    that
                                            .search(this.value)
                                            .draw();
                                }
                            });
                        })
                    }
                });
            });
        </script>

        <script>
            $(document).ready(function () {
                var table = $('#example').DataTable({
                    lengthChange: false,
                    buttons: ['excel', 'pdf']
                });

                table.buttons().container()
                        .appendTo('#example_wrapper .col-md-6:eq(0)');
            });
        </script>

    </body>
</html>