/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.data.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexandrelerario
 */
@Embeddable
public class ItemcompraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "compra")
    private int compra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "produto")
    private int produto;

    public ItemcompraPK() {
    }

    public ItemcompraPK(int compra, int produto) {
        this.compra = compra;
        this.produto = produto;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) compra;
        hash += (int) produto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemcompraPK)) {
            return false;
        }
        ItemcompraPK other = (ItemcompraPK) object;
        if (this.compra != other.compra) {
            return false;
        }
        if (this.produto != other.produto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.data.entity.ItemcompraPK[ compra=" + compra + ", produto=" + produto + " ]";
    }
    
}
