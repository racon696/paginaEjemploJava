/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;

/**
 *
 * @author Racon
 */
public class ControladorCliente extends HttpServlet {

    private Cliente c;
    private ClienteDAO cdao;
    List<Cliente> clientes;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            //Declaracion de variables
            int id_cliente = 0;
            String rut = "";
            String nombre = "";
            String apellidoP = "";
            String apellidoM = "";

            //obtención de variables
            String link = request.getContextPath();
            String opcion = request.getParameter("opcion");

            if (opcion.equals("Agregar")) {

                cdao = new ClienteDAO();
                c = new Cliente();

                if (request.getParameter("id_cliente") != null && request.getParameter("rut") != null
                        && request.getParameter("nombre") != null && request.getParameter("apellidoP") != null
                        && request.getParameter("apellidoM") != null) {

                    id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
                    rut = request.getParameter("rut");
                    nombre = request.getParameter("nombre");
                    apellidoP = request.getParameter("apellidoP");
                    apellidoM = request.getParameter("apellidoM");

                    c.setIdCliente(id_cliente);
                    c.setRut(rut);
                    c.setNombre(nombre);
                    c.setApellidoP(apellidoP);
                    c.setApellidoM(apellidoM);

                    if (cdao.ingresarCliente(c)) {
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Agregado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- agregar ControladorCliente : cliente ingresado correctamente");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Cliente NO Agregado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : agregar ControladorCliente : cliente no ingresado");
                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "Agregar No realizado: Existen campos vacios");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error agregar controladorCliente: rut cero o negativo");
                }
            }

           
            if (opcion.equals("Buscar")) {
                cdao = new ClienteDAO();
                c = new Cliente();

                if (request.getParameter("id_cliente") != null) {

                    id_cliente = Integer.parseInt(request.getParameter("id_cliente"));

                    if (cdao.buscarCliente(id_cliente) != null) {

                        c = cdao.buscarCliente(id_cliente);
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("clientebuscado", c);
                        //redirigir 
                        response.sendRedirect(link + "/CrudCliente/ClienteEdit.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : buscar ControladorCliente : buscado correctamente");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe un Cliente con el id: " + id_cliente);
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : buscar ControladorCliente : cliente no existe");
                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "No Ingresaste el id de cliente");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error : buscar ControladorCliente : id nulo");
                }
            }

            
            if (opcion.equals("Eliminar")) {
                cdao = new ClienteDAO();
                c = new Cliente();
                if (request.getParameter("id_usuario") != null) {

                    id_cliente = Integer.parseInt(request.getParameter("id_cliente"));

                    if (cdao.eliminarCliente(id_cliente).equalsIgnoreCase("Eliminado")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Eliminado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : eliminar ControladorCliente : eliminado correctamente");

                    } else if (cdao.eliminarCliente(id_cliente).equalsIgnoreCase("noexiste")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe un Cliente con el id: " + id_cliente);
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : eliminar ControladorCliente : cliente no existe");

                    } else if (cdao.eliminarCliente(id_cliente) == null) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : eliminar ControladorCliente : falla con la base de datos");

                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "No Ingresaste el id de cliente");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error : eliminar ControladorCliente : id nulo");

                }
            }

           
            if (opcion.equals("Actualizar")) {
               
                c = new Cliente();
                cdao = new ClienteDAO();
                
                if (request.getParameter("id_cliente") != null && request.getParameter("rut") != null
                        && request.getParameter("nombre") != null && request.getParameter("apellidoP") != null
                        && request.getParameter("apellidoM") != null) {

                    id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
                    rut = request.getParameter("rut");
                    nombre = request.getParameter("nombre");
                    apellidoP = request.getParameter("apellidoP");
                    apellidoM = request.getParameter("apellidoM");


                    c.setIdCliente(id_cliente);
                    c.setRut(rut);
                    c.setNombre(nombre);
                    c.setApellidoP(apellidoP);
                    c.setApellidoM(apellidoM);

                    if (cdao.actualizarCliente(c).equalsIgnoreCase("Actualizado")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Actualizado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : actualizar ControladorCliente : acualizado correctamente");

                    } else if (cdao.actualizarCliente(c).equalsIgnoreCase("noexiste")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe un Cliente con el id: " + id_cliente);
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : actualizar ControladorCliente : usuario no existe");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : actualizar ControladorCliente : falla con la base de datos");

                    }

                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "Actualizar No realizado: Existen campos vacios");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error actualizar controladorCliente: id no valido o vacio");

                }
            }

            if (opcion.equals("Listar")) {

                response.sendRedirect(link + "/CrudCliente/EditarCliente.jsp");

            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
