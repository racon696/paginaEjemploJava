<%-- 
    Document   : CerrarSesion
    Created on : 09-07-2019, 18:47:36
    Author     : Fox
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    HttpSession sesion = request.getSession();
    String nombre = "";
    Usuario u = (Usuario) sesion.getAttribute("usuario");
    if (u == null) {
        response.sendRedirect("index.jsp");
    } else {
        nombre = u.getNombre() + " " + u.getApellidoP() + " " + u.getApellidoM();
    }

    sesion.invalidate();
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
        <link rel="stylesheet" href="Estilo/StyleCerrarSesion.css">
    </head>
    <body class="FondoCerrarSesion">

        <div class="row">
            <div class="col s12 m6">
                <div class="card blue lighten-1">
                    <div class="card-content white-text">
                        <span class="card-title">Sesión Cerrada</span>
                        <p> !Todo Listo! <%=nombre%>, tu sesión ha sido cerrada exitosamente.</p>
                    </div>
                    <div class="card-action">
                        <a href="index.jsp" style="color: #000000" >Volver al Inicio </a>

                    </div>
                </div>
            </div>
        </div>

        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
