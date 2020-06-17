<%-- 
    Document   : index
    Created on : 09-07-2019, 16:27:38
    Author     : Fox
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link rel="stylesheet" href="Estilo/Style.css">
        <title>JSP Page</title>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0,
              maximun-scale=1.0, minumun-scale=1.0"/>
    </head>
    <body class="FondoPrincipal">
        <div id="particles-js">
            <form action="ControladorLogin" method="POST">


                <div class="row">
                    <div class="col s12 m4 offset-m4">
                        <div class="card">
                            <div class="card-action teal lighten-1 white-text">
                                <h3>Login</h3>
                            </div>

                            <div class="card-content" >
                                <table class="tabla">
                                    <div class="form-field">
                                        <input type="text" id="usuario" name="usuario" autocomplete="off ">
                                        <label for="usuario">Usuario</label>
                                    </div><br>
                                    <div class="form-field">
                                        <input type="password" id="clave" name="clave" autocomplete="off">
                                        <label for="clave">Contraseña</label>
                                    </div><br>
                                    <div class="form-field">
                                        <button class="btn-large waves-effect waves-dark" style="width: 100%"
                                                type="submit" name="opcion" value="Login">Login</button>
                                    </div>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>
        
    </body>
</html>
