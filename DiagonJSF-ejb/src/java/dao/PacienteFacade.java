/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Paciente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Azahar
 */
@Stateless
public class PacienteFacade extends AbstractFacade<Paciente> {
    @PersistenceContext(unitName = "DiagonJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteFacade() {
        super(Paciente.class);
    }
    
    public Paciente findPacienteByUserId(int usuario){
       List<Paciente> listaPacientes = em.createNamedQuery("Paciente.findByUsuarioId").setParameter("idUsuario", usuario).getResultList();
       return listaPacientes.isEmpty()?null: listaPacientes.get(0);
        
    }
    
}
