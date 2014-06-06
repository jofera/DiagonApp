/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import dao.MedicoFacade;
import entity.Usuario;
import dao.UsuarioFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gonzalo
 */
@Named(value = "authBean")
@RequestScoped
public class AuthBean {
    @EJB
    private MedicoFacade medicoFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    private String username, password;
    FacesContext facesContext = FacesContext.getCurrentInstance();

    /**
     * Creates a new instance of AuthBean
     */
    public AuthBean() {
    }
    
    /*** Funcion login de usuario a partir de DNI y Contraseña ***/
    public void Login() throws IOException{
        FacesContext context=FacesContext.getCurrentInstance();
        String relativePath = context.getExternalContext().getRequestContextPath();
        Usuario user;
        if(!username.isEmpty() && !password.isEmpty()){
            user = usuarioFacade.findUsuarioByDNI(username);
            if(user != null && user.getPassword().equals(password)){
                /*** El login es correcto, doy acceso ***/
                if(medicoFacade.findUsuarioByDNI(user.getId()) != null)
                    facesContext.getExternalContext().getSessionMap().put("userRol", "medico");
                else
                    facesContext.getExternalContext().getSessionMap().put("userRol", "paciente");
                facesContext.getExternalContext().getSessionMap().put("userName", username);
                context.getExternalContext().redirect(relativePath + "/index.jsf");
            }
        }
        context.getExternalContext().redirect(relativePath + "/login.jsf");
    }
    
    /*** Devuelve una instancia del usuario logeado ***/
    public Usuario getLogedUser(){
        this.facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String userName = (String) httpServletRequest.getSession().getAttribute("userName");
        Usuario user = usuarioFacade.findUsuarioByDNI(userName);
        return user;
    }
    
    /*** Devuelve nombre y apellidos del usuario logeado ***/
    public String getLogedUserName(){
        String fullname = "";
        Usuario user = this.getLogedUser();
        if(user != null)
            fullname = user.getNombre().concat(" ").concat(user.getApellidos());
        return fullname;
    }
    
    /*** Devuelve el rol del usuario conectado al sistema ***/
    public String getLogedUserRol(){
        this.facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String userRol = (String) httpServletRequest.getSession().getAttribute("userRol");
        return userRol;
    }
    
    /*** Comprueba si el usuario está logeado, si no lo manda al login form ***/
    public void checkUserSession() throws IOException{
        FacesContext context=FacesContext.getCurrentInstance();
        if(!isUserLoged())
            context.getExternalContext().redirect("login.jsf");
            
    }
    
    /*** Comprueba si el usuario está logeado como medico ***/
    public void checkUserIsMedico() throws IOException{
        this.checkUserSession();
        FacesContext context=FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
        HttpServletRequest httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String userRol = (String) httpServletRequest.getSession().getAttribute("userRol");
        if(!userRol.equals("medico"))
		nav.performNavigation("error/access-denied");
    }
    
    /*** Comprueba si el usuario está logeado como paciente ***/
    public void checkUserIsPaciente() throws IOException{
        this.checkUserSession();
        FacesContext context=FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
        HttpServletRequest httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String userRol = (String) httpServletRequest.getSession().getAttribute("userRol");
        if(!userRol.equals("paciente"))
		nav.performNavigation("error/access-denied");
    }
    
    public Boolean isUserLoged(){
        return (this.getLogedUser() != null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
