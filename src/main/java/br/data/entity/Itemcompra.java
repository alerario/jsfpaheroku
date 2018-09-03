/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.data.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexandrelerario
 */
@Entity
@Table(name = "itemcompra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemcompra.findAll", query = "SELECT i FROM Itemcompra i")
    , @NamedQuery(name = "Itemcompra.findByCompra", query = "SELECT i FROM Itemcompra i WHERE i.itemcompraPK.compra = :compra")
    , @NamedQuery(name = "Itemcompra.findByQuantidade", query = "SELECT i FROM Itemcompra i WHERE i.quantidade = :quantidade")
    , @NamedQuery(name = "Itemcompra.findByProduto", query = "SELECT i FROM Itemcompra i WHERE i.itemcompraPK.produto = :produto")})
public class Itemcompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemcompraPK itemcompraPK;
    @Column(name = "quantidade")
    private Integer quantidade;
    @JoinColumn(name = "compra", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compra compra1;
    @JoinColumn(name = "produto", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto1;

    public Itemcompra() {
    }

    public Itemcompra(ItemcompraPK itemcompraPK) {
        this.itemcompraPK = itemcompraPK;
    }

    public Itemcompra(int compra, int produto) {
        this.itemcompraPK = new ItemcompraPK(compra, produto);
    }

    public ItemcompraPK getItemcompraPK() {
        return itemcompraPK;
    }

    public void setItemcompraPK(ItemcompraPK itemcompraPK) {
        this.itemcompraPK = itemcompraPK;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Compra getCompra1() {
        return compra1;
    }

    public void setCompra1(Compra compra1) {
        this.compra1 = compra1;
    }

    public Produto getProduto1() {
        return produto1;
    }

    public void setProduto1(Produto produto1) {
        this.produto1 = produto1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemcompraPK != null ? itemcompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemcompra)) {
            return false;
        }
        Itemcompra other = (Itemcompra) object;
        if ((this.itemcompraPK == null && other.itemcompraPK != null) || (this.itemcompraPK != null && !this.itemcompraPK.equals(other.itemcompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.data.entity.Itemcompra[ itemcompraPK=" + itemcompraPK + " ]";
    }
    
}
