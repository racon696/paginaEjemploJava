<%-- 
    Document   : newjsp
    Created on : 12-07-2019, 16:49:31
    Author     : Gateway
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venta Editable</title>
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
            <br><br>
            <form class="col s12" action="${pageContext.request.contextPath}/ControladorVenta" method="POST" >
            <div class="row">
                <div class="col s8 offset-s2"> 

                    <div class="input-field col s12">
                        <input value="<c:out value="${sessionScope.ventabuscado.folioventa}"></c:out>" id="folioventa" name="folioventa" type="text" class="validate" >
                            <label for="folioventa">Folio Venta</label>
                        </div>
                        <br>

                        <div class="input-field col s12">
                            <input value="<c:out value="${sessionScope.ventabuscado.fecha}"></c:out>" id="fecha" name="fecha" type="text" class="validate">
                            <label for="fecha">Fecha</label>
                        </div>
                        <br>
                        <div class="input-field col s12">
                            <input value="<c:out value="${sessionScope.ventabuscado.hora}"></c:out>" id="hora" name="hora" type="text" class="validate">
                            <label for="hora">Hora</label>
                        </div>

                        <br>
                        <div class="input-field col s12">
                            <input value="<c:out value="${sessionScope.ventabuscado.nroboleta}"></c:out>" id="nroboleta" name="nroboleta" type="number" class="validate">
                            <label for="nroboleta">Nro Boleta</label>
                        </div>
                        <br>

                        <div class="input-field col s12">
                            <input value="<c:out value="${sessionScope.ventabuscado.idUsuario}"></c:out>" id="id_usuario" name="id_usuario" type="text" class="validate">
                            <label for="id_usuario">Usuario</label>
                        </div>    
                        <br>
                        <div class="input-field col s12">
                            <input value="<c:out value="${sessionScope.ventabuscado.idCliente}"></c:out>" id="id_cliente" name="id_cliente" type="text" class="validate">
                            <label for="id_cliente">Cliente</label>
                        </div> 
                        <br>
                        <div class="input-field col s12">
                            <input value="<c:out value="${sessionScope.ventabuscado.idProducto}"></c:out>" id="id_producto" name="id_producto" type="text" class="validate">
                            <label for="id_cliente">Producto</label>
                        </div> 
                        <br>
                        <div class="input-field col s12">
                            <input value="<c:out value="${sessionScope.ventabuscado.cantidad}"></c:out>" id="cantidad" name="cantidad" type="number" class="validate">
                            <label for="cantidad">Cantidad</label>
                        </div>
                        <br>
                        <div class="input-field col s12">
                            <input value="<c:out value="${sessionScope.ventabuscado.total}"></c:out>" id="total" name="total" type="number" class="validate">
                        <label for="total">Total</label>
                    </div>
                    <br>
                    <button class="btn waves-effect waves-light" type="submit" name="opcion" value="Eliminar">Eliminar
                        <i class="material-icons right"></i>
                    </button>
                    <button class="btn waves-effect waves-light" type="submit" name="opcion" value="Actualizar">Actualizar
                        <i class="material-icons right"></i>
                    </button>

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
