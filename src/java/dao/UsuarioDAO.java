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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Usuario;

/**
 *
 * @author Fox
 */
public class UsuarioDAO {

    private static Usuario usuario;
    private static Conectar conn;
    private static ArrayList<Usuario> alUsuarios = new ArrayList<>();

    private EntityManager em = null;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PruebaUltimaPrograPU", System.getProperties());

    
    
    public static Usuario conectarUsuario(String userva, String claveva) {

        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "select * from usuario where user='" + userva + "' and clave='" + claveva + "';";
                ResultSet rs = st.executeQuery(query);

                if (rs.first()) {
                    Usuario u = new Usuario();
                    u.setNombre(rs.getString("nombre"));
                    u.setApellidoP(rs.getString("apellidoP"));
                    u.setApellidoM(rs.getString("apellidoM"));
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setTipousuario(rs.getInt("tipousuario"));
                    u.setRut(rs.getString("rut"));

                    return u;
                }

            } catch (Exception e) {
                System.out.println("Error no se a podido encontrar el usuario" + e);
            }

        }
        return null;
    }

    public static ArrayList<Usuario> obtenerDatos() {
        usuario = null;
        alUsuarios.clear();
        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "select * from usuario";

                ResultSet rs = st.executeQuery(query);

                int id_usuario = 0;
                String rut = "";
                String nombre = "";
                String apellidoP = "";
                String apellidoM = "";
                int tipousuario = 0;
                String user = "";
                String clave = "";

                while (rs.next()) {
                    //System.out.printlnt("Entro al while");
                    id_usuario = rs.getInt("id_usuario");
                    rut = rs.getString("rut");
                    nombre = rs.getString("nombre");
                    apellidoP = rs.getString("apellidoP");
                    apellidoM = rs.getString("apellidoM");
                    tipousuario = rs.getInt("tipousuario");
                    user = rs.getString("user");
                    clave = rs.getString("clave");

                    Usuario obj = new Usuario(id_usuario, rut, nombre, apellidoP, apellidoM, tipousuario, user, clave);

                    //System.out.prinln("Usuario:"+obj.toString());
                    alUsuarios.add(obj);

                }
                connection.close();

            } catch (Exception e) {
                System.out.println("Error: UsuarioDao obtenerdatos:" + e.getMessage());
            }
        }
        return alUsuarios;
    }

    // metodos de EntityManager
    public boolean ingresarUsuario(Usuario usuario) {
        boolean estado = false;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                em.persist(usuario);
                em.flush();
                em.getTransaction().commit();
                estado = true;
            } else {
                System.out.println("- Error ingresar UsuarioDAO: EM vacio.");
            }

        } catch (Exception e) {
            System.out.println("- Error ingresar UsuarioDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }

        }
        return estado;
    }

    
    /**
     * 
     * @param idusuario identificador de usuario
     * @return retorna un objeto usuario o "null" si no lo encuentra
     */
    public Usuario buscarUsuario(int idusuario) {
        usuario = null;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                usuario = em.find(Usuario.class, idusuario);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("- Error buscar UsuarioDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return usuario;
    }

    /**
     * 
     * @param idusuario Recibe el identificador de usuario
     * @return null si no se realiza, "Eliminado" si lo realiza, "noexiste" si no lo encuentra
     */
    public String eliminarUsuario(int idusuario) {
        String estado = null;
        usuario = null;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                usuario = em.find(Usuario.class, idusuario);
                if (usuario != null) {
                    em.remove(usuario);
                    em.flush();
                    em.getTransaction().commit();
                    estado = "Eliminado";
                } else {
                    System.out.println("- Error eliminar UsuarioDAO: cliente no encontrado");
                    estado = "noexiste";
                }
            }
        } catch (Exception e) {
            System.out.println("- Error eliminar UsuarioDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estado;
    }

    
    /**
     * 
     * @param usuario
     * @return "null" si no lo realiza, "Actualizado" si lo realiza, "noexiste" si no lo encuentra
     */
    public String actualizarUsuario(Usuario usuario) {
        String estado = null;
        Usuario usuariobd;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                usuariobd = em.find(Usuario.class, usuario.getIdUsuario());
                if (usuariobd.getIdUsuario().equals(usuario.getIdUsuario())) {
                    em.merge(usuario);
                    em.flush();
                    em.getTransaction().commit();
                    estado = "Actualizado";
                } else {
                    System.out.println("- Error actualizar UsuarioDAO: usuario no encontrado");
                    estado = "noexiste";
                }
            } else {
                System.out.println("- Error actualizar UsuarioDAO: problemas con la base de datos");
            }
        } catch (Exception e) {
            System.out.println("- Error actualiza UsuarioDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estado;
    }

    public List<Usuario> listaUsuarios() {
        List<Usuario> usuarios = null;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                javax.persistence.Query q = em.createQuery("SELECT c FROM Usuario c");
                usuarios = q.getResultList();
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            System.out.println("- Error listar UsuarioDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return usuarios;
    }

    
     public static boolean bloquearUser(int userBlock, String userAct) {
        boolean estado = false;
        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "update usuario set tipousuario='" + userBlock + "' where user='" + userAct + "';";
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
    
    public static Usuario VerificarBloqueo(String userE) {

        conn = new Conectar();
        if (conn != null) {
            try {
                Connection connection = conn.getconnection();
                Statement st = connection.createStatement();
                String query = "select * from usuario where user='" + userE + "' and tipousuario=3;";
                ResultSet rs = st.executeQuery(query);

                if (rs.first()) {
                    Usuario u = new Usuario();
                    u.setNombre(rs.getString("nombre"));
                    u.setApellidoP(rs.getString("apellidoP"));
                    u.setApellidoM(rs.getString("apellidoM"));
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setTipousuario(rs.getInt("tipousuario"));
                    u.setRut(rs.getString("rut"));

                    return u;
                }

            } catch (Exception e) {
                System.out.println("Error no se a podido encontrar el usuario" + e);
            }

        }
        return null;
    }

}
