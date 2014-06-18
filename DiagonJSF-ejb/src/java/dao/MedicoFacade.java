/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Medico;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Azahar
 */
@Stateless
public class MedicoFacade extends AbstractFacade<Medico> {
    @PersistenceContext(unitName = "DiagonJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicoFacade() {
        super(Medico.class);
    }
    public Medico findUsuarioByDNI(int usuario){
       List<Medico> listaMedicos = em.createNamedQuery("Medico.findByUsuarioId").setParameter("idUsuario", usuario).getResultList();
       return listaMedicos.isEmpty()?null: listaMedicos.get(0);
   }
    
}
