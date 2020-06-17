/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Cliente;

/**
 *
 * @author Fox
 */
public class ClienteDAO {

    private EntityManager em = null;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PruebaUltimaPrograPU", System.getProperties());

    public boolean ingresarCliente(Cliente cliente) {
        boolean estado = false;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                em.persist(cliente);
                em.flush();
                em.getTransaction().commit();
                estado = true;
            } else {
                System.out.println("- Error ingresar ClienteDAO: EM vacio.");
            }

        } catch (Exception e) {
            System.out.println("- Error ingresar ClienteDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }

        }
        return estado;
    }

    public Cliente buscarCliente(int idcliente) {
        Cliente cliente = null;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                cliente = em.find(Cliente.class, idcliente);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("- Error buscar ClienteDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return cliente;
    }

    public String eliminarCliente(int idcliente) {
        String estado = null;
        Cliente cliente;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                cliente = em.find(Cliente.class, idcliente);
                if (cliente != null) {
                    em.remove(cliente);
                    em.flush();
                    em.getTransaction().commit();
                    estado = "Eliminado";
                } else {
                    System.out.println("- Error eliminar ClienteDAO: cliente no encontrado");
                    estado = "noexiste";
                }
            }
        } catch (Exception e) {
            System.out.println("- Error eliminar ClienteDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estado;
    }

    public String actualizarCliente(Cliente cliente) {
        String estado = null;
        Cliente clientebd;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                clientebd = em.find(Cliente.class, cliente.getIdCliente());
                if (clientebd == cliente) {
                    em.merge(cliente);
                    em.flush();
                    em.getTransaction().commit();
                    estado = "Actualizado";
                } else {
                    System.out.println("- Error actualizar ClienteDAO: cliente no encontrado");
                    estado = "noexiste";
                }
            } else {
                System.out.println("- Error actualizar ClienteDAO: problemas con la base de datos");
            }
        } catch (Exception e) {
            System.out.println("- Error actualiza ClienteDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estado;
    }

    public List<Cliente> listaClientes() {
        List<Cliente> clientes = null;
        try {
            em = factory.createEntityManager();
            if (em != null) {
                em.getTransaction().begin();
                javax.persistence.Query q = em.createQuery("SELECT c FROM Cliente c");
                clientes = q.getResultList();
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            System.out.println("- Error listar ClienteDAO: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return clientes;
    }
}
