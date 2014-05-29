/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Azahar
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByIdUsuario", query = "SELECT r FROM Roles r WHERE r.rolesPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "Roles.findByIdRol", query = "SELECT r FROM Roles r WHERE r.rolesPK.idRol = :idRol")})
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesPK rolesPK;

    public Roles() {
    }

    public Roles(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public Roles(int idUsuario, int idRol) {
        this.rolesPK = new RolesPK(idUsuario, idRol);
    }

    public RolesPK getRolesPK() {
        return rolesPK;
    }

    public void setRolesPK(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesPK != null ? rolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.rolesPK == null && other.rolesPK != null) || (this.rolesPK != null && !this.rolesPK.equals(other.rolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Roles[ rolesPK=" + rolesPK + " ]";
    }
    
}
