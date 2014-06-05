/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Rol;
import entity.Roles;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gonzalo
 */
@Stateless
public class RolesFacade extends AbstractFacade<Roles> {
    @PersistenceContext(unitName = "DiagonJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesFacade() {
        super(Roles.class);
    }
    /*** Lista de roles del usuario. FALTA IMPLEMENTAR PORQUE HAY QUE TOCAR ENTIDAD ***/
    public List<Rol> getUserRolList(int user){
        List<Rol> listaRoles = new ArrayList<Rol>();
        return listaRoles;
    }
    
}
