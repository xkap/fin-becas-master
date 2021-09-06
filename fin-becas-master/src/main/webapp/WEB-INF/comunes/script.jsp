<!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="js/custom.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>

        <script>
                    $(document).ready(function () {
                        $("#example").DataTable({
                            "language": {
                                "url": "https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"
                            }
                        });
                    });
        </script>