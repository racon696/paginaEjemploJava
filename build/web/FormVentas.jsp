<%-- 
    Document   : FormVentas
    Created on : 10-07-2019, 14:36:11
    Author     : Fox
--%>
<!-- Etiqueta de librerias para jstl -->

<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Venta</title>
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
        <sql:query dataSource = "${snapshot}" var = "usuarios">
            SELECT * FROM usuario;
        </sql:query>
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


                    <li><a href="FormCliente.jsp">Cliente Crud</a></li>
                    <li><a href="FormUsuario.jsp">Usuario Crud</a></li>
                    <li><a href="FormProductos.jsp">Producto Crud</a></li>
                    
                    <li><a href="Reportes.jsp">Reportes</a></li>
                    <li><a href="FormVentaReal.jsp">Vender</a></li>



                    <li> <a class="dropdown-trigger" href="#!" data-target="opciones" ><c:out value="${sessionScope.usuario.nombre}"></c:out> <i class="material-icons right">arrow_drop_down</i></a></li>
                    </ul>
                </div>
            </nav>    
            <br><br>

            <div class="row">
                <form class="col s12" action="${pageContext.request.contextPath}/ControladorVenta" method="POST" >
                <div class="row">
                    <div class="col s8 offset-s2">

                        <div class="input-field col s12">
                            <input value="1" id="folioventa" name="folioventa" type="text" class="validate" >
                            <label for="folioventa">Folio Venta</label>
                        </div>

                        <div class="input-field col s12">
                            <input type="text" class="datepicker" name="fecha" id="fecha" format="yyyy-mm-dd">
                        </div>

                        <div class="input-field col s12">
                            <input twelveHour="false" type="text" class="timepicker" name="hora" id="hora" >
                        </div>

                        <div class="input-field col s12">
                            <input value="0" id="nroboleta" name="nroboleta" type="number" class="validate">
                            <label for="nroboleta">Nro Boleta</label>
                        </div>

                        <div class="input-field col s12">
                            <select name="id_usuario" id="id_usuario">
                                <option value="" disabled selected>-Seleccione-</option>

                                <!-- Ciclo para rellenar con datos del select usando jstl-->
                                <c:forEach var = "rowu" items = "${usuarios.rows}">

                                    <option value="<c:out value = "${rowu.id_usuario}"/>"><c:out value = "${rowu.rut}"/></option>

                                </c:forEach>
                            </select>
                            <label>Seleccione Usuario</label>
                        </div>

                        <div class="input-field col s12">
                            <select name="id_cliente" id="id_cliente">
                                <option value="" disabled selected>-Seleccione-</option>

                                <!-- Ciclo para rellenar con datos del select usando jstl-->
                                <c:forEach var = "rowcl" items = "${clientes.rows}">

                                    <option value="<c:out value = "${rowcl.id_cliente}"/>"><c:out value = "${rowcl.rut}"/></option>

                                </c:forEach>
                            </select>
                            <label>Seleccione Cliente</label>
                        </div>

                        <div class="input-field col s12">
                            <select name="id_producto" id="id_producto">
                                <option value="" disabled selected>-Seleccione-</option>

                                <!-- Ciclo para rellenar con datos del select usando jstl-->
                                <c:forEach var = "rowp" items = "${productos.rows}">

                                    <option value="<c:out value = "${rowp.id_producto}"/>"><c:out value = "${rowp.nombre}"/></option>

                                </c:forEach>
                            </select>
                            <label>Seleccione Producto</label>
                        </div>

                        <div class="input-field col s12">
                            <input value="1" id="cantidad" name="cantidad" type="number" class="validate">
                            <label for="cantidad">Cantidad</label>
                        </div>

                        <div class="input-field col s12">
                            <input  value="1" id="total" name="total" type="number" class="validate">
                            <label for="total">Total</label>
                        </div>

                        <button class="btn waves-effect waves-light" type="submit" name="opcion" value="Buscar">Buscar
                            <i class="material-icons right">chevron_right</i>
                        </button>
                        <button class="btn waves-effect waves-light" type="submit" name="opcion" value="Agregar">Agregar
                            <i class="material-icons right">cloud_upload</i>
                        </button>

                        <button class="btn waves-effect waves-light" type="submit" name="opcion" value="Listar">Listar
                            <i class="material-icons right">chevron_right</i>
                        </button>

                    </div>
                </div>
            </form>
        </div>
        <script>
            $(document).ready(function () {
                $('.datepicker').datepicker();
                $('select').formSelect();
                $('.timepicker').timepicker();
                $(".dropdown-trigger").dropdown();
            });
        </script> 
        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
