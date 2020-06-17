/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProductoDAO;
import dao.VentaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Producto;
import modelo.Usuario;
import modelo.Venta;

/**
 *
 * @author Gateway
 */
public class ControladorVenta extends HttpServlet {

    private Usuario usuarioactual=null;
    private ProductoDAO pdao = null;
    private Producto p = null;
    private Venta v = null;
    private VentaDAO vdao = null;
    private List<Venta> ventas = null;

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

            HttpSession sessionactual = request.getSession();
                    
            if(sessionactual.getAttribute("usuario")!=null){
                usuarioactual = (Usuario)sessionactual.getAttribute("usuario");
            }       
            
            //Declaracion de variables
            int folioventa = 0;
            String fecha = "";
            String hora = "";
            int nroboleta = 0;
            int id_usuario = 0;
            int id_cliente = 0;
            int id_producto = 0;
            int cantidad = 0;
            int total = 0;

            //obtención de variables
            String link = request.getContextPath();
            String opcion = request.getParameter("opcion");

            if (opcion.equals("Agregar")) {
                
                vdao = new VentaDAO();
                v = new Venta();

                if (request.getParameter("folioventa") != null && !request.getParameter("folioventa").equals("")
                        && request.getParameter("fecha") != null && request.getParameter("hora") != null 
                        && request.getParameter("nroboleta") != null && request.getParameter("id_usuario") != null 
                        && request.getParameter("id_cliente") != null && request.getParameter("id_producto") != null 
                        && request.getParameter("cantidad") != null && request.getParameter("total") != null) {

                    System.out.println("- comprobación agregar controladorVenta : folio ingresado : "+request.getParameter("folioventa"));
                    
                    folioventa = Integer.parseInt(request.getParameter("folioventa"));
                    fecha = request.getParameter("fecha");
                    hora = request.getParameter("hora");
                    nroboleta = Integer.parseInt(request.getParameter("nroboleta"));
                    id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
                    id_producto = Integer.parseInt(request.getParameter("id_producto"));
                    cantidad = Integer.parseInt(request.getParameter("cantidad"));
                    total = Integer.parseInt(request.getParameter("total"));

                    v.setFolioventa(folioventa);
                    v.setFecha(fecha);
                    v.setHora(hora);
                    v.setNroboleta(nroboleta);
                    v.setIdUsuario(id_usuario);
                    v.setIdCliente(id_cliente);
                    v.setIdProducto(id_producto);
                    v.setCantidad(cantidad);
                    v.setTotal(total);

                    if (vdao.ingresarVenta(v)) {
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Agregado");
                        
                        switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("-ControladorVenta : venta ingresado correctamente");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Venta NO Agregada");
                        switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : agregar ControladorVenta : venta no ingresado");
                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "Agregar No realizado: Existen campos vacios");
                    switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error agregar controladorVenta: id cero o negativo");
                }
            }

            if (opcion.equals("Buscar")) {
                vdao = new VentaDAO();
                v = new Venta();

                if (request.getParameter("folioventa") != null) {

                    folioventa = Integer.parseInt(request.getParameter("folioventa"));

                    if (vdao.buscarVenta(folioventa) != null) {

                        v = vdao.buscarVenta(folioventa);
                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("ventabuscado", v);
                        //redirigir 
                        response.sendRedirect(link + "/CrudVenta/VentaEdit.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : buscar ControladorUsuario : buscado correctamente");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe una Venta con folio: " + folioventa);
                        switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : buscar ControladorVenta : venta no existe");
                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "No Ingresaste el folio de venta");
                    switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error : buscar ControladorVenta : id nulo");
                }
            }

            if (opcion.equals("Eliminar")) {

                vdao = new VentaDAO();
                if (request.getParameter("id_usuario") != null) {

                    folioventa = Integer.parseInt(request.getParameter("folioventa"));

                    if (vdao.eliminarVenta(folioventa).equalsIgnoreCase("Eliminado")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Eliminado");
                        switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : eliminar ControladorVenta : eliminado correctamente");

                    } else if (vdao.eliminarVenta(folioventa).equalsIgnoreCase("noexiste")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe una Venta con el folio: " + folioventa);
                        switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : eliminar ControladorVenta : venta no existe");

                    } else if (vdao.eliminarVenta(folioventa) == null) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                        switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : eliminar ControladorVenta : falla con la base de datos");

                    }
                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "No Ingresaste el folio de venta");
                    switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error : eliminar ControladorVenta : id nulo");

                }
            }

            if (opcion.equals("Actualizar")) {
                vdao = new VentaDAO();
                v = new Venta();

                if (request.getParameter("folioventa") != null && request.getParameter("fecha") != null
                        && request.getParameter("hora") != null && request.getParameter("nroboleta") != null
                        && request.getParameter("id_usuario") != null && request.getParameter("id_cliente") != null
                        && request.getParameter("id_producto") != null && request.getParameter("cantidad") != null
                        && request.getParameter("total") != null) {

                    folioventa = Integer.parseInt(request.getParameter("folioventa"));
                    fecha = request.getParameter("fecha");
                    hora = request.getParameter("hora");
                    nroboleta = Integer.parseInt(request.getParameter("nroboleta"));
                    id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
                    id_producto = Integer.parseInt(request.getParameter("id_producto"));
                    cantidad = Integer.parseInt(request.getParameter("cantidad"));
                    total = Integer.parseInt(request.getParameter("total"));

                    v.setFolioventa(folioventa);
                    v.setFecha(fecha);
                    v.setHora(hora);
                    v.setNroboleta(nroboleta);
                    v.setIdUsuario(id_usuario);
                    v.setIdCliente(id_cliente);
                    v.setIdProducto(id_producto);
                    v.setCantidad(cantidad);
                    v.setTotal(total);

                    if (vdao.actualizarVenta(v).equalsIgnoreCase("Actualizado")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Correctamente Actualizado");
                        switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                        
                        //redirigir 
                        response.sendRedirect("PageOK.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- OK : actualizar ControladorVenta : acualizado correctamente");

                    } else if (vdao.actualizarVenta(v).equalsIgnoreCase("noexiste")) {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! no existe una Venta con el folio: " + folioventa);
                        switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : actualizar ControladorVenta : venta no existe");

                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                        switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : actualizar ControladorVenta : falla con la base de datos");

                    }

                } else {

                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "Actualizar No realizado: Existen campos vacios");
                    switch (usuarioactual.getTipousuario()) {
                        case 0:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Vendedor.jsp");
                            break;
                        case 1:
                            request.getSession().setAttribute("servletMsg2", link+"/TipoDeUsuarios/Administrador.jsp");
                            break;
                        }
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error actualizar controladorVenta: folio no valido o vacio");

                }
            }

            if (opcion.equals("Listar")) {

                response.sendRedirect(link + "/CrudVenta/EditarVenta.jsp");

            }

            if (opcion.equals("Continuar")) {
                v = new Venta();
                pdao = new ProductoDAO();
                vdao = new VentaDAO();
                p = new Producto();
                ventas = null;

                if (request.getParameter("folioventa") == null && request.getParameter("fecha") == null
                        && request.getParameter("hora") == null && request.getParameter("nroboleta") == null
                        && request.getParameter("id_usuario") != null && request.getParameter("id_cliente") != null
                        && request.getParameter("id_producto") != null && request.getParameter("cantidad") != null
                        && request.getParameter("total") == null) {

                    id_producto = Integer.parseInt(request.getParameter("id_producto"));
                    System.out.println("- comprobacion : continuar ControladorVenta : id_producto " + id_producto);

                    if (pdao.buscarProducto(id_producto) != null) {

                        p = pdao.buscarProducto(id_producto);
                        int precio = p.getPrecio();
                        int stock = p.getStock();

                        cantidad = Integer.parseInt(request.getParameter("cantidad"));
                        System.out.println("- comprobacion : continuar ControladorVenta : p " + p.toString());
                        if (cantidad != 0) {
                            if (stock >= cantidad) {

                                System.out.println("- comprobacion : continuar ControladorVenta :(antes) stock " + stock + " cantidad " + cantidad);
                                try {
                                    List<Venta> ventasbd = (List) vdao.listaDeTodasLasVentas();
                                    System.out.println("- comprobacion : continuar ControladorVenta : " + vdao.listaDeTodasLasVentas().toString());
                                    System.out.println("- comprobacion : continuar ControladorVenta : " + vdao.listaDeTodasLasVentas().toString());
                                    ventas = ventasbd;
                                } catch (Exception e) {
                                    System.out.println("- Error : continuar ControladorVenta listar ventas: " + e.getMessage());
                                }

                                int ultimofolio = 0;
                                int ultimaboleta = 0;

                                for (Venta venta : ventas) {
                                    if (venta.getFolioventa() > ultimofolio) {
                                        ultimofolio = venta.getFolioventa();
                                        ultimaboleta = venta.getNroboleta();

                                    }
                                }

                                if (ultimofolio != 0 && ultimaboleta != 0) {

                                    Date fechasistema = new Date();
                                    SimpleDateFormat sdffecha = new SimpleDateFormat("yyyy-MM-dd");
                                    SimpleDateFormat sdfhora = new SimpleDateFormat("HH:mm:ss");

                                    folioventa = ultimofolio+1;
                                    fecha = sdffecha.format(fechasistema);
                                    hora = sdfhora.format(fechasistema);
                                    nroboleta = ultimaboleta+1;
                                    id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                                    id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
                                    id_producto = Integer.parseInt(request.getParameter("id_producto"));

                                    total = (int) (precio * cantidad);

                                    v.setFolioventa(folioventa);
                                    v.setFecha(fecha);
                                    v.setHora(hora);
                                    v.setNroboleta(nroboleta);
                                    v.setIdUsuario(id_usuario);
                                    v.setIdCliente(id_cliente);
                                    v.setIdProducto(id_producto);
                                    v.setCantidad(cantidad);
                                    v.setTotal(total);

                                    request.getSession().setAttribute("ventacontinuada", v);

                                    response.sendRedirect(link + "/FormVentaReal2.jsp");
                                } else {

                                    //añadir mensajes a la sesion
                                    request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                                    request.getSession().setAttribute("servletMsg2", link+"/FormVentaReal.jsp");
                                    //redirigir
                                    response.sendRedirect("PageError.jsp");
                                    //dejar un mensaje en consola
                                    System.out.println("- Error : continuar ControladorVenta : ultima venta no encontrada");
                                }

                            } else {

                                //añadir mensajes a la sesion
                                request.getSession().setAttribute("servletMsg1", "Hey! La Cantidad es mayor que el stock de productos");
                                request.getSession().setAttribute("servletMsg2", link+"/FormVentaReal.jsp");
                                //redirigir
                                response.sendRedirect("PageError.jsp");
                                //dejar un mensaje en consola
                                System.out.println("- Error : continuar ControladorVenta : Stock menor a Cantidad");
                            }
                        } else {

                            //añadir mensajes a la sesion
                            request.getSession().setAttribute("servletMsg1", "Hey! La Cantidad no puede ser 0");
                            request.getSession().setAttribute("servletMsg2", link+"/FormVentaReal.jsp");
                            //redirigir
                            response.sendRedirect("PageError.jsp");
                            //dejar un mensaje en consola
                            System.out.println("- Error : continuar ControladorVenta : Cantidad igual a 0");
                        }
                    } else {

                        //añadir mensajes a la sesion
                        request.getSession().setAttribute("servletMsg1", "Hey! hubo fallas con el servidor");
                        request.getSession().setAttribute("servletMsg2", link+"/FormVentaReal.jsp");
                        //redirigir
                        response.sendRedirect("PageError.jsp");
                        //dejar un mensaje en consola
                        System.out.println("- Error : continuar ControladorVenta : Producto no encontrado");
                    }

                } else {
                    //añadir mensajes a la sesion
                    request.getSession().setAttribute("servletMsg1", "Hey! Hay campos vacios, Revise bien los datos antes de continuar");
                    request.getSession().setAttribute("servletMsg2", link+"/FormVentaReal.jsp");
                    //redirigir
                    response.sendRedirect("PageError.jsp");
                    //dejar un mensaje en consola
                    System.out.println("- Error : continuar ControladorVenta : Recepción de variables incorrecta");

                    
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
