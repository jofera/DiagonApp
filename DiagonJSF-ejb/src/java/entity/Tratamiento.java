/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "tratamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tratamiento.findAll", query = "SELECT t FROM Tratamiento t"),
    @NamedQuery(name = "Tratamiento.findById", query = "SELECT t FROM Tratamiento t WHERE t.id = :id"),
    @NamedQuery(name = "Tratamiento.findByMedico", query = "SELECT t FROM Tratamiento t WHERE t.medico = :medico"),
    @NamedQuery(name = "Tratamiento.findByDduracion", query = "SELECT t FROM Tratamiento t WHERE t.dduracion = :dduracion")})
public class Tratamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "medico")
    private int medico;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "finicio")
    private String finicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dduracion")
    private int dduracion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;

    public Tratamiento() {
    }

    public Tratamiento(Integer id) {
        this.id = id;
    }

    public Tratamiento(Integer id, int medico, String finicio, int dduracion, String descripcion) {
        this.id = id;
        this.medico = medico;
        this.finicio = finicio;
        this.dduracion = dduracion;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMedico() {
        return medico;
    }

    public void setMedico(int medico) {
        this.medico = medico;
    }

    public String getFinicio() {
        return finicio;
    }

    public void setFinicio(String finicio) {
        this.finicio = finicio;
    }

    public int getDduracion() {
        return dduracion;
    }

    public void setDduracion(int dduracion) {
        this.dduracion = dduracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tratamiento)) {
            return false;
        }
        Tratamiento other = (Tratamiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tratamiento[ id=" + id + " ]";
    }
    
}
