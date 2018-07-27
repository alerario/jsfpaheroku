/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.data.crud;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author alexandrelerario
 */
public class CrudTeste1 extends AbstractCrud<br.data.entity.Teste1> {

    private EntityManager em;

    private static final String PU = EMNames.EMN1;

    public CrudTeste1() {
        super(br.data.entity.Teste1.class);
    }

    public List<br.data.entity.Teste1> SelectByNome(String nome) {
        List<br.data.entity.Teste1> lista;
        try {
            lista= getEntityManager().createNamedQuery("Teste1.findByNome").setParameter("nome", "%" + nome.toUpperCase() + "%").getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }

}
