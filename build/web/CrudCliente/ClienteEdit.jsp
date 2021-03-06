<%-- 
    Document   : newjsp
    Created on : 12-07-2019, 16:49:31
    Author     : Racon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente Editable</title>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="../css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../Estilo/StyleAdministrador.css">
    </head>
    <body>

        <!--Dropdown structure-->

        <ul id="opciones" class="dropdown-content">
            <li><a href="../CerrarSesion.jsp">Cerrar Sesion</a></li>
            <li class="divider"></li>
            <li><a href="#!">Cancelar</a></li>
        </ul>

        <nav>
            <div class="nav-wrapper gray darken-1">
                <a href="#!" class="brand-logo">Somos pulentos</a>
                <ul class="right hide-on-med-and-down">


                    <li><a href="../FormCliente.jsp">Cliente Crud</a></li>
                    <li><a href="../FormUsuario.jsp">Usuario Crud</a></li>
                    <li><a href="../FormProductos.jsp">Producto Crud</a></li>
                    <li><a href="../FormVentas.jsp">Venta Crud</a></li>
                    <li><a href="../Reportes.jsp">Reportes</a></li>
                    <li><a href="../FormVentaReal.jsp">Vender</a></li>



                    <li> <a class="dropdown-trigger" href="#!" data-target="opciones" ><c:out value="${sessionScope.usuario.nombre}"></c:out> <i class="material-icons right">arrow_drop_down</i></a></li>
                    </ul>
                </div>
            </nav>

            <form action="${pageContext.request.contextPath}/ControladorCliente" method="POST">
            <div class="col s8 offset-s2"> 
                <table>
                    <tr>
                        <td>Id Cliente:</td><td> <input   type="Text" name="id_cliente" value="<c:out value="${sessionScope.clientebuscado.idCliente}"></c:out>"></input></td>
                        </tr>
                        <tr>
                            <td>Nombre:</td><td><input type="Text" name="nombre" value="<c:out value="${sessionScope.clientebuscado.nombre}"></c:out>"></input></td>
                        </tr>
                        <tr>
                            <td>Apellido Pat.:</td><td><input   type="Text" name="apellidoP" value="<c:out value="${sessionScope.clientebuscado.apellidoP}"></c:out>"></input></td>
                        </tr>
                        <tr>
                            <td>Apellido Mat.:</td><td><input   type="Text" name="apellidoM" value="<c:out value="${sessionScope.clientebuscado.apellidoM}"></c:out>"></input></td>
                        </tr>
                        <tr>
                            <td>Rut:</td><td><input   type="Text" name="rut" value="<c:out value="${sessionScope.clientebuscado.rut}"></c:out>"></input></td>
                    </tr>

                    <tr>
                        <td colspan="2">


                            <input type="submit" value="Actualizar" name="opcion"></input>
                            <input type="submit" value="Eliminar" name="opcion"></input>

                        </td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </form>

        <script>
            $(document).ready(function () {
                $(".dropdown-trigger").dropdown();
            });
        </script> 
        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="../js/materialize.min.js"></script>        
    </body>
</html>
