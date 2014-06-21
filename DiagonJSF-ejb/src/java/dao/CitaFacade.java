/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cita;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Azahar
 */
@Stateless
public class CitaFacade extends AbstractFacade<Cita> {
    @PersistenceContext(unitName = "DiagonJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaFacade() {
        super(Cita.class);
    }
    
    public List<Cita> obtenerCitasUsuario(int usuario){
       List<Cita> listaCitas = em.createNamedQuery("Cita.findByUsuario").setParameter("idUsuario", usuario).getResultList();
       return listaCitas.isEmpty() ? null: listaCitas;
    }
    
}
