/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;

/**
 *
 * @author Gateway
 */
public class ControladorProducto extends HttpServlet {

    private Producto p;
    private ProductoDAO pdao;

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
            int id_producto = 0;
            String nombre = "";
            int precio = 0;
            int stock = 0;

            //obtención de variables
            String link = request.getContextPath();
            String opcion = request.getParameter("opcion");

            if (opcion.equals("Agregar")) {

                pdao = new ProductoDAO();
                p = new Producto();

                if (request.getParameter("id_producto") != null && request.getParameter("nombre") != null
                        && request.getParameter("precio") != null && request.getParameter("stock") != null) {

                    id_producto = Integer.parseInt(request.getParameter("id_producto"));
                    nombre = request.getParameter("nombre");
                    precio = Integer.parseInt(request.getParameter("precio"));
                    stock = Integer.parseInt(request.getParameter("stock"));

                    p.setIdProducto(id_producto);
                    p.setNombre(nombre);
                    p.setPrecio(precio);
                    p.setStock(stock);

                    if (pdao.ingresarProducto(p)) {
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Agregado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("-ControladorProducto : producto ingresado correctamente");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Producto NO Agregado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : agregar ControladorProducto : producto no ingresado");
                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "Agregar No realizado: Existen campos vacios");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error agregar controladorProducto: id cero o negativo");
                }
            }

            if (opcion.equals("Buscar")) {
                pdao = new ProductoDAO();
                p=new Producto();
                if (request.getParameter("id_producto") != null) {

                    id_producto = Integer.parseInt(request.getParameter("id_producto"));

                    if (pdao.buscarProducto(id_producto) != null) {

                        p = pdao.buscarProducto(id_producto);
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("productobuscado", p);
                        //redirigir 
                        response.sendRedirect(link + "/CrudProducto/ProductoEdit.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : buscar ControladorProducto : buscado correctamente");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe un Producto con el id: " + id_producto);
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : buscar ControladorProducto : producto no existe");
                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "No Ingresaste el id de producto");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error : buscar ControladorProducto : id nulo");
                }
            }

           
            if (opcion.equals("Eliminar")) {

                pdao = new ProductoDAO();
                if (request.getParameter("id_usuario") != null) {

                    id_producto = Integer.parseInt(request.getParameter("id_producto"));

                    if (pdao.eliminarProducto(id_producto).equalsIgnoreCase("Eliminado")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Eliminado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : eliminar ControladorProducto : eliminado correctamente");

                    } else if (pdao.eliminarProducto(id_producto).equalsIgnoreCase("noexiste")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe un Producto con el id: " + id_producto);
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : eliminar ControladorProducto : producto no existe");

                    } else if (pdao.eliminarProducto(id_producto) == null) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : eliminar ControladorProducto : falla con la base de datos");

                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "No Ingresaste el id de usuario");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error : eliminar ControladorUsuario : id nulo");

                }
            }

            
            if (opcion.equals("Actualizar")) {
                pdao = new ProductoDAO();
                p = new Producto();

                if (request.getParameter("id_producto") != null && request.getParameter("nombre") != null
                        && request.getParameter("precio") != null && request.getParameter("stock") != null) {

                    id_producto = Integer.parseInt(request.getParameter("id_producto"));
                    nombre = request.getParameter("nombre");
                    precio = Integer.parseInt(request.getParameter("precio"));
                    stock = Integer.parseInt(request.getParameter("stock"));

                    p.setIdProducto(id_producto);
                    p.setNombre(nombre);
                    p.setPrecio(precio);
                    p.setStock(stock);

                    if (pdao.actualizarProducto(p).equalsIgnoreCase("Actualizado")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Actualizado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : actualizar ControladorProducto : acualizado correctamente");

                    } else if (pdao.actualizarProducto(p).equalsIgnoreCase("noexiste")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe un Producto con el id: " + id_producto);
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : actualizar ControladorProducto : producto no existe");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : actualizar ControladorProducto : falla con la base de datos");

                    }

                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "Actualizar No realizado: Existen campos vacios");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error actualizar controladorProducto: id no valido o vacio");

                }
            }

           
            if (opcion.equals("Listar")) {

                response.sendRedirect(link + "/CrudProducto/EditarProducto.jsp");

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
