/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import dao.UsuarioFacade;
import entity.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Luby
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean{

    @EJB
    private UsuarioFacade usuarioFacade;

    
    //----------------------------------------------------------------Campos para crear usuarios---------------------------------------------------------------------------------------------
    
    private Integer id;
    private String nombre;
    private String apellidos;
    private Date fecha_nacimiento;
    private String dni;    
    private String direccion;
    private String telefono;    
    private String password;
    
    //--------------------------------------------------------------------Para crear/editar usuarios-------------------------------------------------------------------------------------------------
        
    private Usuario nuevoUsuario;
    private Usuario editUsuario;
    private List<Usuario> listaUsuarios;
    

   
    @PostConstruct
     public void Init(){         
         listaUsuarios = usuarioFacade.findAll();
         nuevoUsuario = new Usuario();
         editUsuario = new Usuario();
         
     }
    
    //-----------------------------------------------------------------------Getters Y Setters------------------------------------------------------------------------------------------------
    public Usuario getEditUsuario() {
        return editUsuario;
    }

    public void setEditUsuario(Usuario editUsuario) {
        this.editUsuario = editUsuario;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    
    //------------------------------------------------------------------Para hacer CRUD------------------------------------------------------------------------------------------------------
   
    
    public String crearUsuario() {
        usuarioFacade.create(nuevoUsuario);
        listaUsuarios = usuarioFacade.findAll();
    return "listarUsuarios.jsf";
    }
    
    public String borrarUsuario(int id){
        usuarioFacade.remove(usuarioFacade.find(id));
        setListaUsuarios(usuarioFacade.findAll());
        return "listarUsuarios.jsf";
        
    }
   
   public String modificarUsuario(int id){
       usuarioFacade.edit(editUsuario);
       return "listarUsuarios.jsf";
   }
   
   public String botonModificarUsuario(int id){
       setEditUsuario(usuarioFacade.find(id));
       return "editarUsuario.jsf";
   }
   
   public String modificarMisDatos(int id){
       //setEditUsuario(usuarioFacade.find(id));
       setEditUsuario(usuarioFacade.findUsuarioById(id));
       return "usuarios/editarUsuario.jsf";
   }
   
    public void modificarMisDatosSubmit(int id) throws IOException{
       usuarioFacade.edit(editUsuario);
       ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
       context.redirect("../index.jsf");
   }
    
    
}
