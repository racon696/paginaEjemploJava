<%-- 
    Document   : ListaProductos
    Created on : 12-07-2019, 16:51:16
    Author     : Racon
--%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql"   uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix = "fn"  uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Productos</title>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="../css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../Estilo/StyleAdministrador.css">
    </head>
    <body>

        <sql:setDataSource
            driver="com.mysql.jdbc.Driver"
            url="jdbc:mysql://localhost:3306/jpa"
            user="root"
            password=""/>

        <sql:query var="productos">
            SELECT * FROM producto
        </sql:query>


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

            <h1>Editar Producto</h1>
            <br><br>
            <div class="row"> 
                <div class="col s8 offset-s2"> 
                    <div class="container white">
                        <table class="highlight-4 responsive-table centered">
                            <thead >
                                <tr>
                                    <th>Id</th>
                                    <th>Nombre</th>
                                    <th>Precio</th>
                                    <th>Stock</th>
                                </tr>
                            </thead>
                            <tbody>

                            <c:set var="context" value="${pageContext.request.contextPath}" />
                            <c:forEach var = "row" items = "${productos.rows}">
                                <tr>
                                    <td> <c:out value = "${row.id_producto}"/></td>
                                    <td> <c:out value = "${row.nombre}"/></td>
                                    <td> <c:out value = "${row.precio}"/></td>
                                    <td> <c:out value = "${row.stock}"/></td>
                                    <td><form action="<c:out value="${pageContext.request.contextPath}"></c:out>/ControladorProducto" method="POST" ><input type="hidden" name="id_producto" value="<c:out value="${row.id_producto}"></c:out>"></input><input type="submit" value="Buscar" name="opcion"></input></form></td>

                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>            
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $(".dropdown-trigger").dropdown();
            });
        </script> 
        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="../js/materialize.min.js"></script>
    </body>
</html>
