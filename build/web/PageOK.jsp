<%-- 
    Document   : PageOK
    Created on : 12-07-2019, 13:12:16
    Author     : Gateway
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page OK</title>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="Estilo/Style.css">
    </head>
    <body class="FondoPrincipal">

        <div class="row">
            <div class="col s8 offset-s2">
                <div class="col s12 m5">
                    <div class="card-panel teal">
                        <span class="white-text " >
                            <table >
                                <tr>
                                    <td >
                                        <p>Perfecto !!</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p>Hey <c:out value="${sessionScope.usuario.nombre}"></c:out> <c:out value="${sessionScope.usuario.apellidoP}"></c:out>, Todo ha salido de maravilla!</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                                <p><c:out value="${sessionScope.servletMsg1}"></c:out></p>
                                        </td>
                                    </tr>
                                </table>
                            </span>
                            <a class="waves-effect waves-light btn" href ="<c:out value="${sessionScope.servletMsg2}"></c:out>">Volver</a>
                    </div>
                </div>
            </div>
        </div>

        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="js/materialize.min.js"></script>         
    </body>
</html>
