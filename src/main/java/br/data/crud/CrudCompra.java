/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.data.crud;

import br.data.entity.Compra;
import br.data.entity.Itemcompra;
import br.data.entity.ItemcompraPK;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author alexandrelerario
 */
public class CrudCompra extends AbstractCrud<br.data.entity.Compra> {

    private EntityManager em;

    public CrudCompra() {
        super(br.data.entity.Compra.class);
    }

    @Override
    public Exception persist(Compra compra) { //precisamos de um persist especifico neste caso
        try {
            getEntityManager().getTransaction().begin();
            Collection<br.data.entity.Itemcompra> litem = compra.getItemcompraCollection();
            compra.setItemcompraCollection(new ArrayList<br.data.entity.Itemcompra>());
            getEntityManager().persist(compra);
            getEntityManager().getTransaction().commit();
            
            if (litem.size() > 0) {
                getEntityManager().getTransaction().begin();
                compra.setItemcompraCollection(litem);
                for (Itemcompra itemcompra : litem) {
                    itemcompra.setCompra1(compra);
                    itemcompra.setItemcompraPK(new ItemcompraPK(compra.getCodigo(), itemcompra.getProduto1().getCodigo()));
                    getEntityManager().persist(itemcompra);
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
