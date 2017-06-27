<%-- 
    Document   : Proveedor
    Created on : 28-abr-2016, 15:37:58
    Author     : lab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantencion de Calidades</title>
    </head>
     
      <form method="post" action="SrvProveedores">
       <body>

        <table align="center" width="289" border="1" class="datos_form">
          <tr class=titulo_tabla><td colspan=2>MANTENCION DE PROVEEDORES </td></tr>
            <tr>
               <td>Rut</td>
               <td><input type ="text" name="PROVRUT" class="texto"></td>
                
            </tr>
            <tr>
                <td>Razon</td>
                <td><input type ="text" name="PROVRAZON" class="texto"></td>
            </tr>
            <tr>
                <td>Ciudad</td>
                <td><input type ="text" name="PROVCIUDAD" class="texto"></td>
            </tr>
            <tr>
                <td>Region</td>
                <td><input type ="text" name="PROVREGION" class="texto"></td>
            </tr>
            <tr>
                <td>Direccion</td>
                <td><input type ="text" name="PROVDIRECCION" class="texto"></td>
            </tr>
            

        </table>
           <table align="center">
               <tr></tr>
               <tr>  
                <td><input type ="submit" name="Boton" value="Agregar" class="texto"></td>
                <td><input type ="submit" name="Boton" value="Actualizar" class="texto"></td>
                <td><input type ="submit" name="Boton" value="Eliminar" class="texto"></td>
                <td><input type ="submit" name="Boton" value="Listado" class="texto"></td>
            </tr>
               
           </table>
       </body>
      </form>
</html>
