/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.data.crud;

import br.data.entity.Itemvenda;
import br.data.entity.Venda;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author alexandrelerario
 */
public class CrudVenda extends AbstractCrud<br.data.entity.Venda> {

    private EntityManager em;

    public CrudVenda() {
        super(br.data.entity.Venda.class);
    }

    @Override
    public Exception persist(Venda venda) { //precisamos de um persist especifico neste caso
        try {
            getEntityManager().getTransaction().begin();
            Collection<br.data.entity.Itemvenda> litem = venda.getItemvendaCollection();
            venda.setItemvendaCollection(new ArrayList<br.data.entity.Itemvenda>());
            getEntityManager().persist(venda);
            getEntityManager().getTransaction().commit();
            
            if (litem.size() > 0) {
                getEntityManager().getTransaction().begin();
                venda.setItemvendaCollection(litem);
                for (Itemvenda itemvenda : litem) {
                    itemvenda.setVenda(venda);
                    getEntityManager().persist(itemvenda);
                }
                 getEntityManager().getTransaction().commit();
            }
           
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }

}
