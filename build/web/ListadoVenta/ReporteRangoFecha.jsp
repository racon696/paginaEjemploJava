<%-- 
    Document   : ReporteRangoFecha
    Created on : 10-07-2019, 15:56:49
    Author     : Fox
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String nombre = "";
    modelo.Usuario u = (modelo.Usuario) session.getAttribute("usuario");
    modelo.Venta venta = (modelo.Venta) session.getAttribute("venta");
    String nada = (String) session.getAttribute("nada");
    if (u == null) {
        response.sendRedirect("index.jsp");
    } else {
        nombre = u.getNombre() + " " + u.getApellidoP() + " " + u.getApellidoM();
    }
%>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <title>Ventas</title>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


    </head>
    <body>
        <nav>
            <div class="nav-wrapper">
                <a href="../PaginaPrincipal.jsp" class="brand-logo">
                    <%=nombre%>
                </a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="../CrudUsuario/UsuarioMostrar.jsp">Usuario</a></li>
                    <li><a href="../CrudSala/SalaMostrar.jsp">Sala</a></li>
                    <li><a href="HorarioMostrarCoordinador.jsp">Horario</a></li>
                    <li><a href="../index.jsp">Cerrar Sesion</a></li>
                </ul>
            </div>
        </nav> 
        <div class="container">
            <a href="../FormHorario.jsp" class="left select-label">
                <label class="btn-floating btn-large waves-effect waves-light red">
                    <i class="material-icons">add</i>
                </label>
                <label class="black-text">Agregar un horario</label>
            </a>
            <h3 class="center">Horario</h3>
            <form method="POST" action="../ControladorHorario">
                <div class="row">
                    <div class="col s2">
                        <div class="col s12">
                            <label for="id_asignacion">ID Asignacion:</label>
                        </div>
                        <div class="col s12">
                            <input type='number' name='id_asignacion' id='id_asignacion' class='validate'><br>
                        </div>  
                    </div>
                    <div class="col s2">
                        <div><label>Rut Docente:</label></div>
                        <div>
                            <select name="rut_docente" id="rut_docente" class="browser-default">
                                <option></option>
                                <%
                                    ArrayList<modelo.Usuario> usuarios = dao.UsuarioDAO.obtenerDatos();
                                    for (modelo.Usuario usuario : usuarios) {
                                        if (usuario.getTipousuario() == 1) {
                                            out.println("<option value='" + usuario.getRut() + "'>" + usuario.getRut() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="col s2">
                        <div class="col s12">
                            <label>ID Sala:</label>
                        </div>
                        <div class="col s12">
                            <select name="id_sala" id="id_sala" class="browser-default">
                                <option></option>
                                <%
                                    ArrayList<modelo.Venta> ventas = dao.VentaDAO.obtenerDatos();
                                    for (modelo.Venta sala : ventas) {
                                        out.println("<option value='" + sala.getFolioventa()+ "'>" + sala.getFolioventa() + "</option>");
                                    }
                                %>
                            </select>   
                        </div>
                    </div>
                    <div class="col s2">
                        <div class="col s12">
                            <label>Fecha:</label>
                        </div>
                        <div class="col s12">
                            <input type="date" id="fecha" name="fecha">
                        </div> 
                    </div>
                    <div class="col s2">
                        <div class="col s12">
                            <label>Modulo:</label>
                        </div>
                        <div class="col s12">
                            <select name="modulo" id="modulo" class="browser-default">
                                <option></option>
                                <option value='1'>8:30 a 9:15</option>
                                <option value='2'>9:15 a 10:00</option>
                                <option value='3'>10:15 a 11:00</option>
                                <option value='4'>11:00 a 11:45</option>
                                <option value='5'>11:45 a 12:30</option>
                                <option value='6'>12:30 a 13:15</option>
                                <option value='7'>13:45 a 14:30</option>
                                <option value='8'>14:30 a 15:15</option>
                                <option value='9'>15:15 a 16:00</option>
                                <option value='10'>16:00 a 16:45</option>
                                <option value='11'>16:45 a 17:30</option>
                                <option value='12'>17:30 a 18:15</option>
                                <option value='13'>18:15 a 19:00</option>
                                <option value='14'>19:00 a 19:45</option>
                                <option value='15'>19:45 a 20:30</option>
                                <option value='16'>20:30 a 21:15</option>
                                <option value='17'>21:15 a 22:00</option>
                                <option value='18'>22:00 a 22:45</option>      
                            </select>
                        </div>
                    </div>
                    <div class="col s2">
                        <br>
                        <button class="btn waves-effect waves-ligth right" type="submit" name="opcion" value="Buscar">Buscar</button>
                    </div>
                </div>
            </form>
            <table class="striped">
                <thead>
                    <tr>
                        <th>Asignaci&oacute;n</th>
                        <th>Sala</th>
                        <th>Rut Docente</th>
                        <th>Semestre</th>
                        <th>M&oacute;dulo</th>
                        <th>D&iacute;a</th>
                        <th>Fecha</th>
                        <th>Activa</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (horario != null) {

                            out.println("<tr>");
                            out.println("<td>" + horario.getId_asignacion() + "</td>");
                            out.println("<td>" + horario.getId_sala() + "</td>");
                            out.println("<td>" + horario.getRut_docente() + "</td>");
                            out.println("<td>" + horario.getSemestre() + "</td>");
                            switch (horario.getModulo()) {
                                case 1:
                                    out.println("<td>8:30 a 9:15</td>");
                                    break;
                                case 2:
                                    out.println("<td>9:15 a 10:00</td>");
                                    break;
                                case 3:
                                    out.println("<td>10:15 a 11:00</td>");
                                    break;
                                case 4:
                                    out.println("<td>11:00 a 11:45</td>");
                                    break;
                                case 5:
                                    out.println("<td>11:45 a 12:30</td>");
                                    break;
                                case 6:
                                    out.println("<td>12:30 a 13:15</td>");
                                    break;
                                case 7:
                                    out.println("<td>13:45 a 14:30</td>");
                                    break;
                                case 8:
                                    out.println("<td>14:30 a 15:15</td>");
                                    break;
                                case 9:
                                    out.println("<td>15:15 a 16:00</td>");
                                    break;
                                case 10:
                                    out.println("<td>16:00 a 16:45</td>");
                                    break;
                                case 11:
                                    out.println("<td>16:45 a 17:30</td>");
                                    break;
                                case 12:
                                    out.println("<td>17:30 a 18:15</td>");
                                    break;
                                case 13:
                                    out.println("<td>18:15 a 19:00</td>");
                                    break;
                                case 14:
                                    out.println("<td>19:00 a 19:45</td>");
                                    break;
                                case 15:
                                    out.println("<td>19:45 a 20:30</td>");
                                    break;
                                case 16:
                                    out.println("<td>20:30 a 21:15</td>");
                                    break;
                                case 17:
                                    out.println("<td>21:15 a 22:00</td>");
                                    break;
                                case 18:
                                    out.println("<td>22:00 a 22:45</td>");
                                    break;
                                default:
                                    out.println("<td>---</td>");
                                    break;
                            }

                            out.println("<td>" + horario.getDia() + "</td>");
                            out.println("<td>" + horario.getFecha() + "</td>");
                            if (horario.getActiva() == 1) {
                                out.println("<td>Si</td>");
                            } else {
                                out.println("<td>No</td>");
                            }
                            out.println("<td>"
                                    + "<a href='http://localhost:8080/WebMaterialize LoginV2/ControladorHorario?opcion=Editar&id_asignacion=" + horario.getId_asignacion() + "'>"
                                    + "<img src='https://img.icons8.com/metro/26/000000/edit-file.png'>"
                                    + "</a>  "
                                    + "<a href='http://localhost:8080/WebMaterialize LoginV2/ControladorHorario?opcion=Eliminar&id_asignacion=" + horario.getId_asignacion() + "'>"
                                    + "<img src='https://img.icons8.com/metro/26/000000/delete.png'>"
                                    + "</a>"
                                    + "</td>");
                            out.println("</tr>");

                        } else if (nada.equals("si")){
                            out.println("<tr><td colspan='8'>No se encontr&oacute; ning&uacute;n registro</td></tr>");
                            session.setAttribute("nada", "no");
                        } else {
                            ArrayList<modelo.HorarioSala> horarios = dao.HorarioSalaDAO.obtenerDatos();
                            if (horarios != null) {
                                for (modelo.HorarioSala h : horarios) {
                                    out.println("<tr>");
                                    out.println("<td>" + h.getId_asignacion() + "</td>");
                                    out.println("<td>" + h.getId_sala() + "</td>");
                                    out.println("<td>" + h.getRut_docente() + "</td>");
                                    out.println("<td>" + h.getSemestre() + "</td>");
                                    switch (h.getModulo()) {
                                        case 1:
                                            out.println("<td>8:30 a 9:15</td>");
                                            break;
                                        case 2:
                                            out.println("<td>9:15 a 10:00</td>");
                                            break;
                                        case 3:
                                            out.println("<td>10:15 a 11:00</td>");
                                            break;
                                        case 4:
                                            out.println("<td>11:00 a 11:45</td>");
                                            break;
                                        case 5:
                                            out.println("<td>11:45 a 12:30</td>");
                                            break;
                                        case 6:
                                            out.println("<td>12:30 a 13:15</td>");
                                            break;
                                        case 7:
                                            out.println("<td>13:45 a 14:30</td>");
                                            break;
                                        case 8:
                                            out.println("<td>14:30 a 15:15</td>");
                                            break;
                                        case 9:
                                            out.println("<td>15:15 a 16:00</td>");
                                            break;
                                        case 10:
                                            out.println("<td>16:00 a 16:45</td>");
                                            break;
                                        case 11:
                                            out.println("<td>16:45 a 17:30</td>");
                                            break;
                                        case 12:
                                            out.println("<td>17:30 a 18:15</td>");
                                            break;
                                        case 13:
                                            out.println("<td>18:15 a 19:00</td>");
                                            break;
                                        case 14:
                                            out.println("<td>19:00 a 19:45</td>");
                                            break;
                                        case 15:
                                            out.println("<td>19:45 a 20:30</td>");
                                            break;
                                        case 16:
                                            out.println("<td>20:30 a 21:15</td>");
                                            break;
                                        case 17:
                                            out.println("<td>21:15 a 22:00</td>");
                                            break;
                                        case 18:
                                            out.println("<td>22:00 a 22:45</td>");
                                            break;
                                        default:
                                            out.println("<td>---</td>");
                                            break;
                                    }

                                    out.println("<td>" + h.getDia() + "</td>");
                                    out.println("<td>" + h.getFecha() + "</td>");
                                    if (h.getActiva() == 1) {
                                        out.println("<td>Si</td>");
                                    } else {
                                        out.println("<td>No</td>");
                                    }
                                    out.println("<td>"
                                            + "<a href='http://localhost:8080/WebMaterialize LoginV2/ControladorHorario?opcion=Editar&id_asignacion=" + h.getId_asignacion() + "'>"
                                            + "<img src='https://img.icons8.com/metro/26/000000/edit-file.png'>"
                                            + "</a>  "
                                            + "<a href='http://localhost:8080/WebMaterialize LoginV2/ControladorHorario?opcion=Eliminar&id_asignacion=" + h.getId_asignacion() + "'>"
                                            + "<img src='https://img.icons8.com/metro/26/000000/delete.png'>"
                                            + "</a>"
                                            + "</td>");
                                    out.println("</tr>");
                                }
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>

    </body>
</html>
