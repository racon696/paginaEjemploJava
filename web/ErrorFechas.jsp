<%-- 
    Document   : ErrorFechas
    Created on : 15-07-2019, 2:18:25
    Author     : Fox
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    </head>
    <body>

        <%
            HttpSession sesion = request.getSession();

            String msgfC = (String) sesion.getAttribute("servletMsgfC");
            String msgfT= (String) sesion.getAttribute("servletMsgfT");

            String nombre = "";
            Usuario u = (Usuario) sesion.getAttribute("usuario");
            if (u == null) {

            } else {
                nombre = u.getNombre() + " " + u.getApellidoP() + " " + u.getApellidoM();
            }


        %>

        <!--Dropdown structure-->

        <ul id="opciones" class="dropdown-content">
            <li><a href="CerrarSesion.jsp">Cerrar Sesion</a></li>
            <li class="divider"></li>
            <li><a href="Reportes.jsp">Volver a la seccion de reportes</a></li>
            <li><a href="TipoDeUsuarios/Administrador.jsp">Menu principal</a></li>
            <li><a href="#!">Cancelar</a></li>
        </ul>

        <nav>
            <div class="nav-wrapper gray darken-1">
                <a href="#!" class="brand-logo">Somos pulentos</a>
                <ul class="right hide-on-med-and-down">



                    <!-- Dropdown Trigger -->
                    <li><a class="dropdown-trigger" href="#!" data-target="opciones"><%=nombre%><i class="material-icons right">arrow_drop_down</i></a></li>
                </ul>
            </div>
        </nav>
    <center><h3><%=msgfT%></h3></center>
    <div class="container">
        <a href="FormVentas.jsp" class="left select-label">
            <label class="btn-floating btn-large waves-effect waves-light red">
                <i class="material-icons">add</i>
            </label>

            <label class="black-text">Agregar venta</label>
        </a>
    </div>
</div><br>
<di>
    <br>
</di>
<di>
    <br>
</di>
<div class="container">
    <center><table class="striped">
        <tr>
            <td>
                <h1><%=msgfC%></h1>
            </td>
        </tr>
        </table></center>
</div>
<script>
    $(document).ready(function () {
        $(".dropdown-trigger").dropdown();
    });
</script> 
<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
</html>
