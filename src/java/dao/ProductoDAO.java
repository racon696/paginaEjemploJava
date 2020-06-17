package dao;

import conexion.Conectar;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Producto;

/**
 *
 * @author Racon
 */
public class ProductoDAO {
    private Producto producto;
    private static Conectar conn;
    private EntityManager em = null;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PruebaUltimaPrograPU", System.getProperties());

    public boolean ingresarProducto(Producto producto) {
        boolean estado = false;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                em.persist(producto);
                em.flush();
                em.getTransaction().commit();
                estado = true;
            } else {
                System.out.println("- Error ingresar ProductoDAO: EM vacio.");
            }

        } catch (Exception e) {
            System.out.println("- Error ingresar ProductoDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }

        }
        return estado;
    }

    public Producto buscarProducto(int idproducto) {
        producto = null;
        try {
            em = factory.createEntityManager();
            
            if (em != null) {
                producto = new Producto();
                em.getTransaction().begin();
                producto = em.find(Producto.class, idproducto);
                em.getTransaction().commit();
            }else{
                System.out.println("- Error buscar ProductoDAO : entity manager vacio");
            }
        } catch (Exception e) {
            System.out.println("- Error buscar ProductoDAO : " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return producto;
    }

    public String eliminarProducto(int idproducto) {
        String estado = null;
        producto=null;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                producto = em.find(Producto.class, idproducto);
                if (producto != null) {
                    em.remove(producto);
                    em.flush();
                    em.getTransaction().commit();
                    estado = "Eliminado";
                } else {
                    System.out.println("- Error eliminar ProductoDAO: producto no encontrado");
                    estado = "noexiste";
                }
            }
        } catch (Exception e) {
            System.out.println("- Error eliminar ProductoDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estado;
    }

    public String actualizarProducto(Producto producto) {
        String estado = null;
        
        Producto productobd;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                productobd = em.find(Producto.class, producto.getIdProducto());
                if (productobd == producto) {
                    em.merge(producto);
                    em.flush();
                    em.getTransaction().commit();
                    estado = "Actualizado";
                } else {
                    System.out.println("- Error actualizar ProductoDAO: producto no encontrado");
                    estado = "noexiste";
                }
            } else {
                System.out.println("- Error actualizar ProductoDAO: problemas con la base de datos");
            }
        } catch (Exception e) {
            System.out.println("- Error actualiza ProductoDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estado;
    }

    public List<Producto> listaProductos() {
        List<Producto> productos = null;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                javax.persistence.Query q = em.createQuery("SELECT c FROM Producto c");
                productos = q.getResultList();
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            System.out.println("- Error listar ProductoDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return productos;
    }

    public static boolean DescontarStock(int StockFinal, int id_producto) {
        boolean estado = false;
        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "update producto set stock='" + StockFinal + "' where id_producto='" + id_producto + "';";
                int filas = st.executeUpdate(query);
                if (filas > 0) {
                    estado = true;
                }
            } catch (Exception e) {
                System.err.println("Error ProductoDAO bloquearUser:" + e.getMessage());
            }
        }
        return estado;
    }
}
