<%-- 
    Document   : Reportes
    Created on : 10-07-2019, 14:40:53
    Author     : Fox
--%>

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
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="Estilo/StyleAdministrador.css">
    </head>
    <body class="FondoAdministrador">
        
        <!--Dropdown structure-->
        
        <ul id="opciones" class="dropdown-content">
            <li><a href="TipoDeUsuarios/Administrador.jsp">Volver al menu principal</a></li>
            <li class="divider"></li>
            <li><a href="#!">Cancelar</a></li>
        </ul>
        
        <nav>
            <div class="nav-wrapper gray darken-1">
                <a href="#!" class="brand-logo">Somos pulentos</a>
                <ul class="right hide-on-med-and-down">

                    <li><a href="ReporteFecha.jsp">Reporte general</a></li>
                    <li><a href="FormBuscarFecha.jsp">Buscar fecha especifica</a></li>
                    <li><a href="FormRangoFecha.jsp">Buscar rango de fecha</a></li>

                    <!-- Dropdown Trigger -->
                    <li><a class="dropdown-trigger" href="#!" data-target="opciones"><%=nombre%><i class="material-icons right">arrow_drop_down</i></a></li>
                </ul>
            </div>
        </nav>
        
        
        <script>
            $(document).ready(function () {
                $(".dropdown-trigger").dropdown();
            });
        </script> 
        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>