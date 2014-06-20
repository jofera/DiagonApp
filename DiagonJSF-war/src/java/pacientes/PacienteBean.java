/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacientes;

import dao.MedicoFacade;
import dao.PacienteFacade;
import dao.UsuarioFacade;
import entity.Medico;
import entity.Paciente;
import entity.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Luby
 */
@Named(value = "pacienteBean")
@RequestScoped
public class PacienteBean {
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private MedicoFacade medicoFacade;
    @EJB
    private PacienteFacade pacienteFacade;
    
    
   //Recoger del form los valores
    private Medico medico;
    private Usuario paciente;
    
    private Integer idMedico;
    private Integer idUsuario;
    
    //Creacion de los tratamientos
    private Paciente pacienteActual;
    private Paciente pacienteNuevo;
    private List<Paciente> listaPacientes; 
    private List<Usuario> listaUsuarios;
    private List<Medico> listaMedicos;
      
    
    
    @PostConstruct
     public void Init(){
         listaPacientes = pacienteFacade.findAll();
         listaMedicos = medicoFacade.findAll();
         listaUsuarios = usuarioFacade.findAll();
         pacienteNuevo = new Paciente();
         pacienteActual = new Paciente();
    }

    //------------------------------------------------------------------------------Getters y Setters-----------------------------------------------------------------------------------------------
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
   
    

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuario paciente) {
        this.paciente = paciente;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Paciente getPacienteActual() {
        return pacienteActual;
    }

    public void setPacienteActual(Paciente pacienteActual) {
        this.pacienteActual = pacienteActual;
    }

    public Paciente getPacienteNuevo() {
        return pacienteNuevo;
    }

    public void setPacienteNuevo(Paciente pacienteNuevo) {
        this.pacienteNuevo = pacienteNuevo;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Medico> getListaMedicos() {
        return listaMedicos;
    }

    public void setListaMedicos(List<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }
   
    
    
    
    /**
     * Creates a new instance of PacienteBean
     */
    public PacienteBean() {
    }
    
    
    public String crearPaciente(){        
        medico = medicoFacade.find(idMedico);
        paciente = usuarioFacade.find(idUsuario);
        
        pacienteNuevo.setIdMedico(medico);
        pacienteNuevo.setIdUsuario(paciente);
        
        pacienteFacade.create(pacienteNuevo);
        listaPacientes = pacienteFacade.findAll();
        
        return "listarPacientes.jsf";
    }
    
    public String editarPaciente(int id){ 
        medico = medicoFacade.find(idMedico);
        paciente = usuarioFacade.find(idUsuario);
        
        pacienteNuevo.setIdMedico(medico);
        pacienteNuevo.setIdUsuario(paciente);
        
        pacienteFacade.edit(pacienteNuevo);
        listaPacientes = pacienteFacade.findAll();
        
        return "listarPacientes.jsf";
    }
    
    public String botonEditarPaciente(int id){
        setPacienteActual(pacienteFacade.find(id));
        return "editarPaciente.jsf"; 
    }
    
    public Paciente obtenerPacienteFromUsuario(int usuario){
        return pacienteFacade.findPacienteByUserId(usuario);
    }
}
