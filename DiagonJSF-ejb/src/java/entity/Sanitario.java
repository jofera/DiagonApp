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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Azahar
 */
@Entity
@Table(name = "sanitario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sanitario.findAll", query = "SELECT s FROM Sanitario s"),
    @NamedQuery(name = "Sanitario.findByIdUsuario", query = "SELECT s FROM Sanitario s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "Sanitario.findByEspecialidad", query = "SELECT s FROM Sanitario s WHERE s.especialidad = :especialidad"),
    @NamedQuery(name = "Sanitario.findByConsulta", query = "SELECT s FROM Sanitario s WHERE s.consulta = :consulta"),
    @NamedQuery(name = "Sanitario.findByTelefonoConsulta", query = "SELECT s FROM Sanitario s WHERE s.telefonoConsulta = :telefonoConsulta")})
public class Sanitario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "especialidad")
    private int especialidad;
    @Size(max = 20)
    @Column(name = "consulta")
    private String consulta;
    @Column(name = "telefono_consulta")
    private Integer telefonoConsulta;

    public Sanitario() {
    }

    public Sanitario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Sanitario(Integer idUsuario, int especialidad) {
        this.idUsuario = idUsuario;
        this.especialidad = especialidad;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public Integer getTelefonoConsulta() {
        return telefonoConsulta;
    }

    public void setTelefonoConsulta(Integer telefonoConsulta) {
        this.telefonoConsulta = telefonoConsulta;
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
        if (!(object instanceof Sanitario)) {
            return false;
        }
        Sanitario other = (Sanitario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sanitario[ idUsuario=" + idUsuario + " ]";
    }
    
}
