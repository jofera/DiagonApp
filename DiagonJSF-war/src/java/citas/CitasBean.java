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
import entity.Cita.EstadoCita;
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
    private Usuario u;
    
    private int medico,usuario;
    
    private EstadoCita[] listaEstados;

    public EstadoCita[] getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(EstadoCita[] listaEstados) {
        this.listaEstados = listaEstados;
    }
    
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
        listaEstados = EstadoCita.values();
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
        //nuevaCita = new Cita();
        //nuevaCita.setEstado(Cita.EstadoCita.PENDIENTE.ordinal());
        nuevaCita.setIdMedico(medicoAsociado);
        nuevaCita.setConsulta(medicoAsociado.getConsulta());
        nuevaCita.setIdUsuario(u);
        return "/citas/crearCita.jsf";
    }
    
    public String pedirCitaSubmit(int usuario){
        Usuario u = usuarioFacade.findUsuarioById(usuario);
        Paciente paciente = pacienteFacade.findPacienteByUserId(usuario);
        Medico medicoAsociado = paciente.getIdMedico();
        //nuevaCita = new Cita();
        //nuevaCita.setEstado(Cita.EstadoCita.PENDIENTE.ordinal());
        nuevaCita.setIdMedico(medicoAsociado);
        nuevaCita.setConsulta(medicoAsociado.getConsulta());
        nuevaCita.setIdUsuario(u);
        citaFacade.create(nuevaCita);
        listaCitas = citaFacade.findAll();
        return "/index.jsf";
    }
    
    public String consultarMisCitas(int usuario){
        u = usuarioFacade.findUsuarioById(usuario);
        listaCitas = citaFacade.obtenerCitasUsuario(usuario);
        return "/citas/listarCitas.jsf";
    }
    
    public String[] obtenerEstados(){
        String[] estadoString = new String[EstadoCita.values().length];
        EstadoCita[] estados = EstadoCita.values();
        for(EstadoCita estado : estados){
            estadoString[estadoString.length] = estado.toString();
        }
        return estadoString;
    }
    
    public String estadoToString(int estado){
        return Cita.EstadoCita.values()[estado].toString();
    }
    
    public int estadoToInt(EstadoCita estado){
        return estado.ordinal();
    }
    
    /* Método para que los pacientes puedan cancelar sus citas */
    public void cancelarCita(int cita){
        editarCita = citaFacade.find(cita);
        editarCita.setEstado(EstadoCita.CANCELADA.ordinal());
        citaFacade.edit(editarCita);
        listaCitas = citaFacade.findAll();
   }
    
    /* Método para que los medicos puedan denegar las citas */
    public void denegarCita(int cita){
        editarCita = citaFacade.find(cita);
        editarCita.setEstado(EstadoCita.DENEGADA.ordinal());
        citaFacade.edit(editarCita);
        listaCitas = citaFacade.findAll();
    }
    
    /* Método para que los medicos puedan aceptar las citas */
    public void aceptarCita(int cita){
        editarCita = citaFacade.find(cita);
        editarCita.setEstado(EstadoCita.ACEPTADA.ordinal());
        citaFacade.edit(editarCita);
        listaCitas = citaFacade.findAll();
    }
    
}
