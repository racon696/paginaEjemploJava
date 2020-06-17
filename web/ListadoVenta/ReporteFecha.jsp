<%-- 
    Document   : ReporteFecha
    Created on : 10-07-2019, 15:56:34
    Author     : Fox
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dao.VentaDAO"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Venta"%>
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
    </head>
    <body>

        <!--Dropdown structure-->

        <ul id="opciones" class="dropdown-content">
            <li><a href="../CerrarSesion.jsp">Cerrar Sesion</a></li>
            <li class="divider"></li>
            <li><a href="../Reportes.jsp">Volver a la seccion de reportes</a></li>
            <li><a href="../TipoDeUsuarios/Administrador.jsp">Menu principal</a></li>
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

        <div class="container">
            <a href="../FormVentas.jsp" class="left select-label">
                <label class="btn-floating btn-large waves-effect waves-light red">
                    <i class="material-icons">add</i>
                </label>
                
                <label class="black-text">Agregar venta</label>
            </a>
            <h3 class="center">Listado de ventas por fecha</h3>

        </div>
        <div class="container">
            <table class="striped">
                <thead>
                    <tr>
                        <th>folio de venta</th>
                        <th>fecha</th>
                        <th>hora</th>
                        <th>nroboleta</th>
                        <th>id usuario</th>
                        <th>id cliente</th>
                        <th>id producto</th>
                        <th>cantidad</th>
                        <th>total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        VentaDAO vdao = new VentaDAO();
                        ArrayList<Venta> ventas = vdao.obtenerDatos();
                        if (ventas != null) {
                            for (Venta v : ventas) {
                                out.println("<tr>");
                                out.println("<td>" + v.getFolioventa() + "</td>");
                                out.println("<td>" + v.getFecha() + "</td>");
                                out.println("<td>" + v.getHora() + "</td>");
                                out.println("<td>" + v.getNroboleta() + "</td>");
                                out.println("<td>" + v.getIdUsuario() + "</td>");
                                out.println("<td>" + v.getIdCliente() + "</td>");
                                out.println("<td>" + v.getIdProducto() + "</td>");
                                out.println("<td>" + v.getCantidad() + "</td>");
                                out.println("<td>" + v.getTotal() + "</td>");
                                out.println("</tr>");
                            }
                        }
                    %>
                </tbody>


                <script>
                    $(document).ready(function () {
                        $(".dropdown-trigger").dropdown();
                    });
                </script> 
                <!--JavaScript at end of body for optimized loading-->
                <script type="text/javascript" src="../js/materialize.min.js"></script>
                </body>
                </html>






















