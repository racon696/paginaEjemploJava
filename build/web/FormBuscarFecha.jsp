<%-- 
    Document   : ReporteBuscarFecha
    Created on : 10-07-2019, 18:55:32
    Author     : Fox
--%>

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
        <link rel="stylesheet" href="Estilo/Style.css">
    </head>
    <body class="FondoPrincipal">
        <div id="particles-js">
            <form action="ControladorVentas" method="POST">
                <div class="row">
                    <div class="col s12 m4 offset-m4">
                        <div class="card">
                            <div class="card-action teal lighten-1 white-text">
                                <center><h3>Buscador de fecha</h3></center>
                                <li type="square"><a href="Reportes.jsp">Volver</a></li>
                            </div>

                            <div class="card-content" >
                                <table class="tabla">
                                    <div class="form-field">
                                        <input type="text" class="datepicker" name="fechabuscar" value="" autocomplete="off" required="">
                                        <label for="fechabuscar">Ingrese la fecha a buscar</label>
                                    </div><br>

                                    <div class="form-field">
                                        <button class="btn-large waves-effect waves-dark" style="width: 100%"
                                                type="submit" name="opcionFecha" value="BusFecha">Buscar Fecha</button>
                                    </div>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>7
        <script>
            $(document).ready(function () {
                $(".dropdown-trigger").dropdown();
            });
        </script> 
        
        <!--datePiker-->
        <script>
            $(document).ready(function () {

                $(".datepicker").datepicker({
                    format: 'yyyy-mm-dd', 
                    
                    i18n:{
                        months:['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiempbre', 'Octubre', 'Noviembre'],
                        weekdays:['Lun', 'Mar', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo'],
                        weekdaysAbbrev:['L', 'M', 'M', 'J', 'V', 'S', 'D']
                    }
                });
            });
        </script>

        <!--datePiker-->
        
        
        <!--JavaScript at end of body for optimized loading-->
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>

</html>
