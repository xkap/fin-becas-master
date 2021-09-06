/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ActualizarVigencia(id_porc, is_check)
{
    var dir = "MantenedorPorcentajes?accion=vigencia&id="+id_porc+"&bool="+is_check;
    
    var id_ben = getParameterByName('id_beneficio');
    if (id_ben != null)
        dir += "&id_beneficio="+id_ben;
    
    window.location.href = dir;
}


/**
 * Revisa cual es el valor de un parametro
 * @param String name
 * @return String
 */
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

