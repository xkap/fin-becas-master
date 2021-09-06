/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

actualizarPorcentajesSegunEstado('inputPorcRoot', 'inputPorc', 'inputState');

function actualizarPorcentajesSegunEstado(id_select_porcentaje, id_select_porcentaje_input, id_select_estado){
    var elemento_porc = document.getElementById(id_select_porcentaje);
    var elemento_estado = document.getElementById(id_select_estado);
    var elemento_porcInput = document.getElementById(id_select_porcentaje_input);
    
    if (elemento_estado.value != 3){
        elemento_porc.hidden = true;
        elemento_porcInput.required = false;
    }
    else{
        elemento_porc.hidden = false;
        elemento_porcInput.required = true;
    }
    
}

function ocultarModificadorReg(id_modificador){
    var elemento_mod = document.getElementById(id_modificador);
    elemento_mod.hidden = true;
}