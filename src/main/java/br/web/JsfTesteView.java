/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author alexandrelerario
 */
@ManagedBean
@ViewScoped
public class JsfTesteView {

    /**
     * Creates a new instance of JsfTeste
     */
    public JsfTesteView() {
    }

    private int codigo;
    private String nome;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public java.util.List<br.data.entity.Teste> getSelect() {
        if (this.nome != null && !this.nome.equals("")) {
            return new br.data.crud.CrudTeste().SelectByNome(nome);
        } else {
            return null;
        }
    }

  
}
