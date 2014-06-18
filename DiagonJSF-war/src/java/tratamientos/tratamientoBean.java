package tratamientos;
import dao.MedicoFacade;
import dao.TratamientoFacade;
import dao.UsuarioFacade;
import entity.Medico;
import entity.Tratamiento;
import entity.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 * @author Azahar
 */

@Named(value = "tratamientoBean")
@RequestScoped
public class tratamientoBean {
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private MedicoFacade medicoFacade;   
    @EJB
    private TratamientoFacade tratamientoFacade;
    
    //Recoger del form los valores
    private int idMedico;
    private int idPaciente;    
    private int tamLista;
       
    private Medico medico;
    private Usuario paciente;
    
    //Creacion de los tratamientos
    private Tratamiento tratamientoActual;
    private Tratamiento tratamientoNuevo;
    private List<Tratamiento> listaTratamientos; 
    private List<Usuario> listaUsuarios;
    private List<Medico> listaMedicos;
      
    public tratamientoBean() {        
   
    }
    
     @PostConstruct
     public void Init(){
         listaTratamientos = tratamientoFacade.findAll();
         listaMedicos = medicoFacade.findAll();
         listaUsuarios = usuarioFacade.findAll();
         tratamientoNuevo = new Tratamiento();
         tratamientoActual = new Tratamiento();
         tamLista = listaTratamientos.size();
    }

    //Getter and setter de los tratamientos y usuarios
    public Tratamiento getTratamientoActual() {
        return tratamientoActual;
    }
        
    public void setTratamientoActual(Tratamiento Trata) {
        tratamientoActual = Trata;
    }     
      
    public Tratamiento getTratamientoNuevo() {
        return tratamientoNuevo;
    }

    public void setTratamientoNuevo(Tratamiento trata) {
        this.tratamientoNuevo = trata;
    }    
    
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

    //Getter and setter para los campos del formulario
    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
 
    //Getter and setter de las listas.
    public List<Tratamiento> getListaTratamientos() {
        return listaTratamientos;
    }

    public void setListaTratamientos(List<Tratamiento> lista) {
        listaTratamientos = lista;
    }
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> lista) {
        this.listaUsuarios = lista;
    }

    public List<Medico> getListaMedicos() {
        return listaMedicos;
    }

    public void setListaMedicos(List<Medico> lista) {
        this.listaMedicos = lista;
    }

    public int getTamLista() {
        return tamLista;
    }

    public void setTamLista(int tam) {
        this.tamLista = tam;
    }
        
    public String crearTratamiento() {
        //Con los ids recogidos del formulario buscamos el medico y el usuario correspondiente.        
        medico = medicoFacade.find(idMedico);
        paciente = usuarioFacade.find(idPaciente);
        
        //Asignamos todos los valores al tratamiento nuevo
        tratamientoNuevo.setIdMedico(medico);
        tratamientoNuevo.setIdPaciente(null); //CAMBIARRRRR!!!!!!
      
        //creamos el nuevo tratamiento en la BD
        tratamientoFacade.create(tratamientoNuevo);  
        
        //Volvemos a buscar todos los tratamientos para que se actualice la lista.
        listaTratamientos = tratamientoFacade.findAll();    
        //Mostramos en pantalla la lista de tratamientos.
        return goToListarTratamientos();
     }    

    public String editarTratamiento(){
        
        //Con los ids recogidos del formulario buscamos el medico y el usuario correspondiente.        
        medico = medicoFacade.find(idMedico);
        paciente = usuarioFacade.find(idPaciente);
        
        //Asignamos todos los valores al tratamiento nuevo  
        tratamientoActual.setIdMedico(medico);
        tratamientoActual.setIdPaciente(null); //CAMBIAAAARRR
        
        //Editamos el tratamiento en la BD
        tratamientoFacade.edit(tratamientoActual);
        
        //Volvemos a buscar todos los tratamientos para que se actualice la lista.
        setListaTratamientos(tratamientoFacade.findAll());
        tamLista = listaTratamientos.size();
        
        //Mostramos en pantalla la lista de tratamientos.
        return goToListarTratamientos();
    }   
    
    public String goToEditarTratamiento(int id) {   
        setTratamientoActual(tratamientoFacade.find(id));
        System.out.println(tratamientoActual.getId());
        return "editarTratamiento.jsf";          
    } 
    
    public String goToListarTratamientos(){
        return "listarTratamientos.jsf";
    }
    
    public void borrarTratamiento(int id){
        tratamientoFacade.remove(tratamientoFacade.find(id));
        setListaTratamientos(tratamientoFacade.findAll());
        tamLista = listaTratamientos.size();
    }  
    
}


