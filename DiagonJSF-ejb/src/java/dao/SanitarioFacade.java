/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Sanitario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Azahar
 */
@Stateless
public class SanitarioFacade extends AbstractFacade<Sanitario> {
    @PersistenceContext(unitName = "DiagonJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SanitarioFacade() {
        super(Sanitario.class);
    }
    
}
