package tratamientos;
import dao.MedicoFacade;
import dao.TratamientoFacade;
import dao.UsuarioFacade;
import entity.Medico;
import entity.Tratamiento;
import entity.Usuario;
import java.util.Date;
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
    private MedicoFacade medicoFacade;   
    private TratamientoFacade tratamientoFacade;
    
    //Recoger del form los valores
    private Usuario medico;
    private Date fecha;
    private Integer duracion;
    private String descripcion;
    
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
    }

    public Tratamiento getTratamientoNuevo() {
        return tratamientoNuevo;
    }

    public void setTratamientoNuevo(Tratamiento trata) {
        this.tratamientoNuevo = trata;
    }

    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }   

    public Tratamiento getTratamientoActual() {
        return tratamientoActual;
    }

    public void setTratamientoActual(Tratamiento Trata) {
        tratamientoActual = Trata;
    }

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

    public void setListaMedicos(List<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }
    
    public String crearTratamiento() {
        tratamientoNuevo.setIdMedico(medico);
        tratamientoNuevo.setFechaInicio(fecha);
        tratamientoNuevo.setDuracion(duracion);
        tratamientoNuevo.setDescripcion(descripcion);
        tratamientoFacade.create(tratamientoNuevo);  
        System.out.println(tratamientoNuevo.getId());
        listaTratamientos = tratamientoFacade.findAll();    
        return goToListarTratamientos();
     }    

    public String editarTratamiento(int id){
        tratamientoFacade.edit(tratamientoActual);
        setListaTratamientos(tratamientoFacade.findAll());
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
    }  
}


