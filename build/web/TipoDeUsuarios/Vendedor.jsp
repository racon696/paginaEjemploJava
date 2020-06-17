<%-- 
    Document   : Vendedor
    Created on : 09-07-2019, 17:11:40
    Author     : Fox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String nombre = "";
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null) {
        response.sendRedirect("index.jsp");
    } else {
        nombre = u.getNombre() + " " + u.getApellidoP() + " " + u.getApellidoM();
    }
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="../css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../Estilo/StyleVendedor.css">
    </head>
    <body class="FondoVendedor">
        
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

                    <li><a href="../FormVentaReal.jsp">Realizar Venta</a></li>

                    <!-- Dropdown Trigger -->
                    <li><a class="dropdown-trigger" href="#!" data-target="opciones"><c:out value="${sessionScope.usuario.nombre}"></c:out><i class="material-icons right">arrow_drop_down</i></a></li>
                </ul>
            </div>
        </nav>
        
        
        <script>
            $(document).ready(function () {
                $(".dropdown-trigger").dropdown();
            });
        </script> 
        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="../js/materialize.min.js"></script>
    </body>
</html>
