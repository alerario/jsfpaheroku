/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web;

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
public class JsfTeste1 {

    /**
     * Creates a new instance of JsfTeste1
     */
    public JsfTeste1() {
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

    public String persist() {
        br.data.entity.Teste1 tes;
        tes = new br.data.entity.Teste1();
        tes.setCodigo(codigo);
        tes.setNome(nome);
        Exception insert = new br.data.crud.CrudTeste1().persist(tes);
        if (insert == null) {
            this.setCodigo(0);
            this.setNome("");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro adicionado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = insert.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
        
        return "/operacoes/index.xhtml";
    }

    public java.util.List<br.data.entity.Teste1> getAll() {
        return new br.data.crud.CrudTeste1().getAll();
    }

    public java.util.List<br.data.entity.Teste1> getSelect() {
        if (this.nome != null && !this.nome.equals("")) {
            return new br.data.crud.CrudTeste1().SelectByNome(nome);
        } else {
            return null;
        }
    }

    public void remove(br.data.entity.Teste1 teste) {
        Exception e =new br.data.crud.CrudTeste1().remove(teste);
         if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String update(br.data.entity.Teste1 teste) {
        this.codigo = teste.getCodigo();
        this.nome = teste.getNome();
        return "merge.xhtml";
    }

    public String merge() {
        br.data.entity.Teste1 tes;
        tes = new br.data.crud.CrudTeste1().find(this.codigo);
        tes.setNome(nome);
        Exception e = new br.data.crud.CrudTeste1().merge(tes);
        if (e == null) {
            this.setCodigo(0);
            this.setNome("");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro alterado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "/operacoes/index.xhtml";
    }
    
    public void buttonAction(ActionEvent actionEvent) {
        System.out.println("ola mundo ======================");
    }
}
