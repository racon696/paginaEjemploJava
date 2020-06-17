<%-- 
    Document   : FormProductos
    Created on : 10-07-2019, 14:36:33
    Author     : Fox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../Estilo/StyleAdministrador.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Producto</title>

    </head>
    <body>

        <!--Dropdown structure-->

        <ul id="opciones" class="dropdown-content">
            <li><a href="CerrarSesion.jsp">Cerrar Sesion</a></li>
            <li class="divider"></li>
            <li><a href="#!">Cancelar</a></li>
        </ul>

        <nav>
            <div class="nav-wrapper gray darken-1">
                <a href="#!" class="brand-logo">Somos pulentos</a>
                <ul class="right hide-on-med-and-down">


                    <li><a href="FormCliente.jsp">Cliente Crud</a></li>
                    <li><a href="FormUsuario.jsp">Usuario Crud</a></li>
                    
                    <li><a href="FormVentas.jsp">Venta Crud</a></li>
                    <li><a href="Reportes.jsp">Reportes</a></li>
                    <li><a href="FormVentaReal.jsp">Vender</a></li>



                    <li> <a class="dropdown-trigger" href="#!" data-target="opciones" ><c:out value="${sessionScope.usuario.nombre}"></c:out> <i class="material-icons right">arrow_drop_down</i></a></li>
                    </ul>
                </div>
            </nav>

            <form action="${pageContext.request.contextPath}/ControladorProducto" method="POST" >
            <div class="row">
                <div class="col s8 offset-s2">
                    <table>
                        <tr>
                            <td>Id Producto:</td><td> <input   type="Text" name="id_producto" value="1"></input></td>
                        </tr>
                        <tr>
                            <td>Nombre:</td><td><input type="Text" name="nombre"></input></td>
                        </tr>
                        <tr>
                            <td>Precio:</td><td><input   type="Text" name="precio" value="0"></input></td>
                        </tr>
                        <tr>
                            <td>Stock:</td><td><input   type="Text" name="stock" value="0"></input></td>
                        </tr>



                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Buscar"   name="opcion"></input> 
                                <input type="submit" value="Agregar"  name="opcion"></input>
                                <input type="submit" value="Listar"   name="opcion"></input>
                            </td>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>

        <script>
            $(document).ready(function () {
                $(".dropdown-trigger").dropdown();

            });
        </script> 
        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="js/materialize.min.js"></script>    
    </body>
</html>
