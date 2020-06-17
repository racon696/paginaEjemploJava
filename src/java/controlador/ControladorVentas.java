/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.VentaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import modelo.Venta;

/**
 *
 * @author Fox
 */
public class ControladorVentas extends HttpServlet {

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

        String opcion = request.getParameter("opcionFecha");
        String fechaSeleccionada = "";
        String fechaRango1 = "";
        String fechaRango2 = "";

        if (opcion.equals("BusFecha")) {
            fechaSeleccionada = request.getParameter("fechabuscar");

            Venta v = VentaDAO.verificarfecha(fechaSeleccionada);
            if (v == null) {

                String mensajeC = "No posees ventas con esta fecha !";
                String mensajeT = "Venta en fecha especificada";
                request.getSession().setAttribute("servletMsgfC", mensajeC);
                request.getSession().setAttribute("servletMsgfT", mensajeT);
                response.sendRedirect("ErrorFechas.jsp");
            } else {

                request.getSession().setAttribute("servletMsgFecha", fechaSeleccionada);
                response.sendRedirect("ReporteBuscFecha.jsp");
            }
        }

        if (opcion.equals("BusRango")) {
            fechaRango1 = request.getParameter("fecharangoPrimera");
            fechaRango2 = request.getParameter("fecharangoSegunda");
            Venta v = VentaDAO.verificarRangofecha(fechaRango1, fechaRango2);
            if (v == null) {
                String mensajeC = "No posees ventas entre estos rangos de fecha !";
                String mensajeT = "Ventas en rangos de fechas especificadas";
                request.getSession().setAttribute("servletMsgfC", mensajeC);
                request.getSession().setAttribute("servletMsgfT", mensajeT);
                response.sendRedirect("ErrorFechas.jsp");
            } else {
                request.getSession().setAttribute("servletMsgRango1", fechaRango1);
                request.getSession().setAttribute("servletMsgRango2", fechaRango2);
                response.sendRedirect("ReporteRangoFecha.jsp");
            }

        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorVentas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorVentas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
