/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package citas;

import dao.CitaFacade;
import dao.MedicoFacade;
import dao.PacienteFacade;
import dao.UsuarioFacade;
import entity.Cita;
import entity.Medico;
import entity.Paciente;
import entity.Usuario;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author user
 */
@Named(value = "citasBean")
@RequestScoped
public class CitasBean {
    @EJB
    private PacienteFacade pacienteFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private MedicoFacade medicoFacade;
    @EJB
    private CitaFacade citaFacade;
    

    private Cita nuevaCita, editarCita;
    private List<Medico> listaMedicos;
    private List<Usuario> listaUsuarios;
    private List<Cita> listaCitas;
    
    private int medico,usuario;
    
    /**
     * Creates a new instance of CitasBean
     */
    public CitasBean() {
    }
      
    @PostConstruct
    public void Init(){
        nuevaCita = new Cita();
        editarCita = new Cita();
        listaMedicos = medicoFacade.findAll();
        listaUsuarios = usuarioFacade.findAll();
        listaCitas = citaFacade.findAll();
    }

    public Cita getNuevaCita() {
        return nuevaCita;
    }

    public void setNuevaCita(Cita nuevaCita) {
        this.nuevaCita = nuevaCita;
    }

    public List<Medico> getListaMedicos() {
        return listaMedicos;
    }
    

    public void setListaMedicos(List<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Cita> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(List<Cita> listaCitas) {
        this.listaCitas = listaCitas;
    }

    public String crearCita(){
        nuevaCita.setIdMedico(medicoFacade.find(medico));
        nuevaCita.setIdUsuario(usuarioFacade.find(usuario));
        citaFacade.create(nuevaCita);
        listaCitas = citaFacade.findAll();
        return "listarCitas.jsf";
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public MedicoFacade getMedicoFacade() {
        return medicoFacade;
    }

    public void setMedicoFacade(MedicoFacade medicoFacade) {
        this.medicoFacade = medicoFacade;
    }

    public CitaFacade getCitaFacade() {
        return citaFacade;
    }

    public void setCitaFacade(CitaFacade citaFacade) {
        this.citaFacade = citaFacade;
    }

    public int getMedico() {
        return medico;
    }

    public void setMedico(int medico) {
        this.medico = medico;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
    public void borrarCita(int c){
        citaFacade.remove(citaFacade.find(c));
        listaCitas= citaFacade.findAll();
    }
    public String editRedirect(int id){
        editarCita = citaFacade.find(id);
        usuario = editarCita.getIdUsuario().getId();
        medico = editarCita.getIdMedico().getId();
        return "editarCita.jsf";
    }

    public Cita getEditarCita() {
        return editarCita;
    }

    public void setEditarCita(Cita editarCita) {
        this.editarCita = editarCita;
    }
    
    public String editarCita(){
        System.out.println(editarCita.getId());
        editarCita.setIdMedico(medicoFacade.find(medico));
        editarCita.setIdUsuario(usuarioFacade.find(usuario));
        citaFacade.edit(editarCita);
        listaCitas = citaFacade.findAll();
        return "listarCitas.jsf";
    }
    
    public String pedirCita(int usuario){
        Usuario u = usuarioFacade.findUsuarioById(usuario);
        Paciente paciente = pacienteFacade.findPacienteByUserId(usuario);
        Medico medicoAsociado = paciente.getIdMedico();
        nuevaCita = new Cita();
        nuevaCita.setEstado(Cita.EstadoCita.PENDIENTE.ordinal());
        nuevaCita.setIdMedico(medicoAsociado);
        nuevaCita.setConsulta(medicoAsociado.getConsulta());
        nuevaCita.setIdUsuario(u);
        return "/citas/crearCita.jsf";
    }
    
    public String pedirCitaSubmit(){
        citaFacade.create(nuevaCita);
        listaCitas = citaFacade.findAll();
        return "../index.jsf";
    }
    
}
