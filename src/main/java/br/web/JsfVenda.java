/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web;

import br.data.entity.Itemvenda;
import br.data.entity.Produto;
import br.data.entity.Venda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
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
public class JsfVenda {

    /**
     * Creates a new instance of JsfCompra
     */
    public JsfVenda() {
        litem = new ArrayList();
    }

    private java.util.Collection<br.data.entity.Itemvenda> litem;

    public void addProduto(Produto prod) {
        boolean achou = false;
        for (Itemvenda itemvenda : litem) {
            if (Objects.equals(itemvenda.getProduto().getCodigo(), prod.getCodigo())) {
                //o produto ja esta no carrinho
                itemvenda.setQuantidade(itemvenda.getQuantidade() + 1);
                achou = true;
                break;
            }
        }
        if (!achou) { //o produto nao esta no carrinho
            Itemvenda ic = new Itemvenda();
            ic.setProduto(prod);
            ic.setQuantidade(1);
            litem.add(ic);
        }
    }

    public void removeProduto(Produto prod) {

        for (Itemvenda itemvenda : litem) {
            if (itemvenda.getProduto().getCodigo() == prod.getCodigo()) {
                if (itemvenda.getQuantidade() == 1) {
                    litem.remove(itemvenda);
                } else {
                    itemvenda.setQuantidade(itemvenda.getQuantidade() - 1);
                }
                break;
            }
        }
    }

    public void concluirVenda() {
        Venda venda = new Venda();
        venda.setData(new Date());
        venda.setItemvendaCollection(litem);
        Exception insert = new br.data.crud.CrudVenda().persist(venda);

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

    public Collection<Itemvenda> getLitem() {
        return litem;
    }

    public void setLitem(Collection<Itemvenda> litem) {
        this.litem = litem;
    }

    public java.util.Collection<br.data.entity.Venda> getAll() {
        return new br.data.crud.CrudVenda().getAll();
    }

}
