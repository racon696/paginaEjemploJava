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
        <!-- Etiqueta de conexión a la base de datos de jstl-->
        <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                           url = "jdbc:mysql://localhost/jpa"
                           user = "root"  password = ""/>

        <!-- Etiquetas de consulta a la base de datos con jstl-->

        <sql:query dataSource = "${snapshot}" var = "clientes">
            SELECT * FROM cliente;
        </sql:query>
        <sql:query dataSource = "${snapshot}" var = "productos">
            SELECT * FROM producto;
        </sql:query> 

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
                <form class="col s12" action="${pageContext.request.contextPath}/ControladorVenta" method="POST" >
                <div class="row">

                    <br><br>
                    <div class="col s8 offset-s2">
                        <div class="input-field col s6 ">
                            <select  name="id_usuario" id="id_usuario">

                                <option selected value="<c:out value = "${sessionScope.usuario.idUsuario}"/>"><c:out value = "${sessionScope.usuario.nombre}"/></option>
                            </select>
                            <label>Seleccione Usuario</label>
                        </div>

                        <div class="input-field col s6">
                            <select name="id_cliente" id="id_cliente">
                                <option value="" disabled selected>-Seleccione-</option>
                                <!-- Ciclo para rellenar con datos del select usando jstl-->
                                <c:forEach var = "rowcl" items = "${clientes.rows}">

                                    <option value="<c:out value = "${rowcl.id_cliente}"/>"><c:out value = "${rowcl.rut}"/></option>

                                </c:forEach>
                            </select>
                            <label>Seleccione Cliente</label>
                        </div>
                    </div>  

                    <div class="col s8 offset-s2">
                        <table class="responsive-table">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Precio</th>
                                    <th>Stock</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Ciclo para rellenar con datos del select usando jstl-->
                                <c:forEach var = "rowp" items = "${productos.rows}">
                                    <tr>
                                        <td><c:out value = "${rowp.nombre}"></c:out></td>
                                        <td><c:out value = "${rowp.precio}"></c:out></td>
                                        <td><c:out value = "${rowp.stock}"></c:out></td>
                                        </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>        

                    <div class="col s8 offset-s2">            
                        <div class="input-field col s6">
                            <select name="id_producto" id="id_producto">
                                <option value="" disabled selected>-Seleccione-</option>

                                <!-- Ciclo para rellenar con datos del select usando jstl-->
                                <c:forEach var = "rowp" items = "${productos.rows}">

                                    <option value="<c:out value = "${rowp.id_producto}"></c:out>"><c:out value = "${rowp.nombre}"></c:out></option>

                                </c:forEach>
                            </select>
                            <label>Seleccione Producto</label>
                        </div>
                        <div class="input-field col s6">
                            <input value="0" id="cantidad" name="cantidad" type="text" class="validate">
                            <label for="cantidad">Cantidad</label>
                        </div>    
                    </div>
                    <div class="col s8 offset-s2">       
                        <br>
                            <c:choose>
                                <c:when test="${sessionScope.usuario.tipousuario ==0}">
                                    <a class="btn-floating btn-large waves-effect waves-light red" href="TipoDeUsuarios/Vendedor.jsp"><i class="material-icons">arrow_back</i></a>
                                </c:when> 
                                <c:when test="${sessionScope.usuario.tipousuario ==1}">
                                    <a class="btn-floating btn-large waves-effect waves-light red" href="TipoDeUsuarios/Administrador.jsp"><i class="material-icons">arrow_back</i></a>
                                </c:when> 
                            </c:choose> 
                            <button class="btn waves-effect waves-light" type="submit" name="opcion" value="Continuar">Continuar
                                <i class="material-icons right">chevron_right</i>
                            </button>


                        </div>
                    </div>
                </form>
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
