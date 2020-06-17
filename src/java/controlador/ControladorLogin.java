/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author Fox
 */
public class ControladorLogin extends HttpServlet {

    UsuarioDAO udao = new UsuarioDAO();
    private static int contadorintentos = 0;

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
        PrintWriter out = response.getWriter();

        HttpSession sesion = request.getSession();

        String opcion = "";
        String usuario = "";
        String clave = "";

        opcion = request.getParameter("opcion");

        if (opcion.equals("Login")) {

            usuario = request.getParameter("usuario");
            clave = request.getParameter("clave");

            Usuario ub = new UsuarioDAO().VerificarBloqueo(usuario);
            if (ub != null) {
                String mensaje = "Este usuario se encuentra bloqueado.";
                String volver = "index.jsp";
                request.getSession().setAttribute("servletMsg1", mensaje);
                request.getSession().setAttribute("servletMsg2", volver);
                response.sendRedirect("PageError.jsp");
            } else {

                if (usuario.isEmpty() || clave.isEmpty()) {

                    String mensaje = "Los campos no deben estar vacios!!.";
                    String volver = "index.jsp";
                    request.getSession().setAttribute("servletMsg1", mensaje);
                    request.getSession().setAttribute("servletMsg2", volver);
                    response.sendRedirect("PageError.jsp");

                } else {

                    Usuario u = UsuarioDAO.conectarUsuario(usuario, clave);

                    if (u != null) {

                        HttpSession session = request.getSession();
                        session.setAttribute("usuario", u);

                        switch (u.getTipousuario()) {
                            case 0:
                                response.sendRedirect("TipoDeUsuarios/Vendedor.jsp");
                                break;
                            case 1:
                                response.sendRedirect("TipoDeUsuarios/Administrador.jsp");
                                break;

                        }
                    } else {
                        if (contadorintentos <= 1) {
                            contadorintentos++;
                            String mensaje = "Error! usuario incorrecto nro de fallas :" + contadorintentos;
                            String volver = "index.jsp";

                            request.getSession().setAttribute("servletMsg1", mensaje);
                            request.getSession().setAttribute("servletMsg2", volver);
                            response.sendRedirect("PageError.jsp");

                        } else {
                            if (contadorintentos >= 2) {
                                String mensaje = "Error! usuario bloqueado por maximo de 3 intentos,contacte con su administrador";
                                String volver = "index.jsp";
                                int userBlock = 3;
                                udao.bloquearUser(userBlock, usuario);
                                contadorintentos = 0;
                                request.getSession().setAttribute("servletMsg1", mensaje);
                                request.getSession().setAttribute("servletMsg2", volver);
                                response.sendRedirect("PageError.jsp");

                            }
                        }
                    }

                }
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
