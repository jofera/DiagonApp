/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Tratamiento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Azahar
 */
@Stateless
public class TratamientoFacade extends AbstractFacade<Tratamiento> {
    @PersistenceContext(unitName = "DiagonJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TratamientoFacade() {
        super(Tratamiento.class);
    }
    
    /* Devuelve una lista de tratamientos a partir de un PACIENTE */
    public List<Tratamiento> findTratamientosByPaciente(int paciente){
       List<Tratamiento> listaTratamientos = em.createNamedQuery("Tratamiento.findByPaciente").setParameter("idPaciente", paciente).getResultList();
       return listaTratamientos.isEmpty() ? null : listaTratamientos;
   }
}
