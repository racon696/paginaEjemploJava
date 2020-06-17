<%-- 
    Document   : FormCliente
    Created on : 10-07-2019, 14:35:51
    Author     : Fox
--%>
<!-- Etiqueta de librerias para jstl -->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venta</title>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../Estilo/StyleAdministrador.css">

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

                    <c:if test="${sessionScope.usuario.tipousuario ==1}">
                        <li><a href="FormCliente.jsp">Cliente Crud</a></li>
                        <li><a href="FormUsuario.jsp">Usuario Crud</a></li>
                        <li><a href="FormProductos.jsp">Producto Crud</a></li>
                        <li><a href="FormVentas.jsp">Venta Crud</a></li>
                        <li><a href="Reportes.jsp">Reportes</a></li>
                        </c:if>
                    <li><a href="FormVentaReal.jsp">Vender</a></li>



                    <li> <a class="dropdown-trigger" href="#!" data-target="opciones" ><c:out value="${sessionScope.usuario.nombre}"></c:out> <i class="material-icons right">arrow_drop_down</i></a></li>
                    </ul>
                </div>
            </nav>    

            <div class="row">
                <div class="col s10 offset-s2">  
                    <form class="col s12" action="${pageContext.request.contextPath}/ControladorVenta" method="POST" >
                    <div class="row">

                        <input type="hidden" value="<c:out value="${sessionScope.ventacontinuada.folioventa}"></c:out>" id="folioventa" name="folioventa" type="number" class="validate" >        
                        <input type="hidden" value="<c:out value="${sessionScope.ventacontinuada.fecha}"></c:out>" id="fecha" name="fecha" type="text" class="validate" >
                        <input type="hidden" value="<c:out value="${sessionScope.ventacontinuada.hora}"></c:out>" id="hora" name="hora" type="text" class="validate" >
                        <input type="hidden" value="<c:out value="${sessionScope.ventacontinuada.nroboleta}"></c:out>" id="nroboleta" name="nroboleta" type="number" class="validate">
                        <input type="hidden" value="<c:out value="${sessionScope.ventacontinuada.idUsuario}"></c:out>" id="id_usuario" name="id_usuario" type="number" class="validate">
                        <input type="hidden" value="<c:out value="${sessionScope.ventacontinuada.idCliente}"></c:out>" id="id_cliente" name="id_cliente" type="number" class="validate"> 
                        <input type="hidden" value="<c:out value="${sessionScope.ventacontinuada.idProducto}"></c:out>" id="id_producto" name="id_producto" type="number" class="validate">
                        <input type="hidden" value="<c:out value="${sessionScope.ventacontinuada.cantidad}"></c:out>" id="cantidad" name="cantidad" type="number" class="validate">         
                        <input type="hidden" value="<c:out value="${sessionScope.ventacontinuada.total}"></c:out>" id="total" name="total" type="number" class="validate">

                            <div class="col s10 ">  
                                <br>
                                <!--  Folio -->
                                <div class="input-field col s6">
                                    <input disabled value="<c:out value="${sessionScope.ventacontinuada.folioventa}"></c:out>" name="folioventamostrada" type="number" class="validate" >
                                    <label for="folioventamostrada">Folio Venta</label>
                                </div>

                                <!--  Fecha -->    
                                <div class="input-field col s6">
                                    <input disabled value="<c:out value="${sessionScope.ventacontinuada.fecha}"></c:out>" name="fechaventamostrada" type="text" class="validate" >
                                    <label for="fechaventamostrada">Fecha</label>
                                </div> 

                                <!--  Hora -->    
                                <div class="input-field col s6">
                                    <input disabled value="<c:out value="${sessionScope.ventacontinuada.hora}"></c:out>" name="horaventamostrada" type="text" class="validate" >
                                    <label for="horaventamostrada">Hora</label>
                                </div>    

                                <!--  Boleta -->    
                                <div class="input-field col s6">
                                    <input disabled value="<c:out value="${sessionScope.ventacontinuada.nroboleta}"></c:out>" name="nroboletaventamostrada" type="number" class="validate">
                                    <label for="nroboletaventamostrada">Nro Boleta</label>
                                </div>

                                <!--  Usuario -->    
                                <div class="input-field col s6">
                                    <input disabled value="<c:out value="${sessionScope.ventacontinuada.idUsuario}"></c:out>" name="idUsuarioventamostrada" type="number" class="validate">
                                    <label for="idUsuarioventamostrada">Usuario</label>
                                </div>    

                                <!--  Cliente -->    
                                <div class="input-field col s6">
                                    <input disabled value="<c:out value="${sessionScope.ventacontinuada.idCliente}"></c:out>" name="idClienteventamostrada" type="number" class="validate">
                                    <label for="idClienteventamostrada">Cliente</label>
                                </div>       

                                <!--  Producto -->    
                                <div class="input-field col s6">
                                    <input disabled value="<c:out value="${sessionScope.ventacontinuada.idProducto}"></c:out>" name="idProductoventamostrada" type="number" class="validate">
                                    <label for="idProductoventamostrada">Producto</label>
                                </div>     

                                <!--  Cantidad -->    
                                <div class="input-field col s6">
                                    <input disabled value="<c:out value="${sessionScope.ventacontinuada.cantidad}"></c:out>" name="cantidadventamostrada" type="number" class="validate">
                                    <label for="cantidadventamostrada">Cantidad</label>
                                </div>

                                <!--  Total -->    
                                <div class="input-field col s6">
                                    <input disabled value="<c:out value="${sessionScope.ventacontinuada.total}"></c:out>" name="totalventamostrada" type="number" class="validate">
                                <label for="totalventamostrada">Total</label>
                            </div>

                        </div> 
                        <div class="col s8 offset-s2">           
                            <c:choose>
                                <c:when test="${sessionScope.usuario.tipousuario ==0}">
                                    <a class="btn-floating btn-large waves-effect waves-light red" href="TipoDeUsuarios/Vendedor.jsp"><i class="material-icons">arrow_back</i></a>
                                </c:when> 
                                <c:when test="${sessionScope.usuario.tipousuario ==1}">
                                    <a class="btn-floating btn-large waves-effect waves-light red" href="TipoDeUsuarios/Administrador.jsp"><i class="material-icons">arrow_back</i></a>
                                </c:when> 
                            </c:choose>
                            <button class="btn waves-effect waves-light" type="submit" name="opcion" value="Agregar">Confirmar
                                <i class="material-icons right">chevron_right</i>
                            </button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
        <script>
            $(document).ready(function () {

                $(".dropdown-trigger").dropdown();
                $('select').formSelect();
            });
        </script>         
        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
