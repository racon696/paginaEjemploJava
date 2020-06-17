/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fox
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByFolioventa", query = "SELECT v FROM Venta v WHERE v.folioventa = :folioventa")
    , @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "Venta.findByHora", query = "SELECT v FROM Venta v WHERE v.hora = :hora")
    , @NamedQuery(name = "Venta.findByNroboleta", query = "SELECT v FROM Venta v WHERE v.nroboleta = :nroboleta")
    , @NamedQuery(name = "Venta.findByIdUsuario", query = "SELECT v FROM Venta v WHERE v.idUsuario = :idUsuario")
    , @NamedQuery(name = "Venta.findByIdCliente", query = "SELECT v FROM Venta v WHERE v.idCliente = :idCliente")
    , @NamedQuery(name = "Venta.findByIdProducto", query = "SELECT v FROM Venta v WHERE v.idProducto = :idProducto")
    , @NamedQuery(name = "Venta.findByCantidad", query = "SELECT v FROM Venta v WHERE v.cantidad = :cantidad")
    , @NamedQuery(name = "Venta.findByTotal", query = "SELECT v FROM Venta v WHERE v.total = :total")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "folioventa")
    private Integer folioventa;
    @Size(max = 10)
    @Column(name = "fecha")
    private String fecha;
    @Size(max = 10)
    @Column(name = "hora")
    private String hora;
    @Column(name = "nroboleta")
    private Integer nroboleta;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Column(name = "id_producto")
    private Integer idProducto;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "total")
    private Integer total;

    public Venta(Integer folioventa, String fecha, String hora, Integer nroboleta, Integer idUsuario, Integer idCliente, Integer idProducto, Integer cantidad, Integer total) {
        this.folioventa = folioventa;
        this.fecha = fecha;
        this.hora = hora;
        this.nroboleta = nroboleta;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    
    public Venta() {
    }

    public Venta(Integer folioventa) {
        this.folioventa = folioventa;
    }

    public Integer getFolioventa() {
        return folioventa;
    }

    public void setFolioventa(Integer folioventa) {
        this.folioventa = folioventa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getNroboleta() {
        return nroboleta;
    }

    public void setNroboleta(Integer nroboleta) {
        this.nroboleta = nroboleta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (folioventa != null ? folioventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.folioventa == null && other.folioventa != null) || (this.folioventa != null && !this.folioventa.equals(other.folioventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Venta[ folioventa=" + folioventa + " ]";
    }
    
    
}
