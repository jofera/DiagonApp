/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medicos;

import dao.EspecialidadFacade;
import dao.MedicoFacade;
import dao.UsuarioFacade;
import entity.Especialidad;
import entity.Medico;
import entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Gonzalo
 */
@Named(value = "medicoBean")
@RequestScoped
public class medicoBean implements Serializable {
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private EspecialidadFacade especialidadFacade;
    @EJB
    private MedicoFacade medicoFacade;
    
    /**** Listas de objetos  ****/
    private List<Medico> listaMedicos;
    private List<Especialidad> listaEspecialidades;
    private List<Usuario> listaUsuarios;
    
    /**** Objetos para el CRUD ****/
    private Medico nuevoMedico, editarMedico, borrarMedico;
    
    /**** Variables ****/
    private int idUsuario, idEspecialidad;
    private String consulta, telefono;


    /**
     * Creates a new instance of medicosBean
     */
    public medicoBean() {
    }
    
     @PostConstruct
     public void Init(){
         listaMedicos = medicoFacade.findAll();
         listaEspecialidades = especialidadFacade.findAll();
         listaUsuarios = usuarioFacade.findAll();
         nuevoMedico = new Medico();
         editarMedico = new Medico();
    }
    
    /**** Funciones CRUD ****/
    public String crearMedico(){
        nuevoMedico.setIdUsuario(usuarioFacade.find(idUsuario));
        nuevoMedico.setIdEspecialidad(especialidadFacade.find(idEspecialidad));
        medicoFacade.create(nuevoMedico);
        listaMedicos = medicoFacade.findAll();
        return this.goToListadoMedicos();
    }
    
    public String editarMedico(){
        editarMedico.setIdUsuario(usuarioFacade.find(this.idUsuario));
        editarMedico.setIdEspecialidad(especialidadFacade.find(this.idEspecialidad));
        medicoFacade.edit(editarMedico);
        listaMedicos = medicoFacade.findAll();
        return this.goToListadoMedicos();
    }
    
    public String borrarMedico(int id){
        medicoFacade.remove(medicoFacade.find(id));
        listaMedicos = medicoFacade.findAll();
        return this.goToListadoMedicos();
    }
    
    public String goToEditarMedico(int id){
        editarMedico = medicoFacade.find(id);
        idUsuario = editarMedico.getIdUsuario().getId();
        idEspecialidad = editarMedico.getIdEspecialidad().getId();
        return "editarMedico.jsf";
    }
    
    public String goToListadoMedicos(){
        return "listarMedicos.jsf";
    }
    
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public EspecialidadFacade getEspecialidadFacade() {
        return especialidadFacade;
    }

    public void setEspecialidadFacade(EspecialidadFacade especialidadFacade) {
        this.especialidadFacade = especialidadFacade;
    }

    public MedicoFacade getMedicoFacade() {
        return medicoFacade;
    }

    public List<Medico> getListaMedicos() {
        return listaMedicos;
    }

    public void setListaMedicos(List<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    public Medico getNuevoMedico() {
        return nuevoMedico;
    }

    public void setNuevoMedico(Medico nuevoMedico) {
        this.nuevoMedico = nuevoMedico;
    }

    public List<Especialidad> getListaEspecialidades() {
        return listaEspecialidades;
    }

    public void setListaEspecialidades(List<Especialidad> listaEspecialidades) {
        this.listaEspecialidades = listaEspecialidades;
    }
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Medico getEditarMedico() {
        return editarMedico;
    }

    public void setEditarMedico(Medico editarMedico) {
        this.editarMedico = editarMedico;
    }    
}
