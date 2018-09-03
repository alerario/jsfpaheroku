/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web;

import br.data.crud.CrudTeste1;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author alexandrelerario
 */
@ManagedBean
@RequestScoped
public class JsfProduto {

    /**
     * Creates a new instance of JsfTeste
     */
    public JsfProduto() {
    }





    public java.util.List<br.data.entity.Produto> getAll() {
        return new br.data.crud.CrudProduto().getAll();
    }
    
}
