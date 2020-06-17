/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author Racon
 */
public class ControladorUsuario extends HttpServlet {
    

    private Usuario u;
    private UsuarioDAO udao;
    List<Usuario> usuarios;

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
            int id_usuario = 0;
            String rut = "";
            String nombre = "";
            String apellidoP = "";
            String apellidoM = "";
            int tipousuario = 0;
            String user = "";
            String clave = "";

            //obtención de variables
            String link = request.getContextPath();
            String opcion = request.getParameter("opcion");

            //  respuesta de boton agregar
            if (opcion.equals("Agregar")) {

                udao = new UsuarioDAO();
                u = new Usuario();

                if (request.getParameter("id_usuario") != null && request.getParameter("rut") != null
                        && request.getParameter("nombre") != null && request.getParameter("apellidoP") != null
                        && request.getParameter("apellidoM") != null && request.getParameter("tipousuario") != null
                        && request.getParameter("user") != null && request.getParameter("clave") != null) {

                    id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    rut = request.getParameter("rut");
                    nombre = request.getParameter("nombre");
                    apellidoP = request.getParameter("apellidoP");
                    apellidoM = request.getParameter("apellidoM");
                    tipousuario = Integer.parseInt(request.getParameter("tipousuario"));
                    user = request.getParameter("user");
                    clave = request.getParameter("clave");

                    u.setIdUsuario(id_usuario);
                    u.setRut(rut);
                    u.setNombre(nombre);
                    u.setApellidoP(apellidoP);
                    u.setApellidoM(apellidoM);
                    u.setTipousuario(tipousuario);
                    u.setUser(user);
                    u.setClave(clave);

                    if (udao.ingresarUsuario(u)) {
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Agregado");
                        request.getSession().setAttribute("servletMsg2", "../FormUsuario.jsp");
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("-ControladorUsuario : usuario ingresado correctamente");
                        
                    } else {
                        
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Usuario NO Agregado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : agregar ControladorUsuario : usuario no ingresado");
                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "Agregar No realizado: Existen campos vacios");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error agregar controladorUsuario: rut cero o negativo");
                }
            }

            if (opcion.equals("Buscar")) {
                udao = new UsuarioDAO();

                if (request.getParameter("id_usuario") != null) {

                    id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

                    if (udao.buscarUsuario(id_usuario) != null) {

                        u = udao.buscarUsuario(id_usuario);
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("usuariobuscado", u);
                        //redirigir 
                        response.sendRedirect(link+"/CrudUsuario/UsuarioEdit.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : buscar ControladorUsuario : buscado correctamente");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe un Usuario con el id: " + id_usuario);
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : buscar ControladorUsuario : usuario no existe");
                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "No Ingresaste el id de usuario");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error : buscar ControladorUsuario : id nulo");
                }
            }

            if (opcion.equals("Eliminar")) {

                udao = new UsuarioDAO();
                if (request.getParameter("id_usuario") != null) {

                    id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

                    if (udao.eliminarUsuario(id_usuario).equalsIgnoreCase("Eliminado")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Eliminado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : eliminar ControladorUsuario : eliminado correctamente");

                    } else if (udao.eliminarUsuario(id_usuario).equalsIgnoreCase("noexiste")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe un Usuario con el id: " + id_usuario);
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : eliminar ControladorUsuario : usuario no existe");
                        
                    } else if (udao.eliminarUsuario(id_usuario) == null) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : eliminar ControladorUsuario : falla con la base de datos");
                        
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
                udao = new UsuarioDAO();
                u = new Usuario();
                
                if (request.getParameter("id_usuario") != null && request.getParameter("rut") != null
                        && request.getParameter("nombre") != null && request.getParameter("apellidoP") != null
                        && request.getParameter("apellidoM") != null && request.getParameter("tipousuario") != null
                        && request.getParameter("user") != null && request.getParameter("clave") != null) {

                    id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    rut = request.getParameter("rut");
                    nombre = request.getParameter("nombre");
                    apellidoP = request.getParameter("apellidoP");
                    apellidoM = request.getParameter("apellidoM");
                    tipousuario = Integer.parseInt(request.getParameter("tipousuario"));
                    user = request.getParameter("user");
                    clave = request.getParameter("clave");
                    
                    u.setIdUsuario(id_usuario);
                    u.setRut(rut);
                    u.setNombre(nombre);
                    u.setApellidoP(apellidoP);
                    u.setApellidoM(apellidoM);
                    u.setTipousuario(tipousuario);
                    u.setUser(user);
                    u.setClave(clave);
                    
                    
                    if(udao.actualizarUsuario(u).equalsIgnoreCase("Actualizado")){
                        
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Actualizado");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : actualizar ControladorUsuario : acualizado correctamente");

                        
                    }else if(udao.actualizarUsuario(u).equalsIgnoreCase("noexiste")){
                        
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe un Usuario con el id: " + id_usuario);
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : actualizar ControladorUsuario : usuario no existe");
                        
                    }else{
                        
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                        request.getSession().setAttribute("servletMsg2", link);
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : actualizar ControladorUsuario : falla con la base de datos");
                        
                    }
                    
                }else{
                    
                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "Actualizar No realizado: Existen campos vacios");
                    request.getSession().setAttribute("servletMsg2", link);
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error actualizar controladorUsuario: id no valido o vacio");
                    
                }
            }

            if (opcion.equals("Listar")) {

                 response.sendRedirect(link+"/CrudUsuario/EditarUsuario.jsp");   
                  
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
