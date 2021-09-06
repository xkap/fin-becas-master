<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    </body>
    <script type="text/javascript">
        function checkRut(rut) {
            // Despejar Puntos
            var valor = rut.value.replace('.', '');
            // Despejar Guión
            valor = valor.replace('-', '');

            // Aislar Cuerpo y Dígito Verificador
            cuerpo = valor.slice(0, -1);
            dv = valor.slice(-1).toUpperCase();

            // Formatear RUN
            rut.value = cuerpo + '-' + dv

            // Si no cumple con el mínimo ej. (n.nnn.nnn)
            if (cuerpo.length < 7) {
                rut.setCustomValidity("RUT Incompleto");
                return false;
            }

            // Calcular Dígito Verificador
            suma = 0;
            multiplo = 2;

            // Para cada dígito del Cuerpo
            for (i = 1; i <= cuerpo.length; i++) {

                // Obtener su Producto con el Múltiplo Correspondiente
                index = multiplo * valor.charAt(cuerpo.length - i);

                // Sumar al Contador General
                suma = suma + index;

                // Consolidar Múltiplo dentro del rango [2,7]
                if (multiplo < 7) {
                    multiplo = multiplo + 1;
                } else {
                    multiplo = 2;
                }

            }

            // Calcular Dígito Verificador en base al Módulo 11
            dvEsperado = 11 - (suma % 11);

            // Casos Especiales (0 y K)
            dv = (dv == 'K') ? 10 : dv;
            dv = (dv == 0) ? 11 : dv;

            // Validar que el Cuerpo coincide con su Dígito Verificador
            if (dvEsperado != dv) {
                rut.setCustomValidity("RUT Inválido");
                return false;
            }

            // Si todo sale bien, eliminar errores (decretar que es válido)
            rut.setCustomValidity('');
        }
        function checkRut(hermano1) {
            // Despejar Puntos
            var valor = hermano1.value.replace('.', '');
            // Despejar Guión
            valor = valor.replace('-', '');

            // Aislar Cuerpo y Dígito Verificador
            cuerpo = valor.slice(0, -1);
            dv = valor.slice(-1).toUpperCase();

            // Formatear RUN
            hermano1.value = cuerpo + '-' + dv

            // Si no cumple con el mínimo ej. (n.nnn.nnn)
            if (cuerpo.length < 7) {
                hermano1.setCustomValidity("RUT Incompleto");
                return false;
            }

            // Calcular Dígito Verificador
            suma = 0;
            multiplo = 2;

            // Para cada dígito del Cuerpo
            for (i = 1; i <= cuerpo.length; i++) {

                // Obtener su Producto con el Múltiplo Correspondiente
                index = multiplo * valor.charAt(cuerpo.length - i);

                // Sumar al Contador General
                suma = suma + index;

                // Consolidar Múltiplo dentro del rango [2,7]
                if (multiplo < 7) {
                    multiplo = multiplo + 1;
                } else {
                    multiplo = 2;
                }

            }

            // Calcular Dígito Verificador en base al Módulo 11
            dvEsperado = 11 - (suma % 11);

            // Casos Especiales (0 y K)
            dv = (dv == 'K') ? 10 : dv;
            dv = (dv == 0) ? 11 : dv;

            // Validar que el Cuerpo coincide con su Dígito Verificador
            if (dvEsperado != dv) {
                hermano1.setCustomValidity("RUT Inválido");
                return false;
            }

            // Si todo sale bien, eliminar errores (decretar que es válido)
            hermano1.setCustomValidity('');
        }
        function checkRut(hermano3) {
            // Despejar Puntos
            var valor = hermano3.value.replace('.', '');
            // Despejar Guión
            valor = valor.replace('-', '');

            // Aislar Cuerpo y Dígito Verificador
            cuerpo = valor.slice(0, -1);
            dv = valor.slice(-1).toUpperCase();

            // Formatear RUN
            hermano3.value = cuerpo + '-' + dv

            // Si no cumple con el mínimo ej. (n.nnn.nnn)
            if (cuerpo.length < 7) {
                hermano3.setCustomValidity("RUT Incompleto");
                return false;
            }

            // Calcular Dígito Verificador
            suma = 0;
            multiplo = 2;

            // Para cada dígito del Cuerpo
            for (i = 1; i <= cuerpo.length; i++) {

                // Obtener su Producto con el Múltiplo Correspondiente
                index = multiplo * valor.charAt(cuerpo.length - i);

                // Sumar al Contador General
                suma = suma + index;

                // Consolidar Múltiplo dentro del rango [2,7]
                if (multiplo < 7) {
                    multiplo = multiplo + 1;
                } else {
                    multiplo = 2;
                }

            }

            // Calcular Dígito Verificador en base al Módulo 11
            dvEsperado = 11 - (suma % 11);

            // Casos Especiales (0 y K)
            dv = (dv == 'K') ? 10 : dv;
            dv = (dv == 0) ? 11 : dv;

            // Validar que el Cuerpo coincide con su Dígito Verificador
            if (dvEsperado != dv) {
                hermano3.setCustomValidity("RUT Inválido");
                return false;
            }

            // Si todo sale bien, eliminar errores (decretar que es válido)
            hermano3.setCustomValidity('');
        }
        function checkRut(rut) {
            // Despejar Puntos
            var valor = rut.value.replace('.', '');
            // Despejar Guión
            valor = valor.replace('-', '');

            // Aislar Cuerpo y Dígito Verificador
            cuerpo = valor.slice(0, -1);
            dv = valor.slice(-1).toUpperCase();

            // Formatear RUN
            rut.value = cuerpo + '-' + dv

            // Si no cumple con el mínimo ej. (n.nnn.nnn)
            if (cuerpo.length < 7) {
                rut.setCustomValidity("RUT Incompleto");
                return false;
            }

            // Calcular Dígito Verificador
            suma = 0;
            multiplo = 2;

            // Para cada dígito del Cuerpo
            for (i = 1; i <= cuerpo.length; i++) {

                // Obtener su Producto con el Múltiplo Correspondiente
                index = multiplo * valor.charAt(cuerpo.length - i);

                // Sumar al Contador General
                suma = suma + index;

                // Consolidar Múltiplo dentro del rango [2,7]
                if (multiplo < 7) {
                    multiplo = multiplo + 1;
                } else {
                    multiplo = 2;
                }

            }

            // Calcular Dígito Verificador en base al Módulo 11
            dvEsperado = 11 - (suma % 11);

            // Casos Especiales (0 y K)
            dv = (dv == 'K') ? 10 : dv;
            dv = (dv == 0) ? 11 : dv;

            // Validar que el Cuerpo coincide con su Dígito Verificador
            if (dvEsperado != dv) {
                rut.setCustomValidity("RUT Inválido");
                return false;
            }

            // Si todo sale bien, eliminar errores (decretar que es válido)
            rut.setCustomValidity('');
        }

    </script>
    
    <script type="text/javascript">

    function cargarBecaconve() {
    		    // Objeto de provincias con pueblos
    		    var listaBecaconve = {
    		      beca: ["BECA DEPORTIVA",
    		    	    "BECA SUMATE",
    	    		    "BECA SEDE",
    	    		    "BECA SEGUNDO HERMANO",
    	    		    "BECA TERCER HERMANO",
    	    		    "BECA POM",
    	    		    "BECA LICEO POLITECNICO ANDES"],
    		      convenio: [
    	    		    	"CONVENIO ARMADA",
    	    		    	"CONVENIO ATENTO CHILE S.A.",
    	    		    	"CONVENIO BIENESTAR FUERZA AEREA CHILE",
    	    		   	"CONVENIO CENCOSUD",
    	    		    	"CONVENIO EJERCITO DE CHILE",
    	    		    	"CONVENIO UNIVERSIDAD CATOLICA",
    	    		    	"CONVENIO GRUPO SANTANDER"]
    		    }
    		    
    		    var beneficios = document.getElementById('tipo_solicitud_id_tipoS')
    		    var becaconves = document.getElementById('beneficio_id_beneficio')
    		    var beneficioSeleccionada = beneficios.value.toLowerCase()
    		    
    		    becaconves.innerHTML = '<option value="">Seleccione un Beneficio...</option>'
    		    
    		    if(beneficioSeleccionada !== ''){
    		      beneficioSeleccionada = listaBecaconve[beneficioSeleccionada]
    		      beneficioSeleccionada.sort()
    		    
    		      beneficioSeleccionada.forEach(function(beneficio_id_beneficio){
    		        let opcion = document.createElement('option')
    		        opcion.value = beneficio_id_beneficio
    		        opcion.text = beneficio_id_beneficio
    		        becaconves.add(opcion)
    		      });
    		    }
    		    
    		  }
    		
    		//cargarBeneficio();

    </script>
    
    <script>
                $("#beneficio_id_beneficio").change(function () {
                    if (this.value == "BECA SEGUNDO HERMANO") {
                        $(".DivSegundoHermano").attr("hidden", false);
                        $(".RutHermano").attr("hidden", false);
                        $(".divfile").attr("hidden", false);
                        $(".file-upload__input").attr("hidden", false);
                        $(".divaniodeegreso").attr("hidden", true);
                        $(".aniodeegreso").attr("hidden", true);
                        
                        $("#r2_hermano").attr("required", true);
                        $("#r3_hermano").attr("required", false);
                        $("#anio_egreso").attr("required", false);
                        $("#archivo").attr("required", true);
                        
                    } else if (this.value == "BECA LICEO POLITECNICO ANDES") {
                        $(".divaniodeegreso").attr("hidden", false);
                        $(".divfile").attr("hidden", false);
                        $(".file-upload__input").attr("hidden", false);
                        $(".DivSegundoHermano").attr("hidden", true);
                        $(".DivTercerHermano").attr("hidden", true);
                        $(".RutHermano").attr("hidden", true);
                        
                        $("#r2_hermano").attr("required", false);
                        $("#r3_hermano").attr("required", false);
                        $("#anio_egreso").attr("required", false);
                        $("#archivo").attr("required", true);
                        
                    } else if (this.value == "BECA POM") {
                        $(".divfile").attr("hidden", false);
                        $(".file").attr("hidden", false);
                        
                        $("#r2_hermano").attr("required", false);
                        $("#r3_hermano").attr("required", false);
                        $("#anio_egreso").attr("required", false);
                        $("#archivo").attr("required", true);
                        
                    } else if (this.value == "BECA TERCER HERMANO") {
                        $(".DivSegundoHermano").attr("hidden", false);
                        $(".DivTercerHermano").attr("hidden", false);
                        $(".RutHermano").attr("hidden", false);
                        $(".divfile").attr("hidden", false);
                        $(".file").attr("hidden", false);
                        $(".divaniodeegreso").attr("hidden", true);
                        $(".aniodeegreso").attr("hidden", true);
                        
                        $("#r2_hermano").attr("required", true);
                        $("#r3_hermano").attr("required", true);
                        $("#anio_egreso").attr("required", false);
                        $("#archivo").attr("required", true);
                        
                    } else if (this.value == "CONVENIO ARMADA") {
                        $(".divfile").attr("hidden", false);
                        $(".file-upload__input").attr("hidden", false);
                        $("#archivo").attr("required", true);
                    } else if (this.value == "CONVENIO ATENTO CHILE S.A.") {
                        $(".divfile").attr("hidden", false);
                        $(".file-upload__input").attr("hidden", false)
                        $("#archivo").attr("required", true);
                    } else if (this.value == "CONVENIO BIENESTAR FUERZA AEREA CHILE") {
                        $(".divfile").attr("hidden", false);
                        $(".file-upload__input").attr("hidden", false);
                        $("#archivo").attr("required", true);
                    } else if (this.value == "CONVENIO CENCOSUD") {
                        $(".divfile").attr("hidden", false);
                        $(".file-upload__input").attr("hidden", false);
                        $("#archivo").attr("required", true);
                    } else if (this.value == "CONVENIO EJERCITO DE CHILE") {
                        $(".divfile").attr("hidden", false);
                        $(".file-upload__input").attr("hidden", false);
                        $("#archivo").attr("required", true);
                    } else if (this.value == "CONVENIO UNIVERSIDAD CATOLICA") {
                        $(".divfile").attr("hidden", false);
                        $(".file-upload__input").attr("hidden", false);
                        $("#archivo").attr("required", true);
                    } else if (this.value == "CONVENIO GRUPO SANTANDER") {
                        $(".divfile").attr("hidden", false);
                        $(".file-upload__input").attr("hidden", false);
                        $("#archivo").attr("required", true);
                        
                    } else {
                        $(".divaniodeegreso").attr("hidden", true);
                        $(".aniodeegreso").attr("hidden", true);
                        $(".DivSegundoHermano").attr("hidden", true);
                        $(".DivTercerHermano").attr("hidden", true);
                        $(".RutHermano").attr("hidden", true);
                        $(".divfile").attr("hidden", true);
                        $(".file").attr("hidden", true);
                        
                        $("#r2_hermano").attr("required", false);
                        $("#r3_hermano").attr("required", false);
                        $("#anio_egreso").attr("required", false);
                        $("#archivo").attr("required", false);
                    }
                });
    </script>
       

</html>