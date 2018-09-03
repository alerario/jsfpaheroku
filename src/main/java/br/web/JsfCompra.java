/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web;

import br.data.entity.Compra;
import br.data.entity.Itemcompra;
import br.data.entity.Produto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexandrelerario
 */
@ManagedBean
@SessionScoped //este ManagedBean implementa um carrinho de compras
public class JsfCompra {

    /**
     * Creates a new instance of JsfCompra
     */
    public JsfCompra() {
        litem = new ArrayList();
    }

    private java.util.Collection<br.data.entity.Itemcompra> litem;

    public void addProduto(Produto prod) {
        boolean achou = false;
        for (Itemcompra itemcompra : litem) {
            if (itemcompra.getProduto1().getCodigo() == prod.getCodigo()) {
                //o produto ja esta no carrinho
                itemcompra.setQuantidade(itemcompra.getQuantidade() + 1);
                achou = true;
                break;
            }
        }
        if (!achou) { //o produto nao esta no carrinho
            Itemcompra ic = new Itemcompra();
            ic.setProduto1(prod);
            ic.setQuantidade(1);
            litem.add(ic);
        }
    }

    public void removeProduto(Produto prod) {

        for (Itemcompra itemcompra : litem) {
            if (itemcompra.getProduto1().getCodigo() == prod.getCodigo()) {
                if (itemcompra.getQuantidade() == 1) {
                    litem.remove(itemcompra);
                } else {
                    itemcompra.setQuantidade(itemcompra.getQuantidade() - 1);
                }
                break;
            }
        }
    }

    public void concluirCompra() {
        Compra compra = new Compra();
        compra.setData(new Date());
        compra.setItemcompraCollection(litem);
        Exception insert = new br.data.crud.CrudCompra().persist(compra);

        if (insert == null) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro adicionado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = insert.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

        litem = new ArrayList();
    }

    public Collection<Itemcompra> getLitem() {
        return litem;
    }

    public void setLitem(Collection<Itemcompra> litem) {
        this.litem = litem;
    }

    public java.util.Collection<br.data.entity.Compra> getAll() {
        return new br.data.crud.CrudCompra().getAll();
    }

}
