package tratamientos;
import dao.TratamientoFacade;
import entity.Tratamiento;
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
    private TratamientoFacade tratamientoFacade;
    
    //Recoger del form los valores
    private Integer idmedico;
    private String fecha;
    private Integer duracion;
    private String descripcion;
    
    //Creacion de los tratamientos
    private Tratamiento tratamientoActual;
    private Tratamiento tratamientoEditar;
    private Tratamiento tratamientoNuevo;
    private List<Tratamiento> listaTratamientos;    
      
    public tratamientoBean() {        
   
    }
    
     @PostConstruct
     public void Init(){
         listaTratamientos = tratamientoFacade.findAll();
         tratamientoNuevo = new Tratamiento();
         tratamientoEditar = new Tratamiento();
         tratamientoActual = new Tratamiento();
    }

    public Tratamiento getTratamientoEditar() {
        return tratamientoEditar;
    }

    public void setTratamientoEditar(Tratamiento trata) {
        this.tratamientoEditar = trata;
    }

    public Tratamiento getTratamientoNuevo() {
        return tratamientoNuevo;
    }

    public void setTratamientoNuevo(Tratamiento trata) {
        this.tratamientoNuevo = trata;
    }

    public Integer getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(Integer idmedico) {
        this.idmedico = idmedico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
    
    public String crearTratamiento() {
        tratamientoNuevo.setMedico(idmedico);
        tratamientoNuevo.setFinicio(fecha);
        tratamientoNuevo.setDduracion(duracion);
        tratamientoNuevo.setDescripcion(descripcion);
        tratamientoFacade.create(tratamientoNuevo);  
        System.out.println(tratamientoNuevo.getId());
        listaTratamientos = tratamientoFacade.findAll();    
        return goToListarTratamientos();
     }    

    public void editarTratamiento(int id){
        tratamientoFacade.edit(tratamientoActual);
        setListaTratamientos(tratamientoFacade.findAll());
        goToListarTratamientos();
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


