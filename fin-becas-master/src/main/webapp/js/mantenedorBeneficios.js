/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ActualizarVigencia(id_ben, is_check)
{
    window.location.href = "MantenedorBeneficios?accion=vigencia&id="+id_ben+"&bool="+is_check;
}
