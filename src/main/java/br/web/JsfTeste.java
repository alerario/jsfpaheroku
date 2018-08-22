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
public class JsfTeste {

    /**
     * Creates a new instance of JsfTeste
     */
    public JsfTeste() {
    }

    private int codigo;
    private String nome;
    private int codTeste1;

    public int getCodTeste1() {
        return codTeste1;
    }

    public void setCodTeste1(int codTeste1) {
        this.codTeste1 = codTeste1;
    }

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
        br.data.entity.Teste tes;
        tes = new br.data.entity.Teste();
        tes.setCodigo(codigo);
        tes.setNome(nome);
        tes.setTeste1(new CrudTeste1().find(this.getCodTeste1()));
        Exception insert = new br.data.crud.CrudTeste().persist(tes);
        if (insert == null) {
            this.setCodigo(0);
            this.setNome("");
            this.setCodTeste1(0);
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

    public java.util.List<br.data.entity.Teste> getAll() {
        return new br.data.crud.CrudTeste().getAll();
    }

    public java.util.List<br.data.entity.Teste> getSelect() {
        if (this.nome != null && !this.nome.equals("")) {
            return new br.data.crud.CrudTeste().SelectByNome(nome);
        } else {
            return null;
        }
    }

    public void remove(br.data.entity.Teste teste) {
        Exception e =new br.data.crud.CrudTeste().remove(teste);
         if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String update(br.data.entity.Teste teste) {
        this.codigo = teste.getCodigo();
        this.nome = teste.getNome();
        this.codTeste1 = teste.getTeste1().getCodigo();
        return "merge.xhtml";
    }

    public String merge() {
        br.data.entity.Teste tes;
        tes = new br.data.crud.CrudTeste().find(this.codigo);
        tes.setNome(nome);
        tes.setTeste1(new CrudTeste1().find(this.codTeste1));
        Exception e = new br.data.crud.CrudTeste().merge(tes);
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
    
}
