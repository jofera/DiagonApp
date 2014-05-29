/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Azahar
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByIdUsuario", query = "SELECT p FROM Paciente p WHERE p.idUsuario = :idUsuario"),
    @NamedQuery(name = "Paciente.findByNuss", query = "SELECT p FROM Paciente p WHERE p.nuss = :nuss"),
    @NamedQuery(name = "Paciente.findByMedicoAsignado", query = "SELECT p FROM Paciente p WHERE p.medicoAsignado = :medicoAsignado")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nuss")
    private int nuss;
    @Column(name = "medico_asignado")
    private Integer medicoAsignado;

    public Paciente() {
    }

    public Paciente(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Paciente(Integer idUsuario, int nuss) {
        this.idUsuario = idUsuario;
        this.nuss = nuss;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNuss() {
        return nuss;
    }

    public void setNuss(int nuss) {
        this.nuss = nuss;
    }

    public Integer getMedicoAsignado() {
        return medicoAsignado;
    }

    public void setMedicoAsignado(Integer medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Paciente[ idUsuario=" + idUsuario + " ]";
    }
    
}
