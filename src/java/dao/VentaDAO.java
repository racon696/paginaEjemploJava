/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import modelo.Venta;

/**
 *
 * @author Fox
 */
public class VentaDAO {

    private static Venta ventas;
    private static ArrayList<Venta> alVentas = new ArrayList<>();
    private static Conectar conn;

    private EntityManager em = null;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PruebaUltimaPrograPU", System.getProperties());

    public static ArrayList<Venta> buscarRango(String rango1, String rango2) {
        ventas = null;
        alVentas.clear();
        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "select * from venta where fecha between '" + rango1 + "' and '" + rango2 + "';";

                ResultSet rs = st.executeQuery(query);
                Integer folioventa = 0;
                String fecha = "";
                String hora = "";
                Integer nroboleta = 0;
                Integer id_usuario = 0;
                Integer id_cliente = 0;
                Integer id_producto = 0;
                Integer cantidad = 0;
                Integer total = 0;

                while (rs.next()) {
                    //System.out.printlnt("Entro al while");
                    folioventa = rs.getInt("folioventa");
                    fecha = rs.getString("fecha");
                    hora = rs.getString("hora");
                    nroboleta = rs.getInt("nroboleta");
                    id_usuario = rs.getInt("id_usuario");
                    id_cliente = rs.getInt("id_cliente");
                    id_producto = rs.getInt("id_producto");
                    cantidad = rs.getInt("cantidad");
                    total = rs.getInt("total");

                    Venta obj = new Venta(folioventa, fecha, hora, nroboleta, id_usuario, id_cliente, id_producto, cantidad, total);

                    //System.out.prinln("Usuario:"+obj.toString());
                    alVentas.add(obj);

                }
                connection.close();

            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }
        return alVentas;
    }

    public static ArrayList<Venta> buscarFecha(String fecha) {
        ventas = null;
        alVentas.clear();
        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "select * from venta where fecha='" + fecha + "';";

                ResultSet rs = st.executeQuery(query);
                int folioventa = 0;
                fecha = "";
                String hora = "";
                int nroboleta = 0;
                int id_usuario = 0;
                int id_cliente = 0;
                int id_producto = 0;
                int cantidad = 0;
                int total = 0;

                while (rs.next()) {
                    //System.out.printlnt("Entro al while");
                    folioventa = rs.getInt("folioventa");
                    fecha = rs.getString("fecha");
                    hora = rs.getString("hora");
                    nroboleta = rs.getInt("nroboleta");
                    id_usuario = rs.getInt("id_usuario");
                    id_cliente = rs.getInt("id_cliente");
                    id_producto = rs.getInt("id_producto");
                    cantidad = rs.getInt("cantidad");
                    total = rs.getInt("total");

                    Venta obj = new Venta(folioventa, fecha, hora, nroboleta, id_usuario, id_cliente, id_producto, cantidad, total);

                    //System.out.prinln("Usuario:"+obj.toString());
                    alVentas.add(obj);

                }
                connection.close();

            } catch (Exception e) {
                System.out.println("Error: SalaDao obtenerdatos:" + e.getMessage());
            }
        }
        return alVentas;
    }

    public static ArrayList<Venta> obtenerDatos() {
        ventas = null;
        alVentas.clear();
        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "select * from venta";

                ResultSet rs = st.executeQuery(query);
                int folioventa = 0;
                String fecha = "";
                String hora = "";
                int nroboleta = 0;
                int id_usuario = 0;
                int id_cliente = 0;
                int id_producto = 0;
                int cantidad = 0;
                int total = 0;

                while (rs.next()) {
                    //System.out.printlnt("Entro al while");
                    folioventa = rs.getInt("folioventa");
                    fecha = rs.getString("fecha");
                    hora = rs.getString("hora");
                    nroboleta = rs.getInt("nroboleta");
                    id_usuario = rs.getInt("id_usuario");
                    id_cliente = rs.getInt("id_cliente");
                    id_producto = rs.getInt("id_producto");
                    cantidad = rs.getInt("cantidad");
                    total = rs.getInt("total");

                    Venta obj = new Venta(folioventa, fecha, hora, nroboleta, id_usuario, id_cliente, id_producto, cantidad, total);

                    //System.out.prinln("Usuario:"+obj.toString());
                    alVentas.add(obj);

                }
                connection.close();

            } catch (Exception e) {
                System.out.println("Error: SalaDao obtenerdatos:" + e.getMessage());
            }
        }
        return alVentas;
    }

    // metodos Entity Manager
    public boolean ingresarVenta(Venta venta) {
        boolean estado = false;
        try {
            ValidatorFactory vfactory = Validation.buildDefaultValidatorFactory();
            Validator validator = vfactory.getValidator();
            Set<ConstraintViolation<Venta>> constraintViolations = validator.validate(venta);
            if (constraintViolations.size() > 0) {
                Iterator<ConstraintViolation<Venta>> iterator = constraintViolations.iterator();
                while (iterator.hasNext()) {
                    ConstraintViolation<Venta> cv = iterator.next();
                    System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());

                    System.out.println("- Error ingresar VentaDAO: "+cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
                }
            } else {
                em = factory.createEntityManager();
                if (em != null) {
                    em.getTransaction().begin();
                    em.persist(venta);
                    em.flush();
                    em.getTransaction().commit();
                    estado = true;
                } else {
                    System.out.println("- Error ingresar VentaDAO: EM vacio.");
                }
            }
        } catch (Exception e) {
            System.out.println("- Error ingresar VentaDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }

        }
        return estado;
    }

    public Venta buscarVenta(int folioventa) {
        Venta venta = null;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                venta = em.find(Venta.class, folioventa);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("- Error buscar VentaDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return venta;
    }

    public String eliminarVenta(int folioventa) {
        String estado = null;
        Venta venta;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                venta = em.find(Venta.class, folioventa);
                if (venta != null) {
                    em.remove(venta);
                    em.flush();
                    em.getTransaction().commit();
                    estado = "Eliminado";
                } else {
                    System.out.println("- Error eliminar VentaDAO: venta no encontrado");
                    estado = "noexiste";
                }
            }
        } catch (Exception e) {
            System.out.println("- Error eliminar VentaDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estado;
    }

    public String actualizarVenta(Venta venta) {
        String estado = null;
        Venta ventabd;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                ventabd = em.find(Venta.class, venta.getFolioventa());
                if (ventabd == venta) {
                    em.merge(venta);
                    em.flush();
                    em.getTransaction().commit();
                    estado = "Actualizado";
                } else {
                    System.out.println("- Error actualizar VentaDAO: cliente no encontrado");
                    estado = "noexiste";
                }
            } else {
                System.out.println("- Error actualizar VentaDAO: problemas con la base de datos");
            }
        } catch (Exception e) {
            System.out.println("- Error actualiza VentaDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estado;
    }

    public List<Venta> listaVentas() {
        List<Venta> liventas = null;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                javax.persistence.Query q = em.createQuery("SELECT v FROM Venta v");
                System.out.println("- comprobacion listar VentaDAO : " + q.getResultList().toString());
                liventas = q.getResultList();
                System.out.println("- comprobacion listar VentaDAO : " + liventas.toString());
                em.getTransaction().commit();
                System.out.println("- comprobacion listar VentaDAO : " + liventas.toString());
            }

        } catch (Exception e) {
            System.out.println("- Error listar VentaDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return liventas;
    }

    public List buscaXFolio(String fol_venta) {
        return em.createQuery(
                "SELECT v FROM Venta v WHERE v.folioventa LIKE :folioVenta")
                .setParameter("folioVenta", fol_venta)
                .getResultList();
    }

    public List listaDeTodasLasVentas() {

        em = factory.createEntityManager();
        em.getTransaction().begin();
        return em.createQuery(
                "SELECT v FROM Venta v ORDER BY v.fecha")
                .getResultList();

    }
    
    public static Venta verificarfecha(String fecha) {

        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "select * from venta where fecha='" + fecha + "';";
                ResultSet rs = st.executeQuery(query);

                if (rs.first()) {
                    Venta v = new Venta();
                    v.setFolioventa(rs.getInt("folioventa"));
                    v.setFecha(rs.getString("fecha"));
                    v.setHora(rs.getString("hora"));
                    v.setNroboleta(rs.getInt("nroboleta"));
                    v.setIdUsuario(rs.getInt("id_usuario"));
                    v.setIdCliente(rs.getInt("id_cliente"));
                    v.setIdProducto(rs.getInt("id_producto"));
                    v.setCantidad(rs.getInt("cantidad"));
                    v.setTotal(rs.getInt("total"));

                    return v;
                }

            } catch (Exception e) {
                System.out.println("Error no se a podido encontrar el usuario" + e);
            }

        }
        return null;
    }

    public static Venta verificarRangofecha(String fecha1, String fecha2) {

        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "select * from venta where fecha between '" + fecha1 + "' and '" + fecha2 + "';";
                ResultSet rs = st.executeQuery(query);

                if (rs.first()) {
                    Venta v = new Venta();
                    v.setFolioventa(rs.getInt("folioventa"));
                    v.setFecha(rs.getString("fecha"));
                    v.setHora(rs.getString("hora"));
                    v.setNroboleta(rs.getInt("nroboleta"));
                    v.setIdUsuario(rs.getInt("id_usuario"));
                    v.setIdCliente(rs.getInt("id_cliente"));
                    v.setIdProducto(rs.getInt("id_producto"));
                    v.setCantidad(rs.getInt("cantidad"));
                    v.setTotal(rs.getInt("total"));

                    return v;
                }

            } catch (Exception e) {
                System.out.println("Error no se a podido encontrar el usuario" + e);
            }

        }
        return null;
    }

}
